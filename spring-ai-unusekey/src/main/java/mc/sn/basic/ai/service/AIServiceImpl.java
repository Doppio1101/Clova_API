package mc.sn.basic.ai.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("aiService")
@Transactional(propagation = Propagation.REQUIRED)
public class AIServiceImpl implements AIService {
	
	@Override//Translation
	public String aiTest(String words) throws DataAccessException {
		String result = null;
		StringBuffer res = null;
		String clientId = "보안을 위해 삭제";//애플리케이션 클라이언트 아이디값
        String clientSecret = "보안을 위해 삭제";//애플리케이션 클라이언트 시크릿값
        try {
            String text = URLEncoder.encode(words, "UTF-8");
            String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            System.out.println(res.toString());
            result = res.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
		return result;
	}

	@Override //STT
	public String clovaSpeechToText(String filePathName,String language) {
		String result = null;
		String clientId = "보안을 위해 삭제";             // Application Client ID";
	    String clientSecret = "보안을 위해 삭제";     // Application Client Secret";

	    try {
	    	String imgFile = filePathName;
	        File voiceFile = new File(imgFile);

            //String language = "Kor";        // 언어코드( Kor, Jpn, Eng, Chn )
            //language로 선택되기 때문에 삭제
	        String apiURL = "https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
            URL url = new URL(apiURL);

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/octet-stream");
            conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            conn.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(voiceFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            BufferedReader br = null;
            int responseCode = conn.getResponseCode();
            if(responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            String inputLine;

            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                //result = URLEncoder.encode(result, "UTF-8");
                System.out.println(response.toString());
                result = response.toString();
            } else {
                System.out.println("error !!!");
                //br이 null이 될 일이 없다는 것을 주의로 안내
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
		return result;
	 }

	@Override //OCR
	public String clovaOCRService(String filePathName) {
		// TODO Auto-generated method stub
		String apiURL = "보안을 위해 삭제";
		String secretKey = "보안 위해 삭제";
		String imageFile = filePathName;
		String result = null;
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.put(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(imageFile);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			result = response.toString();
			System.out.println(response);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return result;
	}
	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
	IOException {
	StringBuilder sb = new StringBuilder();
	sb.append("--").append(boundary).append("\r\n");
	sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
	sb.append(jsonMessage);
	sb.append("\r\n");

	out.write(sb.toString().getBytes("UTF-8"));
	out.flush();

	if (file != null && file.isFile()) {
		out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
		StringBuilder fileString = new StringBuilder();
		fileString
			.append("Content-Disposition:form-data; name=\"file\"; filename=");
		fileString.append("\"" + file.getName() + "\"\r\n");
		fileString.append("Content-Type: application/octet-stream\r\n\r\n");
		out.write(fileString.toString().getBytes("UTF-8"));
		out.flush();

		try  {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[8192];
			int count;
			while ((count = fis.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			fis.close();
			out.write("\r\n".getBytes());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
	}
	out.flush();
}
}