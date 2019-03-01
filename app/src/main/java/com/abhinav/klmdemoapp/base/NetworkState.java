package com.abhinav.klmdemoapp.base;

public class NetworkState {
    private final Status status;

    public enum Status {
        RUNNING,
        SUCCESS,
        FAILED,
        PAGING
    }

    public static NetworkState LOADED = new NetworkState(Status.SUCCESS);
    public static NetworkState FAILED = new NetworkState(Status.FAILED);
    public static NetworkState RUNNING = new NetworkState(Status.RUNNING);
    public static NetworkState PAGING = new NetworkState(Status.PAGING);


    private NetworkState(Status status){
        this.status = status;
    }
}
