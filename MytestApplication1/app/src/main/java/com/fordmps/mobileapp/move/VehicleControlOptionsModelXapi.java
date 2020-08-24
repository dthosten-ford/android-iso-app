package com.fordmps.mobileapp.move;

public interface VehicleControlOptionsModelXapi {
    public RemoteStartState getRemoteStartState();

    boolean isPaakCapable();

    RemoteCommandState getRemoteLockState();
//    ObservableSource<? extends VehicleControlOptionsModelXapi> isPaakCapable();
}
