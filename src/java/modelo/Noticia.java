/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author emilio
 */

public class Noticia {
     
    private int id;
    private Date data;
    private String hora;
    private String noticia;

    public Noticia(){
        this.data = new Date();
        this.hora = new SimpleDateFormat("hh:mm").format(this.data);
    }

    public Noticia(String noticia) {
        this();
        this.noticia = noticia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNoticia() {
        return this.noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }    
    
}
