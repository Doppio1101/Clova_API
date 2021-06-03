package mc.sn.basic.ai.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import mc.sn.basic.ai.service.AIService;
/**
 * Handles requests for the application home page.
 */
@Controller("aiController")
public class AIControllerImpl implements AIController {
	
	private static final Logger logger = LoggerFactory.getLogger(AIControllerImpl.class);
	
	@Autowired
	private AIService aiService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Override
	@RequestMapping(value="/nmt1" ,method = RequestMethod.GET)
	@ResponseBody//JSON����� �� �Ŷ� String���� ��ȯ����
	public String nmt(@RequestParam("words") String words, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		System.out.println(words);
		
		String result = aiService.aiTest(words);

		return result;
	}	
	@Override
	@RequestMapping(value="/clovaSTT" ,method = RequestMethod.POST, produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String stt(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String result = "";
		
		try {
			//1. ���� ���� ��� ���� : ���� ���� �Ǵ� ��ġ (������Ʈ �ܺο� ����)
			  String uploadPath =  "c:/ai/";
			  
			  //2.���� ���� �̸�
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. ���� ���� 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  System.out.println(filePathName);
			  
			  //4. ������ ����
			  file.transferTo(file1);
			  
			  result = aiService.clovaSpeechToText(filePathName, language);
			  System.out.println("ai "+result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	@RequestMapping(value="/clovaOCR",method = RequestMethod.POST)
	@ResponseBody
	public String clovaOCR(@RequestParam("uploadFile") MultipartFile file) {
		String result = "";
		
		try {
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);
			  
			  result = aiService.clovaOCRService(filePathName);
			  System.out.println("result = > "+result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
