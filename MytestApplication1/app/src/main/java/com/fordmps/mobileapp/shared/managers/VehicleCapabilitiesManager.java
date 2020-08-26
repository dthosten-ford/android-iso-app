package com.fordmps.mobileapp.shared.managers;

import com.ford.vcs.models.Feature;
import com.ford.vcs.models.VehicleCapabilitiesResponse;
import com.fordmps.data.enums.SdnType;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface VehicleCapabilitiesManager {
    Observable<String> getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<VehicleCapabilitiesResponse> getVehicleCapabilities();

    boolean isValidVcsResponse(VehicleCapabilitiesResponse o);

    Single<SdnType> getVehicleSdnType(String vin);

    boolean getFeatureEligibility(List<Feature> vehicleCapabilitiesResponse, int userReset);
}
