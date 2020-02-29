package io.github.msidolphin.easyvalidator.bean;

import io.github.msidolphin.easyvalidator.annotation.*;

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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", intro='" + intro + '\'' +
                ", idCard='" + idCard + '\'' +
                ", ip='" + ip + '\'' +
                ", birthday='" + birthday + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
