package modelo;

/**
 * La clase Tipo representa un tipo de artículo con su código, nombre y stock.
 */
public class Tipo {
	private Integer codTipo; // Código del tipo de artículo
	private String nombreTipo; // Nombre del tipo de artículo
	private Integer stock; // Stock del tipo de artículo

	/**
	 * Obtiene el código del tipo de artículo.
	 * 
	 * @return El código del tipo de artículo.
	 */
	public Integer getCodTipo() {
		return codTipo;
	}

	/**
	 * Establece el código del tipo de artículo.
	 * 
	 * @param codTipo El nuevo código del tipo de artículo.
	 */
	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	/**
	 * Obtiene el nombre del tipo de artículo.
	 * 
	 * @author melany, santi
	 * 
	 * @return El nombre del tipo de artículo.
	 */
	public String getNombreTipo() {
		return nombreTipo;
	}

	/**
	 * Establece el nombre del tipo de artículo.
	 * 
	 * @param nombreTipo El nuevo nombre del tipo de artículo.
	 */
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	/**
	 * Obtiene el stock del tipo de artículo.
	 * 
	 * @return El stock del tipo de artículo.
	 */
	public Integer getStock() {
		return stock;
	}

	/**
	 * Establece el stock del tipo de artículo.
	 * 
	 * @param stok El nuevo stock del tipo de artículo.
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
