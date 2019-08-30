package kr.or.ddit.board.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Board {
	
	private int boardNo;		//게시판번호
	private String userId;		//게시판 등록자
	private String boardNm;		//게시판이름
	private int boardStatus;	//게시판 사용여부
	private Date boardDate;		//게시판 생성 날짜 
	
	
	//데이트타입 포맷 변경
	public String getBoardDate_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(boardDate);
	}
	
	//기본생성자
	public Board() {	}

	//insert용 생성자
	public Board(String userId, String boardNm, int boardStatus) {
		super();
		this.userId = userId;
		this.boardNm = boardNm;
		this.boardStatus = boardStatus;
	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public int getBoardStatus() {
		return boardStatus;
	}
	public void setBoardStatus(int boardStatus) {
		this.boardStatus = boardStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	

}
