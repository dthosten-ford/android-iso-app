package com.fordmps.mobileapp.move.managers;

import com.ford.dashboard.models.VehicleStatus;

public interface ChargingStatusUtilInterface {
    boolean shouldShowChargingStatus(VehicleStatus vehicleStatus);

    boolean shouldShowUnplugged(VehicleStatus vehicleStatus);
}
