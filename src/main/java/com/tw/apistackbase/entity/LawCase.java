package com.tw.apistackbase.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "LAW_CASE")
public class LawCase {

    public LawCase() {
    }

    public LawCase(String lawCaseName, Long milliseconds) {
        this.lawCaseName = lawCaseName;
        this.milliseconds = milliseconds;
    }

    public LawCase(String lawCaseName, Long milliseconds, CaseInfo caseInfo) {
        this.lawCaseName = lawCaseName;
        this.milliseconds = milliseconds;
        this.caseInfo = caseInfo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "LAW_CASE_NAME", length = 255, nullable = false)
    private String lawCaseName;

    @Column(name = "MILLISECONDS", nullable = false)
    private Long milliseconds;

    @OneToOne(cascade = {CascadeType.ALL})
    private CaseInfo caseInfo;

    public Long getId() {
        return id;
    }

    public String getLawCaseName() {
        return lawCaseName;
    }

    public void setLawCaseName(String lawCaseName) {
        this.lawCaseName = lawCaseName;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public CaseInfo getCaseInfo(){
        return caseInfo;
    }

    public void setCaseInfo(CaseInfo caseInfo){
        this.caseInfo = caseInfo;
    }

}
