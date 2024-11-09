package lk.edu.yogurtproduction.yogurtproductionitsolution.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class InventroyDto {

    private String In_ID;
    private String Item_Type;
    private String Item_Description;
    private int Qty;

}
