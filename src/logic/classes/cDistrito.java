/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classes;

/**
 *
 * @author Joao Coelho
 */
public class cDistrito {
    
    private String sDistrito;
private int iIdDistrito;
    public cDistrito(int id, String Distrito) {
        this.sDistrito = Distrito;
        iIdDistrito = id;
    }

    public String getsDistrito() {
        return sDistrito;
    }

    public void setsDistrito(String sDistrito) {
        this.sDistrito = sDistrito;
    }

    public int getiIdDistrito() {
        return iIdDistrito;
    }

    public void setiIdDistrito(int iIdDistrito) {
        this.iIdDistrito = iIdDistrito;
    }

    public String getDistrito() {
        return sDistrito;
    }

    public void setDistrito(String Distrito) {
        this.sDistrito = Distrito;
    }
    
    
    
}
