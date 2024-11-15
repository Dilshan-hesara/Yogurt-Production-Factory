package lk.edu.yogurtproduction.yogurtproductionitsolution.dto;


import lombok.*;

import java.sql.Array;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CashBookDto {


    private String CBNo;
    private String SupId;
    private String date;
    private String desc;
    private int qty;
    private double amount;
    private String matID;
    private String inID;
    private String itemType;
    private String Prod_id;


    public CashBookDto(String string, String string1, String string2, String string3, int anInt, double aDouble) {
        this.CBNo = string;
        this.SupId = string1;
        this.date = string2;
        this.desc = string3;
        this.qty = anInt;
        this.amount = aDouble;
        this.matID = string3;
        this.inID = string;
        this.itemType = string;

    }
}
