package entities;

import utilities.*;

import java.util.Objects;

public class Institution extends WalkablePlace {
    public boolean inHouse;

    public Institution() {
        super("Безымянное заведение");
        joinStory();
    }

    public Institution(String name, StreetSideType streetSide) {
        super(name);
        this.streetSide = streetSide;
        joinStory();
    }

    private void joinStory() {
        System.out.println(getName() + " присоединились к истории.");
    }

    @Override
    public String toString() {
        return "Institution '" + getName() + "'";
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
        return super.hashCode() + Objects.hash(getName(), inHouse);
    }
}
