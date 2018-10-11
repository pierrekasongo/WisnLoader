/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pierre Kasongo<pkasongo@intrahealth.org><pierrekasongo88@gmail.com>
 * COPYRIGHT NOTICE
 * Namibian Automatic WISN Loader (NAWL), a program developed by IntraHealth International (<intrahealth@intrahealth.org>)
 * to import service statistics into the WISN tool developed by WHO (<hrhinfo@who.int>).
 * 
 * Copyright (C) 2012 IntraHealth International
 * 
 * STATEMENT OF COPYRIGHT PERMISSION
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program.  If not, see 
 * <http://www.gnu.org/licenses/>.
 * 
 */
public class ReadProperties {
    
    private Properties prop;
    
    private InputStream input;
    
    public   ReadProperties() throws IOException{
        
        prop=new Properties();
        try {
            
            input=new FileInputStream("config.properties");
            
            prop.load(input);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadProperties.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(input != null){
                
                input.close();
            }
        }
    }
    
    public String getActivitiesFileName(){
        
        return this.prop.getProperty("ActivitiesFileName");
    }
    
    public String getFacilitiesFileName(){
        return this.prop.getProperty("FacilitiesFileName");
    }
    
    public String getStaffFileName(){
        return this.prop.getProperty("StaffFileName");
    }
    
    public String getWISNFileExtention(){
        return this.prop.getProperty("WISNFileExtension");
    }
    
    public String getTemplateFolderName(){
        return this.prop.getProperty("TemplateFolderName");
    }
}
