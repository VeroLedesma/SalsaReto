package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modelo.Tipo;

class TipoTest {


    @Test
    public void testGetSetCodTipo() {
        Tipo tipo = new Tipo();
        
        tipo.setCodTipo(101);
        assertEquals(Integer.valueOf(101), tipo.getCodTipo());
        assertNotNull("Comprobar que el código obtenido no sea nulo", tipo.getCodTipo());
        assertTrue("El código obtenido es mayor que cero", tipo.getCodTipo() > 0);
    }

    @Test
    public void testGetSetNombreTipo() {
        Tipo tipo = new Tipo();
        
        tipo.setNombreTipo("Pantalón");
        
        assertEquals( "Pantalon", tipo.getNombreTipo());
        assertNotNull("Verificar que el nombre obtenido no sea nulo", tipo.getNombreTipo());
        
    }

    @Test
    public void testGetSetStok() {
        Tipo tipo = new Tipo();
        
        tipo.setStok(50);
        
        assertEquals( Integer.valueOf(50), tipo.getStok());
        assertNotNull("Verificar que el stock obtenido no sea nulo", tipo.getStok());
        assertTrue("Ver que el stock obtenido es mayor o igual que cero", tipo.getStok() >= 0);
    }
}
