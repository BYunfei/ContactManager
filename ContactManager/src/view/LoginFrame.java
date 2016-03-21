package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;

import dao.RuntimeData;
import dao.UsersDAO;
import entity.Users;
import util.DBHelper;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class LoginFrame {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 获取屏幕的宽度、高度，以居中窗口
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 518;
		int frameHeight = 345;

		frame = new JFrame();
		frame.setTitle("\u901A\u8BAF\u5F55\u7BA1\u7406\u7CFB\u7EDF");
		frame.setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(518, 345);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel titelPanel = new JPanel();
		titelPanel.setBounds(0, 0, 512, 178);
		frame.getContentPane().add(titelPanel);
		titelPanel.setBackground(new Color(68, 119, 136));
		titelPanel.setLayout(null);

		JLabel label = new JLabel("\u901A \u8BAF \u5F55 \u7BA1 \u7406 \u7CFB \u7EDF");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 38));
		label.setBounds((512 - 332) / 2, (178 - 42) / 2, 332, 42);
		label.setForeground(Color.WHITE);
		titelPanel.add(label);

		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(0, 177, 512, 139);
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);

		usernameField = new JTextField();
		usernameField.setBounds(178, 32, 150, 21);
		loginPanel.add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(178, 63, 150, 21);
		loginPanel.add(passwordField);

		JLabel usernameLabel = new JLabel("\u7528\u6237\u540D\uFF1A");
		usernameLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		usernameLabel.setBounds(110, 34, 60, 15);
		loginPanel.add(usernameLabel);

		JLabel passwordLabel = new JLabel("\u5BC6    \u7801\uFF1A");
		passwordLabel.setFont(new Font("微软雅黑 Light", Font.PLAIN, 14));
		passwordLabel.setBounds(110, 66, 60, 15);
		loginPanel.add(passwordLabel);

		JButton submitButton = new JButton("登陆");
		submitButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		submitButton.addActionListener(new LoginController());
		submitButton.setBounds(345, 31, 70, 52);
		submitButton.setContentAreaFilled(false);
		submitButton.setForeground(Color.WHITE);
		loginPanel.add(submitButton);

		JPanel submitButtonBackground = new JPanel();
		submitButtonBackground.setBounds(345, 31, 70, 52);
		submitButtonBackground.setBackground(new Color(68, 119, 136));
		loginPanel.add(submitButtonBackground);

		JCheckBox checkIfAutoLogin = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		checkIfAutoLogin.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		checkIfAutoLogin.setBounds(175, 90, 73, 23);
		loginPanel.add(checkIfAutoLogin);

		JCheckBox checkIfRemember = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		checkIfRemember.setFont(new Font("微软雅黑 Light", Font.PLAIN, 12));
		checkIfRemember.setBounds(261, 90, 85, 23);
		loginPanel.add(checkIfRemember);

		JLabel findPasswordBtn = new JLabel("\u5FD8\u8BB0\u5BC6\u7801\uFF1F");
		findPasswordBtn.setFont(new Font("微软雅黑 Light", Font.PLAIN, 11));
		findPasswordBtn.setBounds(442, 114, 60, 15);
		loginPanel.add(findPasswordBtn);
		
		JLabel regBtn = new JLabel("\u6CE8\u518C");
		regBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		regBtn.setBounds(390, 114, 34, 15);
		regBtn.addMouseListener(new RegBtnController());
		loginPanel.add(regBtn);
		
	}
	
	//处理登陆事件
	class LoginController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			UsersDAO userDao = new UsersDAO();
			String username = usernameField.getText();
			String password = passwordField.getText();
			//创建只含用户名和密码的用户对象用于数据比对
			Users u = new Users(username, password);
			if (userDao.isUserCorrect(u)) {
				//获取真实用户
				Users realUser = userDao.getUserByUsername(u.getUsername());
				RuntimeData.setUser(realUser);//将用户信息储存到一个静态的类中，方便获取用户信息
				new MainFrame(); //跳转到主页面
				frame.setVisible(false);
				System.out.println(u.getUsername() + "登陆成功"+realUser.isAdmin());
			} else {
				//密码错误，弹窗提示
				JOptionPane.showMessageDialog(frame, "用户名或密码错误，请重试");
			}
		}
	}
	
	class RegBtnController implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			new RegFrame();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
