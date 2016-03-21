package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ItemsDAO;
import dao.UsersDAO;
import entity.Items;
import entity.Users;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class RegFrame extends JFrame {

	private JFrame frame;
	private JTextField nameField;
	private JTextField IDField;
	private JTextField phoneField;
	private JTextField qqField;
	private JTextField ageField;
	private JTextField cityField;
	private JPanel contentPanel;
	private ListPanel listPanel;
	private Users user;
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JTextField adminField;

	/**
	 * Create the frame.
	 */
	public RegFrame() {
		frame = this;

		// 窗口标题
		setTitle("\u6CE8\u518C");
		// 获取屏幕宽度，与窗口宽度进行计算，使得窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 282;
		int frameHeight = 529;

		Font mainFont = new Font("微软雅黑 Light", Font.PLAIN, 13);

		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		setResizable(false);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 282, 0 };
		gridBagLayout.rowHeights = new int[] { 63, 230, 132, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel title = new JLabel("\u6CE8\u518C\u4FE1\u606F");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		getContentPane().add(title, gbc_title);

		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setLayout(null);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		getContentPane().add(mainPanel, gbc_mainPanel);

		JLabel nameLabel = new JLabel("\u59D3\u540D\uFF1A");
		nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		nameLabel.setBounds(49, 28, 45, 15);
		mainPanel.add(nameLabel);

		JLabel IDLabel = new JLabel("\u5B66\u53F7\uFF1A");
		IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		IDLabel.setBounds(49, 80, 45, 15);
		mainPanel.add(IDLabel);

		JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
		sexLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sexLabel.setBounds(49, 55, 45, 15);
		mainPanel.add(sexLabel);

		JLabel phoneLabel = new JLabel("\u7535\u8BDD\uFF1A");
		phoneLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		phoneLabel.setBounds(49, 113, 45, 15);
		mainPanel.add(phoneLabel);

		JLabel qqLabel = new JLabel("QQ\uFF1A");
		qqLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		qqLabel.setBounds(49, 144, 45, 15);
		mainPanel.add(qqLabel);

		JLabel ageLabel = new JLabel("\u5E74\u9F84\uFF1A");
		ageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ageLabel.setBounds(49, 175, 45, 15);
		mainPanel.add(ageLabel);

		JLabel cityLabel = new JLabel("\u57CE\u5E02\uFF1A");
		cityLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		cityLabel.setBounds(49, 206, 45, 15);
		mainPanel.add(cityLabel);

		nameField = new JTextField();
		nameField.setBounds(97, 26, 130, 21);
		mainPanel.add(nameField);
		nameField.setColumns(10);

		IDField = new JTextField();
		IDField.setBounds(97, 80, 130, 21);
		mainPanel.add(IDField);
		IDField.setColumns(10);

		phoneField = new JTextField();
		phoneField.setBounds(97, 111, 130, 21);
		mainPanel.add(phoneField);
		phoneField.setColumns(10);

		qqField = new JTextField();
		qqField.setBounds(97, 142, 130, 21);
		mainPanel.add(qqField);
		qqField.setColumns(10);

		ageField = new JTextField();
		ageField.setBounds(97, 173, 130, 21);
		mainPanel.add(ageField);
		ageField.setColumns(10);

		cityField = new JTextField();
		cityField.setBounds(97, 204, 130, 21);
		mainPanel.add(cityField);
		cityField.setColumns(10);

		maleRadio = new JRadioButton("\u7537");
		maleRadio.setBounds(100, 51, 54, 23);
		maleRadio.setSelected(true);
		mainPanel.add(maleRadio);

		femaleRadio = new JRadioButton("\u5973");
		femaleRadio.setBounds(156, 52, 45, 23);
		mainPanel.add(femaleRadio);

		ButtonGroup sexbtnGroup = new ButtonGroup();
		sexbtnGroup.add(maleRadio);
		sexbtnGroup.add(femaleRadio);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 2;
		getContentPane().add(btnPanel, gbc_btnPanel);

		JButton saveBtn = new JButton("\u6CE8\u518C");
		saveBtn.setBounds(50, 126, 84, 23);
		saveBtn.addActionListener(new RegisterController());
		btnPanel.add(saveBtn);

		JButton reSetBtn = new JButton("\u6E05\u7A7A");
		reSetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reSetBtn.setBounds(147, 125, 81, 23);
		// reSetBtn.addActionListener(new ReSetController());
		btnPanel.add(reSetBtn);

		JLabel label = new JLabel("\u5BC6   \u7801\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(51, 42, 60, 15);
		btnPanel.add(label);

		JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(51, 72, 84, 15);
		btnPanel.add(label_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 40, 114, 21);
		btnPanel.add(passwordField);

		JLabel usernameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		usernameLabel.setBounds(51, 13, 60, 15);
		btnPanel.add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setBounds(114, 10, 114, 21);
		btnPanel.add(usernameField);
		usernameField.setColumns(10);

		rePasswordField = new JPasswordField();
		rePasswordField.setBounds(128, 70, 100, 21);
		btnPanel.add(rePasswordField);

		JLabel adminLabel = new JLabel("\u7BA1\u7406\u5458\u6697\u53F7\uFF1A");
		adminLabel.setBounds(51, 101, 85, 15);
		btnPanel.add(adminLabel);

		adminField = new JTextField();
		adminField.setBounds(128, 98, 100, 21);
		btnPanel.add(adminField);
		adminField.setColumns(10);

		setVisible(true);

	}

	class RegisterController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UsersDAO usersdao = new UsersDAO();
				//根据填写的信息创建联系人对象
				Items item = new Items();
				item.setId(IDField.getText());
				if (ageField.getText().equals(""))
					item.setAge(-1);
				else
					item.setAge(Integer.parseInt(ageField.getText()));
				item.setCity(cityField.getText());
				item.setName(nameField.getText());
				item.setPhoneNum(phoneField.getText());
				item.setQQ(qqField.getText());
				if (maleRadio.isSelected())
					item.setSex("男");
				else
					item.setSex("女");
				boolean isAdmin = false;
				//判断管理员暗号是否正确
				if (usersdao.isAdminCorrect(adminField.getText()))
					isAdmin = true;
				Users user = new Users(IDField.getText(), usernameField.getText(), passwordField.getText(), isAdmin);
				//判断用户是否存在，存在则注册失败/重复输入密码是否正确
				if (usersdao.isUserExist(user)) {
					JOptionPane.showMessageDialog(frame, "学号或用户名已存在，请重试或联系管理员");
				}
				else if (!passwordField.getText().equals(rePasswordField.getText())) {
					JOptionPane.showMessageDialog(frame, "两次输入密码不一致，请确认后重试");
				}else {
					if (usersdao.addUser(user, item)) {
						JOptionPane.showMessageDialog(frame,
								usernameField.getText() + "成功注册为" + (isAdmin ? "管理员" : "普通用户"));
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(frame, "学号或用户名已存在请重试");
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
