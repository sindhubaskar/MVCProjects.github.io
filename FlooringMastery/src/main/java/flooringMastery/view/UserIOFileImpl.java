/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringMastery.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author sindhu
 */
public class UserIOFileImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String msg) {

        System.out.println(msg);
    }

    @Override
    public int readInt(String prompt) {

        int toReturn = Integer.MIN_VALUE;
        System.out.println(prompt);

        boolean isValid = false;
        while (!isValid) {
            try {
                String toCheck = sc.nextLine();
                toReturn = Integer.parseInt(toCheck);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {

        int toReturn = Integer.MIN_VALUE;
        
        boolean isValid = false;
        while (!isValid) {
            try {
                toReturn = readInt(prompt);

                if (toReturn < min || toReturn > max) {
                    System.out.println("Please enter the numner between" + min + "and" + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        return toReturn;

    }

    @Override
    public float readFloat(String prompt) {

        float toReturn = Float.NaN;
        System.out.println(prompt);
        boolean isValid = false;
        while (!isValid) {
            try {
                String toCheck = sc.nextLine();
                toReturn = Float.parseFloat(toCheck);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter the valid number");
            }
        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        
        
        float toReturn = Float.NaN;
        boolean isValid = false;
        while (!isValid) {
            try {
                toReturn = readFloat(prompt);

                if (toReturn < min || toReturn > max) {
                    System.out.println("Please enter the numner between" + min + "and" + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {

        double toReturn = Double.NaN;
        System.out.println(prompt);
        boolean isValid = false;
        while (!isValid) {
            try {
                String toCheck = sc.nextLine();
                toReturn = Double.parseDouble(toCheck);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter the valid number");
            }
        }

        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        
        
        double toReturn = Double.NaN;
        boolean isValid = false;
        while (!isValid) {
            try {
                toReturn = readDouble(prompt);

                if (toReturn < min || toReturn > max) {
                    System.out.println("Please enter the numner between" + min + "and" + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }

        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
         
        BigDecimal toReturn = null;
        System.out.println(prompt);

        boolean isValid = false;
        while (!isValid) {
            try {
                String toCheck = sc.nextLine();
                toReturn = new BigDecimal(toCheck);

                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("please enter a valid number");

            }
        }
        return toReturn;
    }

    

    @Override
    public LocalDate readLocalDate(String prompt) {
        
         LocalDate toReturn = LocalDate.MIN;
        
        boolean isValid = false;
        while (!isValid) {
            try {
                String toCheck =readString(prompt);
                DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("MM/dd/yyyy");
                toReturn =LocalDate.parse(toCheck, dateformat);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter the valid Date.");
            }
        }
        return toReturn;
    }

    @Override
    public String readString(String prompt) {
        
        System.out.println(prompt);
        
        String toReturn=sc.nextLine();
        
        return toReturn;
    }

}
