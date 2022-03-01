package entities;

import exceptions.InvalidNameException;
import utilities.*;

import java.util.ArrayList;

public class Sea implements Observable {
    private final String name;

    public Sea(String name) {
        if (name == null || name.matches(".*\\d+.*") || name.isEmpty()) {
            throw new InvalidNameException("Название моря некорректно");
        }
        this.name = name;
        joinStory();
    }

    //Вложенный класс
    public class Bay implements Observable {
        private final String name;
        private final ArrayList<City> coastalCities = new ArrayList<>();

        public Bay(String name) {
            if (name == null || name.matches(".*\\d+.*") || name.isEmpty()) {
                throw new InvalidNameException("Название залива некорректно");
            }
            this.name = name;
            joinStory();
        }

        public void addCityOnCoast(City city) {
            coastalCities.add(city);
            System.out.println("Город " + city.getName() + " расположился на берегу залива " + getName());
        }

        private void joinStory() {
            System.out.println("Залив '" + getName() + "' присоединился к истории.");
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void observedBy(Person person) {
            System.out.println("Персонаж" + person.getName() + " любуется заливом '" + getName() + "'");
        }
    }

    private void joinStory() {
        System.out.println("Море '" + getName() + "' присоединилось к истории.");
    }

    public void createBay(String name) {
        Bay bay = new Bay(name);
        System.out.println("Море " + getName() + " образовало залив " + bay.getName() + "!");
    }

    @Override
    public void observedBy(Person person) {
        System.out.println(person.getName() + " любуется морем");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Sea sea = (Sea) obj;

        return getName().equals(sea.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Sea called '" + getName() + "'";
    }
}