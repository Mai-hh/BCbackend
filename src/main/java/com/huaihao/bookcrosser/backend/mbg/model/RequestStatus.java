package com.huaihao.bookcrosser.backend.mbg.model;

public enum RequestStatus {
    REQUESTING("requesting"),
    DRIFTING("drifting"),
    RECEIVED("received"),
    UNCOMMENTED("uncommented"),
    COMMENTED("commented"),
    FINISHED("finished");

    private final String statusString;

    RequestStatus(String statusString) {
        this.statusString = statusString;
    }

    public String getStatusString() {
        return statusString;
    }
}
