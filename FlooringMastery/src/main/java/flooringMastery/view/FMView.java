/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.view;

import flooringMastery.dao.FMPersistenceException;
import flooringMastery.dto.Order;
import flooringMastery.dto.Product;
import flooringMastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sindhu
 */
public class FMView {

    UserIO io;

    public FMView(UserIO io) {
        this.io = io;
    }

    public int getMenuSelection() {

        io.print("***FLOORING PROGRAM***");
        io.print("1.Display Orders");
        io.print("2.Add an Order");
        io.print("3.Edit an Order");
        io.print("4.Remove an Order");
        io.print("5.Quit");

        return io.readInt("Enter your Choice from 1 to 5:");

    }

    public void displayUnknownCommand() {
        io.print("UNKNOWN COMMAND");
    }

    public void displayExitMessage() {
        io.print("GOOD BYE!!");
    }

    public LocalDate getDate() throws FMPersistenceException {
        return io.readLocalDate("Please enter the Date(MM/DD/YYYY)");
    }

    public int getOrderNumber() throws FMPersistenceException {
        return io.readInt("Enter the orderNumber:");
    }

    public void displayAllOrders(List<Order> orderList) {
        if (orderList != null) {
            for (Order currentOrder : orderList) {
                io.print(currentOrder.getOrderNumber() + " ,"
                        + currentOrder.getCustomerName() + " , "
                        + currentOrder.getState() + " , "
                        + currentOrder.getTaxRate() + ", "
                        + currentOrder.getProductType() + ","
                        + currentOrder.getArea() + ","
                        + currentOrder.getCostPSF() + ","
                        + currentOrder.getLaborCostPSF() + ","
                        + currentOrder.getMaterialCost() + ","
                        + currentOrder.getLaborCost() + ","
                        + currentOrder.getTotalTax() + ","
                        + currentOrder.getTotalCost());

            }

        } else {
            io.print("NO such file found");
        }

    }

    public Order getNewOrderData(List<Tax> states, List<Product> products) {

        LocalDate date = io.readLocalDate("Enter the Date:");
        String uName = io.readString("Enter your Name:");

        Tax selectedState = selectState(states);
        Product selectedProduct = selectProduct(products);
        BigDecimal uArea = io.readBigDecimal("Enter the Area:");
        io.print("Summary of your order");
        io.print("CustomerName:" + uName);
        io.print("State:" + selectedState.getStateName());
        io.print("Product Type:" + selectedProduct.getProductType());
        io.print("Area:" + uArea);

        io.readString("Do you want to commit your order?(YES/NO)");

        String uReply = " ";
        Order newOrder = null;

        if (!uReply.equalsIgnoreCase("NO")) {

            newOrder = new Order(date);

            newOrder.setCustomerName(uName);
            newOrder.setState(selectedState.getStateName());
            newOrder.setTaxRate(selectedState.getTaxRate());
            newOrder.setProductType(selectedProduct.getProductType());
            newOrder.setCostPSF(selectedProduct.getCostPSF());
            newOrder.setLaborCostPSF(selectedProduct.getLaborCostPSF());
            newOrder.setArea(uArea);

        }

        return newOrder;

    }

    private Tax selectState(List<Tax> states) {
        boolean validInput = false;
        Tax selectedState = null;
        while (!validInput) {
            String userChoice = io.readString("Please Enter your State:");
            selectedState = getStateFromList(userChoice, states);
            if (selectedState != null) {
                validInput = true;
            }

        }

        return selectedState;

    }

    private Tax editState(List<Tax> states, String existingState) {
        boolean validInput = false;
        Tax selectedState = null;
        while (!validInput) {
            String userChoice = io.readString("Press enter to keep the current state Value : " + existingState);
            if (userChoice.equals("")) {
                userChoice = existingState;
            }
            selectedState = getStateFromList(userChoice, states);
            if (selectedState != null) {
                validInput = true;
            }

        }

        return selectedState;

    }

    private Product editProductType(List<Product> types, String existingType) {
        boolean validInput = false;
        Product selectedType = null;
        while (!validInput) {
            String userChoice = io.readString("Press enter to keep the current ProductType Value : " + existingType);
            if (userChoice.equals("")) {
                userChoice = existingType;
            }
            selectedType = getProductTypeFromList(userChoice, types);
            if (selectedType != null) {
                validInput = true;
            }

        }

        return selectedType;

    }

    private Product selectProduct(List<Product> products) {
        boolean validInput = false;
        Product selectedProduct = null;
        while (!validInput) {
            String userChoice = io.readString("Please enter the Product Type:");
            for (Product productType : products) {
                if (userChoice.equalsIgnoreCase(productType.getProductType())) {
                    validInput = true;

                    selectedProduct = productType;
                }

            }
        }

        return selectedProduct;
    }

    public Order editOrder(Order editOrder, List<Tax> states, List<Product> productTypes) {
        LocalDate date = editOrder.getDate();
        int orderNumber = editOrder.getOrderNumber();

        String userChoice = io.readString("Press enter to keep the current customer value:" + editOrder.getCustomerName());
        if (userChoice.equals("")) {
            userChoice = editOrder.getCustomerName();
        }
        editOrder.setCustomerName(userChoice);

        Tax newState = editState(states, editOrder.getState());

        Product newProductType = editProductType(productTypes, editOrder.getProductType());

      
        
        String userChoice1 = io.readString("Press enter to keep the current Area value:" + editOrder.getArea());
        
        if(!"".equals(userChoice1)){
            BigDecimal b=new BigDecimal(userChoice1);
            editOrder.getArea();
        }
        
        return editOrder;
    }

    private Tax getStateFromList(String userChoice, List<Tax> states) {
        Tax selectedState = null;
        for (Tax state : states) {
            if (userChoice.equalsIgnoreCase(state.getStateName())) {

                selectedState = state;
            }

        }
        return selectedState;
    }

    private Product getProductTypeFromList(String userChoice, List<Product> productTypes) {
        Product selectedProductType = null;
        for (Product type : productTypes) {
            if (userChoice.equalsIgnoreCase(type.getProductType())) {

                selectedProductType = type;
            }

        }
        return selectedProductType;
    }
}
