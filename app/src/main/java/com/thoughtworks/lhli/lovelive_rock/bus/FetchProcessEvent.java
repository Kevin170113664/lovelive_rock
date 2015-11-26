package com.thoughtworks.lhli.lovelive_rock.bus;

public class FetchProcessEvent {
    public String process;

    public FetchProcessEvent(String process) {
        this.process = process;
    }

    public String getProcess() {
        return process;
    }
}
