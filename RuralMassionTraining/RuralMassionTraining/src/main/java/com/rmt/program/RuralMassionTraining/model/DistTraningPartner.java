package com.rmt.program.RuralMassionTraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="RMT_DIST_TR_PRTNER")
public class DistTraningPartner {

    @Column(name="DIST_NM")
    @Id
    private String districtName;
    @Column(name="TR_PRTNER")
    private String trainingPartner;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getTrainingPartner() {
        return trainingPartner;
    }

    public void setTrainingPartner(String trainingPartner) {
        this.trainingPartner = trainingPartner;
    }
}
