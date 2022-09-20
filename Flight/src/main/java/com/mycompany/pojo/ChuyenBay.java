/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class ChuyenBay {
    private String maChuyenBay;
    private String soHieuMayBay;
    private String ngayGioKhoiHanh;
    private String ngayGioDen;
    private String tenSanBayDi;
    private String tenSanBayDen;
    private String sanBayTrungGian; 

    @Override
    public String toString() {
        return this.getMaChuyenBay();
    }

    /**
     * @return the maChuyenBay
     */
    public String getMaChuyenBay() {
        return maChuyenBay;
    }

    /**
     * @param maChuyenBay the maChuyenBay to set
     */
    public void setMaChuyenBay(String maChuyenBay) {
        this.maChuyenBay = maChuyenBay;
    }

    /**
     * @return the soHieuMayBay
     */
    public String getSoHieuMayBay() {
        return soHieuMayBay;
    }

    /**
     * @param soHieuMayBay the soHieuMayBay to set
     */
    public void setSoHieuMayBay(String soHieuMayBay) {
        this.soHieuMayBay = soHieuMayBay;
    }

    /**
     * @return the ngayGioKhoiHanh
     */
    public String getNgayGioKhoiHanh() {
        return ngayGioKhoiHanh;
    }

    /**
     * @param ngayGioKhoiHanh the ngayGioKhoiHanh to set
     */
    public void setNgayGioKhoiHanh(String ngayGioKhoiHanh) {
        this.ngayGioKhoiHanh = ngayGioKhoiHanh;
    }

    /**
     * @return the ngayGioDen
     */
    public String getNgayGioDen() {
        return ngayGioDen;
    }

    /**
     * @param ngayGioDen the ngayGioDen to set
     */
    public void setNgayGioDen(String ngayGioDen) {
        this.ngayGioDen = ngayGioDen;
    }

    /**
     * @return the tenSanBayDi
     */
    public String getTenSanBayDi() {
        return tenSanBayDi;
    }

    /**
     * @param tenSanBayDi the tenSanBayDi to set
     */
    public void setTenSanBayDi(String tenSanBayDi) {
        this.tenSanBayDi = tenSanBayDi;
    }

    /**
     * @return the tenSanBayDen
     */
    public String getTenSanBayDen() {
        return tenSanBayDen;
    }

    /**
     * @param tenSanBayDen the tenSanBayDen to set
     */
    public void setTenSanBayDen(String tenSanBayDen) {
        this.tenSanBayDen = tenSanBayDen;
    }

    /**
     * @return the sanBayTrungGian
     */
    public String getSanBayTrungGian() {
        return sanBayTrungGian;
    }

    /**
     * @param sanBayTrungGian the sanBayTrungGian to set
     */
    public void setSanBayTrungGian(String sanBayTrungGian) {
        this.sanBayTrungGian = sanBayTrungGian;
    }

    
}