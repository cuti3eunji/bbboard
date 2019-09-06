package kr.or.ddit.comment;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.comment.model.Comment;
import kr.or.ddit.comment.service.CommentService;
import kr.or.ddit.comment.service.ICommentService;

public class CommentServiceTest {
private ICommentService cmtService;
	
	@Before
	public void setup() {
		cmtService = new CommentService();
	}

	@Test
	public void getCmtListTest() {
		/***Given***/
		int postNo = 1;

		/***When***/
		List<Comment> list = cmtService.getCmtList(postNo);

		/***Then***/
		assertEquals(13, list.size());
	}
	
	
	@Test
	public void insertCmtTest() {
		/***Given***/
		Comment cmt = new Comment();
		cmt.setCommentContent("test");
		cmt.setPostNo(4);
		cmt.setUserId("sally");

		/***When***/
		int insertCnt = cmtService.insertCmt(cmt);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteCmtTest() {
		/***Given***/
		int commentNo = 5;

		/***When***/
		int deleteCnt = cmtService.deleteCmt(commentNo);

		/***Then***/
		assertEquals(1, deleteCnt);
		
	}
	

}
