package com.wy.study.druid.dao;

import com.wy.study.druid.domain.DeviceDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeviceMapper {


    /**
     * 插入设备
     *
     * @param devicePO
     */
    @Insert("INSERT INTO hardware.device_cp (company_id,terminal_type, nickname,device_serial_no, token, status, ver, addtime, modtime) VALUES( #{companyId},#{terminalType},#{nickname}, #{deviceSerialNo}, #{token},#{status}, #{ver}, unix_timestamp(), unix_timestamp())")
    void insertDevice(DeviceDO devicePO);

    /**
     * 解绑考勤机设备表信息
     *
     * @param deviceId
     * @return
     */
    @Update("update hardware.device_cp set is_del=1, modtime=unix_timestamp() where device_serial_no=#{deviceId} and is_del=0")
    void deleteByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 查询考勤机类型
     *
     * @param companyId
     * @return
     */
    @Select("SELECT terminal_type FROM hardware.device_cp WHERE company_id=#{companyId} and is_del=0 group by terminal_type")
    List<String> selectDeviceTypeNumberByCompanyId(String companyId);

    /**
     * 查询考勤机
     *
     * @param deviceId
     * @return
     */
    @Select("SELECT * FROM hardware.device_cp WHERE device_serial_no=#{deviceId} AND is_del=0")
    DeviceDO getDeviceById(String deviceId);

    /**
     * 获取公司考勤机列表
     *
     * @param companyId
     * @return
     */
    @Select("SELECT * FROM hardware.device WHERE company_id=#{companyId} AND is_del=0")
    List<DeviceDO> getDeviceListByCompanyId(String companyId);

    /**
     * 获取指定类型的考勤机列表
     *
     * @param type
     * @return
     */
    @Select("SELECT * FROM hardware.device_cp WHERE terminal_type=#{type} AND is_del=0")
    List<DeviceDO> getDeviceListByType(String type);

    /**
     * 获取公司下指定类型的考勤机
     *
     * @param companyId
     * @param type
     * @return
     */
    @Select("SELECT * FROM hardware.device_cp WHERE company_id=#{companyId} AND terminal_type=#{type} AND is_del=0")
    List<DeviceDO> getDeviceListByCompanyIdAndType(@Param("companyId") String companyId, @Param("type") String type);

    /**
     * 获取所有考勤机
     *
     * @return
     */
    @Select("SELECT * FROM hardware.device_cp WHERE is_del=0")
    List<DeviceDO> getAllDevieList();

    /**
     * 更新管理员数目
     *
     * @param deviceId
     * @param adminNum
     * @return
     */
    @Update("update hardware.device_cp set admin_num=#{adminNum}, modtime=unix_timestamp() where device_serial_no=#{deviceId} and is_del=0")
    void updateAdminNum(@Param("deviceId") String deviceId, @Param("adminNum") int adminNum);

    /**
     * 删除考勤机
     *
     * @param companyId
     * @param deviceId
     */
    @Update("UPDATE hardware.device_cp SET is_del=1,modtime=unix_timestamp() WHERE company_id=#{companyId} AND device_serial_no=#{deviceId}")
    void deleteDevice(@Param("companyId") String companyId, @Param("deviceId") String deviceId);

    /**
     * 删除全公司考勤机
     *
     * @param companyId
     */
    @Update("UPDATE hardware.device_cp SET is_del=1,modtime=unix_timestamp() WHERE company_id=#{companyId}")
    void deleteDeviceByCompanyId(String companyId);

    /**
     * 更新考勤机
     *
     * @param devicePO
     */
    void updateDevice(DeviceDO devicePO);

    /**
     * 获取某种类型考勤机所有在使用的公司
     *
     * @param type
     * @return
     */
    @Select("SELECT distinct(company_id) FROM hardware.device_cp WHERE terminal_type=#{type} AND is_del=0")
    List<String> getUsedDeviceCompanyIdsByType(String type);

    /**
     * 批量更新设备列表的版本号
     *
     * @param serialDeviceNos 序列号列表
     */
    void batchUpdateDeviceVersion(@Param("serialDeviceNos") List<String> serialDeviceNos, @Param("version") Integer version);

    void batchInsert(List<DeviceDO> deviceDOS);
}
