package lk.edu.yogurtproduction.yogurtproductionitsolution.dto;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PckingDto {

    private String Pac_ID;
    private String Prod_ID;
    private String Packing_Type;
    private String Packing_Description;
    private String Packing_Date;
    private String Expire_Date;
    private double qty;
    private  String Emp_ID;


}
