package lk.edu.yogurtproduction.yogurtproductionitsolution.model;

import lk.edu.yogurtproduction.yogurtproductionitsolution.dto.ProdMixDto;
import lk.edu.yogurtproduction.yogurtproductionitsolution.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<String> getAllProdName() throws SQLException {

        ResultSet rst = CrudUtil.execute("SELECT Prod_Name FROM production_mix_recip");

        ArrayList<String> prodtName = new ArrayList<>();

        while (rst.next()) {
            prodtName.add(rst.getString(1));
        }

        return prodtName;
    }
}