package com.rmt.program.RuralMassionTraining.model;

import javax.persistence.*;

@Entity(name="RM_ONGOING_UPDATE")
public class OngoingUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ONGOING_UPD_ID")
    private int ongoingUpdateId;
    @Column(name="PARTNER")
    private String partner;
    @Column(name="DISTRICT")
    private String district;
    @Column(name="DATE_TIME")
    private String dateAndTime;
    @Column(name="BATCH_COMMENCED")
    private int batchCommenced;
    @Column(name="BATCH_TYPE")
    private String batchType;
    @Column(name="ENROLLED_CANDIDATES")
    private int enrolledCandidates;
    @Column(name="BATCHES_COMPLETED")
    private int batchesCompleted;
    @Column(name="HOUSE_TAGGED")
    private int houseTagged;
    @Column(name="PLINTH_LEVEL")
    private int plinthLevel;
    @Column(name ="LINTEL_LEVEl")
    private int lintelLevel;
    @Column(name="ROOF_LEVEL")
    private int roofLevel;
    @Column(name="COMPLETED_LEVEL")
    private int completedLevel;
    @Transient
    private int ongoingBatchUpdate;
    @Transient
    private int RRL;
    @Transient
    private int STT;

    public int getRRL() {
        return RRL;
    }

    public void setRRL(int RRL) {
        this.RRL = RRL;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public OngoingUpdate(){}

    public int getOngoingBatchUpdate() {
        return ongoingBatchUpdate;
    }

    public void setOngoingBatchUpdate(int ongoingBatchUpdate) {
        this.ongoingBatchUpdate = ongoingBatchUpdate;
    }

    public int getOngoingUpdateId() {
        return ongoingUpdateId;
    }

    public void setOngoingUpdateId(int ongoingUpdateId) {
        this.ongoingUpdateId = ongoingUpdateId;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getBatchCommenced() {
        return batchCommenced;
    }

    public void setBatchCommenced(int batchCommenced) {
        this.batchCommenced = batchCommenced;
    }

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public int getEnrolledCandidates() {
        return enrolledCandidates;
    }

    public void setEnrolledCandidates(int enrolledCandidates) {
        this.enrolledCandidates = enrolledCandidates;
    }

    public int getBatchesCompleted() {
        return batchesCompleted;
    }

    public void setBatchesCompleted(int batchesCompleted) {
        this.batchesCompleted = batchesCompleted;
    }

    public int getHouseTagged() {
        return houseTagged;
    }

    public void setHouseTagged(int houseTagged) {
        this.houseTagged = houseTagged;
    }

    public int getPlinthLevel() {
        return plinthLevel;
    }

    public void setPlinthLevel(int plinthLevel) {
        this.plinthLevel = plinthLevel;
    }

    public int getLintelLevel() {
        return lintelLevel;
    }

    public void setLintelLevel(int lintelLevel) {
        this.lintelLevel = lintelLevel;
    }

    public int getRoofLevel() {
        return roofLevel;
    }

    public void setRoofLevel(int roofLevel) {
        this.roofLevel = roofLevel;
    }

    public int getCompletedLevel() {
        return completedLevel;
    }

    public void setCompletedLevel(int completedLevel) {
        this.completedLevel = completedLevel;
    }
}
