package com.wy.study.druid.domain;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 考勤机绑定员工详情
 * 姓名、卡号、卡密码、指纹、人脸
 *
 * @author yangli on 17/6/2.
 */
public class DeviceEmployeeDetailPO {

    private Integer id;
    private String shortId;
    private String mobile;
    private String name;
    private String companyId;
    private String employeeId;
    private String cardNumber;
    private String password;
    private String finger0;
    private String finger1;
    private String finger2;
    private String finger3;
    private String finger4;
    private String finger5;
    private String finger6;
    private String finger7;
    private String finger8;
    private String finger9;
    private String face;
    private Integer staffType;
    private Integer isDel;
    private int addtime;
    private int modtime;

    public DeviceEmployeeDetailPO() {}

    public DeviceEmployeeDetailPO(String companyId, String mobile) {
        this.companyId = companyId;
        this.mobile=mobile;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFinger0() {
        return finger0;
    }

    public void setFinger0(String finger0) {
        this.finger0 = finger0;
    }

    public String getFinger1() {
        return finger1;
    }

    public void setFinger1(String finger1) {
        this.finger1 = finger1;
    }

    public String getFinger2() {
        return finger2;
    }

    public void setFinger2(String finger2) {
        this.finger2 = finger2;
    }

    public String getFinger3() {
        return finger3;
    }

    public void setFinger3(String finger3) {
        this.finger3 = finger3;
    }

    public String getFinger4() {
        return finger4;
    }

    public void setFinger4(String finger4) {
        this.finger4 = finger4;
    }

    public String getFinger5() {
        return finger5;
    }

    public void setFinger5(String finger5) {
        this.finger5 = finger5;
    }

    public String getFinger6() {
        return finger6;
    }

    public void setFinger6(String finger6) {
        this.finger6 = finger6;
    }

    public String getFinger7() {
        return finger7;
    }

    public void setFinger7(String finger7) {
        this.finger7 = finger7;
    }

    public String getFinger8() {
        return finger8;
    }

    public void setFinger8(String finger8) {
        this.finger8 = finger8;
    }

    public String getFinger9() {
        return finger9;
    }

    public void setFinger9(String finger9) {
        this.finger9 = finger9;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public Integer getStaffType() {
        return staffType;
    }

    public void setStaffType(Integer staffType) {
        this.staffType = staffType;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
    }

    public int getModtime() {
        return modtime;
    }

    public void setModtime(int modtime) {
        this.modtime = modtime;
    }

    public Map<String, String> getUserFingerInfo() {

        Map<String, String> fingerInfo = new HashMap<>();
        if (!StringUtils.isEmpty(finger0)) {
            fingerInfo.put("0", finger0);
        }
        if (!StringUtils.isEmpty(finger1)) {
            fingerInfo.put("1", finger1);
        }
        if (!StringUtils.isEmpty(finger2)) {
            fingerInfo.put("2", finger2);
        }
        if (!StringUtils.isEmpty(finger3)) {
            fingerInfo.put("3", finger3);
        }
        if (!StringUtils.isEmpty(finger4)) {
            fingerInfo.put("4", finger4);
        }
        if (!StringUtils.isEmpty(finger5)) {
            fingerInfo.put("5", finger5);
        }
        if (!StringUtils.isEmpty(finger6)) {
            fingerInfo.put("6", finger6);
        }
        if (!StringUtils.isEmpty(finger7)) {
            fingerInfo.put("7", finger7);
        }
        if (!StringUtils.isEmpty(finger8)) {
            fingerInfo.put("8", finger8);
        }
        if (!StringUtils.isEmpty(finger9)) {
            fingerInfo.put("9", finger9);
        }
        return fingerInfo;
    }
}
