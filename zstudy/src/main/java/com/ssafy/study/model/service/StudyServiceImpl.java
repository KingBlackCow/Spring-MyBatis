package com.ssafy.study.model.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.study.model.StudyDto;
import com.ssafy.study.model.mapper.StudyDao;
import com.ssafy.util.PageNavigation;

@Service
public class StudyServiceImpl implements StudyService {
	
	@Autowired
	private SqlSession sqlSession;
	


	@Override
	public void writeArticle(StudyDto studyDto) throws Exception {
	

		sqlSession.getMapper(StudyDao.class).writeArticle(studyDto);
	}

	@Override
	public List<StudyDto> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("key", map.get("key") == null ? "" : map.get("key"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		int start = (currentPage - 1) * sizePerPage;
		param.put("start", start);
		param.put("spp", sizePerPage);
		return sqlSession.getMapper(StudyDao.class).listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		int naviSize = 10;
		int currentPage = Integer.parseInt(map.get("pg"));
		int sizePerPage = Integer.parseInt(map.get("spp"));
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(StudyDao.class).getTotalCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public StudyDto getArticle(int articleno) throws Exception {
		return sqlSession.getMapper(StudyDao.class).getArticle(articleno);
		//return guestBookDao.getArticle(articleno);
	}

	@Override
	public void modifyArticle(StudyDto guestBookDto) throws Exception {
		sqlSession.getMapper(StudyDao.class).modifyArticle(guestBookDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleno) throws Exception {
		sqlSession.getMapper(StudyDao.class).deleteArticle(articleno);
	}

	@Override
	public void joinStudy(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", map.get("name"));
		param.put("id", map.get("id"));
		sqlSession.getMapper(StudyDao.class).joinStudy(map);
	}

}
