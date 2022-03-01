package entities;

import exceptions.*;
import utilities.*;

import java.util.ArrayList;
import java.util.Objects;


public class City implements Observable {
    private final String name;
    private ArrayList<Person> citizens = new ArrayList<>();
    private ArrayList<Street> streets = new ArrayList<>();
    private boolean beMostBeatiful;
    private boolean goodWeather;

    public City(String name) {
        if (name == null || name.matches(".*\\d+.*") || name.isEmpty()) {
            throw new InvalidNameException("Название города некорректно");
        }
        this.name = name;
        setBeauty();
        setWeather();
        joinStory();
    }

    public void setBeauty() {
        if (this.getName().equals("Лос-Паганос")) {
            this.beMostBeatiful = true;
        }
    }

    public void addCitizen(Person citizen) throws NullObjectException, ToManyCitizensException {
        final int MAX_COUNT_OF_CITIZENS = 5;
        if (citizen == null) {
            throw new NullObjectException("В метод addCitizen передан пустой объект");
        } else if (citizens.toArray().length == MAX_COUNT_OF_CITIZENS) {
            throw new ToManyCitizensException("Город '" + getName() + "' переполнен!");
        } else {
            citizens.add(citizen);
            System.out.println("В городе '" + getName() + "' новый житель: '" + citizen.getName() + "'");
        }
    }

    public void deleteCitizen(Person citizen) {
        if (citizen == null) {
            throw new NullObjectException("В метод deleteCitizen передан пустой объект");
        } else {
            citizens.remove(citizen);
            System.out.println("'" + citizen.getName() + "' покидает город '" + getName() + "'");
        }
    }

    public void addStreet(Street street) {
        if (street == null) {
            throw new NullObjectException("В метод addStreet передан пустой объект");
        }
        streets.add(street);
        System.out.println("Улица '" + street.getName() + "' находится в городе '" + getName() + "'");
    }

    public void setWeather() {
        final double BAD_WEATHER_PROBABILITY = 0.1;
        //Климат замечательный, поэтому погода хорошая в 90% случаев
        if (Math.random() > BAD_WEATHER_PROBABILITY) {
            goodWeather = true;
            System.out.println("Погода в городе '" + getName() + "' просто прекрасная!");
        } else {
            goodWeather = false;
            System.out.println("Город '" + getName() + "' превратился в спб, погода соответствующая.");
        }
    }

    public boolean getWeather() {
        return goodWeather;
    }

    private void joinStory() {
        if (beMostBeatiful) {
            System.out.println("Красивейший город '" + getName() + "' присоединился к истории.");
        } else {
            System.out.println("Город '" + getName() + "' присоединился к истории.");
        }
    }

    @Override
    public void observedBy(Person person) {
        System.out.println(person.getName() + " любуется городом '" + getName() + "'");
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
        City city = (City) obj;

        return getName().equals(city.getName()) &&
                citizens.equals(city.citizens) &&
                streets.equals(city.streets) &&
                beMostBeatiful == city.beMostBeatiful &&
                goodWeather == city.goodWeather;
    }

    @Override
    public int hashCode() {
        return Objects.hash(citizens, streets);
    }

    @Override
    public String toString() {
        return "City called '" + getName() + "'; Count of streets: " + streets.toArray().length + "; " +
                "Count of citizens: " + citizens.toArray().length + ";";

    }
}