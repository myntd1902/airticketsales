/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.LoaiTK;
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
public class LoaiTKService {
    private Connection conn;
    
    public LoaiTKService(Connection conn) {
        this.conn = conn;
    }
    public List<LoaiTK> getLoaiTK() throws SQLException {
        Statement stm = this.conn.createStatement();
        ResultSet r = stm.executeQuery("SELECT * FROM loaitk");
        
        List<LoaiTK> re = new ArrayList<>();
        while (r.next()) {
            LoaiTK tk = new LoaiTK();
            tk.setId(r.getInt("id"));
            tk.setTk(r.getString("tk"));
            
            re.add(tk);
        }
        return re;
    }
    
    public LoaiTK getLoaiTKById(int id) throws SQLException {
        String sql = "SELECT * FROM loaitk WHERE id=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, id);
        
        ResultSet rs = stm.executeQuery();
        LoaiTK tk = null;
        while (rs.next()) {
            tk = new LoaiTK();
            tk.setId(rs.getInt("id"));
            tk.setTk(rs.getString("tk"));
        }
        return tk;
    }
}
