package com.rmt.program.RuralMassionTraining.repository;

import com.rmt.program.RuralMassionTraining.model.DistTraningPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistPartnerRepository extends JpaRepository<DistTraningPartner, Integer> {

    @Query(value = "select dist_nm\n" +
            ",LISTAGG(tr_prtner , '/' ) WITHIN GROUP(ORDER BY tr_prtner) AS tr_prtner \n" +
            ",LISTAGG(target, '/' ) WITHIN GROUP(ORDER BY tr_prtner) AS target\n" +
            ",LISTAGG(to_char(dist_tp_id), '/' ) WITHIN GROUP(ORDER BY tr_prtner) AS dist_tp_id\n" +
            "from  RMT_DIST_TR_PRTNER group by dist_nm", nativeQuery = true)
    List<Object[]> getDistrictPartnerDetails();

    @Query(value = "select distinct dist_nm from RMT_DIST_TR_PRTNER", nativeQuery = true)
    List<Object[]> getDistinctDistrictName();

    @Query(value = "select distinct tr_prtner from RMT_DIST_TR_PRTNER", nativeQuery = true)
    List<Object[]> getDistinctTrainingPartnerName();

}
