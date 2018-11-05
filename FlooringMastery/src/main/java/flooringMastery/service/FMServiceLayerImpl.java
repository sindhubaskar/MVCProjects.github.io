/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.service;


import flooringMastery.dao.FMPersistenceException;
import flooringMastery.dao.OrderDao;
import flooringMastery.dao.ProductDao;
import flooringMastery.dao.TaxDao;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sindhu
 */
public class FMServiceLayerImpl implements FMServiceLayer{
    
    TaxDao tdao;
    ProductDao pdao;
    OrderDao odao;
    
    
    public FMServiceLayerImpl(TaxDao tdao,ProductDao pdao,OrderDao odao){
        this.tdao=tdao;
        this.pdao=pdao;
        this.odao=odao;
       
        
    }

    @Override
    public List<Tax> getAllTaxes() throws FMPersistenceException {
        return tdao.getAllTaxes();
    }

    @Override
    public List<Product> getAllProducts() throws FMPersistenceException {
        return pdao.getAllProducts();
    }

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FMPersistenceException ,FileNotFoundException{
        return odao.getAllOrders(date);
    }

    @Override
    public void addOrder(Order toAdd) throws FMPersistenceException {
         odao.addOrder(toAdd);
    }

    @Override
    public void editOrder(Order editedOrder) throws FMPersistenceException {
         odao.editOrder(editedOrder);
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FMPersistenceException {
        return odao.getOrder(date, orderNumber);
         
    }

    @Override
    public void removeOrder(LocalDate date, int orderNumber) throws FMPersistenceException {
         odao.removeOrder(date, orderNumber);
    }
    
}   
