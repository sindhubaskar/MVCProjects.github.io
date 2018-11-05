 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Order;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author sindhu
 */
public class OrderDaoTest {

    OrderDao dao = new OrderDaoFileImpl();

    public OrderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        String dataPath = "./OrderFiles/";
        File OrderFolder = new File(dataPath);
        File[] dataFileArray = OrderFolder.listFiles();
        for (File currentFile : dataFileArray) {
            currentFile.delete();
        }
        File seedFolder = new File("./SeedFiles");
        File[] filesArray = seedFolder.listFiles();
        for (File currentFile : filesArray) {
            Files.copy(Paths.get(currentFile.getAbsolutePath()), Paths.get(dataPath + currentFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllOrders method, of class OrderDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        LocalDate date = LocalDate.parse("06012013", DateTimeFormatter.ofPattern("MMddyyyy"));
        List<Order> testOrder = dao.getAllOrders(date);
        Order currentOrder=testOrder.get(0);
        
            assertEquals("Wise", currentOrder.getCustomerName());

            assertEquals("OH", currentOrder.getState());

            assertEquals(new BigDecimal("6.25"), currentOrder.getTaxRate());

            assertEquals("Wood", currentOrder.getProductType());

            assertEquals(new BigDecimal("100.00"), currentOrder.getArea());

            assertEquals(new BigDecimal("5.15"), currentOrder.getCostPSF());

            assertEquals(new BigDecimal("4.75"), currentOrder.getLaborCostPSF());

            assertEquals(new BigDecimal("515.00"), currentOrder.getMaterialCost());

            assertEquals(new BigDecimal("475.00"), currentOrder.getLaborCost());

            assertEquals(new BigDecimal("61.88"), currentOrder.getTotalTax());

            assertEquals(new BigDecimal("1051.88"), currentOrder.getTotalCost());

        
    }

    /**
     * Test of removeOrder method, of class OrderDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate date = LocalDate.parse("06012013", DateTimeFormatter.ofPattern("MMddyyyy"));
        
        Order currentOrder=dao.getOrder(date, 1);
        
        
            assertEquals("Wise", currentOrder.getCustomerName());

            assertEquals("OH", currentOrder.getState());

            assertEquals(new BigDecimal("6.25"), currentOrder.getTaxRate());

            assertEquals("Wood", currentOrder.getProductType());

            assertEquals(new BigDecimal("100.00"), currentOrder.getArea());

            assertEquals(new BigDecimal("5.15"), currentOrder.getCostPSF());

            assertEquals(new BigDecimal("4.75"), currentOrder.getLaborCostPSF());

            assertEquals(new BigDecimal("515.00"), currentOrder.getMaterialCost());

            assertEquals(new BigDecimal("475.00"), currentOrder.getLaborCost());

            assertEquals(new BigDecimal("61.88"), currentOrder.getTotalTax());

            assertEquals(new BigDecimal("1051.88"), currentOrder.getTotalCost());

        
            dao.removeOrder(date, 1);
            
             List<Order> testOrder = dao.getAllOrders(date);
           
            assertEquals(0,testOrder.size());
          
    }

    /**
     * Test of editOrder method, of class OrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
      LocalDate date = LocalDate.parse("06012013", DateTimeFormatter.ofPattern("MMddyyyy"));

        Order currentOrder=dao.getOrder(date, 1);
        
        
            assertEquals("Wise", currentOrder.getCustomerName());

            assertEquals("OH", currentOrder.getState());

            assertEquals(new BigDecimal("6.25"), currentOrder.getTaxRate());

            assertEquals("Wood", currentOrder.getProductType());

            assertEquals(new BigDecimal("100.00"), currentOrder.getArea());

            assertEquals(new BigDecimal("5.15"), currentOrder.getCostPSF());

            assertEquals(new BigDecimal("4.75"), currentOrder.getLaborCostPSF());

            assertEquals(new BigDecimal("515.00"), currentOrder.getMaterialCost());

            assertEquals(new BigDecimal("475.00"), currentOrder.getLaborCost());

            assertEquals(new BigDecimal("61.88"), currentOrder.getTotalTax());

            assertEquals(new BigDecimal("1051.88"), currentOrder.getTotalCost());
            
            
            
           
            
            currentOrder.setCustomerName("sindhu");
            
            currentOrder.setState("OH");
            
            currentOrder.setTaxRate(new BigDecimal("6.25"));
            
            currentOrder.setProductType("carpet");
            
            currentOrder.setCostPSF(new BigDecimal("2.25"));
            
            currentOrder.setLaborCostPSF(new BigDecimal("2.10"));
            
            
            Order toEdit=dao.editOrder(currentOrder);
            
            assertEquals("sindhu",toEdit.getCustomerName());
            
            assertEquals("OH",toEdit.getState());
            
            assertEquals(new BigDecimal("6.25"),toEdit.getTaxRate());
            
            assertEquals("carpet",toEdit.getProductType());
            
            assertEquals(new BigDecimal("2.25"),toEdit.getCostPSF());
            
            assertEquals(new BigDecimal("2.10"),toEdit.getLaborCostPSF());
            
            assertEquals(new BigDecimal("225.00"),toEdit.getMaterialCost());
            
            assertEquals(new BigDecimal("210.00"),toEdit.getLaborCost());
            
            assertEquals(new BigDecimal("27.19"),toEdit.getTotalTax());
            
            assertEquals(new BigDecimal("462.19"),toEdit.getTotalCost());
            

        
    }

    /**
     * Test of addOrder method, of class OrderDao.
     */
    @Test
    public void testAddOrder() throws Exception {
        LocalDate date = LocalDate.parse("06012013", DateTimeFormatter.ofPattern("MMddyyyy"));

        Order order = new Order(date);
        
        order.setCustomerName("Bob");
        
        order.setState("MI");
        
        order.setTaxRate(new BigDecimal("6.24"));
        
        order.setProductType("Tile");
        
        order.setArea(new BigDecimal("120.00"));
        
        order.setCostPSF(new BigDecimal("6.15"));
        
        order.setLaborCostPSF(new BigDecimal("4.75"));


        Order addedOrder=dao.addOrder(order);
        
        
        List<Order> allOrders=dao.getAllOrders(date);
        
        assertEquals(2,allOrders.size());
        
        Order validationOrder=dao.getOrder(date, addedOrder.getOrderNumber());
        
        assertEquals("Bob",validationOrder.getCustomerName());
        
        assertEquals("MI",validationOrder.getState());
        
        assertEquals(new BigDecimal("6.24"),validationOrder.getTaxRate());
        
        assertEquals("Tile",validationOrder.getProductType());
        
        assertEquals(new BigDecimal("120.00"),validationOrder.getArea());
        
        assertEquals(new BigDecimal("6.15"),validationOrder.getCostPSF());
        
        assertEquals(new BigDecimal("4.75"),validationOrder.getLaborCostPSF());
        
        assertEquals(new BigDecimal("738.00"),validationOrder.getMaterialCost());
        
        assertEquals(new BigDecimal("570.00"),validationOrder.getLaborCost());
        
        assertEquals(new BigDecimal("81.62"),validationOrder.getTotalTax());
        
        assertEquals(new BigDecimal("1389.62"),validationOrder.getTotalCost());
        
        
    }

    /**
     * Test of getOrder method, of class OrderDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        
        LocalDate date = LocalDate.parse("06012013", DateTimeFormatter.ofPattern("MMddyyyy"));
        //List<Order> testOrder = dao.getOrders(date);
       
        
        Order currentOrder= dao.getOrder(date, 1);


            assertEquals("Wise", currentOrder.getCustomerName());

            assertEquals("OH", currentOrder.getState());

            assertEquals(new BigDecimal("6.25"), currentOrder.getTaxRate());

            assertEquals("Wood", currentOrder.getProductType());

            assertEquals(new BigDecimal("100.00"), currentOrder.getArea());

            assertEquals(new BigDecimal("5.15"), currentOrder.getCostPSF());

            assertEquals(new BigDecimal("4.75"), currentOrder.getLaborCostPSF());

            assertEquals(new BigDecimal("515.00"), currentOrder.getMaterialCost());

            assertEquals(new BigDecimal("475.00"), currentOrder.getLaborCost());

            assertEquals(new BigDecimal("61.88"), currentOrder.getTotalTax());

            assertEquals(new BigDecimal("1051.88"), currentOrder.getTotalCost());

        
    }

}
