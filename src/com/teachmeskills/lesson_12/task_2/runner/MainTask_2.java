package com.teachmeskills.lesson_12.task_2.runner;

import com.teachmeskills.lesson_12.task_2.genericarray.GenericArray;
import java.util.Arrays;

/**
 *  Задание 2. (Дополнительное задание)
 *    Представим, что в Java нет коллекции типа ArrayList.
 *    Создать свой класс, симулирующий работу класса динамической коллекции - т.е. создать свою кастомную коллекцию.
 *    В основе коллекции будет массив.
 *    1) Кастомная коллекция должна хранить элементы разных классов(т.е. это generic).
 *    2) Предусмотреть операции добавления элемента, удаления элемента, получение элемента по индексу,
 *       проверка есть ли элемент в коллекции, метод очистки всей коллекции.
 *    3) Предусмотреть конструктор без параметров - создает массив размером по умолчанию.
 *    4) Предусмотреть конструктор с задаваемым размером внутреннего массива.
 *    5) Предусмотреть возможность автоматического расширения коллекции при добавлении элемента в том случае,
 *       когда коллекция уже заполнена.
 */

public class MainTask_2 {

    public static void main(String[] args) {
        //lengthArray
        //addElement
        //deleteElement
        //setElement
        //getElement
        //containsElement
        //deleteAllElement
        //equals
        //hashCode
        //toString

        GenericArray<Character> charArray = new GenericArray<>();    // новый массив
        System.out.println(charArray +" length "+ charArray.lengthArray());

        GenericArray<Integer> intArray = new GenericArray<>();       // новый массив
        intArray.setElement(9,65);
        System.out.println(Arrays.toString(new GenericArray[]{intArray}));

        intArray.addElement(5);
        intArray.addElement(643);
        intArray.addElement(76);
        intArray.addElement(12);
        System.out.println(Arrays.toString(new GenericArray[]{intArray}));

        intArray.deleteElement(0);
        System.out.println(intArray.getElement(1));
        System.out.println(intArray.containsElement(13));
        System.out.println(intArray.containsElement(65));

        intArray.clearAllElement();
        System.out.println(intArray +" length "+ intArray.lengthArray());
    }

}
