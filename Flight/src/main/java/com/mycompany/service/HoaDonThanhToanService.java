/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HoaDonThanhToan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class HoaDonThanhToanService {
    private Connection conn;

    public HoaDonThanhToanService(Connection conn) {
        this.conn = conn;
    }
    
    public boolean addHDTT(HoaDonThanhToan hdtt) throws SQLException {
        String sql = "INSERT INTO hoadonthanhtoan (maHD, tenNguoiTT, tenKH,ngayTT) VALUES (?, ?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, hdtt.getMaHoaDon());
        stm.setString(2, hdtt.getTenNguoiTT());
        stm.setString(3, hdtt.getTenKH());
        stm.setString(4, hdtt.getNgayTT());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
}
