package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *系统维护：(SystemMaintenance)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "SystemMaintenance")
public class SystemMaintenance implements Serializable {

    //SystemMaintenance编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "system_maintenance_id")
    private Integer system_maintenance_id;
   // 维护时间
   @Basic
    private Timestamp maintenance_time;
   // 维护原因
   @Basic
    private String maintenance_reason;
   // 操作人
   @Basic
    private String operator;
   // 备注
   @Basic
    private String remarks;
   // 维护日志
   @Basic
    private String maintenance_log;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
