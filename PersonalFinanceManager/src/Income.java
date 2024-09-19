public class Income {
    private String description;
    private double amount;

    public Income(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    // Getter ve Setter metodları
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
