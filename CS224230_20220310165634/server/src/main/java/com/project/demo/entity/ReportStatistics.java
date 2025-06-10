package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *报表统计：(ReportStatistics)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReportStatistics")
public class ReportStatistics implements Serializable {

    //ReportStatistics编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_statistics_id")
    private Integer report_statistics_id;
   // 接种人群
   @Basic
    private String vaccinated_population;
   // 接种数量
   @Basic
    private String inoculation_quantity;
   // 日期
   @Basic
    private Timestamp date;
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
