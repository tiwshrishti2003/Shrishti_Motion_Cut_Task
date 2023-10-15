package com.uifinexp;

import com.finexp.CatgSummz;
import com.finexp.MgrExpense;
import com.finexp.UserSummz;

import java.util.List;
import java.util.Scanner;

public class Mainfin {
    public static void main(String[] args) {
        Mainfin.prc_ui();
    }

    public void disp_menu(){
        System.out.println("Expense Tracker");
        System.out.println("---------------------");
        String[] optx = {
                "1. Add Expense",
                "2. List User Expense",
                "3. Summarize by Category",
                "4. Summarize by Timeline",
                "5. Save to Expense File",
                "6. Exit"
        };
        for(String x : optx){
            System.out.println(x);
        }
    }

    public void add_exp(){
        MgrExpense mexp = MgrExpense.get_manager_instance();
        int expid = mexp.get_unique_key();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%4d",expid));
        sb.append(",");
        System.out.println("Add expense details");
        Scanner inx = new Scanner(System.in);
        String lst_row = null;
        String dx = null;
        String[] msgs = {
                "Enter User id:",
                "Enter Name:",
                "Enter Category name(Grocery,Transportation,Stationery,Decoration,Clothes):",
                "Enter Description:",
                "Enter Date in (MM/dd/yyyy)",
                "Enter amount:"
        };
        int cnt=0;
        for (String x : msgs){
            System.out.println(x);
            dx = inx.next();
            sb.append(dx);
            if(cnt < msgs.length-1){
                sb.append(',');
            }
            cnt++;
        }
        lst_row = sb.toString();
        System.out.println(lst_row);
        mexp.addExpense(lst_row);
    }

    public void disp_exp(){
        MgrExpense mexp = MgrExpense.get_manager_instance();
        System.out.println("Display user expense");
        String uid = null;
        System.out.println("Please enter user id");
        Scanner inx = new Scanner(System.in);
        uid = inx.next();
        List<String> lst_uid_exp = mexp.viewExpense(uid);
        if(lst_uid_exp.size() == 0){
            System.out.println("No user data available");
            return;
        }
        for (String x : lst_uid_exp){
            System.out.println(x);
        }
    }

    public void summ_catg(){
        MgrExpense mexp = MgrExpense.get_manager_instance();
        System.out.println("Summary expense by Category");
        List<CatgSummz> lst_catg_exp = mexp.summaryExpense_by_category();
        for (CatgSummz x :  lst_catg_exp){
            System.out.println(x.toString());
        }
    }


    public void summ_timeline(){
        MgrExpense mexp = MgrExpense.get_manager_instance();
        System.out.println("Summary expense by Timeline");
        String st_dt = null;
        String ed_dt = null;
        Scanner inx = new Scanner(System.in);
        System.out.println("Please Enter start date in (MM/dd/yyyy):");
        st_dt = inx.next();
        System.out.println("Please Enter end date in (MM/dd/yyyy):");
        ed_dt = inx.next();
        List<UserSummz> lst_timeline_exp = mexp.summaryExpense_by_timeline(st_dt,ed_dt);
        for(UserSummz x : lst_timeline_exp){
            System.out.println(x.toString());
        }
    }

    public void save_exp(){
        MgrExpense mexp = MgrExpense.get_manager_instance();
        System.out.println("Saving data to file");
        mexp.write_lst_lines();
    }
    public void enter_data(){

        Scanner inx = new Scanner(System.in);
        String ival = null;
        int mn_itm = 0;
        boolean flag = true;
        while (flag){
            this.disp_menu();
            System.out.println("Please enter choice (1-6)");
            ival = inx.next();
            try {
                mn_itm = Integer.parseInt(ival);
                System.out.println("Value is: "+ mn_itm);
            }
            catch (ArithmeticException ex) {
                System.out.println("Invalid option please retry");
                flag = false;
            }
            if(!flag){
                continue;
            }
            switch (mn_itm){
                case 1: {
                    this.add_exp();
                    break;
                }
                case 2: {
                    this.disp_exp();
                    break;
                }
                case 3: {
                    this.summ_catg();
                    break;
                }
                case 4: {
                    this.summ_timeline();
                    break;
                }
                case 5: {
                    this.save_exp();
                    break;
                }
                case 6: {
                    System.out.println("Exiting app");
                    System.exit(4);
                    break;
                }
                default:{
                    System.out.println("Invalid option, please retry");
                }
            }
        }
    }
    public static void prc_ui(){
        Mainfin mfn = new Mainfin();
        boolean flag = true;
        while(flag){
            mfn.enter_data();
        }
    }
}
