import java.io.*;
import java.net.StandardSocketOptions;

public class KnapsackBU {
    private static int[] wArray;
    private static int[] vArray;
    private static File wFile;
    private static File vFile;
    private static int lineCount;

    public static void main(String[] args) {
        int numItems = 0;
        int maxWeight = 0;
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

        try {
            compareFiles(wFile, vFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //print out array
        for (int i = 0; i < lineCount; i++) {
            System.out.print(wArray[i] + " ");

        }
        System.out.println("");
        for (int i = 0; i < lineCount; i++) {
            System.out.print(vArray[i] + " ");
        }
        System.out.println(" ");

        sackBU(numItems, wArray, vArray, maxWeight);
        System.out.println("********done**********");

    }
    private static int[][] sackBU(int n, int[] w, int[]v, int maxSize){


        int[][] temp= new int [n+1][maxSize+1];



        for(int i=1; i<=n;i++){
            for(int c =1; c<=maxSize; c++){
                if(w[i-1] >c ){
                    temp[i][c] = temp[i-1][c];
                }else {
                    temp[i][c] = Math.max(temp[i-1][c],v[i-1]+temp[i-1][c-w[i-1]]);
                }
            }
        }
        for(int i = 0; i < temp.length; i++){
            for(int j = 0; j < temp[0].length;j++){
                System.out.print(temp[i][j]+" ");
            }
            System.out.println();
        }
        return temp;
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

        try (BufferedReader br = new BufferedReader(new FileReader(x))) {
            String line;
            while ((line = br.readLine()) != null) {
                countX++;
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(y))) {
            String line;
            while ((line = br.readLine()) != null) {
                countY++;
            }
        }
        if (countX != countY) {
            System.out.println("Files do not contain equal amounts of weight and value items");
            System.out.println("File " + x + "lines: " + countX + "File " + y + "lines: " + countY);
            System.exit(1);
        } else {
            lineCount = countX;
            if(wArray.length != lineCount){
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
}