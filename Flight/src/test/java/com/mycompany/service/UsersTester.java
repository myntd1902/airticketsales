/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.Users;
import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.Duration;
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
public class UsersTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   @Test
   @DisplayName("Kiem thu chuc nang login voi tenTK = null")
   public void testLoginTenTKNull() {
        try {
            Users u = new Users();
            u.setTenTK(null);
            u.setMatKhau("1234");
            UsersService us = new UsersService(conn);
            Assertions.assertFalse(us.login(u));
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu chuc nang login voi matKhau = null")
   public void testLoginMatKhauNull() {
        try {
            Users u = new Users();
            u.setTenTK("NV01");
            u.setMatKhau(null);
            UsersService us = new UsersService(conn);
            Assertions.assertFalse(us.login(u));
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu chuc nang login voi taiKhoan =null va matKhau = null")
   public void testLoginTaiKhoanMatKhauNull() {
        try {
            Users u = new Users();
            u.setTenTK(null);
            u.setMatKhau(null);
            UsersService us = new UsersService(conn);
            Assertions.assertFalse(us.login(u));
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   public void testLogin() {
        try {
            Users u = new Users();
            u.setTenTK("NV01");
            u.setMatKhau("1234");
            UsersService us = new UsersService(conn);
            Assertions.assertTrue(us.login(u));
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
    public void testTimeoutLogin() {
        Users u = new Users();
        u.setTenTK("NV01");
        u.setMatKhau("1234");
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new UsersService(conn).login(u);
        });
    }
    
   @Test
   @DisplayName("Kiem thu getUsers voi tenTK ton tai")
   public void testWithTenTK() {
        try {
            UsersService us = new UsersService(conn);
            Users u = us.getUsers("NV02");
            Assertions.assertTrue(u.getTenTK().contains("NV02"));
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getUsers voi tenTK khong ton tai")
   public void testUnknownWithTenTK() {
        try {
            UsersService us = new UsersService(conn);
            Users u = us.getUsers("ABCsadk");
            Assertions.assertEquals(null, u);
        } catch (SQLException ex) {
            Logger.getLogger(UsersTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    @Test
    public void testTimeoutGetUsers() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            new UsersService(conn).getUsers("");
        });
    }
}

