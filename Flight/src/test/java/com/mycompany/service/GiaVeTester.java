/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.GiaVe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class GiaVeTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(GiaVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(GiaVeTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
    
   @Test
   @DisplayName("Kiem thu getGiaVe voi maCB va hangVe ton tai")
   public void testWithId() {
        try {
            GiaVeService gvs = new GiaVeService(conn);
            GiaVe gv = gvs.getGiaVeByChuyenBay_HangVe("1", "Phổ thông");
            Assertions.assertTrue(gv.getHangVe().compareTo("Phổ thông") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(GiaVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getGiaVe voi hangVe khong ton tai")
   public void testUnknownWithId() {
        try {
            GiaVeService gvs = new GiaVeService(conn);
            GiaVe gv = gvs.getGiaVeByChuyenBay_HangVe("1", "Đại gia");
            Assertions.assertEquals(null, gv);
        } catch (SQLException ex) {
            Logger.getLogger(GiaVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
