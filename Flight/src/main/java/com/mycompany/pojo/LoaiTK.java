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
public class LoaiTK {
    private int id;
    private String tk;
    
    @Override
    public String toString() {
        return this.tk;
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
     * @return the tk
     */
    public String getTk() {
        return tk;
    }

    /**
     * @param tk the tk to set
     */
    public void setTk(String tk) {
        this.tk = tk;
    }
}
