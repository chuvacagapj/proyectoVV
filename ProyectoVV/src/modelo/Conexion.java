package modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
	
  private static Conexion     mysqlconn = null;	
  private static String       usuario;
  private static String       baseDatos;
  private static String       password;
  private static String       url;
  private static Connection   conn;
  
  public static void setInfo(String usr, String bd, String pw, String ip){
	  usuario = usr;
	  baseDatos = bd;
	  password = pw;
	  url = ip;
  }
  
  public static Conexion getConexion(){
	  if ( Conexion.mysqlconn == null){
		  Conexion.mysqlconn = new Conexion();
	  }
	  return Conexion.mysqlconn;
  }
  
  public static void closeConexion(){
	  try{
		  conn.close();
	  }
	  catch(Exception e){
		  JOptionPane.showMessageDialog(null, e.getMessage(), "Error al cerrar la base de datos", JOptionPane.ERROR_MESSAGE);
	  }
	  conn = null;
	  mysqlconn = null;
  }
  
  private Conexion(){
	  try{
		  Class.forName("com.mysql.jdbc.Driver");
		  conn = DriverManager.getConnection("jdbc:mysql://"+ url +"/" + baseDatos, usuario, password); 
	  }
	  catch (Exception e){
		  JOptionPane.showMessageDialog(null, e.getMessage(), "Error al conectarse a la base de datos", JOptionPane.ERROR_MESSAGE);
	  }
  }
  
  public Statement hacerConsulta(){
	  Statement consulta  = null;
	  
	  try{
		  consulta = conn.createStatement();
	  }
	  catch(Exception e){
		  JOptionPane.showMessageDialog(null, e.getMessage(), "Error al hacer la consulta", JOptionPane.ERROR_MESSAGE);
	  }
	  return consulta;
  }
  
  public Object clone() throws CloneNotSupportedException {
	  throw new CloneNotSupportedException();
  }
}
