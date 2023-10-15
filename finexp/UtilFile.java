package com.finexp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilFile {
    private static UtilFile utilx;
    //public static final String EXP_FILE = "./src/main/java/com/finexp/expense_file.txt";

    private static final String EXP_FILE = "./expense_file.txt";
    private UtilFile(){
    }

    public static UtilFile get_file_instance() {
        //Singleton object
        if(UtilFile.utilx == null){
            UtilFile.utilx = new UtilFile();
        }
        return UtilFile.utilx;
    }

    public boolean check_file(){
        File fn = new File(EXP_FILE);
        if(fn.isFile()){
            return true;
        }
        return false;
    }

    public List<String> readFromfile() throws IOException{
        List<String> lst_lines = new ArrayList<>();
        FileInputStream fileStream = new FileInputStream(new File(EXP_FILE));
        InputStreamReader reader = new InputStreamReader(fileStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        System.out.println("Start reading file");
        String line;
        while((line=bufferedReader.readLine())!=null){
            lst_lines.add(line);
        }
        System.out.println("File Read");
        return lst_lines;
    }
    public void writeToFile(List<String> lst_lines) throws IOException{
        FileWriter file = new FileWriter(EXP_FILE);
        BufferedWriter output = new BufferedWriter(file);
        for(String data:lst_lines){
            output.write(data+"\n");
        }
        output.flush();
        System.out.println("Data flushed to file");
        output.close();
    }
}
