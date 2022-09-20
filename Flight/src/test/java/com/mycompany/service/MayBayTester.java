/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.MayBay;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Admin
 */
public class MayBayTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(MayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(MayBayTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    @Test
    public void testQuantity() {
        try {
            int expected = 2;
            MayBayService mbs = new MayBayService(conn);
            List<MayBay> mb = mbs.getMayBay();
            
            System.out.println(mb);
            Assertions.assertTrue(mb.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(MayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueMayBay() {
         try {
            MayBayService mbs = new MayBayService(conn);
            List<MayBay> mb = mbs.getMayBay();
            
            List<String> m = new ArrayList<>();
            mb.forEach(b -> {
                m.add(b.getSoHieuMayBay());
             });
            
             Set<String> m2 = new HashSet<>(m);
            
            Assertions.assertTrue(m.size() == m2.size());
         } catch (SQLException ex) {
            Logger.getLogger(MayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
