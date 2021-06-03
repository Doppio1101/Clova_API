package mc.sn.basic.ai.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;


public interface AIService {
	 public String aiTest(String words) throws DataAccessException;
	 public String clovaSpeechToText(String filePathName,String language);
	public String clovaOCRService(String filePathName);
}
