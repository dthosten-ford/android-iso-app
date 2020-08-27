/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.move;

import com.ford.locale.ServiceLocale;
import com.ford.paak.bluetooth.BleConnectivityManager;
import com.ford.paak.bluetooth.service.PaakBleServiceConnection;
import com.ford.rxutils.CacheTransformerProvider;
import com.ford.vcs.models.Feature;
import com.ford.vcs.models.State;
import com.ford.vehiclecommon.enums.Source;
import com.ford.vehiclecommon.models.GarageVehicleProfile;
import com.ford.xapi.models.response.XapiAuthStatus;
import com.fordmps.mobileapp.move.paak.LocksAssetManager;
import com.fordmps.mobileapp.shared.datashare.usecases.GarageVehicleSelectedVinUseCase;
import com.fordmps.mobileapp.shared.events.StartActivityEvent;
import com.fordmps.viewutils.R;
import com.google.common.base.Optional;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static com.ford.vcs.models.Feature.FeatureNames.USER_RESET;
import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GarageVehicleViewModelTest extends BaseGarageVehicleViewModelTest {

    @Mock
    PaakBleServiceConnection paakBleServiceConnection;

    @Mock
    BleConnectivityManager bleConnectivityManager;

    @Test
    public void onLaunchVehicleDetails_sendsStartActivityEvent() {
        ArgumentCaptor<GarageVehicleSelectedVinUseCase> useCaseCaptor = ArgumentCaptor.forClass(GarageVehicleSelectedVinUseCase.class);
        setUpSubject();
        when(vehicleProfile.getYear()).thenReturn("2018");
        when(vehicleProfile.getLocalizedModelName()).thenReturn(Optional.of("Focus"));
        when(vehicleProfile.getModel()).thenReturn("Focus");
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        when(resourceProvider.getString(R.string.common_environment_brand)).thenReturn("Ford");

        subject.launchVehicleDetails();

        verify(eventBus).send(StartActivityEvent.build(subject).activityName(VehicleDetailsActivity.class));
        verify(transientDataProvider).save(useCaseCaptor.capture());
        assertThat(useCaseCaptor.getValue().getVin()).isEqualTo(VIN);
        assertThat(useCaseCaptor.getValue().getVehicleYearModel()).isEqualTo("2018 Ford Focus");
        assertThat(useCaseCaptor.getValue().getMyVehiclePrefix()).isEqualTo(VEHICLE_DISPLAY_PREFIX);
        assertThat(useCaseCaptor.getValue().getModelName()).isEqualTo("Focus");
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYearBefore2019ForNARegion_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2017");
        when(resourceProvider.getString(R.string.common_sync_connect_brand_name)).thenReturn("SYNC Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
        setupVehicleAuthStates();

        assertSyncConnect();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndSdnTypeTMC_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2017");
        when(vehicleProfile.getSDNSourceForTCU()).thenReturn(Source.SOURCE_TMC);
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_header)).thenReturn("Activate vehicle");
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_message)).thenReturn("Activate this vehicle in vehicle details to enable connected vehicle features");
        setupVehicleAuthStates();

        assertTmcVehicle();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYear2019ForNARegion_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2019");
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("FordPass Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
        setupVehicleAuthStates();

        assertFordPassConnect();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYearAfter2019ForNARegion_showsCorrectAuthStateHeaderAndDescription() {
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2020");
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("FordPass Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
        setupVehicleAuthStates();

        assertFordPassConnect();
    }

    @Test
    public void checkVehicleAuthStatus_vehicleAuthorized_callsToGetVehicleStatus() {
        VehicleControlOptionsModel optionsModel = mock(VehicleControlOptionsModel.class);
        when(optionsModel.isRemoteStartEligible()).thenReturn(true);
        when(optionsModel.isLockUnlockEligible()).thenReturn(true);
        when(vehicleControlManager.getVehicleControlOptionsModel(VIN)).thenReturn(Observable.just(optionsModel));
        setupAuthorizedVehicleStatus();

        assertThat(subject.shouldShowVehicleControls.get()).isEqualTo(true);
        verify(vehicleInfoProvider).getGarageVehicleStatus(vehicleProfile, CacheTransformerProvider.Policy.NETWORK_ONLY);
    }

    @Test
    public void getBrandYearAndModel_isLocaleNotCanadaFrench_returnsModelYearBeforeModelName() {
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        String localisedModelName = "Focus";
        String brand = "Ford";
        when(vehicleProfile.getLocalizedModelName()).thenReturn(Optional.of(localisedModelName));
        when(vehicleProfile.getYear()).thenReturn("2018");
        setUpSubject();

        String brandYearAndModel = subject.getBrandYearAndModel(brand);

        assertEquals(brandYearAndModel, "2018 " + brand + " " + localisedModelName);
    }

    @Test
    public void getBrandYearAndModel_isLocaleCanadaFrench_returnsModelYearAfterModelName() {
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.CANADA_FRENCH);
        String localisedModelName = "Focus";
        String brand = "Ford";
        when(vehicleProfile.getLocalizedModelName()).thenReturn(Optional.of(localisedModelName));
        when(vehicleProfile.getYear()).thenReturn("2018");
        setUpSubject();

        String brandYearAndModel = subject.getBrandYearAndModel(brand);

        assertEquals(brandYearAndModel, brand + " " + localisedModelName + " 2018");
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYearBefore2019ForNARegionAndTmcMigrationEnabledAndUserResetFalse_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(false);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities("VIN")).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(false);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2017");
        when(resourceProvider.getString(R.string.common_sync_connect_brand_name)).thenReturn("SYNC Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");

        setupVehicleAuthStates();

        assertSyncConnect();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYear2019ForNARegionAndTmcMigrationEnabledAndUserResetFalse_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(false);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities("VIN")).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(false);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2019");
        when(resourceProvider.getString(R.string.common_fordpass_connect_brand_name)).thenReturn("FordPass Connect™");
        when(resourceProvider.getString(R.string.move_landing_sync_connect_description)).thenReturn("You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");

        setupVehicleAuthStates();

        assertFordPassConnect();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYearBefore2019ForNARegionAndTmcMigrationEnabledAndUserResetTrue_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(true);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities("VIN")).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(true);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2017");
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_header)).thenReturn("Activate vehicle");
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_message)).thenReturn("Activate this vehicle in vehicle details to enable connected vehicle features");

        setupVehicleAuthStates();

        assertTmcVehicle();
    }

    @Test
    public void checkVehicleAuthStatus_unauthorizedAndModelYear2019ForNARegionAndTmcMigrationEnabledAndUserResetTrue_showsCorrectAuthStateHeaderAndDescription() {
        setUpSubject();

        Feature userreset = mock(Feature.class);
        State userresetFeatureState = mock(State.class);
        List<Feature> features = Collections.singletonList(userreset);
        when(userreset.getFeature()).thenReturn("USER_RESET");
        when(userresetFeatureState.getEligible()).thenReturn(true);
        when(userreset.getState()).thenReturn(userresetFeatureState);
        when(result.getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getVehicleCapabilities("VIN")).thenReturn(Observable.just(vehicleCapabilitiesResponse));
        when(vehicleCapabilitiesManager.isValidVcsResponse(vehicleCapabilitiesResponse)).thenReturn(true);
        when(vehicleCapabilitiesResponse.getResult().getFeatures()).thenReturn(features);
        when(vehicleCapabilitiesManager.getFeatureEligibility(features, USER_RESET)).thenReturn(true);
        when(configurationProvider.getConfiguration().isTmcMigrationEnabled()).thenReturn(true);
        when(vehicleAuthorizationDataManager.getXApiAuthorizationStatus()).thenReturn(XapiAuthStatus.UNAUTHORIZED);
        when(vehicleProfile.getYear()).thenReturn("2019");
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_header)).thenReturn("Activate vehicle");
        when(resourceProvider.getString(R.string.move_landing_epid_no_activation_message)).thenReturn("Activate this vehicle in vehicle details to enable connected vehicle features");

        setupVehicleAuthStates();

        assertTmcVehicle();
    }

    @Test
    public void getBrandYearAndModel_vehicleProfileHasBrandModelAndYear_returnsBrandNameModelAndYear() {
        when(vehicleProfile.getYear()).thenReturn("2018");
        when(vehicleProfile.getLocalizedModelName()).thenReturn(Optional.of("Focus"));
        when(serviceLocaleProvider.getLocale()).thenReturn(ServiceLocale.US);
        setUpSubject();

        assertEquals(subject.getBrandYearAndModel("Ford"), "2018 Ford Focus");
    }

    @Test
    public void getYearAndBrand_returnsYearAndBrand() {
        when(vehicleProfile.getYear()).thenReturn("2018");
        setUpSubject();

        assertEquals(subject.getYearAndBrand("Ford"), "2018 Ford");
    }

    @Test
    public void getYearAndBrand_doesNotHaveYear_returnsEmptyString() {
        when(vehicleProfile.getYear()).thenReturn("");
        setUpSubject();

        assertEquals(subject.getYearAndBrand("Ford"),  " Ford");
    }

    private void assertSyncConnect() {
        assertEquals(subject.authStateHeader.get(), "SYNC Connect™");
        assertEquals(subject.authStateDescription.get(), "You can enable features such as remote start, lock and unlock, and schedule start in vehicle details below.");
    }

    private void assertTmcVehicle() {
        assertEquals(subject.authStateHeader.get(), "Activate vehicle");
        assertEquals(subject.authStateDescription.get(), "Activate this vehicle in vehicle details to enable connected vehicle features");
    }

    @Override
    protected void setUpSubject() {
        when(paakVehicleControlsViewModelFactory.newInstance(any(GarageVehicleProfile.class))).thenReturn(paakVehicleControlsViewModel);
        when(paakVehicleControlsViewModelFactory.newInstance()).thenReturn(paakVehicleControlsViewModel);
        when(paakVehicleControlsViewModel.getLocksAssetManager()).thenReturn(mock(LocksAssetManager.class));
        subject = new GarageVehicleViewModel.Factory(() -> glideProvider, activeVhaAlertsManager, integrationUtil, vehicleImageUrlProvider, eventBus, transientDataProvider,
                vehicleInfoProvider, resourceProvider, ngsdnVehicleProvider, vehicleCapabilitiesManager, sharedPrefsUtil,
                errorMessageUtil, serviceLocaleProvider, configurationProvider, rxSchedulingHelper,
                vinLookupProvider, chargingStatusUtil, () -> paakAdapter, paakVehicleControlsViewModelFactory,
                accountInfoProvider, vehicleControlManager, vehicleAuthorizationDataManager, rxSchedulerProvider)
                .newInstance(vehicleProfile, imageLoadEvent);
    }
}