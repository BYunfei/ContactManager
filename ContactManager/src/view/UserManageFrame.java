package view;

import java.awt.Color;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.FilterHelper;
import dao.ItemsDAO;
import dao.RuntimeData;
import dao.UsersDAO;
import entity.Items;
import entity.Users;
import view.InfoTableFrame.MyTableModel;
import view.InfoTableFrame.SupplyBtnController;
import view.InfoTableFrame.updateController;

public class UserManageFrame extends JFrame {

	private JPanel contentPane;
	private JTable infoTable;
	private JScrollPane scrolltablePane;
	private DefaultTableModel tableModel;
	private JCheckBox isAdminCheck;
	private JCheckBox notAdminCheck;
	private JButton delBtn;
	UsersDAO usersdao;

	public UserManageFrame() {
		usersdao = new UsersDAO();
		// 窗口标题
		setTitle("\u7528\u6237\u7BA1\u7406");
		// 获取屏幕宽度，与窗口宽度进行计算，使得窗口居中
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 640;
		int frameHeight = 480;

		Font mainFont = new Font("微软雅黑 Light", Font.PLAIN, 13);

		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		setResizable(false);

		// 内容面板
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 39, 404, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// 筛选栏面板
		JPanel barPanel = new JPanel();
		barPanel.setLayout(null);
		GridBagConstraints gbc_barPanel = new GridBagConstraints();
		gbc_barPanel.insets = new Insets(0, 0, 5, 0);
		gbc_barPanel.fill = GridBagConstraints.BOTH;
		gbc_barPanel.gridx = 0;
		gbc_barPanel.gridy = 0;
		contentPane.add(barPanel, gbc_barPanel);

		JLabel label = new JLabel("\u662F\u5426\u7BA1\u7406\u5458\uFF1A");
		label.setFont(mainFont);
		label.setBounds(17, 9, 82, 15);
		barPanel.add(label);

		isAdminCheck = new JCheckBox("\u662F");
		isAdminCheck.setFont(mainFont);
		isAdminCheck.setBounds(94, 6, 40, 23);
		isAdminCheck.setSelected(true);
		barPanel.add(isAdminCheck);

		notAdminCheck = new JCheckBox("\u5426");
		notAdminCheck.setFont(mainFont);
		notAdminCheck.setBounds(136, 6, 44, 23);
		notAdminCheck.setSelected(true);
		barPanel.add(notAdminCheck);

		JButton supplyBtn = new JButton("\u7B5B\u9009");
		supplyBtn.setFont(mainFont);
		supplyBtn.addActionListener(new SupplyBtnController());
		supplyBtn.setBounds(213, 5, 113, 23);
		barPanel.add(supplyBtn);

		JButton addBtn = new JButton("\u6DFB\u52A0");
		addBtn.setFont(mainFont);
		addBtn.setBounds(477, 5, 66, 23);
		addBtn.addActionListener(new AddController());
		barPanel.add(addBtn);

		delBtn = new JButton("\u5220\u9664");
		delBtn.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		delBtn.addActionListener(new DelController());
		delBtn.setBounds(553, 5, 71, 23);
		barPanel.add(delBtn);
		
		JButton freshBtn = new JButton("\u5237\u65B0");
		freshBtn.setFont(new Font("微软雅黑 Light", Font.PLAIN, 13));
		freshBtn.setBounds(336, 5, 71, 23);
		freshBtn.addActionListener(new FreshController());
		barPanel.add(freshBtn);
		// 筛选栏---结束

		// 信息表格（使用DefaultTableModel填充内容）
		JPanel tablePanel = new JPanel();
		tableModel = getInfoTable(loadAllItems());
		infoTable = new JTable(tableModel);
		infoTable.setPreferredScrollableViewportSize(new Dimension(600, 370));
		infoTable.addMouseListener(new UpdateController());
		GridBagConstraints gbc_tablePane = new GridBagConstraints();
		gbc_tablePane.fill = GridBagConstraints.BOTH;
		gbc_tablePane.gridx = 0;
		gbc_tablePane.gridy = 1;

		scrolltablePane = new JScrollPane(infoTable);
		contentPane.add(scrolltablePane, gbc_tablePane);

		setVisible(true);
	}

	// 构建表格，导入数据
	private DefaultTableModel getInfoTable(ArrayList<Users> itemsList) {
		if (itemsList.size() > 0) {
			String[] columnNames = { "学号", "用户名", "密码", "是否管理员" };
			String[][] rowData = new String[itemsList.size()][columnNames.length];
			for (int i = 0; i < itemsList.size(); i++) {
				for (int j = 0; j < rowData[i].length; j++) {
					rowData[i][j] = itemsList.get(i).getAtrributes()[j];
				}
			}
			DefaultTableModel tableModel = new MyTableModel(rowData, columnNames);
			return tableModel;
		}
		return null;
	}

	private ArrayList<Users> loadAllItems() {
		UsersDAO usersdao = new UsersDAO();
		ArrayList<Users> usersList = usersdao.getAllUsers();
		System.out.println("用户列表加载数据");
		return usersList;
	}
	
	//更新表格数据
	private void updateTable(ArrayList<Users> usersList){
		String[] columnNames = { "学号", "用户名", "密码", "是否管理员" }; // 列名
		String[][] rowData = new String[usersList.size()][columnNames.length]; // 数据
		Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
		Vector<String> columnVector = new Vector<String>();
		// 生成列名的Vector
		for (int i = 0; i < columnNames.length; i++) {
			columnVector.addElement(columnNames[i]);
		}
		// 生成数据的Vector
		for (int i = 0; i < usersList.size(); i++) {
			Vector<String> v = new Vector<String>();
			for (int j = 0; j < rowData[i].length; j++) {
				v.addElement(usersList.get(i).getAtrributes()[j]);
				System.out.println(i + " " + j + " " + v.get(j));
			}
			dataVector.addElement(v);
		}
		// 创建Model，向表格中传入数据
		tableModel.setDataVector(dataVector, columnVector);
	}

	// 处理筛选
	class SupplyBtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean isAdmin, notAdmin;
			String position = "";
			// 获取筛选值，处理不合格式的值
			isAdmin = isAdminCheck.isSelected();
			notAdmin = notAdminCheck.isSelected();

			UsersDAO usersdao = new UsersDAO();
			ArrayList<Users> resultsList = new ArrayList<Users>();
			if (isAdmin) {
				resultsList.addAll(usersdao.filterAdmin(true));
			}
			if (notAdmin) {
				resultsList.addAll(usersdao.filterAdmin(false));
			}
			updateTable(resultsList);
		}

	}
	
	class FreshController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			updateTable(usersdao.getAllUsers());
		}

	}

	class UpdateController extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() >= 2) {
				int selectedRow = infoTable.getSelectedRow();
				String id = (String) tableModel.getValueAt(selectedRow, 0);
				ItemsDAO itemsdao;
				try {
					itemsdao = new ItemsDAO();
					Items item = itemsdao.getItemByID(id);
					new DetialsFrame(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	class AddController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RegFrame regFrame = new RegFrame();
			updateTable(loadAllItems());
		}

	}

	class DelController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedRow = infoTable.getSelectedRow();
			String id = (String) tableModel.getValueAt(selectedRow, 0);
			int choice = JOptionPane.showConfirmDialog(null, "确认删除？", "确认删除？", JOptionPane.YES_NO_OPTION);//确认是否删除
			if(choice == 0){
				try {
					UsersDAO usersdao = new UsersDAO();
					ItemsDAO itemsdao = new ItemsDAO();
					Users user = usersdao.getUserByID(id);
					Items item = itemsdao.getItemByID(id);
					boolean isDelUser = usersdao.deleteUserBy(id);
					boolean isDelItem = itemsdao.deleteItemByID(id);
					updateTable(usersdao.getAllUsers());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

	}

	class MyTableModel extends DefaultTableModel {

		public MyTableModel(String[][] rowData, String[] columnNames) {
			super(rowData, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
