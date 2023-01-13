package utilities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

public class CommonUtils {
	static Integer programID;
	static HashMap<String, Object> map = new HashMap<String, Object>();

	public String getDateISOformat() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSxxx");
		String output = OffsetDateTime.now(ZoneOffset.UTC).format(f);
		System.out.println(output);
		return output;
	}

	public int getRandomint() {
		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		return randomnum;
	}

	public void setProgramID(Integer prm) {

		if (map.containsKey("prgrmID")) {
			map.put("prgrmID1", prm);
		} else {
			map.put("prgrmID", prm);
		}
	}

	public void setProgramName(String prmname) {

		if (map.containsKey("prgrmName")) {
			map.put("prgrmName1", prmname);
		} else {
			map.put("prgrmName", prmname);
		}

	}

	public void setProgramDesc(String programdesc) {
		if (map.containsKey("programDescription")) {
			map.put("programDescription1", programdesc);
		} else {
			map.put("programDescription", programdesc);
		}
	}

	public void setProgramStatus(String programstatus) {
		if (map.containsKey("programStatus")) {
			map.put("programStatus1", programstatus);
		} else {
			map.put("programStatus", programstatus);
		}

	}

	public Object getKeyValue(String key) {
		return map.get(key);

	}

}
