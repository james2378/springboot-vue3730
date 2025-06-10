package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *公告栏：(BulletinBoard)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "BulletinBoard")
public class BulletinBoard implements Serializable {

    //BulletinBoard编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bulletin_board_id")
    private Integer bulletin_board_id;
   // 公告标题
   @Basic
    private String announcement_title;
   // 发布人
   @Basic
    private String publisher;
   // 发布日期
   @Basic
    private Timestamp release_date;
   // 备注
   @Basic
    private String remarks;
   // 公告内容
   @Basic
    private String announcement_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
