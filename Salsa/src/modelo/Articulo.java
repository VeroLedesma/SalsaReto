package modelo;

public class Articulo {
	private Integer codArticulo;
	private Float porcentajeDecuento, precio;
	private String color, modelo;
	private Temporada temporada;

	// Getters y Setters
	public Integer getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(Integer codArticulo) {
		this.codArticulo = codArticulo;
	}

	public float getPorcentajeDecuento() {
		return porcentajeDecuento;
	}

	public void setPorcentajeDecuento(Float porcentajeDecuento) {
		this.porcentajeDecuento = porcentajeDecuento;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Temporada getTemporada() {
		return temporada;
	}

	public void setTemporada(Temporada temporada) {
		this.temporada = temporada;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}