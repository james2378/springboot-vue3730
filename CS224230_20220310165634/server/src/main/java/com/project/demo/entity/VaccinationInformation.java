package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *接种信息：(VaccinationInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VaccinationInformation")
public class VaccinationInformation implements Serializable {

    //VaccinationInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_information_id")
    private Integer vaccination_information_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 姓名
   @Basic
    private String full_name;
   // 年龄
   @Basic
    private String age;
   // 联系方式
   @Basic
    private String contact_information;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 预约时间
   @Basic
    private Timestamp time_of_appointment;
   // 结束时间
   @Basic
    private Timestamp end_time;
   // 接种地点
   @Basic
    private String vaccination_site;
    // 审核状态
    @Basic
    private String examine_state;
    // 审核回复
    @Basic
    private String examine_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
