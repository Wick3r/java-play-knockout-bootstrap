package model.dtos;

/**
 * Created by Daniel on 09.10.2015.
 */
public class Reservation {

    private String passenger;
    private Meal meal;

    public Reservation(String passenger, Meal meal) {
        this.passenger = passenger;
        this.meal = meal;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
