package com.tw.apistackbase;

import com.tw.apistackbase.entity.Judge;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.repository.ProcuratorateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProcuratorateTest {
    @Autowired
    private ProcuratorateRepository procuratorateRepository;

    @Test
    public void should_return_procuratorate_when_find_by_id() {
        Long id =procuratorateRepository.saveAndFlush(new Procuratorate("Procuratorate1")).getId();
        Procuratorate procuratorate = procuratorateRepository.findById(id).get();
        Assertions.assertEquals("Procuratorate1", procuratorate.getName());
    }

    @Test
    public void should_return_exception_when_is_null() {
        Procuratorate procuratorate = new Procuratorate();
        Assertions.assertThrows(RuntimeException.class,()->{
            procuratorateRepository.saveAndFlush(procuratorate);
        });
    }
    @Test
    public void should_return_procuratorate_when_add_new_procuratorate() {
//        Long id =procuratorateRepository.saveAndFlush(new Procuratorate("Procuratorate1")).getId();
        Procuratorate procuratorate = new Procuratorate("Procuratorate2");
        List<Judge> judges = new ArrayList<>();
        judges.add(new Judge("Judge1"));
        judges.add(new Judge("Judge2"));
        procuratorate.setJudges(judges);
        Procuratorate procuratorateResult = procuratorateRepository.save(procuratorate);
        Assertions.assertEquals("Judge1", procuratorateResult.getJudges().get(0).getName());
    }
}