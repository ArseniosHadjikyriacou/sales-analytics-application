package src.utils;

public class Sale {
  private String date;
  private String product;
  private int quantity;
  private double price;

  public Sale(String date, String product, int quantity, double price) {
    this.date = date;
    this.product = product;
    this.quantity = quantity;
    this.price = price;
  }

  public String getDate() {
    return date;
  }

  public String getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public String toString() {
    return "Sale{" +
           "date=" + date +
           ", product=" + product +
           ", quantity=" + quantity +
           ", price=" + price +
           '}';
  }
}
