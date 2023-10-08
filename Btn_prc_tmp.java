package com.tmpx_temperature;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Btn_prc_tmp implements ActionListener {
    TempConv tcv;
    public Btn_prc_tmp(TempConv tcv)
    {
        this.tcv = tcv;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] btn_strlst = this.tcv.get_btn_strlst();
        for(int i=0;i < btn_strlst.length;i++)
        {
            if(e.getActionCommand().equals(btn_strlst[i]))
            {
                this.tcv.prc_btn_action(btn_strlst[i]);
            }
        }
    }
}
