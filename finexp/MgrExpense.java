package com.finexp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MgrExpense implements InterfaceExpense{
    private static final int NUM_OF_COLUMNS = 7;
    private List<Expense> lst_exp;
    private List<String> lst_lines;
    private UtilFile util;
    private static MgrExpense mgx;
    public static MgrExpense get_manager_instance(){
        //Singleton object
        if(MgrExpense.mgx==null){
            MgrExpense.mgx=new MgrExpense();

        }
        return MgrExpense.mgx;
    }
    private MgrExpense(){
        this.lst_exp = new LinkedList<>();
        this.util = UtilFile.get_file_instance();
        this.bld_lst_lines();
    }

    private void bld_lst_lines(){
        try{
            if(this.util.check_file()){
                this.lst_lines = util.readFromfile();
                this.load_exp_from_lines();
            }
        }
        catch (IOException ex){
            System.out.println("File does not exist");
        }
    }


    private void load_exp_from_lines(){
        String[] lst_row = null;
        Date date = null;
        for(String x:this.lst_lines){
            lst_row = x.split(",");
            try{
                date = new SimpleDateFormat("MM/dd/yyyy").parse(lst_row[5]);
            }
            catch (ParseException ex){
                System.out.println("Date String error " + lst_row[5]);
                date = null;
            }
            this.lst_exp.add(
                    new Expense(
                            Integer.parseInt(lst_row[0].trim()),
                            lst_row[1],
                            lst_row[2],
                            Category.valueOf(lst_row[3]),
                            lst_row[4],
                            date,
                            Float.parseFloat(lst_row[6])
                    )
            );

        }
    }

    @Override
    public void addExpense(String lst_row_str) {
        if(!this.validateExpense(lst_row_str)){
            System.out.println("Data validation failed");
            return;
        }
        System.out.println("Data validation sucdeeded");
        Date date = null;
        String[] lst_row = lst_row_str.split(",");
        try{
            date = new SimpleDateFormat("MM/dd/yyyy").parse(lst_row[5]);
        }
        catch (ParseException ex){

        }
        this.lst_exp.add(
                new Expense(
                        Integer.parseInt(lst_row[0].trim()),
                        lst_row[1],
                        lst_row[2],
                        Category.valueOf(lst_row[3]),
                        lst_row[4],
                        date,
                        Float.parseFloat(lst_row[6])
                )
        );
    }

    @Override
    public List<String> viewExpense(String uid) {
        List<String> lst_uid_exp = new ArrayList<>();
        for(Expense exp:this.lst_exp){
            if(exp.getUid().equalsIgnoreCase(uid)){
                lst_uid_exp.add(exp.toString());
            }
        }
        return lst_uid_exp;
    }

    private List<String> get_unique_uids(){
        Set<String> lst_unq_ids = new TreeSet<>();
        for(Expense exp:this.lst_exp){
            lst_unq_ids.add(exp.getUid());
        }
        List<String> lst_uids = new ArrayList<>();
        Iterator<String> itr = lst_unq_ids.iterator();
        while(itr.hasNext()){
            lst_uids.add(itr.next());
        }
        return lst_uids;
    }

    @Override
    public List<UserSummz> summaryExpense_by_timeline(String str_st_date, String str_ed_date) {
        List<UserSummz> lst_timeline_exp = new ArrayList<>();
        if(!this.valid_date(str_st_date) || !this.valid_date(str_ed_date)){
            System.out.println("Invalid date");
            return null;
        }
        Date date_st = this.get_date_from_str(str_st_date);
        Date date_ed = this.get_date_from_str(str_ed_date);
        if(date_ed.compareTo(date_st)<0){
            System.out.println("End date cannot be smaller than start date "+
                    date_ed + ':' + date_st);
            return null;
        }
        List<String> lst_uids = this.get_unique_uids();
        float total = 0.0f;
        UserSummz usz = null;
        for(String uid : lst_uids){
            total = 0.0f;
            for(Expense exp : this.lst_exp){
                if(
                        exp.getUid().equalsIgnoreCase(uid) &&
                        exp.getDate().compareTo(date_st) >= 0 &&
                        date_ed.compareTo(exp.getDate()) >= 0
                )
                {
                    total += exp.getAmount();
                }

            }
            lst_timeline_exp.add(new UserSummz(uid,total));
        }
        return lst_timeline_exp;
    }
    private Date get_date_from_str(String str_date){
        Date date = null;
        try{
            date = new SimpleDateFormat("MM/dd/yyyy").parse(str_date);
        }
        catch (ParseException ex){
            System.out.println("Date String error " + str_date);
            date = null;
        }
        return date;
    }
    private boolean valid_date(String str_date){
        if(this.get_date_from_str(str_date)==null){
            return false;
        }
        return true;
   }

    private boolean valid_ctg(String str_catg){
        boolean flag = true;
        if(this.get_catg(str_catg) == null)
        {
            flag = false;
            return flag;
        }
        return flag;
   }
    private Category get_catg(String str_catg){

       Category catg = null;
       try{
           catg = Category.valueOf(str_catg);
       }
       catch (IllegalArgumentException ex){
           System.out.println("Invalid category " + str_catg);
       }
       return catg;
   }

    private List<Category> get_unique_ctgs(){
        Set<Category> lst_unq_ctg = new TreeSet<>();
        for(Expense exp:this.lst_exp){
            lst_unq_ctg.add(exp.getCatid());
        }
        List<Category> lst_ctgs = new ArrayList<>();
        Iterator<Category> itr = lst_unq_ctg.iterator();
        while(itr.hasNext()){
            lst_ctgs.add(itr.next());
        }
        return lst_ctgs;
    }
    @Override
    public List<CatgSummz> summaryExpense_by_category() {
        List<CatgSummz> lst_catg_exp = new ArrayList<>();
        List<Category> lst_ctgs = this.get_unique_ctgs();
        float total = 0.0f;
        CatgSummz ctz = null;
        for(Category catg : lst_ctgs){
            total = 0.0f;
            for(Expense exp:this.lst_exp){
                if(exp.getCatid().compareTo(catg)==0){
                    total += exp.getAmount();
                }
            }
            lst_catg_exp.add(new CatgSummz(catg,total));
        }
        return lst_catg_exp;
    }

    @Override
    public boolean validateExpense(String lst_row_str) {
        boolean flag = true;
        String[] lst_row = lst_row_str.split(",");
        if(lst_row.length != MgrExpense.NUM_OF_COLUMNS){
            System.out.println("Data columns not correct");
            flag = false;
        }
        if(!flag){
            return flag;
        }
        try{
            int exp_id = Integer.parseInt(lst_row[0].trim());
        }
        catch (ArithmeticException ex){
            System.out.println("exp id is not a valid integer");
            flag = false;
        }
        if(!flag){
            return flag;
        }
        if(lst_row[1].length()==0
                || lst_row[2].length()==0
                || lst_row[4].length()==0){
            System.out.println("Data field length cannot be empty");
            flag = false;
        }
        if(!flag){
            return flag;
        }
        try{
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(lst_row[5]);
        }
        catch (ParseException ex){
            System.out.println("Date format incorrect");
            flag = false;
        }
        if(!flag){
            return flag;
        }
        Category ctx;
        try{
            ctx = Category.valueOf(lst_row[3]);
        }
        catch (IllegalArgumentException ex){
            System.out.println("Item category not valid");
            flag= false;
        }
        if(!flag){
            return flag;
        }
        float valx;
        try{
            valx = Float.parseFloat(lst_row[6]);
        }
        catch (ArithmeticException ex){
            System.out.println("Amount field invalid");
            flag = false;
        }
        if(!flag){
            return flag;
        }
        return flag;
    }

    private void gen_write_lines(){
        this.lst_lines = new ArrayList<>();
        String lst_row = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dt = null;
        for(Expense exp:this.lst_exp){
            dt = sdf.format(exp.getDate());
            lst_row = String.format("%4d,%s,%s,%s,%s,%s,%12.2f",
                    exp.getExpId(),exp.getUid(),exp.getName(),
                    exp.getCatid(),exp.getDesc(),dt,exp.getAmount());
            this.lst_lines.add(lst_row);
        }
    }

    public void write_lst_lines(){
        System.out.println("Writing object data to string data");
        this.gen_write_lines();
        try{
            System.out.println("Writing String data as csv");
            this.util.writeToFile(this.lst_lines);
        }
        catch (IOException ex){
            System.out.println("Error Writing data");
        }
    }

    public int get_unique_key(){
        int max_id = -1;
        for(Expense exp:this.lst_exp){
            if(max_id < exp.getExpId()){
                max_id = exp.getExpId();
            }
        }
        if(max_id == -1){
            return 1000;
        }
        max_id++;
        return max_id;
    }

}
