/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


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

public class Constants{    
    /**
     *
     * @param region
     * @return
     */
    public String getRegionID(String region){
        if(region.compareToIgnoreCase("Kinshasa") == 0)
                return ""+REGION.KINSHASAID;
        else if(region.compareToIgnoreCase("Nord-Ubangi") == 0)
            return ""+REGION.NORDUBANGIID;
        else if(region.compareToIgnoreCase("Kasai") == 0)
            return ""+REGION.KASAIID;
        else if(region.compareToIgnoreCase("Kasai-Central") == 0)
            return ""+REGION.KASAICENTRALID;
        else if(region.compareToIgnoreCase("Maniema")==0)
            return ""+REGION.MANIEMAID;
        return "-1";
    }
    
    public String getDistrictID(String district){        
        try{
            String fileName = "zone_sante.xml";
		File fXmlFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
                NodeList elements = doc.getElementsByTagName("element");                
                
                for(int i=0;i<elements.getLength();i++){
                    Node element = elements.item(i);
                    if (element.getNodeType() == Node.ELEMENT_NODE) {
                        
                        if( element.getAttributes().getNamedItem("name").getTextContent().compareToIgnoreCase(district) == 0 ){                            
                            return element.getAttributes().getNamedItem("id").getTextContent();                            
                        }
                    }
                }
                return "-1";
            
        }catch (Exception e) {
		e.printStackTrace();
                return "-1";
	}
        
    }
    
    public String getInstitutionTypeID(String institutionType){
        
        
        if(institutionType.compareToIgnoreCase("Soins de sante primaires") == 0)
                return ""+INSTITUTIONTYPE.PRIMARYCAREINSTITUTIONID;
        
        if(institutionType.compareToIgnoreCase("Hopital") == 0)
                return ""+INSTITUTIONTYPE.HOSPITALBEDDEDINSTITUTIONID;
        
        return "-1";
    }
    
    public String getInstitutionDescriptionID(String institutionDescription){
    
        
        if(institutionDescription.compareToIgnoreCase("Soins de sante primaire") == 0)
                return ""+INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDSID;
        
        if(institutionDescription.compareToIgnoreCase("Hopital general") == 0)
                return ""+INSTITUTIONDESCRIPTION.DISTRICTGENERALHOSPITALID;
        
//        if(institutionDescription.compareToIgnoreCase("District (general) hospital") == 0)
//                return ""+INSTITUTIONDESCRIPTION.DISTRICTGENERALHOSPITALID;
//        
//        if(institutionDescription.compareToIgnoreCase("Specialty hospital") == 0)
//                return ""+INSTITUTIONDESCRIPTION.SPECIALTYHOSPITALID;
//        
//        if(institutionDescription.compareToIgnoreCase("Other") == 0)
//                return ""+INSTITUTIONDESCRIPTION.OTHERID;
//        
//        if(institutionDescription.compareToIgnoreCase("Soins de santé primaires - avec des lits") == 0)
//                return ""+INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDSID;
//        
//        if(institutionDescription.compareToIgnoreCase("Primary health centre without beds") == 0)
//                return ""+INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHOUTBEDSID;
//        
//        if(institutionDescription.compareToIgnoreCase("General family practice") == 0)
//                return ""+INSTITUTIONDESCRIPTION.GENERALFAMILYPRACTICEID;
//        
//        if(institutionDescription.compareToIgnoreCase("Dental practice") == 0)
//                return ""+INSTITUTIONDESCRIPTION.DENTALPRACTICEID;       
//        
//        if(institutionDescription.compareToIgnoreCase("Pharmacy") == 0)
//                return ""+INSTITUTIONDESCRIPTION.PHARMACYID;
//        
//        if(institutionDescription.compareToIgnoreCase("Independent laboratory") == 0)
//                return ""+INSTITUTIONDESCRIPTION.INDEPENDENTLABORATORYID;
//        
//        if(institutionDescription.compareToIgnoreCase("Other") == 0)
//                return ""+INSTITUTIONDESCRIPTION.OTHER11ID;
        
        return "-1";
    }
}
interface REGION{
    public final static String KINSHASA = "Kinshasa";
    public final static String KINSHASAID = "4";
    
    public final static String NORDUBANGI="Nord-Ubangi";
    public final static String NORDUBANGIID="6";
    
    public final static String KASAICENTRAL="Kasai-Central";
    public final static String KASAICENTRALID="2";
    
    public final static String KASAI="Kasai";
    public final static String KASAIID="1";
    
    public final static String MANIEMA="Maniema";
    public final static String MANIEMAID="5";
}

interface DISTRICT{
    //****************KINSHASA**************//
    public final static String MATETE = "MATETE";
    public final static String MATETEID = "17";
    
    public final static String KIKIMI = "KIKIMI";
    public final static String KIKIMIID = "14";
    
    public final static String NGABA = "NGABA";
    public final static String NGABAID = "19";
    
    public final static String KASAVUBU = "KASA_VUBU";
    public final static String KASAVUBUID = "13";
    
    public final static String KISENSO = "KISENSO";
    public final static String KISENSOID = "16";
    
    public final static String BUMBU = "BUMBU";
    public final static String BUMBUID = "12";
    
    public final static String KITAMBO = "KINGASANI";
    public final static String KITAMBOID = "15";
    
    public final static String NDJILI = "N DJILI";
    public final static String NDJILIID = "18";
    
    //****************KASAI-CENTRAL**************//
    public final static String KANANGA = "KANANGA";
    public final static String KANANGAID = "20";
    public final static String DEMBA = "DEMBA";
    public final static String DEMBAID = "5";
    public final static String MIKALAYI = "MIKALAYI";
    public final static String MIKALAYIID = "9";
    public final static String TSHIKAJI = "TSHIKAJI";
    public final static String TSHIKAJIID = "4";
    public final static String NDESHA = "NDESHA";
    public final static String NDESHAID = "5";
    public final static String KATOKA = "KATOKA";
    public final static String KATOKAID = "6";
    public final static String LUKONGA = "LUKONGA";
    public final static String LUKONGAID = "8";
    
    //****************KASAI********************//
    public final static String KITANGUA = "KITANGUA";
    public final static String KITANGUAID = "1";
    
    public final static String KANZALA = "KANZALA";
    public final static String KANZALAID = "2";
    
    public final static String KALONDA_OUEST = "KALONDA_OUEST";
    public final static String KALONDA_OUESTID = "3";
    
    public final static String TSHIKAPA = "TSHIKAPA";
    public final static String TSHIKAPAID = "4";
    
    
    
    //****************NORD-UBANGI****************//
    public final static String ABUZI="ABUZI";
    public final static String ABUZIID="27";
    
    public final static String BILI="BILI";
    public final static String BILIID="28";
    
    public final static String BOSOBOLO="BOSOBOLO";
    public final static String BOSOBOLOID="29";
    
    public final static String BUSINGA="BUSINGA";
    public final static String BUSINGAID="30";
    
    public final static String GBADOLITE="GBADOLITE";
    public final static String GBADOLITEID="31";
    
    public final static String KARAWA="KARAWA";
    public final static String KARAWAID="32";
    
    public final static String LOKO="LOKO";
    public final static String LOKOID="33";
    
    public final static String MOBAYIMBONGO="MOBAYI_MBONGO";
    public final static String MOBAYIMBONGOID="34";
    
    public final static String WAPINDA="WAPINDA";
    public final static String WAPINDAID="35";
    
    public final static String WASOLO="WASOLO";
    public final static String WASOLOID="10";
    
    public final static String YAKOMA="YAKOMA";
    public final static String YAKOMAID="36";
    
     //****************MANIEMA****************//
    public final static String ALUNGULI="ALUNGULI";
    public final static String ALUNGULIID="21";
    
    public final static String KAILO="KAILO";
    public final static String KAILOID="22";
    
    public final static String KALIMA ="KALIMA";
    public final static String KALIMAID="23";
    
    public final static String KASONGO="KASONGO";
    public final static String KASONGOID="24";
    
    public final static String KINDU="KINDU";
    public final static String KINDUID="25";
    
    public final static String SARAMABILA="SARAMABILA";
    public final static String SARAMABILAID="26";
    
}

interface INSTITUTION{
    public final static String ID = "institutionName";
}

interface INSTITUTIONTYPE{
    
    public final static String HOSPITALBEDDEDINSTITUTION = "Hopital";
    public final static String HOSPITALBEDDEDINSTITUTIONID = "0";
    
    public final static String PRIMARYCAREINSTITUTION = "Centre";
    public final static String PRIMARYCAREINSTITUTIONID = "1";
}

interface INSTITUTIONDESCRIPTION{
//    public final static String CENTRALREGIONATERTIARYHOSPITAL = "CS PMA";
//    public final static String CENTRALREGIONATERTIARYHOSPITALID = "0";
//    
//    public final static String DISTRICTGENERALHOSPITAL = "Provincial (general) hospital";
//    public final static String DISTRICTGENERALHOSPITALID = "1";
    
//    public final static String DISTRICTGENERALHOSPITAL = "Hôpital général de référence";
//    public final static String DISTRICTGENERALHOSPITALID = "2";
    
    public final static String DISTRICTGENERALHOSPITAL = "Hopital general";
    public final static String DISTRICTGENERALHOSPITALID = "2";
    
    public final static String PRIMARYHEALTHCENTREWITHBEDS = "Soins de sante primaire";
    public final static String PRIMARYHEALTHCENTREWITHBEDSID = "5";
//    
//    public final static String SPECIALTYHOSPITAL = "Specialty hospital";
//    public final static String SPECIALTYHOSPITALID = "3";
//    
//    public final static String OTHER = "Other";
//    public final static String OTHERID = "4";
//    
    
//    
//    public final static String PRIMARYHEALTHCENTREWITHOUTBEDS = "Primary health centre without beds";
//    public final static String PRIMARYHEALTHCENTREWITHOUTBEDSID = "6";
//    
//    public final static String GENERALFAMILYPRACTICE = "General family practice";
//    public final static String GENERALFAMILYPRACTICEID = "7";
//    
//    public final static String DENTALPRACTICE = "Dental practice";
//    public final static String DENTALPRACTICEID = "8";
//    
//    public final static String PHARMACY = "Pharmacy";
//    public final static String PHARMACYID = "9";
//    
//    public final static String INDEPENDENTLABORATORY = "Independent laboratory";
//    public final static String INDEPENDENTLABORATORYID = "10";
//    
//    public final static String OTHER11 = "Other";
//    public final static String OTHER11ID = "11";
    
}
