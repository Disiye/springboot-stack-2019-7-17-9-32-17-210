package com.tw.apistackbase.entity;

import javax.persistence.*;

@Entity
@Table(name = "JUDGE")
public class Judge {

    public Judge() {
    }

    public Judge(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "PROCURATORATE_ID", nullable = true)
    private String procuratorateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
