public class Product {
    private String ID;
    private String name;
    private int amount;
    private float price;

    public Product() {
    }

    public Product(String ID, String name, int amount, float price) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
