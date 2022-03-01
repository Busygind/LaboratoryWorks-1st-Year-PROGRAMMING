package entities;

import exceptions.InvalidNameException;
import utilities.*;

import java.util.Objects;

public class Street implements ObjectInterface {
    private final String name;
    private boolean beTheBiggest;
    private boolean dragOnShore;
    private City location;

    public Street(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Название улицы некорректно");
        }
        this.name = name;
        joinStory();
    }

    public Street(String name, City city, boolean beTheBiggest) {
        if (name.isEmpty() || name == null) {
            throw new InvalidNameException("Название улицы некорректно");
        }
        this.name = name;
        this.beTheBiggest = true;
        this.setLocation(city);
        joinStory();
    }

    public void drag() {
        this.dragOnShore = true;
        System.out.println("Улица " + name + " тянется вдоль береговой линии");
    }

    private void joinStory() {
        if (this.beTheBiggest) {
            System.out.println("Самая большая и красивая улица '" + name + "' присоединилась к истории.");
        } else {
            System.out.println("Обычная улица '" + name + "' присоединилась к истории.");

        }
    }

    public void setLocation(City city) {
        this.location = city;
        city.addStreet(this);
    }

    public City getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (beTheBiggest) {
            return "Street '" + name + "' is biggest and most beautiful";
        }
        return "Just street '" + name + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Street street = (Street) obj;

        return getName().equals(street.getName()) &&
                beTheBiggest == street.beTheBiggest &&
                dragOnShore == street.dragOnShore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, beTheBiggest, dragOnShore);
    }
}
