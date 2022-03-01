package entities;

import utilities.Person;

import java.util.Objects;

public class Driver extends Person {
    private Infrastructure currentPlace;

    public Driver(String name) {
        super(name);
    }

    public static void beHappy(MainCharacter waiter) {
        System.out.println("Всем автолюбителям это понравилось");
        getSignal(waiter);
    }

    public static void getSignal(MainCharacter waiter) {
        System.out.println("Автолюбители дают сигнал");
        if (Objects.equals(waiter.getName(), "Официант")) {
            waiter.jumpOut();
            waiter.serveLunch();
        }
    }

    @Override
    public void stopWalking() {
        System.out.println("Водитель нагулялся в месте: '" + this.currentPlace.getName() + "'");
        currentPlace.deleteWalker(this);
        this.currentPlace = null;
    }

    @Override
    public void walkBy(Infrastructure infrastructure) {
        System.out.println("Водитель прогуливается в месте '" + infrastructure.getName() + "'");
        infrastructure.addWalker(this);
    }

    @Override
    public void jumpOut() {
        System.out.println("Бешеный водитель выскочил из машины");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Driver driver = (Driver) obj;

        return getName().equals(driver.getName()) && (currentPlace.getName()).equals(driver.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(currentPlace);
    }

    @Override
    public String toString() {
        return "Driver '" + getName() + "', current place: '" + currentPlace.getName() + "'";
    }
}
