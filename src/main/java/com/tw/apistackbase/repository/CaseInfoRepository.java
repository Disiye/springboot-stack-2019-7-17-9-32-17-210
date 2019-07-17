package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.CaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseInfoRepository extends JpaRepository<CaseInfo, Long> {
}
