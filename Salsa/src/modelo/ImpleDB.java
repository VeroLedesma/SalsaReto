package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controlador.Dao;

public class ImpleDB implements Dao {

	// Objeto para crear conexión mysql
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	// Consultas a la Base de Datos
	private final String CONSULTA_USUARIO = "SELECT dni, nombre, apellido,fechaNac, direccion, email, genero FROM persona ";
	private final String ALTA_PERSONA = "INSERT INTO persona (dni, nombre, apellido, fechaNac, contrasena, direccion, email, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String ALTA_TRABAJADOR = "INSERT INTO trabajador (dni, nss) VALUES (?, ?)";
	private final String ALTA_USUARIO = "INSERT INTO usuario (dni, fechaReg) VALUES (?, ?)";
	private final String ALTA_ARTICULO = "INSERT INTO articulo (cod_articulo, color, modelo, temporada, precio, descuento) VALUES (?, ?, ?, ?, ?, ?)";
	private final String CONSULTA_COMPROBAR_USUARIO = "SELECT dni, nombre, apellido,fechaNac, direccion, email, genero FROM persona WHERE email=? AND contrasena=?";
	private final String MODIFICACION_USUARIO = "UPDATE persona SET nombre = ?, apellido = ?,fechaNac=?,  direccion = ?, email = ?, genero=? WHERE dni = ?";

	@Override
	public List<Persona> listarUsuarios() {
//arreglar este metodo que lo que hara sera verificar la existencia del usuario que inicie sesion en la aplicacion

		List<Persona> personas = new ArrayList<>();
		conn = ConnectionMysql.openConnection();

		try {
			stmt = conn.prepareStatement(CONSULTA_USUARIO);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Persona per = new Persona();
				per.setDni(resultSet.getString("dni"));
				per.setNombre(resultSet.getString("nombre"));
				per.setApellido(resultSet.getString("apellido"));
				per.setFechaNacimiento(LocalDate.parse(resultSet.getString("fechaNac")));
				per.setDireccion(resultSet.getString("direccion"));
				per.setEmail(resultSet.getString("email"));
				per.setGenero(Sexo.valueOf(resultSet.getString("genero").toUpperCase()));
				personas.add(per);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al intentar comprobar el usuario.");
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			ConnectionMysql.closeConnection();
		}
		return personas;
	}

	@Override
	public boolean registrarUsuario(Persona per) {
		conn = ConnectionMysql.openConnection();

		try {
			// Inserción en la tabla persona
			stmt = conn.prepareStatement(ALTA_PERSONA);
			stmt.setString(1, per.getDni());
			stmt.setString(2, per.getNombre());
			stmt.setString(3, per.getApellido());
			stmt.setString(4, per.getFechaNacimiento().toString());
			stmt.setString(5, per.getContrasena());
			stmt.setString(6, per.getDireccion());
			stmt.setString(7, per.getEmail());
			stmt.setString(8, per.getGenero().toString());
			stmt.executeUpdate();

			// Inserción en la tabla trabajador (simplemente inserta el DNI)
			if (per instanceof Trabajador) {
				stmt = conn.prepareStatement(ALTA_TRABAJADOR);
				stmt.setString(1, per.getDni());
				stmt.setString(2, ((Trabajador) per).getNnss());
				stmt.executeUpdate();
			} else if (per instanceof Usuario) {
				// Inserción en la tabla usuario (simplemente inserta el DNI y la fecha de
				stmt = conn.prepareStatement(ALTA_USUARIO);
				stmt.setString(1, per.getDni());
				stmt.setString(2, ((Usuario) per).getFechaRegistro().toString()); // Fecha de registro actual
				stmt.executeUpdate();

			}

			// Si todas las inserciones fueron exitosas, retorna true
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			if (conn != null) {
				ConnectionMysql.closeConnection();
			}
		}
	}

	@Override
	public boolean altaArticulo(Articulo art) {
		conn = ConnectionMysql.openConnection();

		try {
			// insercion de la tabla articulo
			stmt = (conn.prepareStatement(ALTA_ARTICULO));
			stmt.setInt(1, art.getCodArticulo());
			stmt.setString(2, art.getColor());
			stmt.setString(3, art.getModelo());
			stmt.setString(4, art.getTemporada().toString());
			stmt.setFloat(5, art.getPrecio());
			stmt.setFloat(6, art.getPorcentajeDecuento());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			if (conn != null) {
				ConnectionMysql.closeConnection();
			}
		}
	}

	@Override
	public boolean iniciarSesion(String email, String contrasena) {
		boolean existe = false;
		int rows = 0;
		conn = ConnectionMysql.openConnection();

		try {
			stmt = conn.prepareStatement(CONSULTA_COMPROBAR_USUARIO);
			stmt.setString(1, email);
			stmt.setString(2, contrasena);

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				rows++;
			}
			if (rows != 0) {
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al intentar comprobar el usuario.");
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			ConnectionMysql.closeConnection();
		}
		return existe;
	}

	@Override
	public boolean modificarUsuario(Persona per) {
		// revisarlo y verificar que la query haga lo que debe hacer porque lo hizo
		// santi

		conn = ConnectionMysql.openConnection();
		boolean modificado = false;

		try {
			PreparedStatement stmt = conn.prepareStatement(MODIFICACION_USUARIO);
			stmt.setString(1, per.getNombre());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getFechaNacimiento().toString());
			stmt.setString(4, per.getEmail());
			stmt.setString(5, per.getDireccion());
			stmt.setString(6, per.getEmail());
			stmt.setString(6, per.getGenero().toString());
			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				modificado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar modificar el usuario.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Error en el cierre de la conexión.");
				e.printStackTrace();
			}
		}

		return modificado;
	}

}
