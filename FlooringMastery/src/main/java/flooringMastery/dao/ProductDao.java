/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Product;
import java.util.List;

/**
 *
 * @author sindhu
 */
public interface ProductDao {

    public List<Product> getAllProducts() throws FMPersistenceException;

}
