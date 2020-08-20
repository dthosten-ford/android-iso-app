/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.vinlookup.managers;

//import com.ford.androidutils.CacheUtil;
//import com.ford.androidutils.validators.VinValidator;
//import com.ford.msdncommon.models.MSDNError;
//import com.ford.networkutils.interceptors.NgsdnNetworkTransformer;
//import com.ford.networkutils.utils.NetworkingErrorUtil;
//import com.ford.networkutils.utils.StatusCodes;
//import com.ford.ngsdncommon.models.NgsdnException;
//import com.ford.ngsdnvehicle.models.NgsdnVehicle;
//import com.ford.ngsdnvehicle.repositories.VehicleDatabase;
//import com.ford.rxutils.schedulers.RxSchedulingHelper;
//import com.ford.rxutils.schedulers.Threads;
//import com.ford.utils.providers.LocaleProvider;
//import com.ford.vehiclecommon.models.VehicleVinType;
//import com.ford.vinlookup.models.EslAddVehicleRequest;
//import com.ford.vinlookup.models.VinDetailsLookup;
//import com.ford.vinlookup.models.VinDetailsLookupResponse;
//import com.ford.vinlookup.models.VinLookup;
//import com.ford.vinlookup.models.VinLookupError;
//import com.ford.vinlookup.models.VinLookupEslResponse;
//import com.ford.vinlookup.models.VinLookupResponse;
//import com.ford.vinlookup.repositories.VinDetailsLookupRepository;
//import com.ford.vinlookup.repositories.VinLookupRepository;
//import com.ford.vinlookup.services.VinLookupService;
//import com.google.common.base.Optional;
//
//import io.reactivex.Completable;
//import io.reactivex.Single;
//import io.reactivex.SingleSource;
//import io.reactivex.functions.Function;
//
//import static com.ford.vinlookup.models.VinLookup.DIESEL_FUEL_TYPE_VALUE;
//import static com.ford.vinlookup.models.VinLookup.ELECTRIC_FUEL_TYPE_VALUE;
//import static com.ford.vinlookup.models.VinLookup.PLUGIN_HYBRID_ELECTRIC_FUEL_TYPE_VALUE;

//public class VinLookupProvider implements VinLookupProviderInterface {
//
//    private final VinLookupService vinLookupService;
//    private final VinLookupRepository vinLookupRepository;
//    private final VinDetailsLookupRepository vinDetailsLookupRepository;
//    private final VinValidator vinValidator;
//    private final NetworkingErrorUtil networkingErrorUtil;
//    private final NgsdnNetworkTransformer ngsdnNetworkTransformer;
//    private final CacheUtil cacheUtil;
//    private final VehicleDatabase vehicleDatabase;
//    private final RxSchedulingHelper rxSchedulingHelper;
//    private final LocaleProvider localeProvider;
//
//    public VinLookupProvider(VinLookupService vinLookupService, VinLookupRepository vinLookupRepository,
//                             VinDetailsLookupRepository vinDetailsLookupRepository, NgsdnNetworkTransformer ngsdnNetworkTransformer,
//                             VinValidator vinValidator, NetworkingErrorUtil networkingErrorUtil, CacheUtil cacheUtil, VehicleDatabase vehicleDatabase,
//                             RxSchedulingHelper rxSchedulingHelper, LocaleProvider localeProvider) {
//        this.vinLookupService = vinLookupService;
//        this.vinLookupRepository = vinLookupRepository;
//        this.vinDetailsLookupRepository = vinDetailsLookupRepository;
//        this.ngsdnNetworkTransformer = ngsdnNetworkTransformer;
//        this.vinValidator = vinValidator;
//        this.networkingErrorUtil = networkingErrorUtil;
//        this.cacheUtil = cacheUtil;
//        this.vehicleDatabase = vehicleDatabase;
//        this.rxSchedulingHelper = rxSchedulingHelper;
//        this.localeProvider = localeProvider;
//    }
//
//    public Single<VinLookup> lookupVin(final String vin) {
//        VinLookup cacheValue = vinLookupRepository.getVinLookup(vin);
//        if (cacheValue != null) {
//            return Single.just(cacheValue);
//        } else if (isElectricOrHybridVin(vin)) {
//            return Single.just(new VinLookup(VehicleVinType.FORD_ELECTRIC_OR_HYBRID, "", "", null, VinLookup.PRODUCT_TYPE_CAR));
//        } else {
//            return getVinLookupObservable(vin);
//        }
//    }
//
//    public Single<Optional<String>> getTransmissionType(final String vin) {
//        return fetchVinDetails(vin)
//                .filter(vinDetailsLookupOptional -> vinDetailsLookupOptional.isPresent())
//                .map(vinDetailsLookupOptional -> Optional.fromNullable(vinDetailsLookupOptional.get().getTransmissionInd()))
//                .defaultIfEmpty(Optional.absent())
//                .toSingle();
//    }
//
//    public Single<VinDetailsLookupResponse> lookupVinDetails(String vin) {
//        return vinLookupService.lookupVinDetails(vin, localeProvider.getDeviceLocale().getLanguage().toUpperCase(), localeProvider.getAccountLocale().getISO3Country())
//                .compose(ngsdnNetworkTransformer.getSingleNetworkErrorReauthTransformer(true));
//    }
//
//    public Single<VinLookupEslResponse> lookUpEsl(String vin, String appBrand, String appRegion) {
//        if (vehicleAlreadyAdded(vin)) {
//            return Single.error(new NgsdnException(StatusCodes.ERROR_VEHICLE_ALREADY_ADDED));
//        } else {
//            EslAddVehicleRequest eslAddVehicleRequest = new EslAddVehicleRequest(vin, appBrand, appRegion);
//            return vinLookupService.lookUpEsl(eslAddVehicleRequest)
//                    .compose(ngsdnNetworkTransformer.getSingleNetworkErrorReauthTransformer(true))
//                    .flatMap(vinLookupESLResponse -> {
//                        final NgsdnVehicle vehicle = vinLookupESLResponse.getVehicle();
//                        if (vehicle != null) {
//                            cacheUtil.clearDashboardCache();
//                            vehicleDatabase.insertVehicle(vehicle);
//                            return Single.just(vinLookupESLResponse);
//                        } else {
//                            return getErrorCode(Integer.parseInt(vinLookupESLResponse.getStatus()));
//                        }
//                    });
//        }
//    }
//
//    public Single<VinLookupEslResponse> lookUpEsl(String vin, String appBrand, String appRegion, String nickname, String countryCode) {
//        if (vehicleAlreadyAdded(vin)) {
//            return Single.error(new NgsdnException(StatusCodes.ERROR_VEHICLE_ALREADY_ADDED));
//        } else {
//            EslAddVehicleRequest eslAddVehicleRequest = new EslAddVehicleRequest(vin, appBrand, appRegion, nickname, countryCode);
//            return vinLookupService.lookUpEsl(eslAddVehicleRequest)
//                    .compose(ngsdnNetworkTransformer.getSingleNetworkErrorReauthTransformer(true))
//                    .flatMap(vinLookupESLResponse -> {
//                        final NgsdnVehicle vehicle = vinLookupESLResponse.getVehicle();
//                        if (vehicle != null) {
//                            cacheUtil.clearDashboardCache();
//                            if (!vehicle.getLocalizedModelName().isPresent() && null != vinLookupESLResponse.getVinDetailsLookup() && null != vinLookupESLResponse.getVinDetailsLookup().getLocalVehicleLineDescription()) {
//                                vehicle.setLocalizedModelName(vinLookupESLResponse.getVinDetailsLookup().getLocalVehicleLineDescription());
//                            }
//                            vehicleDatabase.insertVehicle(vehicle);
//                            return Single.just(vinLookupESLResponse);
//                        } else {
//                            return getErrorCode(Integer.parseInt(vinLookupESLResponse.getStatus()));
//                        }
//                    });
//        }
//    }
//
//    public boolean isElectricOrHybridVin(final String vin) {
//        return vinValidator.isElectricOrHybridFordVinFormat(vin);
//    }
//
//    public boolean isElectricOrPhevVehicle(String vin) {
//        VinLookup cacheValue = vinLookupRepository.getVinLookup(vin);
//        if (cacheValue != null && (cacheValue.getFuelType().equals(ELECTRIC_FUEL_TYPE_VALUE) || cacheValue.getFuelType().equals(PLUGIN_HYBRID_ELECTRIC_FUEL_TYPE_VALUE))) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean isFordElectricForEurope(final String vin) {
//        return vinValidator.isElectricFordVinFormatForEurope(vin);
//    }
//
//    public Single<Boolean> isElectricOrHybridVehicle(final String vin) {
//        return lookupVin(vin).map(vinLookup -> ELECTRIC_FUEL_TYPE_VALUE.equals(vinLookup.getFuelType())
//                || PLUGIN_HYBRID_ELECTRIC_FUEL_TYPE_VALUE.equals(vinLookup.getFuelType()));
//    }
//
//    public Single<Boolean> isHybridElectricVehicle(final String vin) {
//        return lookupVin(vin).map(vinLookup -> PLUGIN_HYBRID_ELECTRIC_FUEL_TYPE_VALUE.equals(vinLookup.getFuelType()));
//    }
//
//    public Single<Boolean> isFuelTypeDiesel(final String vin) {
//        return lookupVin(vin).map(vinLookup -> vinLookup.getFuelType().equalsIgnoreCase(DIESEL_FUEL_TYPE_VALUE));
//    }
//
//    public Single<Boolean> isElectricVehicle(final String vin) {
//        return lookupVin(vin).map(vinLookup -> vinLookup.getFuelType().equalsIgnoreCase(ELECTRIC_FUEL_TYPE_VALUE));
//    }
//
//    public Single<Optional<VinDetailsLookup>> fetchVinDetails(String vin) {
//        return vinDetailsLookupRepository.getVinDetailsLookup(vin)
//                .onErrorReturnItem(Optional.absent())
//                .flatMap(vinDetailsLookupOptional -> {
//                    if (vinDetailsLookupOptional.isPresent()) {
//                        return Single.just(vinDetailsLookupOptional);
//                    }
//                    return lookupVinDetails(vin)
//                            .onErrorResumeNext(throwable -> {
//                                Optional<VinDetailsLookupResponse> vinDetailsLookupResponse = networkingErrorUtil.getResponse(throwable, VinDetailsLookupResponse.class);
//                                if (vinDetailsLookupResponse.isPresent()) {
//                                    return Single.just(vinDetailsLookupResponse.get());
//                                }
//                                return Single.error(throwable);
//                            })
//                            .filter(vinDetailsLookupResponse -> vinDetailsLookupResponse.getValue() != null)
//                            .map(vinDetailsLookupResponse -> {
//                                cacheVinDetailsLookup(vin, vinDetailsLookupResponse.getValue());
//                                return Optional.of(vinDetailsLookupResponse.getValue());
//                            })
//                            .switchIfEmpty(Single.just(Optional.absent()));
//                })
//                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO));
//    }
//
//    private void cacheVinDetailsLookup(String vin, VinDetailsLookup vinDetailsLookup) {
//        Completable.fromCallable(() -> {
//            vinDetailsLookupRepository.saveVinDetailsLookup(vin, vinDetailsLookup);
//            return Completable.complete();
//        }).compose(rxSchedulingHelper.completableSchedulers(Threads.IO)).subscribe(() -> {
//        }, Throwable::printStackTrace);
//    }
//
//    private <T> Single<T> getErrorCode(int status) {
//        return Single.error(new NgsdnException(status));
//    }
//
//    private boolean vehicleAlreadyAdded(final String vin) {
//        return vehicleDatabase.getVehicle(vin).isPresent();
//    }
//
//    private Single<VinLookup> getVinLookupObservable(final String vin) {
//        return vinLookupService.lookupVin(vin)
//                .compose(rxSchedulingHelper.singleSchedulers(Threads.IO))
//                .onErrorReturn(throwable -> {
//                    Optional<VinLookupResponse> vinLookupResponse = networkingErrorUtil.getResponse(throwable, VinLookupResponse.class);
//                    if (vinLookupResponse.isPresent()) {
//                        return vinLookupResponse.get();
//                    }
//                    return new VinLookupResponse(new MSDNError(networkingErrorUtil.getErrorStatusCode(throwable), ""));
//                })
//                .flatMap((Function<VinLookupResponse, SingleSource<VinLookup>>) vinLookupResponse -> {
//                    MSDNError error = vinLookupResponse.getError();
//                    VinLookup vinLookup = vinLookupResponse.getValue();
//                    if (error != null) {
//                        return Single.error(error);
//                    } else if (vinLookup == null) {
//                        return Single.error(new VinLookupError(StatusCodes.ERROR_UNKNOWN, null));
//                    } else if (vinLookup.getVehicleVinType() == VehicleVinType.UNKNOWN) {
//                        return Single.error(new VinLookupError(StatusCodes.ERROR_VIN_LOOKUP_UNSUPPORTED_VEHICLE, vinLookup));
//                    } else {
//                        vinLookupRepository.saveVinLookup(vin, vinLookup);
//                        return Single.just(vinLookup);
//                    }
//                });
//    }
//}
