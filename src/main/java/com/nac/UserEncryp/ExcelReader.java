package com.nac.UserEncryp;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private File file;

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public ExcelReader(File file) {
		this.file = file;
	}

	public List<ConsumerReport> read(String recordedDate) throws Exception {

		List<ConsumerReport> bsList = new ArrayList<ConsumerReport>();
		String extension = null;
		Iterator<Row> rowIterator = null;
		HSSFWorkbook hssfWorkbook = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			Path filePath = file.toPath();
			BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
			FileTime ft = attr.creationTime();
			FileInputStream fileStream = new FileInputStream(this.file);
			extension = ExcelUtil.getExtension(file.getAbsolutePath());
			if (extension.equals("xls")) {

				hssfWorkbook = new HSSFWorkbook(fileStream);
				HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
				rowIterator = sheet.iterator();
			} else {

				xssfWorkbook = new XSSFWorkbook(fileStream);
				XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
				rowIterator = sheet.iterator();
			}
			try {
				while (rowIterator.hasNext()) {

					Row row = rowIterator.next();
					ConsumerReport report = new ConsumerReport();

					// System.out.println(ExcelUtil.cellType(row.getCell(0)));
					if (row.getRowNum() >= 1) {

						List<Object> values = convertRowToObjectList(true, row.getLastCellNum(), row).stream()
								.collect(Collectors.toList());

						report.setConsumerName(String.valueOf(values.get(0)));
						report.setDateOfBirth(String.valueOf(values.get(1)));
						report.setGender(String.valueOf(values.get(2)));
						report.setIncomeTaxIdNumber(String.valueOf(values.get(3)));
						report.setPassportNumber(String.valueOf(values.get(4)));
						report.setPassportIssueDate(String.valueOf(values.get(5)));
						report.setPassportExpiryDate(String.valueOf(values.get(6)));
						report.setVoterIDNumber(String.valueOf(values.get(7)));
						report.setDrivingLicenseNumber(String.valueOf(values.get(8)));
						report.setDrivingLicenceIssueDate(String.valueOf(values.get(9)));
						report.setDrivingLicenceExpiryDate(String.valueOf(values.get(10)));
						report.setRationCardNumber(String.valueOf(values.get(11)));
						report.setUniversalIdNumber(String.valueOf(values.get(12)));
						report.setAdditionalId1(String.valueOf(values.get(13)));
						report.setAdditionalId2(String.valueOf(values.get(14)));
						report.setTelephone(String.valueOf(values.get(15)));
						report.setTelephoneResidence(String.valueOf(values.get(16)));
						report.setTelephoneOffice(String.valueOf(values.get(17)));
						report.setExtensionOffice(String.valueOf(values.get(18)));
						report.setTelephoneOther(String.valueOf(values.get(19)));
						report.setExtentionOther(String.valueOf(values.get(20)));
						report.setEmailId1(String.valueOf(values.get(21)));
						report.setEmailId2(String.valueOf(values.get(22)));
						report.setAddressLine1(String.valueOf(values.get(23)));
						report.setStateCode1(String.valueOf(values.get(24)));
						report.setPinCode1(String.valueOf(values.get(25)));
						report.setAddressCategory1(String.valueOf(values.get(26)));
						report.setResidenceCode1(String.valueOf(values.get(27)));
						report.setAddressLine2(String.valueOf(values.get(28)));
						report.setStateCode2(String.valueOf(values.get(29)));
						report.setPinCode2(String.valueOf(values.get(30)));
						report.setAddressCategory2(String.valueOf(values.get(31)));
						report.setResidenceCode2(String.valueOf(values.get(32)));
						report.setCurrentOrNewMemberCode(String.valueOf(values.get(33)));
						report.setCurrentOrNewMemberShortName(String.valueOf(values.get(34)));
						report.setCurrentOrNewAccountNumber(String.valueOf(values.get(35)));
						report.setAccountType(String.valueOf(values.get(36)));
						report.setOwnershipIndicator(String.valueOf(values.get(37)));
						report.setDateOpened(String.valueOf(values.get(38)));
						report.setDateOfLastPayment(String.valueOf(values.get(39)));
						report.setDateClosed(String.valueOf(values.get(40)));
						report.setDateReported(String.valueOf(values.get(41)));
						report.setHighCreditAmount(String.valueOf(values.get(42)));
						report.setCurrentBalance(String.valueOf(values.get(43)));

						report.setAmountOverdue(String.valueOf(values.get(44)));
						report.setNumberOfDaysPastDue(String.valueOf(values.get(45)));
						report.setOldMbrCode(String.valueOf(values.get(46)));
						report.setOldMbrShortName(String.valueOf(values.get(47)));
						report.setOldAccNo(String.valueOf(values.get(48)));
						report.setOldAccType(String.valueOf(values.get(49)));
						report.setOldOwnershipIndicator(String.valueOf(values.get(50)));
						report.setSuitFiledDefault(String.valueOf(values.get(51)));
						report.setSettledStatus(String.valueOf(values.get(52)));
						report.setAssetClassification(String.valueOf(values.get(53)));
						report.setValueOfCollateral(String.valueOf(values.get(54)));
						report.setTypeOfCollateral(String.valueOf(values.get(55)));
						report.setCreditLimit(String.valueOf(values.get(56)));
						report.setCashLimit(String.valueOf(values.get(57)));

						report.setRateOfInterest(String.valueOf(values.get(58)));
						report.setRepaymentTenure(String.valueOf(values.get(59)));
						report.setEmiAmount(String.valueOf(values.get(60)));
						if (values.size() > 61)
							report.setWrittenOffAmount(String.valueOf(values.get(61)));
						if (values.size() > 62)
							report.setWrittenoffPrincipalAmount(String.valueOf(values.get(62)));
						if (values.size() > 63)
							report.setSettlementAmt(String.valueOf(values.get(63)));
						if (values.size() > 64)
							report.setPaymentFrequency(String.valueOf(values.get(64)));
						if (values.size() > 65)
							report.setActualPaymentAmt(String.valueOf(values.get(65)));
						if (values.size() > 66)
							report.setOccupationCode(String.valueOf(values.get(66)));
						if (values.size() > 67)
							report.setIncome(String.valueOf(values.get(67)));
						if (values.size() > 68)
							report.setNetOrGrossIncomeIndicator(String.valueOf(values.get(68)));
						if (values.size() > 69)
							report.setMonthlyOrAnnualIncomeIndicator(String.valueOf(values.get(69)));

					}

					if (report != null && report.getConsumerName() != null)
						bsList.add(report);

				}
			} catch (Exception ex) {
				// ex.printStackTrace();
				logger.info(ex.toString());
			}

			fileStream.close();
			if (xssfWorkbook != null) {
				xssfWorkbook.close();
			}

			if (hssfWorkbook != null) {
				hssfWorkbook.close();
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.info(e.toString());
			throw e;
		}

		Encryptor ey = new Encryptor();
		ey.encrypt(bsList, recordedDate);

		return bsList;

	}

	public List<Object> convertRowToObjectList(Boolean isRS, int maxDataCount, Row row) {
		List<Object> singleRows = new ArrayList<>();
		// For each row, iterate through all the columns
		for (int cn = 0; cn < maxDataCount; cn++) {
			Cell cell = row.getCell(cn, MissingCellPolicy.CREATE_NULL_AS_BLANK);

			switch (cell.getCellType()) {

			case NUMERIC:

				if (DateUtil.isCellDateFormatted(cell)) {

					singleRows.add(new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()));

				} else {

					Double e1Val = cell.getNumericCellValue();
					if (e1Val % 1 == 0 && e1Val != 0)

					{

						BigDecimal bd = new BigDecimal(e1Val.toString());

						long lonVal = bd.longValue();

						singleRows.add(lonVal);

					} else

						singleRows.add(e1Val);
				}
				break;

			case STRING:
				// Checking if some values are not na or blank add those
				// as error
				String cellValue = cell.getStringCellValue();
				if (!Constants.NA.equalsIgnoreCase(cellValue) && !Constants.NULL.equalsIgnoreCase(cellValue)
						&& !Constants.NONE.equalsIgnoreCase(cellValue)
						&& !Constants.N_DOT_A_DOT.equalsIgnoreCase(cellValue)
						&& !Constants.HASH_N_SLASH_A.equalsIgnoreCase(cellValue)
						&& !Constants.N_SLASH_A.equalsIgnoreCase(cellValue))
					singleRows.add(cellValue);
				else {
					singleRows.add(null);
				}
				break;

			case BLANK:
				singleRows.add(null);
				break;

			case ERROR:
				singleRows.add(null);
				break;

			case FORMULA:
				singleRows.add(isRS ? cell.getCellFormula() : null);
				break;

			case BOOLEAN:
				singleRows.add(null);
				break;

			default:
				singleRows.add(cell.getStringCellValue());
			}
		}
		return singleRows;
	}

}
