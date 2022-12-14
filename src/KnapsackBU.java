import java.io.*;
import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

public class KnapsackBU {
    private static int[] wArray;
    private static int[] vArray;
    private static File wFile;
    private static File vFile;
    private static int lineCount;
    private static int maxWeight;

    private static int optVal;
    private static int ref;

    private static int [][] table;
    private static int [][] dTable;
    private static ArrayList<Integer> list= new ArrayList<>();

    public static void main(String[] args) {
        int numItems = 0;
        maxWeight = 0;
        String weight = null;
        String value = null;
        int debugLevel = 0;


        if (args.length > 3 && args.length < 6) {
            numItems = Integer.parseInt(args[0]);
            maxWeight = Integer.parseInt(args[1]);
            weight = args[2];
            value = args[3];
            if (args.length == 5) {
                if (args[4].equals("1")) {
                    debugLevel = 1;
                } else if (args[4].equals("0")) {
                    debugLevel = 0;
                } else {
                    System.out.println("Invalid debug input");
                    Usage();
                    System.exit(1);
                }
            }
        } else {
            Usage();
            System.exit(1);
        }
        System.out.println("Num items: " + numItems + " Max weight: " + maxWeight + " Weight file: " + weight
                + " Value file: " + value + " Debug option: " + debugLevel + "\n");

        wArray = new int[numItems];
        vArray = new int[numItems];
        int lines;

        wFile = new File(weight);
        vFile = new File(value);
        String v= "KnapsackBU-VTable.txt";
        String d= "KnapsackBU-DTable.txt";


        try {
            compareFiles(wFile, vFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        table = sackBU(numItems, wArray, vArray, maxWeight);
        optVal = table[table.length - 1][table[table.length-1].length - 1];
        dTable = sackFind(numItems, wArray, vArray, maxWeight,table);

        if(debugLevel == 1){
            writeToFile(v,table);
            System.out.println("KnapsackDP-VTable:" + "");
            for(int i = 0; i < table.length; i++){
                for(int j = 0; j < table[0].length;j++){
                    System.out.print(table[i][j]+" ");
                }
                System.out.println();
            }
            writeToFile(d,dTable);
            System.out.println("\n" + "KnapsackDP-DTable:" +"");
            //DTable
            for(int x = 0; x < dTable.length; x++){
                for(int y = 0; y< dTable[0].length;y++){
                    System.out.print(dTable[x][y]+" ");
                }
                System.out.println();
            }

        }

        System.out.println("Optimal solution:" + "\n" + list);
        System.out.println("Total Weight: "+ maxWeight);
        System.out.println("Optimal Value: " + optVal);
        System.out.println("Number of table references: " + ref);



    }
    private static int[][] sackBU(int n, int[] w, int[]v, int maxSize){
        int[][] temp= new int [n+1][maxSize+1];
        for(int i=1; i<=n;i++){

            for(int c =1; c<=maxSize; c++){
                if(w[i-1] >c ){
                    ref++;
                    temp[i][c] = temp[i-1][c];

                }else {
                    ref+=2;
                    temp[i][c] = Math.max(temp[i-1][c],v[i-1]+temp[i-1][c-w[i-1]]);

                }
            }
        }
        return temp;
    }

    private static int[][] sackFind(int n, int[] w, int[]v, int maxSize, int[][] arr){
         int[][] findArr =  new int [n+1][maxSize+1];
        int i = n;
        int j = maxSize;
        while(i >0 && j > 0 ){
            if(arr[i][j] != arr[i-1][j]){
                findArr[i][j] = 1;
                list.add(i);
                i = i-1;
                    j=j-w[i];
            }
            else {
                i=i-1;
            }
        }
        Collections.reverse(list);
        return findArr;
    }



    private static void Usage() {
        System.out.println("\n** Usage : java KnapsackBU <n> <W> <w.txt> <v.txt> [<debug level>] **");
        System.out.println("n: Number of items \n" + "W: Max weight for knapsack\n"
                + "w.text: File containing itme's weight\n" + "v.txt: File containing item's value\n"
                + "[<debug level>]: 0 - default, 1 - print to files\n"
        );
    }
    private static void compareFiles(File x, File y) throws IOException {
        BufferedReader br1;
        BufferedReader br2;
        int countX = 0;
        int countY = 0;
        lineCount = 0;

        countX = fileLineCount(x);
        countY = fileLineCount(y);

        if (countX != countY) {
            System.out.println("Files do not contain equal amounts of weight and value items");
            System.out.println("File " + x + "lines: " + countX + "File " + y + "lines: " + countY);
            System.exit(1);
        } else {
            lineCount = countX;
            if (wArray.length != lineCount) {
                System.out.println("The number of items entered  does not match number of items in files");
                System.out.println("You enterd n: " + wArray.length + ", number of items in file: " + lineCount);
                System.exit(1);
            }
            br1 = new BufferedReader(new FileReader(wFile));
            br2 = new BufferedReader(new FileReader(vFile));
            for (int i = 0; i < lineCount; i++) {
                wArray[i] = Integer.parseInt(br1.readLine());
                vArray[i] = Integer.parseInt(br2.readLine());
            }
        }
    }
    private static int fileLineCount(File x){
        int c=0;
        try (BufferedReader br = new BufferedReader(new FileReader(x))) {
            String line;
            while ((line = br.readLine()) != null) {
                c++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return c;
    }
    private static void writeToFile(String fileName, int [][] arr){
        PrintWriter outPut;
        try {
            outPut = new PrintWriter(fileName);
            outPut.println("KnapsackDP-VTable:" + "\n");
            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[0].length;j++){
                    outPut.print(arr[i][j]+" ");
                }
                    outPut.println(" ");
            }
            outPut.println("\n" + "Optimal solution:" + "\n" + list);
            outPut.println("Total Weight: "+ maxWeight);
            outPut.println("Optimal Value: " + optVal);
            outPut.println("Number of table references: " + ref +"\n");
            outPut.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}