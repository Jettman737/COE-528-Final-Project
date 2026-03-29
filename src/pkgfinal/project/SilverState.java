package pkgfinal.project;

/**
 * Represents the Silver membership tier.
 * RI: None.
 * AF: Standard point accumulation (1 point per $1).
 */
public class SilverState implements StatusState {
    @Override
    public void handlePurchase(Customer customer, double amount) {
        // Silver earns 1 point per $1
        customer.addPoints((int)amount);

        // Check for upgrade to Gold
        if (customer.getPoints() >= 1000) {
            customer.setStatus(new GoldState());
        }
    }

    @Override
    public String getStatusName() {
        return "Silver";
    }
}
