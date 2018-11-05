/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 *
 * @author sindhu
 */
public class Order {
    
    LocalDate date;
    int orderNumber;
    String customerName;
    String state;
    BigDecimal taxRate;
    String productType;
    BigDecimal area;
    BigDecimal costPSF;
    BigDecimal laborCostPSF;
  
   
    
    public Order (LocalDate date){
        this.date=date;
       
        
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setCostPSF(BigDecimal costPSF) {
        this.costPSF = costPSF;
    }

    public void setLaborCostPSF(BigDecimal laborCostPSF) {
        this.laborCostPSF = laborCostPSF;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public String getCustomerName() {
        return customerName;
    }
    
    public String getState() {
        return state;
    }
    
    public BigDecimal getTaxRate() {
        return taxRate;
    }
    
    public String getProductType() {
        return productType;
    }
    
    public BigDecimal getArea() {
        return area;
    }
    
    public BigDecimal getCostPSF() {
        return costPSF;
    }
    
    public BigDecimal getLaborCostPSF() {
        return laborCostPSF;
    }
    
    public BigDecimal getMaterialCost() {
        return costPSF.multiply(area).setScale(2, RoundingMode.HALF_UP);
        
    }
    
    public BigDecimal getLaborCost() {
        return laborCostPSF.multiply(area).setScale(2, RoundingMode.HALF_UP);
        
    }
    
    public BigDecimal getTotalTax() {
        BigDecimal totalCost =getMaterialCost().add(getLaborCost());
        BigDecimal taxPerc=taxRate.divide(new BigDecimal(100));
        BigDecimal totalTax=totalCost.multiply(taxPerc).setScale(2, RoundingMode.HALF_UP);
        return totalTax;
    }
    
    public BigDecimal getTotalCost() {
        return getMaterialCost().add(getLaborCost()).add(getTotalTax().setScale(2, RoundingMode.HALF_UP));
    }
    
}
