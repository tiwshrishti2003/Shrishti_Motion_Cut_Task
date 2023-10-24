package com.gzm;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Util {
    private static final String[] fnames = {
            "chamber.csv","asura.csv","artifact.csv"
    };
    private static final String fpath = "./src/main/java/com/gzm/";

    private static boolean check_fname_valid(String fname)
    {
        boolean flag = true;
        for (String fnx: Util.fnames)
        {
            if (fnx.equalsIgnoreCase(fname))
            {
                return flag;
            }
        }
        return false;
    }
    public static List<String> readFromFile(String fname) throws IOException {
        if (!Util.check_fname_valid(fname))
        {
            return null;
        }
        List<String> lst_lines = new ArrayList<>();
        FileInputStream fileStream = new FileInputStream(new File(fpath + fname));
        InputStreamReader reader = new InputStreamReader(fileStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        //System.out.println("\n3. Start Reading file using BufferedReader");
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            lst_lines.add(line);
        }
        //System.out.println("4. End Reading file using BufferedReader");
        return lst_lines;
    }
    public static List<String> shuffle_data(List<String> x)
    {
        Collections.shuffle(x);
        return x;
    }
    public static List<String> shuffle_data_ignore_first_last_endpoints(List<String> x)
    {
        List<String> y = new ArrayList<>();
        for (int i = 1;i< x.size() - 1;i++)
        {
            y.add(x.get(i));
        }
        Collections.shuffle(y);
        y.add(0,x.get(0));
        y.add(x.get(
                        (x.size()-1)
                )
        );
        return y;
    }
    public static List<String> shuffle_data_ignore_endpoints(List<String> x)
    {
        List<String> y = new ArrayList<>();
        for (int i = 0;i< x.size() - 1;i++)
        {
            y.add(x.get(i));
        }
        Collections.shuffle(y);
        y.add(x.get(
                        (x.size()-1)
                )
        );
        return y;
    }
}
