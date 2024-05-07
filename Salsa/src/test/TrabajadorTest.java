package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Trabajador;

class TrabajadorTest {

	
	

	    @Test
	    public void testGetSetNnss() {
	        Trabajador trabajador = new Trabajador();
	        
	      
	        trabajador.setNnss("1234567890");
	        
	      
	        assertEquals("1234567890", trabajador.getNnss());
	        assertNotNull("Comprobar que el número de seguridad social obtenido no sea nulo", trabajador.getNnss());
	        assertFalse( trabajador.getNnss().isEmpty());
	    }
	}

