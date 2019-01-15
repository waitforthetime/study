package com.wy.study.druid.dao;

import com.wy.study.druid.Runner;
import com.wy.study.druid.domain.DeviceEmployeeDetailPO;
import com.wy.study.druid.util.UUIDUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Runner.class)
public class IDeviceEmployeeDetailDAOTest {

    @Resource
    private IDeviceEmployeeDetailDAO detailDAO;

    @Test
    public void batchInsert() {
//        for (int i = 0; i < 100; i++) {
//            List<DeviceEmployeeDetailPO> pos = genPOs();
//            detailDAO.batchInsert(pos);
//        }
        List<DeviceEmployeeDetailPO> pos = detailDAO.selectByCompanyId("cefdb0c668e644e38fa4507e9f606e66");
        initPassword(pos);
        detailDAO.batchInsert(pos);

    }

    @Test
    public void batchUpdate() {
        List<DeviceEmployeeDetailPO> pos = detailDAO.selectByCompanyId("cefdb0c668e644e38fa4507e9f606e66");
        initPassword(pos);
        detailDAO.batchUpdate(pos);
    }

    @Test
    public void batchUpdate2() {
        List<DeviceEmployeeDetailPO> pos = detailDAO.selectByCompanyId("cefdb0c668e644e38fa4507e9f606e66");
        initPassword(pos);
        detailDAO.batchUpdate2(pos);
    }

    @Test
    public void selectByCompanyId() {
        List<DeviceEmployeeDetailPO> deviceEmployeeDetailPOS = detailDAO.selectByCompanyId("cefdb0c668e644e38fa4507e9f606e66");
        System.out.println(deviceEmployeeDetailPOS.size());
    }


    private void initPassword(List<DeviceEmployeeDetailPO> pos) {

        for (DeviceEmployeeDetailPO po : pos) {

            po.setPassword("456");
        }

    }

    private List<DeviceEmployeeDetailPO> genPOs() {

        String companyId1 = UUIDUtil.getUUID();
        String companyId2 = UUIDUtil.getUUID();
        String companyId3 = UUIDUtil.getUUID();

        List<String> mobiles = genMobiles();
        List<DeviceEmployeeDetailPO> list = new ArrayList<>(300);
        for (int i = 0; i < 100; i++) {
            DeviceEmployeeDetailPO po1 = new DeviceEmployeeDetailPO(companyId1, mobiles.get(i));
            DeviceEmployeeDetailPO po2 = new DeviceEmployeeDetailPO(companyId2, mobiles.get(i));
            DeviceEmployeeDetailPO po3 = new DeviceEmployeeDetailPO(companyId3, mobiles.get(i));

            list.add(po1);
            list.add(po2);
            list.add(po3);
        }
        return list;
    }

    private List<String> genMobiles() {

        List<String> mobiles = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            mobiles.add("19011000"+String.format("%3d",i));
        }
        return mobiles;
    }


}