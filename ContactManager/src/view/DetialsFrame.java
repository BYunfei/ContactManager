package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ItemsDAO;
import dao.RuntimeData;
import dao.UsersDAO;
import entity.Items;
import entity.Users;
import javax.swing.JButton;
import java.awt.SystemColor;

//��ϵ�˵���ϸ��Ϣ������ϵ���б�����ϵ��ʱ���֣�����Ա�����ڴ˴��޸Ļ���ɾ������ϵ�ˣ�
public class DetialsFrame extends JFrame {
	private Users user;
	private Items item;
	private JFrame frame;
	private JPanel mainPanel;
	private JLabel name;
	private JLabel ID;
	private JLabel age;
	private JLabel sex;
	private JLabel city;
	private JLabel phone;
	private JLabel qq;
	private JLabel label;
	private JTextField nameField;
	private JTextField sexField;
	private JTextField IDField;
	private JTextField phoneField;
	private JTextField qqField;
	private JTextField ageField;
	private JTextField cityField;
	private ListPanel listPanel;

	public DetialsFrame(Items item) {
		frame = this;
		listPanel = RuntimeData.getListPanel();
		System.out.println("listPanel: "+listPanel);
		this.user = RuntimeData.getUser();
		setTitle(item.getName() + "-��ϸ��Ϣ");

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 288;
		int frameHeight = 529;

		int column1 = 56;
		int column2 = 107;

		this.item = item;
		this.user = user;
		this.frame = this;
		Font labelFont = new Font("΢���ź�", Font.PLAIN, 15);

		setBounds((screenWidth - frameWidth) / 2 + 288, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 282, 0 };
		gridBagLayout.rowHeights = new int[] { 77, 329, 99, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel title = new JLabel(item.getName());
		title.setFont(new Font("΢���ź�", Font.PLAIN, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.fill = GridBagConstraints.VERTICAL;
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		getContentPane().add(title, gbc_title);

		mainPanel = new JPanel();
		mainPanel.setBorder(null);
		mainPanel.setLayout(null);
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 0;
		gbc_mainPanel.gridy = 1;
		getContentPane().add(mainPanel, gbc_mainPanel);

		JLabel nameLabel = new JLabel("\u59D3\u540D\uFF1A");
		nameLabel.setFont(labelFont);
		nameLabel.setBounds(column1, 28, 48, 15);
		mainPanel.add(nameLabel);

		JLabel IDLabel = new JLabel("\u5B66\u53F7\uFF1A");
		IDLabel.setFont(labelFont);
		IDLabel.setBounds(column1, 109, 48, 15);
		mainPanel.add(IDLabel);

		JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
		sexLabel.setFont(labelFont);
		sexLabel.setBounds(column1, 69, 48, 15);
		mainPanel.add(sexLabel);

		JLabel phoneLabel = new JLabel("\u7535\u8BDD\uFF1A");
		phoneLabel.setFont(labelFont);
		phoneLabel.setBounds(column1, 150, 48, 15);
		mainPanel.add(phoneLabel);

		JLabel qqLabel = new JLabel("QQ\uFF1A");
		qqLabel.setFont(labelFont);
		qqLabel.setBounds(column1, 190, 48, 15);
		mainPanel.add(qqLabel);

		JLabel ageLabel = new JLabel("\u5E74\u9F84\uFF1A");
		ageLabel.setFont(labelFont);
		ageLabel.setBounds(column1, 231, 48, 15);
		mainPanel.add(ageLabel);

		JLabel cityLabel = new JLabel("\u57CE\u5E02\uFF1A");
		cityLabel.setFont(labelFont);
		cityLabel.setBounds(column1, 275, 48, 15);
		mainPanel.add(cityLabel);

		name = new JLabel();
		name.setBounds(column2, 26, 130, 21);
		name.setFont(labelFont);
		mainPanel.add(name);

		sex = new JLabel();
		sex.setBounds(column2, 67, 130, 21);
		sex.setFont(labelFont);
		mainPanel.add(sex);

		ID = new JLabel();
		ID.setBounds(column2, 107, 130, 21);
		ID.setFont(labelFont);
		mainPanel.add(ID);

		phone = new JLabel();
		phone.setBounds(column2, 148, 130, 21);
		phone.setFont(labelFont);
		mainPanel.add(phone);

		qq = new JLabel();
		qq.setBounds(column2, 188, 130, 21);
		qq.setFont(labelFont);
		mainPanel.add(qq);

		age = new JLabel();
		age.setBounds(column2, 229, 130, 21);
		age.setFont(labelFont);
		mainPanel.add(age);

		city = new JLabel();
		city.setBounds(column2, 273, 130, 21);
		city.setFont(labelFont);
		mainPanel.add(city);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		GridBagConstraints gbc_btnPanel = new GridBagConstraints();
		gbc_btnPanel.fill = GridBagConstraints.BOTH;
		gbc_btnPanel.gridx = 0;
		gbc_btnPanel.gridy = 2;
		getContentPane().add(btnPanel, gbc_btnPanel);
		
		label = new JLabel("\u4FDD\u5B58\u6210\u529F");
		label.setBounds(10, 67, 54, 15);
		label.setVisible(false);
		btnPanel.add(label);
		
		//�ж��Ƿ�Ϊ����Ա������Ա�������޸ĺ�ɾ������
		if (user.isAdmin()) {
			
			JButton changeBtn = new JButton("\u4FEE\u6539");
			changeBtn.setBackground(SystemColor.activeCaption);
			changeBtn.setBounds(56, 10, 70, 23);
			changeBtn.setFont(labelFont);
			changeBtn.addActionListener(new ChangeBtnController());
			btnPanel.add(changeBtn);

			JButton saveBtn = new JButton("\u4FDD\u5B58");
			saveBtn.setBackground(SystemColor.activeCaption);
			saveBtn.setBounds(136, 10, 88, 53);
			saveBtn.setFont(labelFont);
			saveBtn.addActionListener(new SaveController());
			btnPanel.add(saveBtn);

			JButton resetBtn = new JButton("\u91CD\u7F6E");
			resetBtn.setBackground(SystemColor.activeCaption);
			resetBtn.setBounds(56, 40, 70, 23);
			resetBtn.setFont(labelFont);
			resetBtn.addActionListener(new ReSetController());
			btnPanel.add(resetBtn);

			JLabel delLabelBtn = new JLabel("\u5220\u9664");
			delLabelBtn.setFont(new Font("����", Font.PLAIN, 12));
			delLabelBtn.setBounds(235, 70, 34, 15);
			delLabelBtn.addMouseListener(new DelController(delLabelBtn));
			btnPanel.add(delLabelBtn);
		}

		setVisible(true);
		loadItem(item);

	}

	// �������޸ġ���ť������������¼�ʱ������Ϣ���ı�ǩ�ı�Ϊ�ı���
	class ChangeBtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mainPanel.removeAll();
			exchangePanel();
			mainPanel.repaint();
		}

		// ����ǩ�ı�Ϊ�ı���
		private void exchangePanel() {
			int column1 = 56;
			int column2 = 107;

			Font labelFont = new Font("΢���ź�", Font.PLAIN, 15);

			JLabel nameLabel = new JLabel("\u59D3\u540D\uFF1A");
			nameLabel.setFont(labelFont);
			nameLabel.setBounds(column1, 28, 48, 15);
			mainPanel.add(nameLabel);

			JLabel IDLabel = new JLabel("\u5B66\u53F7\uFF1A");
			IDLabel.setFont(labelFont);
			IDLabel.setBounds(column1, 109, 48, 15);
			mainPanel.add(IDLabel);

			JLabel sexLabel = new JLabel("\u6027\u522B\uFF1A");
			sexLabel.setFont(labelFont);
			sexLabel.setBounds(column1, 69, 48, 15);
			mainPanel.add(sexLabel);

			JLabel phoneLabel = new JLabel("\u7535\u8BDD\uFF1A");
			phoneLabel.setFont(labelFont);
			phoneLabel.setBounds(column1, 150, 48, 15);
			mainPanel.add(phoneLabel);

			JLabel qqLabel = new JLabel("QQ\uFF1A");
			qqLabel.setFont(labelFont);
			qqLabel.setBounds(column1, 190, 48, 15);
			mainPanel.add(qqLabel);

			JLabel ageLabel = new JLabel("\u5E74\u9F84\uFF1A");
			ageLabel.setFont(labelFont);
			ageLabel.setBounds(column1, 231, 48, 15);
			mainPanel.add(ageLabel);

			JLabel cityLabel = new JLabel("\u57CE\u5E02\uFF1A");
			cityLabel.setFont(labelFont);
			cityLabel.setBounds(column1, 275, 48, 15);
			mainPanel.add(cityLabel);

			nameField = new JTextField(item.getName());
			nameField.setBounds(column2, 26, 130, 21);
			nameField.setFont(labelFont);
			mainPanel.add(nameField);

			sexField = new JTextField(item.getSex());
			sexField.setBounds(column2, 67, 130, 21);
			sexField.setFont(labelFont);
			mainPanel.add(sexField);

			IDField = new JTextField(item.getId());
			IDField.setBounds(column2, 107, 130, 21);
			IDField.setFont(labelFont);
			mainPanel.add(IDField);

			phoneField = new JTextField(item.getPhoneNum());
			phoneField.setBounds(column2, 148, 130, 21);
			phoneField.setFont(labelFont);
			mainPanel.add(phoneField);

			qqField = new JTextField(item.getQQ());
			qqField.setBounds(column2, 188, 130, 21);
			qqField.setFont(labelFont);
			mainPanel.add(qqField);

			ageField = new JTextField("" + item.getAge());
			ageField.setBounds(column2, 229, 130, 21);
			ageField.setFont(labelFont);
			mainPanel.add(ageField);

			cityField = new JTextField(item.getCity());
			cityField.setBounds(column2, 273, 130, 21);
			cityField.setFont(labelFont);
			mainPanel.add(cityField);
		}
	}
	
	//���������桱��ť��������Ϣ
	class SaveController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ItemsDAO itemsdao = new ItemsDAO();
				itemsdao.updateItems(getItems());
				listPanel.loadItemsIntoList();
				listPanel.updateUI();
				;
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	//����ǰ���������ı����е���Ϣ
	class ReSetController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ItemsDAO itemsdao = new ItemsDAO();
				loadItem(item);
				label.setText("����ɹ�");
				label.setVisible(true);
			} catch (Exception ex) {

			}

		}

		private void loadItem(Items item) {
			if (item != null) {
				ageField.setText("" + item.getAge());
				cityField.setText(item.getCity());
				IDField.setText(item.getId());
				nameField.setText(item.getName());
				phoneField.setText(item.getPhoneNum());
				qqField.setText(item.getQQ());
				sexField.setText(item.getSex());
				cityField.setText(item.getCity());
			}

		}

	}
	
	//ɾ������
	class DelController implements MouseListener {

		private JLabel label;
		
		//��ȡ��ɾ������ǩ���󣬲��Դ���������������Ƴ��Ķ���
		public DelController(JLabel label) {
			this.label = label;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				int choice = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��ɾ����", JOptionPane.YES_NO_OPTION);//ȷ���Ƿ�ɾ��
				System.out.println("ѡ��Ϊ�� " + choice);
				if (choice == 0) {
					ItemsDAO itemsdao = new ItemsDAO();
					UsersDAO usersdao = new UsersDAO();
					itemsdao.deleteItemByID(item.getId());
					usersdao.deleteUserBy(item.getId());
					System.out.println(listPanel);
					listPanel.loadItemsIntoList();	//��ϵ���б�������������
					listPanel.updateUI();			//ˢ��
					frame.setVisible(false);		//ɾ����رյ�ǰ����
					label.setText("ɾ���ɹ�");
					label.setVisible(true);
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(rootPane, "ɾ��ʧ�ܣ������ԣ�\n������Ϣ��"+e1.getMessage());
				e1.printStackTrace();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//�������
			label.setText("<html><u>ɾ��</u></html>");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//�Ƴ�
			label.setText("\u5220\u9664");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	//�����ı�������Ϣ��������ϵ�˶���
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

	//��ȡ��ǰ��ϵ����Ϣ���򿪴���ʱ�����Ϣ
	private void loadItem(Items item) {
		System.out.println(item);
		if (item != null) {
			age.setText("" + item.getAge());
			city.setText(item.getCity());
			ID.setText(item.getId());
			name.setText(item.getName());
			phone.setText(item.getPhoneNum());
			qq.setText(item.getQQ());
			sex.setText(item.getSex());
		}
	}

	//����
//	public static void main(String[] args) {
//		Users u = new Users("2222", "abc", "a", true);
//		Items i = new Items("aoeu", "nan", 18, "13333333333", "333333333", "aoeus");
//		DetialsFrame df = new DetialsFrame(new ListPanel(u), u, i);
//	}
}
