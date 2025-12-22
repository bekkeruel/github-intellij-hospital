public class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;

    public Medicine(int id, String name, String manufacturer, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        }
    }

    public void displayMedicine() {
        System.out.println("Medicine: " + name + ", Quantity: " + quantity);
    }
}
