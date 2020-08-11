package com.fordmps.mobileapp.move;

import io.reactivex.Observable;

public interface VehicleControlManagerInterface {
    Observable<Object> getVehicleControlOptionsXapi(String vin);
}
