package com.teachmeskills.lesson_12.task_2.genericarray;

import java.util.Arrays;

public class GenericArray<T> {

    private Object[] arrayObj;  // с этим массивом мы и будем работать
    private Object[] newArrayObj;  // нужен только для работы нескольких методов

    public GenericArray() {   // массив по умолчанию(на 10 элементов)
        this.arrayObj = new Object[10];
    }

    public GenericArray(int length) {   // задать длину массиву(через создание объекта этого класса)
        this.arrayObj = new Object[length];
    }


    // длина массива
    public int lengthArray() {
        return arrayObj.length;
    }


    // получаем элемент по введенному индексу
    public T getElement(int index) {
        return (T) this.arrayObj[index];
    }


    // расширяем массив(по формуле)
    private Object[] extendArray(int index) {
        int size = (index * 3) / 2 + 1;
        newArrayObj = new Object[size];

        // присваиваем новое значение массиву(скопировав старый массив и поменяв размер на новый)
        newArrayObj = Arrays.copyOf(arrayObj, size);
        arrayObj = newArrayObj;

        return arrayObj;
    }


    // добавляем элемент(в самый конец массива)
    public Object[] addElement(T element) {
        for (int i = 0; i <= arrayObj.length; i++) {
            if (i == arrayObj.length){
                int a = arrayObj.length;
                arrayObj = extendArray(a);
                arrayObj[i] = element;
                break;
            }
            if (arrayObj[i] == null) {
                arrayObj[i] = element;
                break;
            }
        }

        return arrayObj;
    }


    // удаляем ячейку со значением(элемента массива как будто и не было)
    public void deleteElement(int index) {
        if (index < arrayObj.length) {  // удаляем элементы под индексом который есть в массиве
            arrayObj[index] = null;

            for (int i = (index + 1); i < arrayObj.length; i++) { // смещаем все элементы(налево)
                arrayObj[i - 1] = arrayObj[i];
                arrayObj[i] = null;
            }

            int size = (arrayObj.length - 1);   // уменьшаем массив на "удаленный" элемент
            newArrayObj = new Object[size];
            newArrayObj = Arrays.copyOf(arrayObj, size);
            arrayObj = newArrayObj;
        }
    }


    // проверка, находится ли элемент в массиве
    public boolean containsElement(T element) {
        boolean check = false;

        if (element != null) {
            for (Object elem : arrayObj) {
                if (element.equals(elem)) {
                    check = true;
                    break;
                }
            }
        }

        return check;
    }


    // очистка всего массива
    public T clearAllElement() {
        for (int i = 0; i < arrayObj.length; i++) {
            arrayObj[i] = null;
        }

        return (T) arrayObj;
    }


    // устанавливаем(или перезаписываем) значение элемента в массиве по указанному индексу
    // (если длина массива меньше или равна вводимому индексу, массив рассширяется и добавляется элемент)
    public void setElement(int index, T element) {
        if (arrayObj.length <= index) {
            arrayObj = extendArray(index);
            arrayObj[index] = element;

        } else {
            arrayObj[index] = element;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericArray<?> that = (GenericArray<?>) o;
        return Arrays.equals(arrayObj, that.arrayObj);
    }


    @Override
    public int hashCode() {
        return Arrays.hashCode(arrayObj);
    }


    @Override
    public String toString() {
        return "class GenericArray: {arrayObj "+ Arrays.toString(arrayObj) +'}';
    }

}
