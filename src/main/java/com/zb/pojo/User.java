package com.zb.pojo;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
  private int id;
  private String username;
  private String password;
  private String realName;
  private String phoneNumber;
  private String email;
  private int deleted;
  private int gender;
  private String academy;
  private String grade;
  private String studentNumber;
  private String role;
  private Date creatTime;
  private Date updateTime;
  private List<Item> items;
}
