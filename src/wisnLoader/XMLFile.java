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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import java.io.File;
 
public class XMLFile {
    //VARIABLES TO READ AN XML
    private String outputFileName;
    private String inputFileName;
    private File fXmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    
    //VARIABLES TO WRITE TO XML
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DOMSource source;
    private StreamResult result;
    
    public XMLFile( String inputFileName, String outputFileName ){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
	
    }
    public XMLFile( String inputFileName ){
        this.inputFileName = inputFileName;
    }
    
    public void readFile(){
        try{
            fXmlFile = new File(getInputFileName());
//            fXmlFile = new File("C:\\Users\\intrah1\\Desktop\\WISN 1.1.0\\repertoire.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            setDoc(dBuilder.parse(fXmlFile));
            getDoc().getDocumentElement().normalize();
            
        }catch (Exception e) {
		e.printStackTrace();
                System.out.println("Error Reading");
	  }
    }
    
    public void readWriteFile(){        
        try{
            fXmlFile = new File(getInputFileName());
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            setDoc(dBuilder.parse(fXmlFile));
            getDoc().getDocumentElement().normalize();
            
        }catch (Exception e) {
		e.printStackTrace();
                System.out.println("Error Reading");
	  }
        try{
            // write the content into xml file
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(getDoc());
            result = new StreamResult(new File(getOutputFileName()));
            transformer.transform(source, result);
            
        }catch (Exception e) {
		e.printStackTrace();
                System.out.println("Error Writing");
	  }
        
    }
    
    public void writeFile(){
        
        try{
            // write the content into xml file
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(getDoc());
            result = new StreamResult(new File(getOutputFileName()));
            transformer.transform(source, result);
            
        }catch (Exception e) {
		e.printStackTrace();
                System.out.println("Error Writing");
	  }
        
    }
    public void writeFile(Document doc){
        setDoc(doc);
        try{
            // write the content into xml file
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            source = new DOMSource(getDoc());
            result = new StreamResult(new File(getOutputFileName()));
            transformer.transform(source, result);
            
        }catch (Exception e) {
		e.printStackTrace();
                System.out.println("Error Writing");
	  }
        
    }

    /**
     * @return the outputFileName
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * @param outputFileName the outputFileName to set
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * @return the inputFileName
     */
    public String getInputFileName() {
        return inputFileName;
    }

    /**
     * @param inputFileName the inputFileName to set
     */
    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
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
 
}
