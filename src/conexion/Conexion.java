package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LSI-LAB8-PC12
 */
public class Conexion {
    public Connection Conectar(){
        Connection conex = null;
        try{
            Class.forName("org.postgresql.Driver");
            conex = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Countries","postgres","19977");
        }
        catch(SQLException | ClassNotFoundException exc){
            JOptionPane.showMessageDialog(null, exc.getMessage(), "WARNING" , JOptionPane.ERROR_MESSAGE);
        }
        return conex;
    }
}
