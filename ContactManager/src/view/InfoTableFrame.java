package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import dao.FilterHelper;
import dao.ItemsDAO;
import dao.RuntimeData;
import entity.Items;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

//��ϵ�˱��--��������ɱ�������˴�
public class InfoTableFrame extends JFrame {

	private JPanel contentPane;
	private JTable infoTable;
	private JTextField ageDown;
	private JTextField ageUp;
	private JTextField positionField;
	private JScrollPane scrolltablePane;
	private DefaultTableModel tableModel;
	private JCheckBox sexMaleCheck;
	private JCheckBox sexFemaleCheck;

	/**
	 * Create the frame.
	 */
	public InfoTableFrame() {
		// ���ڱ���
		setTitle("\u751F\u6210\u8868\u683C");
		// ��ȡ��Ļ��ȣ��봰�ڿ�Ƚ��м��㣬ʹ�ô��ھ���
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screen = kit.getScreenSize();
		int screenWidth = screen.width;
		int screenHeight = screen.height;
		int frameWidth = 640;
		int frameHeight = 480;

		Font mainFont = new Font("΢���ź� Light", Font.PLAIN, 13);

		setBounds((screenWidth - frameWidth) / 2, (screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		setResizable(false);

		// �������
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 39, 404, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// ɸѡ�����
		JPanel barPanel = new JPanel();
		barPanel.setLayout(null);
		GridBagConstraints gbc_barPanel = new GridBagConstraints();
		gbc_barPanel.insets = new Insets(0, 0, 5, 0);
		gbc_barPanel.fill = GridBagConstraints.BOTH;
		gbc_barPanel.gridx = 0;
		gbc_barPanel.gridy = 0;
		contentPane.add(barPanel, gbc_barPanel);

		// ������ɸѡ���ı�ǩ���ı���
		JLabel ageLabel = new JLabel("\u5E74\u9F84\uFF1A");
		ageLabel.setFont(mainFont);
		ageLabel.setBounds(18, 12, 44, 15);
		barPanel.add(ageLabel);

		ageDown = new JTextField();
		ageDown.setBounds(54, 9, 36, 21);
		barPanel.add(ageDown);
		ageDown.setColumns(10);

		JLabel to = new JLabel("\u2192");
		to.setBounds(89, 12, 19, 15);
		barPanel.add(to);

		ageUp = new JTextField();
		ageUp.setBounds(101, 9, 36, 21);
		barPanel.add(ageUp);
		ageUp.setColumns(10);

		JLabel label = new JLabel("\u6027\u522B\uFF1A");
		label.setFont(mainFont);
		label.setBounds(155, 12, 44, 15);
		barPanel.add(label);

		sexMaleCheck = new JCheckBox("\u7537");
		sexMaleCheck.setFont(mainFont);
		sexMaleCheck.setBounds(194, 8, 40, 23);
		barPanel.add(sexMaleCheck);

		sexFemaleCheck = new JCheckBox("\u5973");
		sexFemaleCheck.setFont(mainFont);
		sexFemaleCheck.setBounds(234, 8, 44, 23);
		barPanel.add(sexFemaleCheck);

		JLabel label_1 = new JLabel("\u5730\u533A\uFF1A");
		label_1.setFont(mainFont);
		label_1.setBounds(284, 12, 49, 15);
		barPanel.add(label_1);

		positionField = new JTextField();
		positionField.setBounds(322, 9, 66, 21);
		barPanel.add(positionField);
		positionField.setColumns(10);

		JButton supplyBtn = new JButton("\u7B5B\u9009");
		supplyBtn.setFont(mainFont);
		supplyBtn.addActionListener(new SupplyBtnController());
		supplyBtn.setBounds(427, 8, 113, 23);
		barPanel.add(supplyBtn);

		JButton saveBtn = new JButton("����");
		saveBtn.setFont(mainFont);
		saveBtn.setBounds(558, 8, 66, 23);
		barPanel.add(saveBtn);
		// ɸѡ��---����

		// ��Ϣ���ʹ��DefaultTableModel������ݣ�
		JPanel tablePanel = new JPanel();
		tableModel = getInfoTable(loadAllItems());
		infoTable = new JTable(tableModel);
		infoTable.setPreferredScrollableViewportSize(new Dimension(600, 370));
		infoTable.addMouseListener(new updateController());
		GridBagConstraints gbc_tablePane = new GridBagConstraints();
		gbc_tablePane.fill = GridBagConstraints.BOTH;
		gbc_tablePane.gridx = 0;
		gbc_tablePane.gridy = 1;

		scrolltablePane = new JScrollPane(infoTable);
		contentPane.add(scrolltablePane, gbc_tablePane);

		setVisible(true);
	}

	// ����ɸѡ
	class SupplyBtnController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int lowerAge, upperAge;
			boolean isMale, isFemale;
			String position = "";
			// ��ȡɸѡֵ�������ϸ�ʽ��ֵ
			lowerAge = ageDown.getText().matches("^[0-9]+") ? Integer.parseInt(ageDown.getText()) : -1;
			upperAge = ageUp.getText().matches("^[0-9]+") ? Integer.parseInt(ageUp.getText()) : -1;
			isMale = sexMaleCheck.isSelected();
			isFemale = sexFemaleCheck.isSelected();
			position = positionField.getText();
			// ����ɸѡ����dao.FilterHelper��
			FilterHelper filter = new FilterHelper(lowerAge, upperAge, isMale, isFemale, position);

			try {
				ItemsDAO itemsdao = new ItemsDAO();
				ArrayList<Items> resultsList = itemsdao.getItemsByFilter(filter); // ����ɸѡ����ȡ����
				String[] columnNames = { "ѧ��", "����", "�Ա�", "�绰����", "����", "QQ", "����" }; // ����
				String[][] rowData = new String[resultsList.size()][columnNames.length]; // ����
				Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
				Vector<String> columnVector = new Vector<String>();
				// ����������Vector
				for (int i = 0; i < columnNames.length; i++) {
					columnVector.addElement(columnNames[i]);
				}
				// �������ݵ�Vector
				for (int i = 0; i < resultsList.size(); i++) {
					Vector<String> v = new Vector<String>();
					for (int j = 0; j < rowData[i].length; j++) {
						v.addElement(resultsList.get(i).getAtrributes()[j]);
						System.out.println(i + " " + j + " " + v.get(j));
					}
					dataVector.addElement(v);
				}
				// ����Model�������д�������
				tableModel.setDataVector(dataVector, columnVector);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class updateController extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() >= 2) {
				int selectedRow = infoTable.getSelectedRow();
				String id = (String) tableModel.getValueAt(selectedRow, 0);
				System.out.println(id);
				ItemsDAO itemsdao;
				try {
					itemsdao = new ItemsDAO();
					Items item = itemsdao.getItemByID(id);
					System.out.println(item.getId());
					new DetialsFrame(item);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

	}

	// ������񣬵�������
	private DefaultTableModel getInfoTable(ArrayList<Items> itemsList) {
		if (itemsList.size() > 0) {
			String[] columnNames = { "ѧ��", "����", "�Ա�", "�绰����", "����", "QQ", "����" };
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

	private ArrayList<Items> loadAllItems() {
		try {
			ItemsDAO itemsdao = new ItemsDAO();
			ArrayList<Items> itemsList = itemsdao.getAllItems();
			return itemsList;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			return null;
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
