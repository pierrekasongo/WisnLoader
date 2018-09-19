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
public class Contants {
    
     String Organisation= "IntraHelth International";
     String OrganisationEmail= "intrahealth@intrahealth.org";
     String WHOEmail= "hrhinfo@who.int";
     String URL="http://www.gnu.org/licenses/";
    
    
    public void Copyright()
    {
        Contants cons=new Contants();
        System.out.println("COPYRIGHT NOTICE:");
        System.out.println("DRC Automatic WISN Loader (DRCWL), a program developed by "+Organisation+
                "("+cons.OrganisationEmail+")to import service statistics into the WISN tool developed by WHO ("
                +cons.WHOEmail+").");
        System.out.println("Copyright (C) 2012"+cons.Organisation);
        System.out.println();
    }
    public void Statement()
    {
        System.out.println("STATEMENT OF COPYRIGHT PERMISSION:");
        System.out.println("This program is free software: you can redistribute it and/or modify it under the terms"
                +" of the GNU General Public License as published by the Free Software Foundation, either version 3 "
                +"of the License, or (at your option) any later version.");
        System.out.println("This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;"
                +"without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU"
                +" General Public License for more details.");
        System.out.println("You should have received a copy of the GNU General Public License along with this program. "
                +" If not, see <"+URL+">.");
        System.out.println();
    }
    public void NAWLlogo()
    {
        System.out.println("     ***     **        ***      **     **    **      ***       ");
        System.out.println("     ** *    **       ** **      **    **    **      ***       ");
        System.out.println("     **  *   **      **   **      **   **   **       ***       ");
        System.out.println("     **   *  **     *********      **  **  **        ***       ");
        System.out.println("     **    * **    **       **      ** ** **         ********  ");
        System.out.println("     ***    ***   ***       ***     ********         ********  ");
        System.out.println("______________________________________________________________________");
        System.out.println("                 DRC Automatic WISN Loader                ");
        System.out.println("______________________________________________________________________");
        System.out.println(" ");
    }
    
    
    
}
