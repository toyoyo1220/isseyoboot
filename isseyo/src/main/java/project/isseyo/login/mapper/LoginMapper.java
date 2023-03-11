package project.isseyo.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.isseyo.login.dto.LoginDto;

@Mapper
public interface LoginMapper {

	void insertUser(LoginDto loginDto);

	LoginDto selectUser(LoginDto loginDto);

}
