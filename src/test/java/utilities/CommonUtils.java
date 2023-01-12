package utilities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CommonUtils {

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
}
