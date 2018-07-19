package com.purchaser.pojo;

public class AdmirerWithBLOBs extends Admirer {
    private String qualification;

    private String learning;

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification == null ? null : qualification.trim();
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning == null ? null : learning.trim();
    }
}