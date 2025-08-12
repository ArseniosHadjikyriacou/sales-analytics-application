package src.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Sale implements Comparable<Sale> {
  private LocalDate date;
  private double amount;
  private String dateFormat;

  public static double average(ArrayList<Sale> sales, int startIndex, int endIndex) {
    double total = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      total += sales.get(i).getAmount();
    }
    return total / (endIndex - startIndex + 1);
  }

  public static double sigma(ArrayList<Sale> sales, int startIndex, int endIndex) {
    double avg = average(sales, startIndex, endIndex);
    double sum = 0;
    for (int i = startIndex; i <= endIndex; i++) {
      double diff = sales.get(i).getAmount() - avg;
      sum += diff * diff;
    }
    return Math.sqrt(sum / (endIndex - startIndex + 1));
  }

  public Sale(LocalDate date, double amount, String dateFormat) {
    this.date = date;
    this.amount = amount;
    this.dateFormat = dateFormat;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getDateFormat() {
    return dateFormat;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    return "Sale{" + "date:" + formatter.format(date) + ", amount=" + amount + '}';
  }

  @Override
  public int compareTo(Sale other) {
    return this.date.compareTo(other.date);
  }
}
