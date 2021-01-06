package com.zb.pojo;

import lombok.*;

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
  private int gender;
  private String academy;
  private String grade;
  private String studentNumber;
}
