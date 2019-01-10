package com.sui.study.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user")
public class User {
    private String realName;
    private String idCard;
    private Dog dog;

    @Override
    public String toString() {
        return "User{" +
                "realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", dog=" + dog +
                '}';
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
