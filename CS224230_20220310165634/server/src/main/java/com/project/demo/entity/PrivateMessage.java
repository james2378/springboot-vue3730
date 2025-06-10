package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *私密留言：(PrivateMessage)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PrivateMessage")
public class PrivateMessage implements Serializable {

    //PrivateMessage编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "private_message_id")
    private Integer private_message_id;
   // 用户名
   @Basic
    private Integer user_name;
   // 姓名
   @Basic
    private String full_name;
   // 联系方式
   @Basic
    private String contact_information;
   // 日期
   @Basic
    private Timestamp date;
   // 留言内容
   @Basic
    private String message_content;
   // 工作人员回复
   @Basic
    private String staff_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
