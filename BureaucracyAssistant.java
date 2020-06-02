import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class BureaucracyAssistant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            gettingBasicCitizensInfo(scanner);

            if(!keepWorking()) {
                break;
            }
        }

    }

    /**
     * Метод вземащ данни подадени от потребителя
     *
     * @param scanner взема данните
     */
    public static String receavingOrder(Scanner scanner){
        return scanner.nextLine();
    }

    /**
     * Метод, натоварен с цялата логика на програмата
     *
     */
    public static void gettingBasicCitizensInfo(Scanner scanner) {
            String order = receavingOrder(scanner);

            String[] normalData;
            String[] specialData;

            File fileReference = new File("resourses/citizens.txt");
            try {
                FileReader fileReader = new FileReader(fileReference);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String lineReference;

                while ((lineReference = bufferedReader.readLine()) != null) {
                    String[] citizenData = lineReference.split("::");

                    // Идеята зад всички неизползвани стрингове беше да докарам изходните данни в
                    // конзолата идентични с примера, но поради слаба подредба преценка на времето и възможностите ми
                    // остават просто да заемат място
                    normalData = citizenData[0].split("@");
                    String citizenType = normalData[0];
                    String citizenName = normalData[1];
                    String citizenFName = normalData[2];
                    String citizenAge = normalData[3];
                    String citizenPhone = normalData[4];
                    String citizenSex = normalData[5];

                    specialData = citizenData[1].split("=");
                    if (specialData[0].equals("{KID}")) {
                        specialData = citizenData[1].split("@");
                        String kidFirstName = specialData[0];
                        String kidFamilyName = specialData[1];
                        String kidAge = specialData[2];
                    } else {
                        String income = specialData[1];
                    }

                    if (order.equals("SELECT")) {
                        System.out.println(Arrays.toString(normalData) + " " + Arrays.toString(specialData));

                        for (String normalDatum : normalData) {
                            if (order.contains(normalDatum)) {
                                System.out.println(Arrays.toString(normalData) + " " + Arrays.toString(specialData));
                            }
                        }

                        for (String specialDatum : specialData) {
                            if (order.contains(specialDatum)) {
                                System.out.println(Arrays.toString(normalData) + " " + Arrays.toString(specialData));
                            }
                        }

                        // Тозо коментар е неуспешният ми опит да накрам програмата да извежда граждани
                        // на базата на два или повече определящи входни данни
                        //
                        //StringBuilder builder = new StringBuilder();
                        //for (String s : normalData) {
                        //    builder.append(s);
                        //}
                        //String normData = builder.toString();

                        //if (order.contains("@")) {
                        //    String[] orderData = order.split("@");
//
                        //    for (int i = 0; i < orderData.length; i++) {
                        //        for (int j = orderData.length-1; j > 0; j--) {
                        //            if (normData.contains(orderData[i]) && normData.contains(orderData[j])) {
                        //                System.out.println(Arrays.toString(normalData) + " " + Arrays.toString(specialData));
                        //            }
                        //        }
                        //    }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Метод предлагащ опция за повторна проверка преди изключване
     *
     * @return решение на потребителя за изключване
     */
    public static boolean keepWorking() {
        boolean running = true;
        while (true) {
            Scanner Hunt    = new Scanner(System.in);
            System.out.println("Искате ли да направите друга проверка");
            String huntCommand = Hunt.nextLine();
            switch (huntCommand) {
                case "y":
                    running = true;
                    break;
                case "n":
                    running = false;
                    break;
            }
            if (huntCommand.equals("y") || huntCommand.equals("n")) {
                break;
            }
        }
        return running;
    }
}

