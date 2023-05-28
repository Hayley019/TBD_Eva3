/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package eva_7_javadbconnector;

import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
//imports para la conexion
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//omport para instrucciones
import java.sql.Statement;
import java.sql.ResultSet;  
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;
/**
 *
 * @author maini
 */
public class Eva_7_JavaDBConnector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //conexion
            String url = "jdbc:mysql://localhost:3306/eva_3";
            connection = DriverManager.getConnection(url,"root","16060749");
            
            /*//consulta
            Statement statement = connection.createStatement();
            //obtener un resultset
            ResultSet resultSet;
            resultSet = statement.executeQuery("select * from actor limit 50;");*/
            
            String query = "Select * from actor where actor_id = ?;";
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1,152);
            ResultSet resultSet;
            resultSet = pStatement.executeQuery();
                 
            int actor_id;
            String f_name;
            String l_name;
            while(resultSet.next()){
                actor_id = resultSet.getInt("actor_id");
                f_name = resultSet.getString("first_name");
                l_name = resultSet.getString("last_name");
                System.out.println("ID: " + actor_id + "\n" + "First Name: " + f_name + "\n"
                + "Last Name: " + l_name);
            }
            /*String qInsert = "insert into actor(first_name, last_name)"
                    + "values(?,?);";
            PreparedStatement pSInsert = connection.prepareStatement(qInsert);
            pSInsert.setString(1,"Mario");
            pSInsert.setString(2,"Bros");
            pSInsert.execute();*/
            
            //Llamar a la funcion/procedimiento
            CallableStatement callStat = connection.prepareCall("? = call generar_rfc(?,?,?,?);");
            //Parametro de salida
            callStat.registerOutParameter(1, Types.VARCHAR);
            //parametros de etrada
            callStat.setString(2, "Juan");
            callStat.setString(3, "Perez");
            callStat.setString(4, "Jolote");
            callStat.setObject(5, LocalDate.of(2004,11,20));
            callStat.execute();
            System.out.println("RFC: "+callStat.getString(1));           
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Error: " + ex.getErrorCode());
        }
    }
    
}
