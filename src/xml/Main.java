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

public class Main {       
    /**
     * N'OUBLIEZ PAS DE PERSONNALISER REGION ET DISTRICT DANS Template.java-(lignes 84-85) et dans XMLDocument.java-(ligne 54-56)
     * DANS LE TEMPLATE XML, METTRE 0 COMME VALUE DE L'ELEMENT "DISTRICT"; METTRE REGION A LA PLACE DE "KASAI" ET VALUE 1 A 0
     * Value dans le fichier du template doit avoir la même valeur que dans XMLDOcument.java(templateDistrictID="0")
     * @param argv
     * @throws Exception 
     */
    public static void main(String argv[]) throws Exception {
        
        CreateWatFile m = new CreateWatFile();        
        Contants cons=new Contants();
        cons.Copyright();
        cons.Statement();
        cons.NAWLlogo();
        System.out.println("STARTING DRCWL ........");
        System.out.println();
        System.out.println("___________________GENERATING THE FACILITIES (.wat)___________________");
        System.out.println();
        m.loadingExcelFile("PYRAMIDE.xls"); 
        
        m.readOPD();
        int i;        
        int length = 165;//m.getNumberOfRows();//Nombre des lignes dans le fichier PYRAMIDE.xls
//        int length = m.getNumberOfRows();
        //m.getLoop().getTickedCadresID();
        
        Template temp;
        
        String folder;
        
        String subfolder;
        
        for( i = 1 ; i < length ; i++){
            
            String regionCode = m.getFacilities()[i].getRegionCode()+"_";
            
            String regionName=m.getFacilities()[i].getRegion()+"_";
            
            folder=m.getFacilities()[i].getRegion();
            
            subfolder=m.getFacilities()[i].getDistrict();
            
            temp=new Template("REGION", "0");           
            
            String code = m.getFacilities()[i].getCode()+"_";
            
            String districtName=m.getFacilities()[i].getDistrict()+"_";
            
            String facilityName = m.getFacilities()[i].getInstitutionName().replaceAll("\\s", "_");
            
            String extention = ".wat";
//            String fileNameFormat = regionCode+code+facilityName+extention;
            String fileNameFormatHGR = facilityName+extention;
            
            String fileNameFormatCS = facilityName+extention;
            //System.out.println("facilities//"+"delete.wat");
//            if( m.getFacilities()[i].getInstitutionDescription().compareToIgnoreCase(INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDS) == 0 ){
//                m.primaryHeathCentreWithBeds( i, m , fileNameFormat );
//            }
//            else if( m.getFacilities()[i].getInstitutionDescription().compareToIgnoreCase(INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHOUTBEDS) == 0 ){
//                     m.primaryHeathCentreWithoutBeds( i, m , fileNameFormat );              
//                
//                 }
            
            if( m.getFacilities()[i].getInstitutionDescription().compareToIgnoreCase(INSTITUTIONDESCRIPTION.DISTRICTGENERALHOSPITAL) == 0 ){
                     m.traiter_HGR(i, m, fileNameFormatHGR,folder);//Provincial template[HGR]
            }
            else if( m.getFacilities()[i].getInstitutionDescription().compareToIgnoreCase(INSTITUTIONDESCRIPTION.PRIMARYHEALTHCENTREWITHBEDS) == 0 ){
                     m.traiter_CS(i, m, fileNameFormatCS,folder,subfolder);//Central template[CS]
            }           
        }
        System.out.println("----------------------"+LogFile.getNumberFilesCreated()+" Files Created!!! ----------------------");
        LogFile.writeLog();
        //System.out.println(m.getCorrectFacilities());
        //System.out.println(m);
        //m.changingDetailsPerFacility(m.getFacilities()[34]);            
        //System.out.println(m.getFacilities()[36].getDistrict());
        
    } 
}
