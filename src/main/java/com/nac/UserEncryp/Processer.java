package com.nac.UserEncryp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Processer extends Thread {

	private String filePath = null;
	private Date selectedDate = null;
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public Processer(String filePath, Date selectedDate) {
		this.filePath = filePath;
		this.selectedDate = selectedDate;
	}

	@Override
	public void run() {

		AppGUI.isProcessing = true;
		AppGUI.jProgressBar1.setVisible(true);
		AppGUI.jProgressBar1.setIndeterminate(true);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		ExcelReader reader = new ExcelReader(new File(this.filePath));
		try {

			String date = dateFormatter.format(this.selectedDate);
			reader.read(date.split("-")[0] + date.split("-")[1] + date.split("-")[2]);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.info(e.toString());

		}
	}
}
