/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;

import java.util.Map;

 /**
 * @author Jason Ndemueda<jndemueda@intrahealth.org><ndemuedajason@gmail.com>
 * @author Pierre Kasongo<pkasongo@intrahealth.org><pierrekasongo88@gmail.com>
 * COPYRIGHT NOTICE
 * WISN Loader, a program developed by IntraHealth International (<intrahealth@intrahealth.org>)
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

public class Main {       
    /**
     * @param argv
     * @throws Exception 
     */
    
    public static void main(String argv[]) throws Exception {
        
        ReadProperties rp=new ReadProperties();

        //Load regions from template
        //Load districts from template
        //Load facility types from template
        
        TemplateData data;
        
        CreateWatFile m = new CreateWatFile();        
        Contants cons=new Contants();
        cons.Copyright();
        cons.Statement();
        System.out.println("STARTING WISN LOADER ........");
        System.out.println();
        System.out.println("___________________GENERATING THE FACILITIES (.wat)___________________");
        System.out.println();
        m.loadingExcelFile(rp.getFacilitiesFileName()); 

        m.readOPD();
        
        int i;
        
        ReadFacilities r=new ReadFacilities(rp.getFacilitiesFileName());
        
        int size=r.getFacilities().size();

        String templateFolder=rp.getTemplateFolderName();//"templates//template_hgr.xml";
        
        for( i = 0 ; i < size ; i++){
            
            String regionName=m.getFacilities().get(i).getRegion();
            
            String institutionType=m.getFacilities().get(i).getType();
            
            String institutionName=m.getFacilities().get(i).getInstitutionName();
            
            String institutionTypeDesc=m.getFacilities().get(i).getInstitutionDescription();
            
            String file=institutionType.replace(" ", "_").toLowerCase()+".xml";//TEMPLATE FILE NAMES SHOULD MATCH THE INSTITUTION TYPE NAME (SMALL LETTERS)
            
            String templateFile=templateFolder+"//"+file;
            
            data=TemplateData.getInstance();
            
            data.setTemplateFile(templateFile);
            
            m.processFacility(i,m, institutionType,institutionTypeDesc,institutionName,templateFile,regionName);         
        }
        System.out.println("----------------------"+LogFile.getNumberFilesCreated()+" Files Created!!! ----------------------");
        LogFile.writeLog();
    } 
}
