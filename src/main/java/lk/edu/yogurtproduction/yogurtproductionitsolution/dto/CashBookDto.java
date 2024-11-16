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
    private String matID;
    private String inID;
    private String desc;
    private int qty;
    private double amount;
    private String date;


}
