package utilities;

import entities.Infrastructure;

import java.util.Objects;

public abstract class Person implements ObjectInterface {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void walkBy(Infrastructure infrastructure);

    public abstract void stopWalking();

    public abstract void jumpOut();

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Person person = (Person) obj;

        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Персонаж, которого зовут '" + name + "'";
    }
}
