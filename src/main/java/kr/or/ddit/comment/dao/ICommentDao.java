package kr.or.ddit.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comment.model.Comment;

public interface ICommentDao {
	/**
	* Method : getCmtList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : postNo를 받아와서 글에 해당하는 댓글을 모두 출력
	*/
	public List<Comment> getCmtList(SqlSession sqlSession, int postNo);
	
	
	
	public int insertCmt(SqlSession sqlSession, Comment comm);
	
	public int updateCmt(SqlSession sqlSession, Comment comm);
	
	public int deleteCmt(SqlSession sqlSession, int commentNo);
	
	
	
	/**
	* Method : getMyCmt
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param commentNo
	* @return
	* Method 설명 : 본인댓글인지 아이디 받아와서 확인
	*/
	public String getMyCmt(SqlSession sqlSession, int commentNo);
	
}
