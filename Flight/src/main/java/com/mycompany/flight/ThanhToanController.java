/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight;

import com.mycompany.pojo.ChiTietHoaDon;
import com.mycompany.pojo.HoaDonThanhToan;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.Users;
import com.mycompany.pojo.VeMayBay;
import com.mycompany.service.ChiTietHoaDonService;
import com.mycompany.service.HoaDonThanhToanService;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.UsersService;
import com.mycompany.service.VeMayBayService;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class ThanhToanController implements Initializable {
    @FXML private TextField txtTenNguoiTT;
    @FXML private TextField txtTongTien;
    @FXML private TableView<VeMayBay> tbVeChuaTT;
    @FXML private TableView<KhachHang> tbTTKH;
    Users nd;
    BigDecimal tongTien;
    List<VeMayBay> vmbChon = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableVeChuaTT();
        loadTableTTKH();
        
        
        
        this.tbVeChuaTT.setRowFactory(obj -> {
            TableRow r = new TableRow();
            
            r.setOnMouseClicked(evt -> {
                try {
                    Connection conn = JdbcUtils.getConn();
                    KhachHangService khs = new KhachHangService(conn);
                    VeMayBay vmb = this.tbVeChuaTT.getSelectionModel().getSelectedItem();
                    loadTTKH(khs.getKhachHang(vmb.getTenKH()));
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            return r;
        });
    }
                
    
    public void setTTUser(Users u){
        nd = u;
        loadVeMayBayChuaTT(nd);
        this.txtTenNguoiTT.setText(nd.getHoTen());
        this.txtTongTien.setText("");
    }
    
    public void loadVeMayBayChuaTT(Users u){
        try {
            this.tbVeChuaTT.getItems().clear();
            Connection conn = JdbcUtils.getConn();
            VeMayBayService vmbs = new VeMayBayService(conn);
            this.tbVeChuaTT.setItems(FXCollections.observableList(
                    vmbs.getVeMayBaysChuaTT(u)));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TraCuuVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableVeChuaTT() {
        tongTien = new BigDecimal(0);
        TableColumn colMaVe = new TableColumn("Mã Vé");
        colMaVe.setCellValueFactory(new PropertyValueFactory("maVe"));
        colMaVe.setMaxWidth( 1f * Integer.MAX_VALUE * 8);
        
        TableColumn colHangVe = new TableColumn("Hạng Vé");
        colHangVe.setCellValueFactory(new PropertyValueFactory("hangVe"));
        colHangVe.setMaxWidth( 1f * Integer.MAX_VALUE * 9);
        
        TableColumn colGiaVe = new TableColumn("Giá Vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giaVe"));
        colGiaVe.setMaxWidth( 1f * Integer.MAX_VALUE * 5);
        
        TableColumn colMaGhe = new TableColumn("Mã Ghế");
        colMaGhe.setCellValueFactory(new PropertyValueFactory("maGhe"));
        colMaGhe.setMaxWidth( 1f * Integer.MAX_VALUE * 8);
        
        TableColumn colNgayXuatVe = new TableColumn("Ngày Xuất Vé");
        colNgayXuatVe.setCellValueFactory(new PropertyValueFactory("ngayXuatVe"));
        colNgayXuatVe.setMaxWidth( 1f * Integer.MAX_VALUE * 15);
        
        TableColumn colTenNguoiDat = new TableColumn("Tên Người Đặt");
        colTenNguoiDat.setCellValueFactory(new PropertyValueFactory("tenNguoiDat"));
        colTenNguoiDat.setMaxWidth( 1f * Integer.MAX_VALUE * 13);
        
        TableColumn colTenKH = new TableColumn("Tên Khách Hàng");
        colTenKH.setCellValueFactory(new PropertyValueFactory("tenKH"));
        colTenKH.setMaxWidth( 1f * Integer.MAX_VALUE * 13);
        
        TableColumn colMaCB = new TableColumn("Mã Chuyến Bay");
        colMaCB.setCellValueFactory(new PropertyValueFactory("maCB"));
        colMaCB.setMaxWidth( 1f * Integer.MAX_VALUE * 12);
        
        TableColumn colTrangThai = new TableColumn("Trạng Thái");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangThai"));
        colTrangThai.setMaxWidth( 1f * Integer.MAX_VALUE * 13);
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((obj) -> {
            CheckBox chbx = new CheckBox();
            
            chbx.setOnAction(evt -> {
                        TableCell cell = (TableCell) ((CheckBox) evt.getSource()).getParent();
                        VeMayBay vmb = (VeMayBay) cell.getTableRow().getItem();
                        BigDecimal tong = tongTien;
                        List<VeMayBay> ve = vmbChon;
                        chbx.setId(Integer.toString(vmb.getMaVe()));
                        if (chbx.getId() != null || chbx.getId() != Integer.toString(vmb.getMaVe()))
                            if (chbx.isSelected()) {
                                tong = tong.add(vmb.getGiaVe());
                                ve.add(vmb);
                            }
                            else {
                               tong = tong.subtract(vmb.getGiaVe());
                               ve.remove(vmb);
                            }
                        else {
                            Utils.getBox("Vui lòng không chọn tại đây!!!", Alert.AlertType.ERROR).show();
                            chbx.setSelected(false);
                            chbx.setIndeterminate(false);
                        }
                        vmbChon = ve;
                        tongTien = tong;
                        if (tongTien.compareTo(new BigDecimal(0)) == 0)
                            this.txtTongTien.setText("");
                        else
                            this.txtTongTien.setText(tongTien.toString());
            });
            
            TableCell cell = new TableCell();
            cell.setGraphic(chbx);
            return cell;
        });
        colAction.setMaxWidth( 1f * Integer.MAX_VALUE * 3);
        
        this.tbVeChuaTT.getColumns().addAll(colMaVe, colMaCB, colHangVe, colMaGhe, colTenNguoiDat, colTenKH, colGiaVe, colNgayXuatVe, colTrangThai, colAction);
    }
    
    public void loadTTKH(KhachHang kh){
        try {
            this.tbTTKH.getItems().clear();
            Connection conn = JdbcUtils.getConn();
            KhachHangService khs = new KhachHangService(conn);
            if (kh != null)
                this.tbTTKH.setItems(FXCollections.observableList(khs.getKhachHangsByTenKH(kh.getTenKH())));
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(TraCuuVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadTableTTKH() {
        TableColumn colMaKH = new TableColumn("Mã Khách Hàng");
        colMaKH.setCellValueFactory(new PropertyValueFactory("maKH"));
        colMaKH.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        
        TableColumn colTenKH = new TableColumn("Tên KH");
        colTenKH.setCellValueFactory(new PropertyValueFactory("tenKH"));
        colTenKH.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        
        TableColumn colIDCard = new TableColumn("CMND/CCCD");
        colIDCard.setCellValueFactory(new PropertyValueFactory("idCard"));
        colIDCard.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        
        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colEmail.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        
        TableColumn colSDT = new TableColumn("Số điện thoại");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));
        colSDT.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        
        this.tbTTKH.getColumns().addAll(colMaKH, colTenKH, colIDCard, colEmail, colSDT);
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
    
    public void cancelHandler(ActionEvent evt) throws IOException {
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
    
    public void payHandler(ActionEvent evt) throws IOException {
        try {
            if (vmbChon.isEmpty() == false) {
                UUID chuoiRD;
                Connection conn = JdbcUtils.getConn();
                HoaDonThanhToanService hdtts = new HoaDonThanhToanService(conn);
                ChiTietHoaDonService cthds = new ChiTietHoaDonService(conn);
                HoaDonThanhToan hdtt = null;
                ChiTietHoaDon cthd = null;
                VeMayBayService vmbs = new VeMayBayService(conn);
                Calendar cal;
                SimpleDateFormat simpleformat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                String ngay;
                for(int i = 0; i < vmbChon.size(); i++) {
                    VeMayBay vmb = vmbChon.get(i);
                    hdtt = new HoaDonThanhToan();
                    cthd = new ChiTietHoaDon();
                    cal = Calendar.getInstance();
                    ngay = simpleformat.format(cal.getTime());
                    chuoiRD = UUID.randomUUID();
                    hdtt.setMaHoaDon(chuoiRD.toString());
                    hdtt.setTenNguoiTT(this.txtTenNguoiTT.getText());
                    hdtt.setTenKH(vmbs.getVeMayBayByMaVe(vmb.getMaVe()).getTenKH());
                    hdtt.setNgayTT(ngay);
                    if (hdtts.addHDTT(hdtt)) {
                        cthd.setMaVe(vmb.getMaVe());
                        cthd.setMaHoaDon(hdtt.getMaHoaDon());
                        cthd.setGiaVe(vmb.getGiaVe());
                        if (cthds.addHDTT(cthd)) {
                            if (vmbs.updateTrangThaiVe(vmb.getMaVe())){
                                Utils.getBox("Bạn đã thanh toán thành công vé " + vmb.getMaVe() + " !!!", Alert.AlertType.INFORMATION).show();
                                this.tbVeChuaTT.getColumns().clear();
                                this.txtTongTien.clear();
                                
                                
                                loadTableVeChuaTT();
                                loadVeMayBayChuaTT(nd);
                                loadTTKH(null);
                            }
                        }
                    }
                    else
                        Utils.getBox("Bạn đã thanh toán thất bại!!!", Alert.AlertType.INFORMATION).show();
                }
            vmbChon.removeAll(vmbChon);
            conn.close();
            }
            else 
                Utils.getBox("Vui lòng chọn vé để thanh toán", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(ThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
