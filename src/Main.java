package src;

import src.utils.Sale;
import src.utils.DateFormatValidator;
import src.utils.DateFormatException;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;


public class Main {


  public static void main(String[] args) {
    ArrayList<String> sales = new ArrayList<String>();
    Sale saleData = new Sale("2023-10-01", "Laptop", 2, 1500.00);
    System.out.println("Sample Sale Data: " + saleData.toString());

    // Get the file paths from command line arguments
    if (args.length == 0) {
      System.out.println("Usage: java Main <path/to/file1> <path/to/file2> ...");
      return;
    }
    String[] filePaths = args;
    for (int i = 0; i < filePaths.length; i++) {
      // file.separator ensures compatibility across different OS
      filePaths[i] = filePaths[i].replace("/", File.separator).replace("\\", File.separator);
    }

    for (String filePath : filePaths) {
      try {
        File dataFile = new File(filePath);
        Scanner dataReader = new Scanner(dataFile);
        while (dataReader.hasNextLine()) {
          String data = dataReader.nextLine();
          // String[] parts = data.split("##");
          // String dateString = parts[0];
          // String amountString = parts[1];
          sales.add(data);
        }
        dataReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("Please check that the specified filepath (" + filePath + ") is correct and that the file exists");
        e.printStackTrace();
        return;
      }
    }
    System.out.println("Length of sales data: " + sales.size());
    System.out.println();
      

    Scanner scanner = new Scanner(System.in);
    String dateFormat = "";

    while (true) {
      System.out.println("Enter a valid date format.");
      System.out.println("Supported formats: " + DateFormatValidator.getSupportedFormatsString());
      dateFormat = scanner.nextLine();

      try {
        DateFormatValidator.validateDateFormat(dateFormat);
        System.out.println("You entered date format: " + dateFormat);
        System.out.println();
        break;
      } catch (DateFormatException e) {
        System.out.println(e.getMessage());
        System.out.println();
      }
    }

    String amountFormat = "";

    while (true) {
      System.out.println("Enter a valid amount format.");
      System.out.println("Supported formats: integer, double, float");
      amountFormat = scanner.nextLine();

      if (amountFormat.equals("integer") || amountFormat.equals("double") || amountFormat.equals("float")) {
        System.out.println("You entered amount format: " + amountFormat);
        System.out.println();
        break;
      } else {
        System.out.println("Invalid amount format. Please try again.");
        System.out.println();
      }
    }
    System.out.println("You have successfully set up the application with date format: " + dateFormat + " and amount format: " + amountFormat);
    System.out.println();
    scanner.close();

  }


}
