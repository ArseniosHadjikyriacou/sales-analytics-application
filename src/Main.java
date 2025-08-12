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

    // Create a single scanner object to process all user inputs
    Scanner cmdScanner = new Scanner(System.in);

    // Get the date format from the user
    String dateFormat = "";
    while (true) {
      System.out.println("Enter a valid date format.");
      System.out.println("Supported formats: " + DateFormatValidator.getSupportedFormatsString());
      dateFormat = cmdScanner.nextLine();

      try {
        DateFormatValidator.validateDateFormat(dateFormat);
        break;
      } catch (DateFormatException e) {
        System.out.println(e.getMessage());
      }
    }
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
    System.out.println("Number of available sales for processing: " + sales.size());

    // Sort the sales array
    Collections.sort(sales);
    LocalDate firstSaleDate = sales.get(0).getDate();
    LocalDate lastSaleDate = sales.get(sales.size() - 1).getDate();

    System.out.println("Sales range: " + firstSaleDate.format(formatter) + " to " + lastSaleDate.format(formatter));
    System.out.println();

    // Get the start date for analysis from the user
    LocalDate startDate;
    while (true) {
      System.out.println("Enter a valid start date in the format: " + dateFormat);
      String startDateString = cmdScanner.nextLine();

      try {
        startDate = LocalDate.parse(startDateString, formatter);
        if (startDate.isBefore(firstSaleDate) || startDate.isAfter(lastSaleDate)) {
          System.out.println("Start date must be within the sales range: " + firstSaleDate.format(formatter) + " to " + lastSaleDate.format(formatter));
          System.out.println();
          continue;
        }
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date format: " + startDateString + " does not match the format " + dateFormat);
        System.out.println();
      }
    }

    // Get the end date for analysis from the user
    LocalDate endDate;
    while (true) {
      System.out.println("Enter a valid end date in the format: " + dateFormat);
      String endDateString = cmdScanner.nextLine();

      try {
        endDate = LocalDate.parse(endDateString, formatter);
        if (endDate.isBefore(startDate)) {
          System.out.println("End date cannot be before start date: " + startDate.format(formatter));
          System.out.println();
          continue;
        }
        if (endDate.isBefore(firstSaleDate) || endDate.isAfter(lastSaleDate)) {
          System.out.println("End date must be within the sales range: " + firstSaleDate.format(formatter) + " to " + lastSaleDate.format(formatter));
          System.out.println();
          continue;
        }
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Invalid date format: " + endDateString + " does not match the format " + dateFormat);
        System.out.println();
      }
    }
    // Close the command scanner since we no longer need it
    cmdScanner.close();

    // Perform analysis on the sales data within the specified date range
    int startDateIndex = -1, endDateIndex = -1;
    for (int i = 0; i < sales.size(); i++) {
      LocalDate date = sales.get(i).getDate();
      if (startDateIndex == -1 && !date.isBefore(startDate)) {
        startDateIndex = i;
      }
      if (date.isAfter(endDate)) {
        endDateIndex = i - 1;
        break;
      }
    }
    
    // If we never found a date after endDate, it means endDate includes the last sales
    if (endDateIndex == -1) {
      endDateIndex = sales.size() - 1;
    }

    System.out.println();
    // System.out.println("Analyzing sales from index " + startDateIndex + " to " + endDateIndex);
    System.out.println("Average of sales is: " + Sale.average(sales, startDateIndex, endDateIndex));
    System.out.println("Standard deviation of sales is: " + Sale.sigma(sales, startDateIndex, endDateIndex));

  }

}
