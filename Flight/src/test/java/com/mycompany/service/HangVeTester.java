/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.HangVe;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
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
public class HangVeTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
    
   @Test
   @DisplayName("Kiem thu getHangVeById voi id ton tai")
   public void testWithId() {
        try {
            HangVeService hvs = new HangVeService(conn);
            HangVe hv = hvs.getHangVeById(1);
            Assertions.assertTrue(hv.getId() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   @Test
   @DisplayName("Kiem thu getHangVeById voi id khong ton tai")
   public void testUnknownWithId() {
        try {
            HangVeService hvs = new HangVeService(conn);
            HangVe hv = hvs.getHangVeById(99);
            Assertions.assertEquals(null, hv);
        } catch (SQLException ex) {
            Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   @Test
   @DisplayName("Kiem thu getHangVe voi so luong HangVe")
    public void testQuantity() {
        try {
            int expected = 2;
            HangVeService hvs = new HangVeService(conn);
            List<HangVe> hv = hvs.getHangVe();
            
            System.out.println(hv);
            Assertions.assertTrue(hv.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueHangVe() {
         try {
            HangVeService hvs = new HangVeService(conn);
            List<HangVe> hv = hvs.getHangVe();
            
            List<String> v = new ArrayList<>();
            hv.forEach(h -> {
                v.add(h.getHangVe());
             });
            
             Set<String> v2 = new HashSet<>(v);
            
            Assertions.assertTrue(v.size() == v2.size());
         } catch (SQLException ex) {
            Logger.getLogger(HangVeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
