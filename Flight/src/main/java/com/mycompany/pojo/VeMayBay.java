/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author ADmin
 */
public class VeMayBay {
    private int maVe;
    private String hangVe;
    private BigDecimal giaVe;
    private String maGhe;
    private String ngayXuatVe;
    private String tenNguoiDat;
    private String tenKH;
    private String maCB;
    private String trangThai;

    
    @Override
    public String toString() {
        return Integer.toString(this.getMaVe());
    }

    /**
     * @return the maVe
     */
    public int getMaVe() {
        return maVe;
    }

    /**
     * @param maVe the maVe to set
     */
    public void setMaVe(int maVe) {
        this.maVe = maVe;
    }

    /**
     * @return the hangVe
     */
    public String getHangVe() {
        return hangVe;
    }

    /**
     * @param hangVe the hangVe to set
     */
    public void setHangVe(String hangVe) {
        this.hangVe = hangVe;
    }

    /**
     * @return the giaVe
     */
    public BigDecimal getGiaVe() {
        return giaVe;
    }

    /**
     * @param giaVe the giaVe to set
     */
    public void setGiaVe(BigDecimal giaVe) {
        this.giaVe = giaVe;
    }

    /**
     * @return the maGhe
     */
    public String getMaGhe() {
        return maGhe;
    }

    /**
     * @param maGhe the maGhe to set
     */
    public void setMaGhe(String maGhe) {
        this.maGhe = maGhe;
    }

    /**
     * @return the ngayXuatVe
     */
    public String getNgayXuatVe() {
        return ngayXuatVe;
    }

    /**
     * @param ngayXuatVe the ngayXuatVe to set
     */
    public void setNgayXuatVe(String ngayXuatVe) {
        this.ngayXuatVe = ngayXuatVe;
    }

    /**
     * @return the tenNguoiDat
     */
    public String getTenNguoiDat() {
        return tenNguoiDat;
    }

    /**
     * @param tenNguoiDat the tenNguoiDat to set
     */
    public void setTenNguoiDat(String tenNguoiDat) {
        this.tenNguoiDat = tenNguoiDat;
    }

    /**
     * @return the tenKH
     */
    public String getTenKH() {
        return tenKH;
    }

    /**
     * @param tenKH the tenKH to set
     */
    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    /**
     * @return the maCB
     */
    public String getMaCB() {
        return maCB;
    }

    /**
     * @param maCB the maCB to set
     */
    public void setMaCB(String maCB) {
        this.maCB = maCB;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
}
