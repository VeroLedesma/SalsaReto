package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Articulo;
import modelo.Temporada;

class ArticuloTest {

	
	
	@Test
	 public void testGetSetColorArticulo(){
		
		Articulo articulo= new Articulo ();
		articulo.setColor("Rojo");
		assertEquals( "Rojo", articulo.getColor());
		
	}
	

	@Test
	 public void testGetSetPrecio(){
		
		 Articulo articulo = new Articulo();
		 articulo.setPrecio(20.99f);
		 assertEquals(20.99f, articulo.getPrecio(), 0.01);
		 assertTrue("Verificar que el precio obtenido es mayor que cero", articulo.getPrecio() > 0);
		 assertNotNull("Verificar que el precio no sea nulo", articulo.getPrecio());
		

		    }


    @Test
    public void testGetSetCodArticulo() {
        Articulo articulo = new Articulo();
        articulo.setCodArticulo(1001);
        assertEquals(Integer.valueOf(1001), articulo.getCodArticulo());
            }
	
   
		
	@Test
	 public void testGetSetPorcentajeDescuento(){
		
		 Articulo articulo = new Articulo();
		 articulo.setPorcentajeDecuento(10.50f);
		 assertEquals(10.50f, articulo.getPorcentajeDecuento(), 0.01);
		 assertTrue("Verificar que el precio obtenido es mayor que cero", articulo.getPorcentajeDecuento() >= 0);
		 assertTrue("Verificar que el porcentaje de descuento obtenido es menor o igual a cien", articulo.getPorcentajeDecuento() <= 100);
		

		    }
	

    @Test
    public void testGetSetTemporada() {
        Articulo articulo = new Articulo();
        articulo.setTemporada(Temporada.VERANO); 
        assertEquals(Temporada.VERANO, articulo.getTemporada());
    }

    
    
    
    
    
    
}
