/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

/**
 *
 * @author Admin
 */

import com.mycompany.pojo.SanBay;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SanBayService {
    private Connection conn;

    public SanBayService(Connection conn) {
        this.conn = conn;
    }
    
    public List<SanBay> getSanBay() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM sanbay");
        
        List<SanBay> sanbay = new ArrayList<>();
        while (rs.next()) {
            SanBay sb = new SanBay();
            sb.setMaSanBay(rs.getString("maSanBay"));
            sb.setTenSanBay(rs.getString("tenSanBay"));
            sb.setDiaDiem(rs.getString("diaDiem"));
            sb.setQuocGia(rs.getString("quocGia"));
            sb.setTrangThai(rs.getBoolean("trangThai"));
            
            sanbay.add(sb);
        }
        return sanbay;
    }
}