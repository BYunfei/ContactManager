package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.RuntimeData;
import entity.Items;
import entity.Users;

//������ϵ�����--��������ϵ���б�listPanel����
//���������ᵯ����Ӧ��ϵ�˵���ϸ��Ϣ��DetialsFrame��
public class ItemPane extends JPanel {

	private ListPanel listPanel;
	private Items item;
	private Users user;

	public ItemPane() {
		super();
	}

	/**
	 * Create the panel.
	 */
	public ItemPane(Items item) {
		this.listPanel = RuntimeData.getListPanel();
		this.item=item;
		this.user = RuntimeData.getUser();
		this.setBackground(new Color(255, 255, 255));
		this.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.setBounds(0, 0, 230, 59);
		this.setPreferredSize(new Dimension(233, 62));
		GridBagLayout gbl_item = new GridBagLayout();
		gbl_item.columnWidths = new int[] { 70, 160, 0 };
		gbl_item.rowHeights = new int[] { 57, 0 };
		gbl_item.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_item.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		this.setLayout(gbl_item);

		JPanel head = new JPanel();
		head.setBorder(new LineBorder(new Color(255, 255, 255), 5));
		GridBagConstraints gbc_head = new GridBagConstraints();
		head.setBackground(new Color(133, 178, 193));
		head.setForeground(Color.WHITE);
		gbc_head.fill = GridBagConstraints.BOTH;
		gbc_head.insets = new Insets(0, 0, 0, 5);
		gbc_head.gridx = 0;
		gbc_head.gridy = 0;
		this.add(head, gbc_head);
		
		//�������ʾ���ϵĲ���
		JLabel label = new JLabel((String) item.getName().subSequence(0, 1));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 30));
		label.setForeground(Color.WHITE);
		head.add(label);
		
		JPanel info = new JPanel();
		GridBagConstraints gbc_info = new GridBagConstraints();
		gbc_info.fill = GridBagConstraints.BOTH;
		gbc_info.gridx = 1;
		gbc_info.gridy = 0;
		info.setBackground(Color.WHITE);
		this.add(info, gbc_info);
		info.setLayout(new GridLayout(2, 0, 0, 0));
		
		//����
		JLabel name = new JLabel(item.getName());
		info.add(name);
		name.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		
		//�绰
		JLabel phone = new JLabel(item.getPhoneNum());
		info.add(phone);
		phone.setFont(new Font("΢���ź� Light", Font.PLAIN, 16));
		this.setSize(250, 60);

		System.out.println("���سɹ�");
		addMouseListener(new showDeialsController());
	}
	
	//����������ϸ��Ϣ
	class showDeialsController extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			new DetialsFrame(item).setVisible(true);
			
		}

	}

}
