package com.calx;

import javax.swing.*;
import java.awt.*;

public class Calcx {
    /*
    GUI Calculator Class
     */
    JFrame fm;
    JPanel brd_pn;
    JPanel text_pn;
    JPanel btn_pn;
    JLabel jl[];
    JTextField jt[];
    JButton jb[];
    BtnPrc btnPrc;
    public Calcx() {
        fm = new JFrame();
        jl = new JLabel[3];
        jt = new JTextField[3];
        jb = new JButton[5];

    }
    public String[] get_btn_lst(){
        //Get Button Names
        String[] btn_lst = {"Add", "Sub", "Multi", "Div", "Clr"};
        return btn_lst;
    }
    public JPanel crt_border_panel(){
        //Create Base Panel
        brd_pn = new JPanel();
        brd_pn.setLayout(new BorderLayout());
        //Add Text Panel
        brd_pn.add(this.crt_text_panel(),BorderLayout.CENTER);
        //Add Button Panel
        brd_pn.add(this.crt_button_panel(), BorderLayout.SOUTH);
        return brd_pn;
    }

    public JPanel crt_text_panel(){
        text_pn = new JPanel();
        text_pn.setLayout(new GridLayout(3,2));
        //text_pn.add(new JLabel("demo"));
        this.crt_text_fld();
        return text_pn;
    }
    public void crt_text_fld(){
        String[] lbl = {"Num1:", "Num2:", "Res:"};
        System.out.println("addr at start: " + this.jt[0]);

        for(int i = 0; i < 3; i++){
            this.jl[i] = new JLabel(lbl[i]);
            this.jt[i] = new JTextField("");
            this.set_txt_fnt_and_align(this.jt[i]);
            this.set_lbl_fnt_and_align(this.jl[i]);
            //System.out.println("addr crt_text_fld : " + this.jt[i]);

            this.text_pn.add(this.jl[i]);
            this.text_pn.add(this.jt[i]);
        }
    }
    private void set_txt_fnt_and_align(JTextField jtx){
        jtx.setHorizontalAlignment(JTextField.RIGHT);
        jtx.setFont(new Font("Times Roman", Font.BOLD, 30));

    }
    private void set_lbl_fnt_and_align(JLabel jbx){
        jbx.setHorizontalAlignment(JTextField.CENTER);
        jbx.setFont(new Font("Times Roman", Font.BOLD, 30));
    }
    public JPanel crt_button_panel(){
        btn_pn = new JPanel();
        btn_pn.setLayout(new GridLayout(0,5));
        this.crt_btn();
        //btn_pn.add(new JButton("click"));

        return btn_pn;
    }
    public void crt_btn(){
        String[] btn_lst = this.get_btn_lst();

        this.btnPrc = new BtnPrc(this);

        for(int i = 0; i < btn_lst.length; i++ ){
            this.jb[i] = new JButton(btn_lst[i]);

            //Add ActionListener for Button CLicks
            this.jb[i].addActionListener(this.btnPrc);
            this.btn_pn.add(this.jb[i]);
        }

    }

    public void perf_opar(int i){
        //Carry out Math operation and set the UI text Field
        //System.out.println(this.jt[0].getText());
        //System.out.println(this.jt[1].getText());
        double f1 = 0.0;
        double f2 = 0.0;
        double rslt = 0.0;
        boolean flag = true;
        try
        {
            if(
                    this.jt[0].getText().length() == 0 ||
                            this.jt[1].getText().length() == 0
            )
            {
                throw new InvalidNbr("Invalid number entered");
            }
            f1 = Double.parseDouble(this.jt[0].getText());
            f2 = Double.parseDouble(this.jt[1].getText());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            flag = false;
        }
        if(!flag)
        {
            return;
        }
        flag = true;
        switch (i){
            case 0:
                rslt = f1 + f2;
                break;
            case 1:
                rslt = f1 - f2;
                break;
            case 2:
                rslt = f1 * f2;
                break;
            case 3:
                if(f2 < 1e-05){
                    flag = false;
                    this.jt[2].setText("Divide by zero");
                    break;
                }
                rslt = f1 / f2;
                break;
            default:
                System.out.println("Invalid choice");

        }
        if(!flag){
            return;
        }
        String reslt_fmt = String.format("%12.3f", rslt);
        this.jt[2].setText(reslt_fmt);
        //System.out.println(f1+f2);
    }
    public void clr_scr(){
        //Clear Fields
        this.jt[0].setText("");
        this.jt[1].setText("");
        this.jt[2].setText("");

    }
    public void start_frame(){
        //Frame setup and add base panel and set visibility
        fm.setTitle("Simple Calculator");
        fm.setSize(600, 400);
        fm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fm.add(
                this.crt_border_panel()
        );

        fm.setVisible(true);
    }
}
