package entities;

import utilities.BuildingInterface;
import utilities.StreetSideType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class House implements BuildingInterface {
    private StreetSideType streetSide;
    public ArrayList<Institution> institutions = new ArrayList<>();

    public House() {
        this.setStreetSide();
        joinStory();
    }

    public House(StreetSideType streetSide) {
        this.streetSide = streetSide;
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
    public StreetSideType getStreetSide() {
        return this.streetSide;
    }

    @Override
    public void setStreetSide() {
        this.streetSide = StreetSideType.LEFT_SIDE;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        House house = (House) obj;

        return streetSide.equals(house.streetSide) && institutions.equals(house.institutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetSide, institutions);
    }

    @Override
    public String toString() {
        StringBuilder institutionsInfo = new StringBuilder();
        if (!institutions.isEmpty()) {
            for (Institution institution : institutions) {
                institutionsInfo.append(institution.toString()).append(", ");
            }
            if (streetSide.equals(StreetSideType.LEFT_SIDE)) {
                return "House '" + getName() + "', street side: LEFT, information about institutions: " + institutionsInfo;
            }
            if (streetSide.equals(StreetSideType.RIGHT_SIDE)) {
                return "House '" + getName() + "', street side: RIGHT, information about institutions: " + institutionsInfo;
            }
            return "UNDETECTED";
        }
        if (streetSide.equals(StreetSideType.LEFT_SIDE)) {
            return "House '" + getName() + "', street side: LEFT, has not institutions";
        }
        if (streetSide.equals(StreetSideType.RIGHT_SIDE)) {
            return "House '" + getName() + "', street side: RIGHT, has not institutions";
        }
        return "UNDETECTED";
    }
}
