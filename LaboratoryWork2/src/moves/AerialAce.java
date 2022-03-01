package moves;

import ru.ifmo.se.pokemon.*;

public class AerialAce extends PhysicalMove{
    public AerialAce() {
        super(Type.FLYING, 60, 9999999);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def){
        return true;
    }

    @Override
    protected String describe() {
        return "использует Aerial Ace";
    }
}
