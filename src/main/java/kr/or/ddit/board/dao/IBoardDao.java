package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.Board;

public interface IBoardDao {
	
	/**
	* Method : getAllBoardList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 전체 Board List
	*/
	public List<Board> getAllBoardList(SqlSession sqlSession);
	
	/**
	* Method : getBoardStatusList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param boardStatus
	* @return
	* Method 설명 : 게시판 사용여부에 따른 Board List
	*/
	public List<Board> getBoardStatusList(SqlSession sqlSession, int boardStatus);
	
	/**
	* Method : insertBoard
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param board
	* @return
	* Method 설명 : 게시판 생성
	*/
	public int insertBoard(SqlSession sqlSession, Board board);

	/**
	* Method : updateBoard
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param board
	* @return
	* Method 설명 : 게시판 수정
	*/
	public int updateBoard(SqlSession sqlSession, Board board);

	public Board getBoardInfo(SqlSession sqlSession, int boardNo);
}
