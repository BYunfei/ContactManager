package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.ItemsDAO;
import dao.RuntimeData;
import entity.Items;
import entity.Users;

public class ListPanel extends JPanel {
	JPanel panel;
	JScrollPane scrollPane;
	int panelWidth;
	int panelHeight;
	private Users user;

	public ListPanel(Users user) {
		super();
		this.user = user;
		this.panelWidth = 242;
		this.panelHeight = 589;
		setBounds(0, 0, 242, 589);

		setBackground(Color.WHITE);
		setLayout(null);

		panel = new JPanel();
		panel.setBounds(1, 0, panelWidth, panelHeight);
		panel.setPreferredSize(new Dimension(242, 0));
		panel.setBackground(Color.WHITE);
		add(panel);

		scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(Color.WHITE);
		add(scrollPane);
		scrollPane.setBounds(0, 0, panelWidth, panelHeight);
		RuntimeData.setListPanel(this);
		System.out.println("------------");
		System.out.println("test: "+this);

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

	public ListPanel(Users user, ArrayList<Items> itemsList) {
		this(user);
		loadItemsIntoList(itemsList);
	}

	public ListPanel(Users user, ArrayList<Items> itemsList, int width, int height) {
		this(user, itemsList);
		scrollPane.setBounds(0, 0, width, height);
		panel.setBounds(0, 0, width, height);
	}

	public void loadItemsIntoList() {
		try {
			ItemsDAO itemsdao = new ItemsDAO();
			ArrayList<Items> itemsList = itemsdao.getAllItems();
			System.out.println(itemsList);
			panel.removeAll();
			if(itemsList!=null)
				loadItemsIntoList(itemsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void loadItemsIntoList(ArrayList<Items> itemsList) {
		int i = 0;
		if (itemsList.size() != 0) {
			for (i = 0; i < itemsList.size(); i++) {
				Items item = itemsList.get(i);
				panel.add(new ItemPane(item));
			}
			panel.setPreferredSize(new Dimension(panelWidth, i * 67));
		}
	}
}
