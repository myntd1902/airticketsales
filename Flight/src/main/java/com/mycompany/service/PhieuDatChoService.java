/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.PhieuDatCho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */

public class PhieuDatChoService {
    private Connection conn;

    public PhieuDatChoService(Connection conn) {
        this.conn = conn;
    }
    
    public PhieuDatCho getPhieuDatChoByMaVe(int maVe) throws SQLException {
        String sql = "SELECT * FROM phieudatcho WHERE maVe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maVe);
        ResultSet rs = stm.executeQuery();
        PhieuDatCho pdc = null;
        while (rs.next()) {
            pdc = new PhieuDatCho();
            pdc.setMaPhieu(rs.getInt("maPhieu"));
            pdc.setMaVe(rs.getInt("maVe"));
            pdc.setTenKH(rs.getString("tenKH"));
            pdc.setNgayDatVe(rs.getString("ngayDatVe"));
        }
        return pdc;
    }
    
    public boolean addPhieuDatCho(PhieuDatCho pdc) throws SQLException {
        String sql = "INSERT INTO phieudatcho(maVe, tenKH, ngayDatVe) VALUES(?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, pdc.getMaVe());
        stm.setString(2, pdc.getTenKH());
        stm.setString(3, pdc.getNgayDatVe());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean delelePhieuDatCho(int maVe) throws SQLException {
        String sql = "DELETE FROM phieudatcho WHERE maVe=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maVe);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
}
