package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *公开留言：(PublicMessage)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PublicMessage")
public class PublicMessage implements Serializable {

    //PublicMessage编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "public_message_id")
    private Integer public_message_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 姓名
   @Basic
    private String full_name;
   // 日期
   @Basic
    private Timestamp date;
   // 留言内容
   @Basic
    private String message_content;
   // 用户回复
   @Basic
    private String user_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
