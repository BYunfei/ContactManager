package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Items;
import entity.Users;

import java.awt.Color;

//搜索结果窗口，点击“搜索”弹出此窗口，此窗口复用listPanel实现
public class SearchResultFrame extends JFrame {
	
	private Users user;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// SearchResultFrame frame = new SearchResultFrame();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 */
	public SearchResultFrame(Users user,ArrayList<Items> itemsList) {
		this.user = user;
		int frameWidth = 288;
		int frameHeight = 280;

		setBackground(Color.WHITE);
		setTitle("\u641C\u7D22\u7ED3\u679C");
		setBounds(280, 480, frameWidth, frameHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		ListPanel listPanel = new ListPanel(user,itemsList, frameWidth, frameHeight);
		contentPane.add(listPanel);
	}

}
