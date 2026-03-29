package pkgfinal.project;

/**
 * Interface for the Customer's membership status.
 * AF(customer) = A behavior set that determines how points are earned.
 */
public interface StatusState {
    void handlePurchase(Customer customer, double amount);
    double redeemPoints(Customer customer, double totalCost);
    String getStatusName();
}
