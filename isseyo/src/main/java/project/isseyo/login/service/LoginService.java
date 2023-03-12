package project.isseyo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.isseyo.login.dto.LoginDto;
import project.isseyo.login.mapper.LoginMapper;


@Service
public class LoginService {
	
	@Autowired //Mapper와 연결
	private LoginMapper loginMapper;
	/**
	 * 사용자 가입
	 * @param loginDto
	 * @return 
	 * @exception Exception
	 */
	public void insertUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		loginMapper.insertUser(loginDto);
		return;
		
	}
	/**
	 * 사용자 정보 선택
	 * @param loginDto
	 * @return loginDto
	 * @exception Exception
	 */
	public LoginDto selectUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		return loginMapper.selectUser(loginDto);
	}
	/**
	 * 사용자 정보 갱신
	 * @param loginDto
	 * @return
	 * @exception Exception
	 */
	public void updateUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		loginMapper.updateUser(loginDto);
		return;
	}
	
	/**
	 * 사용자 API 체크
	 * @param bizApiKey
	 * @return
	 * @exception Exception
	 */
	public LoginDto apiCheack(String bizApiKey) {
		// TODO Auto-generated method stub
		return loginMapper.apiCheack(bizApiKey);
	}
}
