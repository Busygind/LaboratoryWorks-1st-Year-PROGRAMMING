package entities;

import utilities.Person;
import utilities.WalkablePlace;

import java.util.ArrayList;
import java.util.Objects;

public class Walker extends Person {
    private boolean walkingRightNow;
    private ArrayList<Infrastructure> places = new ArrayList<>();
    private WalkablePlace currentPlace;

    public Walker() {
        super("неопознанный гуляющий персонаж");
        joinStory();
    }

    public Walker(String name) {
        super(name);
        joinStory();
    }

    private void joinStory() {
        System.out.println("Гуляющий персонаж '" + getName() + "' присоединилось к истории.");
    }

    public boolean isWalkingRightNow() {
        return walkingRightNow;
    }

    @Override
    public void stopWalking() {
        this.walkingRightNow = false;
        System.out.println(getName() + " нагулялся в месте: '" + this.currentPlace.getName() + "'");
        currentPlace.deleteWalker(this);
        this.currentPlace = null;
    }

    @Override
    public void walkBy(Infrastructure infrastructure) {
        this.walkingRightNow = true;
        places.add(infrastructure);
        this.currentPlace = infrastructure;
        System.out.println(getName() + " начал гулять в месте: '" + this.currentPlace.getName() + "'");
        infrastructure.addWalker(this);
    }

    @Override
    public void jumpOut() {
        System.out.println("Гуляющий человек выскакивает, а находится он в месте: '" + this.currentPlace + "'");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Walker walker = (Walker) obj;

        return getName().equals(walker.getName()) &&
                currentPlace.equals(walker.currentPlace) &&
                places.equals(walker.places);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(walkingRightNow, places, currentPlace);
    }

    @Override
    public String toString() {
        StringBuilder namesOfPlaces = new StringBuilder();
        if (!places.isEmpty()) {
            for (Infrastructure place : places) {
                namesOfPlaces.append(place.getName()).append(", ");
            }
            if (walkingRightNow) {
                return "Walker '" + getName() + "', now walking on: '" + currentPlace.getName() + "', discovered places: '" + namesOfPlaces;
            }
            return "Walker '" + getName() + "', not walking now, discovered places: '" + namesOfPlaces;
        }
        return "Walker '" + getName() + "' never walked";
    }
}