package model.dtos;

/**
 * Created by Daniel on 09.10.2015.
 */
public class Meal {

    private String mealName;
    private double price;

    public Meal(String mealName, double price) {
        this.mealName = mealName;
        this.price = price;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
