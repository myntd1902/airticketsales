/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.MayBay;
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
public class MayBayService {
    private Connection conn;

    public MayBayService(Connection conn) {
        this.conn = conn;
    }
    
    public List<MayBay> getMayBay() throws SQLException {
        String sql = "SELECT * FROM maybay";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        
        ResultSet rs = stm.executeQuery();
        List<MayBay> maybay = new ArrayList<>();
        while (rs.next()) {
            MayBay mb = new MayBay();
            mb.setSoHieuMayBay(rs.getString("soHieuMayBay"));
            mb.setHangMayBay(rs.getString("hangMayBay"));
            maybay.add(mb);
        }
        return maybay;
    }
}
