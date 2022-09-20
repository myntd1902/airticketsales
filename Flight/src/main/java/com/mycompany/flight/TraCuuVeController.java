/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight;

import com.mycompany.pojo.ChuyenBay;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import com.mycompany.service.ChuyenBayService;
import com.mycompany.service.GheService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.PhieuDatChoService;
import com.mycompany.service.UsersService;
import com.mycompany.service.VeMayBayService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class TraCuuVeController implements Initializable {
    @FXML private TextField txtMaVe;
    @FXML private TextField txtMaCB;
    @FXML private TextField txtTenKH;
    @FXML private Label lbHidden;
    @FXML private TableView<VeMayBay> tbVeMayBay;
    Users nd;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTable();
        
        
        this.txtMaVe.textProperty().addListener((obj) -> {
            nhapDL();
        });
        
        this.txtMaCB.textProperty().addListener((obj) -> {
            nhapDL();
        });
        this.txtTenKH.textProperty().addListener((obj) -> {
            nhapDL();
        });
        
        
    }

    public void setTTUser(Users u){
        nd = u;
        loadVeMayBay(0, "", "", nd);
    }
    
    public void loadVeMayBay(int maVe, String maCB, String tenKH, Users u){
        try {
            this.tbVeMayBay.getItems().clear();
            Connection conn = JdbcUtils.getConn();
            VeMayBayService vmbs = new VeMayBayService(conn);
            this.tbVeMayBay.setItems(FXCollections.observableList(
                    vmbs.getVeMayBays(maVe, maCB, tenKH, u)));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TraCuuVeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    private void loadTable() {
        TableColumn colMaVe = new TableColumn("Mã Vé");
        colMaVe.setCellValueFactory(new PropertyValueFactory("maVe"));
        
        TableColumn colHangVe = new TableColumn("Hạng Vé");
        colHangVe.setCellValueFactory(new PropertyValueFactory("hangVe"));
        
        TableColumn colGiaVe = new TableColumn("Giá Vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        
        TableColumn colMaGhe = new TableColumn("Mã Ghế");
        colMaGhe.setCellValueFactory(new PropertyValueFactory("maGhe"));
        
        TableColumn colNgayXuatVe = new TableColumn("Ngày Xuất Vé");
        colNgayXuatVe.setCellValueFactory(new PropertyValueFactory("ngayXuatVe"));
        
        TableColumn colTenNguoiDat = new TableColumn("Tên Người Đặt");
        colTenNguoiDat.setCellValueFactory(new PropertyValueFactory("tenNguoiDat"));
        
        TableColumn colTenKH = new TableColumn("Tên Khách Hàng");
        colTenKH.setCellValueFactory(new PropertyValueFactory("tenKH"));
        
        TableColumn colMaCB = new TableColumn("Mã Chuyến Bay");
        colMaCB.setCellValueFactory(new PropertyValueFactory("maCB"));
        
        TableColumn colTrangThai = new TableColumn("Trạng Thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            Button btn = new Button("Hủy Vé");
            
            btn.setOnAction(evt -> {
                Utils.getBox("Bạn có xác nhận hủy không?", Alert.AlertType.CONFIRMATION)
                     .showAndWait().ifPresent(bt -> {
                         if (bt == ButtonType.OK) {
                             try {
                                 TableCell cell = (TableCell) ((Button) evt.getSource()).getParent();
                                 VeMayBay vmb = (VeMayBay) cell.getTableRow().getItem();
                                 
                                 if (vmb.getTrangThai() == "Chưa thanh toán") {
                                    Connection conn = JdbcUtils.getConn();
                                    VeMayBayService vmbs = new VeMayBayService(conn);
                                    PhieuDatChoService pdcs = new PhieuDatChoService(conn);
                                    GheService gs = new GheService(conn);
                                    ChuyenBayService cbs = new ChuyenBayService(conn);
                                    UsersService us = new UsersService(conn);

                                    int idLoaiTK = us.getUsersByTenNguoiDatVe(vmb.getTenNguoiDat()).getIdLoaiTK();
                                    int idNguoiDat = us.getUsersByTenNguoiDatVe(vmb.getTenNguoiDat()).getId();
                                    if ((nd.getIdLoaiTK() == 1 && idLoaiTK != 2) || idNguoiDat == nd.getId()) {
                                       if (pdcs.getPhieuDatChoByMaVe(vmb.getMaVe()) != null) {
                                          if (pdcs.delelePhieuDatCho(vmb.getMaVe())){
                                             if (vmbs.deleleVeMayBay(vmb.getMaVe())) 
                                                 if (gs.updateGhe(vmb.getMaGhe()
                                                   , cbs.getChuyenBayByMaCB(vmb.getMaCB())
                                                   .getSoHieuMayBay(), false) == true) {
                                                   Utils.getBox("Đã hủy vé thành công", Alert.AlertType.INFORMATION).show();
                                             } else
                                                 Utils.getBox("Đã hủy vé thất bại", Alert.AlertType.ERROR).show();
                                          }
                                       } else {
                                          if (vmbs.deleleVeMayBay(vmb.getMaVe()))
                                              if (gs.updateGhe(vmb.getMaGhe()
                                                   , cbs.getChuyenBayByMaCB(vmb.getMaCB())
                                                   .getSoHieuMayBay(), false) == true) {
                                               Utils.getBox("Đã hủy vé thành công", Alert.AlertType.INFORMATION).show();
                                         } else
                                             Utils.getBox("Đã hủy vé thất bại", Alert.AlertType.ERROR).show();
                                       }
                                       loadVeMayBay(vmb.getMaVe(), vmb.getMaCB(), vmb.getTenKH(), nd);
                                    } else
                                           Utils.getBox("Bạn không được phép hủy vé này", Alert.AlertType.ERROR).show();
                                    if (vmb == null)
                                        Utils.getBox("Không có vé để hủy tại đây", Alert.AlertType.ERROR).show();

                                    conn.close();
                                 } else
                                     Utils.getBox("Không thể hủy vé đã thnah toán", Alert.AlertType.WARNING).show();
                             } catch (SQLException ex) {
                                 
                                 ex.printStackTrace();
                                 Logger.getLogger(TraCuuVeController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     });
                
                
               
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(btn);
            return cell;
        });
        
        
        this.tbVeMayBay.getColumns().addAll(colMaVe, colMaCB, colHangVe, colMaGhe, colTenKH, colTenNguoiDat, colGiaVe, colNgayXuatVe, colTrangThai, colAction);
    }
    
    
    public void nhapDL() {
        int maVe = 0;
        String maCB = "";
        String tenKH = "";

        if (this.txtMaVe.getText().isEmpty() == false)
            maVe = Integer.parseInt(this.txtMaVe.getText());
        if (this.txtMaCB.getText().isEmpty() == false)
            maCB = this.txtMaCB.getText();
        if (this.txtTenKH.getText().isEmpty() == false)
            tenKH = this.txtTenKH.getText();
        
        loadVeMayBay(maVe, maCB, tenKH, nd);
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
