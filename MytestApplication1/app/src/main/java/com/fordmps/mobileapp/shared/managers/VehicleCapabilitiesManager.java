package com.fordmps.mobileapp.shared.managers;

import com.ford.vcs.models.Feature;
import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.move.VehicleCapabilitiesResponse;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface VehicleCapabilitiesManager {
    Observable<String> getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<VehicleCapabilitiesResponse> getVehicleCapabilities();

    boolean isValidVcsResponse(VehicleCapabilitiesResponse o);

    Single<SdnType> getVehicleSdnType(String vin);

    boolean getFeatureEligibility(List<Feature> vehicleCapabilitiesResponse, int userReset);
}
