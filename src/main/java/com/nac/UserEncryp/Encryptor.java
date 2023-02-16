package com.nac.UserEncryp;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Encryptor {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static final String USER_ID = "NB69080001";
	private static final String SHORT_NAME = "Northernarccap";
	//private static final String SHORT_NAME = "IFMR Capital";
	private static final String PSWD = "J552H";
	DecimalFormat decimalFormat = new DecimalFormat("0");

	public void encrypt(List<ConsumerReport> report, String recordedDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		StringBuilder record = new StringBuilder();

		// Header portion

		String head = "";
		head = "TUDF12";
		String spaces = "";
		for (int i = USER_ID.length(); i < 30; i++) {
			spaces = spaces + " ";
		}
		head = head + USER_ID + spaces; // length 30
		spaces = "";
		for (int i = SHORT_NAME.length(); i < 16; i++) {
			spaces = spaces + " ";
		}
		head = head + SHORT_NAME + spaces; // length 16
		head = head + "01"; // cycle identification
		head = head + recordedDate;
		spaces = "";
		for (int i = PSWD.length(); i < 30; i++) {
			spaces = spaces + " ";
		}
		head = head + PSWD + spaces; // length 30
		head = head + "L"; // specify auth method
		head = head + "00000"; // future use
		spaces = "";
		for (int i = 0; i < 48; i++) {
			spaces = spaces + " ";
		}
		head = head + spaces; // member data
		record.append(head);
		// Header portion

		for (ConsumerReport ele : report) {

			String str = "";

			if (ele.getConsumerName().length() <= 26) {
				str = str + "PN03N0101" + getLength(ele.getConsumerName()) + ele.getConsumerName(); //////
			} else if (ele.getConsumerName().length() > 26 && ele.getConsumerName().length() <= 52) {
				if (ele.getConsumerName().indexOf(" ") != -1) {
					String[] name = ele.getConsumerName().split(" ");
					String first = "";
					String last = "";

					if (name[0] != null) {
						first = name[0];
					}

					if (name[1] != null) {
						last = name[1];
					}

					str = "PN03N0101" + getLength(first) + first + "02" + getLength(last) + last;
				}

			} else {

				if (ele.getConsumerName().indexOf(" ") != -1) {
					String[] name = ele.getConsumerName().split(" ");
					String first = "";
					String mid = "";
					String last = "";

					if (name[0] != null) {
						first = name[0];
					}

					if (name[1] != null) {
						mid = name[1];
					}

					if (name[1] != null) {
						last = name[1];
					}

					str = "PN03N0101" + getLength(first) + first + "02" + getLength(mid) + mid + "03" + getLength(last)
							+ last;
				}

			}

			str = str + "0708" + ele.getDateOfBirth();
			str = str + "0801" + ele.getGender();
			if (ele.getIncomeTaxIdNumber() != null && !ele.getIncomeTaxIdNumber().trim().equals("null")) {
				str = str + "ID03I01010201" + "02" + getLength(ele.getIncomeTaxIdNumber()) + ele.getIncomeTaxIdNumber();
			} else if (ele.getPassportNumber() != null && !ele.getPassportNumber().trim().equals("null")) {
				str = str + "ID03I01010202" + "02" + getLength(ele.getPassportNumber()) + ele.getPassportNumber();
			} else if (ele.getVoterIDNumber() != null && !ele.getVoterIDNumber().trim().equals("null")) {
				str = str + "ID03I01010203" + "02" + getLength(ele.getVoterIDNumber()) + ele.getVoterIDNumber();
			} else if (ele.getDrivingLicenseNumber() != null && !ele.getDrivingLicenseNumber().trim().equals("null")) {
				str = str + "ID03I01010204" + "02" + getLength(ele.getDrivingLicenseNumber())
						+ ele.getDrivingLicenseNumber();
			} else if (ele.getRationCardNumber() != null && !ele.getRationCardNumber().trim().equals("null")) {
				str = str + "ID03I01010205" + "02" + getLength(ele.getRationCardNumber()) + ele.getRationCardNumber();
			} else if (ele.getUniversalIdNumber() != null && !ele.getUniversalIdNumber().trim().equals("null")) {
				str = str + "ID03I01010206" + "02" + getLength(ele.getUniversalIdNumber()) + ele.getUniversalIdNumber();
			} else {
				str = "";
				continue;
			}

			if (ele.getTelephone() != null && !ele.getTelephone().trim().equals("null"))
				str = str + "PT03T0101" + getLength(ele.getTelephone()) + ele.getTelephone() + "030201";

			String address = ele.getAddressLine1().replace(", ", " ");
			address = address.replace(",", " ");

			if (address.length() <= 40) {
				str = str + "PA03A0101" + getLength(address) + address;
			} else if (address.length() > 40 && address.length() <= 80) {
				String first = "";
				String sec = "";
				String third = "";
				String[] addr = address.split(" ");
				int len = 0;
				int end = 0;
				for (String ad : addr) {
					if (len > 30) {
						int len1 = (sec.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((sec.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							sec = sec + ad.substring(0, end);
							third = third + ad.substring(end);
						} else {
							sec = sec + ad;
						}
					} else {
						int len1 = (first.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((first.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							first = first + ad.substring(0, end);
							sec = sec + ad.substring(end);
						} else {
							first = first + ad;
						}
					}
					len = len + ad.length();

				}
				len = 0;
				str = str + "PA03A0101" + getLength(first) + first + "02" + getLength(sec) + sec + "03"
						+ getLength(third) + third;

			} else if (address.length() > 80 && address.length() <= 120) {
				String first = "";
				String sec = "";
				String third = "";
				String[] addr = address.split(" ");
				int len = 0;
				int end = 0;
				for (String ad : addr) {
					if (len >= 30 && len < 70) {
						int len2 = ((sec.length() + 1) + (ad.length()));
						if (len2 > 40) {
							for (int i = 0; i < ad.length(); i++) {
								if (((sec.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							sec = sec + ad.substring(0, end);
							third = third + ad.substring(end);
						} else {
							sec = sec + ad;
						}
					} else if (len < 30) {
						int len1 = (first.length()) + (ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((first.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							first = first + ad.substring(0, end);
							sec = sec + ad.substring(end);
						} else {
							first = first + ad;
						}
					} else {
						third = third + ad;
					}
					len = len + ad.length();

				}
				len = 0;
				str = str + "PA03A0101" + getLength(first) + first + "02" + getLength(sec) + sec + "03"
						+ getLength(third) + third;

			} else if (address.length() > 120 && address.length() <= 160) {

				String first = "";
				String sec = "";
				String third = "";
				String four = "";
				String[] addr = address.split(" ");
				int len = 0;
				int end = 0;
				for (String ad : addr) {
					if (len > 30 && len < 70) {
						int len2 = (sec.length() + ad.length());
						if (len2 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((sec.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							sec = sec + ad.substring(0, end);
							third = third + ad.substring(end);
						} else {
							sec = sec + ad;
						}
					} else if (len < 30) {
						int len1 = (first.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((first.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							first = first + ad.substring(0, end);
							sec = sec + ad.substring(end);
						} else {
							first = first + ad;
						}
					} else if (len > 70 && len < 110) {
						int len1 = (third.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((third.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							third = third + ad.substring(0, end);
							four = four + ad.substring(end);
						} else {
							third = third + ad;
						}
					} else if (len > 110) {
						four = four + ad;
					}
					len = len + ad.length();

				}
				/*
				 * System.out.println("first"+first.length());
				 * System.out.println("sec"+sec.length());
				 * System.out.println("third"+third.length());
				 * System.out.println("four"+four.length());
				 */

				len = 0;
				str = str + "PA03A0101" + getLength(first) + first + "02" + getLength(sec) + sec + "03"
						+ getLength(third) + third + "04" + getLength(four) + four;
			}

			else {

				String first = "";
				String sec = "";
				String third = "";
				String four = "";
				String five = "";
				String[] addr = address.split(" ");
				int len = 0;
				int end = 0;
				for (String ad : addr) {
					if (len > 30 && len < 70) {
						int len2 = (sec.length() + ad.length());
						if (len2 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((sec.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							sec = sec + ad.substring(0, end);
							third = third + ad.substring(end);
						} else {
							sec = sec + ad;
						}
					} else if (len < 30) {
						int len1 = (first.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((first.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							first = first + ad.substring(0, end);
							sec = sec + ad.substring(end);
						} else {
							first = first + ad;
						}
					} else if (len > 70 && len < 110) {
						int len1 = (third.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((third.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							third = third + ad.substring(0, end);
							four = four + ad.substring(end);
						} else {
							third = third + ad;
						}
					} else if (len > 110 && len < 150) {
						int len1 = (four.length() + ad.length());
						if (len1 > 40) {
							for (int i = 1; i < ad.length(); i++) {
								if (((four.length() + 1) + i) > 40) {
									end = i;
									break;
								}
							}
							four = four + ad.substring(0, end);
							five = five + ad.substring(end);
						} else {
							four = four + ad;
						}
					}
					len = len + ad.length();

				}
				/*
				 * System.out.println("first"+first.length());
				 * System.out.println("sec"+sec.length());
				 * System.out.println("third"+third.length());
				 * System.out.println("four"+four.length());
				 */

				len = 0;
				str = str + "PA03A0101" + getLength(first) + first + "02" + getLength(sec) + sec + "03"
						+ getLength(third) + third + "04" + getLength(four) + four + "05" + getLength(five) + five;

			}

			str = str + "0602" + ele.getStateCode1() + "07" + getLength(ele.getPinCode1()) + ele.getPinCode1();

			if (!str.contains("PA")) {
				System.out.println("@@@@");
			}
			// Account info ---

			str = str + "TL04T00101" + getLength(ele.getCurrentOrNewMemberCode()) + ele.getCurrentOrNewMemberCode();
			if (ele.getCurrentOrNewMemberShortName() != null
					&& !ele.getCurrentOrNewMemberShortName().trim().equals("null"))
				str = str + "02" + getLength(ele.getCurrentOrNewMemberShortName())
						+ ele.getCurrentOrNewMemberShortName();
			if (ele.getCurrentOrNewAccountNumber() != null && !ele.getCurrentOrNewAccountNumber().trim().equals("null"))
				str = str + "03" + getLength(ele.getCurrentOrNewAccountNumber()) + ele.getCurrentOrNewAccountNumber();
			if (ele.getAccountType() != null && !ele.getAccountType().trim().equals("null"))
				str = str + "0402" + ele.getAccountType();
			if (ele.getOwnershipIndicator() != null && !ele.getOwnershipIndicator().trim().equals("null"))
				str = str + "0501" + ele.getOwnershipIndicator();
			if (ele.getDateOpened() != null && !ele.getDateOpened().trim().equals("null"))
				str = str + "08" + getLength(ele.getDateOpened()) + ele.getDateOpened();
			if (ele.getDateOfLastPayment() != null && !ele.getDateOfLastPayment().trim().equals("null"))
				str = str + "09" + getLength(ele.getDateOfLastPayment()) + ele.getDateOfLastPayment();
			if (ele.getDateClosed() != null && !ele.getDateClosed().trim().equals("null"))
				str = str + "10" + getLength(ele.getDateClosed()) + ele.getDateClosed();
			if (ele.getDateReported() != null && !ele.getDateReported().trim().equals("null"))
				str = str + "11" + getLength(ele.getDateReported()) + ele.getDateReported();
			if (ele.getHighCreditAmount() != null && !ele.getHighCreditAmount().trim().equals("null"))
				str = str + "12" + getLength(getAsWholeNumber(ele.getHighCreditAmount()))
						+ getAsWholeNumber(ele.getHighCreditAmount());
			if (ele.getCurrentBalance() != null && !ele.getCurrentBalance().trim().equals("null"))
				str = str + "13" + getLength(getAsWholeNumber(ele.getCurrentBalance()))
						+ getAsWholeNumber(ele.getCurrentBalance());

			if (ele.getAmountOverdue() != null && !ele.getAmountOverdue().trim().equals("null"))
				str = str + "14" + getLength(getAsWholeNumber(ele.getAmountOverdue()))
						+ getAsWholeNumber(ele.getAmountOverdue());
			if (ele.getNumberOfDaysPastDue() != null && !ele.getNumberOfDaysPastDue().trim().equals("null"))
				str = str + "15" + getLength(getAsWholeNumber(ele.getNumberOfDaysPastDue()))
						+ getAsWholeNumber(ele.getNumberOfDaysPastDue());
			if (ele.getRateOfInterest() != null && !ele.getRateOfInterest().trim().equals("null"))
				str = str + "38" + getLength(getAsWholeNumber(ele.getRateOfInterest()))
						+ getAsWholeNumber(ele.getRateOfInterest());
			if (ele.getRepaymentTenure() != null && !ele.getRepaymentTenure().trim().equals("null"))
				str = str + "39" + getLength(ele.getRepaymentTenure()) + ele.getRepaymentTenure();
			if (ele.getEmiAmount() != null && !ele.getEmiAmount().trim().equals("null"))
				str = str + "40" + getLength(ele.getEmiAmount()) + ele.getEmiAmount();
			str = str + "ES02**";
			record.append(str);

		}
		record.append("TRLR");
		File outFile = null;
		Date date = new Date();
		File downloads = new File(".");
		outFile = new File(downloads, "TUDF" + "-" + dateFormat.format(date) + ".txt");
		try (FileWriter outputfile = new FileWriter(outFile)) {

			outputfile.write(record.toString());
		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.info(ex.toString());
		}

		if (outFile != null) {

			try {
				Desktop.getDesktop().open(outFile);
				logger.info("Report Generated Successfully");
			} catch (IOException e) {
				// e.printStackTrace();
				logger.info(e.toString());
			}
		}

		AppGUI.isProcessing = false;
		AppGUI.jProgressBar1.setVisible(false);

	}

	private String getLength(String value) {
		return value.length() < 10 ? ("0" + value.length()) : String.valueOf(value.length());
	}

	private String getAsWholeNumber(String value) {
		// return value != null && value.contains(".")? value.split(".")[0] : value;
		// return value != null ?
		// String.valueOf(Integer.parseInt(Double.valueOf(value))): value;
		return value != null ? String.valueOf(decimalFormat.format(Double.valueOf(value))) : value;
	}
}
