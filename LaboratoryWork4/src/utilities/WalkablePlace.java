package utilities;

import exceptions.InvalidNameException;
import exceptions.NullObjectException;

import java.util.ArrayList;
import java.util.Objects;

public abstract class WalkablePlace implements ObjectInterface {
    private final String name;
    public boolean hasWalkers = false;
    public StreetSideType streetSide;
    public ArrayList<Person> currentWalkers = new ArrayList<>();

    public WalkablePlace(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidNameException("Название объекта ландшафта некорректно");
        }
        this.name = name;
    }

    //Вложенный статический перечислимый класс
    public static enum StreetSideType {
        LEFT_SIDE,
        RIGHT_SIDE
    }

    public void addWalker(Person walker) {
        if (walker == null) {
            throw new NullObjectException("В метод addWalker передан пустой объект");
        } else {
            this.currentWalkers.add(walker);
            this.hasWalkers = true;
        }
    }

    public void deleteWalker(Person walker) {
        if (walker == null) {
            throw new NullObjectException("В метод deleteWalker передан пустой объект");
        } else {
            this.currentWalkers.remove(walker);
            if (this.currentWalkers.isEmpty()) {
                this.hasWalkers = false;
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Walkable place's name: '" + getName() + "', current walkers: " + currentWalkers;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        WalkablePlace place = (WalkablePlace) obj;

        return name.equals(place.name) &&
                hasWalkers == place.hasWalkers &&
                streetSide.equals(place.streetSide) &&
                currentWalkers.equals(place.currentWalkers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hasWalkers, currentWalkers, streetSide);
    }
}
