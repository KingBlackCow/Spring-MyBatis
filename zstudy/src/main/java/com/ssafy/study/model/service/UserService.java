package com.ssafy.study.model.service;

import java.util.Map;

import com.ssafy.study.model.MemberDto;

public interface UserService {

	public MemberDto login(Map<String, String> map) throws Exception;
	
}
