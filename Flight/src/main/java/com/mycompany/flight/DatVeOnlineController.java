/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight;

import com.mycompany.pojo.ChuyenBay;
import com.mycompany.pojo.HangVe;
import com.mycompany.pojo.Ghe;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.PhieuDatCho;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import com.mycompany.service.ChuyenBayService;
import com.mycompany.service.HangVeService;
import com.mycompany.service.GheService;
import com.mycompany.service.GiaVeService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.PhieuDatChoService;
import com.mycompany.service.UsersService;
import com.mycompany.service.VeMayBayService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class DatVeOnlineController implements Initializable {
    @FXML private ComboBox<ChuyenBay> cbMaCB;
    @FXML private ComboBox<HangVe> cbHangVe;
    @FXML private ComboBox<Ghe> cbViTri;
    @FXML private TextField txtGiaTien;
    @FXML private TextField txtHoTen;
    @FXML private TextField txtIDCard;
    @FXML private TextField txtSDT;
    @FXML private TextField txtEmail;
    Users nd;
    String ngay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = JdbcUtils.getConn();
            ChuyenBayService s = new ChuyenBayService(conn);
            VeMayBayService ss = new VeMayBayService(conn);
            this.cbMaCB.setItems(FXCollections.observableList(s.getChuyenBay()));
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTTUser(Users u){
            nd = u;
            txtHoTen.setText(u.getHoTen());
            if (u.getEmail().isEmpty() == false)
                txtEmail.setText(u.getEmail());
            if (u.getSdt().isEmpty() == false)
                txtSDT.setText(u.getSdt());
            if (u.getIdCard().isEmpty() == false)
                txtIDCard.setText(u.getIdCard());
    }
    
    public void chonComboBoxMaCB(ActionEvent evt){
        if (evt.getSource() == this.cbMaCB) {
            try {
                    if (this.cbMaCB.getSelectionModel().isEmpty() == false) {
                        Connection conn = JdbcUtils.getConn();
                        HangVeService hvs = new HangVeService(conn);
                        this.cbHangVe.setItems(FXCollections.observableList(hvs.getHangVe()));
                        conn.close();
                    }
                        
                } catch (SQLException ex) {
                    Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
//            maCB = this.cbMaCB.getSelectionModel().getSelectedItem().getMaChuyenBay();
//            Utils.getBox("Mã chuyến bay: " + maCB, Alert.AlertType.CONFIRMATION).show();
//            Connection conn;
//            try {
//                conn = JdbcUtils.getConn();
//                VeMayBayService ss = new VeMayBayService(conn);
//                //giaVe = String.valueOf(ss.getGiaVeByMaCB(maCB));
//                this.txtGiaTien.setText("Lỗi");
//                conn.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
        }
//        return maCB;
    }
    
    public void chonComboBoxHangVe(ActionEvent evt){
        if (evt.getSource() == this.cbHangVe) {
            try {
                    if (this.cbHangVe.getSelectionModel().isEmpty() == false)
                    {
                        Connection conn = JdbcUtils.getConn();
                        GheService gs = new GheService(conn);
                        GiaVeService gvs = new GiaVeService(conn);
                        ChuyenBayService cbs = new ChuyenBayService(conn);
                        String soHieuMB = cbs.getChuyenBayByMaCB(
                            this.cbMaCB.getSelectionModel().getSelectedItem()
                            .getMaChuyenBay()).getSoHieuMayBay();
                        this.cbViTri.setItems(FXCollections.observableList(
                            gs.getGhe(soHieuMB, this.cbHangVe.getSelectionModel()
                            .getSelectedItem().getHangVe())));
                        this.txtGiaTien.setText(gvs.getGiaVeByChuyenBay_HangVe(
                            this.cbMaCB.getSelectionModel().getSelectedItem()
                            .getMaChuyenBay(), this.cbHangVe.getSelectionModel()
                            .getSelectedItem().getHangVe()).getGiaVe().toString());
                        //String maCB = this.cbMaCB.getSelectionModel().getSelectedItem().getMaChuyenBay();
//                        List<VeMayBay> vmb = ss.getVeMayBayByMaCB(maCB);
//                        this.cbHangVe.setItems(FXCollections.observableList(vmb));
                        
                        
                        //this.cbViTri.setItems(FXCollections.observableList(ss.getVeMayBay()));
                    }
                        
            } catch (SQLException ex) {
                Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void confirmHandler(ActionEvent evt) throws IOException {
        Connection conn;
        try {
            conn = JdbcUtils.getConn();
            VeMayBayService vmbs = new VeMayBayService(conn);
            VeMayBay vmb = new VeMayBay();
            GheService gs = new GheService(conn);
            ChuyenBayService cbs = new ChuyenBayService(conn);
            PhieuDatChoService pdcs = new PhieuDatChoService(conn);
            PhieuDatCho pdc = new PhieuDatCho();
            KhachHangService khs = new KhachHangService(conn);
            KhachHang kh;
            Calendar cal;
            SimpleDateFormat simpleformat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            
            
            if (this.cbMaCB.getSelectionModel().isEmpty())
                Utils.getBox("Vui lòng chọn mã chuyến bay!!!", Alert.AlertType.WARNING).show();
                else if (this.cbHangVe.getSelectionModel().isEmpty())
                        Utils.getBox("Vui lòng chọn hạng vé!!!", Alert.AlertType.WARNING).show();
                    else if (this.cbViTri.getSelectionModel().isEmpty())
                            Utils.getBox("Vui lòng chọn vị trí!!!", Alert.AlertType.WARNING).show();
                        else if (this.txtHoTen.getText().isEmpty())
                                Utils.getBox("Vui lòng nhập họ tên!!", Alert.AlertType.WARNING).show();
                            else if (this.txtIDCard.getText().isEmpty())
                                    Utils.getBox("Vui lòng nhập CMND/CCCD!!!", Alert.AlertType.WARNING).show();
                                else if (this.txtEmail.getText().isEmpty())
                                        Utils.getBox("Vui lòng nhập email!!!", Alert.AlertType.WARNING).show();
                                    else if (this.txtSDT.getText().isEmpty())
                                            Utils.getBox("Vui lòng nhập số điện thoại!!!", Alert.AlertType.WARNING).show();
                                        else if (this.txtIDCard.getText().length() != 12 && this.txtIDCard.getText().length() != 9)
                                                Utils.getBox("CMND có 9 số hoặc 12 số, CCCD có 12 số. Vui lòng nhập lại!!!", Alert.AlertType.WARNING).show();
                                            else if (this.txtSDT.getText().length() != 10)
                                                    Utils.getBox("Số điện thoại có 10 số. Vui lòng nhập lại!!!", Alert.AlertType.WARNING).show();
                                                else if (this.txtEmail.getText().indexOf("@") < 0)
                                                        Utils.getBox("Email không hợp lệ. Vui lòng nhập lại!!!", Alert.AlertType.WARNING).show();
                                                    else {
                                                        vmb.setMaCB(this.cbMaCB.getSelectionModel().getSelectedItem().getMaChuyenBay());
                                                        vmb.setHangVe(this.cbHangVe.getSelectionModel().getSelectedItem().getHangVe());
                                                        vmb.setMaGhe(this.cbViTri.getSelectionModel().getSelectedItem().getMaGhe());
                                                        vmb.setGiaVe(new BigDecimal(this.txtGiaTien.getText()));
                                                        cal = Calendar.getInstance();
                                                        ngay = simpleformat.format(cal.getTime());
                                                        vmb.setNgayXuatVe(ngay);
                                                        kh = khs.getKhachHang(this.txtHoTen.getText());

                                                        if (kh == null){
                                                            KhachHang k = new KhachHang();
                                                            k.setTenKH(this.txtHoTen.getText());
                                                            k.setIdCard(this.txtIDCard.getText());
                                                            k.setEmail(this.txtEmail.getText());
                                                            k.setSdt(this.txtSDT.getText());

                                                            if (khs.addKhachHang(k) == true)
                                                                kh = khs.getKhachHang(this.txtHoTen.getText());
                                                        }
                                                        vmb.setTenKH(kh.getTenKH());
                                                        vmb.setTenNguoiDat(nd.getHoTen());

                                                        if (vmbs.addVeMayBay(vmb) == true) {
                                                            if (gs.updateGhe(vmb.getMaGhe()
                                                                    , cbs.getChuyenBayByMaCB(vmb.getMaCB())
                                                                    .getSoHieuMayBay(), true) == true) {
                                                                Utils.getBox("Đặt vé thành công! \nVui lòng thanh toán để hoàn tất quá trình đặt vé!!!", Alert.AlertType.INFORMATION).show();
                                                                Parent dvo;
                                                                Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
                                                                FXMLLoader loader = new FXMLLoader();
                                                                loader.setLocation(getClass().getResource("datveonline.fxml"));
                                                                dvo = loader.load();
                                                                Scene scene = new Scene(dvo);
                                                                DatVeOnlineController controller = loader.getController();
                                                                controller.setTTUser(nd);
                                                                stage.setScene(scene);
                                                                stage.show();
                                                            }
                                                        }
                                                        else 
                                                            Utils.getBox("Đặt vé thất bại!!!", Alert.AlertType.ERROR).show();
                                                    }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void payHandler(ActionEvent e) throws IOException {
        Parent thanhtoan;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("thanhtoan.fxml"));
        thanhtoan = loader.load();
        Scene scene = new Scene(thanhtoan);
        ThanhToanController controller = loader.getController();
        controller.setTTUser(nd);
        stage.setScene(scene);
        stage.show();
    }
    
    public void logoutHandler(ActionEvent e) throws IOException {
        Parent dangnhap;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dangnhap.fxml"));
        dangnhap = loader.load();
        Scene scene = new Scene(dangnhap);
        stage.setScene(scene);
        stage.show();
    }
    
    public void continueHandler(ActionEvent e) throws IOException {
        Parent trangChuNV;
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UIkhachhang.fxml"));
        trangChuNV = loader.load();
        Scene scene = new Scene(trangChuNV);
        TrangChuController controller = loader.getController();
        controller.setTenTK(nd);
        stage.setScene(scene);
        stage.show();
    }
}

