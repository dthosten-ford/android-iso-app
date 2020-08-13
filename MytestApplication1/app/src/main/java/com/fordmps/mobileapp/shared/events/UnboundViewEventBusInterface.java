package com.fordmps.mobileapp.shared.events;

import com.fordmps.mobileapp.move.StartActivityEvent;

public interface UnboundViewEventBusInterface {
    void send(StartActivityEvent event);
}
