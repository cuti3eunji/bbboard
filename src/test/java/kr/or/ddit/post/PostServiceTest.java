package kr.or.ddit.post;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostService;

public class PostServiceTest {
	private IPostService postService;
	
	@Before
	public void setup() {
		postService = new PostService();
	}


	@Test
	public void getUserPagingListTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PAGE", 1);
		map.put("PAGESIZE", 10);
		map.put("BOARDNO", 1);
		
		/***When***/
		List<Post> list = postService.getPostPagingList(map);
		/***Then***/
		assertEquals(10, list.size());
	}
	
	@Test
	public void getPostTotalCntTest() {
		/***Given***/
		int boardNo = 1;

		/***When***/
		int res = postService.getPostTotalCnt(boardNo);

		/***Then***/
		assertEquals(39, res);
	}
	
	
	@Test
	public void getPostDetailTest() {
		/***Given***/
		int postNo = 5;

		/***When***/
		Post post = postService.getPostDetail(postNo);

		/***Then***/
		assertEquals("cony", post.getUserId());
	}
	
	
	@Test
	public void insertPostTest() {
		/***Given***/
		Post post = new Post();
		post.setBoardNo(2);
		post.setParent_postNo(3);
		post.setPostTitle("테스트타이틀");
		post.setPostContent("테스트내용");
		post.setUserId("sally");
		
		/***When***/
		int insertCnt = postService.insertPost(post);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	
	@Test
	public void updatePostTest() {
		/***Given***/
		Post post = new Post();
		post.setPostTitle("test");
		post.setPostContent("testtt");
		post.setPostNo(5);

		/***When***/
		int updateCnt = postService.updatePost(post);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void deletePostTest() {
		/***Given***/
		int postNo = 53;

		/***When***/
		int updateCnt = postService.deletePost(postNo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
}
