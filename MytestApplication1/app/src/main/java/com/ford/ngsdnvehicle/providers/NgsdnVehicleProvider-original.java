///*
// * CONFIDENTIAL FORD MOTOR COMPANY
// * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
// * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
// * Copyright 2019, Ford Motor Company.
// *
// */
//
//package com.ford.ngsdnvehicle.providers;
//
////import androidx.annotation.NonNull;
////import androidx.core.util.Pair;
////
////import com.ford.androidutils.CacheUtil;
////import com.ford.locale.ServiceLocaleProvider;
////import com.ford.networkutils.interceptors.NgsdnNetworkTransformer;
////import com.ford.networkutils.utils.StatusCodes;
////import com.ford.ngsdncommon.models.BaseNgsdnResponse;
////import com.ford.ngsdncommon.models.NgsdnException;
////import com.ford.ngsdnvehicle.models.FeaturePackageDetail;
////import com.ford.ngsdnvehicle.models.NgsdnAddVehicleRequest;
////import com.ford.ngsdnvehicle.models.NgsdnAddVehicleResponse;
////import com.ford.ngsdnvehicle.models.NgsdnPreferredDealerResponse;
////import com.ford.ngsdnvehicle.models.NgsdnRemoteScheduleListResponse;
////import com.ford.ngsdnvehicle.models.NgsdnRemoteScheduleResponse;
////import com.ford.ngsdnvehicle.models.NgsdnScheduledStart;
////import com.ford.ngsdnvehicle.models.NgsdnSyncGenerationResponse;
////import com.ford.ngsdnvehicle.models.NgsdnUpdateVehicleRequest;
////import com.ford.ngsdnvehicle.models.NgsdnVehicle;
////import com.ford.ngsdnvehicle.models.NgsdnVehicleAuthorizationStatusResponse;
////import com.ford.ngsdnvehicle.models.NgsdnVehicleCommandResponse;
////import com.ford.ngsdnvehicle.models.NgsdnVehicleDetailResponse;
////import com.ford.ngsdnvehicle.models.NgsdnVehicleOwnerManualDetailsResponse;
////import com.ford.ngsdnvehicle.models.NgsdnVehicleStatusResponse;
////import com.ford.ngsdnvehicle.models.NgsdnVehiclesResponse;
////import com.ford.ngsdnvehicle.models.VehicleCapabilityRequest;
////import com.ford.ngsdnvehicle.models.VehicleCapabilityResponse;
////import com.ford.ngsdnvehicle.models.VehicleEnrollmentsRequest;
////import com.ford.ngsdnvehicle.models.VehicleEnrollmentsResponse;
////import com.ford.ngsdnvehicle.models.trailerlightcheck.TrailerLightCheckStatusQuery;
////import com.ford.ngsdnvehicle.models.trailerlightcheck.TrailerLightCheckStatusResponse;
////import com.ford.ngsdnvehicle.models.vehiclestatus.CcsSettingsImpl;
////import com.ford.ngsdnvehicle.qualifiers.NgsdnDefaultVehicleService;
////import com.ford.ngsdnvehicle.qualifiers.NgsdnScheduledRemoteStartVehicleService;
////import com.ford.ngsdnvehicle.repositories.NavigationCapabilityRepository;
////import com.ford.ngsdnvehicle.repositories.PaakCapabilityRepository;
////import com.ford.ngsdnvehicle.repositories.VehicleDatabase;
////import com.ford.ngsdnvehicle.services.CvfmaService;
////import com.ford.ngsdnvehicle.services.NgsdnSyncGenerationService;
////import com.ford.ngsdnvehicle.services.NgsdnVehicleService;
////import com.ford.ngsdnvehicle.services.VehicleDetailsService;
////import com.ford.ngsdnvehicle.utils.NgsdnUpdateVehicleRequestBuilder;
////import com.ford.rxutils.CacheTransformerProvider;
////import com.ford.rxutils.schedulers.RxSchedulingHelper;
////import com.ford.rxutils.schedulers.Threads;
////import com.ford.utils.CacheStalenessMap;
////import com.ford.utils.TextUtils;
////import com.ford.vehiclecommon.models.VehicleAuthStatus;
////import com.ford.vehiclecommon.models.VehicleCommandConfiguration;
////import com.ford.vehiclecommon.models.VehicleDetails;
////import com.ford.vehiclecommon.models.VehicleStatus;
////import com.google.common.base.Optional;
////
////import org.jetbrains.annotations.NotNull;
////
////import java.util.List;
////import java.util.UUID;
////import java.util.concurrent.TimeUnit;
////
////import javax.inject.Inject;
//import javax.inject.Singleton;
////
////import dagger.Lazy;
////import io.reactivex.Completable;
////import io.reactivex.Maybe;
////import io.reactivex.Observable;
////import io.reactivex.Single;
////import io.reactivex.subjects.BehaviorSubject;
////import io.reactivex.subjects.PublishSubject;
////import retrofit2.Response;
////
////import static com.ford.ngsdnvehicle.repositories.PaakCapabilityRepository.PaakCapableCode.NOT_PAAK_CAPABLE_VIN;
////import static com.ford.ngsdnvehicle.repositories.PaakCapabilityRepository.PaakCapableCode.PAAK_CAPABLE_VIN;
//
//@Singleton
//public class NgsdnVehicleProvider implements NgsdnVehicleProviderInterface {
//
////    private final static long VEHICLE_DETAILS_CACHE_FRESHNESS_DURATION_MILLIS = TimeUnit.MINUTES.toMillis(5);
////    private final static String ALL_VEHICLE_CAPABILITY_CUSTOMER_TYPE = "A";
////    private final static String PAAK_FEATURE = "PAAK";
////
////    private final VehicleDatabase vehicleDatabase;
////    private final CacheUtil cacheUtil;
////    private final ServiceLocaleProvider serviceLocaleProvider;
////    private final BehaviorSubject<Pair<String, NgsdnVehicleStatusResponse>> lockStatusBehaviorSubject = BehaviorSubject.createDefault(new Pair<>(null, null));
////    private final PublishSubject<Pair<String, NgsdnVehicleStatusResponse>> wifiSettingsPublishSubject = PublishSubject.create();
////    private final RxSchedulingHelper rxSchedulingHelper;
////    private final CacheStalenessMap vehicleDetailsCacheStalenessMap;
////    private final CacheTransformerProvider cacheTransformerProvider;
////    private final @NgsdnDefaultVehicleService
////    NgsdnVehicleService ngsdnVehicleService;
////    private final @NgsdnScheduledRemoteStartVehicleService
////    NgsdnVehicleService ngsdnScheduledRemoteStartService;
////    private final Lazy<NgsdnNetworkTransformer> ngsdnNetworkTransformerProvider;
////    private final CvfmaService cvfmaService;
////    private final NavigationCapabilityRepository navigationCapabilityRepository;
////    private final VehicleDetailsService vehicleDetailsService;
////    private final NgsdnSyncGenerationService ngsdnSyncGenerationService;
////    private final PaakCapabilityRepository paakCapabilityRepository;
////    private final NgsdnUpdateVehicleRequestBuilder ngsdnUpdateVehicleRequestBuilder;
////
////    @Inject
////    public NgsdnVehicleProvider(VehicleDatabase vehicleDatabase,
////                                RxSchedulingHelper rxSchedulingHelper,
////                                CacheUtil cacheUtil,
////                                CacheStalenessMap.Factory vehicleDetailsCacheStalenessMapFactory,
////                                CacheTransformerProvider cacheTransformerProvider,
////                                @NgsdnDefaultVehicleService NgsdnVehicleService ngsdnVehicleService,
////                                @NgsdnScheduledRemoteStartVehicleService NgsdnVehicleService ngsdnScheduledRemoteStartService,
////                                Lazy<NgsdnNetworkTransformer> ngsdnNetworkTransformerProvider,
////                                ServiceLocaleProvider serviceLocaleProvider,
////                                CvfmaService cvfmaService,
////                                NavigationCapabilityRepository navigationCapabilityRepository,
////                                PaakCapabilityRepository paakCapabilityRepository,
////                                VehicleDetailsService vehicleDetailsService,
////                                NgsdnSyncGenerationService ngsdnSyncGenerationService,
////                                NgsdnUpdateVehicleRequestBuilder ngsdnUpdateVehicleRequestBuilder) {
////        this.vehicleDatabase = vehicleDatabase;
////        this.rxSchedulingHelper = rxSchedulingHelper;
////        this.cacheUtil = cacheUtil;
////        this.cacheTransformerProvider = cacheTransformerProvider;
////        this.paakCapabilityRepository = paakCapabilityRepository;
////        this.ngsdnUpdateVehicleRequestBuilder = ngsdnUpdateVehicleRequestBuilder;
////        vehicleDetailsCacheStalenessMap = vehicleDetailsCacheStalenessMapFactory.newInstance(VEHICLE_DETAILS_CACHE_FRESHNESS_DURATION_MILLIS);
////        this.ngsdnVehicleService = ngsdnVehicleService;
////        this.ngsdnScheduledRemoteStartService = ngsdnScheduledRemoteStartService;
////        this.ngsdnNetworkTransformerProvider = ngsdnNetworkTransformerProvider;
////        this.serviceLocaleProvider = serviceLocaleProvider;
////        this.cvfmaService = cvfmaService;
////        this.navigationCapabilityRepository = navigationCapabilityRepository;
////        this.vehicleDetailsService = vehicleDetailsService;
////        this.ngsdnSyncGenerationService = ngsdnSyncGenerationService;
////    }
////
////    public boolean vehicleAlreadyAdded(final String vin) {
////        return vehicleDatabase.getVehicle(vin).isPresent();
////    }
////
////    public Observable<List<NgsdnVehicle>> getVehicleListChangeObservable() {
////        return vehicleDatabase.getVehicleListUpdateObservable()
////                .compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
////    }
////
////    public Single<NgsdnVehicle> addVehicle(final String vin, final String nickname) {
////        return Single
////                .defer(() -> {
////                    if (vehicleAlreadyAdded(vin)) {
////                        return Single.error(new NgsdnException(StatusCodes.ERROR_VEHICLE_ALREADY_ADDED));
////                    } else {
////                        final NgsdnAddVehicleRequest request = new NgsdnAddVehicleRequest(nickname, vin);
////                        return postAddVehicle(request);
////                    }
////                })
////                .flatMap(ngsdnAddVehicleResponse -> {
////                    final NgsdnVehicle vehicle = ngsdnAddVehicleResponse.getVehicle();
////                    if (vehicle != null && !TextUtils.isEmpty(vehicle.getVin())) {
////                        cacheUtil.clearDashboardCache();
////                        vehicleDatabase.insertVehicle(vehicle);
////                        return Single.just(vehicle);
////                    } else {
////                        return Single.error(new NgsdnException(StatusCodes.ERROR_UNKNOWN));
////                    }
////                })
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Completable removeVehicle(final String vin) {
////        return Completable.defer(() -> {
////            if (vehicleDatabase.getVehicle(vin).isPresent()) {
////                return deleteVehicle(vin)
////                        .doOnSuccess(baseNgsdnResponse -> {
////                            vehicleDatabase.deleteVehicle(vin);
////                            navigationCapabilityRepository.deleteVehicle(vin);
////                        })
////                        .compose(rxSchedulingHelper.singleSchedulers(Threads.IO))
////                        .ignoreElement();
////            } else {
////                return Completable.error(new NgsdnException(StatusCodes.ERROR_UNKNOWN));
////            }
////        });
////    }
////
////    public Completable updateVehicle(final String vin, final VehicleUpdateParams vehicleUpdateParams) {
////        NgsdnUpdateVehicleRequest request = ngsdnUpdateVehicleRequestBuilder.buildNgsdnUpdateVehicleRequest(vin, vehicleUpdateParams);
////        return updateVehicle(vin, request)
////                .doOnSuccess(baseNgsdnResponse -> {
////                    if (vehicleUpdateParams.getNickName() != null) {
////                        vehicleDatabase.updateVehicleNickname(vin, vehicleUpdateParams.getNickName());
////                    }
////                    if (vehicleDatabase.getVehicle(vin).isPresent()) {
////                        NgsdnVehicle vehicle = vehicleDatabase.getVehicle(vin).get();
////                        if (vehicle.getVehicleDetails().isPresent()) {
////                            VehicleDetails vehicleDetails = vehicle.getVehicleDetails().get();
////                            vehicleUpdateParams.updateVehicleDetails(vehicleDetails);
////                            vehicleDatabase.upsertVehicleDetails(vin, vehicleDetails);
////                            vehicleDetailsCacheStalenessMap.invalidate(vin);
////                        }
////                    }
////                })
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO))
////                .ignoreElement();
////    }
////
////    public void updateLockEventStatus(final String vin, final NgsdnVehicleStatusResponse ngsdnVehicleStatusResponse) {
////        lockStatusBehaviorSubject.onNext(new Pair<>(vin, ngsdnVehicleStatusResponse));
////    }
////
////    public void updateWifiSettings(final String vin, final NgsdnVehicleStatusResponse ngsdnVehicleStatusResponse) {
////        wifiSettingsPublishSubject.onNext(new Pair<>(vin, ngsdnVehicleStatusResponse));
////    }
////
////    public Single<Optional<NgsdnVehicle>> fetchVehicleFromCache(final String vin) {
////        return getCachedVehicleObservable(vin);
////    }
////
////    public Single<VehicleAuthStatus> fetchVehicleAuthStatus(final String vin) {
////        return fetchVehicleUpdatedAuthStatus(vin).compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    private Single<VehicleAuthStatus> fetchVehicleUpdatedAuthStatus(final String vin) {
////        return getVehicleAuthorizationStatus(vin, "05-15-2015")
////                .map(NgsdnVehicleAuthorizationStatusResponse::getVehicleAuthStatus);
////    }
////
////    public Single<VehicleStatus> fetchVehicleStatus(final String vin) {
////        return getNetworkVehicleStatusObservable(vin)
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Single<VehicleStatus> fetchVehicleStatusV4(final String vin) {
////        return ngsdnVehicleService.fetchVehicleStatusV4(vin)
////                .map(NgsdnVehicleStatusResponse::getVehicleStatus)
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Observable<Pair<String, NgsdnVehicleStatusResponse>> getVehicleLockStatusObservable(final String vin) {
////        return lockStatusBehaviorSubject.hide()
////                .filter(pair -> vin.equals(pair.first));
////    }
////
////    public Observable<Pair<String, NgsdnVehicleStatusResponse>> getWifiSettingsDataObservable(final String vin) {
////        return wifiSettingsPublishSubject.hide()
////                .filter(pair -> vin.equals(pair.first));
////    }
////
////    public Observable<Optional<List<NgsdnVehicle>>> fetchVehiclesFromCache() {
////        return Observable.fromCallable(vehicleDatabase::getVehicles).compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
////    }
////
////    public Observable<NgsdnVehicle> fetchVehicleDetailsCacheFirst(final String vin) {
////        if (vehicleDetailsCacheStalenessMap.isUninitialized(vin)) {
////            return fetchVehicleDetailsFromNetwork(vin).toObservable();
////        } else if (vehicleDetailsCacheStalenessMap.isStale(vin)) {
////            return Observable.concat(getPresentCachedVehicleObservable(vin).toObservable(), fetchVehicleDetailsFromNetwork(vin).toObservable());
////        } else {
////            return getPresentCachedVehicleObservable(vin).toObservable();
////        }
////    }
////
////    public Single<NgsdnVehicle> fetchVehicleDetailsSingleResponse(final String vin) {
////        if (vehicleDetailsCacheStalenessMap.isStale(vin)) {
////            return fetchVehicleDetailsFromNetwork(vin)
////                    .onErrorResumeNext(throwable -> getPresentCachedVehicleObservable(vin).toSingle());
////        } else {
////            return getPresentCachedVehicleObservable(vin)
////                    .switchIfEmpty(Single.defer(() -> fetchVehicleDetailsFromNetwork(vin)));
////        }
////    }
////
////    public Observable<VehicleDetails> getVehicleDetails(CacheTransformerProvider.Policy policy, final String vin) {
////        Observable<Optional<VehicleDetails>> cacheObservable = getCachedVehicleDetailsObservable(vin).toObservable();
////        Observable<VehicleDetails> networkObservable = getVehicleDetails(vin)
////                .map(response -> {
////                    VehicleDetails vehicleDetails = response.getVehicleDetails();
////                    updateVehicleAndCache(vehicleDetails, vin);
////                    return vehicleDetails;
////                }).toObservable();
////
////        return networkObservable
////                .compose(cacheTransformerProvider.get(policy, cacheObservable, () -> vehicleDetailsCacheStalenessMap.isUninitialized(vin) || vehicleDetailsCacheStalenessMap.isStale(vin)))
////                .compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
////    }
////
////    public Single<Optional<CcsSettingsImpl>> getCcsSettings(final String vin) {
////        return ngsdnVehicleService.getCcsSettings(vin)
////                .map(ngsdnCcsSettingsResponse -> Optional.fromNullable(ngsdnCcsSettingsResponse.getCcsSettings())).compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Observable<Boolean> getVehiclePaakCapability(final String vin) {
////        return getVehiclePaakCapabilityWithoutSchedulerWithCvfma(vin)
////                .compose(rxSchedulingHelper.observableSchedulers(Threads.IO));
////    }
////
////    public Observable<Boolean> getVehiclePaakCapabilityWithoutSchedulerWithCvfma(final String vin) {
////        if (!paakCapabilityRepository.hasPaakCapableCachedValue(vin)) {
////            return getVehicleCapabilityCheck(createVehiclePaakCapabilityCheckRequest(vin))
////                    .filter(vehicleCapabilityResponseResponse -> vehicleCapabilityResponseResponse.body() != null)
////                    .map(vehicleCapabilityResponse -> new Pair<>(vin, vehicleCapabilityResponse.body()))
////                    .flatMapObservable(vehicleCapabilityResponsePair -> {
////                        savePaakCapability(vehicleCapabilityResponsePair.first, vehicleCapabilityResponsePair.second);
////                        return Observable.just(paakCapabilityRepository.isPaakCapableVin(vin));
////                    });
////        }
////        return Observable.just(paakCapabilityRepository.isPaakCapableVin(vin));
////    }
////
////    public Observable<Boolean> observeIsPaakEnabled(@NotNull final String vin, final boolean isFeatureEnabled) {
////        return !isFeatureEnabled ? Observable.just(false) : getVehiclePaakCapability(vin);
////    }
////
////    public boolean isNavCapableVin(String vin) {
////        return navigationCapabilityRepository.isNavCapableVin(vin);
////    }
////
////    public Single<Response<VehicleEnrollmentsResponse>> fetchVehicleEnrollmentDetails(final String vin) {
////        VehicleEnrollmentsRequest vehicleEnrollmentsRequest = new VehicleEnrollmentsRequest(vin, serviceLocaleProvider.getLocale().getISO3Country(),
////                UUID.randomUUID().toString());
////        return getVehicleEnrollments(vehicleEnrollmentsRequest);
////    }
////
////    public Single<NgsdnVehiclesResponse> getVehiclesList() {
////        return vehicleDetailsService.getVehiclesList("5-15-2015")
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnAddVehicleResponse> postAddVehicle(final NgsdnAddVehicleRequest addVehicleRequest) {
////        return vehicleDetailsService.postAddVehicle(addVehicleRequest)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<BaseNgsdnResponse> deleteVehicle(final String vin) {
////        return vehicleDetailsService.deleteVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<BaseNgsdnResponse> updateVehicle(String vin, NgsdnUpdateVehicleRequest updateVehicleRequest) {
////        return vehicleDetailsService.updateVehicle(vin, updateVehicleRequest)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleAuthorizationStatusResponse> getVehicleAuthorizationStatus(final String vin, final String lastRequestDate) {
////        return ngsdnVehicleService.getVehicleAuthorizationStatus(vin, lastRequestDate)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getVehicleStatus(final String vin) {
////        return ngsdnVehicleService.getVehicleStatus(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnSyncGenerationResponse> getSyncGeneration(final String vin) {
////        return ngsdnSyncGenerationService.getSyncGeneration(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnPreferredDealerResponse> getPreferredDealer(final String vin) {
////        return ngsdnVehicleService.getPreferredDealer(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleOwnerManualDetailsResponse> getOwnersManual(final String vin, final String country, final String locale) {
////        return ngsdnVehicleService.getOwnerManualDetails(vin, country, locale)
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO))
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleDetailResponse> getVehicleDetails(final String vin) {
////        return vehicleDetailsService.getVehicleDetail(vin, "05-15-2015")
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> putVehicleRefreshStatus(final String vin) {
////        return ngsdnVehicleService.putVehicleRefreshStatus(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getVehicleRefreshStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getVehicleRefreshStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<BaseNgsdnResponse> postAuthorizeVehicle(final String vin) {
////        return ngsdnVehicleService.postAuthorizeVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> putStartVehicle(final String vin) {
////        return ngsdnVehicleService.putStartVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> deleteStartVehicle(final String vin) {
////        return ngsdnVehicleService.deleteStartVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getStartVehicleStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getStartVehicleStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> lockVehicle(final String vin) {
////        return ngsdnVehicleService.lockVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> unlockVehicle(final String vin) {
////        return ngsdnVehicleService.unlockVehicle(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    //TODO: possible class level constant for duration
////    public Single<NgsdnVehicleCommandResponse> lightsAndHorn(final String vin) {
////        return ngsdnVehicleService.issueLightsAndHornCommand(vin, "5")
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> cargoUnlock(final String vin) {
////        return ngsdnVehicleService.issueCargoUnlockCommand(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> cabinUnlock(final String vin) {
////        return ngsdnVehicleService.issueCabinUnlockCommand(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> getWifiSettings(final String vin) {
////        return ngsdnVehicleService.getWifiSettings(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> activateZoneLighting(final String vin) {
////        return ngsdnVehicleService.activateZoneLighting(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> deactivateZoneLighting(final String vin) {
////        return ngsdnVehicleService.deactivateZoneLighting(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> updatePttbStatus(final String vin) {
////        return ngsdnVehicleService.updatePttbStatus(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getUpdatePttbStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getUpdatePttbStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> turnOnZone(final String vin, final int zoneType) {
////        return ngsdnVehicleService.turnOnZone(vin, zoneType)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> turnOffZone(final String vin, final int zoneType) {
////        return ngsdnVehicleService.turnOffZone(vin, zoneType)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> updateWifiSettingsConfiguration(VehicleCommandConfiguration wifiConfiguration) {
////        return ngsdnVehicleService.updateWifiConfiguration(wifiConfiguration)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformerWithSuccessCodes(true,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS,
////                        StatusCodes.TCU_FIRMWARE_UPGRADE_IN_PROGRESS_V2,
////                        StatusCodes.ERROR_CCS_SETTINGS_OFF));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getLockVehicleStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getLockVehicleStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getZoneLightingActivationRequestStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getZoneLightingRequestStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getZoneLightingOnOffRequestStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getZoneRequestStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getWifiSettingsStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getWifiSettingsStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getUpdateWifiSettingsStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getUpdateWifiSettingsStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnRemoteScheduleListResponse> getRemoteScheduledStarts(final String vin) {
////        return ngsdnScheduledRemoteStartService.getRemoteScheduledStarts(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnRemoteScheduleResponse> addRemoteScheduledStart(final String vin, final NgsdnScheduledStart requestBody) {
////        return ngsdnScheduledRemoteStartService.addRemoteScheduledStart(vin, requestBody)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnRemoteScheduleResponse> editRemoteScheduledStart(final String vin, final int scheduleId, final NgsdnScheduledStart requestBody) {
////        return ngsdnScheduledRemoteStartService.editRemoteScheduledStart(vin, scheduleId, requestBody)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<BaseNgsdnResponse> deleteRemoteScheduledStart(final String vin, final int scheduleId) {
////        return ngsdnScheduledRemoteStartService.deleteRemoteScheduledStart(vin, scheduleId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<BaseNgsdnResponse> requestVehicleAccess(String vin) {
////        return ngsdnVehicleService.requestVehicleAccess(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<Response<VehicleCapabilityResponse>> getVehicleCapabilityCheck(final VehicleCapabilityRequest vehicleCapabilityRequest) {
////        return cvfmaService.getVehicleCapabilityCheck(vehicleCapabilityRequest);
////    }
////
////    public Single<Response<VehicleEnrollmentsResponse>> getVehicleEnrollments(final VehicleEnrollmentsRequest vehicleEnrollmentsRequest) {
////        return cvfmaService.getVehicleEnrollments(vehicleEnrollmentsRequest)
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Completable deletePaakCapabilityCache(String vin) {
////        return Completable.fromAction(() -> paakCapabilityRepository.deleteVehicle(vin))
////                .compose(rxSchedulingHelper.completableSchedulers(Threads.IO));
////    }
////
////    private Single<NgsdnVehicle> fetchVehicleDetailsFromNetwork(final String vin) {
////        return getVehicleDetails(vin)
////                .map(response -> {
////                    VehicleDetails vehicleDetails = response.getVehicleDetails();
////                    updateVehicleAndCache(vehicleDetails, vin);
////
////                    return vehicleDatabase.getVehicle(vin).get();
////                })
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    @NonNull
////    private Single<VehicleStatus> getNetworkVehicleStatusObservable(final String vin) {
////        return getVehicleStatus(vin)
////                .map(NgsdnVehicleStatusResponse::getVehicleStatus)
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    @NonNull
////    private VehicleCapabilityRequest createVehiclePaakCapabilityCheckRequest(String vin) {
////        return new VehicleCapabilityRequest(vin, serviceLocaleProvider.getLocale().getISO3Country(),
////                UUID.randomUUID().toString(), ALL_VEHICLE_CAPABILITY_CUSTOMER_TYPE, PAAK_FEATURE);
////    }
////
////    private void savePaakCapability(String vin, VehicleCapabilityResponse response) {
////        String paakPackageCode = "";
////        int paakCapable = NOT_PAAK_CAPABLE_VIN;
////        if (Integer.parseInt(response.getReturnCode()) == StatusCodes.SUCCESS) {
////            if (response.getFeaturePackageDetailList() != null && !response.getFeaturePackageDetailList().isEmpty()) {
////                FeaturePackageDetail
////                        featurePackageDetail = response.getFeaturePackageDetailList().get(0);
////                if (PAAK_FEATURE.equalsIgnoreCase(featurePackageDetail.getFeatureCode())
////                        && !featurePackageDetail.getPackageCode().isEmpty()) {
////                    paakCapable = PAAK_CAPABLE_VIN;
////                    paakPackageCode = featurePackageDetail.getPackageCode();
////                }
////            }
////        }
////        paakCapabilityRepository.saveVehiclePaakCapability(vin, paakCapable, paakPackageCode);
////    }
////
////    private void updateVehicleAndCache(VehicleDetails vehicleDetails, String vin) {
////        vehicleDatabase.upsertVehicleDetails(vin, vehicleDetails);
////        vehicleDetailsCacheStalenessMap.update(vin);
////    }
////
////    private Maybe<NgsdnVehicle> getPresentCachedVehicleObservable(final String vin) {
////        return getCachedVehicleObservable(vin)
////                .filter(Optional::isPresent)
////                .map(Optional::get);
////    }
////
////    @NonNull
////    private Single<Optional<NgsdnVehicle>> getCachedVehicleObservable(final String vin) {
////        return Single.fromCallable(() -> vehicleDatabase.getVehicle(vin))
////                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    @NonNull
////    private Single<Optional<VehicleDetails>> getCachedVehicleDetailsObservable(final String vin) {
////        return Single.fromCallable(() -> Optional.<VehicleDetails>fromNullable(vehicleDatabase.getVehicleDetails(vin))).compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> startTrailerLightCheck(final String vin) {
////        return ngsdnVehicleService.startTrailerLightCheck(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleCommandResponse> stopTrailerLightCheck(final String vin) {
////        return ngsdnVehicleService.stopTrailerLightCheck(vin)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<NgsdnVehicleStatusResponse> getTrailerLightCheckCommandStatus(final String vin, final String commandId) {
////        return ngsdnVehicleService.getTrailerLightCheckCommandStatus(vin, commandId)
////                .compose(ngsdnNetworkTransformerProvider.get().getSingleNetworkErrorReauthTransformer(true));
////    }
////
////    public Single<TrailerLightCheckStatusResponse> getTrailerLightCheckStatus(final String vin, final TrailerLightCheckStatusQuery trailerLightCheckStatusQuery) {
////        return ngsdnVehicleService.getTrailerLightCheckStatus(vin, trailerLightCheckStatusQuery);
////    }
////
////    public static class VehicleUpdateParams {
////        private String nickName;
////        private String preferredDealer;
////        private String licensePlate;
////        private Integer odometer;
////
////        public String getNickName() {
////            return nickName;
////        }
////
////        public void setNickName(String nickName) {
////            this.nickName = nickName;
////        }
////
////        public String getPreferredDealer() {
////            return preferredDealer;
////        }
////
////        public void setPreferredDealer(String preferredDealer) {
////            this.preferredDealer = preferredDealer;
////        }
////
////        public String getLicensePlate() {
////            return licensePlate;
////        }
////
////        public void setLicensePlate(String licensePlate) {
////            this.licensePlate = licensePlate;
////        }
////
////        public Integer getOdometer() {
////            return odometer;
////        }
////
////        public void setOdometer(Integer odometer) {
////            this.odometer = odometer;
////        }
////
////        private void updateVehicleDetails(VehicleDetails vehicleDetails) {
////            if (getPreferredDealer() != null) {
////                vehicleDetails.setPreferredDealer(getPreferredDealer());
////            }
////            if (getLicensePlate() != null) {
////                vehicleDetails.setLicensePlate(getLicensePlate());
////            }
////            if (getOdometer() != null) {
////                vehicleDetails.setOdometer(getOdometer());
////            }
////        }
////
////        @Override
////        public boolean equals(Object o) {
////            if (this == o) return true;
////            if (o == null || getClass() != o.getClass()) return false;
////
////            VehicleUpdateParams that = (VehicleUpdateParams) o;
////
////            if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null)
////                return false;
////            if (preferredDealer != null ? !preferredDealer.equals(that.preferredDealer) : that.preferredDealer != null)
////                return false;
////            if (licensePlate != null ? !licensePlate.equals(that.licensePlate) : that.licensePlate != null)
////                return false;
////            if (odometer != null ? !odometer.equals(that.odometer) : that.odometer != null)
////                return false;
////
////            return true;
////        }
////
////        @Override
////        public int hashCode() {
////            int result = nickName != null ? nickName.hashCode() : 0;
////            result = 31 * result + (preferredDealer != null ? preferredDealer.hashCode() : 0);
////            result = 31 * result + (licensePlate != null ? licensePlate.hashCode() : 0);
////            result = 31 * result + (odometer != null ? odometer.hashCode() : 0);
////            return result;
////        }
////    }
//}
