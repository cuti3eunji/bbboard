package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.Post;

public interface IPostService {
	/**
	* Method : getPostPagingList
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 해당 게시판의 게시글을 페이징 처리하여 전체 리스트 조회
	*/
//	public Map<String, Object> getPostPagingList(Map map);

	public List<Post> getPostPagingList(Map map);
	
	/**
	* Method : getPostTotalCnt
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 :
	*/
	public int getPostTotalCnt(int boardNo);
	
	
	/**
	* Method : getPostDetail
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 게시글조회
	*/
	public Post getPostDetail(int postNo); 
	
	
	/**
	* Method : insertPost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 작성
	*/
	public int insertPost(Post post);
	
	/**
	* Method : postSeq
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 현재 시퀀스 
	*/
	public int postSeq();

	
	/**
	* Method : updatePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	public int updatePost(Post post);
	
	
	/**
	* Method : deletePost
	* 작성자 : 박은지
	* 변경이력 :
	* @param sqlSession
	* @param postNo
	* @return
	* Method 설명 : 게시글 삭제 - 삭제여부 수정
	*/
	public int deletePost(int postNo);
	
	
	public List<String> getMyPost(String userId);

	
}
