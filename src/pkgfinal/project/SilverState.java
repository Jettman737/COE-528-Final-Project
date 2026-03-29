package pkgfinal.project;

/**
 * Represents the Silver membership tier.
 * RI: None.
 * AF: Standard point accumulation (1 point per $1).
 */
public class SilverState implements StatusState {
    @Override
    public void handlePurchase(Customer customer, double amount) {
        customer.addPoints((int)(amount * 10));

        if (customer.getPoints() >= 1000) {
            customer.setStatus(new GoldState());
        }
    }
    @Override
    public double redeemPoints(Customer customer, double totalCost) {
        double discount = customer.getPoints() / 100.0; // 100 pts = $1
        if (discount >= totalCost) {
            customer.addPoints(-(int)(totalCost * 100));
            return 0.0;
        } else {
            customer.addPoints(-customer.getPoints());
            return totalCost - discount;
        }
    }
    @Override
    public String getStatusName() {
        return "Silver";
    }
}
