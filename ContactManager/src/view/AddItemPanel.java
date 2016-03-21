package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ItemsDAO;
import entity.Items;
import java.awt.SystemColor;

//添加信息窗口，仅管理员可见（内容与个人信息面板类似）
public class AddItemPanel extends JPanel {
	private JTextField nameField;
	private JTextField sexField;
	private JTextField IDField;
	private JTextField phoneField;
	private JTextField qqField;
	private JTextField ageField;
	private JTextField cityField;
	private JPanel contentPanel;
	private ListPanel listPanel;
	JLabel label;

	/**
	 * Create the panel.
	 */
	public AddItemPanel(ListPanel listPanel) {
		this.listPanel = listPanel;
		contentPanel = this;

		setBounds(0, 0, 282, 529);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 282, 0 };
		gridBagLayout.rowHeights = new int[] { 77, 267, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel title = new JLabel("\u6DFB\u52A0\u8054\u7CFB\u4EBA");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setLayout(null);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		add(mainPanel, gbc_mainPanel);

		JLabel nameLabel = new JLabel("\u59D3\u540D\uFF1A");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameLabel.setBounds(31, 28, 45, 15);
		mainPanel.add(nameLabel);

		JLabel IDLabel = new JLabel("\u5B66\u53F7\uFF1A");
		IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		IDLabel.setBounds(31, 109, 45, 15);
		mainPanel.add(IDLabel);

		JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
		sexLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sexLabel.setBounds(31, 69, 45, 15);
		mainPanel.add(sexLabel);

		JLabel phoneLabel = new JLabel("\u7535\u8BDD\uFF1A");
		phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		phoneLabel.setBounds(31, 150, 45, 15);
		mainPanel.add(phoneLabel);

		JLabel qqLabel = new JLabel("QQ\uFF1A");
		qqLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		qqLabel.setBounds(31, 190, 45, 15);
		mainPanel.add(qqLabel);

		JLabel ageLabel = new JLabel("\u5E74\u9F84\uFF1A");
		ageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ageLabel.setBounds(31, 231, 45, 15);
		mainPanel.add(ageLabel);

		JLabel cityLabel = new JLabel("\u57CE\u5E02\uFF1A");
		cityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cityLabel.setBounds(31, 275, 45, 15);
		mainPanel.add(cityLabel);

		nameField = new JTextField();
		nameField.setBounds(79, 26, 130, 21);
		mainPanel.add(nameField);
		nameField.setColumns(10);

		sexField = new JTextField();
		sexField.setBounds(79, 67, 130, 21);
		mainPanel.add(sexField);
		sexField.setColumns(10);

		IDField = new JTextField();
		IDField.setBounds(79, 107, 130, 21);
		mainPanel.add(IDField);
		IDField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(79, 148, 130, 21);
		mainPanel.add(phoneField);
		phoneField.setColumns(10);

		qqField = new JTextField();
		qqField.setBounds(79, 188, 130, 21);
		mainPanel.add(qqField);
		qqField.setColumns(10);

		ageField = new JTextField();
		ageField.setBounds(79, 229, 130, 21);
		mainPanel.add(ageField);
		ageField.setColumns(10);

		cityField = new JTextField();
		cityField.setBounds(79, 273, 130, 21);
		mainPanel.add(cityField);
		cityField.setColumns(10);

		label = new JLabel("\u4FDD\u5B58\u6210\u529F");
		label.setForeground(SystemColor.activeCaption);
		label.setBounds(31, 0, 241, 15);
		label.setVisible(false);
		mainPanel.add(label);

		JPanel btnPanel = new JPanel();
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 2;
		add(btnPanel, gbc_btnPanel);

		JButton saveBtn = new JButton("\u4FDD\u5B58");
		saveBtn.addActionListener(new SaveController());
		btnPanel.add(saveBtn);

		JButton reSetBtn = new JButton("\u6E05\u7A7A");
		reSetBtn.addActionListener(new EmptyBtnController());
		btnPanel.add(reSetBtn);

	}

	class SaveController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ItemsDAO itemsdao = new ItemsDAO();
				Items item = getItems();
				if (!itemsdao.isItemExits(item.getId())) {
					itemsdao.saveItems(item);
					listPanel.loadItemsIntoList();
					label.setText("保存成功");
					label.setVisible(true);
					listPanel.updateUI();
				}else{
					label.setText("该学号已存在，请直接修改");
					label.setVisible(true);
					listPanel.updateUI();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	class EmptyBtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ageField.setText("");
			cityField.setText("");
			IDField.setText("");
			nameField.setText("");
			phoneField.setText("");
			qqField.setText("");
			sexField.setText("");
			contentPanel.updateUI();
		}

	}

	private Items getItems() {
		Items item = new Items();
		item.setAge(Integer.parseInt(ageField.getText()));
		item.setCity(cityField.getText());
		item.setId(IDField.getText());
		item.setName(nameField.getText());
		item.setPhoneNum(phoneField.getText());
		item.setQQ(qqField.getText());
		item.setSex(sexField.getText());
		return item;
	}

}
