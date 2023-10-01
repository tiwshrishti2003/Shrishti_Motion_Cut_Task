package com.calx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnPrc implements ActionListener {
    //Class BtnPrc that implements ActionListener Interface

    private Calcx cx;

    public BtnPrc(Calcx cx) {
        this.cx = cx;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn_lst[] = this.cx.get_btn_lst();
        for (int i = 0; i < btn_lst.length; i++) {
            if (i <= 3) {
                //Check for specific button click and according carry out the Math operation
                if (e.getActionCommand().equals(btn_lst[i])) {
                    cx.perf_opar(i);
                }
            } else if (i == 4) {
                if (e.getActionCommand().equals(btn_lst[i])) {

                    cx.clr_scr();
                }
            }
        }
    }
}
