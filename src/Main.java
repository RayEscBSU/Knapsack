import jdk.jfr.StackTrace;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        int numItems;
        int maxWeight;
        String weight = null;
        String value= null;
        int debugLevel =0;

        if (args.length >0){
            try{
                 numItems = Integer.parseInt(args[0]);
                 maxWeight = Integer.parseInt(args[1]);
                 weight= args[2];
                 value= args[3];
                 if (args[4].equals("1")){debugLevel = 1;}
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e);
            }
        }
        try{
            File wFile= new File (weight);
            File vFile = new File (value);
        } catch (NullPointerException e) {
            System.out.print("NullPointerException Caught");
        }


    }
}