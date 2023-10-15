package com.finexp;

import java.util.List;

public interface InterfaceExpense {
    void addExpense(String lst_row_str);
    List<String> viewExpense(String uid);
    List<UserSummz> summaryExpense_by_timeline (String str_st_date,String str_ed_date);
    List<CatgSummz> summaryExpense_by_category();
    boolean validateExpense(String lst_row_str);
}
