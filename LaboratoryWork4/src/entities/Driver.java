package entities;

import exceptions.*;
import utilities.*;

import java.util.Objects;

public class Driver extends Person {
    private Infrastructure currentPlace;
    public double money;
    public boolean hasCar;

    public Driver(String name) {
        super(name);
        this.money = Math.random() * 100000;
    }

    public void buyCar() {
        //Локальный класс
        class Car {
            private final double price;

            public Car() {
                price = Math.random() * 100000;
            }

            public double getPrice() {
                return price;
            }
            //очень глубокое и подробное описание строения автомобиля
        }
        Car car = new Car();
        if (car.getPrice() < this.money) {
            this.hasCar = true;
        }
    }

    public void beHappy(MainCharacter waiter) {
        if (waiter == null) {
            throw new NullObjectException("В метод beHappy передан пустой объект");
        }
        if (this.hasCar) {
            System.out.println("Всем автолюбителям это понравилось");
            getSignal(waiter);
        }
    }

    public void getSignal(MainCharacter waiter) {
        if (waiter == null) {
            throw new NullObjectException("В метод getSignal передан пустой объект");
        }
        System.out.println("Автолюбители дают сигнал");
        if (Objects.equals(waiter.getName(), "Официант")) {
            waiter.jumpOut();
            waiter.serveLunch(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Driver driver = (Driver) obj;

        return getName().equals(driver.getName()) &&
                (currentPlace.getName()).equals(driver.getName()) &&
                money == driver.money &&
                hasCar == driver.hasCar;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(currentPlace, hasCar, money);
    }

    @Override
    public String toString() {
        return "Driver '" + getName() + "', current place: '" + currentPlace.getName() + "'; " +
                "current savings: " + money + " dollars";
    }
}
