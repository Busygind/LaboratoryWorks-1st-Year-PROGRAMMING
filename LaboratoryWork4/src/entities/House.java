package entities;

import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class House implements ObjectInterface {
    public ArrayList<Institution> institutions = new ArrayList<>();

    public House() {
        joinStory();
    }

    public void fillHouse(Institution[] institutions) {
        for (Institution institution : institutions) {
            this.institutions.add(institution);
            institution.inHouse = true;
        }
        System.out.println("Дома заполнились заведениями");
    }

    public void showHouseContent() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.institutions.toArray().length; i++) {
            names.add(this.institutions.get(i).getName());
        }
        System.out.println(Arrays.toString(new ArrayList[]{names}));
    }

    private void joinStory() {
        System.out.println("Дома присоединились к истории.");
    }

    @Override
    public String getName() {
        return "Это обычный дом, зачем ему имя?";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        House house = (House) obj;

        return institutions.equals(house.institutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(institutions);
    }

    @Override
    public String toString() {
        return "House with institutions: " + institutions;
    }
}
