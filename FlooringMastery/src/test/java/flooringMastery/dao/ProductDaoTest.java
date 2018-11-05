/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Product;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
public class ProductDaoTest {
    
    ProductDao pdao = new ProductDaoFileImpl();
    
    public ProductDaoTest() {
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
     * Test of getAllProducts method, of class ProductDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        
        List<Product> allProducts=pdao.getAllProducts();
        
        Product currentProduct=allProducts.get(0);
        
        assertEquals("Laminate",currentProduct.getProductType());
        
        assertEquals(new BigDecimal("1.75"),currentProduct.getCostPSF());
        
        assertEquals(new BigDecimal("2.10"),currentProduct.getLaborCostPSF());
        
    }

    
    
}
