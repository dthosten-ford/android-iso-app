package com.fordmps.mobileapp.shared.datashare;

import com.fordmps.mobileapp.move.FindCollisionCenterVehicleInfoUseCase;
import com.fordmps.mobileapp.move.FindVehicleLocationUseCase;
import com.fordmps.mobileapp.move.GarageVehicleSelectedVinUseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.UseCase;
import com.fordmps.mobileapp.shared.datashare.usecases.VehicleStatusUseCase;

public interface TransientDataProvider {
    void save(FindCollisionCenterVehicleInfoUseCase findCollisionCenterVehicleInfoUseCase);

    void save(UseCase useCase);

    void save(FindVehicleLocationUseCase findVehicleLocationUseCase);

    void save(GarageVehicleSelectedVinUseCase garageVehicleSelectedVinUseCase);

    Object observeUseCase(Class<VehicleStatusUseCase> vehicleStatusUseCaseClass);
}
