package com.lab5.client.entities;

/**
 * Класс координат, объекты которого присваиваются элементам колекции
 */
public class Coordinates {
    /**
     * Максимальное допустимое по условию задачи значение координаты Х
     */
    static final int MAX_X_VALUE = 603;
    /**
     * Координата Х
     * <strong>(Максимальное значение поля: 603, поле не может быть null)</strong>
     */
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
        if (x > MAX_X_VALUE) {
            throw new IllegalArgumentException("Некорретное значение координаты х");
        } else {
            this.x = x;
        }
    }

    /**
     * Метод, устанавливающий значение координаты Y у данного объекта координат
     *
     * @param y Y
     */
    public void setY(float y) {
        if (y == Float.NEGATIVE_INFINITY | y == Float.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("Слишком большое значение, попробуйте снова");
        }
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
