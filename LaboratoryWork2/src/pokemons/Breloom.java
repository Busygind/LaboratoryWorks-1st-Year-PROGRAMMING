package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Breloom extends Pokemon {
    public Breloom(String name, int level){
        super(name, level);
        setStats(60, 130, 80, 60, 60, 70);
        setType(Type.GRASS, Type.FIGHTING);
        setMove(new AerialAce(), new Swagger(), new Facade(), new CloseCombat());
    }
}
