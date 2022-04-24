package lab7.common.util.entities;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Класс пещеры дракона, объекты которого присваиваются полю cave элементов коллекции
 */
public class DragonCave implements Serializable, Comparable<DragonCave> {
    /**
     * Глубина текущей пещеры
     */
    private double depth;
    /**
     * Количество сокровищ в текущей пещере
     * <strong>(Значение поля должно быть больше 0)</strong>
     */
    private int numberOfTreasures;

    public DragonCave(double depth, int numberOfTreasures) {
        this.depth = depth;
        this.numberOfTreasures = numberOfTreasures;
    }

    public DragonCave() {
        //do nothing, just initialize
    }

    /**
     * Метод, устанавливающий объекту пещеры глубину
     *
     * @param depth глубина пещеры, диапазон допустимых значений совпадает с диапазоном типа double
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * Метод, устанавливающий объекту пещеры количество сокровищ
     *
     * @param numberOfTreasures количество сокровищ, диапазон допустимых значений (0; ]
     */
    public void setNumberOfTreasures(int numberOfTreasures) {
        this.numberOfTreasures = numberOfTreasures;
    }

    /**
     * Метод, возвращающий глубину текущей пещеры
     *
     * @return глубина пещеры
     */
    public double getDepth() {
        return depth;
    }

    /**
     * Метод, возвращающий количество сокровищ в данной пещере
     *
     * @return количество сокровищ
     */
    public int getNumberOfTreasures() {
        return numberOfTreasures;
    }

    @Override
    public String toString() {
        return "depth: " + depth + " number of treasures: " + numberOfTreasures;
    }

    @Override
    public int compareTo(DragonCave o) {
        if (o == null) {
            return 1;
        }
        return Comparator.comparing(DragonCave::getDepth).thenComparing(DragonCave::getNumberOfTreasures).compare(this, o);
    }
}
