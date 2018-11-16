package com.altimetrik.trip.models;

public class Itineraries {
    private Outbound outbound;
    private Inbound inbound;

    public Inbound getInbound() {
        return inbound;
    }

    public void setInbound(Inbound inbound) {
        this.inbound = inbound;
    }

    public Outbound getOutbound() {
        return outbound;
    }

    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }

    @Override
    public String toString() {
        return "Itineraries [outbound=" + outbound + ", inbound=" + inbound + "]";
    }
}
