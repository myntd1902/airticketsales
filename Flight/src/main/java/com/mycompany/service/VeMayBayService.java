/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.flight.Utils;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Admin
 */
public class VeMayBayService {
    private Connection conn;

    public VeMayBayService(Connection conn) {
        this.conn = conn;
    }
    public List<VeMayBay> getVeMayBays(int maVe, String maCB, String tenKH, Users u) throws SQLException{
        String sql="";
        if ((maVe == 0 && maCB == "" && tenKH == "") || u.getIdLoaiTK() == 1) {
            sql = "SELECT * FROM vemaybay"
                    + " WHERE (maVe like concat('%', ?, '%')"
                    + " OR tenKH like concat('%', ?, '%')"
                    + " OR maChuyenBay like concat('%', ?, '%')"
                    + " OR tenNguoiDat like concat('%', ?, '%'))"
                    + " ORDER BY maVe ASC";
        }
        else if (u.getIdLoaiTK() == 2) 
            sql = "SELECT vemaybay.* FROM vemaybay, users"
                    + " WHERE (maVe like concat('%', ?, '%')"
                    + " OR vemaybay.tenKH like concat('%', ?, '%')"
                    + " OR maChuyenBay like concat('%', ?, '%'))"
                    + " AND idLoaiTK = 2 AND tenNguoiDat = hoTen AND tenNguoiDat=?"
                    + " ORDER BY maVe ASC";
            
        
        PreparedStatement stm = this.conn.prepareStatement(sql);
        if (maVe == 0)
            stm.setString(1, null);
        else
            stm.setInt(1, maVe);
        if (tenKH == "")
            stm.setString(2, null);
        else
            stm.setString(2, tenKH);
        if (maCB == "")
            stm.setString(3, null);
        else
            stm.setString(3, maCB);
        if (u == null)
            stm.setString(4, null);
        else
            stm.setString(4, u.getHoTen());
        ResultSet rs = stm.executeQuery();
        
        List<VeMayBay> veMayBay = new ArrayList<>();
        while (rs.next()) {
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(rs.getInt("maVe"));
            vmb.setHangVe(rs.getString("hangVe"));
            vmb.setGiaVe(rs.getBigDecimal("giaVe"));
            vmb.setMaGhe(rs.getString("maGhe"));
            vmb.setNgayXuatVe(rs.getString("ngayXuatVe"));
            vmb.setTenNguoiDat(rs.getString("tenNguoiDat"));
            vmb.setTenKH(rs.getString("tenKH"));
            vmb.setMaCB(rs.getString("maChuyenBay"));
            vmb.setTrangThai(rs.getString("trangThai"));
            
            veMayBay.add(vmb);
        }
        return veMayBay;
    }
    
    public List<VeMayBay> getVeMayBaysChuaTT(Users u) throws SQLException{
        String sql="";
        if (u.getIdLoaiTK() == 1){
            sql = "SELECT vemaybay.* FROM vemaybay, users"
                    + " WHERE vemaybay.trangThai = 'Chưa thanh toán' "
                    + " AND (tenNguoiDat = ? OR idLoaiTK = 1)"
                    + " AND hoTen = tenNguoiDat";
        }else if (u.getIdLoaiTK() == 2) 
                sql = "SELECT * FROM vemaybay WHERE trangThai = 'Chưa thanh toán' "
                        + "AND tenNguoiDat = ?";
            
        
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, u.getHoTen());
        
        ResultSet rs = stm.executeQuery();
        
        List<VeMayBay> veMayBay = new ArrayList<>();
        while (rs.next()) {
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(rs.getInt("maVe"));
            vmb.setHangVe(rs.getString("hangVe"));
            vmb.setGiaVe(rs.getBigDecimal("giaVe"));
            vmb.setMaGhe(rs.getString("maGhe"));
            vmb.setNgayXuatVe(rs.getString("ngayXuatVe"));
            vmb.setTenNguoiDat(rs.getString("tenNguoiDat"));
            vmb.setTenKH(rs.getString("tenKH"));
            vmb.setMaCB(rs.getString("maChuyenBay"));
            vmb.setTrangThai(rs.getString("trangThai"));
            
            veMayBay.add(vmb);
        }
        return veMayBay;
    }
    
    public VeMayBay getVeMayBay(String ngayXuatVe, String tenKH) throws SQLException {
        String sql = "SELECT * FROM vemaybay WHERE ngayXuatVe = ? AND tenKH = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, ngayXuatVe);
        stm.setString(2, tenKH);
        
        ResultSet rs = stm.executeQuery();
        VeMayBay vmb = null;
        while (rs.next()) {
            vmb = new VeMayBay();
            vmb.setMaVe(rs.getInt("maVe"));
            vmb.setHangVe(rs.getString("hangVe"));
            vmb.setGiaVe(rs.getBigDecimal("giaVe"));
            vmb.setNgayXuatVe(rs.getString("ngayXuatVe"));
            vmb.setTenNguoiDat(rs.getString("tenNguoiDat"));
            vmb.setTenKH(rs.getString("tenKH"));
            vmb.setMaCB(rs.getString("maChuyenBay"));
        }
        return vmb;
    }
    
    public VeMayBay getVeMayBayByMaVe(int maVe) throws SQLException {
        String sql = "SELECT * FROM vemaybay WHERE maVe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maVe);
        
        ResultSet rs = stm.executeQuery();
        VeMayBay vmb = null;
        while (rs.next()) {
            vmb = new VeMayBay();
            vmb.setMaVe(rs.getInt("maVe"));
            vmb.setHangVe(rs.getString("hangVe"));
            vmb.setGiaVe(rs.getBigDecimal("giaVe"));
            vmb.setNgayXuatVe(rs.getString("ngayXuatVe"));
            vmb.setTenNguoiDat(rs.getString("tenNguoiDat"));
            vmb.setTenKH(rs.getString("tenKH"));
            vmb.setMaCB(rs.getString("maChuyenBay"));
        }
        return vmb;
    }
    
    public boolean addVeMayBay(VeMayBay vmb) throws SQLException {
        String sql = "INSERT INTO vemaybay(hangVe, giaVe, maGhe, ngayXuatVe"
                + ", tenNguoiDat, tenKH, maChuyenBay, trangThai) VALUES(?, ?, ?, ?, ?, ?, ?, 'Chưa thanh toán')";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, vmb.getHangVe());
        stm.setBigDecimal(2, vmb.getGiaVe());
        stm.setString(3, vmb.getMaGhe());
        stm.setString(4, vmb.getNgayXuatVe());
        stm.setString(5, vmb.getTenNguoiDat());
        stm.setString(6, vmb.getTenKH());
        stm.setString(7, vmb.getMaCB());
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public BigDecimal getGiaVeByMaCB(String maChuyenBay) throws SQLException {
        String sql = "SELECT giaVe FROM vemaybay WHERE maChuyenBay=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        VeMayBay vmb = null;
        while (rs.next()) {
            vmb = new VeMayBay();
            vmb.setGiaVe(rs.getBigDecimal("giaVe"));
        }
        return vmb.getGiaVe();
    }
    
    public boolean deleleVeMayBay(int maVe) throws SQLException {
        String sql = "DELETE FROM vemaybay WHERE maVe=?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maVe);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
    public boolean updateTrangThaiVe(int maVe) throws SQLException {
        String sql = "UPDATE vemaybay "
                + "SET trangThai = 'Đã thanh toán' "
                + "WHERE maVe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setInt(1, maVe);
        
        int row = stm.executeUpdate();
        
        return row > 0;
    }
    
}
