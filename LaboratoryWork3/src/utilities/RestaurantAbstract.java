package utilities;

import entities.MainCharacter;

import java.util.Objects;

public abstract class RestaurantAbstract implements BuildingInterface {
    private final String name;
    private boolean isCommon;

    public RestaurantAbstract(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean hasTerrace();

    public abstract boolean hasOutsideService();

    public boolean restaurantIsCommon() {
        return isCommon;
    }

    public abstract void getOutsideServiceAvialability(MainCharacter waiter);

    protected void setCommon(boolean common) {
        this.isCommon = common;
    }

    @Override
    public String toString() {
        if (isCommon) {
            return "Обычный ресторан, который называется '" + name + "'";
        }
        return "Необычный ресторан, который называется '" + name + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RestaurantAbstract rest = (RestaurantAbstract) obj;

        return name.equals(rest.name) && isCommon == rest.isCommon;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isCommon);
    }
}
