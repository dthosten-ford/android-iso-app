/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.providers;

import com.ford.asdn.models.ASDNVehicle;
import com.ford.dashboard.models.VehicleInfo;
import com.ford.ngsdnvehicle.models.NgsdnVehicleOwnerManualDetailsResponse;
import com.ford.rxutils.CacheTransformerProvider;
import com.ford.vehiclecommon.models.Vehicle;
import com.ford.vehiclecommon.models.VehicleDetails;
import com.ford.vinlookup.models.VinDetailsLookup;
import com.ford.vinlookup.models.VinLookup;
import com.ford.vinlookup.models.VinLookupEslResponse;
import com.ford.wifihotspot.models.NgsdnImsiResponse;
import com.ford.wifihotspot.models.NgsdnWifiDataUsageResponse;
import com.google.common.base.Optional;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface VehicleInfoProvider {
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
//    Observable<VehicleInfo> getVehicleInfo(final String vin, CacheTransformerProvider.Policy cachePolicy);
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
