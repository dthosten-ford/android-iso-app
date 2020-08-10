/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.move;


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
import com.ford.androidutils.ui.glide.GlideProviderInterface;
import com.ford.applink.managers.ActiveVhaAlertsManager;
import com.ford.applink.managers.ActiveVhaAlertsManagerInterface;
import com.ford.applink.providers.VcsAppLinkCapabilityProvider;
import com.ford.dashboard.models.VehicleInfo;
import com.ford.ngsdnuser.providers.AccountInfoProvider;
import com.ford.ngsdnuser.providers.AccountInfoProviderInterface;
import com.ford.ngsdnvehicle.providers.NgsdnVehicleProvider;
import com.ford.ngsdnvehicle.providers.NgsdnVehicleProviderInterface;
import com.ford.paak.PaakAdapter;
import com.ford.recall.fsa.repo.common.VehicleRecallAndFsa;
import com.ford.rxutils.CacheTransformerProvider;
import com.ford.rxutils.schedulers.RxSchedulingHelper;
import com.ford.rxutils.schedulers.RxSchedulingHelperInterface;
import com.ford.rxutils.schedulers.Threads;
import com.ford.utils.TextUtils;
import com.ford.vehiclecommon.models.Vehicle;
import com.ford.vehiclecommon.models.VehicleStatus;
import com.ford.vehiclehealth.models.VehicleAlertResponse;
import com.ford.vinlookup.managers.VinLookupProvider;
import com.ford.vinlookup.managers.VinLookupProviderInterface;
import com.ford.xapi.models.response.VehicleCapability;
import com.ford.xapi.models.response.VehicleDetail;
import com.fordmps.core.BaseLifecycleViewModel;
import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.find.categories.Country;
import com.fordmps.mobileapp.move.managers.ChargingStatusUtil;
import com.fordmps.mobileapp.move.managers.ChargingStatusUtilInterface;
import com.fordmps.mobileapp.shared.configuration.ConfigurationProvider;
import com.fordmps.mobileapp.shared.configuration.ConfigurationProviderInterface;
import com.fordmps.mobileapp.shared.datashare.ResourceProvider;
import com.fordmps.mobileapp.shared.datashare.ResourceProviderInterface;
import com.fordmps.mobileapp.shared.datashare.TransientDataProvider;
import com.fordmps.mobileapp.shared.datashare.TransientDataProviderInterface;
import com.fordmps.mobileapp.shared.datashare.usecases.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.FindVehicleLocationUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.GarageVehicleSelectedVinUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.ProgressBarUseCase;
import com.fordmps.mobileapp.shared.events.StartActivityEvent;
import com.fordmps.mobileapp.shared.events.UnboundViewEventBus;
import com.fordmps.mobileapp.shared.events.UnboundViewEventBusInterface;
import com.fordmps.mobileapp.shared.managers.VehicleCapabilitiesManager;
import com.fordmps.mobileapp.shared.managers.VehicleCapabilitiesManagerInterface;
import com.fordmps.mobileapp.shared.providers.VehicleImageUrlProvider;
import com.fordmps.mobileapp.shared.providers.VehicleImageUrlProviderInterface;
import com.fordmps.mobileapp.shared.providers.VehicleInfoProvider;
import com.fordmps.mobileapp.shared.utils.BitmapImageUtil;
import com.fordmps.mobileapp.shared.utils.ErrorMessageUtil;
import com.fordmps.mobileapp.shared.utils.GarageTabOrder;
import com.fordmps.viewutils.R;

import io.reactivex.Completable;
import io.reactivex.Observable;

import static com.ford.vcs.models.Feature.FeatureNames.USER_RESET;
import static com.ford.vehiclecommon.models.Vehicle.SOURCE_ASDN;
import static com.ford.vehiclecommon.models.Vehicle.SOURCE_TMC;
import static com.fordmps.mobileapp.move.vehiclehealthalerts.VehicleHealthAlertsUtil.getVhaSource;


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
//    private final NgsdnVehicleProvider ngsdnVehicleProvider;
    private final NgsdnVehicleProviderInterface ngsdnVehicleProvider;//Dustin: changed to an Interface, break dependency on a Singleton
//    protected final ConfigurationProvider configurationProvider;
    protected final ConfigurationProviderInterface configurationProvider;//Dustin: Goal: Completely remove this
//    private final VinLookupProvider vinLookupProvider;
    private final VinLookupProviderInterface vinLookupProvider;
//    private VehicleControlManager vehicleControlManager;
    private VehicleControlManagerInterface vehicleControlManager;
//    private final ChargingStatusUtil chargingStatusUtil;
    private final ChargingStatusUtilInterface chargingStatusUtil;
//    protected PaakVehicleControlsViewModel vehicleControlsViewModel;
    protected PaakVehicleControlsViewModelInterface vehicleControlsViewModel; //Dustin: Question: Should VM's ref VM's
//    protected final ResourceProvider resourceProvider;
    protected final ResourceProviderInterface resourceProvider;//Dustin: Goal: Completely remove this
    protected VehicleInfo vehicleInfo;
//    protected final UnboundViewEventBus eventBus;
    protected final UnboundViewEventBusInterface eventBus;
//    private ActiveVhaAlertsManager activeVhaAlertsManager;
    private ActiveVhaAlertsManagerInterface activeVhaAlertsManager;//Dustin: Question: what are responsibilyt differences between Managers, adapters, and providers?
//    protected VehicleCapabilitiesManager vehicleCapabilitiesManager;
    protected VehicleCapabilitiesManagerInterface vehicleCapabilitiesManager;
//    protected VehicleAuthorizationDataManager vehicleAuthorizationDataManager;
    protected VehicleAuthorizationDataManagerInterface vehicleAuthorizationDataManager;
    private VehicleInfoProvider vehicleInfoProvider;
    private String myVehiclePrefix;//Dustin: Goal: Find a better home for this. Treat as an object, not one-off
//    private TransientDataProvider transientDataProvider;
    private TransientDataProviderInterface transientDataProvider;
    private PaakAdapter paakAdapter;
    private int vehicleHealthAlertsCount;//Dustin: Goal: Find a better home for this. Treat as an object, not one-off
    private String vin;//Dustin: Goal: Find a better home for this. Treat as an object, not one-off
    private String vehicleCompatibility = NONE;//Dustin: Goal: Find a better home for this. Treat as an object, not one-off
    private boolean hasRecalls = false;//Dustin: Goal: Find a better home for this. Treat as an object, not one-off

    protected BaseGarageVehicleViewModel(VehicleInfo vehicleInfo,
                                         ActiveVhaAlertsManagerInterface activeVhaAlertsManager,
                                         GlideProviderInterface glideProvider,
                                         VehicleImageUrlProviderInterface vehicleImageUrlProvider,
                                         UnboundViewEventBusInterface eventBus,
                                         TransientDataProviderInterface transientDataProvider,
                                         VehicleInfoProvider vehicleInfoProvider,
                                         VehicleImageLoadedEvent vehicleImageLoadedEvent,
                                         ResourceProviderInterface resourceProvider,
                                         VehicleCapabilitiesManagerInterface vehicleCapabilitiesManager,
                                         NgsdnVehicleProviderInterface ngsdnVehicleProvider,
                                         ConfigurationProviderInterface configurationProvider,
                                         SharedPrefsUtil sharedPrefsUtil, ErrorMessageUtil errorMessageUtil,
                                         RxSchedulingHelperInterface rxSchedulingHelper, VinLookupProviderInterface vinLookupProvider,
                                         ChargingStatusUtilInterface chargingStatusUtil, AccountInfoProviderInterface accountInfoProvider,
                                         VehicleControlManagerInterface vehicleControlManager,
                                         PaakAdapter paakAdapter,
                                         VehicleAuthorizationDataManagerInterface vehicleAuthorizationDataManager) {
        this.vehicleInfo = vehicleInfo;
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

        if (vehicleInfo != null) {
            //dustin: Can some other GlideManager handle this?
            glideProvider.load(vehicleImageUrlProvider.generateVehicleImageUrl(getModelName(), vehicleInfo.getModelYear(), vehicleInfo.getVin()))
                    .asBitmap().into(new SimpleTarget<Bitmap>() {
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
            //Dustin: Do we need to set this directly or can we raise a changeVin event?
            sharedPrefsUtil.setCurrentVehicleVin(vehicleInfo.getVin());
            updateFordScriptVisibility();
        }
        subscribeOnLifecycle(accountInfoProvider.getAccountCountry().subscribe(country -> {
            //Dustin: inject the linkText we want to use.
            if (country.equals(Country.GREAT_BRITAIN)) {
                vehicleDetailButtonLinkText.set(R.string.move_landing_details_and_service_booking);
            }
        }));
    }

    @Override
    public String getPageKey() {
        return this.vehicleInfo.getVin();
    }

    @Override
    public @GarageTabOrder.TabOrder
    int tabOrder() {
        return GarageTabOrder.VEHICLE; //Dustin: I like this.
    }

    @Override
    public String getDisplayName() {
        //Dustin: This seems like a job for another class.  All these properties essentially represent a DTO needed to bind to the View.
        // Can someone else manage those little rules?  Seems like vehicleInfo has most.
        // Perhaps a wrapper that includes the other values (myVehiclePrefix) or some extension methods?
        //That wrapper/helper could manage all TextUtils and resourceProviding magic as well.
        if (vehicleInfo.getNickname().isPresent()) {
            return TextUtils.ellipsize(vehicleInfo.getNickname().get(), 15);
        }

        return myVehiclePrefix + " " +
                TextUtils.ellipsize(vehicleInfo.getLocalizedModelName().or(vehicleInfo.getModelName()), 12);
    }

    public void refreshVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        updateFordScriptVisibility();
    }

    public String getModelName() {
        return vehicleInfo.getModelName();
    }

    public boolean isTcuEnabled() {
        return vehicleInfo.isTcuEnabled();
    }

    public String getLocalMarketValue() {
        return vehicleInfo.getLocalizedModelName().or(vehicleInfo.getModelName());
    }

    public String getVin() {
        return vehicleInfo.getVin();
    }

    public String getYearAndBrand(String brand) {
        return (vehicleInfo.getModelYear() != null ? vehicleInfo.getModelYear() : "") + " " + brand;
    }

    public String getJointVenture() {
        return TextUtils.isBlank(vehicleInfo.getJointVenture()) ? "" : vehicleInfo.getJointVenture();
    }

    public void launchVehicleDetails() {
        transientDataProvider.save(new GarageVehicleSelectedVinUseCase(vehicleInfo.getVin(), vehicleImage.get(), myVehiclePrefix, getDisplayName(),
                getBrandYearAndModel(resourceProvider.getString(R.string.common_environment_brand)), vehicleCompatibility, getModelName()));

        StartActivityEvent event = StartActivityEvent.build(this).activityName(VehicleDetailsActivity.class);
        eventBus.send(event);
    }
//Dustin: could we push the logic for TCU into vcManager?  perhaps create a rules class when to get a service?
    private void checkAppLinkCompatibility(VehicleInfo vehicleInfo) {
        if (vehicleInfo.getSDNSourceForTCU() == Vehicle.SOURCE_NGSDN) {
            subscribeOnLifecycle(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(vehicleInfo.getVin()).subscribe(this::setupAppLinkVehicleDetails, Throwable::printStackTrace));
        }
    }

    //Dustin: is there a service we can call to manage the provider calls and logic?
    public void checkVehicleAuthStatus() {
        transientDataProvider.save(new FindVehicleLocationUseCase(vehicleInfo, false));
        transientDataProvider.save(new FindCollisionCenterVehicleInfoUseCase(vehicleInfo));
        subscribeOnLifecycle(updateSdnType().subscribe(() -> {
            if (vehicleInfo != null) {
                this.vin = vehicleInfo.getVin();
                if (vehicleInfo.isTcuEnabled()) {
                    subscribeOnLifecycle(vehicleAuthorizationDataManager.getAuthorizationData(vehicleInfo)
                            .subscribe(this::updateAuthorizationStateDisplay,
                                    throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
                    vehicleCompatibility = TCU_ENABLED;
                    transientDataProvider.save(new FindVehicleLocationUseCase(vehicleInfo, true));
                } else {
                    hideLoadingSpinner();
                    checkAppLinkCompatibility(vehicleInfo);
                }
            }
        }, Throwable::printStackTrace));
    }
//Dustin: could we determine what logic to pass in here from the class injecting this?
    public void updateRemoteStartVisibilityXApi() {
        //Dustin: can we remove all references to configProvider?
        if (configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()) {
            subscribeOnLifecycle(vehicleControlManager.getVehicleControlOptionsXapi(vin)
                    .flatMap(vehicleControlOptionsModelXapi ->
                            vehicleControlOptionsModelXapi.isPaakCapable() && configurationProvider.getConfiguration().isPaakEnabled()
                                    ? paakAdapter.init().onErrorComplete().andThen(Observable.just(vehicleControlOptionsModelXapi))
                                    : Observable.just(vehicleControlOptionsModelXapi))
                    .subscribe(this::setVehicleControlVisibilityXapi, throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
        }
    }
//Dustin: this smells of convienence.
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
//Dustin more lightweight display logic we could consolidate in the vehicleInfoDisplayManager()
    public String getBrandYearAndModel(String brand) {
        return vehicleInfo.getModelYear() + " " + brand + " " + vehicleInfo.getLocalizedModelName().or(vehicleInfo.getModelName());
    }

    public void setVehicleVisible() {
        vehicleControlsViewModel.setVehicleVisible();
    }
    //Dustin: can we bind to some object's property instead of using branching logic to call on and off methods?
    public void setVehicleNotVisible() {
        //Dustin: one VM directly calling another? Is that part of our architecture?
        vehicleControlsViewModel.setVehicleNotVisible();
    }

    protected void updateAuthStateForUnauthorizedVehicles() {
        hideVehicleControls();
        //Dustin: Another place we could remove some reliance on the resourceProvider.
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

    private void checkVehicleDeepSleepStatus(VehicleInfo vehicleInfo) {
        subscribeOnLifecycle(vehicleInfoProvider.getVehicleStatus(vehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY).subscribe(vehicleStatus -> vehicleControlsViewModel.checkVehicleState(vehicleStatus), throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
    }

    private void updateAuthorizationStateDisplay(Pair<VehicleInfo, VehicleCapability> vehicleInfoDetailPair) {
        clearAuthStateText();
        VehicleInfo updatedVehicleInfo = vehicleInfoDetailPair.first;
        VehicleCapability vehicleCapability = vehicleInfoDetailPair.second;
        vehicleAuthorizationDataManager.setVehicleData(updatedVehicleInfo, vehicleCapability);
        if (vehicleAuthorizationDataManager.isPendingReset()) {
            processAuthPendingReset(updatedVehicleInfo);
        } else {
            updateVehicleDataForAuthorizationStatus(updatedVehicleInfo);
        }
    }
//Dustin: This logic looks like someone else could own it.
    private void setupAppLinkVehicleDetails(String vhaType) {
        boolean applinkCompatible = !vhaType.equals(VcsAppLinkCapabilityProvider.VhaType.VHA_NOT_SUPPORTED);
        if (applinkCompatible) {
            fetchVehicleHealthAlerts(getVhaSource(vehicleInfo));
            if (vehicleInfo.isTcuEnabled()) {
                vehicleCompatibility = TCU_ENABLED;
            } else {
                vehicleCompatibility = APPLINK_ENABLED;
            }
        } else if (vehicleInfo.isTcuEnabled()) {
            vehicleCompatibility = TCU_ENABLED;
        } else {
            vehicleCompatibility = NONE;
        }
    }

    private void fetchVehicleHealthAlerts(VehicleInfo vehicleInfo) {
        if (vehicleInfo.isAuthorized()) {
            fetchVehicleHealthAlerts(getVhaSource(vehicleInfo));
        } else {
            checkAppLinkCompatibility(vehicleInfo);
        }
    }

    private void fetchVehicleHealthAlerts(String source) {
        subscribeOnLifecycle(activeVhaAlertsManager.getActiveAlertsFromCacheThenNetwork(vin, source)
                .subscribe(this::showVehicleHealthAlertCount, Throwable::printStackTrace));
    }

    private void showVehicleHealthAlertCount(VehicleAlertResponse vehicleAlertResponse) {
        setVehicleHealthAlertsCount(vehicleAlertResponse.getActiveAlerts().size());
    }

    private void showVehicleYearBrandModel() {
        if (!vehicleInfo.isTcuEnabled() && !vehicleImageAvailable.get()) {
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
//Dustin: another place where dto's and binding may help.
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

    private void setVehicleControlsWithVcs(String vin) {
        subscribeOnLifecycle(vehicleControlManager.getVehicleControlOptions(vin)
                .flatMap(vehicleControlOptionsModel ->
                        vehicleControlOptionsModel.isPaakCapable() && configurationProvider.getConfiguration().isPaakEnabled()
                                ? paakAdapter.init().onErrorComplete().andThen(Observable.just(vehicleControlOptionsModel))
                                : Observable.just(vehicleControlOptionsModel))
                .subscribe(this::setVehicleControlVisibility, throwable -> showGenericErrorMessageAndHideLoadingSpinner()));
    }

    private void setVehicleControlVisibility(VehicleControlOptionsModel vehicleControlOptionsModel) {
        boolean lightsAndHornEnabled = vehicleControlOptionsModel.isLightsAndHornEligible();
        boolean paakEnabled = vehicleControlOptionsModel.isPaakCapable();
        boolean remoteStartEnabled = vehicleControlOptionsModel.isRemoteStartEligible();
        boolean lockUnlockEnabled = vehicleControlOptionsModel.isLockUnlockEligible();
        boolean cargoUnlockEnabled = vehicleControlOptionsModel.isCabinCargoUnlockEligible();
//Dustin: Seems like moving just a little more of this logic out of the VM could make for simple assignments with no logic.
        //the shouldShowVehicle rules could be tested in a VERY simple unit test.
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

//Dustin bind bind bind.
    private void setVehicleControlVisibilityXapi(VehicleControlOptionsModelXapi vehicleControlOptionsModel) {
        shouldShowVehicleControls.set(false);
        shouldShowOnlyLockUnlock.set(false);
        shouldShowPaakVehicleControls.set(false);
        RemoteStartState remoteStartEligibility = vehicleControlOptionsModel.getRemoteStartState();
        shouldShowRemoteStartOnly.set(!remoteStartEligibility.equals(RemoteStartState.DO_NOT_DISPLAY));
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

    private Observable<VehicleInfo> checkVehicleChargingStatus(Boolean isElectricVehicle) {
        if (isElectricVehicle) {
            return vehicleInfoProvider.getVehicleStatus(vehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY);
        } else {
            return Observable.empty();
        }
    }

    private void displayUpdatedVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
        updateFordScriptVisibility();
        VehicleStatus vehicleStatus = vehicleInfo.getVehicleStatus().get();

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
        return vehicleCapabilitiesManager.getVehicleSdnType(vehicleInfo.getVin())
                .doOnSuccess(sdnType -> {
                    if (sdnType == SdnType.TMC) {
                        vehicleInfo.setVehicleSource(SOURCE_TMC);
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

    private void updateVehicleDataForAuthorizationStatus(VehicleInfo updatedVehicleInfo) {
        if (vehicleAuthorizationDataManager.getXApiAuthorizationStatus() != null) {
            switch (vehicleAuthorizationDataManager.getXApiAuthorizationStatus()) {
                case AUTHORIZED:
                    if (!configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()) {
                        checkVehicleDeepSleepStatus(updatedVehicleInfo);
                        if (updatedVehicleInfo.getSDNSourceForTCU() == SOURCE_ASDN) {
                            showVehicleControls();
                        } else {
                            setVehicleControlsWithVcs(vehicleAuthorizationDataManager.getVin());
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
            if (updatedVehicleInfo.getSDNSourceForTCU() != SOURCE_ASDN) {
                fetchVehicleHealthAlerts(updatedVehicleInfo);
            }
        }
    }

    private void processAuthPendingReset(VehicleInfo vehicleInfo) {
        hideVehicleControls();
        setAuthStateText(R.string.move_vehicle_details_user_reset_deactivation_pending, R.string.move_landing_user_reset_deactivation_pending_message);
        vehicleControlsViewModel.checkVehicleState(vehicleInfo);
    }

    private void setAuthPendingTextForTcuSource(VehicleInfo vehicleInfo) {
        authStateHeader.set(vehicleInfo.getSDNSourceForTCU() == SOURCE_TMC
                ? resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_header)
                : resourceProvider.getString(R.string.move_landing_activation_pending_heading));
        authStateDescription.set(vehicleInfo.getSDNSourceForTCU() == SOURCE_TMC
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
    }

    private void setAuthStateText(@StringRes int authStateHeaderText, @StringRes int authStateDescriptionText) {
        authStateHeader.set(resourceProvider.getString(authStateHeaderText));
        authStateDescription.set(resourceProvider.getString(authStateDescriptionText));
    }

    private void hideVehicleControls() {
        shouldShowVehicleControls.set(false);
        shouldShowOnlyLockUnlock.set(false);
        shouldShowPaakVehicleControls.set(false);
    }
}

