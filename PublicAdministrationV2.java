import java.util.Scanner;


/**
 * Реализиране на масив или стринг и изпълнение на всякакви сотировки с него
 *
 * @author Велин Андреев
 */
public class PublicAdministrationV2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            mainMenu(scanner);

            if
            (terminatingTheProgram(scanner)) {
                System.out.println("Програмата се изключва...");
                break;
            }
        }
    }

    /**
     * Метод, отпечатващ главното меню в конзолата
     *
     * @param scanner определя, как ще продълви програмата, спрямо избора на потребителя
     */
    public static void mainMenu(Scanner scanner) {

        System.out.println("Меню:\n" +
                "1. Работа с числа\n" +
                "2. Работа с думи\n" +
                "3. Изход от програмата");

        while (true) {

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    int[] array = initializingArray(scanner);
                    processingNumbersMenu(scanner, array);
                    break;
                case 2:
                    String[] words = initializingString(scanner);
                    processingStringsMenu(scanner, words);
                    break;
                case 3:
                    break;
            }
            if (choice > 0 && choice < 4) {
                break;
            }
        }
    }

    /**
     * Метод създаващ масив от числа, използвайки данни от потребителя
     *
     * @param scanner Приема дължината и елементите в масива
     * @return инизиализираният масив
     */
    public static int[] initializingArray(Scanner scanner) {
        System.out.println("Въведете броя на елементите които с които че работите");

        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];
        int elementValue;

        System.out.println("Въведете вашите елементи от едно до 1 до 10 000 включително");

        for (int i = 0; i < numberOfElements; ++i) {

            System.out.println("Въведете " + (i + 1) + "и елемент");
            elementValue = scanner.nextInt();

            while (elementValue > 10000 || elementValue < 1) {
                System.out.println("Въведената стойност е извън позволените граници (между 1 и 10 000)");
                System.out.println("Въведете " + (i + 1) + "и елемент");
                elementValue = scanner.nextInt();
            }

            array[i] = elementValue;

        }
        return array;
    }


    /**
     * Метод, комбиниращ визуализацията и логиката на менъто за работа с числа
     */
    public static void processingNumbersMenu(Scanner scanner, int[] array) {
        processingNumbersVisual();
        processingNumbersLogic(scanner, array);
    }
    /**
     * Метод отпечатващ менюто за рабоа с числа в конзолата
     */
    public static void processingNumbersVisual() {
        System.out.println("\n Меню:\n" +
                "1. Извеждане само на простите числа от масива\n" +
                "2. Извеждане на най-често срещан елемент в масива\n" +
                "3. Извеждане на максимална редица от нарастващи елементи в масива\n" +
                "4. Извеждане на максимална редица от намаляващи елементи в масива\n" +
                "5. Извеждане на максимална редица от еднакви елементи в масива\n" +
                "6. Извеждане на последователност от числа от масива, които имат сума равна на случайно число\n" +
                "7. Връщане назад към основното меню");
    }
    /**
     * Метод, отговарящ за логиката в меню "Работа с числа"
     *
     * @param scanner Определя каква работа ще бъде исвършена със зададеният маисв
     */
    public static void processingNumbersLogic(Scanner scanner, int[] array) {

        while (true) {

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    displayingArray(array);
                case 1:
                    findingPrimeNumbers(scanner, array);
                    break;
                case 2:
                    searchingForTheMostFrequent(scanner, array);
                    break;
                case 3:
                    printingMaxRow(scanner, array);
                    break;
                case 4:
                    printingMinRow(scanner, array);
                    break;
                case 5:
                    findingTheLongestRowOfSameElements(scanner, array);
                    break;
                case 6:
                    System.out.println("NOT READY YET");
                    processingNumbersMenu(scanner, array);
                    break;
                case 7:
                    mainMenu(scanner);
                    break;
            }
            if (choice > 0 && choice < 8) {
                break;
            }
        }
    }

    /**
     * Метод извеждащ простите числа от зададеният масив
     *
     * @param array масиват който ще се обработва
     */
    public static void findingPrimeNumbers(Scanner scanner, int[] array) {
        System.out.println("Простите числа, сред зададените са: ");
        for (int num : array) {
            boolean flag = false;
            for (int j = 2; j <= num / 2; ++j) {
                if (num % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                System.out.println("- " + num);
        }

        processingNumbersMenu(scanner, array);
    }

    /**
     * Метод намиращ най-срещаният елемнт в масива
     *
     * @param array   масивът чиито елементи ще се проверяват
     */
    public static void searchingForTheMostFrequent (Scanner scanner, int [] array){
        int maxCount  = 0;
        int result = array[0];
        int counter = 0;

        for (int i = 1; i < array.length; i++){
            if (array[i] == array[i - 1])
                counter++;

                if (counter > maxCount){
                    maxCount = counter;
                    result = array[i - 1];
                }
                counter = 1;
        }

        System.out.println("\n" + result + " е най-срещаното число");

        processingNumbersMenu(scanner, array);
    }

    /**
     * Метод намиращ най-дългата редица от последователни, покачващи се елементи
     *
     * @param array Масивът, който ще се проверява за оследователни, покачващи се елементи
     */
    public static void printingMaxRow (Scanner scanner, int [] array) {
        //този метод е копие от (https://github.com/Aleksandur119/BarcaDNA/blob/master/Domashno6.java)
        //нама смисъл да се лъжем и да се гоним кой, от кой е преписал, знам че не трябваше, но наистина не
        //знаех кака точно да го направя и все още не ми е ясно
        //
        //по него съответно са направени и следващите два...
        int maxRow = 0;
        int counter = 0;
        int endIndex = 0;

        for (int i = 1; i < array.length; i++){
            if (array[i - 1] < array[i]){
                counter++;
            }
            else{
                if (counter > maxRow){
                    maxRow = counter;
                    endIndex = i - 1;
                }
                counter = 0;
            }
            if (i == array.length - 1){
                if (counter > maxRow){
                    maxRow = counter;
                    endIndex = i;
                }
            }
        }
            System.out.print("Максималната редица в зададения масив е: ");
        for (int i = endIndex - maxRow; i <= endIndex; i++){
            System.out.print(array[i] + " ");
            }

        processingNumbersMenu(scanner, array);
    }

    /**
     * Метод намиращ най-дългата редица от последователни, намалящи елементи
     *
     * @param array Масивът, който ще се проверява за оследователни, намалящи елементи
     */
    public static void printingMinRow (Scanner scanner, int [] array) {
        int minRow = 0;
        int counter = 0;
        int endIndex = 0;

        for (int i = 1; i < array.length; i++){
            if (array[i - 1] > array[i]){
                counter++;
            }
            else{
                if (counter > minRow){
                    minRow = counter;
                    endIndex = i - 1;
                }
                counter = 0;
            }
            if (i == array.length - 1){
                if (counter > minRow){
                    minRow = counter;
                    endIndex = i;
                }
            }
        }
            System.out.print("Минималната редица в зададения масив е: ");
        for (int i = endIndex - minRow; i <= endIndex; i++){
            System.out.print(array[i] + " ");
        }

        processingNumbersMenu(scanner, array);
    }

    /**
     * Метод намиращ най-дългата редица от последователни, равни елементи
     *
     * @param array Масивът, който ще се проверява за оследователни, равни елементи
     */
    public static void findingTheLongestRowOfSameElements(Scanner scanner, int[] array) {

        int seqRow = 0;
        int counter = 0;
        int endIndex = 0;

        for (int i = 1; i < array.length; i++){
            if (array[i - 1] == array[i]){
                counter++;
            }
            else{
                if (counter > seqRow){
                    seqRow = counter;
                    endIndex = i - 1;
                }
                counter = 0;
            }
            if (i == array.length - 1){
                if (counter > seqRow){
                    seqRow = counter;
                    endIndex = i;
                }
            }
        }
            System.out.print("Равна редица в зададения масив е: ");
        for (int i = endIndex - seqRow; i <= endIndex; i++){
            System.out.print(array[i] + " ");
        }
        processingNumbersMenu(scanner, array);
    }




    /**
         * Метод, комбиниращ визуализацията и логиката на менюто за работа с думи
         *
         */
    public static void processingStringsMenu(Scanner scanner, String[] words) {
        processingStringsVisual();
        processingStringsLogic(scanner, words);
    }
    /**
     * Метод отпечатващ менюто за рабоа с думи в конзолата
     */
    public static void processingStringsVisual(){
        System.out.println("\n Меню:\n" +
                "1. Обърнете буквите на думите от масива наобратно и ги визуализирайте в конзолата\n" +
                "2. Изведете броя на повтарящите се символи за всяка една от думите в масива\n" +
                "3. Изведете броя на символите за всяка една от думите в масива\n" +
                "4. Изведете броя на повтарящите се думи от масива\n" +
                "5. Връщане назад към основното меню");
    }
    /**
     * Метод, отговарящ за логиката в меню "Работа с думи"
     *
     * @param scanner Определя каква работа ще бъде исвършена със зададеният маисв
     */
    public static void processingStringsLogic(Scanner scanner, String[] words){
        while (true) {

            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    displayingString(scanner, words);
                break;
                case 1:
                    reversingAllWords(scanner, words);
                break;
                case 2:
                    System.out.println("NOT READY YET");
                    processingStringsMenu(scanner, words);
                    break;
                case 3:
                    checkingTheLength(scanner, words);
                    break;
                case 4:
                    checkingForReapeatingWords(scanner, words);
                    break;
                case 5:
                    mainMenu(scanner);
                    break;
            }
            if (choice > 0 && choice < 6) {
                break;
            }
        }
    }

    /**
     * Метод, създаващ низа, който ще бъде обработван
     *
     * @param scanner приема данни за дължианта ис съдържанието на масива
     * @return  връща, вече създадения низ
     */
    public static String[] initializingString(Scanner scanner){
        System.out.println("Въведете броя на думите с които ще работите");

        int numberOfWords = scanner.nextInt();
        String[] words = new String[numberOfWords];
        String word;

        System.out.println("Въведете думи, с най-много 20 букви");
        for (int i = 0; i < numberOfWords; ++i) {

            System.out.println("Въведете " + (i + 1) + "a дума");
            word = scanner.nextLine();

            if (word.length() > 20) {
                System.out.println("Въведената дума не е в зададеният радиус (м/у 1 и 20 букви");
                System.out.println("Въведете " + (i + 1) + "a дума");
                word = scanner.nextLine();
            }

            while (word.length() < 1){
                word = scanner.nextLine();
            }

            words[i] = word;

        }
    return words;
    }

    /**
     * Метод, обръщащ въведените от потребителя думи
     *
     * @param words масива чиито думите ще бъдат обърнати от метода
     */
    public static void reversingAllWords(Scanner scanner, String [] words){
        String reversedString = "";
        for (String word : words) {
            String reversedWord = "";
            for (int j = word.length() - 1; j >= 0; j--) {

                reversedWord = reversedWord + word.charAt(j);
            }
            reversedString = reversedString + reversedWord + " ";
        }
        System.out.println("Думите се обръщат...\n" + reversedString);

        processingStringsMenu(scanner, words);
    }

    /**
     * Метод, отпечатващ броя елементи на всяка дума в низа
     *
     * @param words масива, по който се извършва проверката
     */
    public static void checkingTheLength(Scanner scanner, String [] words){
        for (String word : words){
            System.out.println(word + " – брой символи: " + word.length());
        }

        processingStringsMenu(scanner, words);
    }

    /**
     * Метод отпечатващ броя на повтарящите се елементи
     *
     * @param words масива който ще се проверява за повтарящи се еементи
     */
    public static void checkingForReapeatingWords(Scanner scanner, String [] words){

        //Знам че задачата не беше точно такава, но това е най- близкото което оспях да направя

        int counter = 0;
        for(int i = 0; i<words.length-1; i++) {
            for (int j = i+1; j<words.length; j++) {
                if(words[i].compareTo(words[j])>0) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }

        for(int i = 0; i < words.length; i++){
            String temp = words[i];
            System.out.print(temp+" ");
            for(int j = i+1; j < words.length; j++){
                String temp2 = words[j];
                if(temp.compareTo(temp2) == 0){
                    counter++;
                    System.out.print(temp2+" ");
                    i++;
                }
            }
            System.out.println();
        }
        processingStringsMenu(scanner, words);
    }




    /**
     * Метод, отговарящ за приключването на програмата
     *
     * @param scanner приема информация от потребителя, дали да прекрати програмата
     * @return решемието дали програмата да бъде прекратена
     */
    public static boolean terminatingTheProgram(Scanner scanner) {
        boolean terminating = false;
        System.out.println("\nИскате ли да затворите програмата? (y/n)");

        while (true) {
            String workCommand = scanner.nextLine();

            switch (workCommand) {
                case "y":
                    terminating = true;
                    break;
                case "n":
                    terminating = false;
                    break;
            }
            if (workCommand.equals("y") || workCommand.equals("n")) {
                break;
            }
        }
        return terminating;
    }

    public static void displayingArray(int[] array){
        System.out.println("Вашият масив е:");
        for(int element: array) {
            System.out.print(element + " ");
        }
    }   //for testing DELETE AT THE END !!!

    public static void displayingString(Scanner scanner, String[] words){
        System.out.println("Вашият текст е ");
        for(String word : words) {
            System.out.print(word + " ");
        }
        processingStringsMenu(scanner, words);

    }   //for testing DELETE AT THE END !!!
}
