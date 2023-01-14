package utilities;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class CommonUtils {
	static Integer programID;
	public static HashMap<String, Object> map = new HashMap<String, Object>();

	public String getDateISOformat() {
		Loggerload.info("Generate creationTime and lastModTime - as current time");
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSxxx");
		String output = OffsetDateTime.now(ZoneOffset.UTC).format(f);
		return output;
	}

	public int getSerialNumber() {
		Loggerload.info("Generate random serial number");
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
		Loggerload.debug("Adding program ID to map" + map);
	}

	public void setProgramName(String prmname) {

		if (map.containsKey("prgrmName")) {
			map.put("prgrmName1", prmname);
		} else {
			map.put("prgrmName", prmname);
		}
		Loggerload.debug("Adding program Name to map " + map);
	}

	public void setProgramDesc(String programdesc) {
		if (map.containsKey("programDescription")) {
			map.put("programDescription1", programdesc);
		} else {
			map.put("programDescription", programdesc);
		}
		Loggerload.debug("Adding program description to map " + map);
	}

	public void setProgramStatus(String programstatus) {
		if (map.containsKey("programStatus")) {
			map.put("programStatus1", programstatus);
		} else {
			map.put("programStatus", programstatus);
		}
		Loggerload.debug("Adding program status to map " + map);
	}

	public Object getKeyValue(String key) {
		Loggerload.debug("Return Key value from map " + map);
		return map.get(key);
		
	}

	public String getYear() {
		DateFormat df = new SimpleDateFormat("yy");
		String year = df.format(Calendar.getInstance().getTime());
		Loggerload.debug("Return Just the year, with last 2 digits" + year);
		return year;
	}

	public String getMonth() {
		String mon = new DateFormatSymbols().getShortMonths()[0];
		Loggerload.debug("Return short month name for the specific month" + mon);
		return mon;
	}
	
	public String getProgramName() {
		int r = (int) (Math.random() * 4);
		String name = new String[] { "Selenium", "Java", "Python", "SQL" }[r];
		Loggerload.debug("Return random name from the list as program name" + name);
		return name;
	}
	public String constructPrgmName() {
		//Program name pattern : Jan23-TeamName-ProgramName-serial number
		String name = this.getMonth()+this.getYear()+"-NinjaTrainees-"+this.getProgramName()+"-"+this.getSerialNumber();
		Loggerload.debug("construct the Program name - as per the pattern" + name);
		return name;

	}

}
