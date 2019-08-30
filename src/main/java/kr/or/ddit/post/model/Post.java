package kr.or.ddit.post.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private int postNo;			//게시글 번호
	private int boardNo;		//게시판 번호
	private int parent_postNo;	//부모 게시글 번호
	private String userId;		//글 작성자
	private String postTitle;	//게시글 제목
	private String postContent;	//게시글 내용
	private Date postDate;	//게시글 작성 날짜
	private int postStatus;		//게시글 삭제 여부
	private int level;	//레벨~
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	//데이트타입 포맷 변경
	public String getPostdate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(postDate);
	}
	
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getParent_postNo() {
		return parent_postNo;
	}
	public void setParent_postNo(int parent_postNo) {
		this.parent_postNo = parent_postNo;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getPostStatus() {
		return postStatus;
	}
	public void setPostStatus(int postStatus) {
		this.postStatus = postStatus;
	}
	
	
}
