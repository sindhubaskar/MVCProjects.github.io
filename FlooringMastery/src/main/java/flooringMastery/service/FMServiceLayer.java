/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.service;

import flooringMastery.dao.FMPersistenceException;
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
public interface FMServiceLayer {

    public List<Tax> getAllTaxes() throws FMPersistenceException;

    public List<Product> getAllProducts() throws FMPersistenceException;

    public List<Order> getAllOrders(LocalDate date) throws FMPersistenceException, FileNotFoundException;

    public void addOrder(Order toAdd) throws FMPersistenceException;

    public void editOrder(Order editedOrder) throws FMPersistenceException;

    public Order getOrder(LocalDate date, int orderNumber) throws FMPersistenceException;

    public void removeOrder(LocalDate date, int orderNumber) throws FMPersistenceException;
}
