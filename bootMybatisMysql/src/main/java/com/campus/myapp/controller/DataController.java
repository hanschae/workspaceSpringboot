package com.campus.myapp.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.campus.myapp.service.DataService;
import com.campus.myapp.vo.DataVO;

@RestController
@RequestMapping("/data/*")
public class DataController {
	@Inject
	DataService service;
	
	ModelAndView mav = null;
	// 자료실 목록
	@GetMapping("dataList")
	public ModelAndView dataList() {
		
		mav = new ModelAndView();
		
		// DB조회
		mav.addObject("dataList", service.dataAllSelect());
		mav.setViewName("data/dataList");
		return mav;
	}
	// 자료실 글 등록 폼
	@GetMapping("dataWrite")
	public ModelAndView dataWrite() {
		mav = new ModelAndView();
		mav.setViewName("data/dataWrite");
		return mav;
	}
	// 자료실 글 등록 + 파일 업로드
	@PostMapping("dataWriteOk")		// 파일 업로드 위치, MultipartRequest객체를 구한다.
	public ResponseEntity<String> dataWriteOk(DataVO vo, HttpServletRequest request) {
		// 업로드할 위치의 절대 경로
		String path = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("path = " + path);
		
		// --------------------------------------------------
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		// 파일업로드
		String message = ""; // entity의 컨텐츠내용
		try {
			// 1. DataVO에 제목, 글내용은 request되어 있다.
			
			// 2. 클라이언트 컴퓨터에 있는 파일이 서버로 복사하기 위해서는
			//	  MultipartHttpServletRequest 객체를 request객체에서 타입캐스팅으로 구한다.
			
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
			
			// mr에는 업로드한 파일 수 만큼 MultiparFile객체가 존재한다.
			// MultipartFile객체를 List컬렉션에 담아 리턴해준다.
			List<MultipartFile> files = mr.getFiles("filename");
			
			// 업로드한 파일이 있을 때
			if(files != null) { // if 1
				// 첨부한 파일의 수만큼 반복적으로 업로드를 처리한다.
				int cnt = 0; // 업로드 번호매기기
				for(int i=0; i<files.size(); i++) { // for1
					
					// List에서 MultipartFile객체 얻어오기
					MultipartFile mf = files.get(i);
					
					// 업로드한 실제 파일명
					String orgName = mf.getOriginalFilename();
					System.out.println("원래파일명 : " + orgName);
					
					// 이미 파일명이 존재할 경우 rename
					if(orgName != null && !orgName.equals("")) { // if 2		원래 파일명이 있을때
						File f = new File(path, orgName); // a.gif
						// 파일명이 존재하면 파일명을 변경해야한다.
						if(f.exists()) { // if 3		있으면 true, 없으면 false
							// 파일명 변경
							for(int renameNum=1;;renameNum++) { // for 2
								// a_1.gif
								int dot = orgName.lastIndexOf(".");
								String fileName = orgName.substring(0, dot);
								String extName = orgName.substring(dot+1);
								
								f = new File(path, fileName+"_"+renameNum+"."+extName);
								if(!f.exists()) { // 없으면 다른 파일명을 만들 필요없음
									orgName = f.getName(); // 파일명 + 확장자를 구해줌
									break;
								}
							} // for 2
						} // if 3
						
						// 파일 업로드 실행
						mf.transferTo(f);
						cnt++;
						
						if(cnt==1) vo.setFilename1(f.getName());
						if(cnt==2) vo.setFilename2(f.getName());
					} // if 2
				} // for 1
			} // if 1
			
			// DB등록
			vo.setUserid((String)request.getSession().getAttribute("logId"));
			
			int cnt = service.dataInsert(vo);// 레코드 추가
			if(cnt>0) {
				// 리스트로 이동
				message += "<script>";
				message += "alert('자료실 등록이 완료되었습니다.');";
				message += "location.href='/data/dataList';";
				message += "</script>";
				
			}else { // 등록실패
				throw new Exception();
			}
		}catch(Exception e) {
			// 등록실패
			
			// 파일을 지우고 글등록으로 다시 보내기
			fileDelete(path, vo.getFilename1());
			fileDelete(path, vo.getFilename2());
			
			message += "<script>";
			message += "alert('글등록에 실패하였습니다.');";
			message += "history.go(-1);";
			message += "</script>";
		}
		
		ResponseEntity<String> entity = new ResponseEntity<String>(message, headers, HttpStatus.OK);
		return entity;
	}
	public void fileDelete(String path, String filename) {
		try {
			if(filename!=null) {
				File file = new File(path, filename);
				file.delete();
			}
		} catch (Exception e) {	}
	}
	
	// 다운로드 횟수 수정
	@GetMapping("downCountUpdate")
	public int downCountUpdate(int no) {
		// 다운로드 횟수 업데이트
		service.downCount(no);
		// 새로운 다운로드횟수
		return service.newDownCount(no);
	}
	// 글내용 보기 , 조회수 증가
	@GetMapping("dataView/{no}")
	public ModelAndView dataView(@PathVariable("no") int no) {
		mav = new ModelAndView();
		service.hitCount(no); // 조회수 증가
		mav.addObject("dataVO", service.dataSelect(no)); // 레코드 선택
		mav.setViewName("data/dataView");
		return mav;
	}
	// 글수정 폼
	@GetMapping("dataEditForm/{no}")
	public ModelAndView dataEditForm(@PathVariable("no") int no) {
		mav = new ModelAndView();
		mav.addObject("vo", service.dataSelect(no));
		mav.setViewName("data/dataEditForm");
		return mav;
	}
	// 글 수정 (DB)
	@PostMapping("dataEditFormOk")		// 번호,제목,글내용,삭제파일명				새로업로드될 파일
	public ResponseEntity<String> dataEditFormOk(DataVO vo, HttpServletRequest request){
		// DB에 있는 파일명을 보관한다.
		DataVO dbFile = service.getFilenames(vo.getNo());
		
		// 최종으로 사용 할 파일명을 정리할 컬렉션
		List<String> editFileList = new ArrayList<String>();
		
		// DB에서 선택한 파일을 컬렉션에 담기
		editFileList.add(dbFile.getFilename1());
		if(dbFile.getFilename2()!=null) {
			editFileList.add(dbFile.getFilename2());
		}
		
		// 삭제파일명과 같은 파일을 최종파일을 저장하는 컬렉션에서 제거하기
		if(vo.getDelFile()!=null) {
			for(String del : vo.getDelFile()) {
				editFileList.remove(del);
			}
		}
		// 업로드 할 위치
		String path = request.getServletContext().getRealPath("/upload");
		System.out.println("update.path="+path);
		// 새로 업로드
		// 새로 업로드 한 파일명 보관할 컬렉션
		List<String> newUpload = new ArrayList<String>();
		
		String msg=""; // 스크립트 코드
		try {
			MultipartHttpServletRequest mr = (MultipartHttpServletRequest)request;
			
			List<MultipartFile> newMF = mr.getFiles("filename");
						
			if(newMF!=null) { // 업로드 한 파일이 있으면
				for(int i=0; i<newMF.size(); i++) {
					MultipartFile mf = newMF.get(i);
					String org_filename = mf.getOriginalFilename();
					
					if(org_filename !=null && !org_filename.equals("")) {
						File file = new File(path, org_filename);
						if(file.exists()) {
							for(int j=1;;j++) {
								int p = org_filename.lastIndexOf(".");
								String filenameNoExt = org_filename.substring(0, p);
								String ext = org_filename.substring(p);
								String renameFilename = filenameNoExt+"_"+j+ext;
								file = new File(path, renameFilename);
								if(!file.exists()) {
									org_filename = renameFilename;
									break;
								}
							} // for
						}
						// 업로드
						mf.transferTo(file);
						newUpload.add(file.getName());
						editFileList.add(file.getName());						
					}
				}
			}
			// DB업로드
			// 수정할 파일명이 있는 컬렉션의 값을 filename1, filename2 에 세팅하여야 한다.
			for(int i=0; i<editFileList.size();i++) {
				if(i==0) vo.setFilename1(editFileList.get(0));
				if(i==1) vo.setFilename2(editFileList.get(1));
			}
			
			// 세션에 로그인 한 아이디
			vo.setUserid((String)request.getSession().getAttribute("logId"));
			
			int result = service.dataUpdate(vo);
			
			if(result>0) { // 수정됨
				// 삭제한 파일을 지운다.
				if(vo.getDelFile()!=null) {
					for(String f : vo.getDelFile()) {
						fileDelete(path, f);
					}
				}
				
				// 글 내용보기로 페이지 이동
				msg += "<script>";
				msg += "alert('자료실 글 수정 성공하였습니다.');";
				msg += "location.href='/data/dataView/"+vo.getNo()+"'";
				msg += "</script>";				
				
			}else { // 수정실패
				throw new Exception();
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		
			// update 실패
			// 새로 업로드 된 파일을 지운다.
			for(String f :newUpload) {
				fileDelete(path, f);
			}
			// 수정페이지로 이동 (history)
			msg += "<script>";
			msg += "alert('자료실 글 수정 실패하였습니다.');";
			msg += "history.back();";
			msg += "</script>";
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("text","html",Charset.forName("UTF-8")));
		headers.add("Content-Type", "text/html; charset=UTF-8");
		
		ResponseEntity<String> entity = new ResponseEntity<String>(msg, headers, HttpStatus.OK);
		
		return entity;
	}
	// 자료실 삭제
	@GetMapping("dataDelete/{no}")
	public ModelAndView dataDelete(@PathVariable("no") int no, HttpSession session) {
		String userid = (String)session.getAttribute("logId");
		
		// 업로드 파일명을 DB조회한다.
		DataVO fileVO = service.getFilenames(no);
		
		int result = service.dataDelete(no, userid);
		
		mav = new ModelAndView();
		if(result>0) { // 삭제
			// 레코드가 삭제되었으므로 파일도 삭제한다
			String path = session.getServletContext().getRealPath("/upload");
			fileDelete(path, fileVO.getFilename1());
			fileDelete(path, fileVO.getFilename2());
			
			mav.setViewName("redirect:/data/dataList");
		}else { // 삭제 실패
			mav.setViewName("redirect:/data/dataView/"+no);
			
		}
		return mav;
	}
}
