package src.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sale implements Comparable<Sale> {
  private LocalDate date;
  private double amount;
  private String dateFormat;

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
