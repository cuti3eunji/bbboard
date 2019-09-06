package kr.or.ddit.comment.service;

import java.util.List;

import kr.or.ddit.comment.model.Comment;

public interface ICommentService {
	/**
	* Method : getCmtList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : postNo를 받아와서 글에 해당하는 댓글을 모두 출력
	*/
	public List<Comment> getCmtList(int postNo);
	
	
	
	public int insertCmt(Comment comm);
	
	public int updateCmt(Comment comm);
	
	public int deleteCmt(int commentNo);
	
	
	
	/**
	* Method : getMyCmt
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param commentNo
	* @return
	* Method 설명 : 본인댓글인지 아이디 받아와서 확인
	*/
	public String getMyCmt(int commentNo);
}
