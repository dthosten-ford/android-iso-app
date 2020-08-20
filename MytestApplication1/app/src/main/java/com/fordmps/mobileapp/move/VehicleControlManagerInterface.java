package com.fordmps.mobileapp.move;

import io.reactivex.Observable;

public interface VehicleControlManagerInterface {
    Observable<VehicleControlOptionsModelXapi> getVehicleControlOptionsXapi(String vin);

    Observable<VehicleControlOptionsModel> getVehicleControlOptions(String vin);
}
