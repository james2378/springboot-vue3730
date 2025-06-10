package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *咨询电话：(ConsultationTelephone)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ConsultationTelephone")
public class ConsultationTelephone implements Serializable {

    //ConsultationTelephone编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consultation_telephone_id")
    private Integer consultation_telephone_id;
   // 咨询电话
   @Basic
    private String consultation_telephone;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
