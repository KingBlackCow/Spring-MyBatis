package com.ssafy.study.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.study.model.StudyDto;
import com.ssafy.util.PageNavigation;

public interface StudyService {

	public void writeArticle(StudyDto studyDto) throws Exception;
	public List<StudyDto> listArticle(Map<String, String> map) throws Exception;
	public void joinStudy(Map<String, String> map) throws Exception;
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
	public StudyDto getArticle(int articleno) throws Exception;
	public void modifyArticle(StudyDto studyDto) throws Exception;
	public void deleteArticle(int articleno) throws Exception;
	
}
