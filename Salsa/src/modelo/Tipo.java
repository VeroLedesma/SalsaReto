package modelo;

/**
 * Clase que representa un tipo de artículo.
 */
public class Tipo {
	private Integer codTipo;
	private String nombreTipo;
	private Integer stock;

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
	 * @param codTipo El código del tipo de artículo.
	 */
	public void setCodTipo(Integer codTipo) {
		this.codTipo = codTipo;
	}

	/**
	 * Obtiene el nombre del tipo de artículo.
	 * 
	 * @return El nombre del tipo de artículo.
	 */
	public String getNombreTipo() {
		return nombreTipo;
	}

	/**
	 * Establece el nombre del tipo de artículo.
	 * 
	 * @param nombreTipo El nombre del tipo de artículo.
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
	 * @param stock El stock del tipo de artículo.
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}