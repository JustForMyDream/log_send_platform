package com.weaver.police;

import com.weaver.police.util.DatabaseHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoliceApplicationTests {



    @Test
    public void contextLoads() {

        List<String> list = new ArrayList<>();
        list.add("  id                  NUMBER(20) not null,");
        list.add("  num_id              varchar2(32) not null,");
        list.add("  sendid              VARCHAR2(320)");
//        System.out.println(DatabaseHelper.execute("test",list,"log"));

    }

}
