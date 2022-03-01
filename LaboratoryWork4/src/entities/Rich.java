package entities;

import utilities.*;

import java.util.Objects;

public class Rich extends Person {
    private double money;

    public Rich(String name) {
        super(name);
        this.money = Math.round(Math.random() * 1000000);
        joinStory();
    }

    public void spendMoney() {
        money -= Math.random() * 10000;
    }

    private void joinStory() {
        System.out.println("Богач '" + getName() + "' присоединился к истории.");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Rich rich = (Rich) obj;

        return getName().equals(rich.getName()) &&
                money == rich.money;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(money);
    }

    @Override
    public String toString() {
        return "Rich '" + getName() + "' has " + money + " dollars";
    }
}
