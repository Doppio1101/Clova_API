package mc.sn.basic.ai.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


public interface AIController {
	public String nmt(@RequestParam("words") String words,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String stt(@RequestParam("uploadFile") MultipartFile file,@RequestParam("language") String language,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public String clovaOCR(@RequestParam("uploadFile") MultipartFile file);

}
