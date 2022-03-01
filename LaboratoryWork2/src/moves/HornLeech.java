package moves;

import ru.ifmo.se.pokemon.*;

import static java.lang.Math.abs;

public class HornLeech extends PhysicalMove {
    public HornLeech(){
        super(Type.GRASS, 75, 100);
    }

    @Override
    protected void applySelfDamage(Pokemon p, double damage){
        damage = -0.5*abs(p.getHP() - p.getStat(Stat.HP));
        System.out.println("Regice восстанавливает " + -damage + " единицы здоровья");
    }

    @Override
    protected String describe() {
        return "использует Horn Leech";
    }
}
