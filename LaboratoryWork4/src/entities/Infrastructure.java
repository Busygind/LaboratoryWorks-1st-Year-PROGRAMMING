package entities;

import utilities.*;

import java.util.Objects;

public class Infrastructure extends WalkablePlace {

    public Infrastructure(String name) {
        super(name);
        this.streetSide = StreetSideType.RIGHT_SIDE;
        joinStory();
    }

    public Infrastructure(String name, StreetSideType streetSide) {
        super(name);
        this.streetSide = streetSide;
        joinStory();
    }

    private void joinStory() {
        System.out.println("Заведение '" + getName() + "' присоединилось к истории.");
    }

    public boolean isHasWalkers() {
        return this.hasWalkers;
    }

    @Override
    public String toString() {
        StringBuilder namesOfCurrentWalkers = new StringBuilder();
        if (hasWalkers) {
            for (Person walker : currentWalkers) {
                namesOfCurrentWalkers.append(walker.getName()).append(", ");
            }
            return "Infrastructure '" + getName() + "', current walkers: " + namesOfCurrentWalkers;
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
        return super.hashCode() + getName().hashCode();
    }
}
