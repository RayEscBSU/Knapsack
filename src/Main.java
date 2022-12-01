import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numItems = 0;
        int maxWeight = 0;
        String weight = null;
        String value= null;
        int debugLevel =0;
        BufferedReader br;
        List<Integer> wList;
        List<Integer> vList;

        if (args.length >3  && args.length <6){
                 numItems = Integer.parseInt(args[0]);
                 maxWeight = Integer.parseInt(args[1]);
                 weight= args[2];
                 value= args[3];
                 if (args.length == 5){
                     if(args[4].equals("1")){debugLevel = 1;}
                     else if(args[4].equals("0")){debugLevel = 0;}
                     else {
                         System.out.println("Invalid debug input");
                         Usage();
                         System.exit(1);
                     }
                 }
        }
        else{
            Usage();
            System.exit(1);
        }
        System.out.print("Num items: " + numItems + " Max weight: " + maxWeight + " Weight file: "+ weight
                + " Value file: " + value + " Debug option: " + debugLevel);

        wList = new ArrayList<>(numItems);
        vList= new ArrayList<>(numItems);
//
//        try{
//            File wFile= new File (weight);
//            File vFile = new File (value);
//            br = new BufferedReader(new FileReader(wFile));
//
//        } catch (IOException e) {
//            System.out.print("Exception Caught");
//            e.printStackTrace();
//        }

    }

    private static void Usage (){
        System.out.println("\n** Usage : java KnapsackBU <n> <W> <w.txt> <v.txt> [<debug level>] **");
        System.out.println("n: Number of items \n" + "W: Max weight for knapsack\n"
                + "w.text: File containing itme's weight\n" + "v.txt: File containing item's value\n"
                + "[<debug level>]: 0 - default, 1 - print to files"
        );
    }
}