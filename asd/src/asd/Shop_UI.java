package asd;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shop_UI extends JFrame {

	JPanel pnl = new JPanel();
	JPanel pnl_logoinfo = new JPanel();
	JPanel pnl_menu = new JPanel();
	JPanel pnl_btn = new JPanel();
	JPanel pnl_main = new JPanel();
	JPanel pnl_image = new JPanel();
	JPanel pnl_info = new JPanel();
	

	JPanel[] pnl_arry = new JPanel[8];
	JPanel[] pnl_arry2 = new JPanel[8];
	JPanel[] pnl_arry3 = new JPanel[8]; //��ǰ����
	JPanel[] pnl_arry4 = new JPanel[8]; //��ǰ���� ����
	
	JLabel[] lb_name = new JLabel[8];
	JLabel[] lb_size = new JLabel[8];
	JLabel[] lb_price = new JLabel[8];
	JLabel[] lb_jumun = new JLabel[8];
	JLabel[] lb_1day = new JLabel[8];
	JLabel[] lb_image = new JLabel[8];
	JLabel[] lb_name_arry = new JLabel[8];
	JLabel[] lb_size_arry = new JLabel[8];
	JLabel[] lb_price_arry = new JLabel[8];
	JLabel lb_top_logo = new JLabel("�̹���"); // ���ΰ� or ���θ� �ΰ�
	JLabel lb_top_info1 = new JLabel("���̵� : "); // �����Ҷ� �Է��� ���̵�
	JLabel lb_top_info2 = new JLabel("��� : "); // admin or user
	JLabel lb_top_setinfo1 = new JLabel(" rozen"); // �ӽ÷� rozen�־�� �۾��Ҷ� ����� �޾ƿ� ���̵� ���� �󺧿� ���̸�� 
	JLabel lb_top_setinfo2 = new JLabel("admin");// �ӽ÷� admin�־�� �۾��Ҷ� ����� �޾ƿ� ���̵� ���� �󺧿� ���̸�� 

	String[] str_name = { "�������� �ݹ�����1", "�������� �ݹ�����2", "�������� �ݹ�����3", "�������� �ݹ�����4", "�������� �ݹ�����5", "�������� �ݹ�����6",
			"�������� �ݹ�����7", "�������� �ݹ�����8" };
	String[] str_size = { "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)", "M~L(90~100)",
			"M~L(90~100)", "M~L(90~100)" };
	String[] str_price = { "250�޼�", "250�޼�", "250�޼�", "250�޼�", "250�޼�", "250�޼�", "250�޼�", "250�޼�" };
	String[] str_btn_name = { "����", "����", "�ƿ���", "����", "�Ź�", "�Ǽ�����" };
	int i;
	int j;
	int k;
	JButton[] btn_menu = new JButton[6];
	BufferedImage[] img=null;
	
	
	public Shop_UI() {
		
		
		this.setTitle("�����ƹٺ�");
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		pnl.setLayout(new BorderLayout());
		pnl_menu.setLayout(new BorderLayout());
		pnl_btn.setLayout(new GridLayout(0, 6, 15, 0));
		pnl_main.setLayout(new GridLayout(0, 4));
		pnl_logoinfo.setLayout(new GridLayout(0, 2));
		pnl_info.setLayout(new GridLayout(2, 0));
		this.add(pnl);
		pnl.add(pnl_logoinfo, "North");
		pnl.add(pnl_menu, "Center");

		pnl_menu.add(pnl_btn, "North");
		pnl_menu.add(pnl_main, "Center");

		pnl_logoinfo.add(pnl_image);
		pnl_logoinfo.add(pnl_info);
		pnl_image.add(lb_top_logo);
		pnl_info.add(lb_top_info1);
		pnl_info.add(lb_top_setinfo1);
		pnl_info.add(lb_top_info2);
		pnl_info.add(lb_top_setinfo2);

		for (int j = 0; j < btn_menu.length; j++) {
			btn_menu[j] = new JButton(str_btn_name[j]);
			pnl_btn.add(btn_menu[j]);
		}

		for (int i = 0; i < pnl_arry.length; i++) {
			lb_image[i] = new JLabel("�̹����ڸ�");
			pnl_arry[i] = new JPanel();
			pnl_arry2[i] = new JPanel();
			pnl_arry3[i] = new JPanel();
			pnl_arry4[i] = new JPanel();
			
			
			
			pnl_arry3[i].setLayout(new BoxLayout(pnl_arry3[i], BoxLayout.Y_AXIS));
			pnl_arry4[i].setLayout(new BoxLayout(pnl_arry4[i], BoxLayout.Y_AXIS));
			
			pnl_arry[i].setLayout(new BorderLayout());
			lb_name[i] = new JLabel("�̸� : ");
			lb_size[i] = new JLabel("������ : ");
			lb_price[i] = new JLabel("���� : ");
			lb_jumun[i] = new JLabel("[�ֹ�����]");
			lb_1day[i] = new JLabel("[���Ϲ��]");
			lb_name_arry[i] = new JLabel(str_name[i]);
			lb_size_arry[i] = new JLabel(str_size[i]);
			lb_price_arry[i] = new JLabel(str_price[i]);
			
			pnl_main.add(pnl_arry[i]);
			pnl_arry[i].add(lb_image[i], "North");
			pnl_arry[i].add(pnl_arry2[i], "Center");
			
			pnl_arry2[i].add(pnl_arry3[i]);
			pnl_arry2[i].add(pnl_arry4[i]);
			
			pnl_arry3[i].add(lb_name[i]);
			pnl_arry4[i].add(lb_name_arry[i]);
			pnl_arry3[i].add(lb_size[i]);
			pnl_arry4[i].add(lb_size_arry[i]);
			pnl_arry3[i].add(lb_price[i]);
			pnl_arry4[i].add(lb_price_arry[i]);
			pnl_arry3[i].add(lb_jumun[i]);
			pnl_arry4[i].add(lb_1day[i]);

		}

		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Shop_UI();
	}

}
