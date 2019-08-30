package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.Board;

public interface IBoardService {
	/**
	* Method : getAllBoardList
	* 작성자 : 박은지
	* 변경이력 :
	* @return
	* Method 설명 : 전체 Board List
	*/
	public List<Board> getAllBoardList();
	
	/**
	* Method : getBoardStatusList
	* 작성자 : 박은지
	* 변경이력 :
	* @param boardStatus
	* @return
	* Method 설명 : 게시판 사용여부에 따른 Board List
	*/
	public List<Board> getBoardStatusList(int boardStatus);
	
	/**
	* Method : insertBoard
	* 작성자 : 박은지
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 생성
	*/
	public int insertBoard(Board board);

	/**
	* Method : updateBoard
	* 작성자 : 박은지
	* 변경이력 :
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	public int updateBoard(Board board);

}
