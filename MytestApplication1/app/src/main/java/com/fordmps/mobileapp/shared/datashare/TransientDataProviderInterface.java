package com.fordmps.mobileapp.shared.datashare;

import com.fordmps.mobileapp.move.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.move.FindVehicleLocationUseCase;

public interface TransientDataProviderInterface {
    public void save(FindCollisionCenterVehicleInfoUseCase findCollisionCenterVehicleInfoUseCase);

    void save(FindVehicleLocationUseCase findVehicleLocationUseCase);
}
