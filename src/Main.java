import jdk.jfr.StackTrace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        wList = new ArrayList<>(numItems);
        vList= new ArrayList<>(numItems);

        try{
            File wFile= new File (weight);
            File vFile = new File (value);
            br = new BufferedReader(new FileReader(wFile));

        } catch (IOException e) {
            System.out.print("Exception Caught");
            e.printStackTrace();
        }




    }
}