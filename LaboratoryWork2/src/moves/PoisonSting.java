package moves;

import ru.ifmo.se.pokemon.*;

public class PoisonSting extends PhysicalMove {
    public PoisonSting(){
        super(Type.POISON, 15, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        if (Math.random()<0.3) {
            Effect.poison(p);
        }
    }

    @Override
    protected String describe() {
        return "использует Poison Sting";
    }
}
