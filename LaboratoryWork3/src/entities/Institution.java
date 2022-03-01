package entities;

import utilities.ObjectInterface;
import utilities.Person;
import utilities.StreetSideType;
import utilities.WalkablePlace;

import java.util.Objects;

public class Institution implements WalkablePlace {
    private StreetSideType streetSide;
    private final String name;
    private boolean hasWalkers;
    public boolean inHouse;

    public Institution() {
        name = "неопознанное заведение";
        joinStory();
    }

    public Institution(String name, StreetSideType streetSide) {
        this.name = name;
        this.streetSide = streetSide;
        joinStory();
    }

    private void joinStory() {
        System.out.println(name + " присоединились к истории.");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Institution '" + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Institution institution = (Institution) obj;

        return getName().equals(institution.getName()) && inHouse == institution.inHouse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, inHouse);
    }

    @Override
    public StreetSideType getStreetSide() {
        return streetSide;
    }

    @Override
    public void deleteWalker(Person walker) {
        this.currentWalkers.remove(walker);
        if (this.currentWalkers.isEmpty()) {
            this.hasWalkers = false;
        }
    }

    @Override
    public void addWalker(Person walker) {
        this.currentWalkers.add(walker);
        this.hasWalkers = true;
    }

    @Override
    public void setStreetSide(StreetSideType streetSide) {
        this.streetSide = streetSide;
    }
}
