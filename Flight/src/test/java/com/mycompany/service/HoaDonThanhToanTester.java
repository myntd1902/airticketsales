/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HoaDonThanhToan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class HoaDonThanhToanTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Test
    public void addHoaDonThanhToanMaHoaDonSai() {
        try {
            HoaDonThanhToanService hdtts = new HoaDonThanhToanService(conn);
            HoaDonThanhToan hdtt = new HoaDonThanhToan();
            hdtt.setMaHoaDon("8cc4dc5d-ccsssscc-aaaa-b285-b113661c6e46");
            hdtt.setTenNguoiTT("Nhân Viên 01");
            hdtt.setTenKH("Phạm Anh D");
            hdtt.setNgayTT("05:00:00 02-03-2021");
            Assertions.assertFalse(hdtts.addHDTT(hdtt));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addHoaDonThanhToanTenNguoiTTSai() {
        try {
            HoaDonThanhToanService hdtts = new HoaDonThanhToanService(conn);
            HoaDonThanhToan hdtt = new HoaDonThanhToan();
            hdtt.setMaHoaDon("8cc4dc5d-cccc-bbbb-b285-b113661c6e46");
            hdtt.setTenNguoiTT("ABC");
            hdtt.setTenKH("Phạm Anh D");
            hdtt.setNgayTT("05:00:00 02-03-2021");
            Assertions.assertFalse(hdtts.addHDTT(hdtt));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addHoaDonThanhToanTenKHSai() {
        try {
            HoaDonThanhToanService hdtts = new HoaDonThanhToanService(conn);
            HoaDonThanhToan hdtt = new HoaDonThanhToan();
            hdtt.setMaHoaDon("8cc4dc5d-cccc-aaaa-b285-b113661c6e46");
            hdtt.setTenNguoiTT("Nhân Viên 01");
            hdtt.setTenKH("XAHFS");
            hdtt.setNgayTT("05:00:00 02-03-2021");
            Assertions.assertFalse(hdtts.addHDTT(hdtt));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addHoaDonThanhToanNgayTTSai() {
        try {
            HoaDonThanhToanService hdtts = new HoaDonThanhToanService(conn);
            HoaDonThanhToan hdtt = new HoaDonThanhToan();
            hdtt.setMaHoaDon("8cc4dc5d-cccc-aaaa-b285-b113661c6e46");
            hdtt.setTenNguoiTT("Nhân Viên 01");
            hdtt.setTenKH("XAHFS");
            hdtt.setNgayTT("05:00:-2021");
            Assertions.assertFalse(hdtts.addHDTT(hdtt));
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonThanhToanTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
