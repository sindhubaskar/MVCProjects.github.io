/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author sindhu
 */
public class OrderDaoFileImpl implements OrderDao {

    

    @Override
    public List<Order> getAllOrders(LocalDate date) throws FMPersistenceException {
        List<Order> fileList = new ArrayList<Order>();

        String fileName = convertDateToFileName(date);
        fileList = new ArrayList<Order>();
        File f = new File(fileName);
        if (f.exists()) {
            fileList = loadOrder(date);
        }

        return fileList;

    }

    @Override
    public void removeOrder( LocalDate date,int orderNumber) throws FMPersistenceException {
        
       List<Order> orders=getAllOrders(date);
      
       List<Order> neworders=orders.stream()
                   .filter(n->n.getOrderNumber()!=orderNumber)
                   .collect(Collectors.toList());
       
       writeOrder(neworders,date);
    }

   

    @Override
    public Order addOrder(Order toAdd) throws FMPersistenceException {

        LocalDate date = toAdd.getDate();
        List<Order> fileList = getAllOrders(date);

        if (fileList.isEmpty()) {
            toAdd.setOrderNumber(1);

        } else {
            Order maxOrderNumber = fileList
                    .stream()
                    .max(Comparator.comparing(Order::getOrderNumber))
                    .orElseThrow(NoSuchElementException::new);
            toAdd.setOrderNumber(maxOrderNumber.getOrderNumber() + 1);

        }
        fileList.add(toAdd);

        writeOrder(fileList, date);
        return toAdd;

    }

    private String convertDateToFileName(LocalDate date) throws FMPersistenceException {

        LocalDate localDate = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String formattedString = localDate.format(formatter);

        return "./OrderFiles/Orders_" + formattedString + ".txt";

    }

    private List<Order> loadOrder(LocalDate date) throws FMPersistenceException {

        String fileName = convertDateToFileName(date);
        File f = new File(fileName);

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(f)));

        } catch (FileNotFoundException e) {
            throw new FMPersistenceException(
                    "-_- Could not load FILE data into memory.", e);
        }
        List<Order> fileList = new ArrayList<Order>();
        String currentLine;

        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(",");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

            Order currentOrder = new Order(date);
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentOrder.setState(currentTokens[2]);
            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]).setScale(2, RoundingMode.HALF_UP));
            currentOrder.setProductType(currentTokens[4]);
            currentOrder.setArea(new BigDecimal(currentTokens[5]).setScale(2, RoundingMode.HALF_UP));
            currentOrder.setCostPSF(new BigDecimal(currentTokens[6]).setScale(2, RoundingMode.HALF_UP));
            currentOrder.setLaborCostPSF(new BigDecimal(currentTokens[7]).setScale(2, RoundingMode.HALF_UP));
           
            fileList.add(currentOrder);

        }
        return fileList;
    }

    private void writeOrder(List<Order> wOrder, LocalDate date) throws FMPersistenceException {
        String header = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,TaxTotal";
        String fileName = convertDateToFileName(date);
        File newFile = new File(fileName);

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(newFile));
        } catch (IOException e) {
            throw new FMPersistenceException(
                    "Could not save Order data.", e);
        }
        out.println(header);
        for (Order currentOrder : wOrder) {
            
            out.println(currentOrder.getOrderNumber()+","
                    + currentOrder.getCustomerName()+","
                    + currentOrder.getState()+","
                    +currentOrder.getTaxRate()+","
                    + currentOrder.getProductType()+","
                    +currentOrder.getArea()+","
                    + currentOrder.getCostPSF()+","
                    + currentOrder.getLaborCostPSF()+","
                    + currentOrder.getMaterialCost()+","
                    + currentOrder.getTotalCost()+","
                    + currentOrder.getLaborCost()+","
                    + currentOrder.getTotalTax()+","
                    + currentOrder.getTotalCost());
            out.flush();
        }

        out.close();

    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws FMPersistenceException {
        List<Order> fileList = getAllOrders(date);
        Order defaultOrder=null;
        List<Order> order=  fileList.stream()
                                     .filter(o->o.getOrderNumber()==orderNumber)
                                     .collect(Collectors.toList());
       
              if(!order.isEmpty()){
                  defaultOrder=order.get(0);
                  
              }  
        loadOrder(date);
        return defaultOrder;
        
    }
    
     @Override
    public Order editOrder(Order toEdit) throws FMPersistenceException {
        removeOrder(toEdit.getDate(),toEdit.getOrderNumber());  
        List<Order> fileList = getAllOrders(toEdit.getDate());
        
        fileList.add( toEdit);
         
        writeOrder(fileList,toEdit.getDate());  
          
       return toEdit;
    }
}
