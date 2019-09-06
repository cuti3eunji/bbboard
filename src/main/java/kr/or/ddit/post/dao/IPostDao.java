package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.post.model.Post;

public interface IPostDao {
	/**
	* Method : getPostPagingList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param map
	* @return
	* Method 설명 : 해당 게시판의 게시글을 페이징 처리하여 전체 리스트 조회
	*/
	public List<Post> getPostPagingList(SqlSession sqlSession, Map map);
	
	/**
	* Method : getPostTotalCnt
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 :
	*/
	public int getPostTotalCnt(SqlSession sqlSession, int boardNo);
	
	
	/**
	* Method : getPostDetail
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 게시글조회
	*/
	public Post getPostDetail(SqlSession sqlSession, int postNo); 
	
	
	/**
	* Method : insertPost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 작성
	*/
	public int insertPost(SqlSession sqlSession, Post post);
	
	/**
	* Method : postSeq
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 현재 시퀀스 
	*/
	public int postSeq(SqlSession sqlSession);

	
	/**
	* Method : updatePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	public int updatePost(SqlSession sqlSession, Post post);
	
	
	/**
	* Method : deletePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 게시글 삭제 - 삭제여부 수정
	*/
	public int deletePost(SqlSession sqlSession, int postNo);
	
	
	/**
	* Method : getMyPost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param userId
	* @return
	* Method 설명 : 본인 게시글인지 확인
	*/
	public List<String> getMyPost(SqlSession sqlSession, String userId);
}
