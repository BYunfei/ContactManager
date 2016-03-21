package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.DialogPeer;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.LineBorder;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

import dao.ItemsDAO;
import dao.RuntimeData;
import entity.Items;
import entity.Users;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField searchField;
	private ListPanel listPanel;
	private Users user;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		// 设置用户
		this.user = RuntimeData.getUser();

		// 获取屏幕的宽度、高度
		setTitle("通讯录管理系统");
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 288;
		int frameHeight = 640;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ---------- 创建搜索面板----------
		JPanel search = new JPanel();
		search.setBounds(0, 0, 282, 23);
		contentPane.add(search);
		GridBagLayout gbl_search = new GridBagLayout();
		gbl_search.columnWidths = new int[] { 197, 56, 14, 0 };
		gbl_search.rowHeights = new int[] { 11, 0 };
		gbl_search.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_search.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		search.setLayout(gbl_search);

		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.fill = GridBagConstraints.BOTH;
		gbc_searchField.insets = new Insets(0, 0, 0, 5);
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 0;
		search.add(searchField, gbc_searchField);
		searchField.setColumns(10);

		JButton searchBtn = new JButton("\u641C\u7D22");
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.insets = new Insets(0, 0, 0, 5);
		gbc_searchBtn.fill = GridBagConstraints.BOTH;
		gbc_searchBtn.gridx = 1;
		gbc_searchBtn.gridy = 0;
		searchBtn.addActionListener(new SearchController(this));
		search.add(searchBtn, gbc_searchBtn);

		// --------创建标签面板--------
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 23, 282, 589);
		tabbedPane.setTabPlacement(JTabbedPane.RIGHT);
		contentPane.add(tabbedPane);

		// ------- 创建联系人列表面板 --------
		listPanel = new ListPanel(user);
		listPanel.loadItemsIntoList();
		RuntimeData.setListPanel(listPanel);
		tabbedPane.addTab("<html>通<br>讯<br>录</html>", listPanel);

		// ---------创建添加联系人面板---------
		System.out.println(user.isAdmin());
		if (user.isAdmin()) {
			JPanel addItemPanel = new AddItemPanel(listPanel);
			tabbedPane.addTab("<html>添<br>加<br></html>", addItemPanel);
		}
		
		// ---------创建个人信息面板---------
		JPanel myInfoPanel = new MyInfoPanel();
		tabbedPane.add("<html>个<br>人<br>信<br>息</html>", myInfoPanel);

		// ---------创建工具面板---------
		JPanel toolsPanel = new ToolsPanel();
		tabbedPane.add("<html>工<br>具</html>", toolsPanel);

		setVisible(true);
	}

	public void loadItemsIntoList() {
		try {
			panel.removeAll();
			ItemsDAO itemsdao = new ItemsDAO();
			ArrayList<Items> itemsList = itemsdao.getAllItems();
			int i = 0;
			for (i = 0; i < itemsList.size(); i++) {
				Items item = itemsList.get(i);
				panel.add(new ItemPane(item));
			}
			panel.setPreferredSize(new Dimension(250, i * 67));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class SearchController implements ActionListener {
		JFrame frame;

		public SearchController(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ItemsDAO itemsdao = new ItemsDAO();
				ArrayList<Items> itemsList = new ArrayList<Items>();
				itemsList.addAll(itemsdao.serachByName(searchField.getText()));
				itemsList.addAll(itemsdao.searchByID(searchField.getText()));
				itemsList.addAll(itemsdao.searchByCity(searchField.getText()));
				SearchResultFrame srFrame = new SearchResultFrame(user, itemsList);
				srFrame.setVisible(true);
			} catch (Exception ex) {

			}
		}

	}
}
