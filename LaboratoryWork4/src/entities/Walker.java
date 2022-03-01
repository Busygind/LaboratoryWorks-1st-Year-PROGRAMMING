package entities;

import utilities.*;

import java.util.ArrayList;
import java.util.Objects;

public class Walker extends Person {
    private boolean walkingRightNow;
    private ArrayList<WalkablePlace> places = new ArrayList<>();
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
    public void stopWalking(WalkablePlace place) {
        this.walkingRightNow = false;
        System.out.println(getName() + " нагулялся в месте: '" + this.currentPlace.getName() + "'");
        place.deleteWalker(this);
        this.currentPlace = null;
    }

    @Override
    public void walkBy(WalkablePlace place) {
        this.walkingRightNow = true;
        places.add(place);
        this.currentPlace = place;
        System.out.println(getName() + " начал гулять в месте: '" + this.currentPlace.getName() + "'");
        place.addWalker(this);
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
            for (WalkablePlace place : places) {
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