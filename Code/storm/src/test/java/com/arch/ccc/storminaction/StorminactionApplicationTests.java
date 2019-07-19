package com.arch.ccc.storminaction;

import com.arch.ccc.storminaction.bolt.ReportBolt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorminactionApplicationTests {

    @Test
    public void contextLoads() {
        int target = 10;
        int[] position = {0,4,2};
        int[] speed = {2,1,3};

        Fleet fleet = new Fleet();
        fleet.carFleet(target, position, speed);
    }

}
