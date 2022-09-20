/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flight;

import com.mycompany.pojo.Users;
import com.mycompany.service.JdbcUtils;
import com.mycompany.service.UsersService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class TrangChuController implements Initializable {
    @FXML private Label lbNhanVien;
    @FXML private Label lbKhachHang;
    Users nd;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    
    
    public void setTenTK(Users u){
        try {
            Connection conn = JdbcUtils.getConn();
            UsersService us = new UsersService(conn);
            nd = us.getUsers(u.getTenTK());
            if (u.getIdLoaiTK() == 1)
                this.lbNhanVien.setText(nd.getHoTen());
            if (u.getIdLoaiTK() == 2)
                this.lbKhachHang.setText(nd.getHoTen());
        } catch (SQLException ex) {
            Logger.getLogger(TraCuuChuyenBayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void traCuuCBHandler(ActionEvent evt) {
        try {
            Parent tccb;
            Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tracuuchuyenbay.fxml"));
            tccb = loader.load();
            Scene scene = new Scene(tccb);
            TraCuuChuyenBayController controller = loader.getController();
            controller.setTTUser(nd);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void traCuuVeHandler(ActionEvent evt) {
        try {
            Parent tcv;
            Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("tracuuve.fxml"));
            tcv = loader.load();
            Scene scene = new Scene(tcv);
            TraCuuVeController controller = loader.getController();
            controller.setTTUser(nd);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void datVeTaiQuayHandler(ActionEvent evt) {
        try {
            Parent dvtq;
            Stage stage = (Stage)((Node) evt.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("datvetaiquay.fxml"));
            dvtq = loader.load();
            Scene scene = new Scene(dvtq);
            DatVeTaiQuayController controller = loader.getController();
            controller.setTTUser(nd);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void datVeOnlineHandler(ActionEvent evt) {
        try {
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
        } catch (IOException ex) {
            Logger.getLogger(TrangChuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
