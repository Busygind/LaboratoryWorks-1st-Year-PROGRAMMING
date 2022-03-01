package entities;

import exceptions.*;
import utilities.*;

import java.util.Objects;

public class FoodStation extends RestaurantAbstract {

    public FoodStation() {
        super("Пищезаправочная станция");
        setCommon(false);
        joinStory();
    }

    public FoodStation(String name) {
        super(name);
        setCommon(false);
        joinStory();
    }

    private void joinStory() {
        System.out.println("Необычный ресторан '" + getName() + "' присоединилась к истории.");
    }

    public void getTerraceAvailability() {
        if (this.hasTerrace()) {
            System.out.println("У ресторана '" + getName() + "' есть терраса");
        } else {
            System.out.println("У ресторана '" + getName() + "' нет террасы");

        }
    }

    @Override
    public void getOutsideServiceAvialability(MainCharacter waiter) {
        if (waiter == null) {
            throw new NullObjectException("В метод getOutsideServiceAvialability передан пустой объект");
        }
        if (this.hasOutsideService()) {
            System.out.println("В ресторане \"" + getName() + "\" можно было пообедать или позавтракать, не выходя из автомашины.");
            Driver driver = new Driver("Олег");
            driver.beHappy(waiter);
        } else {
            System.out.println("В ресторане \"" + getName() + "\" нет обслуживания автомашин");
        }
    }

    @Override
    public boolean hasOutsideService() {
        return !restaurantIsCommon();
    }

    @Override
    public String toString() {
        return "Food station '" + getName() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FoodStation fs = (FoodStation) obj;

        return getName().equals(fs.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + getName().hashCode();
    }
}
