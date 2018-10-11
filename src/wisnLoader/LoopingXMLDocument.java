/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;
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

import java.util.AbstractList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.List;
 
public class LoopingXMLDocument {
    private Document doc;
    private final String templateRegionName = "REGION";
    private final String templateRegionID = "0";
    private List<ActivityDetails> activities;
    private CadreDetails[] cadresDetails;
    private final int activityLength = 0;
    
    
    
    public LoopingXMLDocument( Document doc ){
        this.doc = doc;
        this.doc.getDocumentElement().normalize();
        
        activities=new ArrayList<>();
    }
    
    public void changeRegion(String region, String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent().compareToIgnoreCase(templateRegionName) == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(templateRegionID) == 0 ){
                        environmentChilds.item(i).getAttributes().getNamedItem("title").setTextContent(region);
                        environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(id);
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                    }
        }
    }
    
    //received from the template; this method also fills the cadreDetails class
    public String[] getTickedCadresIDs(){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds=null;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        //int count = 0;
        int length = Integer.parseInt(numberOfTickedCadres());
        String IDs[] = new String[length];
        cadresDetails = new CadreDetails[length];
        
        int IDsCounter = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("specialists_group") == 0 ){
                    //if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Specialists Group") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE ){
                                        //System.out.println(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent());
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) ){
                                            String id = elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent();
                                            String name = elementsChilds.item(k).getAttributes().getNamedItem("name").getTextContent();
                                            IDs[IDsCounter] = id;
                                            cadresDetails[IDsCounter] = new CadreDetails(name,id);
                                            //System.out.println(IDs[IDsCounter]);
                                            IDsCounter++;
                                            //System.out.println(IDs[IDsCounter]);                                            
                                        }
                                    }
                                            
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }        
                        }
                }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        //}
        }
        //System.out.println(""+count);
        return IDs;
    }
    
    //received from the template
    public String numberOfTickedCadres_old(){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        int count = 0;
        
        int s=environmentChilds.getLength();
                
        for(int i=0;i<environmentChilds.getLength();i++){
            
            System.out.println(environmentChilds.item(i).getNodeName());
            
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("specialists_group") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Specialists Group") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        //System.out.println(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent());
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                            count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        return ""+count;        
    }
    
    
    
    public String numberOfTickedCadres(){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds=null;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        int count = 0;
        
        int s=environmentChilds.getLength();
                
        for(int i=0;i<environmentChilds.getLength();i++){
            
//            System.out.println(environmentChilds.item(i).getNodeName());
            
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("specialists_group") == 0 ){
//                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Specialists Group") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
            
                        int dic_length=dictionaryChilds.getLength();
                        
                        for( int j=0;j < dic_length;j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                
                                int el_length=elementsChilds.getLength();
                                
                                for( int k=0;k < el_length;k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE ){
                                        //System.out.println(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent());
//                                        System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                    
                                        if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                            count++;
                                    }
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
//                        }
        }
        //System.out.println(""+count);
        return ""+count;        
    }
    
    
    
    
    //from the template
    public boolean isSpecialistTicked(String id){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("SpGr"+id) == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase("true") == 0 ){
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent()+
                          //        " - "+environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent());
                        return true;                        
                        }
        }
        return false;
    }
    
    //received from the template
    public void getAllActivitiesPerCadre(String cadreID){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("activities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Activities") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE ){
                                        elementChilds = elementsChilds.item(k).getChildNodes();
                                        for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                            if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("spec_group") == 0 )
                                                {
                                                    if( elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(cadreID) == 0 ){
                                                        String id = elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id").getTextContent();
                                                        String specGroupValue = elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent();
                                                        String name = elementChilds.item(l).getParentNode().getAttributes().getNamedItem("name").getTextContent();
                                                        getActivities().add(new ActivityDetails(name, id, specGroupValue));//insert activities sequencially because of activityLength   
                                                        //activityLength++;
                                                        
                                                    }                                                    
                                                        
                                                    
                                                }
                                                    
                                            }
                                            
                                        }
                                        
                                    }
                                        
                                    
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        //return ""+count;        
    }
    //received from the template
    public void getAllActivitiesAllCadres(){
        
        String[] cadreIDs = getTickedCadresIDs();
        
        for(int i = 0; i < cadreIDs.length; i++){
            getAllActivitiesPerCadre(cadreIDs[i]);
//            System.out.println(activities[i].getName());
//            System.out.println(cadreIDs[i]);
        }
//        for(int i=0;i < activities.length;i++){
//            System.out.println(activities[i].getName());
//        }
            
    }
    
    public void changeActivitiesValues(String actID, String value){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        String ActID = "Act"+actID; 
        int length=environmentChilds.getLength();
        //i=313
        for(int i=0;i<length;i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0 ){
                
//                System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent());
                
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(ActID) == 0 ){                        
                    //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent());
                    environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(value);
                    environmentChilds.item(i).getAttributes().getNamedItem("id").setTextContent(ActID);
                }
            }
        }
    }
    
    public void changeExistingStaffValues(String cadreID, String value){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        String ESID = "ES"+cadreID; 
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(ESID) == 0 ){                        
                    //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent());
                    environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(value);
                    environmentChilds.item(i).getAttributes().getNamedItem("id").setTextContent(ESID);
                }
        }
    }
    
    public void changeActivitiesStandard(String asID, String value){
        NodeList nList = doc.getElementsByTagName("environment");
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        String ASID = "AS"+asID; 
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("controlinfo") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(ASID) == 0 ){                        
                    //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("value").getTextContent());
                    environmentChilds.item(i).getAttributes().getNamedItem("value").setTextContent(value);
                    environmentChilds.item(i).getAttributes().getNamedItem("id").setTextContent(ASID);
                }
        }
    }
    //SUPPORTING METHOD
    public String searchCadreID(String cadreName){
        for( int i=0;i<cadresDetails.length;i++ )
            if( cadresDetails[i].getName().trim().compareToIgnoreCase(cadreName) == 0 )
                return cadresDetails[i].getId();
        return "-1";
        
    }
    //SUPPORTING METHOD
    public String searchActivityID(String atcName, String cadreID){
        
        for( int i=0;i< activities.size(); i++){
            if( activities.get(i) != null )
            if( activities.get(i).getName().trim().compareToIgnoreCase(atcName.trim()) == 0) 
                    if(activities.get(i).getSpecGroupValue().compareToIgnoreCase(cadreID) == 0)
                        return activities.get(i).getId();
        }
        return "-1";             
        
    }
    
    public void changeActivity( String cadreName, String activityName, String value ){
        /*REPORT TO LOG FILE THAT CADRE DOES NOT EXIST OR IS NOT TICKED*/
        String cadreID = searchCadreID( cadreName.trim() );
        String actID = searchActivityID(activityName.trim(), cadreID); 
        
        List<Element>lst=new ArrayList<>();
        
        if(cadreID.equals("-1")){
            System.out.println("CADRE,"+cadreName);
        }
        if(actID.equals("-1")){
            System.out.println("ACTIVITY["+cadreName+"]==>"+activityName);
        }
        //System.out.println(actID);
        try{
            changeActivitiesValues(actID, value.trim());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public boolean isActivityListed( String cadreName, String activityName ){
        String cadreID = searchCadreID( cadreName.trim() );
        String actID = searchActivityID(activityName.trim(), cadreID);
        
         if(cadreID.equals("-1")){
            System.out.println("CADRE,"+cadreName);
        }
        if(actID.equals("-1")){
            System.out.println("ACTIVITY["+cadreName+"]==>"+activityName);
        }
        
        if( cadreID.compareToIgnoreCase("-1") == 0)
            return false;        
        if( actID.compareToIgnoreCase("-1") == 0)
            return false;
        return true;
        
    }
    
    public boolean isCadreListed( String cadreName ){
        String cadreID = searchCadreID( cadreName.trim() );
        if( cadreID.compareToIgnoreCase("-1") == 0)
            return false;  
        return true;        
    }
    
    public void changeExistingStaff(String cadreName, String value){
        String cadreID = searchCadreID( cadreName.trim() );
        changeExistingStaffValues( cadreID, value );
    }
    
    public void changeActivityStandard( String cadreName, String activityName, String value ){        
        String cadreID = searchCadreID( cadreName.trim() );
        String actID = searchActivityID(activityName.trim(), cadreID); 
        //System.out.println(actID);
        changeActivitiesStandard(actID, value);
    }    
    
    public void changeCategoryAllowance(String cadreName, String customActivity, String value){
        String cadreID = searchCadreID( cadreName );
        //System.out.println(cadreID);        
        String customActivityID = getCustomActivityID(cadreID, customActivity);
        
        changeCustomActivity(customActivityID, value);
    }
    public void changeIndividualAllowance(String cadreName, String individualActivity, String noOfStaffAllocated, String value){
        String cadreID = searchCadreID( cadreName );
        //System.out.println(cadreID);        
        String customActivityID = getIndividualActivityID(cadreID, individualActivity);
        
        changeIndividualActivity(customActivityID, noOfStaffAllocated,value);
    }
    
    public void changeIndividualActivity(String individualActivityID, String noOfStaffAllocated, String value){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("customActivities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Custom Actions") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        if( elementsChilds.item(k).getNodeName().compareToIgnoreCase("element") == 0 )//IDENT THE CODE BELOW
                                            if( elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(individualActivityID) == 0){                                        
                                        elementChilds = elementsChilds.item(k).getChildNodes();
                                        for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                            if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("value") == 0 ) 
                                                    elementChilds.item(l).getAttributes().getNamedItem("value").setTextContent(value);
                                                else if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("noOfStaffAllocated") == 0 )
                                                    elementChilds.item(l).getAttributes().getNamedItem("value").setTextContent(noOfStaffAllocated);      
                                            }
                                        }  
                                    }
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        //return ""+count;        
    }
    
    public void changeCustomActivity(String customActivityID, String value){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("customActivities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Custom Actions") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        if( elementsChilds.item(k).getNodeName().compareToIgnoreCase("element") == 0 )//IDENT THE CODE BELOW
                                            if( elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(customActivityID) == 0){                                        
                                        elementChilds = elementsChilds.item(k).getChildNodes();
                                        for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                            if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("value") == 0 )
                                                {
                                                    //System.out.println(elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent()); 
                                                    elementChilds.item(l).getAttributes().getNamedItem("value").setTextContent(value);
                                                    
                                                }
                                                    
                                            }
                                            
                                        }
                                        
                                    }
                                        
                                    
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        //return ""+count;        
    }
    
    public String getCustomActivityID(String cadreID, String customActivity){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("customActivities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Custom Actions") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        if( elementsChilds.item(k).getNodeName().compareToIgnoreCase("element") == 0 )
                                            if(elementsChilds.item(k).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase(customActivity)==0){                                        
                                                elementChilds = elementsChilds.item(k).getChildNodes();
                                                for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                                    if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                        if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("staff") == 0 )
                                                            if( elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(cadreID) == 0 )
                                                            {
                                                                //System.out.println(elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id"));  
                                                                return elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id").getTextContent();
                                                    
                                                            }
                                                    
                                                    }
                                            
                                                }
                                        
                                            }
                                        
                                    
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        return "-1";        
    }
    
    public String getIndividualActivityID(String cadreID, String individualActivity){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("customActivities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Custom Actions") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        if( elementsChilds.item(k).getNodeName().compareToIgnoreCase("element") == 0 )
                                            if(elementsChilds.item(k).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase(individualActivity)==0){                                        
                                                elementChilds = elementsChilds.item(k).getChildNodes();
                                                for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                                    if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                        if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("staff") == 0 )
                                                            if( elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase(cadreID) == 0 )
                                                                if(isIndividualActivity(elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id").getTextContent())){//checks if isRelated value = false
                                                                
                                                                //System.out.println(elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id"));  
                                                                return elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id").getTextContent();
                                                    
                                                            }
                                                    
                                                    }
                                            
                                                }
                                        
                                            }
                                        
                                    
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        return "-1";        
    }
    
    public boolean isIndividualActivity(String individualActivityID){
        NodeList nList = doc.getElementsByTagName("dictionaries");
        NodeList dictionaryChilds;
        NodeList elementsChilds;
        Node environment = nList.item(0);
        NodeList environmentChilds = environment.getChildNodes();
        NodeList elementChilds;
        int count = 0;
                
        for(int i=0;i<environmentChilds.getLength();i++){
            if( environmentChilds.item(i).getNodeName().compareToIgnoreCase("dictionary") == 0 )
                if( environmentChilds.item(i).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase("customActivities") == 0 )
                    if( environmentChilds.item(i).getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase("Custom Actions") == 0 ){
                        dictionaryChilds = environmentChilds.item(i).getChildNodes();
                        for( int j=0;j<dictionaryChilds.getLength();j++ ){
                            if( dictionaryChilds.item(j).getNodeName().compareToIgnoreCase("elements") == 0 ){
                                //System.out.println(dictionaryChilds.item(j).getAttributes().getNamedItem("name").getTextContent());
                                elementsChilds = dictionaryChilds.item(j).getChildNodes();
                                for( int k=0;k<elementsChilds.getLength();k++ ){
                                    if( elementsChilds.item(k).getNodeType() == Node.ELEMENT_NODE )
                                        if( elementsChilds.item(k).getNodeName().compareToIgnoreCase("element") == 0 )
                                            if(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent().compareToIgnoreCase(individualActivityID)==0){                                        
                                                elementChilds = elementsChilds.item(k).getChildNodes();
                                                for( int l=0 ; l<elementChilds.getLength(); l++ ){
                                                    if( elementChilds.item(l).getNodeType() == Node.ELEMENT_NODE ){
                                                        if( elementChilds.item(l).getAttributes().getNamedItem("key").getTextContent().compareToIgnoreCase("isRelated") == 0 )
                                                            if( elementChilds.item(l).getAttributes().getNamedItem("value").getTextContent().compareToIgnoreCase("false") == 0 ){
                                                                
                                                                //System.out.println(elementChilds.item(l).getParentNode().getAttributes().getNamedItem("id"));  
                                                                return true;
                                                    
                                                            }
                                                    
                                                    }
                                            
                                                }
                                        
                                            }
                                        
                                    
                                        //System.out.println(isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()));
                                        //if( isSpecialistTicked(elementsChilds.item(k).getAttributes().getNamedItem("id").getTextContent()) )
                                          //  count++;
                                    //if( elementsChilds.item(k).getAttributes().getNamedItem("id") )
                                }
                            }
                                
                        }
                        
                        //System.out.println(dictionaryChilds.item(i).getNodeName());
                        //System.out.println(environmentChilds.item(i).getAttributes().getNamedItem("title").getTextContent());
                        }
        }
        //System.out.println(""+count);
        return false;        
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

    /**
     * @return the activities
     */
    public List<ActivityDetails> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<ActivityDetails> activities) {
        this.activities = activities;
    }

    /**
     * @return the cadresDetails
     */
    public CadreDetails[] getCadresDetails() {
        return cadresDetails;
    }

    /**
     * @param cadresDetails the cadresDetails to set
     */
    public void setCadresDetails(CadreDetails[] cadresDetails) {
        this.cadresDetails = cadresDetails;
    }
    
    
 
}
