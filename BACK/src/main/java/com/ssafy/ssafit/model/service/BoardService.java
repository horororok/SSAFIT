package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Board;
import com.ssafy.ssafit.model.dto.SearchCondition;

public interface BoardService {

	//전체 목록 반환
	List<Board> getList();
	
	//글 하나 반환 (상세보기)
	Board getBoard(int id);

	//글 등록 (행의 변환 개수 반환 (잘 등록되었으면 1, 아니면 0)
	int writeBoard(Board board);

	//글 수정
	int modifyBoard(Board board);
	
	//글 삭제
	int deleteBoard(int id);
	
	//검색조건에 맞는 글만 반환
	List<Board> search(SearchCondition condition);
	
}
