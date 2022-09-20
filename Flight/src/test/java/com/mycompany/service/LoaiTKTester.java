/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.LoaiTK;
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
public class LoaiTKTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
    
   @Test
   @DisplayName("Kiem thu getLoaiTKById voi id ton tai")
   public void testWithId() {
        try {
            LoaiTKService ltks = new LoaiTKService(conn);
            LoaiTK ltk = ltks.getLoaiTKById(1);
            Assertions.assertTrue(ltk.getId() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   @Test
   @DisplayName("Kiem thu getLoaiTKById voi id khong ton tai")
   public void testUnknownWithId() {
        try {
            LoaiTKService ltks = new LoaiTKService(conn);
            LoaiTK ltk = ltks.getLoaiTKById(99);
            Assertions.assertEquals(null, ltk);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
//   @Test
//    public void testTimeoutGetLoaiTKById() {
//        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
//            new LoaiTKService(conn).getLoaiTKById();
//        });
//    }
   
   @Test
   @DisplayName("Kiem thu getLoaiTK voi so luong loaiTK")
    public void testQuantity() {
        try {
            int expected = 3;
            LoaiTKService ltks = new LoaiTKService(conn);
            List<LoaiTK> ltk = ltks.getLoaiTK();
            
            System.out.println(ltk);
            Assertions.assertTrue(ltk.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueTK() {
         try {
            LoaiTKService ltks = new LoaiTKService(conn);
            List<LoaiTK> ltk = ltks.getLoaiTK();
            
            List<String> tk = new ArrayList<>();
            ltk.forEach(c -> {
                tk.add(c.getTk());
             });
            
             Set<String> tk2 = new HashSet<>(tk);
            
            Assertions.assertTrue(tk.size() == tk2.size());
         } catch (SQLException ex) {
            Logger.getLogger(LoaiTKTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

