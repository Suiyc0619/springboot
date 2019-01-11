package com.sui.study.mapper;

import com.sui.study.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("select  * from user where id=#{id}")
    public User selectUser(Long id);

    @Options(useGeneratedKeys = true)
    @Insert("insert into user (name,id_card) values (#{name},#{idCard})")
    public int saveUser(User user);

    @Select("select * from user")
    public List<User> selectAll();

}
