package lk.edu.yogurtproduction.yogurtproductionitsolution.model;
import java.sql.SQLException;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.InventroyDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.PckingDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdtionDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

public class InventroyModel {

    public String getInventroyId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select In_ID from inventory order by In_ID desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("INV%03d", newIdIndex);
        }
        return "INV001";
    }


    public boolean redusqtyOnInventroy(ProdtionDto prodtionDto) throws SQLException {


        boolean milkUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Milk' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_milk());
        boolean gelatinUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Gelat' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_jeley());
        boolean sugarUpdated = CrudUtil.execute("UPDATE inventory i JOIN (SELECT In_ID FROM inventory WHERE Item_Description = 'Sugar' AND Qty > 0 LIMIT 1) subquery ON i.In_ID = subquery.In_ID SET i.Qty = i.Qty - ?", prodtionDto.getP_suguer());

        if (milkUpdated && gelatinUpdated && sugarUpdated) {
            return true;
        } else {
            return false;
        }
    }


    public boolean saveInvetoryPack(PckingDto pckingDtos) throws SQLException {

        return   CrudUtil.execute(
                "INSERT INTO Inventory VALUES (?, ?, ?, ?, ?)",
                pckingDtos.getInID(),
                pckingDtos.getItemType(),
                pckingDtos.getPac_Desc(),
                pckingDtos.getQty(),
                pckingDtos.getProd_ID()

        );
    }

    public boolean saveUpdated(ProdtionDto prodtionDto) throws SQLException {


        return   CrudUtil.execute(
                "INSERT INTO Inventory VALUES (?, ?, ?, ?, ?)",
                prodtionDto.getInID(),
                prodtionDto.getItemType(),
                prodtionDto.getPro_Name(),
                prodtionDto.getProd_Qty(),
                prodtionDto.getProd_ID()

        );    }

    public boolean saveredusPackedQty(PckingDto pckingDtos) throws SQLException {

        return   CrudUtil.execute(
                "UPDATE inventory set Qty = Qty - ? where Prod_ID = ?",
                pckingDtos.getRedusQty(),
                pckingDtos.getProd_ID()

        );

    }

    public int AvQtyFromSelectProd_ID(String getSelectedProdId) throws SQLException {

        ResultSet rst = CrudUtil.execute(
                "SELECT Qty FROM inventory WHERE Prod_ID = ?",
                getSelectedProdId
        );

        if (rst.next()) {

            return rst.getInt("Qty");
        } else {
            return 0;
        }
    }


    public boolean saveInvetory(ArrayList<InventroyDto> inventroyDTOS) throws SQLException {
        for (InventroyDto inventroyDTO : inventroyDTOS) {
            boolean isSaved = savedInventory(inventroyDTO);

            if (!isSaved) {
                return false;
            }
        }
//
//        boolean isMatirealUpdated = materialModel.updatedMatirialReduceQty(cashBookDto);
//        if (isMatirealUpdated) {
//
//      return false;
//
//
//        }
        return true;
    }

    private boolean savedInventory(InventroyDto inventroyDTO) throws SQLException {

        return CrudUtil.execute(
                "insert into inventory  values (?,?,?,?,?)",
                inventroyDTO.getId(),
                inventroyDTO.getItemType(),
                inventroyDTO.getItemDescription(),
                inventroyDTO.getQty(),
                inventroyDTO.getProdId()
        );
    }

    public ArrayList<InventroyDto> getAllInventoryData() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from inventory");

        ArrayList<InventroyDto> inventroyDTOS = new ArrayList<>();

        while (rst.next()) {
            InventroyDto inventroyDTO = new InventroyDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            inventroyDTOS.add(inventroyDTO);
        }
        return inventroyDTOS;


    }

    /*  // Iterate through each order detail in the list
        for (OrderDetailsDto orderDetailsDTO : orderDetailsDTOS) {
            // @isOrderDetailsSaved: Saves the individual order detail
            boolean isOrderDetailsSaved = saveOrderDetail(orderDetailsDTO);
            if (!isOrderDetailsSaved) {
                // Return false if saving any order detail fails
                return false;
            }

            // @isItemUpdated: Updates the item quantity in the stock for the corresponding order detail
            boolean isItemUpdated = itemModel.reduceQuantity(orderDetailsDTO);
            if (!isItemUpdated) {
                // Return false if updating the item quantity fails
                return false;
            }
        }
        // Return true if all order details are saved and item quantities updated successfully
        return true;
    }
    private boolean saveOrderDetail(OrderDetailsDto orderDetailsDTO) throws SQLException {
        // Executes an insert query to save the order detail into the database
        return CrudUtil.execute(
                "insert into orderdetails values (?,?,?,?)",
                orderDetailsDTO.getOrderId(),
                orderDetailsDTO.getItemId(),
                orderDetailsDTO.getQuantity(),
                orderDetailsDTO.getPrice()
        );
    }*/
}



