package com.teachmeskills.lesson_12.task_1.runner;

import com.teachmeskills.lesson_12.task_1.numberdocument.*;
import java.io.*;
import java.util.*;

/**
 *  Задание 1. (Основное задание)
 *    Вернемся к домашнему заданию занятия номер 11 и модифицируем его.
 *
 *    Программа должна получать имена файлов с номерами документов с консоли: каждая новая строка - это путь к файлу и имя файла.
 *    Для завершения ввода списка файлов следует ввести 0.
 *    После получения списка документов программа должна обработать каждый документ: вычитать из файла номера
 *       документов и провалидировать их.
 *    В конце работы создать один файл отчет с выходной информаций: номер документа - комментарий(валиден или не валиден и
 *       по какой причине).
 *    Пусть каждый файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 *    Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности
 *       docnum(далее любая последовательность букв/цифр) или contract(далее любая последовательность букв/цифр).
 *    Учесть, что номера документов могут повторяться в пределах одного файла и так же разные документы могут содержать
 *       одни и те же номера документов.
 *    Если номера документов повторяются, то повторные номера документов не проверять, не валидировать.
 *
 *
 *  Немного технических деталей:
 *    1) Считать с консоли список документов: раз список - то это коллекции типа List,
 *       никаких других условий нет - значит все имена файлов с консоли сохраняем в структуру данных ArrayList.
 *
 *    2) Номера документов могут повторяться, но повторные документы обрабатывать не надо и валидировать не надо,т.е.
 *       по сути дубликаты нам не нужны - значит, надо считать номера документов из файлов и все номера документов
 *       сохранять в коллекцию типа Set. Других условий нет, значит можно использовать HashSet.
 *
 *    3) В конце должна быть структура номер документа - комментарий - т.е. эта структура типа ключ-значений,
 *       значит это коллекция типа Map.
 *    Других условий нет - значит, можно использовать HashMap. Создать такую структуру и уже потом сделать цикл по этой
 *       структуре и записать всю информацию из этой мапы в файл-отчет.
 *
 *
 *      (--->   Предыдущее Задание   <---)
 *    Допустим есть файл с номерами документов.
 *    Номером документа является строка, состоящая из букв и цифр(без служебных символов).
 *    Пусть этот файл содержит каждый номер документа с новой строки и в строке никакой другой информации,
 *       только номер документа.
 *    Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности
 *       docnum(далее любая последовательность букв/цифр) или сontract(далее любая последовательность букв/цифр).
 *    Написать программу для чтения информации из входного файла - путь к входному файлу должен задаваться через консоль.
 *    Программа должна проверять номера документов на валидность.
 *    Валидные номера документов следует записать в один файл-отчет.
 *    Невалидные номера документов следует записать в другой файл-отчет, но после номеров документов следует добавить
 *       ифнформацию о том, почему этот документ невалиден.
 */

public class MainTask_1 {

    public static void main(String[] args) {
        ArrayList<String> arrayListPath = getArrayListPath();
        Set<String> numDocHashSet = new HashSet<>();
        Map<String,String> numDocHashMap = new HashMap<>();

        String numDoc;                                // записываем вводимый номер документа
        String information;                           // информация о номере документа(если пусто,значит ошибок нет)
        String pathOutReportFile = "C:\\Users\\admin\\Desktop\\ReportFile.txt";

        for(String elem : arrayListPath) {
            // из(C:\Users\admin\Desktop\Lesson_11.txt) и еще одного файла, будет считан номер документа
            try(FileInputStream fis = new FileInputStream(elem);
                FileOutputStream reportFileFos = new FileOutputStream(pathOutReportFile);// и сюда "номер док." будет записан

                // (в данном случаее я подумал, что это будет улучшенная версия) потому, что читаем "номер док." целиком
                BufferedReader buffInFile = new BufferedReader(new InputStreamReader(fis));
                BufferedWriter buffOutFile = new BufferedWriter(new OutputStreamWriter(reportFileFos))) {// запис. "номер док." целиком

                while ((numDoc = buffInFile.readLine()) != null) {  // читаем с консоли
                    if (numDocHashSet.add(numDoc)) {  // добавили "номер док." в (HashSet)
                        information = NumberDocument.checkNumDoc(numDoc);

                        // добавляем(неповторяющиеся значения) и "номер док." и информацию о нем в (HashMap)
                        numDocHashMap.put(numDoc, information);
                    }
                }

                for (String key : numDocHashMap.keySet()) {  // перебираем все "ключи", записываем в ФАЙЛ (ReportFile)
                    if(numDocHashMap.get(key).equals("")) {
                        buffOutFile.write(key);

                    } else {
                        buffOutFile.write(key +" - "+ numDocHashMap.get(key));
                    }
                    buffOutFile.newLine();
                    buffOutFile.flush();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // для проверки результата через консоль(не открывая файл)
        //System.out.println("map"+ numDocHashMap);
    }

    private static ArrayList<String> getArrayListPath() {
        System.out.print("Укажите путь к файлу: ");
        Scanner scan = new Scanner(System.in);
        String path;
        ArrayList<String> array = new ArrayList<>();

        boolean thatEnd = false;
        while(!thatEnd) {
            path = scan.nextLine();            // C:\Users\admin\Desktop\Lesson_11.txt
                                               // C:\Users\admin\Desktop\Lesson_12.txt
            if(!path.startsWith("0")) {
                array.add(path);
            } else {
                thatEnd = true;
            }
        }

        scan.close();
        return array;
    }

}
