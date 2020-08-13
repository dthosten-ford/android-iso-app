package com.fordmps.mobileapp.shared.datashare;

import com.fordmps.mobileapp.move.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.move.FindVehicleLocationUseCase;
import com.fordmps.mobileapp.move.GarageVehicleSelectedVinUseCase;

public interface TransientDataProviderInterface {
    void save(FindCollisionCenterVehicleInfoUseCase findCollisionCenterVehicleInfoUseCase);

    void save(FindVehicleLocationUseCase findVehicleLocationUseCase);

    void save(GarageVehicleSelectedVinUseCase garageVehicleSelectedVinUseCase);
}
