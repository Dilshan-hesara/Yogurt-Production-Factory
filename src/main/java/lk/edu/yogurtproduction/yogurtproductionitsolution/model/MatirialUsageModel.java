package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.MatirialUsageDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatirialUsageModel {
    public boolean saveMatUage(ArrayList<MatirialUsageDto> matirialUsageDTOS) throws SQLException {

        for (MatirialUsageDto matirialUsageDTO : matirialUsageDTOS) {
            boolean isSaved = saveMatUsage(matirialUsageDTO);

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

    private boolean saveMatUsage(MatirialUsageDto matirialUsageDTO) throws SQLException {

        return   CrudUtil.execute(
                "INSERT INTO material_usage VALUES (?, ?, ?, ?, ?)",
                matirialUsageDTO.getMatUs_ID(),
                matirialUsageDTO.getProd_ID(),
                matirialUsageDTO.getMat_Milk(),
                matirialUsageDTO.getMat_Suguer(),
                matirialUsageDTO.getMat_Gelatin()

        );

    }
    public String getmatirialUsageId() throws SQLException {

        ResultSet rst = CrudUtil.execute("select MatUs_ID from material_usage order by MatUs_ID desc limit 1");
        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(4);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("MATU%03d", newIdIndex);
        }
        return "MATU001";
        }
//


//    public boolean saveMatUpdated(ProdtionDto prodtionDto) throws SQLException {
//        return   CrudUtil.execute(
//                "INSERT INTO material_usage VALUES (?, ?, ?, ?)",
//           //     prodtionDto.getMatUid(),
//                prodtionDto.getProd_ID(),
//                prodtionDto.getPro_Name(),
//                prodtionDto.getProd_Qty()
//                prodtionDto.getProd_ID()
//
//        );


    }
//}
