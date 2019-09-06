package kr.or.ddit.afile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.afile.model.AFile;
import kr.or.ddit.afile.service.AfileService;
import kr.or.ddit.afile.service.IAfileService;

public class AfileServiceTest {
	private IAfileService afileService;
	
	@Before
	public void setup() {
		afileService = new AfileService();
	}
	
	
	@Test
	public void getAttachedFileTest() {
		/***Given***/
		int postNo = 1;

		/***When***/
		List<AFile> afile = afileService.getAttachedFile(postNo);

		/***Then***/
		assertEquals(3, afile.size());
	}
	
	@Test
	public void insertFileTest() {
		/***Given***/
		AFile afile = new AFile();
		afile.setFilename("test.png");
		afile.setRealfilename("real");
		afile.setPostNo(52);

		/***When***/
		int insertCnt = afileService.insertFile(afile);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteFileTest() {
		/***Given***/
		int fileNo = 27;

		/***When***/
		int deleteCnt = afileService.deleteFile(fileNo);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
}
