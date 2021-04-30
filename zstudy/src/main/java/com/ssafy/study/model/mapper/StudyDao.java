package com.ssafy.study.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.study.model.StudyDto;

public interface StudyDao {

	public void writeArticle(StudyDto studyDto) throws SQLException;
	public List<StudyDto> listArticle(Map<String, Object > map) throws SQLException;
	public void joinStudy(Map<String, String> map) throws Exception;
	public int getTotalCount(Map<String, String> map) throws SQLException;
	
	public StudyDto getArticle(int articleno) throws SQLException;
	public void modifyArticle(StudyDto studyDto) throws SQLException;
	public void deleteArticle(int articleno) throws SQLException;
	
}
