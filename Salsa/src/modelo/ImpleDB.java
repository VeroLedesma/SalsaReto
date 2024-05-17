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

/**
 * creada la clase para la gestion del enlace con la base dedatos.
 * 
 *
 */
public class ImpleDB implements Dao {

	// Objeto para crear conexión mysql
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet resultSet;
	private CallableStatement callableStatement;
	// Consultas a la Base de Datos
	private final String CONSULTA_PERSONA = "SELECT dni, nombre, apellido,fechaNac,contrasena,  direccion, email, genero FROM persona ";
	private final String ALTA_PERSONA = "INSERT INTO persona (dni, nombre, apellido, fechaNac, contrasena, direccion, email, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private final String ASIGNACIONUSUARIO = "{CALL setPersonaInvitado(?,?,?)}";
	private final String ASIGNACIONTRABAJADOR = "{CALL setTrabajador(?,?,?,?)}";
	private final String ALTA_ARTICULO = "INSERT INTO articulo (color, temporada, precio, descuento, nombreImg, cod_tipo) VALUES (  ?, ?, ?, ?, ?, ?)";
	private final String CONSULTA_COMPROBAR_USUARIO = "SELECT dni, nombre, apellido,fechaNac, direccion, email, genero FROM persona WHERE email=? AND contrasena=?";
	private final String MODIFICACION_ARTICULO = "UPDATE articulo SET color = ?, temporada = ?, precio=?, descuento= ? , cod_tipo=? WHERE cod_articulo = ?";
	private final String MODIFICACION_USUARIO = "UPDATE persona SET nombre = ?, apellido = ?,  contrasena=?, direccion = ?, email = ?, genero= ? WHERE dni = ?";
	private final String CONSULTA_ARTICULO = "SELECT a.cod_articulo,a.color,a.temporada, a.precio,a.descuento,t.nombre, a.nombreImg FROM articulo a JOIN tipo t ON a.cod_tipo = t.cod_tipo";
	private final String CONSULTA_TIPO = "SELECT cod_tipo, nombre, stock FROM tipo";
	private final String ALTA_TIPO = "{CALL actualizar_o_insertar_stock(?,?)}";
	private final String BUSCAR_ENCARGADO = "SELECT comprobarEncargado() AS Encargado";
	private final String ELIMINAR_PERSONA = "DELETE FROM persona where dni=? ";
	private final String ELIMINAR_ARTICULO = "DELETE FROM articulo where cod_articulo=?";
	private final String BUSCAR_USUARIO = "SELECT u.dni, u.fechaReg FROM usuario u, persona p WHERE u.dni = p.dni AND p.email=?";
	private final String BUSCAR_ARTICULO = "SELECT cod_articulo, color, temporada, precio, descuento, cod_tipo FROM articulo WHERE cod_articulo = ?";
	private final String ALTA_COMPRA = "INSERT INTO compra (cantidad, dni, cod_articulo) VALUES (1, ?, ?)";

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
				art.setTemporada(Temporada.valueOf(resultSet.getString("temporada").toString().toUpperCase()));
				art.setPrecio(resultSet.getFloat("precio"));
				art.setPorcentajeDecuento(resultSet.getFloat("descuento"));
				art.setNombreImg(resultSet.getString("nombreImg"));
				art.setNombreTipo(resultSet.getString("nombre"));
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
		List<Persona> personas = new ArrayList<>();
		conn = ConnectionMysql.openConnection();

		try {
			stmt = conn.prepareStatement(CONSULTA_PERSONA);
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

				if (((Trabajador) per).isEncargado() == true) { //
					System.out.println("El trabajador es encargado" + ((Trabajador) per).isEncargado());
					callableStatement.setInt(4, 1);

				} else {
					System.out.println("El trabajador es trabajador normal" + ((Trabajador) per).isEncargado());
					callableStatement.setInt(4, 0);
				}
				callableStatement.execute();

			} else if (per instanceof Usuario) {
				// Inserción en la tabla usuario llamando a un procedimiento creado en la base
				// de datos que le asignara el rol de invitado cuyos permisos estan definidos
				callableStatement = conn.prepareCall(ASIGNACIONUSUARIO);
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
			stmt = (conn.prepareStatement(ALTA_ARTICULO, Statement.RETURN_GENERATED_KEYS));
			stmt.setString(1, art.getColor());
			stmt.setString(2, art.getTemporada().toString());
			stmt.setFloat(3, art.getPrecio());
			stmt.setFloat(4, art.getPorcentajeDecuento());
			stmt.setString(5, art.getNombreImg());
			stmt.setInt(6, Integer.parseInt(art.getNombreTipo().split("_")[1]));
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

		conn = ConnectionMysql.openConnection();
		boolean modificado = false;
		try {

			stmt = (conn.prepareStatement(MODIFICACION_USUARIO));

			stmt.setString(1, per.getNombre());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getContrasena());
			stmt.setString(4, per.getDireccion());
			stmt.setString(5, per.getEmail());
			stmt.setString(6, per.getGenero().toString());
			stmt.setString(7, per.getDni());
			int lineaAfectada = stmt.executeUpdate();

			if (lineaAfectada > 0) {
				modificado = true;
			}

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
	public Map<Integer, Tipo> listarTiposArticulos() { // verificar si se usa
		Map<Integer, Tipo> tipoArticulo = new HashMap<>();
		conn = ConnectionMysql.openConnection();
		try {
			stmt = conn.prepareStatement(CONSULTA_TIPO);
			resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Tipo tipo = new Tipo();
				tipo.setCodTipo(resultSet.getInt("cod_tipo"));
				tipo.setNombreTipo(resultSet.getString("nombre"));
				tipo.setStock(resultSet.getInt("stock"));
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
	public boolean introducirTipoArticulo(Tipo tipo) {
		conn = ConnectionMysql.openConnection();

		try {
			// comprobamos que el tipo que se vaya a introducir no exista en la base de
			// datos y si existe se llamara a un procedimiento en el que se actializara el
			// stock y si el tipo de articulo no existe entonces se insertara como uno nuevo
			stmt = (conn.prepareStatement(ALTA_TIPO, Statement.RETURN_GENERATED_KEYS));
			callableStatement = conn.prepareCall(ALTA_TIPO);
			callableStatement.setString(1, tipo.getNombreTipo());
			callableStatement.setInt(2, tipo.getStock());
			boolean existe = callableStatement.execute();
			return existe;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

				if (conn != null) {
					ConnectionMysql.closeConnection();
				}
			}

		}
	}

	@Override
	public int comprobarEncargado() {
		conn = ConnectionMysql.openConnection();
		int existe = 0;
		try {
			// llamamos a un a funcion que nos buscara en la bhase de
			// datos si hay o no un encargado, si la funcion devuelve 1 significa que un
			// encargado ya existe entonces ya no se dara de alta otro encargado
			stmt = conn.prepareStatement(BUSCAR_ENCARGADO);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				existe = resultSet.getInt("Encargado");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

				if (conn != null) {
					ConnectionMysql.closeConnection();
				}
			}
		}
		return existe;

	}

	@Override
	public boolean eliminarArticulo(int art) {

		conn = ConnectionMysql.openConnection();
		try {
			stmt = conn.prepareStatement(ELIMINAR_ARTICULO);
			stmt.setInt(1, art);
			stmt.execute();
			return true;
		} catch (SQLException excepcion) {
			excepcion.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

				if (conn != null) {
					ConnectionMysql.closeConnection();
				}
			}
		}

		return false;
	}

	@Override
	public boolean eliminarPersona(String per) {
		conn = ConnectionMysql.openConnection();
		try {
			stmt = conn.prepareStatement(ELIMINAR_PERSONA);
			stmt.setString(1, per);
			stmt.execute();
			return true;
		} catch (SQLException excepcion) {
			excepcion.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {

				if (conn != null) {
					ConnectionMysql.closeConnection();
				}
			}
		}

		return false;
	}

	@Override
	public boolean modificarArticulo(Articulo art) {

		conn = ConnectionMysql.openConnection();
		boolean modificado = false;
		try {

			stmt = (conn.prepareStatement(CONSULTA_TIPO.concat(" WHERE nombre=?")));
			stmt.setString(1, art.getNombreTipo());
			resultSet = stmt.executeQuery();
			Tipo tipo = new Tipo();
			if (resultSet.next()) {
				tipo.setCodTipo(resultSet.getInt("cod_tipo"));
				tipo.setNombreTipo(resultSet.getString("nombre"));
				tipo.setStock(resultSet.getInt("stock"));
			}
			System.out.println(tipo.getNombreTipo());
			stmt = (conn.prepareStatement(MODIFICACION_ARTICULO));
			stmt.setString(1, art.getColor());
			stmt.setString(2, art.getTemporada().toString());
			stmt.setFloat(3, art.getPrecio());
			stmt.setFloat(4, art.getPorcentajeDecuento());
			stmt.setInt(5, tipo.getCodTipo());
			stmt.setInt(6, art.getCodArticulo());
			int lineaAfectada = stmt.executeUpdate();

			System.out.println(lineaAfectada);
			if (lineaAfectada > 0) {
				modificado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error al intentar modificar el artículo.");

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar el ResultSet.");
					e.printStackTrace();
				}
			}

			// Cerrar el PreparedStatement
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Error al cerrar el PreparedStatement.");
					e.printStackTrace();
				}
			}

			// Cerrar la conexión a la base de datos
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Error en el cierre de la conexión.");
					e.printStackTrace();
				}
			}

		}
		return modificado;
	}

	@Override
	public Usuario obtenerUsuario(String email) {
		conn = ConnectionMysql.openConnection();
		Usuario nuevo = new Usuario();
		try {
			stmt = (conn.prepareStatement(BUSCAR_USUARIO));
			stmt.setString(1, email);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				nuevo.setDni(resultSet.getString("dni"));
				nuevo.setFechaRegistro(LocalDate.parse(resultSet.getString("fechaReg")));
			}
			return nuevo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nuevo;
	}

	@Override
	public Articulo obtenerArticulo(int codArt) {
		conn = ConnectionMysql.openConnection();
		Articulo articulo = new Articulo();
		int codTipo = 0;
		Tipo tipo = new Tipo();

		try {
			stmt = (conn.prepareStatement(BUSCAR_ARTICULO));
			stmt.setInt(1, codArt);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				articulo.setCodArticulo(Integer.parseInt(resultSet.getString("cod_articulo")));
				articulo.setColor(resultSet.getString("color"));
				articulo.setTemporada(Temporada.valueOf(resultSet.getString("temporada").toUpperCase()));
				articulo.setPrecio(Float.parseFloat(resultSet.getString("precio")));
				articulo.setPorcentajeDecuento(resultSet.getFloat("descuento"));
				codTipo = Integer.parseInt(resultSet.getString("cod_tipo"));
			}

			stmt = (conn.prepareStatement(CONSULTA_TIPO.concat(" WHERE cod_tipo = ?")));
			stmt.setInt(1, codTipo);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				tipo.setCodTipo(Integer.parseInt(resultSet.getString("cod_tipo")));
				tipo.setNombreTipo(resultSet.getString("nombre"));
				tipo.setStock(Integer.parseInt(resultSet.getString("stock")));
			}

			articulo.setNombreTipo(tipo.getNombreTipo());
			return articulo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return articulo;
	}

	@Override
	public boolean aniadirCompra(String dni, int codArticulo) {
		conn = ConnectionMysql.openConnection();
		try {
			stmt = (conn.prepareStatement(ALTA_COMPRA));
			stmt.setString(1, dni);
			stmt.setInt(2, codArticulo);
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
