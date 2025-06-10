package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *疫苗统计：(VaccineStatistics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "VaccineStatistics")
public class VaccineStatistics implements Serializable {

    //VaccineStatistics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaccine_statistics_id")
    private Integer vaccine_statistics_id;
   // 疫苗编号
   @Basic
    private String vaccine_number;
   // 疫苗名称
   @Basic
    private String vaccine_name;
   // 库存数量
   @Basic
    private String inventory_quantity;
   // 统计人
   @Basic
    private String statistician;
   // 备注
   @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
