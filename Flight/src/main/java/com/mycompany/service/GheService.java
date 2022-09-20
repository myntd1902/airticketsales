/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Ghe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GheService {
    private Connection conn;

    public GheService(Connection conn) {
        this.conn = conn;
    }
    
    public List<Ghe> getGhe(String soHieuMB, String hangVe) throws SQLException {
        String sql = "SELECT maybay_ghe.maGhe, hangVe"
                + " FROM maybay_ghe, ghe, chuyenbay"
                + " WHERE trangThai = false AND ghe.maGhe = maybay_ghe.maGhe"
                + " AND chuyenbay.soHieuMaybay = maybay_ghe.soHieuMaybay"
                + " AND chuyenbay.soHieuMaybay = ?"
                + " AND hangVe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, soHieuMB);
        stm.setString(2, hangVe);
        
        ResultSet rs = stm.executeQuery();
        
        List<Ghe> ghe = new ArrayList<>();
        while (rs.next()) {
            Ghe g = new Ghe();
            g.setMaGhe(rs.getString("maGhe"));
            g.setHangVe(rs.getString("hangVe"));
            
            ghe.add(g);
        }
        return ghe;
    }
    
    public boolean updateGhe(String maGhe, String soHieuMB, boolean trangThai) throws SQLException{
        String sql = "UPDATE maybay_ghe"
                + " SET trangThai = ?"
                + " WHERE soHieuMayBay = ?"
                + " AND maGhe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setBoolean(1, trangThai);
        stm.setString(2, soHieuMB);
        stm.setString(3, maGhe);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
}
