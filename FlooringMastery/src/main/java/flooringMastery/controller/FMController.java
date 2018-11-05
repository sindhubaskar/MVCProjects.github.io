/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.controller;

import flooringMastery.dao.FMPersistenceException;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;
import flooringMastery.service.FMServiceLayer;
import flooringMastery.view.FMView;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sindhu
 */
public class FMController {

    FMView view;
    FMServiceLayer service;

    public FMController(FMView view, FMServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() throws FMPersistenceException, FileNotFoundException {

        int menuSelection = 0;
        boolean keepGoing = false;
        while (!keepGoing) {
            

            menuSelection = getUserMenuChoice();

            switch (menuSelection) {
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    keepGoing = true;
                    break;
                default:
                    UnknownCommand();
            }
        }
        
          exitMessage();

    }

    private int getUserMenuChoice() {
        return view.getMenuSelection();
    }

    private void displayOrders() throws FMPersistenceException, FileNotFoundException {

        LocalDate date1 = view.getDate();
        List<Order> oList = service.getAllOrders(date1);
        if (oList != null) {
            view.displayAllOrders(oList);
        }
    }

    private void addOrder() throws FMPersistenceException {

        //need to get states and products
        List<Tax> states = service.getAllTaxes();
        List<Product> products = service.getAllProducts();

        //get order information from user
        Order toAdd = view.getNewOrderData(states, products);

        if (toAdd != null) {
            //save to disk
            service.addOrder(toAdd);

        }

    }

    private void editOrder() throws FMPersistenceException {

        LocalDate date = view.getDate();
        int orderNumber = view.getOrderNumber();
        Order toEdit = service.getOrder(date, orderNumber);
        List<Tax> states = service.getAllTaxes();
        List<Product> products = service.getAllProducts();
        Order EditedOrder = view.editOrder(toEdit, states, products);
        service.editOrder(EditedOrder);

    }

    private void removeOrder() throws FMPersistenceException {
        LocalDate date = view.getDate();
        int orderNumber = view.getOrderNumber();
        service.removeOrder(date, orderNumber);
    }

    private void UnknownCommand() {
        view.displayUnknownCommand();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

}
