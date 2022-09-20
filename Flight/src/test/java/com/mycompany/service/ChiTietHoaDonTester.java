/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChiTietHoaDon;
import java.math.BigDecimal;
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
public class ChiTietHoaDonTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(ChiTietHoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Test
    public void addChiTietHoaDonMaHoaDonSai() {
        try {
            ChiTietHoaDonService cthds = new ChiTietHoaDonService(conn);
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setMaHoaDon("8cc4dc5d-cccc-aaaasadssaad-b285-b113661c6e46");
            cthd.setMaVe(4);
            cthd.setGiaVe(new BigDecimal(90000));
            Assertions.assertFalse(cthds.addHDTT(cthd));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addChiTietHoaDonMaVeSai() {
        try {
            ChiTietHoaDonService cthds = new ChiTietHoaDonService(conn);
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setMaHoaDon("8cc4dc5d-cccc-bbbb-b285-b113661c6e46");
            cthd.setMaVe(999);
            cthd.setGiaVe(new BigDecimal(90000));
            Assertions.assertFalse(cthds.addHDTT(cthd));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addChiTietHoaDonGiaVeSai() {
        try {
            ChiTietHoaDonService cthds = new ChiTietHoaDonService(conn);
            ChiTietHoaDon cthd = new ChiTietHoaDon();
            cthd.setMaHoaDon("8cc4dc5d-cccc-bbbb-b285-b113661c6e46");
            cthd.setMaVe(4);
            cthd.setGiaVe(new BigDecimal(1000000000));
            Assertions.assertFalse(cthds.addHDTT(cthd));
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

