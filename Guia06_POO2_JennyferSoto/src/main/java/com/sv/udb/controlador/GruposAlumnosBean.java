/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import static com.fasterxml.jackson.databind.util.ClassUtil.getRootCause;
import com.sv.udb.ejb.GruposAlumnosFacadeLocal;
import com.sv.udb.modelo.GruposAlumnos;
import com.sv.udb.utils.log4j;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Owner
 */
@Named(value = "gruposAlumnosBean")
@ViewScoped
public class GruposAlumnosBean implements Serializable{

    @EJB
    private GruposAlumnosFacadeLocal FCDEGruposAlumnos;
    private GruposAlumnos objeGrupAlum;
    private List<GruposAlumnos> listGrupAlum;
    private boolean guardar;
    
    private static Logger loggi = Logger.getLogger(GruposAlumnos.class);

    public GruposAlumnos getObjeGrupAlum() {
        return objeGrupAlum;
    }

    public List<GruposAlumnos> getListGrupAlum() {
        return listGrupAlum;
    }

    public void setListGrupAlum(List<GruposAlumnos> listGrupAlum) {
        this.listGrupAlum = listGrupAlum;
    }

    public boolean isGuardar() {
        return guardar;
    }
    
    
    /**
     * Creates a new instance of GruposAlumnosBean
     */
    public GruposAlumnosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeGrupAlum = new GruposAlumnos();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEGruposAlumnos.create(this.objeGrupAlum);
            this.listGrupAlum.add(this.objeGrupAlum);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            loggi.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listGrupAlum.remove(this.objeGrupAlum); //Limpia el objeto viejo
            FCDEGruposAlumnos.edit(this.objeGrupAlum);
            this.listGrupAlum.add(this.objeGrupAlum); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            loggi.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEGruposAlumnos.remove(this.objeGrupAlum);
            this.listGrupAlum.remove(this.objeGrupAlum);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
            loggi.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listGrupAlum = FCDEGruposAlumnos.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            loggi.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiGrupAlumPara"));
        try
        {
            this.objeGrupAlum = FCDEGruposAlumnos.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado')");
            loggi.info("Grupos-Alumnos consultado");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            loggi.error(getRootCause(ex).getMessage());
        }
        finally
        {
            
        }
    }
}
