package org.kts1021.docker.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.kts1021.docker.demo.domain.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT DISTINCT * FROM users WHERE username = #{username}")
    List<User> findByUsername(@Param("username") String username);
}
