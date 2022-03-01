package utilities;

import entities.*;
import exceptions.HasNotHomeException;
import exceptions.InvalidNameException;
import exceptions.NullObjectException;

import java.util.Objects;

public abstract class Person implements ObjectInterface {
    private final String name;
    private City home;

    public Person(String name) throws InvalidNameException {
        if (name == null || name.matches(".*\\d+.*") || name.isEmpty()) {
            throw new InvalidNameException("Имя персонажа некорректно");
        } else {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public void stopWalking(WalkablePlace place) {
        if (place == null) {
            throw new NullObjectException("В метод walkBy передан пустой объект");
        }
        System.out.println(getName() + " нагулялся");
        place.deleteWalker(this);
    }

    public void walkBy(WalkablePlace place) {
        if (place == null) {
            throw new NullObjectException("В метод walkBy передан пустой объект");
        }
        System.out.println(getName() + " начал гулять в месте: '" + place.getName() + "'");
        place.addWalker(this);
    }

    public void setHome(City city) {
        this.home = city;
        city.addCitizen(this);
    }

    public City getHome() throws HasNotHomeException {
        if (home == null) {
            throw new HasNotHomeException("Персонаж " + getName() + " числанулся после первого семестра и, очевидно, теперь у него нет дома");
        } else {
            return home;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Person person = (Person) obj;

        return name.equals(person.name) &&
                home.equals(person.home);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, home);
    }

    @Override
    public String toString() {
        return "Person's name is '" + name + "'; Home city is '" + home.getName() + "'";
    }
}
