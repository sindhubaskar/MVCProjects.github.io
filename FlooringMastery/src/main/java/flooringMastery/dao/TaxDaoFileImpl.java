/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author sindhu
 */
public class TaxDaoFileImpl implements TaxDao {

    Map<String, Tax> taxMap = new HashMap<>();

    public static final String TAX_FILE = "Taxes.txt";
    public static final String COMMA = ",";

    @Override
    public List<Tax> getAllTaxes() throws FMPersistenceException {
        loadTax();
        return new ArrayList<Tax>(taxMap.values());
    }


    private void loadTax() throws FMPersistenceException {

        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FMPersistenceException(
                    "-_- Could not load TAX data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(COMMA);
            Tax currentTax = new Tax(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            taxMap.put(currentTax.getStateName(), currentTax);

        }
        scanner.close();
    }

}
