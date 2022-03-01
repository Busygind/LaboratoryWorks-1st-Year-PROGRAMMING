package entities;

import exceptions.*;
import utilities.*;

import java.util.Objects;

//Персонаж, рассуждения которого оказались верными
public class Guy extends Person {
    private boolean calculation;
    private Observable location;

    public Guy(String name) {
        super(name);
        this.verifyCalculation();
        joinStory();
    }

    public Guy(String name, boolean calculation) {
        super(name);
        this.calculation = calculation;
        joinStory();
    }

    public void verifyCalculation() {
        //Локальный класс
        class Calculation {
            private boolean beRight;

            public Calculation(boolean calc) {
                verify(calc);
            }

            public Calculation() {
                final double RIGHT_CALCULATION_PROBABILITY = 0.5;
                beRight = Math.random() > RIGHT_CALCULATION_PROBABILITY;
            }

            private void verify(boolean calc) {
                beRight = calc;
            }

            public boolean getCalc() {
                return beRight;
            }
        }
        Calculation calc = new Calculation();
        calculation = calc.getCalc();
    }

    private void joinStory() {
        if (this.calculation) {
            System.out.println("Персонаж '" + getName() + "' присоединился к истории и его расчеты оказались верными");
        } else {
            System.out.println("Персонаж '" + getName() + "' присоединился к истории и с его расчетами что-то не так");
        }

    }

    public void changeLocation(Observable loc) {
        if (loc == null) {
            throw new NullObjectException("В метод changeLocation передан пустой объект");
        }
        this.location = loc;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Guy guy = (Guy) obj;

        return getName().equals(guy.getName()) &&
                location.equals(guy.location) &&
                calculation == guy.calculation;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(calculation, location);
    }

    @Override
    public String toString() {
        if (location != null) {
            return "Character '" + getName() + "', now in: '" + location.getName() + "'";
        }
        return "Character '" + getName() + "'";
    }
}
