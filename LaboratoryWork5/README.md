# Лабораторная работа №5 по программированию
## Выполнил Бусыгин Дмитрий, Университет ИТМО, программная инженерия
Цель работы: создать расширяемое консольное приложение для обработки коллекции объектов, чтение и запись которых осуществляется с помощью xml-файла. с подробным заданием можно ознакомиться на [se.ifmo](https://se.ifmo.ru/courses/programming#labs). 

Описание исходного кода в формате **javadoc**: [открыть](https://github.com/Busygind/LaboratoryWork5-PROGRAMMING-ITMO-University/tree/master/doc/javadoc.zip)

Сборка проекта осуществлена с помощью: **Maven**

Используемый тип коллекции: **java.util.HashSet**

### Руководство по установке и использованию:
#### Для запуска готового приложения без компиляции:
1.  Проверить наличие исполнителя java-приложений на устройстве (JRE). Если он отсутствует, то найти его можно [здесь](https://www.java.com/en/download/windows_offline.jsp) (пример для Windows 10)
2.  Найти в [папке](https://github.com/Busygind/LaboratoryWork5-PROGRAMMING-ITMO-University/tree/master/lab-client/target) jar-архив **lab-client-0.1-jar-with-dependencies.jar** и поместить его в любую директорию на устройстве
3.  В ту же папку поместить xml-файл с текущей коллекцией драконов (изначально он может быть пустым, но должен быть инициализирован: \<?xml version="1.0" encoding="UTF-8"?\>)
4.  Запустить командную строку, перейти в рабочую директорию с помощью команды **cd directoryName** 
5.  Выполнить следующую команду: **java -jar lab-client-0.1-jar-with-dependencies.jar filename.xml**
