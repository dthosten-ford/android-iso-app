package com.fordmps.mobileapp.move;

public interface VehicleControlOptionsModelXapi {
    public RemoteStartState getRemoteStartState();

    boolean isPaakCapable();

    RemoteStartState getRemoteLockState();
//    ObservableSource<? extends VehicleControlOptionsModelXapi> isPaakCapable();
}
