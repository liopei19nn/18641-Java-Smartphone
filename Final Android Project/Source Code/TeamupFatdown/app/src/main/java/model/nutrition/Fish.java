package model.nutrition;

/**
 * This class represents Fish which is one of the nutrition.
 */
public class Fish extends Nutrition {
    private static final int CALPERSIZE = 40;
    private String name;
    private int quantity;

    /**
     * Constructor
     *
     * @param name
     * @param quantity
     */
    public Fish(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public int getCal() {
        return CALPERSIZE;
    }

    /**
     * Get the name of nutrition
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of nutrition.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of nutrition.
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
