/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.KhachHang;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class KhachHangTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
    
   @Test
   @DisplayName("Kiem thu getKhachHang voi tenKH ton tai")
   public void testWithId() {
        try {
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh = khs.getKhachHang("Nguyễn Thị Diễm M");
            Assertions.assertTrue(kh.getTenKH().compareTo("Nguyễn Thị Diễm M") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getKhachHang voi tenKH khong ton tai")
   public void testUnknownWithId() {
        try {
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh = khs.getKhachHang("Hà Anh A");
            Assertions.assertEquals(null, kh);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   @Test
    public void testQuantity() {
        try {
            int expected = 1;
            KhachHangService khs = new KhachHangService(conn);
            List<KhachHang> kh = khs.getKhachHangsByTenKH("Phạm Anh D");
            
            System.out.println(kh);
            Assertions.assertTrue(kh.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueKhachHang() {
         try {
            KhachHangService khs = new KhachHangService(conn);
            List<KhachHang> kh = khs.getKhachHangsByTenKH("Phạm Anh D");
            
            List<String> k = new ArrayList<>();
            kh.forEach(h -> {
                k.add(h.getTenKH());
             });
            
             Set<String> k2 = new HashSet<>(k);
            
            Assertions.assertTrue(k.size() == k2.size());
         } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addKhachHangSDTSai() {
        try {
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh = new KhachHang();
            kh.setTenKH("Nguyễn Phương H");
            kh.setIdCard("030052645");
            kh.setEmail("nguyenphuongh@gmail.com");
            kh.setSdt("09090901071");
            Assertions.assertFalse(khs.addKhachHang(kh));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addKhachHangIDCardSai() {
        try {
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh = new KhachHang();
            kh.setTenKH("Nguyễn Phương H");
            kh.setIdCard("0300526451234");
            kh.setEmail("nguyenphuongh@gmail.com");
            kh.setSdt("0909090107");
            Assertions.assertFalse(khs.addKhachHang(kh));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void addKhachHangEmailSai() {
        try {
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh = new KhachHang();
            kh.setTenKH("Nguyễn Phương H");
            kh.setIdCard("030052645");
            kh.setEmail("nguyenphuonghgma");
            kh.setSdt("0909090107");
            Assertions.assertFalse(khs.addKhachHang(kh));
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
