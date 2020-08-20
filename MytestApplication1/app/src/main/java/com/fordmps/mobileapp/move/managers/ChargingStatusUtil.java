package com.fordmps.mobileapp.move.managers;

//import com.ford.dashboard.models.VehicleStatus;

import com.ford.vehiclecommon.models.VehicleStatus;

public interface ChargingStatusUtil {
    boolean shouldShowChargingStatus(VehicleStatus vehicleStatus);

    boolean shouldShowUnplugged(VehicleStatus vehicleStatus);

    String getChargingStatus(VehicleStatus vehicleStatus);

    int getChargingIcon(VehicleStatus vehicleStatus);
}
