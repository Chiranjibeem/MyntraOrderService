package com.rmt.program.RuralMassionTraining.repository;

import com.rmt.program.RuralMassionTraining.model.OngoingUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OngoingUpdateRepository extends JpaRepository<OngoingUpdate,Integer> {

    @Query(value="SELECT nvl(sum(BATCH_COMMENCED),0) as BATCH_COMMENCED,nvl(count(BATCH_TYPE),0) as ongoing_batch,nvl(sum(  BATCHES_COMPLETED),0) as batch_completed,\n" +
            "nvl(sum(COMPLETED_LEVEL ),0) as COMPLETED_LEVEL  , nvl(sum(ENROLLED_CANDIDATES),0) as ENROLLED_CANDIDATES  , nvl(sum(HOUSE_TAGGED ),0)  HOUSE_TAGGED, \n" +
            "(select count(batch_type) from RM_ONGOING_UPDATE  where batch_type = 'RPL') as RPL,\n" +
            "(select count(batch_type) from RM_ONGOING_UPDATE  where batch_type = 'STT') as STT,\n"+
            "nvl(sum(PLINTH_LEVEL),0) as PLINTH_LEVEL,nvl(sum(LINTEL_LEVEL),0) as LINTEL_LEVEL,nvl(sum(ROOF_LEVEL) ,0) as ROOF_LEVEL \n"+
            "from RM_ONGOING_UPDATE ",nativeQuery = true)
    public List<Object[]> getDashboardDetails();
}
