/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChuyenBay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChuyenBayService {
    private Connection conn;

    public ChuyenBayService(Connection conn) {
        this.conn = conn;
    }
    
    public List<ChuyenBay> getChuyenBay() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM chuyenbay");
        
        
        List<ChuyenBay> chuyenbay = new ArrayList<>();
        while (r.next()) {
            ChuyenBay cb = new ChuyenBay();
            cb.setMaChuyenBay(r.getString("maChuyenBay"));
            cb.setSoHieuMayBay(r.getString("soHieuMayBay"));
            cb.setNgayGioKhoiHanh(r.getString("ngayGioKhoiHanh"));
            cb.setNgayGioDen(r.getString("ngayGioDen"));
            cb.setTenSanBayDi(r.getString("tenSanBayDi"));
            cb.setTenSanBayDen(r.getString("tenSanBayDen"));
            cb.setSanBayTrungGian(r.getString("sanBayTrungGian"));
            
            chuyenbay.add(cb);
        }
        return chuyenbay;
    }
    
    public ChuyenBay getChuyenBayByMaCB(String maCB) throws SQLException {
        String sql = "SELECT * FROM chuyenbay WHERE maChuyenBay=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, maCB);
        
        ResultSet r = stm.executeQuery();
        ChuyenBay cb = null;
        while (r.next()) {
            cb = new ChuyenBay();
            cb.setMaChuyenBay(r.getString("maChuyenBay"));
            cb.setSoHieuMayBay(r.getString("soHieuMayBay"));
            cb.setNgayGioKhoiHanh(r.getString("ngayGioKhoiHanh"));
            cb.setNgayGioDen(r.getString("ngayGioDen"));
            cb.setTenSanBayDi(r.getString("tenSanBayDi"));
            cb.setTenSanBayDen(r.getString("tenSanBayDen"));
            cb.setSanBayTrungGian(r.getString("sanBayTrungGian"));
            
        }
        
        return cb;
    }
    
    public List<ChuyenBay> getChuyenBayMCs(String tenSanBayDi, String tenSanBayDen, String ngayGioKhoiHanh) throws SQLException{
        String sql = "SELECT * FROM chuyenbay "
                + " WHERE tenSanBayDi like concat('%', ?, '%') "
                + " OR tenSanBayDen like concat('%', ?, '%') "
                + " OR ngayGioKhoiHanh like concat('%', ?, '%')";
                    
        PreparedStatement stm = this.conn.prepareStatement(sql);
        if (tenSanBayDi == "")
            stm.setString(1, null);
        else
            stm.setString(1, tenSanBayDi);
        if (tenSanBayDen == "")
            stm.setString(2, null);
        else
            stm.setString(2, tenSanBayDen);
        if (ngayGioKhoiHanh == null)
            stm.setString(3, null);
        else
            stm.setString(3, ngayGioKhoiHanh);
        ResultSet r = stm.executeQuery();
        
        List<ChuyenBay> chuyenBay = new ArrayList<>();
        while (r.next()) {
            ChuyenBay cb = new ChuyenBay();
            cb.setMaChuyenBay(r.getString("maChuyenBay"));
            cb.setSoHieuMayBay(r.getString("soHieuMayBay"));
            cb.setTenSanBayDi(r.getString("tenSanBayDi"));
            cb.setTenSanBayDen(r.getString("tenSanBayDen"));
            cb.setNgayGioKhoiHanh(r.getString("ngayGioKhoiHanh"));
            cb.setNgayGioDen(r.getString("ngayGioDen"));  
            chuyenBay.add(cb);
        }
        return chuyenBay;
    }
}
