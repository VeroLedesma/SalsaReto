package utilidades;



public class Utilidades {

	// Método para verificar un email
	public static boolean validarEmail(String email) throws IllegalArgumentException {
		try {
			String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			if (email.matches(emailRegex)) {
				return true;
			} else {
				throw new IllegalArgumentException("El email proporcionado no es válido");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para verificar un DNI
	public static boolean validarDNI(String dni) throws IllegalArgumentException {
		try {
			if (dni.length() == 9) {
				if (dni.substring(0, 8).matches("\\d+")) {
					String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
					char letraCalculada = letras.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
					char letraDNI = dni.charAt(8);
					if (letraCalculada == letraDNI) {
						return true;
					} else {
						throw new IllegalArgumentException("El DNI proporcionado no es válido");
					}
				}
			}
			throw new IllegalArgumentException("El DNI proporcionado no es válido");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}