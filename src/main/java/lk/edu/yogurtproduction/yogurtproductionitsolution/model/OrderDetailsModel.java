package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.OrderDetailsDto;

import java.util.ArrayList;

public class OrderDetailsModel {
    public boolean saveOrderDetailsList(ArrayList<OrderDetailsDto> orderDetailsDTOS){

        for (OrderDetailsDto orderDetailsDTO : orderDetailsDTOS) {
            boolean isOrderDetailsSaved = saveOrderDetail(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                return false;
            }


        }
        return true;
    }

    private boolean saveOrderDetail(OrderDetailsDto orderDetailsDTO) {


        return false;
    }
}
