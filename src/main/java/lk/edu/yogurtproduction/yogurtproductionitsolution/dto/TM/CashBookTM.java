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
    private String qty;
    private String amount;
    private String payMethod;


}
