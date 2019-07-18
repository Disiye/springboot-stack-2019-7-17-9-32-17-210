package com.tw.apistackbase;

import com.tw.apistackbase.entity.Judge;
import com.tw.apistackbase.entity.Procuratorate;
import com.tw.apistackbase.repository.JudgeRepository;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JudgeTest {
    @Autowired
    private JudgeRepository judgeRepository;

    @Test
    public void should_return_judge_when_find_by_id() {
        Long id =judgeRepository.save(new Judge("Judge1")).getId();
        Judge judge = judgeRepository.findById(id).get();
        Assertions.assertEquals("Judge1", judge.getName());
    }

    @Test
    public void should_return_exception_when_judge_is_null() {
        Judge judge = new Judge();
        Assertions.assertThrows(RuntimeException.class,()->{
            judgeRepository.saveAndFlush(judge);
        });
    }
}
