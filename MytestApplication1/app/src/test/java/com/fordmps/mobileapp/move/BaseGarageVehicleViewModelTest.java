package com.fordmps.mobileapp.move;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.core.util.Pair;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ford.androidutils.SharedPrefsUtil;
import com.ford.androidutils.ui.glide.GlideProvider;
import com.ford.applink.managers.ActiveVhaAlertsManager;
import com.ford.applink.providers.VcsAppLinkCapabilityProvider;
import com.ford.dashboard.models.VehicleInfo;
import com.ford.locale.ServiceLocale;
import com.ford.locale.ServiceLocaleProvider;
import com.ford.ngsdnuser.providers.AccountInfoProvider;
import com.ford.ngsdnvehicle.providers.NgsdnVehicleProvider;
import com.ford.paak.PaakAdapter;
import com.ford.paak.bluetooth.events.BleEvents;
import com.ford.rxutils.schedulers.RxSchedulingHelper;
import com.ford.rxutils.schedulers.Threads;
import com.ford.vcs.models.Feature;
import com.ford.vcs.models.Result;
import com.ford.vcs.models.State;
import com.ford.vcs.models.VehicleCapabilitiesResponse;
import com.ford.vcs.storage.VehicleSdnTypeProvider;
import com.ford.vehiclecommon.managers.VehicleCommandManager;
import com.ford.vehiclecommon.models.Vehicle;
import com.ford.vehiclecommon.models.VehicleDetails;
import com.ford.vehiclecommon.models.VehicleStatus;
import com.ford.vehiclehealth.models.ActiveAlert;
import com.ford.vehiclehealth.models.VehicleAlertResponse;
import com.ford.vinlookup.managers.VinLookupProvider;
import com.ford.xapi.models.response.VehicleCapability;
import com.ford.xapi.models.response.XapiAuthStatus;
import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.find.categories.Country;
import com.fordmps.mobileapp.move.analytics.MoveAnalyticsManager;
import com.fordmps.mobileapp.move.managers.ChargingStatusUtil;
import com.fordmps.mobileapp.move.vehiclecontrols.EcallAlertBannerViewModel;
import com.fordmps.mobileapp.move.vehiclecontrols.RemoteStartCountdownManager;
import com.fordmps.mobileapp.shared.BaseTest;
import com.fordmps.mobileapp.shared.configuration.Configuration;
import com.fordmps.mobileapp.shared.configuration.ConfigurationProvider;
import com.fordmps.mobileapp.shared.datashare.ResourceProvider;
import com.fordmps.mobileapp.shared.datashare.TransientDataProvider;
import com.fordmps.mobileapp.shared.datashare.usecases.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.FindVehicleLocationUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.GarageVehicleSelectedVinUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.VehicleStatusUseCase;
import com.fordmps.mobileapp.shared.events.UnboundViewEventBus;
import com.fordmps.mobileapp.shared.managers.VehicleCapabilitiesManager;
import com.fordmps.mobileapp.shared.providers.VehicleImageUrlProvider;
import com.fordmps.mobileapp.shared.providers.VehicleInfoProvider;
import com.fordmps.mobileapp.shared.utils.ErrorMessageUtil;
import com.fordmps.viewutils.R;
import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

import static com.ford.applink.providers.VcsAppLinkCapabilityProvider.VhaType.VHA_NOT_SUPPORTED;
import static com.ford.ngsdnvehicle.models.ActiveAlertVinData.SOURCE_APPLINK;
import static com.ford.ngsdnvehicle.models.ActiveAlertVinData.SOURCE_TCU;
import static com.ford.vcs.models.FeatureNames.USER_RESET;
import static com.ford.vehiclecommon.models.Vehicle.SOURCE_ASDN;
import static com.ford.vehiclecommon.models.Vehicle.SOURCE_NGSDN;
import static com.ford.vehiclecommon.models.Vehicle.SOURCE_TMC;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public abstract class BaseGarageVehicleViewModelTest extends BaseTest {
//public abstract class BaseGarageVehicleViewModelTest extends BaseTest {
    protected static final String VIN = "VIN";
    protected static final String MODEL_NAME = "MODEL_NAME";
    protected static final String MODEL_YEAR = "MODEL_YEAR";
    protected static final String VEHICLE_DISPLAY_PREFIX = "My";

    @Mock
    protected UnboundViewEventBus eventBus;

    @Mock
    protected VehicleInfo vehicleInfo;

    @Mock
    protected GlideProvider glideProvider;

    @Mock
    protected VehicleImageUrlProvider vehicleImageUrlProvider;

    @Mock
    protected TransientDataProvider transientDataProvider;

    @Mock
    protected VehicleInfoProvider vehicleInfoProvider;

    @Mock
    protected ResourceProvider resourceProvider;

    @Mock
    protected VehicleCommandManager vehicleCommandManager;

    @Mock
    protected DrawableTypeRequest mDrawableTypeRequest;

    @Mock
    protected BitmapTypeRequest mBitmapTypeRequest;

    @Mock
    protected BitmapRequestBuilder mBitmapRequestBuilder;

    @Mock
    protected VehicleAlertResponse vehicleAlertResponse;

    @Mock
    protected BaseGarageVehicleViewModel.VehicleImageLoadedEvent imageLoadEvent;

    @Mock
    protected RemoteStartCountdownManager.Factory remoteStartCountdownManagerFactory;

    @Mock
    protected VehicleStatus vehicleStatus;

    @Mock
    protected ActiveVhaAlertsManager activeVhaAlertsManager;

    @Mock
    protected VehicleCapabilitiesManager vehicleCapabilitiesManager;

    @Mock
    protected SharedPrefsUtil sharedPrefsUtil;

    @Mock
    protected ErrorMessageUtil errorMessageUtil;

    @Mock
    protected MoveAnalyticsManager moveAnalyticsManager;

    @Mock
    protected ServiceLocaleProvider serviceLocaleProvider;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    protected ConfigurationProvider configurationProvider;

    @Mock
    protected Configuration configuration;

    @Mock
    protected EcallAlertBannerViewModel.Factory eCallAlertBannerViewModelFactory;

    @Mock
    protected PaakAdapter paakAdapter;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    protected NgsdnVehicleProvider ngsdnVehicleProvider;

    @Mock
    protected RxSchedulingHelper rxSchedulingHelper;

    @Mock
    protected VinLookupProvider vinLookupProvider;

    @Mock
    protected ChargingStatusUtil chargingStatusUtil;

    @Mock
    protected AccountInfoProvider accountInfoProvider;

    @Mock
    protected VehicleControlManager vehicleControlManager;

    @Mock
    protected VehicleCapabilitiesResponse vehicleCapabilitiesResponse;

    @Mock
    protected Feature featureRemoteStart;

    @Mock
    protected Feature featureLockUnlock;

    @Mock
    protected Result result;

    @Mock
    protected State stateRemoteStart;

    @Mock
    protected State stateLockUnlock;

    @Mock
    protected VehicleSdnTypeProvider vehicleSdnTypeProvider;

    @Mock
    protected VehicleAuthorizationDataManager vehicleAuthorizationDataManager;

    @Mock
    protected VehicleInfo updatedVehicleInfo;

    @Mock
    private GarageVehicleViewModel garageVehicleViewModel;

    @Mock
    private VehicleCapability vehicleCapability;

    @Mock
    PaakVehicleControlsViewModel paakVehicleControlsViewModel;

    @Mock
    PaakVehicleControlsViewModel.Factory paakVehicleControlsViewModelFactory;

    protected BaseGarageVehicleViewModel subject;
    protected PublishSubject<Class> tdpUseCaseSubject = PublishSubject.create();

    @Before
    public void setup() {
        when(vehicleInfo.getVin()).thenReturn(VIN);
        when(accountInfoProvider.getAccountCountry()).thenReturn(Observable.just(Country.GREAT_BRITAIN.name()));
        when(configurationProvider.getConfiguration()).thenReturn(configuration);
        when(configuration.isPaakEnabled()).thenReturn(false);
        when(vehicleInfo.getModelName()).thenReturn(MODEL_NAME);
        when(vehicleInfo.getModelYear()).thenReturn(MODEL_YEAR);
        when(vehicleInfo.getNickname()).thenReturn(Optional.absent());
        when(vehicleInfo.getVehicleDetails()).thenReturn(Optional.absent());
        when(vehicleInfo.getVehicleStatus()).thenReturn(Optional.of(vehicleStatus));
        when(vehicleImageUrlProvider.generateVehicleImageUrl(anyString(), anyString(), anyString())).thenReturn("TESTURL");
        when(vehicleInfoProvider.getVehicleAuthStatus(vehicleInfo)).thenReturn(Observable.just(vehicleInfo));
        when(transientDataProvider.observeUseCase(VehicleStatusUseCase.class)).thenReturn(tdpUseCaseSubject.filter(clazz -> clazz.equals(VehicleStatusUseCase.class)));
        when(vehicleInfoProvider.getVehicleStatus(vehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY)).thenReturn(Observable.just(vehicleInfo));
        when(vehicleInfoProvider.getVehicleStatus(updatedVehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY)).thenReturn(Observable.just(updatedVehicleInfo));
        when(ngsdnVehicleProvider.getVehicleLockStatusObservable(VIN)).thenReturn(Observable.empty());
        when(activeVhaAlertsManager.getActiveAlertsFromCacheThenNetwork(eq(VIN), anyString())).thenReturn(Observable.just(vehicleAlertResponse));
        when(vehicleInfoProvider.getVehicleInfo(VIN, CacheTransformerProvider.Policy.LOCAL_UNLESS_STALE)).thenReturn(Observable.just(vehicleInfo));
        when(remoteStartCountdownManagerFactory.newInstance()).thenReturn(mock(RemoteStartCountdownManager.class));
        when(rxSchedulingHelper.observableSchedulers(Threads.COMPUTATION)).thenReturn(observable -> observable);
        when(resourceProvider.getString(R.string.move_landing_vehicle_no_nickname)).thenReturn(VEHICLE_DISPLAY_PREFIX);
        when(resourceProvider.getString(R.string.common_environment_brand)).thenReturn("Ford");
        when(paakAdapter.connectionObservable()).thenReturn(Observable.just(BleEvents.Connection.STATE_CONNECTED));
        when(paakAdapter.startBeaconListening(anyString())).thenReturn(Completable.complete());
        when(paakAdapter.hasActiveKeysForVin(vehicleInfo.getVin())).thenReturn(mock(Single.class));
        when(paakAdapter.startBeaconListening(vehicleInfo.getVin())).thenReturn(Completable.complete());
        when(garageVehicleViewModel.getVin()).thenReturn(VIN);
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.never());
        when(ngsdnVehicleProvider.getVehiclePaakCapability(anyString())).thenReturn(Observable.just(true));
        when(result.getFeatures()).thenReturn(Arrays.asList(featureRemoteStart, featureLockUnlock));
        when(featureRemoteStart.getState()).thenReturn(stateRemoteStart);
        when(featureLockUnlock.getState()).thenReturn(stateLockUnlock);
        when(vehicleCapabilitiesResponse.getResult()).thenReturn(result);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(updatedVehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleCapabilitiesManager.getVehicleSdnType(VIN)).thenReturn(Single.just(SdnType.UNKNOWN));
        Pair vehicleInfoVehicleDetailPair = Pair.create(updatedVehicleInfo, vehicleCapability);
        when(vehicleAuthorizationDataManager.getAuthorizationData(vehicleInfo)).thenReturn(Observable.just(vehicleInfoVehicleDetailPair));
        when(vehicleAuthorizationDataManager.getVin()).thenReturn(VIN);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(false);

        mockGlideProvider();
    }

    @Test
    public void refreshVehicleInfo_vehicleInfoSuppliedWithDifferentTcu_cleansAndUpdatesPaakControls(){
        VehicleInfo vehicleInfoNew = mock(VehicleInfo.class);
        PaakVehicleControlsViewModel newPaakVehicleControlsViewModel = mock(PaakVehicleControlsViewModel.class);
        when(vehicleInfoNew.isTcuEnabled()).thenReturn(true);
        when(vehicleInfoNew.getVin()).thenReturn(VIN);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        setUpSubject();
        when(paakVehicleControlsViewModelFactory.newInstance(any(VehicleInfo.class))).thenReturn(newPaakVehicleControlsViewModel);

        subject.refreshVehicleInfo(vehicleInfoNew);

        assertTrue(subject.vehicleInfo == vehicleInfoNew);
        assertTrue(subject.vehicleControlsViewModel == newPaakVehicleControlsViewModel);
        verify(paakVehicleControlsViewModel).onDestroy();
    }

    @Test
    public void refreshVehicleInfo_vehicleInfoSuppliedWithSameTcu_nothingChanged(){
        VehicleInfo vehicleInfoNew = mock(VehicleInfo.class);
        PaakVehicleControlsViewModel newPaakVehicleControlsViewModel = mock(PaakVehicleControlsViewModel.class);
        when(vehicleInfoNew.isTcuEnabled()).thenReturn(false);
        when(vehicleInfoNew.getVin()).thenReturn(VIN);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        setUpSubject();
        when(paakVehicleControlsViewModelFactory.newInstance(any(VehicleInfo.class))).thenReturn(newPaakVehicleControlsViewModel);

        subject.refreshVehicleInfo(vehicleInfoNew);

        assertTrue(subject.vehicleInfo == vehicleInfo);
        assertTrue(subject.vehicleControlsViewModel == paakVehicleControlsViewModel);
        verify(paakVehicleControlsViewModel, never()).onDestroy();
    }

    @Test
    public void onLoad_isFordConfiguration_showFordScript() {
        when(configuration.shouldShowFordScriptOverlay()).thenReturn(true);
        setUpSubject();

        assertThat(subject.shouldShowFordScript.get()).isTrue();
    }

    @Test
    public void onLoad_isNotFordConfiguration_hideFordScript() {
        when(configuration.shouldShowFordScriptOverlay()).thenReturn(false);
        setUpSubject();

        assertThat(subject.shouldShowFordScript.get()).isFalse();
    }

    @Test
    public void onLoad_showFordScriptAndTcuEnabled_hideFordScript() {
        when(configuration.shouldShowFordScriptOverlay()).thenReturn(true);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        setUpSubject();
        subject.vehicleImageAvailable.set(true);

        assertThat(subject.shouldShowFordScript.get()).isFalse();
    }

    @Test
    public void onLoad_showFordScriptWithTcuDisabledAndNoVehicleImage_showFordScript() {
        when(configuration.shouldShowFordScriptOverlay()).thenReturn(true);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        setUpSubject();
        subject.vehicleImageAvailable.set(false);

        assertThat(subject.shouldShowFordScript.get()).isTrue();
    }

    @Test
    public void onLoad_showFordScriptWithTcuEnabledAndHasVehicleImage_hideFordScript() {
        when(configuration.shouldShowFordScriptOverlay()).thenReturn(true);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        setUpSubject();
        subject.vehicleImageAvailable.set(true);

        subject.refreshVehicleInfo(vehicleInfo);

        assertThat(subject.shouldShowFordScript.get()).isFalse();
    }

    @Test
    public void onLoad_savesVinToSharedPreference() {
        setUpSubject();

        verify(sharedPrefsUtil).setCurrentVehicleVin(VIN);
    }

    @Test
    public void getJointVenture_returnsJointVenture() {
        when(vehicleInfo.getJointVenture()).thenReturn("FCO");

        setUpSubject();

        assertEquals(subject.getJointVenture(), "FCO");
    }

    @Test
    public void getJointVenture_doesNothaveJointVenture_returnsEmptyString() {
        when(vehicleInfo.getJointVenture()).thenReturn(null);

        setUpSubject();

        assertEquals(subject.getJointVenture(), "");
    }

    @Test
    public void checkVehicleAuthStatus_paakTrueAndLightsAndHornEnabledCabinCargoUnlockDisabled_shouldShowPaakControlsWithLightsAndHornLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isPaakCapable()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_paakTrueAndLightsAndHornEnabledCabinCargoUnlockEnabled_shouldShowPaakControlsWithLightsAndHornAndCargoCabinUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isPaakCapable()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndLightsAndHornEnabledCargoUnlockDisabled_shouldShowVehicleControlsWithLightsAndHornLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteStartEligibleReturnsDisplay_shouldShowStartButtonOnly() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.DISPLAY);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertTrue(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteStartEligibleReturnsDisplayDisabled_shouldShowStartButtonOnly() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.DISPLAY_DISABLED);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertTrue(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteStartEligibleReturnsNoDisplay_shouldNotShowStartButton() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);

        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.NO_DISPLAY);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertFalse(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndLightsAndHornEnabledCargoUnlockEnabled_shouldShowVehicleControlsWithLightsAndHornAndCargoUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndLightsAndHornDisabledAndCargoCabinUnlockDisabled_shouldShowVehicleControlsLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndLightsAndHornDisabledAndCargoCabinUnlockEnabled_shouldShowVehicleControlsWithCargoCabinUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyLockAndUnlockAndLightsAndHornEnabledAndCargoCabinUnlockDisabled_shouldShowLockAndUnlockWithLightsAndHornLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyLockAndUnlockAndLightsAndHornEnabledAndCargoCabinUnlockEnabled_shouldShowLockAndUnlockWithLightsAndHornAndCargoCabinUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyLockAndUnlockAndLightsAndHornDisabledAndCargoCabinUnlockDisabled_shouldShowLockAndUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteLockEligibleReturnsDisplay_shouldShowLockAndUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.DISPLAY);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertFalse(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertTrue(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteLockEligibleReturnsNoDisplay_shouldNotShowLockAndUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertFalse(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteLockEligibleReturnsDisplayDisabled_shouldShowLockAndUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.NO_DISPLAY);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.DISPLAY_DISABLED);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertFalse(subject.shouldShowRemoteStartOnly.get());
        assertFalse(subject.shouldShowVehicleControls.get());
        assertTrue(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void updateRemoteStartVisibilityXApi_DashboardXapiPhase2EnabledRemoteStartAndLockEligibleReturnsDisplay_shouldShowControlsLayout() {
        setUpSubject();
        VehicleControlOptionsModelXapi optionsModel = mock(VehicleControlOptionsModelXapi.class);
        when(vehicleControlManager.getVehicleControlOptionsXapi(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(configurationProvider.getConfiguration().isDashboardXApiPhase2Enabled()).thenReturn(true);
        when(optionsModel.getRemoteStartState()).thenReturn(RemoteCommandState.DISPLAY);
        when(optionsModel.getRemoteLockState()).thenReturn(RemoteCommandState.DISPLAY);
        when(optionsModel.isPaakCapable()).thenReturn(false);

        subject.updateCommandAndControlButtonsVisibilityXApi();

        verify(optionsModel).getRemoteStartState();

        assertFalse(subject.shouldShowRemoteStartOnly.get());
        assertTrue(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }


    @Test
    public void checkVehicleAuthStatus_onlyLockAndUnlockAndLightsAndHornDisabledAndCargoCabinUnlockEnabled_shouldShowLockAndUnlockLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyRemoteStartAndLightsAndHornEnabledAndCargoCabinUnlockDisabled_shouldShowRemoteStartAndLightsAndHornLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyRemoteStartAndLightsAndHornEnabledAndCargoCabinUnlockEnabled_shouldShowRemoteStartAndLightsAndHornLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isLightsAndHornEligible()).thenReturn(true);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyRemoteStartAndLightsAndHornDisabledAndCargoCabinUnlockDisabled_shouldShowRemoteStartLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_onlyRemoteStartAndLightsAndHornDisabledAndCabinCargoUnlockEnabled_shouldShowRemoteStartLayout() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isExtendStartVisible()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isCabinCargoUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertifySetVehicleControlVisibility(optionsModel);
    }

    @Test
    public void checkVehicleAuthStatus_paakTrueAndVcsRemoteControlEnabled_setPaakNavigationIconTrueAndShouldSeePaakVehicleControls() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isPaakCapable()).thenReturn(true);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(paakAdapter.init()).thenReturn(Completable.complete());
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(true);
        when(vehicleControlManager.getVehicleControlOptions(VIN)).thenReturn(Observable.just(optionsModel));

        subject.checkVehicleAuthStatus();

        verify(paakAdapter).init();
        verify(subject.vehicleControlsViewModel.getLocksAssetManager()).setPaakEnabled(true);
        assertThat(subject.shouldShowVehicleControls.get()).isFalse();
        assertThat(subject.shouldShowPaakVehicleControls.get()).isTrue();
    }

    @Test
    public void checkVehicleAuthStatus_tmcEnabled_checksVehicleSdnType() {
        setUpSubject();
        when(vehicleCapabilitiesManager.getVehicleSdnType(VIN)).thenReturn(Single.just(SdnType.TMC));

        subject.checkVehicleAuthStatus();

        verify(vehicleCapabilitiesManager).getVehicleSdnType(VIN);
        verify(vehicleInfo).setVehicleSource(SOURCE_TMC);
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndVcsRemoteStartEnabeled_setPaakNavigationIconTrueAndShouldSeePaakVehicleControls() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        when(vehicleControlManager.getVehicleControlOptions(VIN)).thenReturn(Observable.just(optionsModel));
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(false);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager(), never()).setPaakEnabled(true);
        verify(paakAdapter, never()).init();
        assertThat(subject.shouldShowRemoteStartOnly.get()).isTrue();
        assertThat(subject.shouldShowVehicleControls.get()).isFalse();
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isFalse();
    }

    @Test
    public void checkVehicleAuthStatus_vehicleSourceIsNgsdnAndSdnTypeIsCached_doesNotCacheSdnTypeAsNgsdn() {
        setUpSubject();
        when(vehicleSdnTypeProvider.hasVehicleSdnTypeCached(VIN)).thenReturn(true);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);

        subject.checkVehicleAuthStatus();

        verify(vehicleSdnTypeProvider, never()).setVehicleSdnType(any(), any());
    }

    @Test
    public void checkVehicleAuthStatus_vehicleSourceIsAsdnAndSdnTypeIsCached_doesNotCacheSdnTypeAsAsdn() {
        setUpSubject();
        when(vehicleSdnTypeProvider.hasVehicleSdnTypeCached(VIN)).thenReturn(true);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);

        subject.checkVehicleAuthStatus();

        verify(vehicleSdnTypeProvider, never()).setVehicleSdnType(any(), any());
    }

    @Test
    public void checkVehicleAuthStatus_3gAuthorizedVehicle_showVehicleControls() {
        setUpSubject();
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        when(updatedVehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager(), never()).setPaakEnabled(true);
        verify(vehicleControlManager, never()).getVehicleControlOptions(VIN);
        assertThat(subject.shouldShowRemoteStartOnly.get()).isFalse();
        assertThat(subject.shouldShowVehicleControls.get()).isTrue();
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isFalse();
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndVcsLockUnlockEnabled_setPaakNavigationIconTrueAndShouldSeePaakVehicleControls() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        when(vehicleControlManager.getVehicleControlOptions(VIN)).thenReturn(Observable.just(optionsModel));
        when(optionsModel.isRemoteStartEligible()).thenReturn(false);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager(), never()).setPaakEnabled(true);
        verify(paakAdapter, never()).init();
        assertThat(subject.shouldShowRemoteStartOnly.get()).isFalse();
        assertThat(subject.shouldShowVehicleControls.get()).isFalse();
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isTrue();
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndVcsLockUnlockAndRemoteStartEnabled_setPaakNavigationIconTrueAndShouldSeePaakVehicleControls() {
        setUpSubject();
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        when(vehicleControlManager.getVehicleControlOptions(VIN)).thenReturn(Observable.just(optionsModel));
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager(), never()).setPaakEnabled(true);
        verify(paakAdapter, never()).init();
        assertThat(subject.shouldShowRemoteStartOnly.get()).isFalse();
        assertThat(subject.shouldShowVehicleControls.get()).isTrue();
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isFalse();
    }

    @Test
    public void checkVehicleAuthStatus_paakFalseAndVcsLockUnlockAndRemoteStartNotEligible_doNotShowCommandControls() {
        setUpSubject();
        subject.shouldShowOnlyLockUnlock.set(true);
        subject.shouldShowVehicleControls.set(true);
        subject.shouldShowRemoteStartOnly.set(true);
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        when(vehicleControlManager.getVehicleControlOptions(VIN)).thenReturn(Observable.just(optionsModel));
        when(optionsModel.isRemoteStartEligible()).thenReturn(false);
        when(optionsModel.isLockUnlockEligible()).thenReturn(false);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager(), never()).setPaakEnabled(true);
        verify(paakAdapter, never()).init();
        assertThat(subject.shouldShowRemoteStartOnly.get()).isFalse();
        assertThat(subject.shouldShowVehicleControls.get()).isFalse();
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isFalse();
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledVehicleAndVehicleIsAuthorizedAndVinPaakCapable_setPaakNavigationIconTrueAndShouldSeePaakVehicleControls() {
        setUpSubject();
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.AUTHORIZED));
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(true);
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isPaakCapable()).thenReturn(true);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager()).setPaakEnabled(true);
        assertThat(subject.shouldShowVehicleControls.get()).isFalse();
        assertThat(subject.shouldShowPaakVehicleControls.get()).isTrue();
        assertEquals("", subject.authStateHeader.get());
        assertEquals("", subject.authStateDescription.get());
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledVehicleAndVehicleIsAuthorizedAndVinNotPaakCapable_setPaakNavigationIconFalseAndSeeNonPaakVehicleControls() {
        setUpSubject();
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.AUTHORIZED));
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(true);
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        verify(subject.vehicleControlsViewModel.getLocksAssetManager()).setPaakEnabled(false);
        assertFalse(subject.shouldShowPaakVehicleControls.get());
        assertTrue(subject.shouldShowVehicleControls.get());
        assertEquals("", subject.authStateHeader.get());
        assertEquals("", subject.authStateDescription.get());
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledVehicleAndVehicleIsAuthorizedAndPaakCapabilityException_setPaakNavigationIconFalse() {
        setUpSubject();
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.AUTHORIZED));
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(true);
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        when(optionsModel.isPaakCapable()).thenReturn(false);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);

        subject.checkVehicleAuthStatus();

        assertThat(subject.shouldShowVehicleControls.get()).isTrue();
        assertThat(subject.shouldShowPaakVehicleControls.get()).isFalse();
        verify(subject.vehicleControlsViewModel.getLocksAssetManager()).setPaakEnabled(false);
        assertEquals("", subject.authStateHeader.get());
        assertEquals("", subject.authStateDescription.get());
    }

    @Test
    public void checkVehicleAuthStatus_VehicleIsTcuEnabledAndAuthorizedAndPaakDisabled_shouldNotCallVehiclePaakCapability() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.AUTHORIZED));
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verify(ngsdnVehicleProvider, never()).getVehiclePaakCapability(any());
        assertEquals("", subject.authStateHeader.get());
        assertEquals("", subject.authStateDescription.get());
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledVehicle_checksVehicleAuthStatus() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verify(transientDataProvider).save(new FindVehicleLocationUseCase(vehicleInfo, false));
        verify(transientDataProvider).save(new FindVehicleLocationUseCase(vehicleInfo, true));
        verify(transientDataProvider).save(new FindCollisionCenterVehicleInfoUseCase(vehicleInfo));
    }

    @Test
    public void checkVehicleAuthStatus_nonTcuVehicle_doesNotCheckVehicleAuthStatus() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_APPLINK));
        setUpSubject();
        subject.checkVehicleAuthStatus();

        verify(vehicleInfoProvider, never()).getVehicleAuthStatus(any(VehicleInfo.class));
        verify(transientDataProvider).save(new FindVehicleLocationUseCase(vehicleInfo, false));
        verify(transientDataProvider, never()).save(new FindVehicleLocationUseCase(vehicleInfo, true));
        verify(transientDataProvider).save(new FindCollisionCenterVehicleInfoUseCase(vehicleInfo));
    }

    @Test
    public void checkNonTcuVehicle_showsVehicleBrandYearModel() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        setUpSubject();

        assertThat(subject.showVehicleYearBrandModel.get()).isEqualTo(true);
    }

    @Test
    public void checkNonTcuVehicleWithImage_doesNotShowVehicleBrandYearModel() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);

        setUpSubject();

        verify(glideProvider.load(anyString()).asBitmap()).into(any(SimpleTarget.class));

        subject.setVehicleImage(mock(Bitmap.class));

        assertThat(subject.showVehicleYearBrandModel.get()).isEqualTo(false);
    }

    @Test
    public void checkTcuVehicleWithoutImage_callsOnVehicleImageReady() {
        ArgumentCaptor<SimpleTarget> targetArgumentCaptor = ArgumentCaptor.forClass(SimpleTarget.class);

        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);

        setUpSubject();

        verify(glideProvider.load(anyString()).asBitmap()).into(targetArgumentCaptor.capture());

        SimpleTarget<Bitmap> simpleTarget = targetArgumentCaptor.getValue();
        simpleTarget.onLoadFailed(new Exception(), null);

        verify(imageLoadEvent).onVehicleImageReady(subject);
    }

    @Test
    public void checkTcuVehicle_doNotShowVehicleBrandYearModel() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        setUpSubject();

        assertThat(subject.showVehicleYearBrandModel.get()).isEqualTo(false);
    }


    @Test
    public void checkVehicleAuthStatus_vehicleNotAuthorized_doesNotShowVehicleControlsComponent() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleInfo.getModelYear()).thenReturn("2019");
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("FordPass Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn(
                "You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
        setUpSubject();
        subject.shouldShowVehicleControls.set(true);
        subject.shouldShowOnlyLockUnlock.set(true);
        subject.shouldShowPaakVehicleControls.set(true);

        subject.checkVehicleAuthStatus();

        assertThat(subject.shouldShowVehicleControls.get()).isEqualTo(false);
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isEqualTo(false);
        assertThat(subject.shouldShowPaakVehicleControls.get()).isEqualTo(false);
    }

    @Test
    public void checkVehicleAuthStatus_primaryAuthPending_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.PRIMARY_AUTH_PENDING);
        when(resourceProvider.getString(R.string.move_landing_activation_pending_heading)).thenReturn("Activation Pending");
        when(resourceProvider.getString(R.string.move_landing_activation_pending_message)).thenReturn(
                "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Activation Pending");
        assertEquals(subject.authStateDescription.get(), "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
    }

    @Test
    public void checkVehicleAuthStatusForTmcVehicle_primaryAuthPending_showsCorrectAuthStateHeaderAndDescription() {
        when(updatedVehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_TMC);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.PRIMARY_AUTH_PENDING);
        when(resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_header)).thenReturn("Activation Pending");
        when(resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_message)).thenReturn("Connected features are being enabled");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Activation Pending");
        assertEquals(subject.authStateDescription.get(), "Connected features are being enabled");
    }

    @Test
    public void checkVehicleAuthStatus_secondaryAuthPending_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.SECONDARY_AUTH_PENDING);
        when(resourceProvider.getString(R.string.move_landing_activation_pending_heading)).thenReturn("Activation Pending");
        when(resourceProvider.getString(R.string.move_landing_activation_pending_secondary_user)).thenReturn(
                "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Activation Pending");
        assertEquals(subject.authStateDescription.get(), "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
    }

    @Test
    public void checkVehicleAuthStatusForTmcVehicle_primaryAuthPendingUserResetTrueTmcMigrationEnabled_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(true);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities()).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(true);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.PRIMARY_AUTH_PENDING);
        when(resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_header)).thenReturn("Activation Pending");
        when(resourceProvider.getString(R.string.move_vehicle_landing_pending_activation_message)).thenReturn("Connected features are being enabled");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Activation Pending");
        assertEquals(subject.authStateDescription.get(), "Connected features are being enabled");
    }

    @Test
    public void checkVehicleAuthStatus_primaryAuthPendingUserResetFalseTmcMigrationEnabled_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(false);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities()).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(false);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.PRIMARY_AUTH_PENDING);
        when(resourceProvider.getString(R.string.move_landing_activation_pending_heading)).thenReturn("Activation Pending");
        when(resourceProvider.getString(R.string.move_landing_activation_pending_message)).thenReturn(
                "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Activation Pending");
        assertEquals(subject.authStateDescription.get(), "Activate this vehicle to enable lock and unlock, remote start, schedule start and vehicle.");
    }

    @Test
    public void checkVehicleAuthStatus_deactivationPending_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.isPendingReset()).thenReturn(true);
        when(resourceProvider.getString(R.string.move_vehicle_details_user_reset_deactivation_pending)).thenReturn("Deactivation Pending");
        when(resourceProvider.getString(R.string.move_landing_user_reset_deactivation_pending_message)).thenReturn("Deactivate car imminent");
        setupVehicleAuthStates();

        assertEquals(subject.authStateHeader.get(), "Deactivation Pending");
        assertEquals(subject.authStateDescription.get(), "Deactivate car imminent");
        assertFalse(subject.shouldShowVehicleControls.get());
        assertFalse(subject.shouldShowOnlyLockUnlock.get());
        assertFalse(subject.shouldShowPaakVehicleControls.get());
    }

    @Test
    public void getVehicleDisplayName_nickNamePresentAndNickNameMoreThanFifteenChars_displaysNickNameWithEllipsis() {
        when(vehicleInfo.getNickname()).thenReturn(Optional.of("Nathaniel's Expedition"));

        setUpSubject();

        assertEquals("Nathaniel's Exp...", subject.getDisplayName());
    }

    @Test
    public void getVehicleDisplayName_nickNameAbsentAndModelNameMoreThanTwelveChars_displaysPrefixedModelNameWithEllipsis() {
        when(vehicleInfo.getModelName()).thenReturn("Transit Connect");
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Transit Conn..."));
        setUpSubject();
        assertEquals("My Transit Conn...", subject.getDisplayName());
    }

    @Test
    public void getVehicleDisplayName_nickNamePresentAndNickNameLessThanFifteenChars_displaysFullNickNameWithoutEllipsis() {
        when(vehicleInfo.getNickname()).thenReturn(Optional.of("Nathaniel's"));

        setUpSubject();
        assertEquals("Nathaniel's", subject.getDisplayName());
    }

    @Test
    public void getVehicleDisplayName_nickNameAbsentAndModelNameLessThanTwelveChars_displaysPrefixedFullModelNameWithoutEllipsis() {
        when(vehicleInfo.getModelName()).thenReturn("Mustang");
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Mustang"));
        setUpSubject();
        assertEquals("My Mustang", subject.getDisplayName());
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndUserAuthorizedAndTcuAuthorized_callsVehicleHealthAlertWithTcu() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        when(updatedVehicleInfo.isAuthorized()).thenReturn(true);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(true);
        when(updatedVehicleInfo.isPreAuthorized()).thenReturn(true);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_TCU));
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        layoutSetup(optionsModel);
        setupAuthorizedVehicleStatus();

        verify(activeVhaAlertsManager).getActiveAlertsFromCacheThenNetwork(VIN, SOURCE_TCU);
        assertThat(subject.getVehicleHealthAlertsCount()).isEqualTo(2);
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndTcuIsNotAuthorizedAndAppLinkCompatible_callsVehicleHealthAlertWithAppLink() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(updatedVehicleInfo.getVin()).thenReturn(VIN);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_APPLINK));
        setupUnAuthorizedVehicleStatus();

        verify(activeVhaAlertsManager).getActiveAlertsFromCacheThenNetwork(VIN, SOURCE_APPLINK);
        assertThat(subject.getVehicleHealthAlertsCount()).isEqualTo(2);
    }

    @Test
    public void checkVehicleAuthStatus_asdnVehicles_doesNotcallVehicleHealthAlert() {
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);
        setupUnAuthorizedVehicleStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_asdnVehicleAndVehicleAuthStatusDoesNotExit_doesNotCallVehicleHealthAlert() {
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);
        setupMissingAuthorizedVehicleStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndTcuIsNotAuthorizedAndAsdnAndAppLinkCompatible_doesNotCallVehicleHealthAlert() {
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);
        setupAuthorizedVehicleStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuNotEnabledAndAsdn_doesNotCallVehicleHealthAlert() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuNotEnabledAndTmc_doesNotCallVehicleHealthAlert() {
        when(vehicleInfo.getVin()).thenReturn(VIN);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        when(vehicleCapabilitiesManager.getVehicleSdnType(VIN)).thenReturn(Single.just(SdnType.TMC));
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.UNAUTHORIZED));
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_TMC);
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verify(vehicleCapabilitiesManager, never()).getVhaTypeFromVehicleCapabilityService(VIN);
        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndNotAuthorizedAndTmc_doesNotCallVehicleHealthAlert() {
        when(vehicleInfo.getVin()).thenReturn(VIN);
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.isAuthorized()).thenReturn(false);
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        when(vehicleCapabilitiesManager.getVehicleSdnType(VIN)).thenReturn(Single.just(SdnType.TMC));
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.UNAUTHORIZED));
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_TMC);
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
        verify(vehicleCapabilitiesManager, never()).getVhaTypeFromVehicleCapabilityService(VIN);
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndTcuIsNotAuthorizedAndAsdnAndAppLinkNotCompatible_doseNotMakeVehicleHealthAlertNetworkCall() {
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("");
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_ASDN);
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VHA_NOT_SUPPORTED.toString()));
        setupUnAuthorizedVehicleStatus();

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledAndTcuIsNotAuthorizedAndAppLinkIsNotCompatible_doesNotCallVehicleHealthAlert() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VHA_NOT_SUPPORTED.toString()));
        setupUnAuthorizedVehicleStatus();

        verify(activeVhaAlertsManager, never()).getActiveAlertsFromCacheThenNetwork(anyString(), anyString());
    }

    @Test
    public void checkVehicleAuthStatus_tcuEnabledTcuAuthorizedAndUserIsNotAuthorized_doesNotCallVehicleHealthAlert() {
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("");
        when(vehicleInfo.getModelYear()).thenReturn("2016");
        setupUnAuthorizedVehicleStatus();
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VHA_NOT_SUPPORTED.toString()));

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void checkVehicleAuthStatus_tcuDisabledAndAppLinkCompatible_callsVehicleHealthAlertFromAppLink() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.of(Vehicle.Authorization.AUTHORIZED));
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_APPLINK));
        setUpSubject();

        subject.checkVehicleAuthStatus();

        verify(activeVhaAlertsManager).getActiveAlertsFromCacheThenNetwork(VIN, SOURCE_APPLINK);
        assertThat(subject.getVehicleHealthAlertsCount()).isEqualTo(2);
    }

    @Test
    public void checkVehicleAuthStatus_tcuNotEnabledAndAppLinkNotCompatible_doesNotCallVehicleHealthAlert() {
        setupUnAuthorizedVehicleStatus();
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VHA_NOT_SUPPORTED.toString()));

        verifyNoMoreInteractions(activeVhaAlertsManager);
    }

    @Test
    public void launchVehicleDetails_tcuEnabledAndTcuIsNotAuthorizedAndAppLinkCompatible_savesGarageVehicleSelectedVinUseCaseWithTcuEnabled() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Transit Conn..."));
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        setupAuthorizedVehicleStatus();
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_APPLINK));

        subject.launchVehicleDetails();

        verify(transientDataProvider).save(new GarageVehicleSelectedVinUseCase(VIN,
                null, VEHICLE_DISPLAY_PREFIX, "My Transit Conn...",
                "MODEL_YEAR Ford Transit Conn...", "tcu enabled", MODEL_NAME));
    }

    @Test
    public void launchVehicleDetails_tcuEnabledAndAppLinkNotCompatible_savesGarageVehicleSelectedVinUseCaseWithTcuEnabled() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Transit Conn..."));
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        setupAuthorizedVehicleStatus();
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_TCU));

        subject.launchVehicleDetails();

        verify(transientDataProvider).save(new GarageVehicleSelectedVinUseCase(VIN,
                null, VEHICLE_DISPLAY_PREFIX, "My Transit Conn...",
                "MODEL_YEAR Ford Transit Conn...", "tcu enabled", MODEL_NAME));
    }

    @Test
    public void launchVehicleDetails_tcuNotEnabledAndAppLinkCompatible_savesGarageVehicleSelectedVinUseCaseWithAppLinkEnabled() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Transit Conn..."));
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VcsAppLinkCapabilityProvider.VhaType.VHA_2_0_APPLINK));
        setUpSubject();

        subject.checkVehicleAuthStatus();
        subject.launchVehicleDetails();

        verify(transientDataProvider).save(new GarageVehicleSelectedVinUseCase(VIN,
                null, VEHICLE_DISPLAY_PREFIX, "My Transit Conn...",
                "MODEL_YEAR Ford Transit Conn...", "applink enabled", MODEL_NAME));
    }

    @Test
    public void launchVehicleDetails_tcuNotEnabledAndAppLinkIsNotCompatible_savesGarageVehicleSelectedVinUseCaseWithNone() {
        ActiveAlert activeAlert = mock(ActiveAlert.class);
        List<ActiveAlert> alertList = Arrays.asList(activeAlert, activeAlert);
        when(vehicleAlertResponse.getActiveAlerts()).thenReturn(alertList);
        VehicleDetails vehicleDetails = mock(VehicleDetails.class);
        when(vehicleDetails.isTcuAuthorized()).thenReturn(false);
        when(vehicleInfo.getVehicleDetails()).thenReturn((Optional<VehicleDetails>) vehicleDetails);
        when(vehicleInfo.isTcuEnabled()).thenReturn(false);
        when(vehicleInfo.getSDNSourceForTCU()).thenReturn(SOURCE_NGSDN);
        when(vehicleInfo.getLocalizedModelName()).thenReturn(Optional.of("Transit Conn..."));
        when(mBitmapTypeRequest.toBytes(Bitmap.CompressFormat.JPEG, 70)).thenReturn(mBitmapRequestBuilder);
        when(mDrawableTypeRequest.asBitmap()).thenReturn(mBitmapTypeRequest);
        when(glideProvider.load(anyString())).thenReturn(mDrawableTypeRequest);
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        setUpSubject();
        when(vehicleCapabilitiesManager.getVhaTypeFromVehicleCapabilityService(anyString())).thenReturn(Observable.just(VHA_NOT_SUPPORTED.toString()));

        subject.checkVehicleAuthStatus();
        subject.launchVehicleDetails();

        GarageVehicleSelectedVinUseCase usecase = new GarageVehicleSelectedVinUseCase(VIN,
                null, VEHICLE_DISPLAY_PREFIX, "My Transit Conn...",
                "MODEL_YEAR Ford Transit Conn...", "none", MODEL_NAME);
        verify(transientDataProvider).save(usecase);
    }

    @Test
    public void setVehicleRecallAndFsa_setsUrgentAlertIcon() {
        VehicleRecallAndFsa vehicleRecallAndFsa = mock(VehicleRecallAndFsa.class, RETURNS_DEEP_STUBS);
        when(vehicleRecallAndFsa.getRecallList().isEmpty()).thenReturn(false);

        setUpSubject();
        subject.setVehicleRecallAndFsa(vehicleRecallAndFsa);

        assertThat(subject.alertImage.get()).isEqualTo(R.drawable.ic_circle_alert_urgent);
        assertTrue(subject.shouldShowAlert.get());
    }

    @Test
    public void setVehicleRecallAndFsa_setsModerateAlertIcon() {
        VehicleRecallAndFsa vehicleRecallAndFsa = mock(VehicleRecallAndFsa.class, RETURNS_DEEP_STUBS);
        when(vehicleRecallAndFsa.getRecallList().isEmpty()).thenReturn(true);
        when(vehicleRecallAndFsa.getFsaList().isEmpty()).thenReturn(false);

        setUpSubject();
        subject.setVehicleRecallAndFsa(vehicleRecallAndFsa);

        assertThat(subject.alertImage.get()).isEqualTo(R.drawable.ic_circle_alert_moderate);
        assertTrue(subject.shouldShowAlert.get());
    }

    @Test
    public void setVehicleHealthAlertIcon_withNoRecallsAndHasVehicleAlerts_updatesAlertImage() {
        VehicleRecallAndFsa vehicleRecallAndFsa = mock(VehicleRecallAndFsa.class, RETURNS_DEEP_STUBS);
        when(vehicleRecallAndFsa.getRecallList().isEmpty()).thenReturn(true);
        when(vehicleRecallAndFsa.getFsaList().isEmpty()).thenReturn(true);
        setUpSubject();

        subject.setVehicleRecallAndFsa(vehicleRecallAndFsa);
        subject.setVehicleHealthAlertsCount(1);

        assertThat(subject.alertImage.get()).isEqualTo(R.drawable.ic_circle_alert_moderate);
        assertTrue(subject.shouldShowAlert.get());
    }

    @Test
    public void setVehicleHealthAlertIcon_withNoRecallsAndNoVehicleAlerts_doesNotUpdateAlertImage() {
        VehicleRecallAndFsa vehicleRecallAndFsa = mock(VehicleRecallAndFsa.class, RETURNS_DEEP_STUBS);
        when(vehicleRecallAndFsa.getRecallList().isEmpty()).thenReturn(true);
        when(vehicleRecallAndFsa.getFsaList().isEmpty()).thenReturn(true);
        setUpSubject();

        subject.setVehicleRecallAndFsa(vehicleRecallAndFsa);
        subject.setVehicleHealthAlertsCount(0);

        assertFalse(subject.shouldShowAlert.get());
    }

    @Test
    public void setVehicleHealthAlertIcon_withRecallsAndHasVehicleAlerts_doesNotUpdateAlertImage() {
        VehicleRecallAndFsa vehicleRecallAndFsa = mock(VehicleRecallAndFsa.class, RETURNS_DEEP_STUBS);
        when(vehicleRecallAndFsa.getRecallList().isEmpty()).thenReturn(false);

        setUpSubject();
        subject.setVehicleRecallAndFsa(vehicleRecallAndFsa);

        assertThat(subject.alertImage.get()).isEqualTo(R.drawable.ic_circle_alert_urgent);

        subject.setVehicleHealthAlertsCount(1);

        assertThat(subject.alertImage.get()).isEqualTo(R.drawable.ic_circle_alert_urgent);
    }

    @Test
    public void setupElectricVehicle_vehicleIsNotElectric_doesNotSetChargeStatus() {
        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(false));

        setUpSubject();
        subject.setupElectricVehicle(garageVehicleViewModel);

        verify(vehicleInfoProvider, never()).getVehicleStatus(any(VehicleInfo.class), eq(CacheTransformerProvider.Policy.NETWORK_ONLY));
        assertThat(subject.vehicleChargingStatus.get()).isEqualTo("");
        assertThat(subject.showVehicleChargingStatus.get()).isFalse();
    }

    @Test
    public void setupElectricVehicle_vehicleIsElectricAndIsUnpluggedAndIsNotOutAndAbout_setsChargingStatusToUnplugged() {
        setUpSubject();

        Drawable unpluggedDrawable = mock(Drawable.class);

        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(true));
        when(vehicleInfoProvider.getVehicleStatus(vehicleInfo, CacheTransformerProvider.Policy.NETWORK_ONLY)).thenReturn(Observable.just(vehicleInfo));
        when(vehicleInfo.getVehicleStatus()).thenReturn(Optional.of(vehicleStatus));
        when(chargingStatusUtil.shouldShowUnplugged(vehicleStatus)).thenReturn(true);
        when(vehicleStatus.isOutAndAbout()).thenReturn(false);
        when(resourceProvider.getString(R.string.move_ev_vehiclelanding_chargestatus_status_unplugged)).thenReturn("Unplugged");
        when(resourceProvider.getDrawable(R.drawable.ic_ev_unplugged)).thenReturn(unpluggedDrawable);

        subject.setupElectricVehicle(garageVehicleViewModel);

        assertThat(subject.vehicleChargingStatus.get()).isEqualTo("Unplugged");
        assertThat(subject.vehicleChargingIcon.get()).isEqualTo(unpluggedDrawable);
        assertThat(subject.showVehicleChargingStatus.get()).isTrue();
    }

    @Test
    public void setupElectricVehicle_vehicleIsElectricAndIsUnPluggedAndIsOutAndAbout_setsChargeStatusToOutAndAbout() {
        setUpSubject();

        Drawable unpluggedDrawable = mock(Drawable.class);

        when(vehicleInfo.getVehicleStatus()).thenReturn(Optional.of(vehicleStatus));
        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(true));
        when(chargingStatusUtil.shouldShowUnplugged(vehicleStatus)).thenReturn(true);
        when(vehicleStatus.isOutAndAbout()).thenReturn(true);
        when(resourceProvider.getDrawable(R.drawable.ic_ev_unplugged)).thenReturn(unpluggedDrawable);
        when(resourceProvider.getString(R.string.move_ev_vehiclelanding_chargestatus_status_out_and_about)).thenReturn("OutAndAbout");

        subject.showVehicleChargingStatus.set(true);
        subject.setupElectricVehicle(garageVehicleViewModel);

        assertThat(subject.vehicleChargingStatus.get()).isEqualTo("OutAndAbout");
        assertThat(subject.showVehicleChargingStatus.get()).isTrue();
        assertThat(subject.vehicleChargingIcon.get()).isEqualTo(unpluggedDrawable);
    }

    @Test
    public void setupElectricVehicle_vehicleIsElectricAndIsPluggedInAndHasShowChargingStatus_setsChargingStatus() {
        setUpSubject();
        Drawable pluggedInDrawable = mock(Drawable.class);

        when(vehicleInfo.getVehicleStatus()).thenReturn(Optional.of(vehicleStatus));
        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(true));
        when(chargingStatusUtil.shouldShowUnplugged(vehicleStatus)).thenReturn(false);
        when(vehicleStatus.isOutAndAbout()).thenReturn(true);
        when(chargingStatusUtil.shouldShowChargingStatus(vehicleStatus)).thenReturn(true);
        when(chargingStatusUtil.getChargingStatus(vehicleStatus)).thenReturn("Charging");
        when(chargingStatusUtil.getChargingIcon(vehicleStatus)).thenReturn(R.drawable.ic_ev_unplugged);
        when(resourceProvider.getDrawable(R.drawable.ic_ev_unplugged)).thenReturn(pluggedInDrawable);

        subject.setupElectricVehicle(garageVehicleViewModel);

        assertThat(subject.vehicleChargingStatus.get()).isEqualTo("Charging");
        assertThat(subject.vehicleChargingIcon.get()).isEqualTo(pluggedInDrawable);
        assertThat(subject.showVehicleChargingStatus.get()).isTrue();
    }

    @Test
    public void setupElectricVehicle_vehicleIsElectricAndIsPluggedInAndDoesNotHaveChargingStatus_doesNotSetChargingStatus() {
        setUpSubject();

        when(vehicleInfo.getVehicleStatus()).thenReturn(Optional.of(vehicleStatus));
        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(true));
        when(chargingStatusUtil.shouldShowUnplugged(vehicleStatus)).thenReturn(false);
        when(vehicleStatus.isOutAndAbout()).thenReturn(true);
        when(chargingStatusUtil.shouldShowChargingStatus(vehicleStatus)).thenReturn(false);
        when(chargingStatusUtil.getChargingStatus(vehicleStatus)).thenReturn("Charging");
        when(chargingStatusUtil.getChargingIcon(vehicleStatus)).thenReturn(R.drawable.ic_ev_unplugged);

        subject.setupElectricVehicle(garageVehicleViewModel);

        verify(chargingStatusUtil, never()).getChargingStatus(any(VehicleStatus.class));
        verify(chargingStatusUtil, never()).getChargingIcon(vehicleStatus);
        assertThat(subject.vehicleChargingStatus.get()).isEqualTo("");
        assertThat(subject.showVehicleChargingStatus.get()).isFalse();
    }

    @Test
    public void setupElectricVehicle_observableReturnsAThrowable_showErrorBanner() {
        setUpSubject();
        when(vinLookupProvider.isHybridElectricVehicle(VIN)).thenReturn(Single.just(true));
        when(vehicleInfoProvider.getVehicleStatus(any(VehicleInfo.class), any(CacheTransformerProvider.Policy.class))).thenReturn(Observable.error(new Throwable()));

        subject.setupElectricVehicle(garageVehicleViewModel);

        verify(errorMessageUtil).showErrorMessage(R.string.common_error_something_went_wrong_no_try_again, 2);
    }

    void assertFordPassConnect() {
        assertEquals(subject.authStateHeader.get(), "FordPass Connect™");
        assertEquals(subject.authStateDescription.get(), "You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
    }

    void setupAuthorizedVehicleStatus() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        setUpSubject();

        subject.checkVehicleAuthStatus();
    }

    void setupUnAuthorizedVehicleStatus() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        setUpSubject();

        subject.checkVehicleAuthStatus();
    }

    void setupMissingAuthorizedVehicleStatus() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleInfo.getAuthorization()).thenReturn(Optional.absent());
        setUpSubject();

        subject.checkVehicleAuthStatus();
    }

    void setupVehicleAuthStates() {
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        setUpSubject();
        subject.checkVehicleAuthStatus();
    }

    void mockGlideProvider() {
        DrawableTypeRequest mockRequest = mock(DrawableTypeRequest.class);
        when(glideProvider.load("TESTURL")).thenReturn(mockRequest);
        BitmapTypeRequest mockBitmapRequest = mock(BitmapTypeRequest.class);
        when(mockRequest.asBitmap()).thenReturn(mockBitmapRequest);
    }

    private void assertifySetVehicleControlVisibility(VehicleControlOptionsModel optionsModel) {
        verify(optionsModel).isLightsAndHornEligible();
        verify(optionsModel).isLockUnlockEligible();
        verify(optionsModel, times(2)).isPaakCapable();
        verify(optionsModel).isRemoteStartEligible();
        verify(paakVehicleControlsViewModel.getLocksAssetManager()).setPaakEnabled(optionsModel.isPaakCapable());
        verify(paakVehicleControlsViewModel).setVehicleControlsForPaak();
        boolean remoteStartEligible = optionsModel.isRemoteStartEligible();
        boolean lockUnlockEligible = optionsModel.isLockUnlockEligible();
        boolean paakCapable = optionsModel.isPaakCapable();
        assertThat(subject.shouldShowPaakVehicleControls.get()).isEqualTo(paakCapable);
        assertThat(subject.shouldShowRemoteStartOnly.get()).isEqualTo(remoteStartEligible && !lockUnlockEligible && !paakCapable);
        assertThat(subject.shouldShowVehicleControls.get()).isEqualTo(remoteStartEligible && lockUnlockEligible && !paakCapable);
        assertThat(subject.shouldShowOnlyLockUnlock.get()).isEqualTo(!remoteStartEligible && lockUnlockEligible && !paakCapable);
    }

    protected void layoutSetup(VehicleControlOptionsModel optionsModel) {
        when(vehicleControlManager.getVehicleControlOptions(any())).thenReturn(Observable.just(optionsModel));
        when(vehicleInfo.isTcuEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.AUTHORIZED);
        when(paakAdapter.init()).thenReturn(Completable.complete());
        when(configurationProvider.getConfiguration().isPaakEnabled()).thenReturn(false);
    }

    protected abstract void setUpSubject();
}
