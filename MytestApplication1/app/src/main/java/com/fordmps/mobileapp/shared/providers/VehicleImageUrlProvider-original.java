/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.providers;

//import androidx.annotation.Nullable;
//
//import com.ford.locale.ServiceLocaleProvider;
//import com.ford.vehiclecommon.models.Vehicle;
//import com.fordmps.mobileapp.configuration.BuildConfigWrapper;
//import com.fordmps.mobileapp.shared.moduleconfigs.VehicleImageApiConfig;

//public class VehicleImageUrlProvider implements VehicleImageUrlProviderInterface {
//    private final ServiceLocaleProvider serviceLocaleProvider;
//    private VehicleImageApiConfig vehicleImageApiConfig;
//    private BuildConfigWrapper buildConfigWrapper;
//
//    public VehicleImageUrlProvider(ServiceLocaleProvider localeProvider, VehicleImageApiConfig vehicleImageApiConfig, BuildConfigWrapper buildConfigWrapper) {
//        serviceLocaleProvider = localeProvider;
//        this.vehicleImageApiConfig = vehicleImageApiConfig;
//        this.buildConfigWrapper = buildConfigWrapper;
//    }
//
//    @Nullable
//    public String generateVehicleImageUrl(Vehicle vehicle) {
//        return generateVehicleImageUrl(vehicle.getModelName(), vehicle.getModelYear(), vehicle.getVin());
//    }
//
//    @Nullable
//    public String generateVehicleImageUrl(String modelName, String modelYear, String vin) {
//        return String.format(vehicleImageApiConfig.getUrl(buildConfigWrapper), modelName, modelYear, vin, serviceLocaleProvider.getLocale().getISO3Country());
//    }
//}
