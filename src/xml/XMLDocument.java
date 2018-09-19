/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;
/**
 * @author Jason Ndemueda<jndemueda@intrahealth.org><ndemuedajason@gmail.com>
 * 
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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
 
public class XMLDocument {
    private Document doc;
    private String templateRegionName;
    private String templateRegionID;
    
    private String templateDistrictName;
    private String templateDistrictID;
    
    private String templateInstitutionName;
    private java.util.List<String>lstInstitutionName;
    
    private String templateInstitutionID;
    
    private String templateInstitutionType;
    private String templateInstitutionTypeID;
    
    private String templateInstitutionDescription;
    private String templateInstututionDescriptionID;
    
    public XMLDocument( Document doc){
        this.doc = doc;
        this.doc.getDocumentElement().normalize();
        templateRegionName = "REGION";
        templateRegionID = "0"; 
        
        templateDistrictName = "DISTRICT";
        templateDistrictID = "0";
    
//        templateInstitutionName = "Name Of Health Center";
        
        //templateInstitutionName="institutionName";
        
        lstInstitutionName=new ArrayList<>();
        
        lstInstitutionName.add("CS");
        lstInstitutionName.add("HGR");
        
        templateInstitutionID = INSTITUTION.ID;
    
        templateInstitutionType = INSTITUTIONTYPE.PRIMARYCAREINSTITUTION;
        templateInstitutionTypeID = INSTITUTIONTYPE.PRIMARYCAREINSTITUTIONID;
    
//        templateInstitutionDescription = INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDS;
//        templateInstututionDescriptionID = INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDSID;
    }
    
    public XMLDocument( Document doc, Template t ){
        this.doc = doc;
        this.doc.getDocumentElement().normalize();
        setTemplate(t);
    }
    
    public void changeRegion(String region, String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName() == "controlinfo" )
                if( environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent().compareToIgnoreCase(templateRegionName) == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateRegionID) == 0 ){
                        environmentChilds.item(i).getAttributes().getNamedItem("title").setTextContent(region);
                        environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(id);
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                    }
            }
    }
    
    public void changeDistrict(String district, String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        //System.out.println(environment.getNodeName());
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
        //Node controlInfo = environmentChilds.item(i);
        //if( controlInfo.getAttributes().getNamedItem("name").getNodeValue() == "Caprivi" )                        
        //System.out.println("Caprivi");
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0){
                String d=environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent();
                if( environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent().compareToIgnoreCase(templateDistrictName) == 0 ){
                    String v=environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent();
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateDistrictID) == 0 ){
                        environmentChilds.item(i).getAttributes().getNamedItem("title").setTextContent(district);//System.out.println(district);
                        environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(id);//System.out.println(id);
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                    }
                }
            
            }
        }
    }
    
    public void changeInstitutionName(String institutionName){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        //System.out.println(environment.getNodeName());
        NodeList environmentChilds = environment.getChildNodes();
       
        for(int i=0;i<environmentChilds.getLength();i++){
        //Node controlInfo = environmentChilds.item(i);
        //if( controlInfo.getAttributes().getNamedItem("name").getNodeValue() == "Caprivi" )                        
        //System.out.println("Caprivi");
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0){
//                if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateInstitutionName) == 0 )
                try{
                   if(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase("HGR")==0 || 
                           environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase("CS")==0){
                        if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(templateInstitutionID) == 0 ){
                            environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(institutionName);
                            environmentChilds.item(i).getAttributes().getNamedItem("id").setTextContent(templateInstitutionID);
                            //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent());
                        }
                    } 
                }catch(Exception ex){
                    
                }
                
            }
        }
    }
    
    public void changeInstitutionType(String institutionType, String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        //System.out.println(environment.getNodeName());
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
        //Node controlInfo = environmentChilds.item(i);
        //if( controlInfo.getAttributes().getNamedItem("name").getNodeValue() == "Caprivi" )                        
        //System.out.println("Caprivi");
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0)
                if( environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent().compareToIgnoreCase(templateInstitutionType) == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateInstitutionTypeID) == 0 ){
                        environmentChilds.item(i).getAttributes().getNamedItem("title").setTextContent(institutionType);
                        environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(id);
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                    }
        }
    }
    
    public void changeInstitutionDescription(String institutionDescription, String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        //System.out.println(environment.getNodeName());
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
        //Node controlInfo = environmentChilds.item(i);
        //if( controlInfo.getAttributes().getNamedItem("name").getNodeValue() == "Caprivi" )                        
        //System.out.println("Caprivi");
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0)
                if( environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent().compareToIgnoreCase(templateInstitutionDescription) == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateInstututionDescriptionID) == 0 ){
                        environmentChilds.item(i).getAttributes().getNamedItem("title").setTextContent(institutionDescription);
                        environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(id);
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                    }
                }
    }
 
    private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
    }

    /**
     * @return the doc
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * @param doc the doc to set
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }
    
    public void setTemplate( Template t ){
        templateRegionName = t.getTemplateRegionName();
        templateRegionID = t.getTemplateRegionID();// SAME FOR ALL
    
        templateDistrictName = t.getTemplateDistrictName();// SAME FOR ALL
        templateDistrictID = t.getTemplateDistrictID();// SAME FOR ALL
    
        templateInstitutionName = t.getTemplateInstitutionName();// DIFERENT FOR EACH
        templateInstitutionID = t.getTemplateInstitutionID();// SAME FOR ALL
    
        templateInstitutionType = t.getTemplateInstitutionType();
        templateInstitutionTypeID = t.getTemplateInstitutionTypeID();
    
        templateInstitutionDescription = t.getTemplateInstitutionDescription();
        templateInstututionDescriptionID = t.getTemplateInstututionDescriptionID();
    }
 
}
