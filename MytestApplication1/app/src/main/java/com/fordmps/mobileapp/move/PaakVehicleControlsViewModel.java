package com.fordmps.mobileapp.move;

import com.ford.dashboard.models.VehicleInfo;

public interface PaakVehicleControlsViewModel {
    void setVehicleVisible();

    void setVehicleNotVisible();

    void showGenericErrorMessage();

    LocksAssetManager getLocksAssetManager();

    void setVehicleControlsForPaak();

    void checkVehicleState(VehicleInfo vehicleInfo);

    void onDestroy();

    public interface Factory {
        Object newInstance(VehicleInfo any);
    }

//    public static class Factory {
//        private final VehicleCommandManager vehicleCommandManager;
//        private final ResourceProvider resourceProvider;
//        private final TransientDataProvider transientDataProvider;
//        private final VehicleInfoProvider vehicleInfoProvider;
//        private final RemoteStartCountdownManager.Factory remoteCountDownManagerFactory;
//        private final CcsAlertBannerViewModel.Factory ccsAlertBannerViewModelFactory;
//        private final VcsRepository vcsRepository;
//        private final UnboundViewEventBus eventBus;
//        private final SharedPrefsUtil sharedPrefsUtil;
//        private final MoveAnalyticsManager moveAnalyticsManager;
//        private final BleConnectivityManager bleConnectivityManager;
//        private final PaakAdapter paakAdapter;
//        private final PaakBleServiceConnection bleServiceConnection;
//        private final WifiHotspotProvider wifiHotspotProvider;
//        private final EcallAlertBannerViewModel.Factory ecallAlertBannerViewModelFactory;
//        private final CommandButton.Factory commandButtonFactory;
//        private final DelayActionUtil delayActionUtil;
//        private final LocksAssetManager locksAssetManager;
//        private final VehicleControlsStringResourceUtil vehicleControlsStringResourceUtil;
//        private final ConfigurationProvider configurationProvider;
//        private final PreferenceManager preferenceManager;
//        private final CvCoreCommandManager cvCoreCommandManager;
//        private final VehicleCommandProcessor.Factory vehicleCommandProcessor;
//        private final VehicleCommandSubmitter.Factory vehicleCommandExecutor;
//        private final VehicleControlManager vehicleControlManager;
//
//        @Inject
//        Factory(VehicleCommandManager vehicleCommandManager,
//                ResourceProvider resourceProvider,
//                TransientDataProvider transientDataProvider,
//                VehicleInfoProvider vehicleInfoProvider,
//                RemoteStartCountdownManager.Factory remoteCountDownManagerFactory,
//                CcsAlertBannerViewModel.Factory ccsAlertBannerViewModelFactory,
//                VcsRepository vcsRepository,
//                UnboundViewEventBus eventBus,
//                SharedPrefsUtil sharedPrefsUtil,
//                MoveAnalyticsManager moveAnalyticsManager,
//                BleConnectivityManager bleConnectivityManager,
//                PaakAdapter paakAdapter,
//                PaakBleServiceConnection bleServiceConnection,
//                WifiHotspotProvider wifiHotspotProvider,
//                EcallAlertBannerViewModel.Factory ecallAlertBannerViewModelFactory,
//                CommandButton.Factory commandButtonFactory,
//                DelayActionUtil delayActionUtil,
//                LocksAssetManager locksAssetManager,
//                VehicleControlsStringResourceUtil vehicleControlsStringResourceUtil,
//                ConfigurationProvider configurationProvider,
//                PreferenceManager preferenceManager,
//                CvCoreCommandManager cvCoreCommandManager,
//                VehicleCommandProcessor.Factory vehicleCommandProcessor,
//                VehicleCommandSubmitter.Factory vehicleCommandExecutor,
//                VehicleControlManager vehicleControlManager) {
//            this.vehicleCommandManager = vehicleCommandManager;
//            this.resourceProvider = resourceProvider;
//            this.transientDataProvider = transientDataProvider;
//            this.vehicleInfoProvider = vehicleInfoProvider;
//            this.remoteCountDownManagerFactory = remoteCountDownManagerFactory;
//            this.ccsAlertBannerViewModelFactory = ccsAlertBannerViewModelFactory;
//            this.vcsRepository = vcsRepository;
//            this.eventBus = eventBus;
//            this.sharedPrefsUtil = sharedPrefsUtil;
//            this.moveAnalyticsManager = moveAnalyticsManager;
//            this.bleConnectivityManager = bleConnectivityManager;
//            this.paakAdapter = paakAdapter;
//            this.bleServiceConnection = bleServiceConnection;
//            this.wifiHotspotProvider = wifiHotspotProvider;
//            this.ecallAlertBannerViewModelFactory = ecallAlertBannerViewModelFactory;
//            this.commandButtonFactory = commandButtonFactory;
//            this.delayActionUtil = delayActionUtil;
//            this.locksAssetManager = locksAssetManager;
//            this.vehicleControlsStringResourceUtil = vehicleControlsStringResourceUtil;
//            this.configurationProvider = configurationProvider;
//            this.preferenceManager = preferenceManager;
//            this.cvCoreCommandManager = cvCoreCommandManager;
//            this.vehicleCommandProcessor = vehicleCommandProcessor;
//            this.vehicleCommandExecutor = vehicleCommandExecutor;
//            this.vehicleControlManager = vehicleControlManager;
//        }
//
//        public PaakVehicleControlsViewModel newInstance() {
//            return newInstance(null);
//        }
//
//        public PaakVehicleControlsViewModel newInstance(VehicleInfo vehicleInfo) {
//            return new PaakVehicleControlsViewModel(vehicleCommandManager,
//                    vehicleInfo,
//                    resourceProvider,
//                    transientDataProvider,
//                    vehicleInfoProvider,
//                    remoteCountDownManagerFactory,
//                    ccsAlertBannerViewModelFactory,
//                    vcsRepository,
//                    eventBus,
//                    sharedPrefsUtil,
//                    moveAnalyticsManager,
//                    bleConnectivityManager,
//                    paakAdapter,
//                    bleServiceConnection,
//                    wifiHotspotProvider,
//                    ecallAlertBannerViewModelFactory,
//                    commandButtonFactory,
//                    delayActionUtil,
//                    locksAssetManager,
//                    vehicleControlsStringResourceUtil,
//                    configurationProvider,
//                    preferenceManager,
//                    cvCoreCommandManager,
//                    vehicleCommandProcessor,
//                    vehicleCommandExecutor,
//                    vehicleControlManager);
//        }
//    }
}
