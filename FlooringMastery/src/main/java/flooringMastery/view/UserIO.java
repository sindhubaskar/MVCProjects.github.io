/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author sindhu
 */
public interface UserIO {
    
    void print(String msg);
    
    int readInt(String prompt);
    
    int readInt(String prompt,int min,int max);
    
    float readFloat(String prompt);
    
    float readFloat(String prompt,float min,float max);
    
    double readDouble(String prompt);
    
    double readDouble(String prompt,double min,double max);
    
    BigDecimal readBigDecimal(String prompt);
    
    LocalDate readLocalDate(String prompt);
    
    String readString(String prompt);
    
    
    
}
