/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author Admin
 */
public class SanBay {
    private String maSanBay;
    private String tenSanBay;
    private String diaDiem;
    private String quocGia;
    private Boolean trangThai;

    @Override
    public String toString() {
        return this.getTenSanBay();
    }
    
    /**
     * @return the maSanBay
     */
    public String getMaSanBay() {
        return maSanBay;
    }

    /**
     * @param maSanBay the maSanBay to set
     */
    public void setMaSanBay(String maSanBay) {
        this.maSanBay = maSanBay;
    }

    /**
     * @return the tenSanBay
     */
    public String getTenSanBay() {
        return tenSanBay;
    }

    /**
     * @param tenSanBay the tenSanBay to set
     */
    public void setTenSanBay(String tenSanBay) {
        this.tenSanBay = tenSanBay;
    }

    /**
     * @return the quocGia
     */
    public String getQuocGia() {
        return quocGia;
    }

    /**
     * @param quocGia the quocGia to set
     */
    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    /**
     * @return the trangThai
     */
    public Boolean getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the diaDiem
     */
    public String getDiaDiem() {
        return diaDiem;
    }

    /**
     * @param diaDiem the diaDiem to set
     */
    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
