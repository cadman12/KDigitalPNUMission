package edu.pnu.dao.log;

import java.io.File;
import java.io.FileWriter;

//@Repository
public class LogDaoFileImpl implements LogDao {
	
	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		try {
			File file = new File("log.txt");
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + "," + sqlstring + "," + success + "\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
