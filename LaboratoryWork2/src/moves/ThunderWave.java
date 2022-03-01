package moves;

import ru.ifmo.se.pokemon.*;

public class ThunderWave extends StatusMove {
    public ThunderWave(){
        super(Type.ELECTRIC, 0, 90);
    }

    @Override
    protected void applyOppEffects(Pokemon p){
        Effect.paralyze(p);
    }

    @Override
    protected String describe(){
        return "использует Thunder Wave";
    }
}
