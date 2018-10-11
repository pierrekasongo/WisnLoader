/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadActivities {

  private String inputFile;
  private File inputWorkbook;
  private Workbook w;
  private Sheet sheet;
  private Cell cell;
  private int rowLength = -1;
  private int countRows;
  
  public ReadActivities(String inputFile){
      this.inputFile=inputFile;
  } 
  public List<Activity> getActivities() throws IOException  {

    List<Activity>lstRes = null; 

    inputWorkbook = new File(inputFile);
    
    Workbook workbook;
      
    try {
          
          lstRes=new ArrayList<>();
          
          workbook = Workbook.getWorkbook(new File(this.inputFile));
          
          Sheet sheet=workbook.getSheet(0);
          
          this.countRows=sheet.getRows();
          
          for(int i =1;i < this.countRows;i++){
                
                String  region=sheet.getCell(0,1).getContents();
                
                String district=sheet.getCell(1,i).getContents();
                
                String facility=sheet.getCell(2,i).getContents();
                
                String activity=sheet.getCell(3,i).getContents();
                
                String value=sheet.getCell(4,i).getContents();
                
                String cadre=sheet.getCell(5,i).getContents();
                
                Activity a=new Activity(region, district, facility, activity, value, cadre);
                
                lstRes.add(a);
           }
    } catch (BiffException e) {
      e.printStackTrace();
      return null;
    }
    return lstRes;
  }

  public static void main(String[] args) throws IOException {
      
    ReadProperties p=new ReadProperties();
      
    ReadActivities test = new ReadActivities(p.getActivitiesFileName());
    
    List<Activity>act=test.getActivities();
    
    for(Activity a:act){
        
        System.out.println(a.getRegion()+" "+a.getDistrict()+" "+a.getInstitutionName()+" "+a.getValue()+" "+a.getCadre());
    }
  }

   public int countRows() {
        return countRows;
    }

} 
