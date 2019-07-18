package com.tw.apistackbase;

import com.tw.apistackbase.entity.CaseInfo;
import com.tw.apistackbase.entity.LawCase;
import com.tw.apistackbase.repository.CaseInfoRepository;
import com.tw.apistackbase.repository.LawCaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CaseInfoTest {

    @Autowired
    private LawCaseRepository lawCaseRepository;

    @Autowired
    private CaseInfoRepository caseInfoRepository;

    @Before
    public void deleteAll(){
        lawCaseRepository.deleteAll();
        caseInfoRepository.deleteAll();
    }

    @Test
    public void return_case_info_when_get_case_info_by_id(){
        Long id =  caseInfoRepository.save(new CaseInfo("objectiveDesc1","subjectiveDesc1")).getId();
        caseInfoRepository.save(new CaseInfo("objectiveDesc2","subjectiveDesc2"));
        Assertions.assertEquals("objectiveDesc1", caseInfoRepository.findById(id).get().getObjectiveDesc());
    }

    @Test
    public void return_same_case_info_when_get_case_info_by_id(){
        Long id = caseInfoRepository.save(new CaseInfo("objectiveDesc1","subjectiveDesc1")).getId();
        caseInfoRepository.save(new CaseInfo("objectiveDesc2","subjectiveDesc2"));
        Assertions.assertEquals("objectiveDesc1", caseInfoRepository.findById(id).get().getObjectiveDesc());
        Assertions.assertEquals("subjectiveDesc1", caseInfoRepository.findById(id).get().getSubjectiveDesc());
    }

    @Test
    public void return_all_case_info_when_get_case_info() {
        Long id = lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime(), new CaseInfo("objectiveDesc1","subjectiveDesc1"))).getId();
        lawCaseRepository.save(new LawCase("LawCase2",new Date().getTime(), new CaseInfo("objectiveDesc1","subjectiveDesc1")));
        LawCase lawCase = lawCaseRepository.findById(id).get();
        Assertions.assertEquals("LawCase1", lawCase.getLawCaseName());
        Assertions.assertEquals("objectiveDesc1", lawCase.getCaseInfo().getObjectiveDesc());
        Assertions.assertEquals("subjectiveDesc1", lawCase.getCaseInfo().getSubjectiveDesc());
    }

    @Test
    public void return_law_case_and_case_info_when_add_case_info_in_law_case() {
        LawCase lawCase = lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime()));
        lawCase.setCaseInfo( new CaseInfo("objectiveDesc1","subjectiveDesc1"));
        LawCase lawCaseResult = lawCaseRepository.findById(lawCase.getId()).get();
        Assertions.assertEquals("LawCase1", lawCaseResult.getLawCaseName());
        Assertions.assertEquals("objectiveDesc1", lawCaseResult.getCaseInfo().getObjectiveDesc());
        Assertions.assertEquals("subjectiveDesc1", lawCaseResult.getCaseInfo().getSubjectiveDesc());
    }
}
