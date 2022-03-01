package utilities;

import java.util.ArrayList;

public interface WalkablePlace extends ObjectInterface{
    boolean hasWalkers = false;
    ArrayList<Person> currentWalkers = new ArrayList<>();
    StreetSideType getStreetSide();

    void deleteWalker(Person walker);
    void addWalker(Person walker);
    void setStreetSide(StreetSideType streetSide);
}
