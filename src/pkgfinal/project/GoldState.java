package pkgfinal.project;

/**
 * Represents the Gold membership tier.
 * RI: None.
 * AF: Premium point accumulation (2 points per $1).
 */
public class GoldState implements StatusState {
    @Override
    public void handlePurchase(Customer customer, double amount) {
        // Gold earns 2 points per $1
        customer.addPoints((int)amount * 2);

        // Check for downgrade if points drop (if your logic allows it)
        if (customer.getPoints() < 1000) {
            customer.setStatus(new SilverState());
        }
    }

    @Override
    public String getStatusName() {
        return "Gold";
    }
}
