package entities;

import utilities.Person;
import utilities.StreetSideType;
import utilities.WalkablePlace;

import java.util.ArrayList;
import java.util.Objects;

public class Infrastructure implements WalkablePlace {
    private final String name;
    private StreetSideType streetSide;
    private boolean hasWalkers;
    private ArrayList<Person> currentWalkers = new ArrayList<>();

    public Infrastructure(String name) {
        this.name = name;
        this.streetSide = StreetSideType.RIGHT_SIDE;
        joinStory();
    }

    public Infrastructure(String name, StreetSideType streetSide) {
        this.name = name;
        this.streetSide = streetSide;
        joinStory();
    }

    public void addWalker(Person walker) {
        this.currentWalkers.add(walker);
        this.hasWalkers = true;
    }

    public void deleteWalker(Person walker) {
        this.currentWalkers.remove(walker);
        if (this.currentWalkers.isEmpty()) {
            this.hasWalkers = false;
        }
    }

    private void joinStory() {
        System.out.println("Заведение '" + name + "' присоединилось к истории.");
    }

    public boolean isHasWalkers() {
        return this.hasWalkers;
    }

    @Override
    public void setStreetSide(StreetSideType streetSide) {
        this.streetSide = streetSide;
    }

    @Override
    public StreetSideType getStreetSide() {
        return streetSide;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder namesOfCurrentWalkers = new StringBuilder();
        if (hasWalkers) {
            for (Person walker : currentWalkers) {
                namesOfCurrentWalkers.append(walker.getName()).append(", ");
            }
            return "Infrastructure '" + name + "', current walkers: " + namesOfCurrentWalkers;
        }
        return "Infrastructure without walkers";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Infrastructure inf = (Infrastructure) obj;

        return getName().equals(inf.getName()) &&
                streetSide.equals(inf.streetSide) &&
                currentWalkers.equals(inf.currentWalkers) &&
                hasWalkers == inf.hasWalkers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, streetSide, hasWalkers, currentWalkers);
    }
}
