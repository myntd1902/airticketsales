/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.SanBay;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class SanBayTester {
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() {
         try {
             conn = JdbcUtils.getConn();
         } catch (SQLException ex) {
             Logger.getLogger(SanBayTester.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     @Test
     public void testQuantity() {
         try {
             int expected = 2;
             SanBayService sbs = new SanBayService(conn);
             List<SanBay> sb = sbs.getSanBay();

             System.out.println(sb);
             Assertions.assertTrue(sb.size() >= expected);
         } catch (SQLException ex) {
             Logger.getLogger(SanBayTester.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     @Test
     public void testUniqueSanBay() {
          try {
             SanBayService sbs = new SanBayService(conn);
             List<SanBay> sb = sbs.getSanBay();

             List<String> s = new ArrayList<>();
             sb.forEach(b -> {
                 s.add(b.getTenSanBay());
              });

              Set<String> s2 = new HashSet<>(s);

             Assertions.assertTrue(s.size() == s2.size());
          } catch (SQLException ex) {
             Logger.getLogger(SanBayTester.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
