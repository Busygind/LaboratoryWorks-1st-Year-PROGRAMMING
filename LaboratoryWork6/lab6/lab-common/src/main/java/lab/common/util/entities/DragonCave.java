package lab.common.util.entities;

/**
 * Класс пещеры дракона, объекты которого присваиваются полю cave элементов коллекции
 */
public class DragonCave {
    /**
     * Глубина текущей пещеры
     */
    private double depth;
    /**
     * Количество сокровищ в текущей пещере
     * <strong>(Значение поля должно быть больше 0)</strong>
     */
    private int numberOfTreasures;

    /**
     * Метод, устанавливающий объекту пещеры глубину
     *
     * @param depth глубина пещеры, диапазон допустимых значений совпадает с диапазоном типа double
     */
    public void setDepth(double depth) {
        if (depth >= Double.MAX_VALUE) {
            throw new IllegalArgumentException("Значение слишком большое, попробуйте снова");
        }
        if (depth <= Double.MIN_VALUE) {
            throw new IllegalArgumentException("Значение слишком маленькое, попробуйте снова");
        }
        this.depth = depth;
    }

    /**
     * Метод, устанавливающий объекту пещеры количество сокровищ
     *
     * @param numberOfTreasures количество сокровищ, диапазон допустимых значений (0; ]
     */
    public void setNumberOfTreasures(int numberOfTreasures) {
        if (numberOfTreasures <= 0) {
            throw new IllegalArgumentException("Некорректное количество сокровищ в пещере, попробуйте снова");
        }
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
        return "depth: " + depth + " numbers of treasures: " + numberOfTreasures;
    }
}
