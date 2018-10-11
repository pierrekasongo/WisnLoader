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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadFacilities {

  private String inputFile;
  private File inputWorkbook;
  private Workbook w;
  private Sheet sheet;
  private Cell cell;
  private int rowLength;
  
  private int countRows;
  
  public ReadFacilities(String inputFile){
      this.inputFile=inputFile;
  } 

  
  public List<Facility> getFacilities() throws IOException  {
      
    Workbook workbook;
    List<Facility>lstRes = null; 
    
      try {
          
          lstRes=new ArrayList<>();
          
          workbook = Workbook.getWorkbook(new File(this.inputFile));
          
          Sheet sheet=workbook.getSheet(0);
          
          this.countRows=sheet.getRows();
          
          for(int i =1;i < this.countRows;i++){
                
                String  type=sheet.getCell(0,i).getContents();
                
                String name=sheet.getCell(1,i).getContents();
                
                String code=sheet.getCell(2,i).getContents();
                
                String district=sheet.getCell(3,i).getContents();
                
                String region=sheet.getCell(4,i).getContents();
                
                String desc=sheet.getCell(5,i).getContents();
                
                String sector=sheet.getCell(6,i).getContents();
                
                //System.err.println("Ligne "+type+","+name+","+code+","+district+","+region);
                
                Facility f=new Facility(type,name,code,district,region,desc,sector);
                
                lstRes.add(f);
           }
          
      } catch (BiffException ex) {
          Logger.getLogger(ReadFacilities.class.getName()).log(Level.SEVERE, null, ex);
      }

    return lstRes;
  }

  public static void main(String[] args) throws IOException {
      ReadFacilities r=new ReadFacilities("PYRAMIDE.xls");
      for(Facility f:r.getFacilities()){
          System.out.println("Facility "+f.getInstitutionName()+" "+f.getType());
      }
  }

    /**
     * @return the rowLength
     */
    public int countRows() {
        return countRows;
    }

} 
