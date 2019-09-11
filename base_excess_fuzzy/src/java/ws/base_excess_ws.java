/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.antlr.runtime.RecognitionException;
import test_fuzzy_logic_medicina.BaseExcessDisorders;
import test_fuzzy_logic_medicina.CPossibleDiagnose;

/**
 * REST Web Service
 *
 * @author rayma
 */
@Path("base_excess")
public class base_excess_ws {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of base_excess_ws
     */
    public base_excess_ws() {
    }

    /**
     * Retrieves representation of an instance of ws.base_excess_ws
     * @param ph
     * @param pco2
     * @param sbe
     * @param hco3
     * @return an instance of java.lang.String
     * @throws org.antlr.runtime.RecognitionException
     */
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("diagnose/get/{ph}/{pco2}/{sbe}/{hco3}")
    public String getDiagnose(@PathParam("ph") double ph,@PathParam("pco2") double pco2,@PathParam("sbe") double sbe,@PathParam("hco3") double hco3) throws RecognitionException{
        BaseExcessDisorders bed=new BaseExcessDisorders();
        bed.resetFis();
        List<CPossibleDiagnose> lista=bed.listDiagnosis( ph,pco2,sbe,hco3);
        Gson g=new Gson();
        return g.toJson(lista);
    
    }

    /**
     * PUT method for updating or creating an instance of base_excess_ws
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        
    }
    
    public static void main(String[] args){
        base_excess_ws bs=new base_excess_ws();
        
    }
}
