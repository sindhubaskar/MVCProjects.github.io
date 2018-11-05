/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Order;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sindhu
 */
public interface OrderDao {
    
    public List<Order> getAllOrders(LocalDate date)throws FMPersistenceException,FileNotFoundException;
    
    public void removeOrder(LocalDate date,int orderNumber)throws FMPersistenceException;
    
    public Order editOrder(Order toEdit)throws FMPersistenceException;
    
    public Order addOrder(Order toAdd)throws FMPersistenceException;
    
    public Order getOrder(LocalDate date,int orderNumber)throws FMPersistenceException;
}
