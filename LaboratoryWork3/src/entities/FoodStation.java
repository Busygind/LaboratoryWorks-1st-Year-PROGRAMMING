package entities;

import utilities.StreetSideType;
import utilities.RestaurantAbstract;

import java.util.Objects;

public class FoodStation extends RestaurantAbstract {
    private StreetSideType streetSide;


    public FoodStation() {
        super("Пищезаправочная станция");
        setCommon(false);
        joinStory();
    }

    public FoodStation(StreetSideType streetSide) {
        super("Пищезаправочная станция");
        this.streetSide = streetSide;
        setCommon(false);
        joinStory();
    }

    private void joinStory() {
        System.out.println("Необычный ресторан '" + getName() + "' присоединилась к истории.");
    }

    public void getOutsideServiceAvialability(MainCharacter waiter) {
        if (this.hasOutsideService()) {
            System.out.println("В ресторане \"" + getName() + "\" можно было пообедать или позавтракать, не выходя из автомашины.");
            Driver.beHappy(waiter);
        } else {
            System.out.println("В ресторане \"" + getName() + "\" нет обслуживания автомашин");
        }
    }

    public void getTerraceAvailability() {
        if (this.hasTerrace()) {
            System.out.println("У ресторана '" + getName() + "' есть терраса");
        } else {
            System.out.println("У ресторана '" + getName() + "' нет террасы");

        }
    }

    @Override
    public boolean hasOutsideService() {
        return !restaurantIsCommon();
    }

    @Override
    public boolean hasTerrace() {
        return this instanceof RestaurantAbstract;
    }

    @Override
    public void setStreetSide() {
        streetSide = StreetSideType.LEFT_SIDE;
    }

    @Override
    public StreetSideType getStreetSide() {
        return streetSide;
    }

    @Override
    public String toString() {
        if (streetSide.equals(StreetSideType.LEFT_SIDE)) {
            return "Food station '" + getName() + "', street side: LEFT, has outside service: '" + hasOutsideService() + "'";
        }
        if (streetSide.equals(StreetSideType.RIGHT_SIDE)) {
            return "Food station '" + getName() + "', street side: RIGHT, has outside service: '" + hasOutsideService() + "'";
        }
        return "UNDETECTED";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FoodStation fs = (FoodStation) obj;

        return getName().equals(fs.getName()) && streetSide.equals(fs.streetSide);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(streetSide);
    }
}
