STATEMENT OF COPYRIGHT PERMISSION
This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License
as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along with this program.  If not, see 
 <http://www.gnu.org/licenses/>
-----------------------------------------------------------------------------

To run this program, please do the following:


1. Go to config.properties in the Root dir and set the following properties to match your files
	
	- ActivitiesFileName: this is the name of the activities statistics file
	- StaffFileName: this the name of the existing staffs file name
	- FacilitiesFileName: this is the name of the file containing the facilities
	- TemplateFolderName: this is the name of the folder containing the template for different institution types
	- Don't change the WISNFileExtension property

2. Add different excel files (.xls format only), according to the properties set above
	For Activities, Available staff and facility list, put them in the Root directory
	Put template files for each institution type in the template folder you set above
	You can refer to the file provided to see the required format

3. Open the project with Netbeans

4.Add jxl.jar library from http://www.java2s.com/Code/Jar/j/Downloadjxl26jar.htm

5. Run the program

6. The generated result files will be under automatically created folder named after the institution types available