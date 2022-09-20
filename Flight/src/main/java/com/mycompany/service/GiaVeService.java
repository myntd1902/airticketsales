/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.mycompany.pojo.ChuyenBay;
import com.mycompany.pojo.GiaVe;
import com.mycompany.pojo.HangVe;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GiaVeService {
    private Connection conn;

    public GiaVeService(Connection conn) {
        this.conn = conn;
    }
    
    public GiaVe getGiaVeByChuyenBay_HangVe(String maCB, String hangVe) throws SQLException {
        ChuyenBayService cbs = new ChuyenBayService(this.conn);
        ChuyenBay cb = new ChuyenBay();
//        HangVeService hvs = new HangVeService(this.conn);
//        HangVe hv = new HangVe();
        cb = cbs.getChuyenBayByMaCB(maCB);
//        hv = hvs.getHangVeById(idHangVe);
        String sql = "SELECT giave.tenSanBayDi, giave.tenSanBayDen, giave.hangVe, "
                + "giave.hangBay, giaVe FROM chuyenbay, hangve, giave, "
                + "maybay WHERE chuyenbay.tenSanBayDi = giave.tenSanBayDi "
                + "AND chuyenbay.tenSanBayDen = giave.tenSanBayDen  "
                + "AND hangve.hangVe = giave.hangVe "
                + "AND maybay.soHieuMayBay = chuyenbay.soHieuMayBay "
                + "AND maybay.hangBay = giave.hangBay AND giave.tenSanBayDi = ? "
                + "AND giave.tenSanBayDen = ? AND giave.hangVe = ?";
        PreparedStatement stm = this.conn.prepareStatement(sql);
        stm.setString(1, cb.getTenSanBayDi());
        stm.setString(2, cb.getTenSanBayDen());
        stm.setString(3, hangVe);
        ResultSet r = stm.executeQuery();
        
        
        GiaVe gv = null;
        while (r.next()) {
            gv = new GiaVe();
            gv.setTenSanBayDi(r.getString("tenSanBayDi"));
            gv.setTenSanBayDen(r.getString("tenSanBayDen"));
            gv.setHangVe(r.getString("hangVe"));
            gv.setHangBay(r.getString("hangBay"));
            gv.setGiaVe(r.getBigDecimal("giaVe"));
        }
        return gv;
    }
}