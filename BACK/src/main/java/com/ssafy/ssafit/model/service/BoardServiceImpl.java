package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.BoardDao;
import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardDao boardDao;
	
	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	
	//상세 글 보기
	@Override
	public Board getBoard(int id) {
		return boardDao.selectOne(id);
	}

	
	//목록 가져오기
	@Override
	public List<Board> getList() {
		return boardDao.selectAll();
	}

	
	//글 작성(등록)
	@Override
	public int writeBoard(Board board) {
		return boardDao.insert(board);
	}

	//글 수정
	@Override
	public int modifyBoard(Board board) {
		return boardDao.update(board);
	}

	//글 삭제
	@Override
	public int deleteBoard(int id) {
		return boardDao.delete(id);
	}

	//검색 조건에 맞는 목록 반환
	@Override
	public List<Board> search(SearchCondition condition) {
		return boardDao.search(condition);
	}
	
	




	

}
