package pokemons;

import moves.*;

public class Pikachu extends Arbok{
    public Pikachu(String name, int level) {
        super(name, level);
        setStats(35, 55, 40, 50, 50, 90);
        addMove(new Leer());
    }
}
