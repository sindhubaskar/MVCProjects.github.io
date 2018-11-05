/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.dao;

import flooringMastery.dto.Product;
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
public class ProductDaoFileImpl implements ProductDao {

    Map<String, Product> productMap = new HashMap<>();

    public static final String PRODUCT_FILE = "Products.txt";
    public static final String COMMA = ",";

    @Override
    public List<Product> getAllProducts() throws FMPersistenceException {
        loadProduct();
        return new ArrayList<Product>(productMap.values());
    }

    private void loadProduct() throws FMPersistenceException {

        Scanner scanner;
        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FMPersistenceException(
                    "-_- Could not load PRODUCT data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(COMMA);
            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPSF(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPSF(new BigDecimal(currentTokens[2]));

            productMap.put(currentProduct.getProductType(), currentProduct);
        }
        scanner.close();
    }
}
