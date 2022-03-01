import pokemons.*;
import ru.ifmo.se.pokemon.*;

public class BattleSimulator {

    public static void main(String[] args) {
        Battle b = new Battle();
        Raichu p1 = new Raichu("S1mple", 1);
        Pikachu p2 = new Pikachu("electronic", 1);
        Arbok p3 = new Arbok("b1t", 1);
        Breloom p4 = new Breloom("Xyp9x", 1);
        Regirock p5 = new Regirock("dupreeh", 1);
        Regice p6 = new Regice("gla1ve", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}