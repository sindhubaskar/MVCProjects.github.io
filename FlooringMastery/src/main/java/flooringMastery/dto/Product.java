/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author sindhu
 */
public class Product {
    
    String productType;
    BigDecimal costPSF;
    BigDecimal LaborCostPSF;
    
    
    public Product(String productType){
        this.productType=productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPSF() {
        return costPSF;
    }

    public void setCostPSF(BigDecimal costPSF) {
        this.costPSF = costPSF;
    }

    public BigDecimal getLaborCostPSF() {
        return LaborCostPSF;
    }

    public void setLaborCostPSF(BigDecimal LaborCostPSF) {
        this.LaborCostPSF = LaborCostPSF;
    }
    
    
    
}
