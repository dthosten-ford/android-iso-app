package com.fordmps.mobileapp.shared.managers;

import com.ford.vcs.models.Feature;
import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.move.VehicleCapabilitiesResponse;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface VehicleCapabilitiesManager {
    Observable<String> getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<VehicleCapabilitiesResponse> getVehicleCapabilities();

    boolean isValidVcsResponse(VehicleCapabilitiesResponse o);

    Maybe<SdnType> getVehicleSdnType(String vin);

    boolean getFeatureEligibility(List<Feature> vehicleCapabilitiesResponse, int userReset);
}
