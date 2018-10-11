/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wisnLoader;

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

public class LogFile{
    private static int numberFilesCreated;
    private static String logText = "**********************************************************************\n"
            +                       "\t\t\t\t Log file\n"
            +                       "**********************************************************************\n\n\n";
    private static String listOfFacilitiesCreated = "";
    private static int numberRowsOPD;
    private static int countOpdUpdatedCorrectly;
    private static int countOpdNotUpdated;
    private static boolean foundOPD;
    private static int countESUpdatedCorrectly;
    private static int countESNotUpdated;
    private static boolean foundES;
    
    
    public static void main(String argv[]) throws Exception {
        LogFile.writeLog();        
               
    }
    
    
    public static void increaseNumberFilesCreated(){
        numberFilesCreated++;
        //writeLog();
    }
    /**
     * @return the numberFilesCreated
     */
    public static int getNumberFilesCreated() {
        return numberFilesCreated;
    }

    /**
     * @param aNumberFilesCreated the numberFilesCreated to set
     */
    public static void setNumberFilesCreated(int aNumberFilesCreated) {
        numberFilesCreated = aNumberFilesCreated;
    }

    /**
     * @return the logText
     */
    public static String getLogText() {
        return logText;
    }

    /**
     * @param aLogText the logText to set
     */
    public static void setLogText(String aLogText) {
        logText = aLogText;
    }
    
    public static void appendText(){
        logText = logText + "\n" + "\nList of facilities created: " + listOfFacilitiesCreated;
        logText = logText + "\n" + "Number of files created: " + numberFilesCreated;
        logText = logText + "\n" + "Total number of lines read from OPD.xls: " + getNumberRowsOPD();
        logText = logText + "\n" + "Total facilities with OPD updated correctly: " + getCountOpdUpdatedCorrectly();
        logText = logText + "\n" + "Total facilities with OPD not updated: " + getCountOpdNotUpdated();
        logText = logText + "\n" + "Total facilities with Existing Staff updated correctly: " + getCountESUpdatedCorrectly();
        logText = logText + "\n" + "Total facilities with Existing Staff not updated: " + getCountESNotUpdated();
        
    }
    
    public static void writeLog() throws Exception {
        FileStreamIO f = new FileStreamIO();
        
        appendText();
        
        f.setFileContent(getLogText());
        f.writeToFile("LogFile.txt");
    }

    public static void appendListOfFacilitiesCreated(String txt){
        listOfFacilitiesCreated = listOfFacilitiesCreated +"\n"+ txt;
        //writeLog();
    }

    /**
     * @return the numberRowsOPD
     */
    public static int getNumberRowsOPD() {
        return numberRowsOPD;
    }

    /**
     * @param aNumberRowsOPD the numberRowsOPD to set
     */
    public static void setNumberRowsOPD(int aNumberRowsOPD) {
        numberRowsOPD = aNumberRowsOPD;
    }
    public static void setNumberRowsOPD(String aNumberRowsOPD) {
        
        numberRowsOPD = Integer.parseInt(aNumberRowsOPD);
    }

    /**
     * @return the countNamesDontMatch
     */
    public static int getCountOpdUpdatedCorrectly() {
        return countOpdUpdatedCorrectly;
    }

    /**
     * @param aCountNamesDontMatch the countNamesDontMatch to set
     */
    public static void setCountOpdUpdatedCorrectly(int aCountOpdUpdatedCorrectly) {
        countOpdUpdatedCorrectly = aCountOpdUpdatedCorrectly;
    }
    
    public static void increaseCountOpdUpdatedCorrectly() {
        countOpdUpdatedCorrectly++;
    }
    public static void increaseCountESUpdatedCorrectly() {
        countESUpdatedCorrectly++;
    }

    /**
     * @return the countOpdNotUpdated
     */
    public static int getCountOpdNotUpdated() {
        return countOpdNotUpdated;
    }

    /**
     * @param aCountOpdNotUpdated the countOpdNotUpdated to set
     */
    public static void setCountOpdNotUpdated(int aCountOpdNotUpdated) {
        countOpdNotUpdated = aCountOpdNotUpdated;
    }
    
    public static void increaseCountOpdNotUpdated() {
        countOpdNotUpdated++;
    }
    
    public static void increaseCountESNotUpdated() {
        countESNotUpdated++;
    }

    /**
     * @return the foundOPD
     */
    public static boolean isFoundOPD() {
        return foundOPD;
    }

    /**
     * @param aFoundOPD the foundOPD to set
     */
    public static void setFoundOPD(boolean aFoundOPD) {
        foundOPD = aFoundOPD;
    }

    /**
     * @return the countESUpdatedCorrectly
     */
    public static int getCountESUpdatedCorrectly() {
        return countESUpdatedCorrectly;
    }

    /**
     * @param aCountESUpdatedCorrectly the countESUpdatedCorrectly to set
     */
    public static void setCountESUpdatedCorrectly(int aCountESUpdatedCorrectly) {
        countESUpdatedCorrectly = aCountESUpdatedCorrectly;
    }

    /**
     * @return the countESNotUpdated
     */
    public static int getCountESNotUpdated() {
        return countESNotUpdated;
    }

    /**
     * @param aCountESNotUpdated the countESNotUpdated to set
     */
    public static void setCountESNotUpdated(int aCountESNotUpdated) {
        countESNotUpdated = aCountESNotUpdated;
    }

    /**
     * @return the foundES
     */
    public static boolean isFoundES() {
        return foundES;
    }

    /**
     * @param aFoundES the foundES to set
     */
    public static void setFoundES(boolean aFoundES) {
        foundES = aFoundES;
    }
    
}
