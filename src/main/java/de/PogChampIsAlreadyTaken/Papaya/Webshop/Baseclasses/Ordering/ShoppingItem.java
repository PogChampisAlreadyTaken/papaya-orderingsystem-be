package de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Ordering;

public class ShoppingItem {

    private Meal meal;
    private int amount;

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
