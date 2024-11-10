package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.SQLException;

public class ProdMixModel {
    public boolean saveProdtMaix(ProdMixDto prodMixDto) throws SQLException {

        boolean isSaved =  CrudUtil.execute(
                "insert into production_mix_recip values (?,?,?,?)",

                prodMixDto.getProdName(),
                prodMixDto.getMilk(),
                prodMixDto.getSuguer(),
                prodMixDto.getJeliy()
        );
        return isSaved;
    }
}
