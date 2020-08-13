package com.fordmps.mobileapp.shared.configuration;

public class Configuration {

    private boolean paakEnabled = false;

    public boolean isPaakEnabled() {
        return paakEnabled;
    }

    public void setPaakEnabled(boolean paakEnabled) {
        this.paakEnabled = paakEnabled;
    }

    public boolean isDashboardXApiPhase2Enabled() {
        return false;
    }

    public boolean shouldShowFordScriptOverlay() {
        return false;
    }

    public boolean isTmcMigrationEnabled() {
        return false;
    }
}
