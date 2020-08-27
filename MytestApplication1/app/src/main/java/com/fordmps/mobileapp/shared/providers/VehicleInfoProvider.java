/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.providers;

//import com.ford.dashboard.models.VehicleAuthStatus;

import com.ford.dashboard.models.VehicleInfo;
import com.ford.vehiclecommon.models.GarageVehicleProfile;
import com.ford.vehiclecommon.models.VehicleAuthStatus;
import com.ford.vehiclecommon.models.VehicleStatus;
import com.fordmps.mobileapp.move.CacheTransformerProvider;

import io.reactivex.Observable;

public interface VehicleInfoProvider {
    Observable<VehicleInfo> getVehicleStatus(VehicleInfo vehicleInfo, CacheTransformerProvider.Policy networkOnly);

    Observable<VehicleInfo> getVehicleAuthStatus(VehicleInfo vehicleInfo);
//    Observable<List<VehicleInfo>> getVehicles();
//
//    Observable<VehicleInfo> getVehicle(final String vin);
//

//    Observable<VehicleInfo> getVehicleStatus(final VehicleInfo vehicle, CacheTransformerProvider.Policy cachePolicy);
//
//    Observable<VehicleInfo> getVehicleAuthStatus(final VehicleInfo vehicleInfo);
//
//    Observable<VehicleDetails> getVehicleDetails(Vehicle vehicle);
//
//    Observable<VehicleDetails> getVehicleDetails(Vehicle vehicle, CacheTransformerProvider.Policy policy);
//
    Observable<VehicleInfo> getVehicleInfo(final String vin, CacheTransformerProvider.Policy cachePolicy);

    Observable<VehicleAuthStatus> getVehicleAuthStatusObject(String vin);

    Observable<VehicleStatus> getGarageVehicleStatus(GarageVehicleProfile vehicleInfo, CacheTransformerProvider.Policy networkOnly);
//
//    Single<NgsdnWifiDataUsageResponse> getWifiDataPlanAndUsage(final String vin);
//
//    Single<NgsdnImsiResponse> getImsi(final String vin);
//
//    Completable removeVehicle(final String vin);
//
//    Single<VinLookup> lookupVin(String vin);
//
//    Single<Optional<String>> getTransmissionType(final String vin);
//
//    Single<Optional<VinDetailsLookup>> fetchVinDetails(String vin);
//
//    Single<VinLookupEslResponse> lookUpEsl(String vin, String appBrand, String appRegion);
//
//    Single<VinLookupEslResponse> lookUpEsl(String vin, String appBrand, String appRegion, String nickName, String countryCode);
//
//    Observable<Optional<ASDNVehicle>> forceUpdateofVehicle(final String vin);
//
//    Single<NgsdnVehicleOwnerManualDetailsResponse> getVehicleOwnersManual(final String vin, final String country, final String locale);
}
