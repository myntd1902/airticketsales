/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChuyenBay;
import com.mycompany.pojo.ChuyenBay;
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
public class ChuyenBayTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Test
    public void testQuantity() {
        try {
            int expected = 2;
            ChuyenBayService cbs = new ChuyenBayService(conn);
            List<ChuyenBay> cb = cbs.getChuyenBay();
            
            System.out.println(cb);
            Assertions.assertTrue(cb.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueChuyenBay() {
         try {
            ChuyenBayService cbs = new ChuyenBayService(conn);
            List<ChuyenBay> cb = cbs.getChuyenBay();
            
            List<String> c = new ArrayList<>();
            cb.forEach(b -> {
             c.add(b.getMaChuyenBay());
             });
            
             Set<String> c2 = new HashSet<>(c);
            
            Assertions.assertTrue(c.size() == c2.size());
         } catch (SQLException ex) {
            Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
   @DisplayName("Kiem thu getChuyenBayByMaCB voi maCB ton tai")
   public void testWithMaCB() {
        try {
            ChuyenBayService cbs = new ChuyenBayService(conn);
            ChuyenBay cb = cbs.getChuyenBayByMaCB("1");
            Assertions.assertTrue(cb.getMaChuyenBay().compareTo("1") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getChuyenBayByMaCB voi maCB khong ton tai")
   public void testUnknownWithMaCB() {
        try {
            ChuyenBayService cbs = new ChuyenBayService(conn);
            ChuyenBay cb = cbs.getChuyenBayByMaCB("0");
            Assertions.assertEquals(null, cb);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   
}
