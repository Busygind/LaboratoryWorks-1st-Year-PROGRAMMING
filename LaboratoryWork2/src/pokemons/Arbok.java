package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Arbok extends Pokemon{
    public Arbok(String name, int level) {
        super(name, level);
        setStats(60, 95, 69, 65, 79, 80);
        setType(Type.POISON);
        setMove(new IceBeam(), new Rest());
    }
}
