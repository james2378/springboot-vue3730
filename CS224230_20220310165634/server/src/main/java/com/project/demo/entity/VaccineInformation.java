package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *疫苗信息：(VaccineInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VaccineInformation")
public class VaccineInformation implements Serializable {

    //VaccineInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_information_id")
    private Integer vaccine_information_id;
   // 疫苗编号
   @Basic
    private String vaccine_number;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 疫苗作用
   @Basic
    private String vaccine_effect;
   // 适应人群
   @Basic
    private String adaptive_population;
   // 禁忌
   @Basic
    private String taboo;
   // 生产厂家
   @Basic
    private String manufacturer;
   // 生产日期
   @Basic
    private Timestamp date_of_manufacture;
   // 过期日期
   @Basic
    private Timestamp expiration_date_;
   // 库存数量
   @Basic
    private String inventory_quantity;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
