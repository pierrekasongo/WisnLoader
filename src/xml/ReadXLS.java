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
import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadXLS {

  private String inputFile;
  private File inputWorkbook;
  private Workbook w;
  private Sheet sheet;
  private Cell cell;
  private int rowLength;
  
  
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }  

  public void read() throws IOException  {
    File inputWbook = new File(inputFile);
    try {
      Workbook wb = Workbook.getWorkbook(inputWbook);
      // Get the first sheet
      Sheet shet = wb.getSheet(0);
      // Loop over first 10 column and lines
      
      for (int j = 0; j < shet.getColumns(); j++) {
        for (int i = 0; i < shet.getRows(); i++) {
          Cell cel = shet.getCell(j, i);
          CellType type = cel.getType();
          if (type == CellType.LABEL) {
            //System.out.println("I got a label "          + cell.getContents());
            if( j == 0 ) {
                  setRowLength(getRowLength() + 1);
              }
          }

          if (type == CellType.NUMBER) {
           // System.out.println("I got a number "             + cell.getContents());
            if( j == 0 ) {
                  setRowLength(getRowLength() + 1);
              }
          }

        }
      }
    } catch (BiffException e) {
    }
  }
  
  public Facility[] getFacilities() throws IOException  {
    read();
    int length = getRowLength();
    Facility[] f = new Facility[length];
    for( int i=0; i<length; i++ ) {
          f[i] = new Facility();
      }
    inputWorkbook = new File(inputFile);
     
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      sheet = w.getSheet(0);
      // Loop over first 10 column and lines

      for (int j = 0; j < sheet.getColumns(); j++) {
        for (int i = 0; i < sheet.getRows(); i++) {
          cell = sheet.getCell(j, i);
          CellType type = cell.getType();
          //System.out.println("i = "+i+" j = "+j);
          if (type == CellType.LABEL) {
            if( j == 0 && i < length) {
                  f[i].setType(cell.getContents());
              }
            if( j == 1 && i < length) {
                  f[i].setInstitutionName(cell.getContents());
              }
            if( j == 2 && i < length) {
                  f[i].setCode(cell.getContents());
              }
            if( j == 3 && i < length) {
                  f[i].setDistrict(cell.getContents().replaceAll("\\n",""));
              }
            if( j == 4 && i < length) {
                  f[i].setRegion(cell.getContents());
              }
            if( j == 5 && i < length) {
                  f[i].setInstitutionType(cell.getContents());
              }
            if( j == 6 && i < length) {
                  f[i].setInstitutionDescription(cell.getContents());
              }
            if( j == 7 && i < length) {
                  f[i].setSector(cell.getContents());
              }
            if( j == 8 && i < length) {
                  f[i].setRegionCode(cell.getContents());
              }            
          }

          if (type == CellType.NUMBER) {
            if( j == 0 && i < length) {
                  f[i].setType(cell.getContents());
              }
            if( j == 1 && i < length) {
                  f[i].setInstitutionName(cell.getContents());
              }
            if( j == 2 && i < length) {
                  f[i].setCode(cell.getContents());
              }
            if( j == 3 && i < length) {
                  f[i].setDistrict(cell.getContents());
              }
            if( j == 4 && i < length) {
                  f[i].setRegion(cell.getContents());
              }
            if( j == 5 && i < length) {
                  f[i].setInstitutionType(cell.getContents());
              }
            if( j == 6 && i < length) {
                  f[i].setInstitutionDescription(cell.getContents());
              }
            if( j == 7 && i < length) {
                  f[i].setSector(cell.getContents());
              }
            if( j == 8 && i < length) {
                  f[i].setRegionCode(cell.getContents());
              }
          }

        }
      }
      return f;
    } catch (BiffException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static void main(String[] args) throws IOException {
    ReadXLS test = new ReadXLS();
    test.setInputFile("nelson.xls");
    test.read();
    int i = test.getRowLength();
    Facility[] arr = test.getFacilities();
    System.out.println(arr[1].getInstitutionName());
  }

    /**
     * @return the rowLength
     */
    public int getRowLength() {
        return rowLength;
    }

    /**
     * @param rowLength the rowLength to set
     */
    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

} 
