package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Regice extends Regirock{
    public Regice(String name, int level){
        super(name, level);
        setStats(80, 50, 100, 100, 200, 50);
        setType(Type.ICE);
        addMove(new HornLeech());
    }
}
