/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package migracions;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSetMetaData;
/**
 *
 * @author Xavier
 */
public class Migracions {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, JSchException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String[] tauless = new String[100000]; 
        String[] errors = new String[100000]; 
        int i = 0;
        int llargadaerrors = 0;
        int o = 0;
        int rr = 1;
        int rows = 0;
        int r2;
        boolean bool = false;
        boolean bool2 = true;
        boolean menu = true;
        boolean boolaoe = false;
        boolean boolfirst = true;
        String ip = "";
        String cadenava = "";
        String variables = null;
        String user = "";
        String password = "";
        String bbdd = "";
        String bbddmsql = "";
        String connexio = "";
        String taula = "";
        String variablecon = "";
        String sshport = "";
        ResultSet re;
        String nom = "";
        int value = 0;
        do 
        {
        r2 = 10000;
        if(menu == true) 
        {
        System.out.println("------------------MENU---------------------");
        System.out.println("1. Migració de dades de Access a sql Automàtica");
        System.out.println("8. Informació del programa (LLEGEIXME)");
        System.out.println("-------------------------------------------");
        }
        String cadena = "";
        if(bool2 == true && menu == true) 
        {
            cadena = br.readLine();
            value = Integer.parseInt(cadena);
        }
        if(menu == false && bool2 == true) 
        {
            value = 1;
        }
        if(bool2 == false) 
        {
            value = 4;
        }
        switch(value) 
        {
            case 1:
                if(bool == false) 
                {
                //Iniciament del Setup (Variables)
                //Escriu tots els valors per comes (bbdd,(mysql) Ip Servidor, Usuari, Contrasenya, bbdd, Port del ssh
                cadena = "access,ip,root,password,mysqldatabase,sshport";
                variables = cadena;
                //Escriu totes les taules per ,
                cadenava = "Bandeja_Entrada,CamposSelRF,Clientes,Consulta1,Consulta3,ContactosClientes,Cuentas,EnlaceContabilidad,FACTURAPROJECTEOBERTS,FacturatProjectesObertsDetalls,Histórico_pedidos,Histórico_pedidos_detalles,Hoja2,LISTA_FACTURAS_CALIBRACION,ListaSeleccionada,Opciones,PedidosPerProjecte,Projectes_oberts,QTYPEDIDOSPERPROJECTES,RegistroBandejaEntradaPedidos,RegistroEstadosPedido,RegistroFacturas,RegistroFacturasDetalles,RegistroPedidos,RegistroPedidosDetalles,Servidores,Sistematest,TítulosFacturas,TítulosPedidos,Tarifas,Temp_Imp,Tipo_Contactos,Tipo_Idiomas";
                tauless = cadenava.split(",");
                //llistat de les taules mencionades
                for (int j = 0; j < tauless.length; j++) 
                {
                    System.out.println(tauless[j]);  
                }
                }
                //Iniciació dels strings com a variables
                String[] arrayvalors = variables.split(",");
                bbdd = arrayvalors[0];
                bbddmsql = arrayvalors[4];
                password = arrayvalors[3];
                user = arrayvalors[2];
                ip = arrayvalors[1];
                connexio = arrayvalors[0];
                sshport = arrayvalors[5];
                //Connexió cap a la bbdd de ACCESS
                Connection conn = DriverManager.getConnection("jdbc:ucanaccess://"+ arrayvalors[0] +".mdb");
                Statement st = conn.createStatement();
                if(menu == false && bool2 == true) 
                {
                    o++;
                }
                try 
                {
                    Statement s = conn.createStatement();
                    //Sistema de Detecció de caràcters no reconeguts
                    if(tauless[o].contains("�")) 
                    {
                        llargadaerrors++;
                        errors[llargadaerrors] = "La taula " +tauless[o] + " No ha pogut ser migrada ja que conté un caràcter no reconegut";
                        bool = true;
                        bool2 = true;
                        menu = false;
                        break;
                    }
                    re = st.executeQuery("select * from "+tauless[o]);
                    re.next();
                    ResultSetMetaData rsm;
                    //Sistema per comptar la llargada de una taula per Columnes aprofitant una excepció del java
                    if(bool2 == true) 
                    {
                    for (rr = 1; rr < r2 ; rr++) 
                    {
                        if(re.getString(rr) == null) 
                        {
                            System.out.println("null");
                        }
                        if(re.getString(rr) != "null" && re.getString(rr) != "FALSE" && re.getString(rr) != "null")
                        {
                            System.out.println(re.getString(rr));
                        }
                        if(re.getString(rr) == "FALSE") 
                        {
                            System.out.println("false");
                            System.out.println(rr);
                        }
                        if(re.getString(rr) == "TRUE") 
                        {
                            System.out.println("true");
                            System.out.println(rr);
                        }
                    }
                    }
                }
                catch(Exception e) 
                {
                    r2 = rr -3;
                    bool2 = false;
                    System.out.println(rr);
                }
                finally 
                {
                }
                break;
                case 4:
                    try 
                    {
                    conn = DriverManager.getConnection("jdbc:ucanaccess://"+ connexio +".mdb");
                    st = conn.createStatement();
                    re = st.executeQuery("SELECT COUNT(*) FROM "+tauless[o]);
                    bool = true;
                    re.next();
                    int i2 = 1;
                    int pos2 = 0;
                    int rows2 = re.getInt(1) -1;
                    String valor = "";
                    String[] forma2 = new String[99999999];
                    re = st.executeQuery("SELECT * FROM "+tauless[o]);
                    re.next();
                    //Creació del array amb els valors de la taula
                    for (pos2 = 0; pos2 < rows2; ) 
                    {
                        if(i2 == rr) 
                        {
                        pos2++;
                        re.next();
                        i2 = 1;
                        }
                        valor = re.getString(i2);
                        forma2[pos2] += valor;
                        forma2[pos2] += ";";
                        forma2[pos2].replace(',', '.');
                        System.out.println(forma2[pos2]);
                        i2++;
                    }
                    //segon for per gravar la última row (Important)
                    for (pos2 = pos2; pos2 == rows2; ) 
                    {
                        if(i2 == rr) 
                        {
                        pos2++;
                        i2 = 1;
                        }
                        valor = re.getString(i2);
                        forma2[pos2] += valor;
                        forma2[pos2] += ";";
                        forma2[pos2].replace(',', '.');
                        System.out.println(forma2[pos2]);
                        i2++;
                    }
                    st.close();
                    //Creació del fitxer txt "variables.txt"
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("variables.txt"), StandardCharsets.UTF_8));
                    for (int j = 0; j < forma2.length; j++)
                    {
                        if(forma2[j] != null) 
                        {
                            String replaceAll = forma2[j].replaceAll("null", "");
                            out.write(replaceAll);
                            out.println("");
                            out.flush();
                        }
                    }
                    out.close();
                    System.out.println("Les variables estàn guardades en el fitxer de text");
                    bool = true;
                    bool2 = true;
                    menu = false;
                    //Aquí es connecta amb el ssh i puja el fitxer cap el mysql
                java.util.Properties pr = new java.util.Properties();
                pr.put("StrictHostKeyChecking", "no");
                JSch a = new JSch();
                a.setConfig(pr);
                Session s = a.getSession(user,ip,Integer.parseInt(sshport));
                s.setPassword(password);
                s.connect(100000000);
                Class.forName("com.mysql.jdbc.Driver");
                if(boolfirst == true) 
                {
                s.setPortForwardingL(3306, "127.0.0.1", 3306);
                boolfirst = false;
                }
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/?useUnicode=true&characterEncoding=utf-8&user="+user+"&password="+password);
                PreparedStatement ps = connection.prepareStatement("use "+bbddmsql);
                int result = ps.executeUpdate();
                ps = connection.prepareStatement("alter table "+tauless[o]+" convert to character set utf8 collate utf8_unicode_ci");
                result = ps.executeUpdate();
                ps = connection.prepareStatement("DELETE FROM " +tauless[o]);
                result = ps.executeUpdate();
                ps = connection.prepareStatement("SET SESSION sql_mode = ''");
                result = ps.executeUpdate();
                ps = connection.prepareStatement("LOAD DATA LOCAL INFILE 'variables.txt' REPLACE INTO TABLE `"+tauless[o]+"` FIELDS TERMINATED BY ';' ESCAPED BY '\\\\' LINES TERMINATED BY '\\n';");
                result = ps.executeUpdate();
                }
                catch (ArrayIndexOutOfBoundsException e) 
                {
                        //Auto correció del arrayoutboundsexception
                        rr--;
                        llargadaerrors++;
                        errors[llargadaerrors] = "La taula " +tauless[o] + " S'ha excedit de columnes, (s'ha reparat sol)";
                }
                catch (SQLException ex) 
                {
                    llargadaerrors++;
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                    errors[llargadaerrors] = "[MYSQL]" + ex.getMessage() + ex.getSQLState() + ex.getErrorCode();
                }
                break;
                case 8:
                    System.out.println("--------------------------------LLEGEIXME----------------------------------------------------------");
                    System.out.println("Migracions Mysql");
                    System.out.println("Programa creat per: Xavier Flores Mestre en l'any 2017");
                    System.out.println("Es permet ús particular sense comercialització");
                    System.out.println("Requisits:");
                    System.out.println("1. Un mínim de 2 rows i 2 columnes per taules (En càs de ser menor la taula no serà pujada)");
                    System.out.println("2. Col.locar les BBDDS de ACCESS en el mateix fitxer que està el programa");
                    System.out.println("3. Tenir el fitxer valors.txt en el mateix fitxer del programa");
                    System.out.println("4. Tenir totes les llibreries del java per funcionar correctament (ACCESS, Mysql, SSH)");
                    System.out.println("5. Escriure la informació adient per comes al String (bbdd, (mysql) ip servidor, base de dades...)");
                    System.out.println("6. Escriure el llistat de les taules per comes al String");
                    System.out.println("- Possibles problemes -");
                    System.out.println("1. El SSH utilitza la ip que vas a posar del mysql, però al connectar amb el jdbc pasarà localhost, i el usuari ssh utilitza 'root' per defecte, ¡en càs de tenir problemes cambiar-ho manualment!");
                    System.out.println("2. (Exception in thread \"main\" com.jcraft.jsch.JSchException: PortForwardingL). En el càs de rebre un error ssh reiniciar el programa o si es editor java (netbeans) reiniciar el editor");
                    System.out.println("3. En càs de no deixar pujar el fitxer variables.txt sería produït per el servidor què ho bloca");
                    System.out.println("_______Funcionament_______");
                    System.out.println("El funcionament del programa depèn de 3 llibreríes: jdbc, ucanaccess i jsch");
                    System.out.println("El mètode de migració entra a la base de dades del access i recull la informació de totes les taules mencionades,");
                    System.out.println("quant es recull les passa al txt de 'variables.txt' com si fòs un csv, un cop recollida tota la informació");
                    System.out.println("de una taula utilitzem el SSH i el Jdbc per entrar cap a la base de dades mencionada a la variable");
                    System.out.println("I procedeix a pujar el csv cap el servidor de forma automàtica, aquest procediment es repetit fins a acabar.");
                    System.out.println("En el càs de què no es pujès algún contingut cap al servidor mysql sortiría al informe d'errors");
                    System.out.println("_______Funcionament_______");
                    System.out.println("--------------------------------LLEGEIXME----------------------------------------------------------");
                    break;
            } 
        }
        while(o != tauless.length);
        System.out.println("--------------Informe d'errors------------------");
        for (int j = 0; j < llargadaerrors; j++) 
        {
            System.out.println("ID Error: " + j +" Descripció "+errors[j]);
        }
        System.out.println("--------------Informe d'errors------------------");
    }
}

