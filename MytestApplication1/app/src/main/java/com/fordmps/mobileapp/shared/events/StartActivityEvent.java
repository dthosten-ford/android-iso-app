package com.fordmps.mobileapp.shared.events;

import com.fordmps.mobileapp.move.BaseGarageVehicleViewModel;
import com.fordmps.mobileapp.move.VehicleDetailsActivity;

public class StartActivityEvent{

    public static StartActivityEvent build(BaseGarageVehicleViewModel baseGarageVehicleViewModel) {
        return new StartActivityEvent();
    }

    public StartActivityEvent activityName(Class<VehicleDetailsActivity> vehicleDetailsActivityClass){
        return new StartActivityEvent();
    };
}
