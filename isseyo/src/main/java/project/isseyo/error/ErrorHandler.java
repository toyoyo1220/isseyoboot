package project.isseyo.error;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorHandler {
	

	public static ArrayList<HashMap<String, Object>> serverError(Object printStackTrace, String message) {
		ArrayList<HashMap<String, Object>> response = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> reponseMap = new HashMap<String, Object>();
		
		reponseMap.put("state", printStackTrace);
		reponseMap.put("message", message);
		reponseMap.put("result", "");
		response.add(reponseMap);
		return response; 
	}

}
