/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UsersService {
    private Connection conn;

    public UsersService(Connection conn) {
        this.conn = conn;
    }
    
    public boolean login(Users u) throws SQLException{
        String sql = "SELECT * FROM users WHERE tenTK=? AND matKhau=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, u.getTenTK());
        stm.setString(2, u.getMatKhau());
        ResultSet rs = stm.executeQuery();
        
        if (rs.next())
            return true;
        return false;
    }
    
    public Users getUsers(String tenTK) throws SQLException {
        String sql = "SELECT id, hoTen, tenTK, idLoaiTK, idCard, sdt, email FROM users WHERE tenTK=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, tenTK);
        
        ResultSet rs = stm.executeQuery();
        Users u = null;
        while (rs.next()) {
            u = new Users();
            u.setId(rs.getInt("id"));
            u.setHoTen(rs.getString("hoTen"));
            u.setTenTK(rs.getString("tenTK"));
            u.setIdLoaiTK(rs.getInt("idLoaiTK"));
            u.setIdCard(rs.getString("idCard"));
            u.setEmail(rs.getString("email"));
            u.setSdt(rs.getString("sdt"));
        }
        return u;
    }
    
    public Users getUsersByTenNguoiDatVe(String tenNguoiDat) throws SQLException {
        String sql = "SELECT users.* FROM vemaybay, users WHERE hoTen = tenNguoiDat AND tenNguoiDat = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, tenNguoiDat);
        
        ResultSet rs = stm.executeQuery();
        Users u = null;
        while (rs.next()) {
            u = new Users();
            u.setId(rs.getInt("id"));
            u.setHoTen(rs.getString("hoTen"));
            u.setTenTK(rs.getString("tenTK"));
            u.setIdLoaiTK(rs.getInt("idLoaiTK"));
            u.setIdCard(rs.getString("idCard"));
            u.setEmail(rs.getString("email"));
            u.setSdt(rs.getString("sdt"));
        }
        return u;
    }
}
