/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.move;

//public class PaakVehicleControlsViewModel extends VehicleControlsViewModel implements PaakVehicleControlsDefaultMethods, PaakVehicleControlsViewModelInterface {
//    // TODO convert to Kotlin and adapt to refactored VehicleControlsViewModel
//    private static final int BLUETOOTH_REQUEST_CODE = 999;
//    private static final int BLUETOOTH_POLL_INTERVAL = 17;
//
//    public final ObservableField<String> vehicleName = new ObservableField<>("");
//    public final ObservableInt bluetoothConnectionStatus = new ObservableInt(R.string.move_vehicle_controls_paak_bt_status_disconnected);
//    public final ObservableBoolean showWindowsUpInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showWindowsUpTransient = new ObservableBoolean(false);
//    public final ObservableBoolean showWindowsDownInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showWindowsDownTransient = new ObservableBoolean(false);
//    public final ObservableBoolean showTrunkInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showTrunkTransient = new ObservableBoolean(false);
//    public final ObservableBoolean showFrunkInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showFrunkTransient = new ObservableBoolean(false);
//    public final ObservableBoolean showLockAndChirpInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showLockAndChirpTransient = new ObservableBoolean(false);
//    public final ObservableBoolean showPanicInitiate = new ObservableBoolean(false);
//    public final ObservableBoolean showPanicTransient = new ObservableBoolean(false);
//    private ObservableBoolean isBluetoothConnectionEstablished = new ObservableBoolean(false);
//    public ObservableBoolean shouldShowFrunkLayout = new ObservableBoolean(false);
//    private ObservableBoolean isKeyDeliveredToVehicle = new ObservableBoolean(false);
//    public final ObservableBoolean bluetoothButtonsClickable =
//            new ObservableBoolean(isCommandInProgress(), isBluetoothConnectionEstablished, isKeyDeliveredToVehicle) {
//                @Override
//                public boolean get() {
//                    return !isCommandInProgress().get() && isBluetoothConnectionEstablished.get() && isKeyDeliveredToVehicle.get();
//                }
//            };
//
//    public final ObservableField<Drawable> bannerIcon = new ObservableField<>();
//    public final ObservableBoolean showBannerIcon = new ObservableBoolean(false);
//    public final ObservableBoolean showPaakNavigationIcon = new ObservableBoolean(false);
//    public final ObservableBoolean isFunctionalityLimited = new ObservableBoolean(false);
//    public final ObservableBoolean showSpinnerAnimation = new ObservableBoolean(false);
//    public CommandButton windowsDownButton;
//    public CommandButton windowsUpButton;
//    public CommandButton frunkButton;
//    public CommandButton lockAndChirpButton;
//    public CommandButton trunkButton;
//    public CommandButton panicButton;
//
//    private final PaakAdapter paakAdapter;
//    private final PaakBleServiceConnection bleServiceConnection;
//    private final BleConnectivityManager bleConnectivityManager;
//    private final PreferenceManager preferenceManager;
//    private Disposable pollIntervalDisposable = Disposables.disposed();
//    private Disposable bluetoothStateDisposable = Disposables.disposed();
//    private boolean panicIsOn = false;
//    private boolean shouldShowBluetoothPrompt = false;
//    private boolean isForegroundServiceRunning = false;
//    private HashMap<String, ActiveCommands> vehicleToActiveCommandMap = new HashMap<String, ActiveCommands>() {{
//        put(VehicleCommand.START, ActiveCommands.REMOTE_START);
//        put(VehicleCommand.EXTEND_START, ActiveCommands.REMOTE_START);
//        put(VehicleCommand.STOP, ActiveCommands.REMOTE_STOP);
//        put(VehicleCommand.LOCK, ActiveCommands.LOCK_ALL);
//        put(VehicleCommand.UNLOCK, ActiveCommands.UNLOCK_ALL_DOORS);
//        put(VehicleCommand.PAAK_WINDOWS_UP, ActiveCommands.GLOBAL_CLOSE);
//        put(VehicleCommand.PAAK_WINDOWS_DOWN, ActiveCommands.GLOBAL_OPEN);
//        put(VehicleCommand.PAAK_OPEN_CLOSE_TRUNK, ActiveCommands.POWER_LIFT_GATE);
//        put(VehicleCommand.PAAK_UNLOCK_FRUNK, ActiveCommands.UNLOCK_FRUNK);
//        put(VehicleCommand.PAAK_LOCK_AND_CHIRP, ActiveCommands.LOCK_ALL);
//        put(VehicleCommand.PAAK_PANIC_ON, ActiveCommands.PANIC_ON);
//        put(VehicleCommand.PAAK_PANIC_OFF, ActiveCommands.PANIC_OFF);
//    }};
//
//    private PaakVehicleControlsViewModel(
//            VehicleCommandManager vehicleCommandManager,
//            VehicleInfo vehicleInfo,
//            ResourceProvider resourceProvider,
//            TransientDataProvider transientDataProvider,
//            VehicleInfoProvider vehicleInfoProvider,
//            RemoteStartCountdownManager.Factory remoteCountDownManagerFactory,
//            CcsAlertBannerViewModel.Factory ccsAlertBannerViewModelFactory,
//            VcsRepository vcsRepository,
//            UnboundViewEventBus eventBus,
//            SharedPrefsUtil sharedPrefsUtil,
//            MoveAnalyticsManager moveAnalyticsManager,
//            BleConnectivityManager bleConnectivityManager,
//            PaakAdapter paakAdapter,
//            PaakBleServiceConnection bleServiceConnection,
//            WifiHotspotProvider wifiHotspotProvider,
//            EcallAlertBannerViewModel.Factory ecallAlertBannerViewModelFactory,
//            CommandButton.Factory commandButtonFactory,
//            DelayActionUtil delayActionUtil,
//            LocksAssetManager locksAssetManager,
//            VehicleControlsStringResourceUtil vehicleControlsStringResourceUtil,
//            ConfigurationProvider configurationProvider,
//            PreferenceManager preferenceManager,
//            CvCoreCommandManager cvCoreCommandManager,
//            VehicleCommandProcessor.Factory vehicleCommandProcessor,
//            VehicleCommandSubmitter.Factory vehicleControlsExecutor,
//            VehicleControlManager vehicleControlManager) {
//        super(resourceProvider, vehicleInfo, transientDataProvider, vehicleInfoProvider,
//                vcsRepository, eventBus, sharedPrefsUtil, moveAnalyticsManager,
//                configurationProvider, delayActionUtil, cvCoreCommandManager, vehicleCommandManager,
//                locksAssetManager, remoteCountDownManagerFactory.newInstance(), wifiHotspotProvider,
//                vehicleControlsStringResourceUtil, ccsAlertBannerViewModelFactory,
//                ecallAlertBannerViewModelFactory, commandButtonFactory,
//                vehicleCommandProcessor, vehicleControlsExecutor, vehicleControlManager);
//
//        this.bleServiceConnection = bleServiceConnection;
//        this.paakAdapter = paakAdapter;
//        this.bleConnectivityManager = bleConnectivityManager;
//        this.preferenceManager = preferenceManager;
//        windowsDownButton = commandButtonFactory.getButton(VehicleCommand.PAAK_WINDOWS_DOWN);
//        windowsUpButton = commandButtonFactory.getButton(VehicleCommand.PAAK_WINDOWS_UP);
//        frunkButton = commandButtonFactory.getButton(VehicleCommand.PAAK_UNLOCK_FRUNK);
//        lockAndChirpButton = commandButtonFactory.getButton(VehicleCommand.PAAK_LOCK_AND_CHIRP);
//        trunkButton = commandButtonFactory.getButton(VehicleCommand.PAAK_OPEN_CLOSE_TRUNK);
//        panicButton = commandButtonFactory.getButton(VehicleCommand.PAAK_PANIC_ON);
//        getCommandButtonMap().put(VehicleCommand.PAAK_WINDOWS_UP, windowsUpButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_WINDOWS_DOWN, windowsDownButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_OPEN_CLOSE_TRUNK, trunkButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_LOCK_AND_CHIRP, lockAndChirpButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_PANIC_ON, panicButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_PANIC_OFF, panicButton);
//        getCommandButtonMap().put(VehicleCommand.PAAK_UNLOCK_FRUNK, frunkButton);
//        getAnimationCommandMap().put(VehicleCommand.PAAK_WINDOWS_UP, new kotlin.Pair<>(showWindowsUpInitiate, showWindowsUpTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_WINDOWS_DOWN, new kotlin.Pair<>(showWindowsDownInitiate, showWindowsDownTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_OPEN_CLOSE_TRUNK, new kotlin.Pair<>(showTrunkInitiate, showTrunkTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_LOCK_AND_CHIRP, new kotlin.Pair<>(showLockAndChirpInitiate, showLockAndChirpTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_PANIC_ON, new kotlin.Pair<>(showPanicInitiate, showPanicTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_PANIC_OFF, new kotlin.Pair<>(showPanicInitiate, showPanicTransient));
//        getAnimationCommandMap().put(VehicleCommand.PAAK_UNLOCK_FRUNK, new kotlin.Pair<>(showFrunkInitiate, showFrunkTransient));
//        getKeyIconDrawable().set(resourceProvider.getDrawable(R.drawable.ic_paak_control_icon));
//        setupBluetoothAndStartBluetoothMonitoring();
//        startBeaconIfThereIsAnActiveKey();
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    public void init() {
//        getOptionsModelObservable().subscribe(optionsModel ->
//                    shouldShowFrunkLayout.set(optionsModel.isFrunkEligible()),
//                    Throwable::printStackTrace);
//        shouldShowBluetoothPrompt = true;
//        if (getTransientDataProvider().containsUseCase(PaakVehicleControlUseCase.class)) {
//            PaakVehicleControlUseCase paakVehicleControlUseCase = getTransientDataProvider().remove(PaakVehicleControlUseCase.class);
//            vehicleName.set(paakVehicleControlUseCase.getVehicleName());
//            getLocksAssetManager().setOnMoveLanding(false);
//        }
//        getMoveAnalyticsManager().trackStateWithVin(MOVE_VEHICLE_PAAK, getVehicleInfo().getVin());
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    public void setupBluetoothMonitoring() {
//        checkVehicleStatus();
//        checkForLimitedFunctionality();
//        checkForSmcUpdates();
//        setupBluetoothAndStartBluetoothMonitoring();
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    public void unbindService() {
//        if (isServiceBound()) {
//            getEventBus().send(UnbindServiceEvent.build(this).serviceConnection(bleServiceConnection));
//        }
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    public void onDestroy() {
//        getRemoteStartSubscription().clear();
//        getLocksAssetManager().setOnMoveLanding(true);
//        pollIntervalDisposable.dispose();
//        bluetoothStateDisposable.dispose();
//    }
//
//    @Override
//    public void navigateUp() {
//        getEventBus().send(FinishActivityEvent.build(this).finishActivityEvent());
//    }
//
//
//    @Override
//    public void showLottieInitiate(boolean shouldShowAnimation, int viewId) {
//        if (bluetoothButtonsClickable.get()) {
//            switch (viewId) {
//                case com.fordmps.mobileapp.R.id.vehicle_control_windows_up_image:
//                    setAnimationState(shouldShowAnimation, VehicleCommand.PAAK_WINDOWS_UP);
//                    break;
//                case com.fordmps.mobileapp.R.id.vehicle_control_windows_down_image:
//                    setAnimationState(shouldShowAnimation, VehicleCommand.PAAK_WINDOWS_DOWN);
//                    break;
//                case com.fordmps.mobileapp.R.id.vehicle_control_open_close_trunk_image:
//                    setAnimationState(shouldShowAnimation, VehicleCommand.PAAK_OPEN_CLOSE_TRUNK);
//                    break;
//                case com.fordmps.mobileapp.R.id.vehicle_control_frunk_image:
//                    setAnimationState(shouldShowAnimation, VehicleCommand.PAAK_UNLOCK_FRUNK);
//                    break;
//                case com.fordmps.mobileapp.R.id.vehicle_control_lock_chirp_image:
//                    setAnimationState(shouldShowAnimation, VehicleCommand.PAAK_LOCK_AND_CHIRP);
//                    break;
//                case com.fordmps.mobileapp.R.id.vehicle_control_panic_image:
//                    setAnimationState(shouldShowAnimation,
//                            panicIsOn ? VehicleCommand.PAAK_PANIC_OFF : VehicleCommand.PAAK_PANIC_ON);
//                    break;
//                default:
//                    super.showLottieInitiate(shouldShowAnimation, viewId);
//            }
//        } else {
//            super.showLottieInitiate(shouldShowAnimation, viewId);
//        }
//    }
//
//    @Override
//    public void checkVehicleState(@NotNull VehicleInfo vehicleInfo) {
//        if (isBluetoothConnectionEstablished.get()) {
//            resetAllAlertBannerVisibilityToFalse();
//            updateAlertBannerDisplay();
//        } else {
//            super.checkVehicleState(vehicleInfo);
//        }
//    }
//
//    @Override
//    public void setVehicleControlsForPaak() {
//        subscribeOnLifecycle(getLocksAssetManager().getPaakButtonsObservable()
//                .distinct()
//                .subscribe(showPaakButtons -> {
//                    showPaakNavigationIcon.set(showPaakButtons);
//                }));
//    }
//
//    @Override
//    public void onActivityResult(ActivityResult results) {
//        if (results.getRequestCode() == BLUETOOTH_REQUEST_CODE && results.getResultCode() == Activity.RESULT_CANCELED) {
//            shouldShowBluetoothPrompt = false;
//        } else {
//            startBluetoothMonitoring();
//        }
//        getRemoteStartSubscription().clear();
//        updateRemoteStartCountDownManager();
//    }
//
//    @Override
//    public void showPressAndHoldWarningBanner() {
//        super.showPressAndHoldWarningBanner();
//        bannerIcon.set(null);
//    }
//
//    @Override
//    public void shouldShowBluetoothPressAndHoldWarningBanner() {
//        if (bluetoothButtonsClickable.get()) {
//            super.showPressAndHoldWarningBanner();
//            bannerIcon.set(null);
//        }
//    }
//
//    void setVehicleVisible() {
//        setVehicleVisible(true);
//        updateRemoteStartCountDownManager();
//    }
//
//    void setVehicleNotVisible() {
//        setVehicleVisible(false);
//        getCcsBannerVisibility().set(false);
//        getShowEcallBanner().set(false);
//    }
//
//    //TODO remove when we convert to kotlin
//    @NotNull
//    @Override
//    public LocksAssetManager getLocksAssetManager() {
//        return super.getLocksAssetManager();
//    }
//
//    @Override
//    protected void processVehicleCommandSuccess(@NotNull @VehicleCommand String command) {
//        super.processVehicleCommandSuccess(command);
//        bannerIcon.set(getResourceProvider().getDrawable(R.drawable.ic_success_banner));
//        if (isPaakConnected()) {
//            updateBluetoothEdgeCases(command);
//        }
//    }
//
//    private void updateBluetoothEdgeCases(@VehicleCommand @NotNull String command) {
//        switch (command) {
//            case VehicleCommand.STOP:
//                pollIntervalDisposable.dispose();
//                getRemoteStartSubscription().clear();
//                break;
//            case VehicleCommand.PAAK_PANIC_ON:
//            case VehicleCommand.PAAK_PANIC_OFF:
//                panicIsOn = !panicIsOn;
//                break;
//        }
//    }
//
//    @Override
//    protected void updateRemoteStartCountDownManager() {
//        if (isPaakConnected()) {
//            initiateBluetoothPollingForVehicleStatus();
//        } else {
//            super.updateRemoteStartCountDownManager();
//        }
//    }
//
//    private boolean isPaakConnected() {
//        return isBluetoothConnectionEstablished != null && isKeyDeliveredToVehicle != null && isBluetoothConnectionEstablished.get() && isKeyDeliveredToVehicle.get();
//    }
//
//    private void handleBluetoothExtendStartBanner() {
//        if (EngineState.STARTED.equals(getEngineState())) {
//            getShowCloseWidget().set(false);
//            getExtendStartValue().set(getResourceProvider().getString(R.string.move_paak_paak_controls_remote_start_extend));
//            getBannerText().set(getResourceProvider().getString(R.string.move_garage_vehicle_start_success_banner));
//            getBannerColor().set(R.color.secondary_background);
//            bannerIcon.set(getResourceProvider().getDrawable(R.drawable.ic_success_banner));
//            showExtendStartIfCapable();
//            getShowBanner().set(true);
//            getEngineControlButton().setState(CommandButton.State.SUCCESS);
//            getRemoteStartSubscription().clear();
//        } else {
//            hideExtendStartBanner();
//        }
//    }
//
//    @Override
//    protected void processVehicleCommandError(@NotNull @VehicleCommand String command, @NotNull Throwable e) {
//        super.processVehicleCommandError(command, e);
//        bannerIcon.set(getResourceProvider().getDrawable(R.drawable.ic_error_red_oval));
//        if (VehicleCommand.EXTEND_START.equals(command) && isPaakConnected()) {
//            initiateBluetoothPollingForVehicleStatus();
//        }
//    }
//
//    @Override
//    public void onMoreClicked() {
//        if (isControlClickable().get()) {
//            startAppropriatePaakActivity();
//        }
//    }
//
//    @Override
//    public void onHelpButtonClicked() {
//        getEventBus().send(StartActivityEvent.build(this).activityName(PaakHelpActivity.class));
//    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        if (hasFocus) {
//            if (bleConnectivityManager.isBluetoothEnabled() && isForegroundServiceRunning) {
//                startBluetoothMonitoring();
//            } else {
//                resetBluetoothStates(false, R.string.move_paak_key_controls_not_connected);
//                setupBluetoothAndStartBluetoothMonitoring();
//            }
//        }
//    }
//
//    public void navigateToManageKeys() {
//        getSharedPrefsUtil().setCurrentVehicleVin(getVehicleInfo().getVin());
//        getEventBus().send(StartActivityEvent.build(this).activityName(PaakKeyListActivity.class));
//    }
//
//    final FordDialogListener smcDialogListener = new FordDialogListener() {
//        @Override
//        public void onButtonClickedAtIndex(int index) {
//            if (index == 0) {
//                getEventBus().send(StartActivityEvent.build(PaakVehicleControlsViewModel.this).activityName(PaakKeySetupActivity.class));
//            } else {
//                getEventBus().send(FinishActivityEvent.build(PaakVehicleControlsViewModel.this).finishActivityEvent());
//            }
//        }
//    };
//
//    private void startBeaconIfThereIsAnActiveKey() {
//        subscribeOnLifecycle(paakAdapter.hasActiveKeysForVin(getVehicleInfo().getVin())
//                .filter(hasKey -> {
//                    isKeyDeliveredToVehicle.set(hasKey);
//                    return hasKey;
//                })
//                .subscribe(hasKey -> startBeaconListening(), e -> Log.d("HasActiveKeysForVin", e.getLocalizedMessage())));
//    }
//
//    public void startBluetoothMonitoring() {
//        bluetoothStateDisposable = paakAdapter.hasActiveKeysForVin(getVehicleInfo().getVin())
//                .filter(hasActiveKey -> {
//                    isKeyDeliveredToVehicle.set(hasActiveKey);
//                    return hasActiveKey;
//                })
//                .flatMapObservable(a -> paakAdapter.isForeGroundServiceRunning())
//                .take(1)
//                .flatMap(foregroundRunning -> {
//                    isForegroundServiceRunning = foregroundRunning;
//                    if (!foregroundRunning) {
//                        getEventBus().send(StartServiceEvent.build(this).action(ACTION_AUTH_SERVER).serviceClass(BleService.class));
//                    }
//                    return paakAdapter.connectionObservable();
//                })
//                .doOnNext(this::updatePaakConnectionStatus)
//                .filter(bleConnection -> {
//                    if (bleConnection == BleEvents.Connection.STATE_CONNECTED) {
//                        return true;
//                    }
//                    updateKeyIcon();
//                    return false;
//                })
//                .subscribe(hasKey -> updateKeyIcon(), e -> Log.d("startBluetoothMonitor", e.getLocalizedMessage()));
//    }
//
//    private void updatePaakConnectionStatus(BleEvents.Connection bleConnection) {
//        switch (bleConnection) {
//            case STATE_CONNECTED:
//                isBluetoothConnectionEstablished.set(true);
//                resetBluetoothStates(true, R.string.move_vehicle_controls_paak_bt_status_connected);
//                updateRemoteStartCountDownManager();
//                checkVehicleState(getVehicleInfo());
//                showSpinnerAnimation.set(false);
//                break;
//
//            case STATE_DISCONNECTED:
//                resetBluetoothStates(false, R.string.move_paak_key_controls_not_connected);
//                isBluetoothConnectionEstablished.set(false);
//                showSpinnerAnimation.set(false);
//                pollIntervalDisposable.dispose();
//                updateRemoteStartCountDownManager();
//                break;
//
//            default:
//                resetBluetoothStates(false, R.string.move_vehicle_controls_paak_bt_status_disconnected);
//                isBluetoothConnectionEstablished.set(false);
//                showSpinnerAnimation.set(true);
//                pollIntervalDisposable.dispose();
//        }
//        updateKeyIcon();
//    }
//
//    private void resetBluetoothStates(boolean isBluetoothConnectionEnabled, int connectionStatus) {
//        isBluetoothConnectionEstablished.set(isBluetoothConnectionEnabled);
//        bluetoothConnectionStatus.set(connectionStatus);
//    }
//
//    @Override
//    protected void submitVehicleCommand(@NotNull @VehicleCommand String command) {
//        if (bluetoothButtonsClickable.get()) {
//            isCommandInProgress().set(true);
//            setAnimationState(command, AnimationState.TRANSIENT);
//            submitBluetoothVehicleCommand(vehicleToActiveCommandMap.get(command), command, command.equals(VehicleCommand.PAAK_LOCK_AND_CHIRP));
//            getMoveAnalyticsManager().trackOperateActionWithVin(MOVE_VEHICLE_PAAK, MOVE_VEHICLE_CTA_PAAK, vehicleToActiveCommandMap.get(command).getTitle(), getVehicleInfo().getVin());
//        } else {
//            super.submitVehicleCommand(command);
//        }
//    }
//
//    private void submitBluetoothVehicleCommand(ActiveCommands command, @VehicleCommand String vehicleCommand, boolean shouldRepeatCommand) {
//        subscribeOnLifecycle(paakAdapter.getActiveCommandsCompletable(command)
//                .doOnTerminate(() -> {
//                    stopCommandButtonAnimation();
//                    isCommandInProgress().set(false);
//                })
//                .subscribe(() -> {
//                    if (shouldRepeatCommand) {
//                        submitBluetoothVehicleCommand(command, vehicleCommand, false);
//                    } else {
//                        if (command == ActiveCommands.REMOTE_START)
//                            setEngineState(EngineState.STARTED);
//                        else if (command == ActiveCommands.REMOTE_STOP)
//                            setEngineState(EngineState.STOPPED);
//                        processVehicleCommandSuccess(vehicleCommand);
//                    }
//                }, e -> {
//                    processVehicleCommandError(vehicleCommand, e);
//                    e.printStackTrace();
//                }));
//
//        subscribeOnLifecycle(bleServiceConnection.getServiceConnectedCompletable()
//                .subscribe(() -> paakAdapter.sendActiveCommand(command, bleServiceConnection.getService()),
//                        Throwable::printStackTrace));
//
//        if (!isServiceBound()) {
//            getEventBus().send(BindServiceEvent.build(this).serviceClass(BleService.class).serviceConnection(bleServiceConnection));
//        }
//    }
//
//    private void updateKeyIcon() {
//        getKeyIconDrawable().set(getResourceProvider().getDrawable(isPaakConnected() ? R.drawable.ic_green_key_icon : R.drawable.ic_paak_control_icon));
//    }
//
//    private void initiateBluetoothPollingForVehicleStatus() {
//        subscribeOnLifecycle(paakAdapter.vehicleStatusObservable().subscribe(vehicleStatus -> {
//            if (BleEvents.VehicleStatus.REMOTE_START_INACTIVE.equals(vehicleStatus)) {
//                pollIntervalDisposable.dispose();
//                setEngineState(EngineState.STOPPED);
//                hideExtendStartBanner();
//            } else if (BleEvents.VehicleStatus.REMOTE_START_ACTIVE.equals(vehicleStatus)) {
//                setEngineState(EngineState.STARTED);
//            }
//            handleBluetoothExtendStartBanner();
//        }, Throwable::printStackTrace));
//        if (isKeyDeliveredToVehicle.get() && pollIntervalDisposable.isDisposed()) {
//            pollIntervalDisposable = getDelayActionUtil().repeatAfterDelayWithInitialValue(0, BLUETOOTH_POLL_INTERVAL, TimeUnit.SECONDS)
//                    .filter(aLong -> isBluetoothConnectionEstablished.get())
//                    .subscribe(aLong -> paakAdapter.sendVehicleStatusRequest(ActiveCommands.REMOTE_START, bleServiceConnection.getService()),
//                            Throwable::printStackTrace);
//        }
//        if (!isServiceBound()) {
//            getEventBus().send(BindServiceEvent.build(this).serviceClass(BleService.class).serviceConnection(bleServiceConnection));
//        }
//    }
//
//    private void setupBluetoothAndStartBluetoothMonitoring() {
//        if (bleConnectivityManager.isBluetoothEnabled()) {
//            startBluetoothMonitoring();
//        } else {
//            askToTurnOnBluetooth();
//        }
//    }
//
//    private boolean isServiceBound() {
//        return bleServiceConnection.getService() != null;
//    }
//
//    private void checkVehicleStatus() {
//        subscribeOnLifecycle(getVehicleInfoProvider().getVehicleStatus(getVehicleInfo(), CacheTransformerProvider.Policy.NETWORK_ONLY)
//                .subscribe(this::checkVehicleState, Throwable::printStackTrace));
//    }
//
//    private void askToTurnOnBluetooth() {
//        if (shouldShowBluetoothPrompt) {
//            getEventBus().send(StartActivityEvent.build(this)
//                    .setIntent(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE))
//                    .setRequestCode(BLUETOOTH_REQUEST_CODE)
//                    .setStartActivityForResult(true));
//        }
//    }
//
//    private void startAppropriatePaakActivity() {
//        getTransientDataProvider().save(new PaakVehicleInfoUseCase(getVehicleInfo(), getEngineState()));
//        subscribeOnLifecycle(paakAdapter.hasActiveKeysForVin(getVehicleInfo().getVin()).subscribe(hasKey -> {
//            isKeyDeliveredToVehicle.set(hasKey);
//            if (hasKey) {
//                navigateToPaakControls();
//            } else {
//                getEventBus().send(StartActivityEvent.build(this).activityName(PaakKeySetupActivity.class));
//            }
//        }, e -> Log.d("hasActiveKeysForVin", e.getLocalizedMessage())));
//    }
//
//    private void navigateToPaakControls() {
//        String brandAndModelYear = getVehicleInfo().getModelYear() + " " + getResourceProvider().getString(R.string.common_environment_brand);
//        String nickName = getVehicleInfo().getNickname().isPresent() ? getVehicleInfo().getNickname().get() : "";
//        String vehicleName = TextUtils.isBlank(nickName) ? brandAndModelYear : nickName;
//        getTransientDataProvider().save(new PaakVehicleControlUseCase(vehicleName));
//        getEventBus().send(StartActivityEvent.build(this).activityName(PaakVehicleControlsActivity.class));
//    }
//
//    private void checkForLimitedFunctionality() {
//        isFunctionalityLimited.set(!paakAdapter.isCalibrationDataAvailable());
//    }
//
//    private void startBeaconListening() {
//        subscribeOnLifecycle(paakAdapter.startBeaconListening(getVehicleInfo().getVin()).subscribe(() -> {
//        }, Throwable::printStackTrace));
//    }
//
//    private void checkForSmcUpdates() {
//        subscribeOnLifecycle(paakAdapter.getServiceMessageCenterUpdates(preferenceManager.getMuid(), preferenceManager.getLastServiceMessageId())
//                .subscribe(this::onSmcSuccess, Throwable::printStackTrace));
//    }
//
//    private void onSmcSuccess() {
//        List<Pair<Integer, String>> buttons = Arrays.asList(Pair.create((R.string.move_paak_setup_paak_body4), PRIMARY),
//                Pair.create((R.string.move_paak_smc_revoke_exit), SECONDARY));
//        FordDialogEvent fordDialogEvent = FordDialogEvent.build(this)
//                .dialogBody(getResourceProvider().getString(R.string.move_paak_smc_revoke_body))
//                .dialogTitle(getResourceProvider().getString(R.string.move_paak_smc_revoke_header))
//                .buttonListWithType(buttons)
//                .listener(smcDialogListener);
//        getEventBus().send(fordDialogEvent);
//    }
//
//}
