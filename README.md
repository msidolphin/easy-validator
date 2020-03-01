# easy-validator

> 轻量级服务端校验框架

![Java CI](https://github.com/msidolphin/easy-validator/workflows/Java%20CI/badge.svg)
[![Build Status](https://travis-ci.org/msidolphin/easy-validator.svg?branch=master)](https://travis-ci.org/msidolphin/easy-validator)
[![codebeat badge](https://codebeat.co/badges/fc1c26fd-0f5a-4abb-83a3-ffb5af9f729c)](https://codebeat.co/projects/github-com-msidolphin-easy-validator-master)
[![codecov](https://codecov.io/gh/msidolphin/easy-validator/branch/master/graph/badge.svg)](https://codecov.io/gh/msidolphin/easy-validator)
![Maven Central](https://img.shields.io/maven-central/v/io.github.msidolphin/easyvalidator?style=plastic)

```xml
<dependency>
  <groupId>io.github.msidolphin</groupId>
  <artifactId>easyvalidator</artifactId>
  <version>1.0.0-RELEASE</version>
</dependency>
```


## 使用方式

### 非注解校验
```java
class Main {
    public static void main(String[] args){
      Map<String, Object> params = new HashMap<>();
      params.put("name", "mike");
      params.put("age", 25);
      User user = new User();
      user.setName("mike")
          .setAge(25)
          .setEmail("validator@vip.com")
          .setPhoneNumber("18600000000")
          .setBirthday("1995-01-01")
          .setIdCard("11010119950101555X")
          .setIp("192.168.0.1")
          .setIntro("我的名字叫迈克")
          .setWeight("75kg");
      Validator.is(params).notNull()
              .get("name").notEmpty().english().maxLength(10)
              .get("age").notEmpty().min(18).max(26)
              .and(user).notNull()
              .get("name").notEmpty().english().maxLength(10)
              .get("age").notEmpty().min(18).max(26)
              .get("email").email()
              .get("phoneNumber").phone()
              .get("birthday").date("yyyy-MM-dd")
              .get("ip").ip()
              .get("idCard").idCard()
              .get("intro").chinese().minLength(2)
              .get("weight").regex("\\d{2,3}kg");
    }
}
```

### 注解校验
```java
public class User {

    @NotEmpty(msg = "please enter you name.")
    @MaxLength(value = 10, msg = "name's length cannot be greater than 10.")
    @English(msg = "name's must be in english, please using english characters only.")
    private String name;

    @NotNull(msg = "please enter you age")
    @Min(value = 18, msg = "age must be greater than 17.")
    @Max(value = 26, msg = "age must be less than 27.")
    private int age;

    @Phone(msg = "the mobile phone number was invalid.")
    @NotEmpty(msg = "please enter you phone number.")
    private String phoneNumber;

    @Email(msg = "the email was invalid.")
    private String email;

    @Chinese
    @MaxLength(20)
    @MinLength(2)
    private String intro;

    @IdCard(msg = "the id card was invalid.")
    private String idCard;

    @IP(msg = "the ip address was invalid.")
    private String ip;

    @Date("yyyy-MM-dd")
    private String birthday;

    @Pattern("\\d{1,3}kg")
    private String weight;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getIntro() {
        return intro;
    }

    public User setIntro(String intro) {
        this.intro = intro;
        return this;
    }

    public String getIdCard() {
        return idCard;
    }

    public User setIdCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public User setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public User setWeight(String weight) {
        this.weight = weight;
        return this;
    }
}
```

```java
class Main {
    public static void main(String[] args){
      User user = new User();
      user.setName("mike")
          .setAge(25)
          .setEmail("validator@vip.com")
          .setPhoneNumber("18600000000")
          .setBirthday("1995-01-01")
          .setIdCard("11010119950101555X")
          .setIp("192.168.0.1")
          .setIntro("我的名字叫迈克")
          .setWeight("75kg");
      Validator.validate(user);
    }
}
```

## 支持的注解

| 注解        | 说明    |
| :--------- | :------ |
| @NotNull | 非null校验 |
| @NotEmpty | 非空校验（包含非null校验） |
| @Max | 最大值校验 |
| @Min | 最小值校验 |
| @MaxLength | 最大长度校验 |
| @MinLength | 最小长度校验 |
| @IdCard | 中国大陆身份证校验 |
| @Email | 邮箱格式校验 |
| @Phone | 手机号校验 |
| @IP | IP地址校验 |
| @Chinese | 中文字符校验 |
| @English | 英文校验 |
| @Pattern | 自定义正则校验 |
| @Date | 日期格式校验 |
| @Constraint | 自定义校验 | 

## 支持的函数

| 函数名        | 说明    | 参数 |
| :--------- | :------ | :----- |
| is | 创建validator对象 | (Object value) |
| get | 获取对象的属性 | (String fieldName) |
| and | 变更待校验对象 | (Object value) |
| notNull | 非null校验| -  /（String message） |
| notEmpty | 非空校验（包含非null校验） | -  /（String message) | 
| max | 最大值校验 | (int max) / (int max, String message) |
| min | 最小值校验 | (int min) / (int min, String message)|
| maxLength | 最大长度校验 | (int max) / (int max, String message) |
| minLength | 最小长度校验 | (int min) / (int min, String message) |
| idCard | 中国大陆身份证校验 | -  /（String message） |
| email | 邮箱格式校验 | -  /（String message） |
| phone | 手机号校验 | -  /（String message） |
| ip | IP地址校验 | -  /（String message） |
| chinese| 中文字符校验 | -  /（String message） |
| english | 英文校验 | -  /（String message） |
| pattern | 自定义正则校验 | (String pattern) / (String pattern, String message) |
| date | 日期格式校验 | (String format) /（String format, String message） |
| custom | 自定义校验 | Class<? extends ConstraintValidator> validatorClass |

## 自定义校验

### 注解

一、使用@Constraint注解，指定自定义校验类的class
```java
import java.lang.annotation.*;
import io.github.msidolphin.easyvalidator.annotation.Constraint;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = SexValidator.class)
public @interface Sex {
}

```

二、自定义校验类继承ConstraintValidator抽象类并重写validate方法
```java
import io.github.msidolphin.easyvalidator.constraint.BaseConstraint;
import io.github.msidolphin.easyvalidator.validator.ConstraintValidator;

public class SexValidator extends ConstraintValidator<String> {

    @Override
    public void validate(String gender, BaseConstraint constraint) {
        if (!"male".equals(gender) && !"female".equals(gender)) {
            throw new RuntimeException("gender must be male or female.");
        }
    }

}
```

### 调用custom函数
```java
class Main {
    public static void main(String[] args){
        User user = new User();
        Validator.is(user).get("sex").custom(SexValidator.class);
    }
}
```

Copyright © 2020-present, msidolphin