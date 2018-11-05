/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flooringmastery;

import flooringMastery.controller.FMController;
import flooringMastery.dao.FMPersistenceException;
import flooringMastery.dao.OrderDao;
import flooringMastery.dao.OrderDaoFileImpl;
import flooringMastery.dao.ProductDao;
import flooringMastery.dao.ProductDaoFileImpl;
import flooringMastery.dao.TaxDao;
import flooringMastery.dao.TaxDaoFileImpl;
import flooringMastery.service.FMServiceLayer;
import flooringMastery.service.FMServiceLayerImpl;
import flooringMastery.view.FMView;
import flooringMastery.view.UserIO;
import flooringMastery.view.UserIOFileImpl;
import java.io.FileNotFoundException;

/**
 *
 * @author sindhu
 */
public class App {
    
    public static void main(String[] args) throws FMPersistenceException,FileNotFoundException
    {
       UserIO myIo = new UserIOFileImpl();
       FMView myView = new FMView(myIo);
       TaxDao mytDao = new TaxDaoFileImpl();
       ProductDao mypDao=new ProductDaoFileImpl();
       OrderDao myoDao=new OrderDaoFileImpl();
       FMServiceLayer myService=new FMServiceLayerImpl(mytDao,mypDao,myoDao);
       FMController controller = new FMController( myView,myService);
       controller.run();
    }
    
}
