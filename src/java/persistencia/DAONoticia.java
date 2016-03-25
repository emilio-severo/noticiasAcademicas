/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import modelo.Noticia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author emilio
 */
public class DAONoticia {
    
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private SimpleDateFormat df;

    public DAONoticia() {
        con = ConexaoFabrica.getConexao();
        df = new SimpleDateFormat("dd/MM/yyyy");
    }
    
    public void incluir(Noticia noticia){
        try {
            pstm = con.prepareStatement("insert into noticias(data, hora, noticia) values (?, ?, ?)");
            pstm.setDate(1, new java.sql.Date(noticia.getData().getTime()));
            pstm.setString(2, noticia.getHora());
            pstm.setString(3, noticia.getNoticia());
            pstm.execute();            
            pstm.close();
            con.close();            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void alterar(Noticia noticia){
        try {
            pstm = con.prepareStatement("update noticias set noticia=? where data=? and hora=?");
            pstm.setString(1, noticia.getNoticia());
            pstm.setDate(2, new java.sql.Date(noticia.getData().getTime()));
            pstm.setString(3, noticia.getHora());
            pstm.execute();            
            pstm.close();
            con.close();            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }        
    }    
    
    public void excluir(int id){
        try {
            pstm = con.prepareStatement("delete from noticias where id=?");
            pstm.setInt(1, id);
            pstm.execute();            
            pstm.close();
            con.close();            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);   
        }
    }
    
    public List<Noticia> getNoticiasPeriodo(Date dataini, Date datafim){
        List<Noticia> noticias = new ArrayList<>();
        try {            
            pstm = con.prepareStatement("select * from noticias where data between ? and ? order by data, hora");
            pstm.setDate(1, new java.sql.Date(dataini.getTime()));
            pstm.setDate(2, new java.sql.Date(datafim.getTime()));
            rs = pstm.executeQuery();    
            while(rs.next()){
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id"));                
                noticia.setData(new java.sql.Date(rs.getDate("data").getTime()));
                noticia.setHora(rs.getString("hora"));
                noticia.setNoticia(rs.getString("noticia"));
                noticias.add(noticia);
            }
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }        
        return noticias;
    }

    public List<Noticia> getNoticiasDia(Date data){
        List<Noticia> noticias = new ArrayList<>();
        try {            
            pstm = con.prepareStatement("select * from noticias where data = ? order by data, hora");
            pstm.setDate(1, new java.sql.Date(data.getTime()));
            rs = pstm.executeQuery();    
            while(rs.next()){
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id"));                
                noticia.setData(new java.sql.Date(rs.getDate("data").getTime()));
                noticia.setHora(rs.getString("hora"));
                noticia.setNoticia(rs.getString("noticia"));
                noticias.add(noticia);
            }
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }        
        return noticias;
    }

    public List<Noticia> getTodasNoticias(){
        List<Noticia> noticias = new ArrayList<>();
        try {            
            pstm = con.prepareStatement("select * from noticias order by data, hora");
            rs = pstm.executeQuery();    
            while(rs.next()){                
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id"));
                noticia.setData(new java.sql.Date(rs.getDate("data").getTime()));
                noticia.setHora(rs.getString("hora"));
                noticia.setNoticia(rs.getString("noticia"));
                noticias.add(noticia);
            }
            pstm.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }        
        return noticias;
    }
    
    
}
