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

public class Template{
    private static String templateName;
    private static String templateRegionName;
    private static String templateRegionID;
    
    private static String templateDistrictName;
    private static String templateDistrictID;
    
    private static String templateInstitutionName;
    private static String templateInstitutionID;
    
    private static String templateInstitutionType;
    private static String templateInstitutionTypeID;
    
    private static String templateInstitutionDescription;
    private static String templateInstututionDescriptionID;
    
    
    private static String regionname;
    private static String regionID;

    /**
     * @return the regionname
     */
    public static String getRegionname() {
        return regionname;
    }

    /**
     * @param aRegionname the regionname to set
     */
    public static void setRegionname(String aRegionname) {
        regionname = aRegionname;
    }

    /**
     * @return the regionID
     */
    public static String getRegionID() {
        return regionID;
    }

    /**
     * @param aRegionID the regionID to set
     */
    public static void setRegionID(String aRegionID) {
        regionID = aRegionID;
    }
    
    public Template(String regionName,String institutionTypeDescription,String institutionType){
        
        this.templateName = institutionTypeDescription;
        
        setFacility(regionName,institutionTypeDescription,institutionType);
    }
    
//    public Template(String _regionname,String _regionID){
//        regionname=_regionname;
//        regionID=_regionID;
//    }
    
    public void setFacility(String regionName,String institutionTypeDescription, String institutionType){
        
        TemplateData data=TemplateData.getInstance();
        
        String regionID="";
        
        setTemplateRegionName(regionName);
        
        if(data.getRegions().containsKey(regionName))  
            regionID= data.getRegions().get(regionName);
        
        setTemplateRegionID(regionID);
        
        setTemplateDistrictName("DISTRICT");
        setTemplateDistrictID("0");
    
        setTemplateInstitutionName("Name Of the institution");
        setTemplateInstitutionID("institutionName");
    
        setTemplateInstitutionType(institutionType);
        //Get institutionType ID
        String instTypeID="";
        if(data.getInstitutionTypes().containsKey(institutionType))  
            instTypeID= data.getInstitutionTypes().get(institutionType);
        setTemplateInstitutionTypeID(instTypeID);
        
        setTemplateInstitutionDescription(institutionTypeDescription);
        
        String instTypeDescID="";
        if(data.getInstitutionTypeDescriptions().containsKey(institutionTypeDescription))  
            instTypeDescID= data.getInstitutionTypeDescriptions().get(institutionTypeDescription);
        setTemplateInstututionDescriptionID(instTypeDescID);
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the templateRegionName
     */
    public String getTemplateRegionName() {
        return "REGION";
    }

    /**
     * @param templateRegionName the templateRegionName to set
     */
    public void setTemplateRegionName(String templateRegionName) {
        this.templateRegionName = templateRegionName;
    }

    /**
     * @return the templateRegionID
     */
    public String getTemplateRegionID() {
        return "0";
    }

    /**
     * @param templateRegionID the templateRegionID to set
     */
    public void setTemplateRegionID(String templateRegionID) {
        this.templateRegionID = templateRegionID;
    }

    /**
     * @return the templateDistrictName
     */
    public String getTemplateDistrictName() {
        return "DISTRICT";
    }

    /**
     * @param templateDistrictName the templateDistrictName to set
     */
    public void setTemplateDistrictName(String templateDistrictName) {
        this.templateDistrictName = templateDistrictName;
    }

    /**
     * @return the templateDistrictID
     */
    public String getTemplateDistrictID() {
        return "0";
    }

    /**
     * @param templateDistrictID the templateDistrictID to set
     */
    public void setTemplateDistrictID(String templateDistrictID) {
        this.templateDistrictID = templateDistrictID;
    }

    /**
     * @return the templateInstitutionName
     */
    public String getTemplateInstitutionName() {
        return templateInstitutionName;
    }

    /**
     * @param templateInstitutionName the templateInstitutionName to set
     */
    public void setTemplateInstitutionName(String templateInstitutionName) {
        this.templateInstitutionName = templateInstitutionName;
    }

    /**
     * @return the templateInstitutionID
     */
    public String getTemplateInstitutionID() {
        return templateInstitutionID;
    }

    /**
     * @param templateInstitutionID the templateInstitutionID to set
     */
    public void setTemplateInstitutionID(String templateInstitutionID) {
        this.templateInstitutionID = templateInstitutionID;
    }

    /**
     * @return the templateInstitutionType
     */
    public String getTemplateInstitutionType() {
        return templateInstitutionType;
    }

    /**
     * @param templateInstitutionType the templateInstitutionType to set
     */
    public void setTemplateInstitutionType(String templateInstitutionType) {
        this.templateInstitutionType = templateInstitutionType;
    }

    /**
     * @return the templateInstitutionTypeID
     */
    public String getTemplateInstitutionTypeID() {
        return templateInstitutionTypeID;
    }

    /**
     * @param templateInstitutionTypeID the templateInstitutionTypeID to set
     */
    public void setTemplateInstitutionTypeID(String templateInstitutionTypeID) {
        this.templateInstitutionTypeID = templateInstitutionTypeID;
    }

    /**
     * @return the templateInstitutionDescription
     */
    public String getTemplateInstitutionDescription() {
        return templateInstitutionDescription;
    }

    /**
     * @param templateInstitutionDescription the templateInstitutionDescription to set
     */
    public void setTemplateInstitutionDescription(String templateInstitutionDescription) {
        this.templateInstitutionDescription = templateInstitutionDescription;
    }

    /**
     * @return the templateInstututionDescriptionID
     */
    public String getTemplateInstututionDescriptionID() {
        return templateInstututionDescriptionID;
    }

    /**
     * @param templateInstututionDescriptionID the templateInstututionDescriptionID to set
     */
    public void setTemplateInstututionDescriptionID(String templateInstututionDescriptionID) {
        this.templateInstututionDescriptionID = templateInstututionDescriptionID;
    }
    
}
