package com.gosun.isap.dao.po.face;

import java.io.Serializable;

public class Groupblacklists extends GroupblacklistsKey implements Serializable {
    private Integer state;

    private Integer usedflag;

    private static final long serialVersionUID = 1L;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUsedflag() {
        return usedflag;
    }

    public void setUsedflag(Integer usedflag) {
        this.usedflag = usedflag;
    }
}