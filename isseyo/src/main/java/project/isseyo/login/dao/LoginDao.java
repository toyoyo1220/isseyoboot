package project.isseyo.login.dao;

import java.util.Optional;

import project.isseyo.login.dto.LoginDto;

public interface LoginDao{
    Optional<LoginDto> findByAccount(String account);
}