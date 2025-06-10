package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *接种预约：(VaccinationAppointment)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VaccinationAppointment")
public class VaccinationAppointment implements Serializable {

    //VaccinationAppointment编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccination_appointment_id")
    private Integer vaccination_appointment_id;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 生产厂家
   @Basic
    private String manufacturer;
   // 适应人群
   @Basic
    private String adaptive_population;
   // 禁忌
   @Basic
    private String taboo;
   // 预约时间
   @Basic
    private Timestamp time_of_appointment;
   // 结束时间
   @Basic
    private Timestamp end_time;
   // 可预约人数
   @Basic
    private String number_of_people_who_can_make_an_appointment;
   // 接种地点
   @Basic
    private String vaccination_site;
   // 疫苗作用
   @Basic
    private String vaccine_effect;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
