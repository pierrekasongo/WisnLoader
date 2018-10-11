/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Jason Ndemueda<jndemueda@intrahealth.org><ndemuedajason@gmail.com>
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
public class TemplateData {
    
    private  Map<String,String>regions;
    private  Map<String,String>districts;
    private  Map<String,String>institutionTypes;
    private  Map<String,String>institutionTypeDescriptions;
    
    private String templateFile;
    
    private static TemplateData instance;
    
    public TemplateData(){        
    }
    
    public  static TemplateData getInstance(){
        if(instance == null)
            instance=new TemplateData();
        return instance;
    }
   
//    public TemplateData(){
//        this.templateFile="templates//hopital.xml";
//        this.regions=loadRegions();
//        this.districts=loadDistricts();
//        this.institutionTypes=loadInstitutionTypes();
//        this.institutionTypeDescriptions=loadInstitutionTypeDescriptions();
//    }
    
    public  void setTemplateFile(String templateFile){
        this.templateFile=templateFile;
    }
    
    private Map<String,String> loadRegions(){
        
        Map<String,String> res=new HashMap<>();
        
        try{

            File fXmlFile = new File(this.templateFile);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList elements = doc.getElementsByTagName("dictionary"); 
            
            Node regionElement=elements.item(1);//Region ID in dictionary list
             
            NodeList children = regionElement.getChildNodes();
             
            int count=children.getLength();
            
            for(int i=0;i<count;i++){
                
                if(children.item(i).getNodeName().compareToIgnoreCase("elements")==0){
                    
                    Node elt=children.item(i);
                    
                    NodeList lstelement=elt.getChildNodes();
                    
                    int size=lstelement.getLength();
                    
                    for(int j=0;j< size;j++){
                        
                        if(lstelement.item(j).getNodeName().compareToIgnoreCase("element")==0){
                            
                            String id=lstelement.item(j).getAttributes().getNamedItem("id").getTextContent();
                            
                            String name=lstelement.item(j).getAttributes().getNamedItem("name").getTextContent();
                            
                            res.put(name, id);
                            
                        }                                           
                    }
                    
                }
            }
            
        }catch (Exception e) {
		          System.err.println(e.getMessage());
	}
        return res;
    }
    
    private Map<String,String> loadDistricts(){
        
        Map<String,String>res=new HashMap<>();
        
        try{
            
            
            File fXmlFile = new File(this.templateFile);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList elements = doc.getElementsByTagName("dictionary"); 
            
            Node districtElement=elements.item(2);//District ID in dictionary list
             
            NodeList children = districtElement.getChildNodes();
             
            int count=children.getLength();
            
            for(int i=0;i<count;i++){
                
                if(children.item(i).getNodeName().compareToIgnoreCase("elements")==0){
                    
                    Node elt=children.item(i);
                    
                    NodeList lstelement=elt.getChildNodes();
                    
                    int size=lstelement.getLength();
                    
                    for(int j=0;j< size;j++){
                        
                        if(lstelement.item(j).getNodeName().compareToIgnoreCase("element")==0){
                            
                            String id=lstelement.item(j).getAttributes().getNamedItem("id").getTextContent();
                            
                            String name=lstelement.item(j).getAttributes().getNamedItem("name").getTextContent();
                            
                            res.put(name,id);
                        }                                           
                    }
                    
                }
            }
            
        }catch (Exception e) {
		          System.err.println(e.getMessage());
	}
        return res;
    }
    
    private Map<String,String> loadInstitutionTypes(){
        
        Map <String,String>res=new HashMap<>();
        
        try{
            
            
            File fXmlFile = new File(this.templateFile);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList elements = doc.getElementsByTagName("dictionary"); 
            
            Node institutionElement=elements.item(3);//Institution type ID in dictionary list
             
            NodeList children = institutionElement.getChildNodes();
             
            int count=children.getLength();
            
            for(int i=0;i<count;i++){
                
                if(children.item(i).getNodeName().compareToIgnoreCase("elements")==0){
                    
                    Node elt=children.item(i);
                    
                    NodeList lstelement=elt.getChildNodes();
                    
                    int size=lstelement.getLength();
                    
                    for(int j=0;j< size;j++){
                        
                        if(lstelement.item(j).getNodeName().compareToIgnoreCase("element")==0){
                            
                            String id=lstelement.item(j).getAttributes().getNamedItem("id").getTextContent();
                            
                            String name=lstelement.item(j).getAttributes().getNamedItem("name").getTextContent();
                            
                            res.put(name, id);
                        }                                           
                    }
                    
                }
            }
            
        }catch (Exception e) {
            System.err.println(e.getMessage());
	}
        return res;
    }
    
    private Map<String,String> loadInstitutionTypeDescriptions(){
        
        Map <String,String>res=new HashMap<>();
        
        try{
            
            
            File fXmlFile = new File(this.templateFile);
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList elements = doc.getElementsByTagName("dictionary"); 
            
            Node institutionElement=elements.item(4);//Institution type description ID in dictionary list
             
            NodeList children = institutionElement.getChildNodes();
             
            int count=children.getLength();
            
            for(int i=0;i<count;i++){
                
                if(children.item(i).getNodeName().compareToIgnoreCase("elements")==0){
                    
                    Node elt=children.item(i);
                    
                    NodeList lstelement=elt.getChildNodes();
                    
                    int size=lstelement.getLength();
                    
                    for(int j=0;j< size;j++){
                        
                        if(lstelement.item(j).getNodeName().compareToIgnoreCase("element")==0){
                            
                            String id=lstelement.item(j).getAttributes().getNamedItem("id").getTextContent();
                            
                            String name=lstelement.item(j).getAttributes().getNamedItem("name").getTextContent();
                            
                            res.put(name, id);
                        }                                           
                    }
                    
                }
            }
            
        }catch (Exception e) {
            System.err.println(e.getMessage());
	}
        return res;
    }

    /**
     * @return the regions
     */
    public  Map<String,String> getRegions() {
        return this.loadRegions();
    }  
    /**
     * @return the districts
     */
    public Map<String,String> getDistricts() {
        return this.loadDistricts();
    }

    /**
     * @return the institutionTypes
     */
    public Map<String,String> getInstitutionTypes() {
        return this.loadInstitutionTypes();
    }
    
    public Map<String,String> getInstitutionTypeDescriptions() {
        return this.loadInstitutionTypeDescriptions();
    }   
}
