CATALAN:

--------------------------------LLEGEIXME----------------------------------------------------------
Migracions Mysql
AVIS!!!!! ------------------------------------------------------------------------------------------------
Obrir desde el Netbeans o qualsevol editor de java el projecte migracions - classe Migracions.java click dret al codi i run file, es important ja què utilitzaràs el codi per col.locar tota la configuració en els Strings anomenats: cadena i cadenava
----------------------------------------------------------------------------------------------------------
Programa creat per: Xavier Flores Mestre en l'any 2017
Es permet ús particular sense comercialització
Requisits:
1. Un mínim de 2 rows i 2 columnes per taules (En càs de ser menor la taula no serà pujada
2. Col.locar les BBDDS de ACCESS en el mateix fitxer que està el programa
3. Tenir el fitxer valors.txt en el mateix fitxer del programa
4. Tenir totes les llibreries del java per funcionar correctament (ACCESS, Mysql, SSH)
5. Escriure la informació adient per comes al String (bbdd, (mysql) ip servidor, base de dades...
6. Escriure el llistat de les taules per comes al String
- Possibles problemes -
1. El SSH utilitza la ip que vas a posar del mysql, però al connectar amb el jdbc pasarà localhost, i el usuari ssh utilitza 'root' per defecte, ¡en càs de tenir problemes cambiar-ho manualment!
2. (Exception in thread \"main\" com.jcraft.jsch.JSchException: PortForwardingL). En el càs de rebre un error ssh reiniciar el programa o si es editor java (netbeans) reiniciar el editor
3. En càs de no deixar pujar el fitxer variables.txt sería produït per el servidor què ho bloca
_______Funcionament_______
El funcionament del programa depèn de 3 llibreríes: jdbc, ucanaccess i jsch
El mètode de migració entra a la base de dades del access i recull la informació de totes les taules mencionades
quant es recull les passa al txt de 'variables.txt' com si fòs un csv, un cop recollida tota la informació
"de una taula utilitzem el SSH i el Jdbc per entrar cap a la base de dades mencionada a la variable
I procedeix a pujar el csv cap el servidor de forma automàtica, aquest procediment es repetit fins a acabar.
En el càs de què no es pujès algún contingut cap al servidor mysql sortiría al informe d'errors
_______Funcionament_______
--------------------------------LLEGEIXME----------------------------------------------------------

English

--------------------------------README----------------------------------------------------------
Migracions Mysql
Warning!!!!! ------------------------------------------------------------------------------------------------
You must open it with a java code editor like "Netbeans" to open the project and customize your options to work properly
----------------------------------------------------------------------------------------------------------
Software created by: Xavier Flores Mestre In 2017
License: personal use without commercialization
Requirements:
1. At least you must have 2 rows and columns for table (if there's less than 2 the table won't be uploaded)
2. You must set the ACCESS databases on the same file than the program to read it
3. The file "valors.txt" must be on the same file than the program
4. You must have all the libraries on the same file of the program (migracions/dist/lib)
5. Really important!!!!! you must write all the information about the connection in the String named as: "cadena"
6. Important too!!!!! the tables you want to migrate must be written in the String named as: "cadenava"
- Troubleshooting -
1. The SSH uses the same ip direction that you gonna set on mysql ip but when you're connecting with jbdc it uses localhost and root by default ¡If you have any problem you always could change it!
2. (Exception in thread \"main\" com.jcraft.jsch.JSchException: PortForwardingL). That's a ssh problem to solve it you only need to reload the java code editor
3. If you cannot upload the "variables.txt" it's because the mysql is blocking it try to search the error
_______How it works?_______
the software depends of 3 libraries: jdbc, ucanaccess and jsch The method works entering to the acess database and recolecting all the information from every table one by one saving all the information into the "variables.txt" file.
When is built the software uses a ssh connection and jbdc to enter to the mysql and send the txt file as it was a csv file.
This process is repeated on every table written on the program and if some table were not uploaded Don't worry! the program is gonna print a report to see that.
_______How it works?_______
--------------------------------README----------------------------------------------------------

