package utility;

import java.io.File;
import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

public class Parsing {
	
	/**
	 * 
	 * @param file with name and path,hObj HashMap<Object,Object> hObj
	 * @throws Exception
	 */
	 
	public void serialization(HashMap<Object,Object> hObj,File fileName) throws Exception{

		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter().writeValue(fileName, hObj);
	}

	public void deSerialization() {
		
	}

}
