package model.nutrition;

/**
 * This class represents the abstract nutrition.
 */
public abstract class Nutrition {

    /**
     * Get the quantity of nutrition.
     *
     * @return
     */
    public abstract int getQuantity();

    /**
     * Get the basic calories per quantity.
     *
     * @return
     */
    public abstract int getCal();

    /**
     * Calculate the calories.
     *
     * @param calPerSize
     * @param quantity
     * @return
     */
    public int calculateCal(int calPerSize, int quantity) {
        return calPerSize * quantity;
    }
}
