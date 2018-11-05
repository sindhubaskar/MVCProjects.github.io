/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Tax;
import java.util.List;

/**
 *
 * @author sindhu
 */
public interface TaxDao {

    public List<Tax> getAllTaxes() throws FMPersistenceException;

}
