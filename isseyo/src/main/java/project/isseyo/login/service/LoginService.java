package project.isseyo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.mapper.LoginMapper;


@Service
public class LoginService {
	
	@Autowired //Mapper와 연결
	private LoginMapper loginMapper;

	public void insertUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		loginMapper.insertUser(loginDto);
		return;
		
	}

	public LoginDto selectUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return loginMapper.selectUser(loginDto);
	}
}
