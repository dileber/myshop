package com.drcosu.myshop.data.model;

import com.drcosu.ndileber.mvp.data.model.SModel;

import java.math.BigDecimal;

public class MonoModel  extends SModel {
    private Integer monoid;

    private String mononame;

    private String monodetail;

    private Integer monostatus;

    private BigDecimal monomoney;

    private String monopic;

    public Integer getMonoid() {
        return monoid;
    }

    public void setMonoid(Integer monoid) {
        this.monoid = monoid;
    }

    public String getMononame() {
        return mononame;
    }

    public void setMononame(String mononame) {
        this.mononame = mononame == null ? null : mononame.trim();
    }

    public String getMonodetail() {
        return monodetail;
    }

    public void setMonodetail(String monodetail) {
        this.monodetail = monodetail == null ? null : monodetail.trim();
    }

    public Integer getMonostatus() {
        return monostatus;
    }

    public void setMonostatus(Integer monostatus) {
        this.monostatus = monostatus;
    }

    public BigDecimal getMonomoney() {
        return monomoney;
    }

    public void setMonomoney(BigDecimal monomoney) {
        this.monomoney = monomoney;
    }

    public String getMonopic() {
        return monopic;
    }

    public void setMonopic(String monopic) {
        this.monopic = monopic == null ? null : monopic.trim();
    }
}