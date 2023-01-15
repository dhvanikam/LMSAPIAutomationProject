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
	static Integer batchID;
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

	public static void setProgramID(Integer prm) {

		if (map.containsKey("prgrmID")) {
			map.put("prgrmID1", prm);
		} else {
			map.put("prgrmID", prm);
		}
		Loggerload.debug("Adding program ID to map" + map);
	}

	public static void setProgramName(String prmname) {

		if (map.containsKey("prgrmName")) {
			map.put("prgrmName1", prmname);
		} else {
			map.put("prgrmName", prmname);
		}
		Loggerload.debug("Adding program Name to map " + map);
	}

	public static void setProgramDesc(String programdesc) {
		if (map.containsKey("programDescription")) {
			map.put("programDescription1", programdesc);
		} else {
			map.put("programDescription", programdesc);
		}
		Loggerload.debug("Adding program description to map " + map);
	}

	public static void setProgramStatus(String programstatus) {
		if (map.containsKey("programStatus")) {
			map.put("programStatus1", programstatus);
		} else {
			map.put("programStatus", programstatus);
		}
		Loggerload.debug("Adding program status to map " + map);
	}

	public static Object getKeyValue(String key) {
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
//Batch
	
	public void setBatchID(Integer batch) {

		if (map.containsKey("batchID")) {
			map.put("batchID1", batch);
		} else {
			map.put("batchID", batch);
		}
		Loggerload.debug("Adding batch ID to map " + map);
	}
	
	public void setBatchName(String batchname) {

		if (map.containsKey("batchName")) {
			map.put("batchName1", batchname);
		} else {
			map.put("batchName", batchname);
		}
		Loggerload.debug("Adding batch Name to map " + map);
	}
	
	public void setBatchDescription(String batchdesc) {
		if (map.containsKey("batchDescription")) {
			map.put("batchDescription1", batchdesc);
		} else {
			map.put("batchDescription", batchdesc);
		}
		Loggerload.debug("Adding batch description to map " + map);
	}
	
	public void setBatchStatus(String batchstatus) {
		if (map.containsKey("batchStatus")) {
			map.put("batchStatus1", batchstatus);
		} else {
			map.put("batchStatus", batchstatus);
		}
		Loggerload.debug("Adding program status to map " + map);
	}
	
	public void setBatchNoOfClasses(Integer batchnoofclass) {

		if (map.containsKey("BatchNoOfClasses")) {
			map.put("BatchNoOfClasses1", batchnoofclass);
		} else {
			map.put("BatchNoOfClasses", batchnoofclass);
		}
		Loggerload.debug("Adding program ID to map" + map);
	}
	
	public int getNoOfClasses() {
		Loggerload.info("Generate random class number for batch");
		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		return randomnum;
	}
	
	public int getBatchNameSeries() {
		Loggerload.info("Generate random class number for batch");
		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		return randomnum;
	}
	public String getBatchName() {
		int r = (int) (Math.random() * 4);
		String name = new String[] { "SDET", "SMPO", "DA", "SalesForce" }[r];
		Loggerload.debug("Return random name from the list as batch name" + name);
		return name;
	}
	
	public String constructbatchName() {
		//Batch name pattern : Jan23-TeamName-ProgramName-BatchName-serialnumber
		String name = this.getMonth()+this.getYear()+"-NinjaTrainees-"+this.getProgramName()+"-"+this.getBatchName()+this.getBatchNameSeries()+"-"+this.getSerialNumber();
		Loggerload.debug("construct the Batch name - as per the pattern" + name);
		return name;
	}
}
