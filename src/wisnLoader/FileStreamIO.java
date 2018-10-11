/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wisnLoader;
import java.io.*;
import java.io.BufferedReader;
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
public class FileStreamIO {

    File file = null;
    FileReader fr = null;
    FileWriter outFile = null;
    PrintWriter out = null;
    LineNumberReader lnr = null;
    private String fileContent = "";
    private String file1 = "";
    private String file2 = "";
    private String fileName = "";
    //private BufferedReader BufferedReader;
    private String encrypedContent = "";
    private String convertedContent = "";

    FileStreamIO()
    {
        fileName = "file.txt";
    }

    FileStreamIO(String name)
    {
        fileName = name;
    }

    /**
     * Reading files methods
     */
    public void readFromFileLineNumber()throws Exception
    {
        try {

            String s = "";
                file = new File(getFileName());

                fr = new FileReader(file);
                lnr = new LineNumberReader(fr);


                //lnr.setLineNumber(400);
                String line = "";
                while ((line = lnr.readLine()) != null) {
                    s = s + "" + " " + line + "\n";
                    setFileContent(s);}
        } finally {

            if (fr != null) {
                fr.close();
            }
            if (lnr != null) {
                lnr.close();
            }
        }
    }

    public void readFromFile()throws Exception
    {
        try {
                String s = "";
                file = new File(getFileName());

                fr = new FileReader(file);
                lnr = new LineNumberReader(fr);


                //lnr.setLineNumber(400);
                String line = "";
                while ((line = lnr.readLine()) != null) {
                    s = s + "" + "" + line + "";
                    setFileContent(s);
                }
        } finally {

                    if (fr != null) {
                        fr.close();
                    }
                    if (lnr != null) {
                        lnr.close();
                    }
        }
    }

    /**
     * Writing to files methods
     */
    public void writeToFile(String name)
    {
        try {
            String str;
            outFile = new FileWriter(name);
            out = new PrintWriter(outFile);
            BufferedWriter writer = new BufferedWriter(outFile);
            BufferedReader reader = new BufferedReader(new StringReader(getFileContent()));

            while( (str = reader.readLine()) != null )
            {
                if( str.length() > 0 ){
                    out.println(str.toString());
                    //System.out.println(str.charAt(0));
                }
            }  
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeToFileEncypted(String name)
    {
        try {
                String str;
                outFile = new FileWriter(name);
                out = new PrintWriter(outFile);
                //BufferedWriter writer = new BufferedWriter(outFile);
                BufferedReader reader = new BufferedReader(new StringReader(getEncrypedContent()));
                //System.out.println(getEncrypedContent());
                while( (str = reader.readLine()) != null )
                {
                    if( str.length() > 0 ){
                        out.println(str.toString());
                        //System.out.println(str.charAt(0));
                    }
                }


                out.close();
        } catch (IOException e){
            e.printStackTrace();
       }
    }
    
    public void writeToFileConverted(String name)
    {
        try {
                String str;
                outFile = new FileWriter(name);
                out = new PrintWriter(outFile);
                //BufferedWriter writer = new BufferedWriter(outFile);
                BufferedReader reader = new BufferedReader(new StringReader(getConvertedContent()));
                //System.out.println(getEncrypedContent());
                while( (str = reader.readLine()) != null )
                {
                    if( str.length() > 0 ){
                        out.println(str.toString());
                        //System.out.println(str.charAt(0));
                    }
                }


                out.close();
        } catch (IOException e){
            e.printStackTrace();
           }

    }

    public void encryptString()
    {
        char s[] = fileContent.toCharArray();
        int ascci;
        for( int i=0 ; i < s.length ; i++ )
        {
            ascci = (int)s[i];
            if( ascci >=65 && ascci <= 90)// A TO Z
            {
                ascci = ascci + 13;
                if( ascci > 90 )
                    ascci = 64 + (ascci - 90);
            }
            else if( ascci >=97 && ascci <= 122)//a to z
            {
                ascci = ascci + 13;
                if( ascci > 122 )
                    ascci = 96 + (ascci - 122);
            }
            encrypedContent = encrypedContent+""+(char)ascci;
        }
    }
    
    public void convertWISN()
    {        
        String regionLine1 = "<element id=\"11\" name=\"";
        String regionLine2 = "<value key=\"country\" value=\"124\" />";
        String regionLine3 = "</element>";
        int space = 10;
        int backspace = 8;
        convertedContent = regionLine1+fileContent;//region insertion
        convertedContent = convertedContent+"\">"+(char)space;//finish the starting tag
        convertedContent = convertedContent+regionLine2+""+(char)space;//second line insertion
        convertedContent = convertedContent+regionLine3+""+(char)space;//third line insertion   
        System.out.println(getConvertedContent());
    }



    /**
     * Start of methods to manipulate
     * the variables of the class
     */

    /**
     * @return the fileContent
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent the fileContent to set
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the encripedContent
     */
    public String getEncrypedContent() {
        return encrypedContent;
    }

    /**
     * @param encripedContent the encripedContent to set
     */
    public void setEncrypedContent(String encripedContent) {
        this.encrypedContent = encripedContent;
    }
    
    /**
     * @return the convertedContent
     */
    public String getConvertedContent() {
        return convertedContent;
    }

    /**
     * @param convertedContent the encripedContent to set
     */
    public void setConvertedContent(String convertedContent) {
        this.convertedContent = convertedContent;
    }

    void doExe1(String fromFile, String toFile)throws Exception
    {
        setFileName(fromFile);
        readFromFileLineNumber();
        writeToFile(toFile);
    }

    void doExe2(String fromFile, String toFile)throws Exception
    {
        setFileName(fromFile);
        readFromFile();//System.out.println(getFileContent());
        encryptString();
        writeToFileEncypted(toFile);
    }

    /**
     * @return the file1
     */
    public String getFile1() {
        return file1;
    }

    /**
     * @param file1 the file1 to set
     */
    public void setFile1(String file1) {
        this.file1 = file1;
    }

    /**
     * @return the file2
     */
    public String getFile2() {
        return file2;
    }

    /**
     * @param file2 the file2 to set
     */
    public void setFile2(String file2) {
        this.file2 = file2;
    }

}
