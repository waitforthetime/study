package com.wy.study.druid;

import com.wy.study.druid.dao.DeviceMapper;
import com.wy.study.druid.domain.DeviceDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Runner.class)
public class BatchInsert {

    @Resource
    private DeviceMapper deviceMapper;

    @Test
    public void batchInsert() {

        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10000);
        for (int i = 0; i < 1000; i++) {
            String deviceId = genDeviceId();
            while (blockingQueue.contains(deviceId)) {
                deviceId = genDeviceId();
            }
            blockingQueue.add(deviceId);
        }
        System.out.println("=========================");

        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new Robot(blockingQueue, deviceMapper, countDownLatch));
            thread.start();
        }
        System.out.println("init over");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void batchIns() {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(1000);
        String deviceId = genDeviceId();
        for (int i = 0; i < 500; i++) {
            blockingQueue.add(deviceId);
        }
        System.out.println("=========================");

        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new Robot(blockingQueue, deviceMapper, countDownLatch));
            thread.start();
        }
        System.out.println("init over");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Robot implements Runnable {

        private final BlockingQueue<String> blockingQueue;//
        private final DeviceMapper deviceMapper;
        private final CountDownLatch countDownLatch;

        Robot(BlockingQueue<String> blockingQueue, DeviceMapper deviceMapper, CountDownLatch countDownLatch) {
            this.blockingQueue = blockingQueue;
            this.deviceMapper = deviceMapper;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!blockingQueue.isEmpty()) {
                String deviceId;
                try {
                    synchronized (blockingQueue) {
                        if (blockingQueue.isEmpty()) {
                            System.out.println(name + " is over");
                            countDownLatch.countDown();
                            return;
                        }
                        deviceId = blockingQueue.take();
                    }
                    List<DeviceDO> deviceDOS = genDeviceDOs(deviceId);
                    StopWatch stopWatch = new StopWatch(deviceId);
                    stopWatch.start();
                    deviceMapper.batchInsert(deviceDOS);
                    stopWatch.stop();
                    System.out.println("spend time:" + stopWatch.getTotalTimeSeconds());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + " is over");
            countDownLatch.countDown();
        }
    }

    public static String genDeviceId() {
        return getUUID().substring(0, 16);
    }

    public static List<DeviceDO> genDeviceDOs(String deviceId) {

        List<DeviceDO> deviceDOS = new ArrayList<>(1000);
        for (int i = 0; i < 999; i++) {
            DeviceDO deviceDO = DeviceDO.builder()
                    .deviceSerialNo(deviceId)
                    .isDel(0)
                    .terminalType(genTerminalType())
                    .nickname("test")
                    .ver(1)
                    .companyId(getUUID())
                    .adminNum(random())
                    .build();
            deviceDOS.add(deviceDO);
        }
        DeviceDO deviceDO = DeviceDO.builder()
                .deviceSerialNo(deviceId)
                .isDel(0)
                .ver(1)
                .nickname("zeze")
                .terminalType(genTerminalType())
                .companyId(getUUID())
                .adminNum(random())
                .build();
        deviceDOS.add(deviceDO);
        return deviceDOS;
    }

    private static String genTerminalType() {
        Integer random = random(3);
        switch (random) {
            case 1:
                return "zk1001";
            case 2:
                return "f500";
            default:
                return "default";
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Integer random() {
        return random(32);
    }

    public static Integer random(int seed) {
        return new Random().nextInt(seed);
    }


}
