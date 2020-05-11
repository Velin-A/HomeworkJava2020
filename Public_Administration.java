import java.util.Scanner;
import java.util.Random;
    /**
     * Реализиране на масив и изпълнение на всякакви сотировки с него
     *
     * @author Велин Андреев
     */
public class Public_Administration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] array = inicializingArray(scanner);

        while (true) {

            menu(array, scanner);

            if (!keepWorking(scanner)) {
                System.out.println("Програмата се изключва...");
                break;
            }
        }
    }

        /**
         * Метод, за създаване на масив, с въведена дължина
         * и елементи от потребителя
         *
         * @param scanner Информазия, въведена от потребителя
         * @return  Масив
         */
    public static int[] inicializingArray(Scanner scanner) {
        System.out.println("Въведете броя на елементите които с които че работите");

        int numberOfElements = scanner.nextInt();
        int[] array = new int[numberOfElements];
        int elementValue;

        System.out.println("Въведете вашите елементи от едно до 1 до 100 включително");

        for (int i = 0; i < numberOfElements; ++i) {

            System.out.println("Въведете " + (i + 1) + "и елемент");
            elementValue = scanner.nextInt();

            while(elementValue > 100 || elementValue <0){
                System.out.println("Въведената стойност е извън позволените граници");
                System.out.println("Въведете " + (i + 1) + "и елемент");
                elementValue = scanner.nextInt();
            }

            array[i] = elementValue;

        }
        return array;
    }

        /**
         * Метод, изписващ меню в конзолата, чрез което потребителя може
         * да работи с масивът
         *
         * @param scanner Информазия, въведена от потребителя
         * @param array Масив, въведен от потребитяля, при стартиране
         */
    public static void menu(int[] array, Scanner scanner) {

        while (true) {
            System.out.println("\n Меню:\n" +
                    "1. Сортиране на въведените числа във възходящ ред\n" +
                    "2. Сортиране на въведените числа в низходящ ред\n" +
                    "3. Търсене на позиция на конкретно число\n" +
                    "4. Разбъркване на числата\n" +
                    "5. Изчисляване на сбора на всички числа\n" +
                    "6. Намиране на най-голямото число\n" +
                    "7. Намиране на най-малкото число\n" +
                    "8. Намиране средно-аритметично на числата\n" +
                    "9. Проверка за симетричност на масива от числа\n" +
                    "10. Обръщане на масива от числа\n" +
                    "11. Визуализирай въведените числа\n" +
                    "12. Изход");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ascendingArrange(array, array.length);
                    break;
                case 2:
                    descendingArrange(array, array.length);
                    break;
                case 3:
                    findingElementsPosition(array, array.length, scanner);
                    break;
                case 4:
                    randomizingArray(array, array.length);
                    break;
                case 5:
                    addingEverythingTogether(array, array.length);
                    break;
                case 6:
                    findingMaxElement(array, array.length);
                    break;
                case 7:
                    findingMinElement(array, array.length);
                    break;
                case 8:
                    calculatingAverage(array, array.length);
                    break;
                case 9:
                    checkingForSymmetry(array, array.length, 0);
                    break;
                case 10:
                    reversingTheArray(array, array.length);
                    break;
                case 11:
                    displayingArray(array);
                    break;
                case 12:
                    break;
            }
            if (choice > 0 && choice < 13) {
                break;
            }
        }
    }                                  //MENU

        /**
         * Метод, подреждащ масивът по възходящ ред
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void ascendingArrange(int[] array, int length) {
        int element;

        for (int i = 1; i < length; i++) {

            for (int j = length - 1; j >= i; j--) {

                if (array[j - 1] > array[j]) {
                    element = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = element;
                }
            }
        }
        System.out.println("Вашият масив е:");
        for (int number : array) {
            System.out.print(number + " ");
        }
    }                           // 1

        /**
         * Метод, подреждащ масивът по низходящ ред
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void descendingArrange(int[] array, int length) {
        int element;

        for (int i = 1; i < length; i++) {

            for (int j = length - 1; j >= i; j--) {

                if (array[j - 1] < array[j]) {
                    element = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = element;
                }
            }
        }
        System.out.println("Вашият масив е:");
        for (int number : array) {
            System.out.print(number + " ");
        }
    }                          // 2

        /**
         * Метод, подреждащ масивът по възходящ ред
         *
         * @param scanner Информазия, въведена от потребителя
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void findingElementsPosition(int[] array, int length, Scanner scanner) {
        int elementsPosition = 0;

        System.out.println("На кой от елементите искате да знаете позицията?");
        int element = scanner.nextInt();

        while (elementsPosition < length) {
            if (array[elementsPosition] == element) {
                System.out.println("Елемента " + element + " е на " + (elementsPosition + 1) + "-а позиция");
                break;
            } else
                elementsPosition = elementsPosition + 1;
        }
        if (elementsPosition == length) {
            System.out.println("Елемента не беше на мерен, убедете се, че е част от масива?");
        }
    }   // 3

        /**
         * Метод, разбъркващ елементите в масива на случаен принцип
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void randomizingArray(int[] array, int length) {
        Random random = new Random();

        for (int elementPosition = 0; elementPosition < length; elementPosition++) {

            int newPosition = random.nextInt(length);
            int newPositionHolder = array[newPosition];

            array[newPosition] = array[elementPosition];
            array[elementPosition] = newPositionHolder;
        }

        System.out.println("Вашият масив е:");
        for (int number : array) {
            System.out.print(number + " ");
        }
    }                           // 4

        /**
         * Метод, събиращ стойностите на елементите в масива
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void addingEverythingTogether(int[] array, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum = sum + array[i];
        }
        System.out.println("Сумата от елементите на масива е " + sum);
    }                   // 5

        /**
         * Метод, намиращ елемента с най-висока стойност в масива
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void findingMaxElement(int[] array, int length) {
        int maxValue = 0;
        for (int value : array) {
            if (value > maxValue) {
                maxValue = value;
            }
        }
        System.out.println("Елемента с най-висока стойност е " + maxValue);
    }                          // 6

        /**
         * Метод, намиращ елемента с най-ниска стойност в масива
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void findingMinElement(int[] array, int length) {
        int minValue = array[0];
        for (int i = 1; i < length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        System.out.println("Елемента с най-ниска стойност е " + minValue);
    }                          // 7

        /**
         * Метод, намиращ средната-аретмитична стойност на елементите в масива
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масивът
         */
    public static void calculatingAverage(int[] array, int length) {
            double sum = 0.0;
            for (int value : array) {
                sum = sum + value;
            }
            double average = (sum / length);
            System.out.println("Средното-аретметично от елементите на масива е " + average);
        }                         // 8

        /**
         *  Метод, проверяващ дали елементите в масива са симетрично разположени
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на масива
         * @param element Позиция на елемент в масива
         * @return Потвърждение или отрицание, дали масивът е симетричен
         */
    public static boolean checkingForSymmetry(int[] array, int length, int element) {
        if (element > length / 2) {
            System.out.println("Масива е симетричен");
            return true;

        } else if (array[element] != array[length - 1 - element]){
            System.out.println("Масива НЕ е симетричен");
        return false;

        }else
            return checkingForSymmetry(array, length, element+1);
    }        // 9

        /**
         * Метод, пренареждащ елементите в масива наобратно
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         * @param length Дължината на мас
         */
    public static void reversingTheArray(int[] array, int length) {
        int[] reversedArray = new int[length];

        for (int value : array) {
            reversedArray[length - 1] = value;
            length = length - 1;
        }

        System.out.println("Вашият масив е:");
        for (int number : reversedArray) {
            System.out.print(number + " ");
        }
    }                          // 10

        /**
         * Метод, извеждащ масива в конзолата
         *
         * @param array Масив, въведен от потребитяля, при стартиране
         */
    public static void displayingArray(int[] array){
        System.out.println("Вашият масив е:");
        for(int element: array) {
            System.out.print(element + " ");
        }
    }                                         // 11

        /**
         * Метод, проверяващ дали потребителяя иска да продължи работа с масива
         *
         * @param scanner Информазия, въведена от потребителя
         * @return Отговор дали потребителя иска да продължи работа с масива
         */
    public static boolean keepWorking(Scanner scanner) {
        boolean goBackToTheMenu = true;
        while (true) {
            System.out.println("\nИскате ли да се върнете в менюто? (y/n)");
            String huntCommand = scanner.nextLine();

            switch (huntCommand) {
                case "y":
                    goBackToTheMenu = true;
                    break;
                case "n":
                    goBackToTheMenu = false;
                    break;
            }
            if (huntCommand.equals("y") || huntCommand.equals("n")) {
                break;
            }
        }
        return goBackToTheMenu;
    }                                     // 12
}