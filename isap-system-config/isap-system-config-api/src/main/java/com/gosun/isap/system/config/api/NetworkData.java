package com.gosun.isap.system.config.api;


public class NetworkData {
    private String name;
    private String address;
    private String netmask;
    private String gateway;
    private String rxpackets;
    private String txpackets;
    private String rxbytes;
    private String txbytes;
    private String rxerrors;
    private String txerrors;
    private String rxdropped;
    private String txdropped;


    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNetmask() {
        return netmask;
    }

    public String getRxpackets() {
        return rxpackets;
    }

    public String getTxpackets() {
        return txpackets;
    }

    public String getRxbytes() {
        return rxbytes;
    }

    public String getTxbytes() {
        return txbytes;
    }

    public String getRxerrors() {
        return rxerrors;
    }

    public String getTxerrors() {
        return txerrors;
    }

    public String getRxdropped() {
        return rxdropped;
    }

    public String getTxdropped() {
        return txdropped;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public void setRxpackets(String rxpackets) {
        this.rxpackets = rxpackets;
    }

    public void setTxpackets(String txpackets) {
        this.txpackets = txpackets;
    }

    public void setRxbytes(String rxbytes) {
        this.rxbytes = rxbytes;
    }

    public void setTxbytes(String txbytes) {
        this.txbytes = txbytes;
    }

    public void setRxerrors(String rxerrors) {
        this.rxerrors = rxerrors;
    }

    public void setTxerrors(String txerrors) {
        this.txerrors = txerrors;
    }

    public void setRxdropped(String rxdropped) {
        this.rxdropped = rxdropped;
    }

    public void setTxdropped(String txdropped) {
        this.txdropped = txdropped;
    }
}
