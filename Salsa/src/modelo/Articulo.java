package modelo;

/**
 * La clase Articulo representa un artículo en el sistema de gestión de stock.
 * Contiene atributos para describir el artículo y métodos para acceder y modificar estos atributos.
 * 
 * @autor Vero
 * @version 1.0
 */
public class Articulo {
    private Integer codArticulo;
    private Float porcentajeDecuento, precio;
    private String color, nombreTipo;
    private Temporada temporada;

    /**
     * Obtiene el código del artículo.
     * 
     * @return el código del artículo
     */
    public Integer getCodArticulo() {
        return codArticulo;
    }

    /**
     * Establece el código del artículo.
     * 
     * @param codArticulo el nuevo código del artículo
     */
    public void setCodArticulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    /**
     * Obtiene el porcentaje de descuento del artículo.
     * 
     * @return el porcentaje de descuento
     */
    public Float getPorcentajeDecuento() {
        return porcentajeDecuento;
    }

    /**
     * Establece el porcentaje de descuento del artículo.
     * 
     * @param porcentajeDecuento el nuevo porcentaje de descuento
     */
    public void setPorcentajeDecuento(Float porcentajeDecuento) {
        this.porcentajeDecuento = porcentajeDecuento;
    }

    /**
     * Obtiene el precio del artículo.
     * 
     * @return el precio del artículo
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del artículo.
     * 
     * @param precio el nuevo precio del artículo
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el color del artículo.
     * 
     * @return el color del artículo
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color del artículo.
     * 
     * @param color el nuevo color del artículo
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene la temporada del artículo.
     * 
     * @return la temporada del artículo
     */
    public Temporada getTemporada() {
        return temporada;
    }

    /**
     * Establece la temporada del artículo.
     * 
     * @param temporada la nueva temporada del artículo
     */
    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    /**
     * Obtiene el nombre del tipo de artículo.
     * 
     * @return el nombre del tipo de artículo
     */
    public String getNombreTipo() {
        return nombreTipo;
    }

    /**
     * Establece el nombre del tipo de artículo.
     * 
     * @param nombreTipo el nuevo nombre del tipo de artículo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }
}
