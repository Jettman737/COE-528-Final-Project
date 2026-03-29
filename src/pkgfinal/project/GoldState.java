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
    public String getStatusName() {
        return "Gold";
    }
}
