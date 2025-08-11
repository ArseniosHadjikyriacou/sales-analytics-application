package src;

import src.utils.Sale;
import src.utils.DateFormatValidator;
import src.utils.DateFormatException;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;


public class Main {


  public static void main(String[] args) {
    ArrayList<Sale> sales = new ArrayList<Sale>();

    // Get the file paths from command line arguments
    if (args.length == 0) {
      System.out.println("Usage: java Main <path/to/file1> <path/to/file2> ...");
      System.exit(1);
    }
    String[] filePaths = args;
    for (int i = 0; i < filePaths.length; i++) {
      // file.separator ensures compatibility across different OS
      filePaths[i] = filePaths[i].replace("/", File.separator).replace("\\", File.separator);
    }

    // Check if all the filepaths are valid
    for (String filePath : filePaths) {
      File file = new File(filePath);
      if (!file.exists() || !file.isFile()) {
        System.out.println("Invalid file path: " + filePath);
        System.exit(1);
      }
    }

    // Get the date format from the user
    Scanner dateScanner = new Scanner(System.in);
    String dateFormat = "";
    while (true) {
      System.out.println("Enter a valid date format.");
      System.out.println("Supported formats: " + DateFormatValidator.getSupportedFormatsString());
      dateFormat = dateScanner.nextLine();

      try {
        DateFormatValidator.validateDateFormat(dateFormat);
        break;
      } catch (DateFormatException e) {
        System.out.println(e.getMessage());
      }
    }
    dateScanner.close();
    System.out.println();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

    // Read the files and construct the sales ArrayList
    for (String filePath : filePaths) {
      try {
        File dataFile = new File(filePath); 
        Scanner dataReader = new Scanner(dataFile); // Possible FileNotFoundException
        while (dataReader.hasNextLine()) {
          String dataLine = dataReader.nextLine();
          try {
            String[] parts = dataLine.split("##");
            if (parts.length != 2) {
              System.out.println("Invalid sale format in file: " + filePath + ". The following sale will be ignored: " + dataLine);
              continue;
            }
            String dateString = parts[0];
            String amountString = parts[1];
            LocalDate date = LocalDate.parse(dateString, formatter); // Possible DateTimeParseException
            Double amount = Double.parseDouble(amountString); // Possible NumberFormatException
            sales.add(new Sale(date, amount, dateFormat));
          } catch (DateTimeParseException e) {
            System.out.println("Error parsing date in file: " + filePath + ". The following sale will be ignored: " + dataLine);
          } catch (NumberFormatException e) {
            System.out.println("Error parsing amount in file: " + filePath + ". The following sale will be ignored: " + dataLine);
          }
        }
        dataReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("File not found: " + filePath);
        System.exit(1);
      }
    }
    System.out.println("Number of sales to be processed: " + sales.size());

    // Sort the sales array
    Collections.sort(sales);

    System.out.println("Sorted sales data:");
    for (Sale sale : sales) {
      System.out.println(sale);
    }

  }


}
