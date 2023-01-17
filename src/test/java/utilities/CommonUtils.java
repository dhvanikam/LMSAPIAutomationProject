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

	public static HashMap<String, Object> map = new HashMap<String, Object>();

	public String getDateISOformat() {
		Loggerload.info("Generate creationTime and lastModTime - as current time");
		DateTimeFormatter f = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSxxx");
		String output = OffsetDateTime.now(ZoneOffset.UTC).format(f);
		return output;
	}

	public int getSerialNumber() {
		
		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		Loggerload.debug("Generate random serial number" +randomnum);
		return randomnum;
	}

	public static void setkeyvalueIntMap(String key,Integer value) {

		if (map.containsKey(key)) {
			map.put(key+"1", value);
		} else {
			map.put(key, value);
		}
		Loggerload.debug("Adding "+key+" and value "+value+"to map");
		Loggerload.debug(map);
	}
	
	public static void setkeyvalueStringMap(String key,String value) {

		if (map.containsKey(key)) {
			map.put(key+"1", value);
		} else {
			map.put(key, value);
		}
		Loggerload.debug("Adding "+key+" and value "+value+"to map" );
		Loggerload.debug(map);
	}

	public static Object getkeyvalueFromMap(String key) {
		if (map.containsKey(key)) {
			return map.get(key);
		}
		else {
			Loggerload.debug(key + "Does not exist");
		}
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
	
	public int getNoOfClasses() {

		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		Loggerload.debug("Generate random class number for batch");
		return randomnum;
	}
	
	public int getBatchNameSeries() {
		
		Random rand = new Random();
		int randomnum = rand.nextInt(100);
		Loggerload.debug("Generate random class number for batch series");
		return randomnum;
	}
	public String getBatchName() {
		int r = (int) (Math.random() * 4);
		String name = new String[] { "SDET", "SMPO", "DA", "SalesForce" }[r];
		Loggerload.debug("Return random name from the list as batch name" + name);
		return name;
	}
	
	public String constructbatchName() {
		/*Batch name pattern : Jan23-TeamName-ProgramName-BatchName-serialnumber*/
		String name = this.getMonth()+this.getYear()+"-NinjaTrainees-"+this.getProgramName()+"-"+this.getBatchName()+this.getBatchNameSeries()+"-"+this.getSerialNumber();
		Loggerload.debug("construct the Batch name - as per the pattern" + name);
		return name;
	}
}
