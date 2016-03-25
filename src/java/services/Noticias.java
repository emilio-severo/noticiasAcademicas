package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.Noticia;
import persistencia.DAONoticia;

/**
 * REST Web Service
 *
 * @author emilio
 */
@Path("noticias")
public class Noticias {

    @Context
    private UriInfo context;
    private Gson gson;
    private DAONoticia daoNoticia;

    public Noticias() {
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
        daoNoticia = new DAONoticia();        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/todas")
    public String getTodasNoticias(){                
        List<Noticia> noticias = daoNoticia.getTodasNoticias();       
        return gson.toJson(noticias);
    }    
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pordata/{data}")
    public String getNoticiasPorData(@PathParam("data") String data) throws ParseException {                
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNoticia = df.parse(data);
        List<Noticia> noticias = daoNoticia.getNoticiasDia(dataNoticia);
        return gson.toJson(noticias);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postNoticia")
    public void postNoticia(String content){
        Noticia noticia = (Noticia) gson.fromJson(content, Noticia.class);
        daoNoticia.incluir(noticia);     
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/putNoticia")
    public void putNoticia(String content){
        Noticia noticia = (Noticia) gson.fromJson(content, Noticia.class);
        daoNoticia.alterar(noticia);
    }
        
    @DELETE
    @Path("/deleteNoticia/{id}")
    public void deleteNoticia(@PathParam("id") String id){
        daoNoticia.excluir(Integer.valueOf(id));
    }    
    
}
