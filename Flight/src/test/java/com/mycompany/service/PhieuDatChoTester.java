/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.PhieuDatCho;
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
public class PhieuDatChoTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
    
   @Test
   @DisplayName("Kiem thu getPhieuDatChoByMaVe voi maVe ton tai")
   public void testWithId() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            PhieuDatCho pdc = pdcs.getPhieuDatChoByMaVe(2);
            Assertions.assertTrue(pdc.getMaVe() == 2);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getPhieuDatCho voi hangVe khong ton tai")
   public void testUnknownWithId() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            PhieuDatCho pdc = pdcs.getPhieuDatChoByMaVe(999);
            Assertions.assertEquals(null, pdc);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
    public void addPhieuDatChoSaiMaVe() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            PhieuDatCho pdc = new PhieuDatCho();
            pdc.setMaVe(999);
            pdc.setTenKH("Lê Thị B");
            pdc.setNgayDatVe("04:00:00 20-01-2021");
            Assertions.assertFalse(pdcs.addPhieuDatCho(pdc));
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addPhieuDatChoSaiTenKH() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            PhieuDatCho pdc = new PhieuDatCho();
            pdc.setMaVe(1);
            pdc.setTenKH("XYZ");
            pdc.setNgayDatVe("04:00:00 20-01-2021");
            Assertions.assertFalse(pdcs.addPhieuDatCho(pdc));
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deletePhieuDatChoKhongMaVe() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            Assertions.assertFalse(pdcs.delelePhieuDatCho(999));
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deletePhieuDatChoMaVe() {
        try {
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            Assertions.assertTrue(pdcs.delelePhieuDatCho(1));
        } catch (SQLException ex) {
            Logger.getLogger(PhieuDatChoTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

