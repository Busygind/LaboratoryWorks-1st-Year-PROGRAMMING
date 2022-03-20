package lab.common.util.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Класс координат, объекты которого присваиваются элементам коллекции
 */
public class Coordinates implements Serializable {
    private static final int MAX_X_VALUE = 603;
    /**
     * Координата Х
     * <strong>(Максимальное значение поля: 603, поле не может быть null)</strong>
     */
    @Max(MAX_X_VALUE)
    @NotNull
    private Integer x;
    /**
     * Координата Y
     */
    private float y;

    /**
     * Метод, устанавливающий значение координаты Х в соответствии с ограничивающими условиями
     *
     * @param x Х
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Метод, устанавливающий значение координаты Y у данного объекта координат
     *
     * @param y Y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Метод, возвращающий значение координаты Х у данного объекта координат
     *
     * @return Х
     */
    public Integer getX() {
        return this.x;
    }

    /**
     * Метод, возвращающий значение координаты Y у данного объекта координат
     *
     * @return Y
     */
    public float getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y;
    }
}
