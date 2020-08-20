package com.fordmps.mobileapp.move;

//@Singleton
//public class VehicleControlManager implements VehicleControlManagerInterface {
//
//    private final VcsRepository vcsRepository;
//
//    private final VehicleCapabilitiesRepository vehicleCapabilitiesRepository;
//
//    @Inject
//    public VehicleControlManager(VcsRepository vcsRepository, VehicleCapabilitiesRepository vehicleCapabilitiesRepository) {
//        this.vcsRepository = vcsRepository;
//        this.vehicleCapabilitiesRepository = vehicleCapabilitiesRepository;
//    }
//
//    public Observable<VehicleControlOptionsModel> getVehicleControlOptions(String vin) {
//        VehicleControlOptionsModel optionsModel = new VehicleControlOptionsModel();
//        return vcsRepository.getVehicleCapabilities(vin)
//                .doOnNext(vcsResponse -> optionsModel.setVehicleCapabilitiesResult(vcsResponse.getResult()))
//                .map(a -> optionsModel);
//    }
//
//    public Observable<VehicleControlOptionsModelXapi> getVehicleControlOptionsXapi(String vin) {
//        VehicleControlOptionsModelXapi optionsModel = new VehicleControlOptionsModelXapi();
//        return vehicleCapabilitiesRepository.getVehicleCapability(vin)
//                .filter(response -> response.getRemoteStart() != null)
//                .doOnNext(response -> optionsModel.setVehicleCapabilitiesResult(response))
//                .map(response -> optionsModel);
//    }
//}
