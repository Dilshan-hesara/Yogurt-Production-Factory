package lk.edu.yogurtproduction.yogurtproductionitsolution.dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CashBookTM {

    private String CBNo;
    private String SupId;
    private String date;
    private String desc;
    private int qty;
    private double amount;  // Change to double to support decimal amounts
    private String payMethod;
    private double price;   // Change to double to support decimal prices

}
