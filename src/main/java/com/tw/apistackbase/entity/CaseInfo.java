package com.tw.apistackbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "CSAE_INFO")
public class CaseInfo {

    public CaseInfo() {
    }

    public CaseInfo(String objectiveDesc, String subjectiveDesc) {
        this.objectiveDesc = objectiveDesc;
        this.subjectiveDesc = subjectiveDesc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "OBJECTIVE_DESC", length = 255, nullable = false)
    private String objectiveDesc;

    @Column(name = "SUBJECTIVE_DESC", length = 255, nullable = false)
    private String subjectiveDesc;

    public Long getId() {
        return id;
    }

    public String getObjectiveDesc() {
        return objectiveDesc;
    }

    public void setObjectiveDesc(String objectiveDesc) {
        this.objectiveDesc = objectiveDesc;
    }
    public String getSubjectiveDesc() {
        return subjectiveDesc;
    }

    public void setSubjectiveDesc(String subjectiveDesc) {
        this.subjectiveDesc = subjectiveDesc;
    }
}
