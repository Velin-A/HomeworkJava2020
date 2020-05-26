import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Готино описание на програмата
 *
 * @author Велин Андреев
 */
public class PrettyVisibleThread {

    public static void main(String[] args){

        execute();

    }

    //Предстои много гаден за четене код, съжелявам за което.
    //Ако се откажете докато го проверявате, напълно ви подкрепям,
    //пък и като качество не е много така че няма да е загуба

    /**
     * Метод вземаш данни за дължина, ширирна и брой на мините на полето
     *
     * @return маив съдържащ нужните данни за полето
     */
    public static int[] table(){
        int[] array = new int [3];
        File fileReference = new File ("resource/enemy_teritory.txt");
        {
            try {
                FileReader fileReader           = new FileReader(fileReference);
                BufferedReader bufferedReader   = new BufferedReader(fileReader);

                String lineReference;
                String valueName;
                int Row = 0;
                int Col = 0;
                int mines = 0;
                while ((lineReference = bufferedReader.readLine()) !=null) {

                    String[] fieldData = lineReference.split("=");
                    valueName      = fieldData[0];
                    if (valueName.equals("height")){
                        Row = Integer.parseInt(fieldData[1]);
                    }
                    if (valueName.equals("width")){
                        Col = Integer.parseInt(fieldData[1]);
                    }
                    if (valueName.equals("mines")){
                        mines = Integer.parseInt(fieldData[1]);
                    }

                }
               System.out.println("Height: " + Row + " Width: " + Col + " Mines: " + mines);

                array[0] = Row;
                array[1] = Col;
                array[2] = mines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    /**
     * Метод задаващ стойностите на началото и финала на играта
     *
     * @param array съсдържа границите на полето
     * @return масив, съсдържащ кординатите на старта и финала
     */
    public static int[] holderForSAndF(int[] array){
        int[] cordinateHolder = new int [4];
        int Row = array[0];
        int Col = array[1];

        Random rand = new Random();
        int startCol = rand.nextInt(Col);
        int startRow = rand.nextInt(Row);

        // За старта и финала знам че не трябва да са статични стойности(1 и 2)
        // защото няма да мога да работя с други стойности освен предварително предвинетите но...

        while (startRow == 1 || startRow == 2) {
            startRow = rand.nextInt(Row);
        }

        int finishCol = rand.nextInt(Col);
        int finishRow = rand.nextInt(Row);

        while (finishCol == startCol){
            finishCol = rand.nextInt(Col);
        }

        while ((finishRow == 1 || finishRow == 2) || (finishRow == startRow)) {
            finishRow = rand.nextInt(Row);
        }
        cordinateHolder[0] = startRow;
        cordinateHolder[1] = startCol;
        cordinateHolder[2] = finishRow;
        cordinateHolder[3] = finishCol;

        return cordinateHolder;
    }

    /**
     * Метод изграждащ полето
     *
     * @param array           параметрите на полето
     * @param cordinateHolder кординатите на старта и финала
     * @return                двумерен масив, представляващ полето
     */
    public static char[][] field(int[] array, int[] cordinateHolder){
        int Row                = array[0];
        int Col                = array[1];
        int mines              = array[2];
        int deploy             = 0;
        char[][] enemyTeritory = new char[Row][Col];

        while(deploy < mines) {
            int [] minesCordinates = deployingMines(array, cordinateHolder);
                if(enemyTeritory[minesCordinates[0]][minesCordinates[1]] == 0){
                    enemyTeritory[minesCordinates[0]][minesCordinates[1]] = 1;
                    deploy++;
                }
        }
        return enemyTeritory;
    }

    /**
     * Метод за поставяне на мините на полето
     *
     * @param array             параметрите на полето
     * @param cordinateHolder   кординатите на старта и финала
     * @return                  масив съдържащкординати на мина
     */
    public static int[] deployingMines(int[] array, int []cordinateHolder){
        int Row = array[0];
        int Col = array[1];
        int[] minesCordinates = new int [2];

        Random rand = new Random();
        int minesCol = rand.nextInt(Col);
        int minesRow = rand.nextInt(Row);

        while(minesRow == cordinateHolder[0] || minesRow == cordinateHolder[2]) {
            minesRow = rand.nextInt(Row);
        }
        minesCordinates[0] = minesRow;
        while(minesCol == cordinateHolder[1] || minesCol == cordinateHolder[3]) {
            minesCol = rand.nextInt(Col);
        }
        minesCordinates[1] = minesCol;


        return minesCordinates;
    }

    /**
     * Метод определящ стартовата позиция на играча
     *
     * @param cordinateHolder кординати на масива
     * @return                масив съдържащ старртовите кординатите на играча
     */
    public static int[] first(int[] cordinateHolder){

        int[] playerCordinates = new int [2];

            playerCordinates[0] = cordinateHolder[0];
            playerCordinates[1] = cordinateHolder[1];

        return playerCordinates;
    }

    /**
     * Метод, отговарящ за поведението и движението на играча
     *
     * @param array             параметри на полето
     * @param playerCordinates  стартовите кординати на играча
     * @return                  масив, съдържащ зададени от потребителя кординати на потребителя
     */
    public static int[] player(int [] array, int[] playerCordinates) {
        int rowHolder;
        int colHOlder;
        int Row = array[0];
        int Col = array[1];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Въведете реда на който искате да се предвижите");
        int newRow = scanner.nextInt();
        while (newRow > playerCordinates[0] + 1 || newRow < playerCordinates[0] - 1 || newRow > Row || newRow < 0) {
            System.out.println("Въведете валиден ред");
            newRow = scanner.nextInt();
        }
        rowHolder = newRow;
        playerCordinates[0] = rowHolder;

        System.out.println("Въведете колоната на който искате да се предвижите");
        int newCol = scanner.nextInt();
        while (newCol > playerCordinates[1] + 1 || newCol < playerCordinates[1] - 1 || newCol > Col || newCol < 0) {
            System.out.println("Въведете валидна колона");
            newCol = scanner.nextInt();
        }
        colHOlder = newCol;
        playerCordinates[1] = colHOlder;

        return playerCordinates;
    }

    /**
     * Метод отговарящ за отпечатването на полето и елементите по него
     *
     * @param array             параметри на полето
     * @param cordinateHolder   кординати на старта и финала
     * @param playerCordinates  кординати на играча
     */
    public static void visual(int[] array, int[] cordinateHolder, int[] playerCordinates){
        char[][] enemyTeritory = field(array, cordinateHolder);
        int startRow           = cordinateHolder[0];
        int startCol           = cordinateHolder[1];
        int finishRow          = cordinateHolder[2];
        int finishCol          = cordinateHolder[3];
        
        for (int i = 0; i < array[0]; i++) {
            for (int j = 0; j < array[1]; j++) {
                enemyTeritory[playerCordinates[0]][playerCordinates[1]] = '*';
            }
        }

        for(int i=0; i<array[0]; i++) {
            for(int j=0; j<array[1]; j++) {
                enemyTeritory[startRow][startCol] = 'S';
            }
        }

        for(int i=0; i<array[0]; i++) {
            for(int j=0; j<array[1]; j++) {
                enemyTeritory[finishRow][finishCol] = 'F';
            }
        }


        for(int i=0; i<array[0]; i++) {
            for(int j=0; j<array[1]; j++) {
                if(enemyTeritory[i][j] == 0)
                    enemyTeritory[i][j] = 'X';
            }
        }

        for(int i=0; i<array[0]; i++) {
            for(int j=0; j<array[1]; j++) {
                if(enemyTeritory[i][j] == 1){
                    enemyTeritory[i][j] = '&';
                }
            }
        }

        for(int i=0; i<array[0]; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < array[1]; j++) {
                System.out.print(enemyTeritory[i][j] + " ");
            }

            System.out.println();
        }
        System.out.print(" ");

        for (int j = 0; j < array[1]; j++) {
            System.out.print(" " + j);
        }
        System.out.println();
    }

    /**
     * Метод обединяващ останалите и за изпълнение
     */
    public static void execute(){

            int[] array = table();
            int [] cordinateHolder = holderForSAndF(array);
            int [] playerCordinates = new int [2];
            playerCordinates [0] = cordinateHolder[0];
            playerCordinates [1] = cordinateHolder[1];
            int [] minesCordinates = deployingMines(array, cordinateHolder);

            visual(array, cordinateHolder, playerCordinates);
            playerCordinates = player(array, first(cordinateHolder));

            while(true){

                visual(array, cordinateHolder, playerCordinates);
                player(array, playerCordinates);

                if(gameOver(cordinateHolder, playerCordinates, minesCordinates)) {
                    break;
                }
            }
        }

    /**
     * Метод прекратяващ играта при достигане на финал или настъпване на мина
     * 
     * @param cordinateHolder   Съдържа кординати на старт и финал
     * @param playerCordinates  Съдържа кординати на играч
     * @param minesCordinates   Съдържа кординати на мините
     * @return                  Решемие дали програмата ще бъде прекъсната
     */
    public static boolean gameOver(int[] cordinateHolder, int[] playerCordinates, int[] minesCordinates) {
        boolean gameOver = false;
        int playerRow = playerCordinates[0];
        int playerCol = playerCordinates[1];
        int finishRow = cordinateHolder[2];
        int finishCol = cordinateHolder[3];

        if (playerCol == minesCordinates[0] || playerRow == minesCordinates[1]){
            System.out.println("YOU DEAD");
            gameOver = true;
        }

        if (playerCol == finishCol && playerRow == finishRow){
            System.out.println("ПОЗДРАВЛЕНИЯ!!!");
            gameOver = true;
        }
        return gameOver;
    }
}
