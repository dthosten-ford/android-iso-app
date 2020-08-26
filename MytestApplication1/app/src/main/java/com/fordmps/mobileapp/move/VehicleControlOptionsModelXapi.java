package com.fordmps.mobileapp.move;

public interface VehicleControlOptionsModelXapi {
    public RemoteCommandState getRemoteStartState();

    boolean isPaakCapable();

    RemoteCommandState getRemoteLockState();
//    ObservableSource<? extends VehicleControlOptionsModelXapi> isPaakCapable();
}
