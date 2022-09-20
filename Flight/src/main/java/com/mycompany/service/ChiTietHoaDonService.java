/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChiTietHoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonService {
    private Connection conn;

    public ChiTietHoaDonService(Connection conn) {
        this.conn = conn;
    }
    
    public boolean addHDTT(ChiTietHoaDon hdtt) throws SQLException {
        String sql = "INSERT INTO chitiethoadon (maHD, maVe, giaVe) VALUES (?, ?, ?)";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, hdtt.getMaHoaDon());
        stm.setInt(2, hdtt.getMaVe());
        stm.setBigDecimal(3, hdtt.getGiaVe());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
}
