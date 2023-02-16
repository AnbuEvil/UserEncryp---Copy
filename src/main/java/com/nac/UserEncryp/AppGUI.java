package com.nac.UserEncryp;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AppGUI extends javax.swing.JFrame {

	// Variables declaration - do not modify
	private javax.swing.JButton browseBtn;
	public static javax.swing.JButton generate;
	private javax.swing.JLabel chooseFileLabel;
	private javax.swing.JTextField fileInput;
	private javax.swing.JPanel jPanel1;
	public static javax.swing.JProgressBar jProgressBar1;
	private javax.swing.JLabel recordDateLabel;
	private String filePath = null;
	private Date selectedDate = null;
	public static boolean isProcessing = false;
	// End of variables declaration
	private Logger logger = Logger.getLogger(this.getClass().getName());

	public AppGUI() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		fileInput = new javax.swing.JTextField();
		browseBtn = new javax.swing.JButton();
		recordDateLabel = new javax.swing.JLabel();
		chooseFileLabel = new javax.swing.JLabel();
		generate = new javax.swing.JButton();
		jProgressBar1 = new javax.swing.JProgressBar();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(97, 209, 226));
		setTitle("TUDF");
		fileInput.setToolTipText("choose file");
		ImageIcon frameIcon;
		frameIcon = loadIcon("/resource/arrow.png");
		setIconImage(frameIcon.getImage());

		browseBtn.setBackground(new java.awt.Color(255, 255, 255));
		browseBtn.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
		browseBtn.setText("Browse");
		browseBtn.setActionCommand("browse");
		browseBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		browseBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				browseBtnActionPerformed(evt);
			}
		});

		UtilDateModel model = new UtilDateModel();
		// model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		final JDatePickerImpl datepicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datepicker.addActionListener(e -> {
			selectedDate = (Date) datepicker.getModel().getValue();
			// System.out.println(selectedDate);
			logger.info(selectedDate.toString());
		});

		recordDateLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		recordDateLabel.setForeground(new java.awt.Color(255, 255, 255));
		recordDateLabel.setText("Recorded Date");

		chooseFileLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		chooseFileLabel.setForeground(new java.awt.Color(255, 255, 255));
		chooseFileLabel.setText("Choose File");

		generate.setBackground(new java.awt.Color(255, 255, 255));
		generate.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
		generate.setText("Generate");
		generate.setActionCommand("Generate");
		generate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		generate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				generateActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(44, 44, 44).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(recordDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(chooseFileLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(datepicker)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(fileInput, javax.swing.GroupLayout.PREFERRED_SIZE, 276,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addComponent(generate, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(50, Short.MAX_VALUE))
				.addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 5,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(42, 42, 42).addComponent(chooseFileLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(fileInput, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(browseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 20,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(17, 17, 17).addComponent(recordDateLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(31, 31, 31).addComponent(generate).addContainerGap(54, Short.MAX_VALUE)));
		jProgressBar1.setVisible(false);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		JFileChooser fileForPerdix = new JFileChooser();
		int returnVal = fileForPerdix.showOpenDialog(jPanel1);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileForPerdix.getSelectedFile();

			if (ExcelUtil.getExtension(file.getAbsolutePath()).equals("xls")
					|| ExcelUtil.getExtension(file.getAbsolutePath()).equals("xlsx")
					|| ExcelUtil.getExtension(file.getAbsolutePath()).equals("csv")) {
				this.filePath = file.getAbsolutePath();
				fileInput.setText(this.filePath);
			} else {

			}
		}

	}

	private void generateActionPerformed(java.awt.event.ActionEvent evt) {

		if (isProcessing == false) {
			generate.setText("Processing...");
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					Processer pro = new Processer(filePath, selectedDate);
					pro.start();
				}
			});

		}
		generate.setText("Generate");
	}

	public ImageIcon loadIcon(String iconName) {

		URL url = getClass().getResource(iconName);
		ImageIcon icon = null;
		if (url == null)
			// System.out.println("Could not find image!");
			logger.info("Could not find image!");
		else {
			icon = new ImageIcon(url);
		}

		return icon;
	}

}

class DateLabelFormatter extends AbstractFormatter {

	static final long serialVersionUID = 1L;
	private static String datePattern = "yyyy-MM-dd";
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

}
