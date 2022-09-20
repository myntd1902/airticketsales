/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pojo;

/**
 *
 * @author Admin
 */
public class HangVe {
    private int id;
    private String hangVe;

    
    @Override
    public String toString() {
        return this.getHangVe();
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the hangVe
     */
    public String getHangVe() {
        return hangVe;
    }

    /**
     * @param hangVe the hangVe to set
     */
    public void setHangVe(String hangVe) {
        this.hangVe = hangVe;
    }

    

}
