import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static Scanner scan;
    public static void main(String[] args) {
        int numItems = 0;
        int maxWeight = 0;
        String weight = null;
        String value = null;
        int debugLevel = 0;

        int[] wArray;
        int[] vArray;


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
                + " Value file: " + value + " Debug option: " + debugLevel);

        wArray = new int[numItems];
        vArray = new int[numItems];
        int lines;

        File wFile= new File (weight);
        File vFile = new File (value);

        try {
           lines= compareFiles(wFile,vFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br2;
        try {
           br = new BufferedReader(new FileReader(wFile));
           br2 = new BufferedReader(new FileReader(vFile));
           for (int i =0; i< lines; i++){
               wArray[i]=Integer.parseInt(br.readLine());
               vArray[i]=Integer.parseInt(br2.readLine());
           }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("done");

    }

    private static void Usage() {
        System.out.println("\n** Usage : java KnapsackBU <n> <W> <w.txt> <v.txt> [<debug level>] **");
        System.out.println("n: Number of items \n" + "W: Max weight for knapsack\n"
                + "w.text: File containing itme's weight\n" + "v.txt: File containing item's value\n"
                + "[<debug level>]: 0 - default, 1 - print to files\n"
        );
    }

    private static int compareFiles(File x, File y) throws IOException {
        int countX = 0;
        int countY= 0;
        int count=0;
        scan = new Scanner(x);

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
        if (countX != countY){
            System.out.println("Files do not contain equal amounts of weight and value items");
            System.out.println("File " + x + "lines: " + countX + "File " + y + "lines: " + countY );
            System.exit(1);
        }
        else {
            count = countX;
        }
        return count ;
    }
}