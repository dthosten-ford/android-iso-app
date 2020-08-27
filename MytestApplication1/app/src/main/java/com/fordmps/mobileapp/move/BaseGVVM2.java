package com.fordmps.mobileapp.move;

/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.StringRes;
import androidx.core.util.Pair;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ford.androidutils.SharedPrefsUtil;
import com.ford.androidutils.ui.glide.GlideProvider;
import com.ford.applink.managers.ActiveVhaAlertsManager;
import com.ford.applink.providers.VcsAppLinkCapabilityProvider;
import com.ford.ngsdnuser.providers.AccountInfoProvider;
import com.ford.ngsdnvehicle.providers.NgsdnVehicleProvider;
import com.ford.paak.PaakAdapter;
import com.ford.recall.fsa.repo.common.VehicleRecallAndFsa;
import com.ford.rxutils.RxSchedulerProvider;
import com.ford.rxutils.schedulers.RxSchedulingHelper;
import com.ford.rxutils.schedulers.Threads;
import com.ford.utils.TextUtils;
import com.ford.vehiclecommon.enums.Source;
import com.ford.vehiclecommon.models.GarageVehicleProfile;
import com.ford.vehiclecommon.models.IntegrationUtil;
import com.ford.vehiclecommon.models.VehicleAuthStatus;
import com.ford.vehiclecommon.models.VehicleStatus;
import com.ford.vehiclehealth.models.VehicleAlertResponse;
import com.ford.vinlookup.managers.VinLookupProvider;
import com.ford.xapi.models.response.VehicleCapability;
import com.fordmps.core.BaseLifecycleViewModel;
import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.find.categories.Country;
import com.fordmps.mobileapp.move.managers.ChargingStatusUtil;
import com.fordmps.mobileapp.shared.configuration.ConfigurationProvider;
import com.fordmps.mobileapp.shared.datashare.ResourceProvider;
import com.fordmps.mobileapp.shared.datashare.TransientDataProvider;
import com.fordmps.mobileapp.shared.datashare.usecases.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.FindVehicleLocationUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.GarageVehicleSelectedVinUseCase;
import com.fordmps.mobileapp.shared.events.StartActivityEvent;
import com.fordmps.mobileapp.shared.events.UnboundViewEventBus;
import com.fordmps.mobileapp.shared.managers.VehicleCapabilitiesManager;
import com.fordmps.mobileapp.shared.providers.VehicleImageUrlProvider;
import com.fordmps.mobileapp.shared.providers.VehicleInfoProvider;
import com.fordmps.mobileapp.shared.utils.BitmapImageUtil;
import com.fordmps.mobileapp.shared.utils.ErrorMessageUtil;
import com.fordmps.mobileapp.shared.utils.GarageTabOrder;
import com.fordmps.viewutils.R;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import kotlin.Lazy;

import static com.ford.vcs.models.Feature.FeatureNames.USER_RESET;

public abstract class BaseGarageVehicleViewModel extends BaseLifecycleViewModel implements BrandGarageBackground, GarageViewPagerItem {

    private static final String MANUAL_TRANSMISSION_TYPE = "M";
    private static final String NONE = "none";
    private static final String APPLINK_ENABLED = "applink enabled";
    private static final String TCU_ENABLED = "tcu enabled";
    private static final int ERROR_BANNER_TIMEOUT_IN_SECONDS = 2;
    public final ObservableField<Bitmap> vehicleImage = new ObservableField<>();
    public final ObservableBoolean vehicleImageAvailable = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowAlert = new ObservableBoolean(false);
    public final ObservableField<Integer> alertImage = new ObservableField<>();
    public final ObservableBoolean shouldShowVehicleControls = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowRemoteStartOnly = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowOnlyLockUnlock = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowPaakVehicleControls = new ObservableBoolean(false);
    public final ObservableBoolean showVehicleYearBrandModel = new ObservableBoolean(false);
    public final ObservableBoolean showVehicleChargingStatus = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowLightsAndHorn = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowCargoUnlock = new ObservableBoolean(false);
    public final ObservableBoolean shouldShowFordScript = new ObservableBoolean(false);
    public final ObservableField<Drawable> vehicleChargingIcon = new ObservableField<>();
    public final ObservableField<String> vehicleChargingStatus = new ObservableField<>("");
    public final ObservableField<String> authStateHeader = new ObservableField<>("");
    public final ObservableField<CharSequence> authStateDescription = new ObservableField<>("");
    public final ObservableInt vehicleDetailButtonLinkText = new ObservableInt(R.string.move_landing_vehicle_details_link);
    private final ErrorMessageUtil errorMessageUtil;
    private final VehicleImageLoadedEvent vehicleImageLoadedEvent;
    private final NgsdnVehicleProvider ngsdnVehicleProvider;
    protected final ConfigurationProvider configurationProvider;
    private final VinLookupProvider vinLookupProvider;
    private VehicleControlManager vehicleControlManager;
    private final ChargingStatusUtil chargingStatusUtil;
    protected PaakVehicleControlsViewModel vehicleControlsViewModel;
    protected final ResourceProvider resourceProvider;
    protected GarageVehicleProfile vehicleProfile;
    protected final IntegrationUtil integrationUtil;
    protected final UnboundViewEventBus eventBus;
    private ActiveVhaAlertsManager activeVhaAlertsManager;
    protected VehicleCapabilitiesManager vehicleCapabilitiesManager;
    protected VehicleAuthorizationDataManager vehicleAuthorizationDataManager;
    private VehicleInfoProvider vehicleInfoProvider;
    private String myVehiclePrefix;
    private TransientDataProvider transientDataProvider;
    private Lazy<PaakAdapter> paakAdapter;
    private int vehicleHealthAlertsCount;
    private String vin;
    private String vehicleCompatibility = NONE;
    private boolean hasRecalls = false;
    private RxSchedulerProvider rxSchedulingProvider;

    protected BaseGarageVehicleViewModel(GarageVehicleProfile vehicleProfile,
                                         IntegrationUtil integrationUtil,
                                         ActiveVhaAlertsManager activeVhaAlertsManager,
                                         Lazy<GlideProvider>     glideProvider,
                                         VehicleImageUrlProvider vehicleImageUrlProvider,
                                         UnboundViewEventBus eventBus,
                                         TransientDataProvider transientDataProvider,
                                         VehicleInfoProvider vehicleInfoProvider,
                                         VehicleImageLoadedEvent vehicleImageLoadedEvent,
                                         ResourceProvider resourceProvider,
                                         VehicleCapabilitiesManager vehicleCapabilitiesManager,
                                         NgsdnVehicleProvider ngsdnVehicleProvider,
                                         ConfigurationProvider configurationProvider,
                                         SharedPrefsUtil sharedPrefsUtil, ErrorMessageUtil errorMessageUtil,
                                         RxSchedulingHelper rxSchedulingHelper, VinLookupProvider vinLookupProvider,
                                         ChargingStatusUtil chargingStatusUtil, AccountInfoProvider accountInfoProvider,
                                         VehicleControlManager vehicleControlManager,
                                         Lazy<PaakAdapter> paakAdapter,
                                         VehicleAuthorizationDataManager vehicleAuthorizationDataManager,
                                         RxSchedulerProvider rxSchedulerProvider) {
        this.vehicleProfile = vehicleProfile;
        this.integrationUtil = integrationUtil;
        this.activeVhaAlertsManager = activeVhaAlertsManager;
        this.eventBus = eventBus;
        this.transientDataProvider = transientDataProvider;
        this.vehicleImageLoadedEvent = vehicleImageLoadedEvent;
        this.vehicleCapabilitiesManager = vehicleCapabilitiesManager;
        this.vehicleInfoProvider = vehicleInfoProvider;
        this.configurationProvider = configurationProvider;
        this.ngsdnVehicleProvider = ngsdnVehicleProvider;
        this.resourceProvider = resourceProvider;
        this.errorMessageUtil = errorMessageUtil;
        this.myVehiclePrefix = resourceProvider.getString(R.string.move_landing_vehicle_no_nickname);
        this.vinLookupProvider = vinLookupProvider;
        this.chargingStatusUtil = chargingStatusUtil;
        this.vehicleControlManager = vehicleControlManager;
        this.paakAdapter = paakAdapter;
        this.vehicleAuthorizationDataManager = vehicleAuthorizationDataManager;
        this.rxSchedulingProvider= rxSchedulerProvider;

        if (vehicleProfile != null) {

            glideProvider.get().load(vehicleProfile.getVehicleImage()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap drawable, GlideAnimation glideAnimation) {
                    Observable.fromCallable(() -> BitmapImageUtil.cropTransparentPadding(drawable))
                            .compose(rxSchedulingHelper.observableSchedulers(Threads.COMPUTATION))
                            .subscribe(BaseGarageVehicleViewModel.this::setVehicleImage, Throwable::printStackTrace);
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    BaseGarageVehicleViewModel.this.vehicleImageLoadedEvent.onVehicleImageReady(BaseGarageVehicleViewModel.this);
                }
            });
            showVehicleYearBrandModel();
            sharedPrefsUtil.setCurrentVehicleVin(vehicleProfile.getVin());
            updateFordScriptVisibility();
        }
        subscribeOnLifecycle(accountInfoProvider.getAccountCountry().subscribe(country -> {
            if (country.equals(Country.GREAT_BRITAIN)) {
                vehicleDetailButtonLinkText.set(R.string.move_landing_details_and_service_booking);
            }
        }));
    }

    @Override
    public String getPageKey() {
        return this.vehicleProfile.getVin();
    }

    @Override
    public @GarageTabOrder.TabOrder
    int tabOrder() {
        return GarageTabOrder.VEHICLE;
    }

    @Override
    public String getDisplayName() {
        if (vehicleProfile.getNickName().isPresent()) {
            return TextUtils.ellipsize(vehicleProfile.getNickName().get(), 15);
        }

        return myVehiclePrefix + " " +
                TextUtils.ellipsize(vehicleProfile.getLocalizedModelName().or(vehicleProfile.getModel()), 12);
    }

    public void refreshVehicleInfo(GarageVehicleProfile garageVehicleProfile) {
        this.vehicleProfile = garageVehicleProfile;
        updateFordScriptVisibility();
    }

    public String getModelName() {
        return vehicleProfile.getModel();
    }

    public boolean isTcuEnabled() {
        return vehicleProfile.getTcuEnabled();
    }

    public String getLocalMarketValue() {
        return vehicleProfile.getLocalizedModelName().or(vehicleProfile.getModel());
    }

    public String getVin() {
        return vehicleProfile.getVin();
    }

    public String getYearAndBrand(String brand) {
        return (vehicleProfile.getYear() != null ? vehicleProfile.getYear() : "") + " " + brand;
    }

    public String getJointVenture() {
        return TextUtils.isBlank(vehicleProfile.getJointVenture()) ? "" : vehicleProfile.getJointVenture();
    }

    public void launchVehicleDetails() {
        transientDataProvider.save(new GarageVehicleSelectedVinUseCase(vehicleProfile.getVin(), vehicleImage.get(), myVehiclePrefix, getDisplayName(),
                getBrandYearAndModel(resourceProvider.getString(R.string.common_environment_brand)), vehicleCompatibility, getModelName()));

        StartActivityEvent event = StartActivityEvent.build(this).activityName(VehicleDetailsActivity.class);
        eventBus.send(event);
    }

    private void checkAppLinkCompatibility(GarageVehicleProfile garageVehicleProfile) {
        if (garageVehicleProfile.getSDNSourceForTCU() == Source.SOURCE_NGSDN)  {
            subscribeOnLifecycle(
                    Observable.combineLatest(
                            vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(garageVehicleProfile.getVin()),
                            vehicleInfoProvider.getVehicleAuthStatusObject(garageVehicleProfile.getVin()),
                            (BiFunction<String, VehicleAuthStatus, Pair>) Pair::new)
                            .subscribe(this::setupAppLinkVehicleDetails, Throwable::printStackTrace)
            );
        }
    }

    public void checkVehicleAuthStatus() {
        transientDataProvider.save(new FindCollisionCenterVehicleInfoUseCase(vehicleProfile));
        subscribeOnLifecycle(updateSdnType().subscribe(() -> {
            if (vehicleProfile != null) {
                this.vin = vehicleProfile.getVin();
                if (vehicleProfile.getTcuEnabled()) {
                    subscribeOnLifecycle(vehicleAuthorizationDataManager.getAuthorizationData(vehicleProfile.getVin())
                            .subscribe(this::updateAuthorizationStateDisplay,
                                    throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
                    vehicleCompatibility = TCU_ENABLED;
                    transientDataProvider.save(new FindVehicleLocationUseCase(vehicleProfile, true));
                } else {
                    hideLoadingSpinner();
                    checkAppLinkCompatibility(vehicleProfile);
                }
            }
        }, Throwable::printStackTrace));
    }


    public PaakVehicleControlsViewModel getVehicleControlsViewModel() {
        return vehicleControlsViewModel;
    }

    public void onClickGarageHeaderAndDescription() {
    }

    public int getVehicleHealthAlertsCount() {
        return vehicleHealthAlertsCount;
    }

    public void setVehicleHealthAlertsCount(int vehicleHealthAlertsCount) {
        this.vehicleHealthAlertsCount = vehicleHealthAlertsCount;
        setVehicleHealthAlertIcon();
    }

    public String getBrandYearAndModel(String brand) {
        return vehicleProfile.getYear() + " " + brand + " " + vehicleProfile.getLocalizedModelName().or(vehicleProfile.getModel());
    }

    public void setVehicleVisible() {
        vehicleControlsViewModel.setVehicleVisible();
    }

    public void setVehicleNotVisible() {
        vehicleControlsViewModel.setVehicleNotVisible();
    }

    protected void updateAuthStateForUnauthorizedVehicles() {
        hideVehicleControls();
        authStateHeader.set(resourceProvider.getString(R.string.common_fordpass_connect_brand_name));
        authStateDescription.set(resourceProvider.getString(R.string.move_landing_sync_connect_description));
    }

    protected void setVehicleImage(Bitmap drawable) {
        vehicleImage.set(drawable);
        vehicleImageAvailable.set(true);
        vehicleImageLoadedEvent.onVehicleImageReady(BaseGarageVehicleViewModel.this);
        showVehicleYearBrandModel.set(false);
    }

    private void showGenericErrorMessageAndHideLoadingSpinner() {
        hideLoadingSpinner();
        vehicleControlsViewModel.showGenericErrorMessage();
    }

    private void handleGenericErrorCase() {
        hideLoadingSpinner();
    }

    private void hideLoadingSpinner() {
        transientDataProvider.save(new ProgressBarUseCase(false, R.string.common_loadingspinner));
    }

    private void checkVehicleDeepSleepStatus(GarageVehicleProfile vehicleInfo) {
        subscribeOnLifecycle(vehicleInfoProvider.getGarageVehicleStatus(vehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY)
                .subscribe(vehicleStatus -> vehicleControlsViewModel.checkVehicleState(vehicleInfo), throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
    }

    private void updateAuthorizationStateDisplay(Pair<VehicleAuthStatus, VehicleCapability> vehicleInfoDetailPair) {
        clearAuthStateText();
        VehicleAuthStatus updatedVehicleInfo = vehicleInfoDetailPair.first;
        VehicleCapability vehicleCapability = vehicleInfoDetailPair.second;
        vehicleAuthorizationDataManager.setVehicleData(vin, updatedVehicleInfo, vehicleCapability);
        if (vehicleAuthorizationDataManager.isPendingReset()) {
            processAuthPendingReset(vehicleProfile);
        } else {
            updateVehicleDataForAuthorizationStatus(vehicleProfile, updatedVehicleInfo);
        }
    }

    private void setupAppLinkVehicleDetails(Pair<String, VehicleAuthStatus> pairVhaTypeAuthStatus) {
        String vhaType = pairVhaTypeAuthStatus.first;
        VehicleAuthStatus authStatus = pairVhaTypeAuthStatus.second;
        boolean applinkCompatible = !vhaType.equals(VcsAppLinkCapabilityProvider.VhaType.VHA_NOT_SUPPORTED);
        if (applinkCompatible) {
            fetchVehicleHealthAlerts(getVhaSource(vehicleProfile, authStatus.isPreAuthorized()));
            if (vehicleProfile.getTcuEnabled()) {
                vehicleCompatibility = TCU_ENABLED;
            } else {
                vehicleCompatibility = APPLINK_ENABLED;
            }
        } else if (vehicleProfile.getTcuEnabled()) {
            vehicleCompatibility = TCU_ENABLED;
        } else {
            vehicleCompatibility = NONE;
        }
    }

    private void fetchVehicleHealthAlerts(GarageVehicleProfile vehicleInfo, boolean isAuthorized, boolean isPreAuthorized) {
        if (isAuthorized) {
            fetchVehicleHealthAlerts(getVhaSource(vehicleInfo, isPreAuthorized));
        } else {
            checkAppLinkCompatibility(vehicleInfo);
        }
    }

    protected abstract String getVhaSource(GarageVehicleProfile vehicleInfo, boolean isPreAuthorized);

    private void fetchVehicleHealthAlerts(String source) {
        subscribeOnLifecycle(activeVhaAlertsManager.getActiveAlertsFromCacheThenNetwork(vin, source)
                .subscribe(this::showVehicleHealthAlertCount, Throwable::printStackTrace));
    }

    private void showVehicleHealthAlertCount(VehicleAlertResponse vehicleAlertResponse) {
        setVehicleHealthAlertsCount(vehicleAlertResponse.getActiveAlerts().size());
    }

    private void showVehicleYearBrandModel() {
        if (!vehicleProfile.getTcuEnabled() && !vehicleImageAvailable.get()) {
            showVehicleYearBrandModel.set(true);
        }
    }

    private void setAlertIcon(VehicleRecallAndFsa vehicleRecallAndFsa) {
        if (null != vehicleRecallAndFsa) {
            if (!vehicleRecallAndFsa.getRecallList().isEmpty()) {
                alertImage.set(R.drawable.ic_circle_alert_urgent);
                shouldShowAlert.set(true);
                hasRecalls = true;
            } else if (!vehicleRecallAndFsa.getFsaList().isEmpty()) {
                alertImage.set(R.drawable.ic_circle_alert_moderate);
                shouldShowAlert.set(true);
                hasRecalls = true;
            } else {
                hasRecalls = false;
            }
        }
    }

    private void setVehicleHealthAlertIcon() {
        if (!hasRecalls) {
            if (vehicleHealthAlertsCount > 0) {
                alertImage.set(R.drawable.ic_circle_alert_moderate);
                shouldShowAlert.set(true);
            } else {
                shouldShowAlert.set(false);
            }
        }
    }

    public void updateVehicleControlsVisibility(String vin) {
        subscribeOnLifecycle(vehicleControlManager.getVehicleControlOptionsModel(vin)
                .doOnNext(vehicleControlOptionsModel -> {
                    if (vehicleControlOptionsModel.isPaakCapable() && configurationProvider.getConfiguration().isPaakEnabled())
                        paakAdapter.get().init();
                })
                .distinctUntilChanged()
                .subscribe(this::setVehicleControlVisibility, throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
    }

    private void setVehicleControlVisibility(VehicleControlOptionsModel vehicleControlOptionsModel) {
        boolean lightsAndHornEnabled = vehicleControlOptionsModel.isLightsAndHornEligible();
        boolean paakEnabled = vehicleControlOptionsModel.isPaakCapable();
        boolean remoteStartEnabled = vehicleControlOptionsModel.isRemoteStartEligible();
        boolean lockUnlockEnabled = vehicleControlOptionsModel.isLockUnlockEligible();
        boolean cargoUnlockEnabled = vehicleControlOptionsModel.isCabinCargoUnlockEligible();

        vehicleControlsViewModel.getLocksAssetManager().setPaakEnabled(paakEnabled);
        vehicleControlsViewModel.setVehicleControlsForPaak();
        /**************************************************************************/
        shouldShowPaakVehicleControls.set(paakEnabled);
        shouldShowRemoteStartOnly.set(remoteStartEnabled && !lockUnlockEnabled && !paakEnabled);
        shouldShowVehicleControls.set(remoteStartEnabled && lockUnlockEnabled && !paakEnabled);
        shouldShowOnlyLockUnlock.set(!remoteStartEnabled && lockUnlockEnabled && !paakEnabled);
        shouldShowLightsAndHorn.set(lightsAndHornEnabled);
        shouldShowCargoUnlock.set(cargoUnlockEnabled);
    }

    public void setVehicleRecallAndFsa(VehicleRecallAndFsa vehicleRecallAndFsa) {
        setAlertIcon(vehicleRecallAndFsa);
    }

    @FunctionalInterface
    public interface VehicleImageLoadedEvent {
        void onVehicleImageReady(BaseGarageVehicleViewModel garageVehicleViewModel);
    }

    public void setupElectricVehicle(GarageVehicleViewModel vehicleViewModel) {
        subscribeOnLifecycle(vinLookupProvider.isHybridElectricVehicle(vehicleViewModel.getVin())
                .flatMapObservable(this::checkVehicleChargingStatus).subscribe(this::displayUpdatedVehicleInfo, e -> onVehicleStatusError()));
    }

    private Observable<VehicleStatus> checkVehicleChargingStatus(Boolean isElectricVehicle) {
        if (isElectricVehicle) {
            return vehicleInfoProvider.getGarageVehicleStatus(vehicleProfile, CacheTransformerProvider.Policy.NETWORK_ONLY);
        } else {
            return Observable.empty();
        }
    }

    private void displayUpdatedVehicleInfo(VehicleStatus vehicleStatus) {
        updateFordScriptVisibility();

        if (chargingStatusUtil.shouldShowUnplugged(vehicleStatus)) {
            if (!vehicleStatus.isOutAndAbout()) {
                vehicleChargingStatus.set(resourceProvider.getString(R.string.move_ev_vehiclelanding_chargestatus_status_unplugged));
            } else {
                vehicleChargingStatus.set(resourceProvider.getString(R.string.move_ev_vehiclelanding_chargestatus_status_out_and_about));
            }
            vehicleChargingIcon.set(resourceProvider.getDrawable(R.drawable.ic_ev_unplugged));
            showVehicleChargingStatus.set(true);
        } else if (chargingStatusUtil.shouldShowChargingStatus(vehicleStatus) && !TextUtils.isEmpty(chargingStatusUtil.getChargingStatus(vehicleStatus))) {
            vehicleChargingStatus.set(chargingStatusUtil.getChargingStatus(vehicleStatus));
            vehicleChargingIcon.set(resourceProvider.getDrawable(chargingStatusUtil.getChargingIcon(vehicleStatus)));
            showVehicleChargingStatus.set(true);
        }
    }

    private void onVehicleStatusError() {
        errorMessageUtil.showErrorMessage(R.string.common_error_something_went_wrong_no_try_again, ERROR_BANNER_TIMEOUT_IN_SECONDS);
    }

    private Completable updateSdnType() {
        return vehicleCapabilitiesManager.getVehicleSdnType(vehicleProfile.getVin())
                .doOnSuccess(sdnType -> {
                    if (sdnType == SdnType.TMC) {
                        vehicleProfile = integrationUtil.mutate(vehicleProfile, Source.SOURCE_TMC);
                    }
                })
                .ignoreElement();
    }

    private void updateFordScriptVisibility() {
        boolean showFordScript = configurationProvider.getConfiguration().shouldShowFordScriptOverlay() && !isTcuEnabled() && !vehicleImageAvailable.get();
        shouldShowFordScript.set(showFordScript);
    }

    private void clearAuthStateText() {
        authStateHeader.set("");
        authStateDescription.set("");
    }

    private void updateVehicleDataForAuthorizationStatus(GarageVehicleProfile updatedVehicleInfo, VehicleAuthStatus authStatus) {
        if (vehicleAuthorizationDataManager.getXApiAuthorizationStatus() != null) {
            switch (vehicleAuthorizationDataManager.getXApiAuthorizationStatus()) {
                case AUTHORIZED:
                    if (!configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()) {
                        checkVehicleDeepSleepStatus(updatedVehicleInfo);
                        if (updatedVehicleInfo.getSDNSourceForTCU() == Source.SOURCE_ASDN) {
                            showVehicleControls();
                        } else {
                            updateVehicleControlsVisibility(vehicleAuthorizationDataManager.getVin());
                        }
                    }
                    break;
                case PRIMARY_AUTH_PENDING:
                    if (configurationProvider.getConfiguration().isTmcMigrationEnabled()) {
                        setAuthTextForPendingWithTmcEnabled();
                    } else {
                        setAuthPendingTextForTcuSource(updatedVehicleInfo);
                        vehicleControlsViewModel.checkVehicleState(updatedVehicleInfo);
                    }
                    break;
                case SECONDARY_AUTH_PENDING:
                    setAuthStateText(R.string.move_landing_activation_pending_heading, R.string.move_landing_activation_pending_secondary_user);
                    vehicleControlsViewModel.checkVehicleState(updatedVehicleInfo);
                    break;
                default:
                    updateAuthStateForUnauthorizedVehicles();
                    vehicleControlsViewModel.checkVehicleState(updatedVehicleInfo);
            }
            if (updatedVehicleInfo.getSDNSourceForTCU() != Source.SOURCE_ASDN) {
                fetchVehicleHealthAlerts(updatedVehicleInfo, authStatus.isAuthorized(), authStatus.isPreAuthorized());
            }
        }
    }

    private void processAuthPendingReset(GarageVehicleProfile vehicleInfo) {
        hideVehicleControls();
        setAuthStateText(R.string.move_vehicle_details_user_reset_deactivation_pending, R.string.move_landing_user_reset_deactivation_pending_message);
        vehicleControlsViewModel.checkVehicleState(vehicleInfo);
    }

    private void setAuthPendingTextForTcuSource(GarageVehicleProfile vehicleInfo) {
        authStateHeader.set(vehicleInfo.getSDNSourceForTCU() == Source.SOURCE_TMC
                ? resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_header)
                : resourceProvider.getString(R.string.move_landing_activation_pending_heading));
        authStateDescription.set(vehicleInfo.getSDNSourceForTCU() == Source.SOURCE_TMC
                ? resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_message)
                : resourceProvider.getString(R.string.move_landing_activation_pending_message));
    }

    private void setAuthTextForPendingWithTmcEnabled() {
        subscribeOnLifecycle(vehicleCapabilitiesManager.getVehicleCapabilities()
                .filter(vehicleCapabilitiesManager::isValidVcsResponse)
                .map(vcsResponse -> vcsResponse.getResult().getFeatures())
                .subscribe(vehicleCapabilitiesResponse -> {
                    if (vehicleCapabilitiesManager.getFeatureEligibility(vehicleCapabilitiesResponse, USER_RESET)) {
                        setAuthStateText(R.string.move_vehicle_landing_pending_activation_header, R.string.move_vehicle_landing_pending_activation_message);
                    } else {
                        setAuthStateText(R.string.move_landing_activation_pending_heading, R.string.move_landing_activation_pending_message);
                    }
                }));
    }

    private void showVehicleControls() {
        shouldShowRemoteStartOnly.set(false);
        shouldShowVehicleControls.set(true);
        shouldShowOnlyLockUnlock.set(false);
        shouldShowPaakVehicleControls.set(false);
    }

    private void setAuthStateText(@StringRes int authStateHeaderText, @StringRes int authStateDescriptionText) {
        authStateHeader.set(resourceProvider.getString(authStateHeaderText));
        authStateDescription.set(resourceProvider.getString(authStateDescriptionText));
    }

    private void hideVehicleControls() {
        shouldShowRemoteStartOnly.set(false);
        shouldShowVehicleControls.set(false);
        shouldShowOnlyLockUnlock.set(false);
        shouldShowPaakVehicleControls.set(false);
    }
}


