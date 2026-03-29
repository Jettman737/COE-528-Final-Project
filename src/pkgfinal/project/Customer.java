package pkgfinal.project;

import java.util.ArrayList;

/**
 * Represents a Customer in the BookStoreSystem.
 * AF(points, status, store) = A customer with 'points' loyalty points,
 * currently in the 'status' state (Silver/Gold), shopping at the 'store'.
 * RI: points >= 0, status != null, store != null.
 */
public class Customer extends User {
    private int points;
    private StatusState status;
    private final BookStoreSystem store;

    /**
     * Constructor for Customer.
     * Links the customer to the store and initializes status to Silver.
     */
    public Customer(String username, String password, BookStoreSystem store) {
        super(username, password);
        this.store = store;
        this.points = 0;
        this.status = new SilverState();
        checkRep();
    }

    /**
     * Asserts that the Rep Invariant holds.
     */
    private void checkRep() {
        if (store == null) {
            throw new RuntimeException("Rep Invariant Violated: Store cannot be null.");
        }
        if (points < 0) {
            throw new RuntimeException("Rep Invariant Violated: Points cannot be negative.");
        }
        if (status == null) {
            throw new RuntimeException("Rep Invariant Violated: Status state cannot be null.");
        }
    }

    /**
     * Prepares inventory data for the Customer GUI table.
     * Column 0: Name, Column 1: Price, Column 2: Checkbox (Boolean).
     */
    public Object[][] viewBooks() {
        checkRep();
        ArrayList<Book> inventory = store.inventory;
        Object[][] data = new Object[inventory.size()][3];

        for (int i = 0; i < inventory.size(); i++) {
            Book b = inventory.get(i);
            data[i][0] = b.getName();  // Column 1: Book Name
            data[i][1] = b.getPrice(); // Column 2: Book Price
            data[i][2] = false;        // Column 3: Select (Checkbox status)
        }
        return data;
    }

    /**
     * Logic for processing a purchase and updating points/status.
     */
    public void buyBooks(ArrayList<Book> cart) {
        checkRep();
        if (cart == null || cart.isEmpty()) return;

        double totalCost = viewCartCost(cart);

        earnPoints(totalCost);

        System.out.println("Purchase successful! Total: $" + totalCost);
    }

    /**
     * Calculates the total cost of books in a given list.
     */
    public double viewCartCost(ArrayList<Book> cart) {
        checkRep();
        if (cart == null) return 0;

        double cost = 0;
        for (Book b : cart) {
            cost += b.getPrice();
        }
        return cost;
    }

    /**
     * Delegates point accumulation logic to the current State object.
     */
    public void earnPoints(double moneySpent) {
        checkRep();
        status.handlePurchase(this, moneySpent);
    }

    /**
     * Displays the current points and status name in the console.
     */
    public void viewPointsAndStatus() {
        checkRep();
        System.out.println("Points: " + points + " | Status: " + status.getStatusName());
    }

    /**
     * Updates the current status state (e.g., from SilverState to GoldState).
     */
    public void setStatus(StatusState newState) {
        this.status = newState;
    }

    /**
     * Returns the current number of points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Adds a specific amount of points to the total.
     */
    public void addPoints(int p) {
        this.points += p;
    }

    /**
     * Returns the String name of the current status.
     */
    public String getStatusString() {
        return status.getStatusName();
    }

    /**
     * Calculates the discount amount based on current points.
     * E: 100 points = $1 CAD discount.
     */
    public double calculateDiscount() {
        return (double) this.points / 100;
    }

    /**
     * Handles the "Redeem points and Buy" logic.
     * TC = Original Cost - (Points / 100).
     */
    public void redeemAndBuy(ArrayList<Book> cart) {
        checkRep();
        if (cart == null || cart.isEmpty()) return;

        double originalCost = viewCartCost(cart);
        double discount = calculateDiscount();

        double finalCost;
        int pointsRedeemed;

        if (discount >= originalCost) {
            finalCost = 0;
            pointsRedeemed = (int) (originalCost * 100);
        } else {
            finalCost = originalCost - discount;
            pointsRedeemed = (int) (discount * 100);
        }

        this.points -= pointsRedeemed;
        if (this.points < 1000) { status = new SilverState();

        earnPoints(finalCost);

        System.out.println("Redeemed " + pointsRedeemed + " points.");
        System.out.println("Final Transaction Cost: $" + finalCost);
    }
}


