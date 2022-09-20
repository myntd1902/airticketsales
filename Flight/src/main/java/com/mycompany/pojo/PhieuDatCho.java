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
public class PhieuDatCho {
    private int maPhieu;
    private int maVe;
    private String tenKH;
    private String ngayDatVe;

    /**
     * @return the maPhieu
     */
    public int getMaPhieu() {
        return maPhieu;
    }

    /**
     * @param maPhieu the maPhieu to set
     */
    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
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
     * @return the ngayDatVe
     */
    public String getNgayDatVe() {
        return ngayDatVe;
    }

    /**
     * @param ngayDatVe the ngayDatVe to set
     */
    public void setNgayDatVe(String ngayDatVe) {
        this.ngayDatVe = ngayDatVe;
    }

    
}
