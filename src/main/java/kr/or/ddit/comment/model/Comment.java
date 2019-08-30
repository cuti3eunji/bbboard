package kr.or.ddit.comment.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	private int commentNo;			//댓글 번호
	private int postNo;				//게시글 번호
	private String userId;			//댓글 작성자
	private String commentContent;	//댓글 내용
	private Date commentDate;		//댓글 작성 날짜
	private int commentStatus;		//댓글 삭제 여부
	
	//데이트타입 포맷 변경
	public String getCommentDate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(commentDate);
	}
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getPostNo() {
		return postNo;
	}
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCommentStatus() {
		return commentStatus;
	}
	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}
	
	
	
	
}
