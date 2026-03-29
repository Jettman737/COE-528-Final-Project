package pkgfinal.project;

/**
 * Represents the Gold membership tier.
 * RI: None.
 * AF: Premium point accumulation (2 points per $1).
 */
public class GoldState implements StatusState {
    @Override
    public void handlePurchase(Customer customer, double amount) {
        customer.addPoints((int)(amount * 10));
    }
    
    @Override
    public double redeemPoints(Customer customer, double totalCost) {
        double discount = customer.getPoints() / 100.0; // 100 pts = $1
        if (discount >= totalCost) {
            customer.addPoints(-(int)(totalCost * 100));
            totalCost = 0.0;
        } else {
            customer.addPoints(-customer.getPoints());
            totalCost -= discount;
        }
        if (customer.getPoints() < 1000) {
            customer.setStatus(new SilverState()); // transition down
        }
        return totalCost;
    }
    @Override
    public String getStatusName() {
        return "Gold";
    }
}
