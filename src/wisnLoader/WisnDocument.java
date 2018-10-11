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
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class WisnDocument {
    private dictionaries dictionaries;
    private environment environment;
    //DICTINARIES
    public class dictionaries{
        private dictionary dictionary;
        
        public class dictionary{
            private fields fields;
            private elements elements;
            
            public class fields{               
                private NodeList field;
                
            }
            public class elements{
                private element[] element;
                public class element{
                    private NodeList value;
                }
            }
        
        }
        
            //FIELDS
            private NodeList field;
            //ELEMENTS
            private NodeList element;
                //ELEMENT
                private NodeList value;
    }
  
    public class environment{        
        //ENVIRONMENT
        private NodeList controlinfo;
        //DEPENDENCES
        private Node dependences;
        private NodeList dependence;
    
    }
}
