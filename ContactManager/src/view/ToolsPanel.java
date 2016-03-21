package view;

import javax.swing.JPanel;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import dao.RuntimeData;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

//工具面板，包含生成表格等功能
public class ToolsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	// 工具面板
	public ToolsPanel() {
		int panelWidth = 282;
		int buttonWidth = 100;
		int buttonHeight = 30;

		// 设置为gridBagLayout布局
		setBounds(0, 0, panelWidth, 529);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0 };
		gridBagLayout.rowHeights = new int[] { 77, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		// 设置标题标签
		JLabel title = new JLabel("\u5DE5\u5177");
		title.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		add(title, gbc_title);

		// 添加面板，用来存放按钮
		JPanel panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);

		// 生成表格 按钮
		JButton createTableBtn = new JButton("\u8054\u7CFB\u4EBA\u8868");
		createTableBtn.setBounds(78, 27, buttonWidth, buttonHeight);
		createTableBtn.addActionListener(new CreateTableController());
		panel.add(createTableBtn);
		
		if (RuntimeData.getUser().isAdmin()) {
			JPanel adminPanel = new JPanel();
			adminPanel.setBorder(new TitledBorder(null, "\u7BA1\u7406\u5458\u529F\u80FD", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			adminPanel.setBounds(20, 155, 217, 266);
			panel.add(adminPanel);
			adminPanel.setLayout(null);

			JButton userManageBtn = new JButton("\u7528\u6237\u7BA1\u7406");
			userManageBtn.setBounds(58, 38, 100, 30);
			adminPanel.add(userManageBtn);
			userManageBtn.addActionListener(new UserManageController());
		}

		// //备份按钮
		// JButton backupBtn = new JButton("\u6570\u636E\u5907\u4EFD");
		// backupBtn.setBounds((panelWidth-buttonWidth)/2, 182, buttonWidth,
		// buttonHeight);
		// panel.add(backupBtn);
	}

	// 处理生成表格的点击事件
	class CreateTableController implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new InfoTableFrame();

		}
	}

	class UserManageController implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new UserManageFrame();

		}
	}
}
