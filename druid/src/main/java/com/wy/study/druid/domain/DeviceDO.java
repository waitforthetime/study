package com.wy.study.druid.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDO {

    private Integer id;
    private String nickname;
    private String terminalType;
    private String deviceSerialNo;
    private String token;
    private String companyId;
    private Integer isDel;
    private Integer status;
    private Integer employeeNum;
    private Integer adminNum;
    private Integer activate;
    private Long addtime;
    private Long modtime;
    private Integer ver;
}
