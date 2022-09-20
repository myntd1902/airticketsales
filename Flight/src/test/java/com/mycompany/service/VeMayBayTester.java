/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.VeMayBay;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import java.math.BigDecimal;
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
 * @author ADmin
 */
public class VeMayBayTester {
    private static Connection conn;
    
   @BeforeAll
   public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @AfterAll
   public static void afterAll() {
       if (conn != null)
           try {
               conn.close();
       } catch (SQLException ex) {
           Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   
   
   @Test
    public void testQuantityVeMayBays() {
        try {
            int expected = 2;
            VeMayBayService vmbs = new VeMayBayService(conn);
            Users u = new Users();
            List<VeMayBay> vmb = vmbs.getVeMayBays(1, "1", "getVeMayBays", u);
            
            System.out.println(vmb);
            Assertions.assertTrue(vmb.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueVeMayBays() {
         try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Users u = new Users();
            List<VeMayBay> vmb = vmbs.getVeMayBays(1, "1", "getVeMayBays", u);
            
            List<String> ve = new ArrayList<>();
            vmb.forEach(mb -> {
                ve.add(mb.getTenKH());
             });
            
             Set<String> ve2 = new HashSet<>(ve);
            
            Assertions.assertTrue(ve.size() == ve2.size());
         } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testQuantityVeMayBaysChuaTT() {
        try {
            int expected = 2;
            VeMayBayService vmbs = new VeMayBayService(conn);
            Users u = new Users();
            List<VeMayBay> vmb = vmbs.getVeMayBaysChuaTT(u);
            
            System.out.println(vmb);
            Assertions.assertTrue(vmb.size() >= expected);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testUniqueVeMayBaysChuaTT() {
         try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Users u = new Users();
            List<VeMayBay> vmb = vmbs.getVeMayBaysChuaTT(u);
            
            List<String> ve = new ArrayList<>();
            vmb.forEach(mb -> {
                ve.add(mb.getTenKH());
             });
            
             Set<String> ve2 = new HashSet<>(ve);
            
            Assertions.assertTrue(ve.size() == ve2.size());
         } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
   @DisplayName("Kiem thu getVeMayBay voi tenKH ton tai")
   public void testWithTenKHGetVeMayBay() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = vmbs.getVeMayBay("06:00:00 27-02-2021", "Trương G");
            Assertions.assertTrue(vmb.getTenKH().compareTo("Trương G") == 0 && vmb.getNgayXuatVe().compareTo("06:00:00 27-02-2021") == 0);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getVeMayBay voi tenKH khong ton tai")
   public void testUnknownWithTenKHGetVeMayBay() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = vmbs.getVeMayBay("06:00:00 27-02-2021", "");
            Assertions.assertEquals(null, vmb);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getVeMayBay voi tenKH khong ton tai")
   public void testUnknownWithNgayXuatVeGetVeMayBay() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = vmbs.getVeMayBay("", "Trương G");
            Assertions.assertEquals(null, vmb);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getVeMayBayByMaVe voi maVe ton tai")
   public void testWithMaVeGetVeMayBay() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = vmbs.getVeMayBayByMaVe(1);
            Assertions.assertTrue(vmb.getMaVe() == 1);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getVeMayBayByMaVe voi maVe khong ton tai")
   public void testUnknownWithMaVeGetVeMayBay() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
           VeMayBay vmb = vmbs.getVeMayBayByMaVe(9999);
            Assertions.assertEquals(null, vmb);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
    public void addVeMayBayVeMBSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(1);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void addVeMayBayHangVeSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("fggđ");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addVeMayBayGiaVeSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(80000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addVeMayBayNgayXuayVeSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addVeMayBayTenNguoiDatSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("@ads");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addVeMayBayTenKHSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("ahdsbsa");
            vmb.setMaCB("1");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addVeMayBayMaCBSai() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            vmb.setMaVe(999);
            vmb.setHangVe("Thương gia");
            vmb.setGiaVe(new BigDecimal(120000));
            vmb.setNgayXuatVe("04:00:00 20-01-2021");
            vmb.setTenNguoiDat("Nhân Viên 01");
            vmb.setTenKH("Lê Thị B");
            vmb.setMaCB("0");
            Assertions.assertFalse(vmbs.addVeMayBay(vmb));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deleteVeMaBayKhongMaVe() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Assertions.assertFalse(vmbs.deleleVeMayBay(999));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deleteVeMayBayMaVe() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Assertions.assertTrue(vmbs.deleleVeMayBay(1));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateVeMaBayKhongMaVe() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Assertions.assertFalse(vmbs.updateTrangThaiVe(0));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void updateVeMayBayMaVe() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            Assertions.assertTrue(vmbs.updateTrangThaiVe(1));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
   @DisplayName("Kiem thu getGiaVeByMaCB voi maCB ton tai")
   public void testWithMaCBGetGiaVeByMaCB() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            BigDecimal giaVe = vmbs.getGiaVeByMaCB("1");
            Assertions.assertTrue(giaVe.equals(new BigDecimal(200000)));
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @Test
   @DisplayName("Kiem thu getGiaVeByMaCB voi maCB khong ton tai")
   public void testUnknownWithMaCBgetGiaVeByMaCB() {
        try {
            VeMayBayService vmbs = new VeMayBayService(conn);
            BigDecimal giaVe = vmbs.getGiaVeByMaCB("0");
            Assertions.assertEquals(null, giaVe);
        } catch (SQLException ex) {
            Logger.getLogger(VeMayBayTester.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
}
