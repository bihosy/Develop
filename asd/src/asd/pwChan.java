package asd;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.table.*;

public class pwChan extends JFrame implements ActionListener {

	// --------DB ���̺� ���� --------//
	String header[] = { "ID", "PW", "�̸�", "����", "�������", "�޴���", "�ּ�", "����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	// --------DB ���̺� ���� --------//

	// ----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����

	String gr = "Members.txt";// �������

	String l;// ���� �о ���ڿ� ����

	JLabel lb_newPW; // ���ο� ��й�ȣ(New Password) ��
	JLabel lb_newPWC; // ���ο� ��й�ȣ Ȯ��(New Password Check) ��

	// ----------------���� �������Ʈ-----------//

	static int pwChanRow;

	JPasswordField pf_newPW; // �� ��й�ȣ
	JPasswordField pf_newPWC; // �� ��й�ȣ Ȯ��
	JButton btn_newPW;

	BufferedImage bi;
	JPanel P;

	public pwChan() {

		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB ���̺� ���� --------//

		try {
			bi = ImageIO.read(new File("h.jfif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg2 = bi.getScaledInstance(395, 212, Image.SCALE_SMOOTH);

		P = new JPanel(null)
		{
			public void paintComponent(Graphics g){
				g.drawImage(dimg2, 0, 0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		pf_newPW = new JPasswordField(10);
		pf_newPWC = new JPasswordField(10);
		btn_newPW = new JButton("����");
		lb_newPW = new JLabel("�� ��й�ȣ");
		lb_newPWC = new JLabel("�� ��й�ȣ Ȯ��");

		this.setTitle("��й�ȣ ����");
		this.setSize(400, 240);

		lb_newPW.setBounds(50, 30, 100, 25);
		pf_newPW.setBounds(190, 30, 150, 25);
		lb_newPWC.setBounds(50, 80, 100, 25);
		pf_newPWC.setBounds(190, 80, 150, 25);
		btn_newPW.setBounds(50, 140, 290, 35);

		lb_newPW.setHorizontalAlignment(JLabel.RIGHT);
		lb_newPWC.setHorizontalAlignment(JLabel.RIGHT);

		lb_newPW.setForeground(Color.WHITE);
		lb_newPWC.setForeground(Color.WHITE);

		this.add(P);
		P.add(lb_newPW);
		P.add(pf_newPW);
		P.add(lb_newPWC);
		P.add(pf_newPWC);
		P.add(btn_newPW);

		btn_newPW.addActionListener(this);

		this.setLocation(750, 400);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���

			while ((l = br.readLine()) != null) {
				String[] str = l.split("/");
				Table_model.addRow(str);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (pf_newPW.getText().equals(pf_newPWC.getText()) ) {
			Table_model.setValueAt(pf_newPWC.getText(), pwChanRow, 1);
			System.out.println(Table_model.getValueAt(pwChanRow, 1));

			try {
				fw = new FileWriter(gr);
				pw = new PrintWriter(fw);
				for (int i = 0; i < tb_Mem.getRowCount(); i++) {
					for (int j = 0; j < tb_Mem.getColumnCount(); j++) {
						if (j == 7) {
							l = Table_model.getValueAt(i, j).toString();
							pw.print(l);
						} else {
							l = Table_model.getValueAt(i, j).toString();
							pw.print(l);
							pw.print("/");
						}
					}
					pw.println();
				}
			}

			catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					fw.close();
					pw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			this.setVisible(false);
			this.setDefaultCloseOperation(3);
			
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ���������� ����Ǿ����ϴ�.", "��й�ȣ ã��", JOptionPane.PLAIN_MESSAGE);
			
		}

		else{
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٸ��ϴ�. �ٽ� �Է����ּ���.", "��й�ȣ ���� ����", JOptionPane.ERROR_MESSAGE);
			Table_model.setRowCount(0);
		}
	}

	public static void main(String[] args) {
		new pwChan();
	}
}