package com.gosun.isap.system.config.api;


public class FileSystemData {
    private String total;
    private String free;
    private String used;
    private String percent;

    public String getTotal() {
        return total;
    }

    public String getFree() {
        return free;
    }

    public String getUsed() {
        return used;
    }

    public String getPercent() {
        return percent;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
