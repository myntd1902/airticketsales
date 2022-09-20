/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HangVe;
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
public class HangVeService {
    private Connection conn;

    public HangVeService(Connection conn) {
        this.conn = conn;
    }
    
    public List<HangVe> getHangVe() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM hangve");
        
        
        List<HangVe> hangve = new ArrayList<>();
        while (r.next()) {
            HangVe hv = new HangVe();
            hv.setId(r.getInt("id"));
            hv.setHangVe(r.getString("hangVe"));
            
            hangve.add(hv);
        }
        return hangve;
    }
    
    public HangVe getHangVeById(int id) throws SQLException {
        String sql = "SELECT * FROM hangve WHERE id=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet r = stm.executeQuery();
        HangVe hv = null;
        while (r.next()) {
            hv = new HangVe();
            hv.setId(r.getInt("id"));
            hv.setHangVe(r.getString("hangVe"));
        }
        return hv;
    }
}
