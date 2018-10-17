/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;

import gui.FrmMain;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
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
//From: http://www.javacreed.com/swing-worker-example/

public class MyWorker extends SwingWorker<Integer, String>{
    
    private JTextArea logScreen;
    
    private PropertyReader rp;
    
    private WatFileCreator m;
   
    
    public MyWorker(final JTextArea _logScreen,PropertyReader _rp,WatFileCreator _m){
        this.logScreen=_logScreen;
        this.rp=_rp;
        this.m=_m;
    }
    
    @Override
    protected Integer doInBackground() throws Exception {
        
        publish("Processing started");
        // Simulate doing something useful.
//        int size=50;
//        
//        int sum=0;
//        
//        for (int i = 0; i <= size; i++) {
//            
//            Thread.sleep(100);
//            
//            setProgress((i + 1) * 100 / size);
//            
//            publish("Processing line "+(i+1));
//            
//            sum+=i;
//            
//        }
//        return sum;
        int i;
        
        int count=0;
        
        ReadFacilities r=new ReadFacilities(rp.getFacilitiesFileName());
        
        int size = 0;
        try {
            size = r.getFacilities().size();
        } catch (IOException ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        String templateFolder=rp.getTemplateFolderName();//"templates//template_hgr.xml";
        
        TemplateData data;
        
        for( i = 0 ; i < size ; i++){
            
            count++;
            
            setProgress((i + 1) * 100 / size);
            
            String regionName=m.getFacilities().get(i).getRegion();
            
            String institutionType=m.getFacilities().get(i).getType();
            
            String institutionName=m.getFacilities().get(i).getInstitutionName();
            
            String institutionTypeDesc=m.getFacilities().get(i).getInstitutionDescription();
            
            String file=institutionType.replace(" ", "_").toLowerCase()+".xml";//TEMPLATE FILE NAMES SHOULD MATCH THE INSTITUTION TYPE NAME (SMALL LETTERS)
            
            String templateFile=templateFolder+ConstantValues.getFileSeparator()+file;
            
            data=TemplateData.getInstance();
            
            data.setTemplateFile(templateFile);
            
            publish(count+" "+regionName+" "+institutionName+" "+institutionType);
            
            m.processFacility(i,m, institutionType,institutionTypeDesc,institutionName,templateFile,regionName);         
        }
        System.out.println("----------------------"+LogFile.getNumberFilesCreated()+" Files Created!!! ----------------------");
        try {
            LogFile.writeLog();
            
        } catch (Exception ex) {
            Logger.getLogger(FrmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    @Override
    protected void process(final List<String>chunks){
        for (final String string : chunks) {
            logScreen.append(string);
            logScreen.append("\n");
        }
    }
    
}
