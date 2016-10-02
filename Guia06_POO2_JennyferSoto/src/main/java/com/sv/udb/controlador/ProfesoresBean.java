/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ProfesoresFacadeLocal;
import com.sv.udb.modelo.Profesores;
import com.sv.udb.utils.log4j;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Owner
 */
@Named(value = "profesoresBean")
@ViewScoped
public class ProfesoresBean implements Serializable{

    @EJB
    private ProfesoresFacadeLocal FCDEProf;
    private Profesores objeProf;
    private List<Profesores> listProf;
    private boolean guardar;
    private log4j loggi;

    public Profesores getObjeProf() {
        return objeProf;
    }

    public void setObjeProf(Profesores objeProf) {
        this.objeProf = objeProf;
    }

    public List<Profesores> getListProf() {
        return listProf;
    }

    public boolean isGuardar() {
        return guardar;
    }
    

    /**
     * Creates a new instance of ProfesoresBean
     */
    public ProfesoresBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
        loggi = new log4j();
    }
    
    public void limpForm()
    {
        this.objeProf = new Profesores();
        this.guardar = true;    
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            FCDEProf.create(this.objeProf);
            this.listProf.add(this.objeProf);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
            loggi.error("No se pudo guardar el profesor");
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
            this.listProf.remove(this.objeProf); //Limpia el objeto viejo
            FCDEProf.edit(this.objeProf);
            this.listProf.add(this.objeProf); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
            loggi.error("No se pudo modificar el profesor");
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
            FCDEProf.remove(this.objeProf);
            this.listProf.remove(this.objeProf);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    public void consTodo()
    {
        try
        {
            this.listProf = FCDEProf.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiProfPara"));
        try
        {
            this.objeProf = FCDEProf.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s %s", this.objeProf.getNombProf(), this.objeProf.getApelProf()) + "')");
            loggi.info("Profesor consultado");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
            loggi.error("No se pudo consultar");
        }
        finally
        {
            
        }
    }
    
}
