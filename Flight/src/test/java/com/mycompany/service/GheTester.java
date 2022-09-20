/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Ghe;
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
import org.junit.jupiter.api.Test;

/**
 *
 * @author Admin
 */
public class GheTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
    @Test
    public void testQuantity() {
        try {
            int expected = 10;
            GheService gs = new GheService(conn);
            List<Ghe> g = gs.getGhe("BA01", "Phổ thông");
            
            System.out.println(g);
            Assertions.assertTrue(g.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueGhe() {
         try {
            GheService gs = new GheService(conn);
            List<Ghe> g = gs.getGhe("BA01", "Phổ thông");
            
            List<String> gh = new ArrayList<>();
            g.forEach(h -> {
               gh.add(h.getMaGhe());
             });
            
             Set<String> gh2 = new HashSet<>(gh);
            
            Assertions.assertTrue(gh.size() == gh2.size());
         } catch (SQLException ex) {
            Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateGheMaGheSai() {
        try {
            GheService gs = new GheService(conn);
            Assertions.assertFalse(gs.updateGhe("Z99", "BA01", true));
        } catch (SQLException ex) {
            Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateGheSoHieuMBSai() {
        try {
            GheService gs = new GheService(conn);
            Assertions.assertFalse(gs.updateGhe("A01", "VJ09", true));
        } catch (SQLException ex) {
            Logger.getLogger(GheTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
