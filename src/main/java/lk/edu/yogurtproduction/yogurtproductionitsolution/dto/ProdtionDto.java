package lk.edu.yogurtproduction.yogurtproductionitsolution.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProdtionDto {
    private String Prod_ID;
    private String Pro_Name;
    private double Prod_Qty;
    private String Prod_Name;
    private int P_milk;
    private int p_suguer;
    private int p_jeley;

    public ProdtionDto(String prodID, String proName, double qty, String prodName) {
        this.Prod_ID = prodID;
        this.Pro_Name = proName;
        this.Prod_Qty = qty;
        this.Prod_Name = prodName;
    }

}
