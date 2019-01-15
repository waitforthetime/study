package com.wy.study.druid.dao;


import com.wy.study.druid.domain.DeviceEmployeeDetailPO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤机绑定员工数据库操作
 *
 * @author yangli on 17/6/1.
 */
@Mapper
public interface IDeviceEmployeeDetailDAO {

    void batchInsert(List<DeviceEmployeeDetailPO> employeeDetailPOList);

    void batchUpdate(List<DeviceEmployeeDetailPO> employeeDetailPOList);

    void batchUpdate2(List<DeviceEmployeeDetailPO> employeeDetailPOS);

    List<DeviceEmployeeDetailPO> selectByCompanyId(@Param("companyId") String companyId);
}
