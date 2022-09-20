/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight;

import com.mycompany.pojo.ChuyenBay;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.MayBay;
import com.mycompany.pojo.PhieuDatCho;
import com.mycompany.pojo.SanBay;
import com.mycompany.service.SanBayService;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import com.mycompany.service.ChuyenBayService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.MayBayService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class TraCuuChuyenBayController implements Initializable {
    @FXML private ComboBox cbNoiDi_KH;
    @FXML private ComboBox cbNoiDen_KH;
    @FXML private ComboBox cbNoiDi_MC;
    @FXML private ComboBox cbNoiDen_MC;
    @FXML private TableView<ChuyenBay> tbvChuyenBayKH;
    @FXML private TableView<ChuyenBay> tbvChuyenBayMC;
    @FXML private DatePicker ngayKhoiHanh;
    @FXML private DatePicker ngayVe;
    @FXML private Button btTraCuu;
    @FXML private Button btTiepTuc;
    @FXML private Button btThoat;
    Users nd;
    LocalDate date = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadTable();
            
            Connection conn = JdbcUtils.getConn();
            SanBayService sbs = new SanBayService(conn);
            SanBay sb = new SanBay();
            this.cbNoiDi_KH.setItems(FXCollections.observableList(sbs.getSanBay()));
            this.cbNoiDen_KH.setItems(FXCollections.observableList(sbs.getSanBay()));
            this.cbNoiDi_MC.setItems(FXCollections.observableList(sbs.getSanBay()));
            this.cbNoiDen_MC.setItems(FXCollections.observableList(sbs.getSanBay()));
            
            
            
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(TraCuuChuyenBayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setTTUser(Users u){
        nd = u;
        loadChuyenBay("", "", "");
    }
    
    public void loadChuyenBay(String tenSanBayDi, String tenSanBayDen, String ngayGioKhoiHanh){
        try {
            this.tbvChuyenBayMC.getItems().clear();
            Connection conn = JdbcUtils.getConn();
            ChuyenBayService cbs = new ChuyenBayService(conn);
            this.tbvChuyenBayMC.setItems(FXCollections.observableList(
                    cbs.getChuyenBayMCs(tenSanBayDi, tenSanBayDen, ngayGioKhoiHanh)));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TraCuuVeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void loadTable() {
        TableColumn colMaCB = new TableColumn("Mã CB");
        colMaCB.setCellValueFactory(new PropertyValueFactory("maChuyenBay"));
        
        TableColumn colSHCB = new TableColumn("Số Hiệu Máy Bay");
        colSHCB.setCellValueFactory(new PropertyValueFactory("soHieuMayBay"));
        
        TableColumn colNoiDi = new TableColumn("Nơi Đi");
        colNoiDi.setCellValueFactory(new PropertyValueFactory("tenSanBayDi"));
        
        TableColumn colNoiDen = new TableColumn("Nơi Đến");
        colNoiDen.setCellValueFactory(new PropertyValueFactory("tenSanBayDen"));
        
        TableColumn colTGKhoiHanh = new TableColumn("Thời Gian Khởi Hành");
        colTGKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngayGioKhoiHanh"));
        
        TableColumn colTGDen = new TableColumn("Thời Gian Đến");
        colTGDen.setCellValueFactory(new PropertyValueFactory("ngayGioDen"));
        
        
        this.tbvChuyenBayMC.getColumns().addAll(colMaCB, colSHCB, colNoiDi, colNoiDen, colTGKhoiHanh, colTGDen);
        this.tbvChuyenBayKH.getColumns().addAll(colMaCB, colSHCB, colNoiDi, colNoiDen, colTGKhoiHanh);
    }
    
    public void traCuuHanler (ActionEvent evt) throws IOException {
        Connection conn;
        try {
            conn = JdbcUtils.getConn();
            if (this.ngayKhoiHanh.getValue()!= null)
                    date = this.ngayKhoiHanh.getValue();
            if (this.cbNoiDi_MC.getSelectionModel().isEmpty() 
                    || this.cbNoiDen_MC.getSelectionModel().isEmpty()
                    || date.toString().isEmpty())
                Utils.getBox("Vui lòng chọn 1 thông tin cần tr cứu!!!", Alert.AlertType.WARNING).show();
                        else {
                            String tenSBDi = cbNoiDi_MC.getSelectionModel().getSelectedItem().toString();
                            String tenSBDen = cbNoiDi_MC.getSelectionModel().getSelectedItem().toString();
                            String ngayKH = date.toString().replace("/", "-");
                            loadChuyenBay(tenSBDi, tenSBDen, ngayKH);
                        }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatVeOnlineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logoutHandler(ActionEvent evt) throws IOException {
        Parent dangnhap;
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("dangnhap.fxml"));
        dangnhap = loader.load();
        Scene scene = new Scene(dangnhap);
        stage.setScene(scene);
        stage.show();

    }
    
    public void continueHandler(ActionEvent evt) throws IOException {
        Parent trangchu;
        var path="";
        if (nd.getIdLoaiTK() == 1)
            path = "UInhanvien.fxml";
        if (nd.getIdLoaiTK() == 2)
            path = "UIkhachhang.fxml";
        Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        trangchu = loader.load();
        Scene scene = new Scene(trangchu);
        TrangChuController controller = loader.getController();
        controller.setTenTK(nd);
        stage.setScene(scene);
        stage.show();
    }
}