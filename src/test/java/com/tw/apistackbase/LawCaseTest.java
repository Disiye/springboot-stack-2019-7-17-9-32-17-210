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
public class LawCaseTest {

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
    public void return_all_law_cases_when_get_all_law_case() {
        CaseInfo caseInfo = new CaseInfo("objectiveDesc","subjectiveDesc");
        lawCaseRepository.saveAndFlush(new LawCase("LawCase1",new Date().getTime(),caseInfo));
        lawCaseRepository.saveAndFlush(new LawCase("LawCase2",new Date().getTime(),caseInfo));
        List<LawCase> lawCases = lawCaseRepository.findAll();
        Assertions.assertEquals("LawCase1", lawCases.get(0).getLawCaseName());
    }

    @Test
    public void should_return_exception_when_is_null() {
        LawCase lawCase = new LawCase();
        Assertions.assertThrows(RuntimeException.class,()->{
                lawCaseRepository.saveAndFlush(lawCase);
        });
    }

    @Test
    public void return_law_case_when_get_law_case_by_id(){
        Long id =lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime())).getId();
        lawCaseRepository.save(new LawCase("LawCase2",new Date().getTime()));
        Assertions.assertEquals("LawCase1", lawCaseRepository.findById(id).get().getLawCaseName());
    }

    @Test
    public void return_all_law_cases_when_get_all_law_case_asc_by_data() {
        lawCaseRepository.saveAndFlush(new LawCase("LawCase1",new Date().getTime()));
        lawCaseRepository.saveAndFlush(new LawCase("LawCase1",new Date().getTime()));
        List<LawCase> lawCases = lawCaseRepository.findByOrderByMillisecondsAsc();
        Assertions.assertEquals("LawCase1", lawCases.get(1).getLawCaseName());
        Assertions.assertEquals("LawCase1", lawCases.get(0).getLawCaseName());
    }

    @Test
    public void return_law_cases_when_get_law_case_by_name() {
        lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime()));
        lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime()));
        lawCaseRepository.save(new LawCase("LawCase2",new Date().getTime()));
        List<LawCase> lawCases = lawCaseRepository.findByLawCaseName("LawCase1");
        Assertions.assertEquals("LawCase1", lawCases.get(0).getLawCaseName());
        Assertions.assertEquals(2, lawCases.size());
    }

    @Test
    public void should_return_delete_when_delete_by_id() {
        Long id = lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime())).getId();
        lawCaseRepository.save(new LawCase("LawCase1",new Date().getTime()));
        lawCaseRepository.save(new LawCase("LawCase2",new Date().getTime()));
        lawCaseRepository.deleteById(id);
        List<LawCase> lawCases = lawCaseRepository.findAll();
        Assertions.assertEquals(2, lawCases.size());
    }

}
