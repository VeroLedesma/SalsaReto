package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase para manejar la conexión a la base de datos MySQL.
 * 
 * @author Melany, santi
 * 
 */
public class ConnectionMysql {
	// Atributos
	private static Connection conn;
	// Credenciales para conexión a la base de datos
	private static final String USERNAME = "root";
	private static final String PASSWORD = "abcd*1234";
	private static String URL = "jdbc:mysql://localhost:3306/salsa?serverTimezone=Europe/Madrid&useSSL=false";

	/**
	 * Abre la conexión a la base de datos. utilizando las credenciales necesarias
	 * para acceder a ella
	 * 
	 * @return la conexión establecida.
	 */
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			return conn;
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return conn;
		}
	}

	/**
	 * Cierra la conexión a la base de datos.
	 *
	 * @return la conexión cerrada.
	 */
	public static Connection closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}