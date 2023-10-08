package com.tmpx_temperature;

//import com.snx.Emp;

import javax.swing.*;
import java.awt.*;

public class TempConv {

    /*
    Task: Temperature Converter
Objective: Build a program in Java that converts temperatures between Celsius and Fahrenheit.
Details: This task will enhance your understanding of basic mathematical operations and user input in Java. Your program should have the following features:
1. It should allow users to input a temperature value and specify whether it's in Celsius or Fahrenheit.
2. Depending on the user's input, your program should convert the temperature to the other unit and display the result.
3. The conversion formulas are as follows:
      - Celsius to Fahrenheit: (Celsius * 9/5) + 32
      - Fahrenheit to Celsius: (Fahrenheit - 32) * 5/9
4. Ensure your program handles input validation (e.g., ensuring the temperature value is valid, and the unit is specified correctly).
5. Provide a user-friendly interface with clear instructions.

Submission Instructions:
- Please submit your completed Temperature Converter program by 8th, October 2023.
- Make sure your code is well-documented, and you follow best practices for Java programming.

This task is designed to reinforce your programming skills and problem-solving abilities. As with previous tasks, there won't be formal training sessions, so use online resources and Java documentation to your advantage.
     */
    JFrame jf;
    JPanel jp_base, jp_btn, jp_txt, jp_msg;
    JLabel[] jl_tmp;
    JTextField[] jt_tmp;
    JLabel jl_msg;
    JButton[] jb_conv;
    Btn_prc_tmp btn_act;
    public TempConv()
    {
        this.jf = new JFrame("Temperature Converter");
        this.jt_tmp = new JTextField[2];
        this.jl_tmp = new JLabel[2];
        this.jb_conv = new JButton[2];
        this.btn_act = new Btn_prc_tmp(this);
    }
    public void set_txt_font(JTextField jtxt)
    {
        jtxt.setFont(new Font("Times Roman",Font.BOLD,18));
        jtxt.setHorizontalAlignment(JTextField.RIGHT);
    }
    public void set_lbl_font(JLabel jlbl)
    {
        jlbl.setFont(new Font("Times Roman",Font.BOLD,18));
        jlbl.setHorizontalAlignment(JLabel.LEFT);
    }
    public void crt_lbl_txt()
    {
        this.jl_tmp[0] = new JLabel("Celsius To Fahrenheit");
        this.jl_tmp[1] = new JLabel("Fahrenheit to Celsius");
        this.jt_tmp[0] = new JTextField("");
        this.jt_tmp[1] = new JTextField("");
        for (int i=0;i < this.jl_tmp.length;i++)
        {
            this.set_lbl_font(this.jl_tmp[i]);
            this.set_txt_font(this.jt_tmp[i]);
            this.jp_txt.add(this.jl_tmp[i]);
            this.jp_txt.add(this.jt_tmp[i]);
        }
    }
    public void crt_pn_txt() {
        this.jp_txt = new JPanel();
        this.jp_txt.setLayout(new GridLayout(2,2));
        this.crt_lbl_txt();
    }
    public void crt_pn_msg() {
        this.jp_msg = new JPanel();
        this.jp_msg.setLayout(new GridLayout(1,1));
        this.jl_msg = new JLabel(".");
        this.jp_msg.add(this.jl_msg);
        this.set_lbl_font(this.jl_msg);
    }
    public String[] get_btn_strlst()
    {
        String[] btn_strlst= {"Cel_to_Fah","Fah_to_Cel"};
        return btn_strlst;
    }
    public void crt_pn_btn() {
        this.jp_btn = new JPanel();
        this.jp_btn.setLayout(new GridLayout(1,2));
        for (int i=0;i < this.jb_conv.length;i++)
        {
            this.jb_conv[i] = new JButton(this.get_btn_strlst()[i]);
            this.jb_conv[i].addActionListener(this.btn_act);
            this.jp_btn.add(jb_conv[i]);
        }
    }
    public void crt_pn_base()
    {
        this.jp_base = new JPanel();
        this.jp_base.setLayout(new BorderLayout());
        this.crt_pn_txt();
        this.crt_pn_btn();
        this.crt_pn_msg();
        this.jp_base.add(this.jp_txt,BorderLayout.CENTER);
        this.jp_base.add(this.jp_btn,BorderLayout.NORTH);
        this.jp_base.add(this.jp_msg,BorderLayout.SOUTH);
    }
    public void strt_frm()
    {
        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setSize(600, 250);
        this.crt_pn_base();
        this.jf.add(this.jp_base);
        this.jf.setVisible(true);
    }
    public boolean validate_number(JTextField jtxt)
    {
        double valx = 0.0;
        boolean flag = true;
        try
        {
            valx = Double.parseDouble(jtxt.getText());
        }
        catch(Exception ex)
        {
            System.out.println("Invalid number entered");
            //ex.printStackTrace();
            flag = false;
        }
        return flag;
    }
    public void clr_fields(JTextField jtxt)
    {
        jtxt.setText("");
    }
    public void clr_lbl_fields(JLabel jlbl)
    {
        jlbl.setText(".");
    }
    public void prc_btn_action(String oper)
    {
        this.clr_lbl_fields(this.jl_msg);
        String[] btn_strlst = this.get_btn_strlst();
        double val_in = 0.0;
        double val_out = 0.0;
        String val_fmt = "";
        if (oper.equals(btn_strlst[0]))
        {
            this.clr_fields(this.jt_tmp[1]);
            if (this.validate_number(this.jt_tmp[0]))
            {
                val_in = Double.parseDouble(this.jt_tmp[0].getText());
                val_out = (val_in * 9.0/5.0) + 32.0;
                val_fmt = String.format("%7.2f",val_out);
                this.jt_tmp[1].setText(val_fmt);
            }
            else
            {
                this.jl_msg.setText("Enter numeric value in Celsius to Fahernheit Textfield");
            }
        }
        else if (oper.equals(btn_strlst[1]))
        {
            this.clr_fields(this.jt_tmp[0]);
            if (this.validate_number(this.jt_tmp[1]))
            {
                val_in = Double.parseDouble(this.jt_tmp[1].getText());
                val_out = (val_in - 32.0) * 5.0/9.0;
                val_fmt = String.format("%7.2f",val_out);
                this.jt_tmp[0].setText(val_fmt);
            }
            else
            {
                this.jl_msg.setText("Enter numeric value in Fahernheit to Celsius Textfield");
            }
        }
        else
        {
            System.out.println("Invalid operation requested");
        }
    }
}
