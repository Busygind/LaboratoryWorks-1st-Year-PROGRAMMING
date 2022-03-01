package entities;

import exceptions.*;
import utilities.*;

import java.util.Objects;

public class CommonRestaurant extends RestaurantAbstract {

    public CommonRestaurant(String name) {
        super(name);
        setCommon(true);
        joinStory();
    }

    private void joinStory() {
        System.out.println("Обычный ресторан '" + getName() + "' присоединился к истории.");
    }

    public void getTerraceAvailability() {
        if (this.hasTerrace()) {
            System.out.println("У ресторана '" + getName() + "' есть терраса");
        } else {
            System.out.println("У ресторана '" + getName() + "' нет террасы");

        }
    }

    @Override
    public void getOutsideServiceAvialability(MainCharacter waiter) throws NullObjectException {
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
        return "Common restaurant '" + getName() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CommonRestaurant rest = (CommonRestaurant) obj;

        return getName().equals(rest.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}