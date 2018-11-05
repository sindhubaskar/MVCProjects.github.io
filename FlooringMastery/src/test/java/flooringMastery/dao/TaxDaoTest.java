/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sindhu
 */
public class TaxDaoTest {
    
    TaxDao tdao=new TaxDaoFileImpl();
    
    public TaxDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllTaxes method, of class TaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        
        List<Tax> allStates=tdao.getAllTaxes();
       
        Tax currentTax=allStates.get(0);
        
        assertEquals("PA",currentTax.getStateName());
        
        assertEquals(new BigDecimal("6.75"),currentTax.getTaxRate());
    }

    
}
