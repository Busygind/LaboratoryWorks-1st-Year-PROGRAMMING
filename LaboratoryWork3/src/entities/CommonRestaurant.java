package entities;

import utilities.StreetSideType;
import utilities.RestaurantAbstract;

import java.util.Objects;

public class CommonRestaurant extends RestaurantAbstract {
    private StreetSideType streetSide;

    public CommonRestaurant(String name) {
        super(name);
        setCommon(true);
        joinStory();
    }

    public CommonRestaurant(String name, StreetSideType streetSide) {
        super(name);
        this.streetSide = streetSide;
        joinStory();
    }

    private void joinStory() {
        System.out.println(getName() + " присоединились к истории.");
    }

    public void getTerraceAvailability() {
        if (this.hasTerrace()) {
            System.out.println("У ресторана '" + getName() + "' есть терраса");
        } else {
            System.out.println("У ресторана '" + getName() + "' нет террасы");

        }
    }

    @Override
    public boolean hasTerrace() {
        return this instanceof RestaurantAbstract;
    }

    @Override
    public void getOutsideServiceAvialability(MainCharacter waiter) {
        if (this.hasOutsideService()) {
            System.out.println("В заведении \"" + getName() + "\" можно было пообедать или позавтракать, не выходя из автомашины.");
            Driver.beHappy(waiter);
        } else {
            System.out.println("В заведении \"" + getName() + "\" нет обслуживания автомашин");
        }
    }

    @Override
    public boolean hasOutsideService() {
        return !restaurantIsCommon();
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
            return "Common Restaurant '" + getName() + "', street side: LEFT";
        }
        if (streetSide.equals(StreetSideType.RIGHT_SIDE)) {
            return "Common Restaurant '" + getName() + "', street side: RIGHT";
        }
        return "UNDETECTED";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        CommonRestaurant rest = (CommonRestaurant) obj;

        return getName().equals(rest.getName()) && streetSide.equals(rest.streetSide);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(streetSide);
    }
}