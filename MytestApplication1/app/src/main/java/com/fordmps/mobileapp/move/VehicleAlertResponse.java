package com.fordmps.mobileapp.move;

import com.ford.vehiclehealth.models.ActiveAlert;

import java.util.Collection;

public interface VehicleAlertResponse {
    Collection<ActiveAlert> getActiveAlerts();
}
