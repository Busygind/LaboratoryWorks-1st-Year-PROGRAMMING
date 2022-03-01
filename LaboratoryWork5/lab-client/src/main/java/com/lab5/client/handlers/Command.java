package com.lab5.client.handlers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация, присваиваемая методам, которые привязаны к командам пользователя
 */
@Retention(RetentionPolicy.RUNTIME)

@Target(ElementType.METHOD)
public @interface Command {

    /**
     * Имя команды
     * @return имя команды
     */
    String name();

    /**
     * Список аргументов команды
     * @return строка с аргументами
     */
    String args();

    /**
     * Описание команды
     * @return строка с описанием команды
     */
    String desc();

    /**
     * Количество необходимых аргументов для данной команды
     * @return количество аргументов
     */
    int countOfArgs();

    /**
     * Возможные записи текущей команды, отличные от name
     * @return массив возможных названий
     */
    String[] aliases();

}
