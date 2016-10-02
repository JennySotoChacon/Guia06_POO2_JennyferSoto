/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;
import org.apache.log4j.*;

/**
 *
 * @author Owner
 */
public class log4j {
    
    private static Logger logger = Logger.getLogger(log4j.class);
    
    public log4j(){
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("/log4j.pr" + "operties").getPath());
    }
    
    public void debug(String mens){
        logger.debug(mens);
    }
    public void info(String mens){
        logger.info(mens);
    }
    public void warm(String mens){
        logger.warn(mens);
    }
    public void error(String mens){
        logger.error(mens);
    }
    public void fatal(String mens){
        logger.fatal(mens);
    }
}


