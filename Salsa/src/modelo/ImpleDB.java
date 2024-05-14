package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controlador.Dao;

public class ImpleDB implements Dao {

	// Objeto para crear conexión mysql
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet resultSet;
	private CallableStatement callableStatement;
	// Consultas a la Base de Datos
	private final String CONSULTA_USUARIO = "SELECT dni, nombre, apellido,fechaNac,contrasena,  direccion, email, genero FROM persona ";
	private final String ALTA_PERSONA = "INSERT INTO persona (dni, nombre, apellido, fechaNac, contrasena, direccion, email, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String ASIGNACION = "{CALL setPersonaInvitado(?,?,?)}";
	private final String ASIGNACIONTRABAJADOR = "{CALL setTrabajador(?,?,?)}";
	private final String ALTA_ARTICULO = "INSERT INTO articulo ( color, modelo, temporada, precio, descuento) VALUES ( ?, ?, ?, ?, ?)";
	private final String CONSULTA_COMPROBAR_USUARIO = "SELECT dni, nombre, apellido,fechaNac, direccion, email, genero FROM persona WHERE email=? AND contrasena=?";
	private final String MODIFICACION_USUARIO = "UPDATE persona SET dni=?, nombre = ?, apellido = ?, fechaNac= ?, contrasena=?, direccion = ?, email = ?, genero= ? WHERE dni = ?";
	private final String CONSULTA_ARTICULO = "SELECT cod_articulo,color,modelo,temporada, precio,descuento,cod_tipo FROM articulo";
	private final String CONSULTA_TIPO = "SELECT cod_tipo, nombre, stock FROM tipo";
	private final String ALTA_TIPO = "INSERT INTO tipo (nombre, stock) VALUES (?, ?)";
	private final String ACTUALIZARSTOCKTIPO = "SELECT calcular_nuevo_stock(?, ?)";

	@Override
	public List<Articulo> listarArticulos() {

		List<Articulo> articulo = new ArrayList<>();
		conn = ConnectionMysql.openConnection();

		try {
			stmt = conn.prepareStatement(CONSULTA_ARTICULO);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Articulo art = new Articulo();

				art.setCodArticulo(resultSet.getInt("cod_articulo"));
				art.setColor(resultSet.getString("color"));
				art.setModelo(resultSet.getString("modelo"));
				art.setTemporada(Temporada.valueOf(resultSet.getString("temporada").toUpperCase()));
				art.setPrecio(resultSet.getFloat("precio"));
				art.setPorcentajeDecuento(resultSet.getFloat("descuento"));
				articulo.add(art);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al intentar comprobar el articulo.");
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

		return articulo;

	}

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
				per.setContrasena(resultSet.getString("contrasena"));
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

			// Inserción en la tabla trabajador llamando a un procedimiento creado en la
			// base de datos que le asiganra el rol de empleado
			if (per instanceof Trabajador) {
				callableStatement = conn.prepareCall(ASIGNACIONTRABAJADOR);
				callableStatement.setString(1, per.getDni());
				callableStatement.setString(2, ((Trabajador) per).getNnss());
				callableStatement.setString(3, per.getContrasena());
				callableStatement.execute();
			} else if (per instanceof Usuario) {
				// Inserción en la tabla usuario llamando a un procedimiento creado en la base
				// de datos que le asignara el rol de invitado cuys permisos estan definidos
				callableStatement = conn.prepareCall(ASIGNACION);
				callableStatement.setString(1, per.getDni());
				callableStatement.setString(2, ((Usuario) per).getFechaRegistro().toString());
				callableStatement.setString(3, per.getContrasena());
				callableStatement.execute();

			}

			// Si todas las inserciones fueron exitosas, retorna true
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (SQLException e) {
			}
			if (conn != null) {
				ConnectionMysql.closeConnection();
			}
		}
	}

	@Override
	public int altaArticulo(Articulo art) {
		conn = ConnectionMysql.openConnection();
		int cod = 0;
		try {
			// insercion de la tabla articulo
			stmt = (conn.prepareStatement(ALTA_ARTICULO, Statement.RETURN_GENERATED_KEYS));
			stmt.setString(1, art.getColor());
			stmt.setString(2, art.getModelo());
			stmt.setString(3, art.getTemporada().toString());
			stmt.setFloat(4, art.getPrecio());
			stmt.setFloat(5, art.getPorcentajeDecuento());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				cod = resultSet.getInt(cod);
			}

			return cod;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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

		conn = ConnectionMysql.openConnection();
		boolean modificado = false;
		try {

			stmt = (conn.prepareStatement(MODIFICACION_USUARIO));
			System.out.println("dentro de la query");
			stmt.setString(1, per.getDni());
			stmt.setString(2, per.getNombre());
			stmt.setString(3, per.getApellido());
			stmt.setString(4, per.getFechaNacimiento().toString());
			stmt.setString(5, per.getContrasena());
			stmt.setString(6, per.getDireccion());
			stmt.setString(7, per.getEmail());
			stmt.setString(8, per.getGenero().toString());

			modificado = stmt.execute();
			System.out.println(stmt.executeUpdate());
//			if (rowsAffected) {
//				modificado = true;
//			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al intentar modificar el usuario.");

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

	@Override
	public Map<Integer, Tipo> listarTiposArticulos() {
		Map<Integer, Tipo> tipoArticulo = new HashMap<>();
		conn = ConnectionMysql.openConnection();

		try {
			stmt = conn.prepareStatement(CONSULTA_TIPO);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Tipo tipo = new Tipo();
				tipo.setCodTipo(resultSet.getInt("cod_tipo"));
				tipo.setNombreTipo(resultSet.getString("nombre"));
				tipo.setStok(resultSet.getInt("stock"));
				tipoArticulo.put(tipo.getCodTipo(), tipo);
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

		return tipoArticulo;
	}

	@Override
	public int introducirTipoArticulo(Tipo tipo) {// revisar si esto funciona
		conn = ConnectionMysql.openConnection();
		int codigo = 0;
		try {
			comprobarTipo(tipo);
			// insercion de la tabla tipo
			stmt = (conn.prepareStatement(ALTA_TIPO, Statement.RETURN_GENERATED_KEYS));
			stmt.setString(1, tipo.getNombreTipo());
			stmt.setInt(2, tipo.getStok());
			stmt.executeUpdate();
			resultSet = stmt.getGeneratedKeys();
			if (resultSet.next()) {
				codigo = resultSet.getInt(1);
			}
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
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

	private boolean comprobarTipo(Tipo tipo) {
//comprobamos que el tipo que se vaya a introducir no exista en la base de datos y si existe se llamara a un procedimiento en el que se actializara el stock

		try {
			stmt = (conn.prepareStatement(CONSULTA_TIPO));

			stmt.setString(1, tipo.getNombreTipo());
			stmt.setString(2, tipo.getStok().toString());
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getString("nombre"));
				if (tipo.getNombreTipo().equalsIgnoreCase(resultSet.getString("nombre"))) {
					callableStatement = conn.prepareCall(ACTUALIZARSTOCKTIPO);
					callableStatement.setString(1, tipo.getNombreTipo());
					callableStatement.setInt(2, tipo.getStok());
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

}
