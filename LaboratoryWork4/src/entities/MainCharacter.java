package entities;

import exceptions.NullObjectException;
import utilities.*;

import java.util.Objects;

public class MainCharacter extends Person {
    private RestaurantAbstract currentPlace;

    public MainCharacter() {
        super("неопознанный персонаж");
        joinStory();
    }

    public MainCharacter(String name) {
        super(name);
        joinStory();
    }

    public void stopNearTheRestaurant(RestaurantAbstract rest) {
        if (rest == null) {
            throw new NullObjectException("В метод stopNearTheRestaurant передан пустой объект");
        }
        this.currentPlace = rest;
        System.out.println(getName() + " остановился около ресторана: '" + this.currentPlace.getName() + "'");
    }

    public void serveLunch(Driver driver) {
        driver.money -= 100;
        System.out.println(getName() + " подает обед прямо в машину водителю '" + driver.getName() + "'");
    }

    private void joinStory() {
        System.out.println("Персонаж '" + getName() + "' присоединился к истории.");
    }

    public RestaurantAbstract getCurrentPlace() {
        return currentPlace;
    }

    public void jumpOut() {
        System.out.println(getName() + " выскакивает из ресторана");
    }

    @Override
    public String toString() {
        if (currentPlace != null) {
            return "Character '" + getName() + "', now in: '" + currentPlace.getName() + "'";
        }
        return "Character '" + getName() + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MainCharacter mainCharacter = (MainCharacter) obj;

        return getName().equals(mainCharacter.getName()) && currentPlace.equals(mainCharacter.currentPlace);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(currentPlace);
    }
}
