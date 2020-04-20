package com.rmt.program.RuralMassionTraining.model;

import javax.persistence.*;

@Entity(name="RMT_DIST_TR_PRTNER")
public class DistTraningPartner {

    @Id
    @Column(name="DIST_TP_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int distTpId;
    @Column(name="DIST_NM")
    private String districtName;
    @Column(name="TR_PRTNER")
    private String trainingPartner;
    @Column(name="TARGET")
    private String target;

    public int getDistTpId() {
        return distTpId;
    }

    public void setDistTpId(int distTpId) {
        this.distTpId = distTpId;
    }

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
