package asd;

import javax.swing.*;

import com.sun.javafx.embed.swing.Disposer;

import java.awt.*;
import java.awt.event.*;

public class payment extends JPanel implements ActionListener {
	JPanel pnl, pnl2, pnl3, // ��������, ���г�, üũ��ư�г�
	pnlP, // �޴��� �г�
	pnlN, pnlN2, pnlN3, pnlN4, pnlN5, pnlN6, // ������ �г�
	pnlC, // ī�� �г�
	pnlA, pnlA1, pnlA2, pnlA3, pnlA4, pnlA5, // ������ü �г�
	pnlPa, pnlPa1, pnlPa2; // payco �г�
	
	ButtonGroup pay, // �� �� 5�� �׷�
	noPay, // ������ �׷�
	accPay, accPay2; // ������ü �׷�
	
	JRadioButton phone, noPassbook, card, account, payco, // ��, ������, ī��, ������ü, payco
	nopYes, nopNo, // ������ ���ݿ�����
	accYes, accNo, // ������ü ���ݿ�����
	ind, ind2, ent, ent2; // ����, ����� ��ư
	
	JLabel payLabel, // üũ��ư�г� �۾�
	phoneLbl, phoneLbl2, // �޴��� ���� ��
	noLbl, noLbl2, noLbl3, noLbl4, noLbl5, noLbl6, noLbl7, // ������ ���� ��
	cardLbl, cardLbl2, // ī����� ��
	accLbl, accLbl2, accLbl3,accLbl4, accLbl5, accLbl6, accLbl7, accLbl8, accLbl9, // ������ü ��
	paycoLbl, paycoLbl2, paycoLbl3, paycoLbl4; //payco ��
	
	JCheckBox payCheck, accCheck, accCheck2; // üũ �� ������ư Ȱ��ȭ , ����ũ�� ����1,2
	
	static JButton payBtn; // �����ϱ� ��ư
	
	JTextField noTf, noTf2, noTf3, // ������ �ؽ�Ʈ�ʵ�
	acTf, // account �ؽ�Ʈ�ʵ�
	accTf, accTf2; // ������ü �ؽ�Ʈ�ʵ�
	
	String bank[] = { ":::::�������ּ���:::::", "īī����ũ 3333-07-0152358", "���� 222-2222-2222-22",
					"�λ����� 333-3333-3333-33", "�������� 555555-55-5555555" }; // ������
	JComboBox<String> bankCombo; // ������ ���� ���� �޺��ڽ�
	String PN[] = { "010", "011", "016", "017", "019", "020" }; // ������ �޴��� ���ڸ�
	String PN2[] = { "010", "011", "016", "017", "019", "020" }; // ������ü �޴��� ���ڸ�
	JComboBox<String> PNCombo, PNCombo2;

	public payment() {
		this.setLayout(new BorderLayout());
		
		pnl = new JPanel();
		phone = new JRadioButton("�޴�������");
		noPassbook = new JRadioButton("�������Ա�");
		card = new JRadioButton("ī�����");
		account = new JRadioButton("�ǽð� ������ü");
		payco = new JRadioButton("PAYCO");
		payBtn = new JButton("�����ϱ�");
		payBtn.addActionListener(this);
		pay = new ButtonGroup();
		pay.add(phone);
		pay.add(noPassbook);
		pay.add(card);
		pay.add(account);
		pay.add(payco);

		phone.addActionListener(this);
		noPassbook.addActionListener(this);
		card.addActionListener(this);
		account.addActionListener(this);
		payco.addActionListener(this);

		pnl.add(phone);
		pnl.add(noPassbook);
		pnl.add(card);
		pnl.add(account);
		pnl.add(payco);

		pnl2 = new JPanel();

		pnl3 = new JPanel();
		payCheck = new JCheckBox();
		payCheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					payBtn.setEnabled(true);
				else
					payBtn.setEnabled(false);
			}
		});
		payLabel = new JLabel("���������� Ȯ���Ͽ�����, �������࿡ �����մϴ�.");
		pnl3.add(payCheck);
		pnl3.add(payLabel, "Center");
		payBtn.setEnabled(false);
		pnl3.add(payBtn, "Center");

		// �޴��� ���� �г�
		pnlP = new JPanel();
		phoneLbl = new JLabel("!");
		phoneLbl.setForeground(Color.RED);
		phoneLbl2 = new JLabel(" �Ҿ� ������ ��� PG�� ��å�� ���� ���� �ݾ� ������ ���� �� �ֽ��ϴ�.");
		pnlP.add(phoneLbl);
		pnlP.add(phoneLbl2);

		// ������ �Ա� �г�
		pnlN = new JPanel();
		pnlN.setLayout(new GridLayout(5,0));
		
		pnlN2 = new JPanel();
		noLbl = new JLabel("�Ա��ڸ�");
		noTf = new JTextField(10); // �Ա��ڸ� �ʵ�
		pnlN2.add(noLbl);
		pnlN2.add(noTf);
		
		pnlN3 = new JPanel();
		noLbl2 = new JLabel("�Ա�����");
		bankCombo = new JComboBox<String>(bank);
		pnlN3.add(noLbl2);
		pnlN3.add(bankCombo);
		
		pnlN4 = new JPanel();
		noLbl3 = new JLabel("���ݿ����� ��û");
		noPay = new ButtonGroup();
		nopYes = new JRadioButton("���ݿ����� ��û");
		nopNo = new JRadioButton("��û����");
		noPay.add(nopYes);
		noPay.add(nopNo);
		pnlN4.add(noLbl3);
		pnlN4.add(nopYes);
		pnlN4.add(nopNo);
		
		pnlN5 = new JPanel();
		noPay = new ButtonGroup();
		ind = new JRadioButton("����");
		ent = new JRadioButton("�����");
		noPay.add(ind);
		noPay.add(ent);
		noLbl4 = new JLabel("���� : ");
		pnlN5.add(noLbl4);
		pnlN5.add(ind);
		pnlN5.add(ent);
		
		pnlN6 = new JPanel();
		noLbl5 = new JLabel("�޴��� ��ȣ : ");
		PNCombo = new JComboBox<String>(PN);
		noLbl6 = new JLabel(" - ");
		noTf2 = new JTextField(5);
		noLbl7 = new JLabel(" - ");
		noTf3 = new JTextField(5);
		pnlN6.add(noLbl5);
		pnlN6.add(PNCombo);
		pnlN6.add(noLbl6);
		pnlN6.add(noTf2);
		pnlN6.add(noLbl7);
		pnlN6.add(noTf3);
		
		pnlN.add(pnlN2);
		pnlN.add(pnlN3);
		pnlN.add(pnlN4);
		pnlN.add(pnlN5);
		pnlN.add(pnlN6);

		nopYes.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == nopYes) {
					ind.setEnabled(true);
					ent.setEnabled(true);
					PNCombo.setEnabled(true);
					noTf2.setEnabled(true);
					noTf3.setEnabled(true);
				}
				pnlN.repaint();
				pnlN.revalidate();
			}
		}); 
		
		nopNo.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == nopNo) {
					ind.setEnabled(false);
					ent.setEnabled(false);
					PNCombo.setEnabled(false);
					noTf2.setEnabled(false);
					noTf3.setEnabled(false);
				}
				pnlN.repaint();
				pnlN.revalidate();
			}
		}); 

		// ī����� �г�
		pnlC = new JPanel();
		cardLbl = new JLabel("!");
		cardLbl.setForeground(Color.RED);
		cardLbl2 = new JLabel(" �Ҿ� ������ ��� PG�� ��å�� ���� ���� �ݾ� ������ ���� �� �ֽ��ϴ�.");
		pnlC.add(cardLbl);
		pnlC.add(cardLbl2);

		// ������ü ���� �г�
		pnlA = new JPanel();
		pnlA.setLayout(new GridLayout(5, 0));

		pnlA1 = new JPanel();
		accLbl = new JLabel("�����ָ�");
		acTf = new JTextField(10); // �����ָ� �Է� �ؽ�Ʈ�ʵ�
		accCheck = new JCheckBox();
		accLbl2 = new JLabel("����ũ��(���ž���) ���񽺸� �����մϴ�.");
		pnlA1.add(accLbl);
		pnlA1.add(acTf);
		pnlA1.add(accCheck);
		pnlA1.add(accLbl2);

		pnlA2 = new JPanel();
		accLbl3 = new JLabel("!");
		accLbl3.setForeground(Color.RED);
		accLbl4 = new JLabel("�Ҿ� ������ ��� PG�� ��å�� ���� ���� �ݾ� ������ ���� �� �ֽ��ϴ�.");
		pnlA2.add(accLbl3);
		pnlA2.add(accLbl4);

		pnlA3 = new JPanel();
		accLbl5 = new JLabel("���ݿ����� ��û");
		accYes = new JRadioButton("���ݿ����� ��û");

		pnlA4 = new JPanel();
		accPay2 = new ButtonGroup();
		ind2 = new JRadioButton("����");
		ent2 = new JRadioButton("�����");
		accPay2.add(ind2);
		accPay2.add(ent2);
		accLbl6 = new JLabel("���� : ");
		pnlA4.add(accLbl6);
		pnlA4.add(ind2);
		pnlA4.add(ent2);

		pnlA5 = new JPanel();
		accLbl7 = new JLabel("�޴��� ��ȣ : ");
		PNCombo2 = new JComboBox<String>(PN2);
		accLbl8 = new JLabel(" - ");
		accTf = new JTextField(5);
		accLbl9 = new JLabel(" - ");
		accTf2 = new JTextField(5);
		pnlA5.add(accLbl7);
		pnlA5.add(PNCombo2);
		pnlA5.add(accLbl8);
		pnlA5.add(accTf);
		pnlA5.add(accLbl9);
		pnlA5.add(accTf2);

		accYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (accYes == e.getSource()) {
					ind2.setEnabled(true);
					ent2.setEnabled(true);
					PNCombo2.setEditable(true);
					accTf.setEditable(true);
					accTf2.setEditable(true);
				}
				pnlA.repaint();
				pnlA.revalidate();
			}
		});

		accNo = new JRadioButton("��û ����");
		accNo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (accNo == e.getSource()) {
					ind2.setEnabled(false);
					ent2.setEnabled(false);
					PNCombo2.setEditable(false);
					accTf.setEditable(false);
					accTf2.setEditable(false);
				}
				pnlA.repaint();
				pnlA.revalidate();
			}
		});
		accPay = new ButtonGroup();
		accPay.add(accYes);
		accPay.add(accNo);
		pnlA3.add(accLbl5);
		pnlA3.add(accYes);
		pnlA3.add(accNo);

		pnlA.add(pnlA1);
		pnlA.add(pnlA2);
		pnlA.add(pnlA3);
		pnlA.add(pnlA4);
		pnlA.add(pnlA5);

		// Payco ���� �г�
		pnlPa = new JPanel();
		pnlPa.setLayout(new GridLayout(5, 0));
		paycoLbl = new JLabel("!");
		paycoLbl.setForeground(Color.RED);
		paycoLbl2 = new JLabel("������ ���� �˾�â���� ��й�ȣ �Է¸����� ������ �����ϰ� ������ ������ ���� �Դϴ�.");
		paycoLbl3 = new JLabel("!");
		paycoLbl3.setForeground(Color.RED);
		paycoLbl4 = new JLabel("www.payco.com �� ȸ������ ��, ���� 1ȸ ī�� �� ���� ������ ����ϼž� ��� �����մϴ�.");

		pnlPa1 = new JPanel();
		pnlPa1.add(paycoLbl);
		pnlPa1.add(paycoLbl2);

		pnlPa2 = new JPanel();
		pnlPa2.add(paycoLbl3);
		pnlPa2.add(paycoLbl4);
		pnlPa.add(pnlPa1);
		pnlPa.add(pnlPa2);

		// ��� ���������� ���ϱ�

		this.add(pnl, BorderLayout.NORTH);
		this.add(pnl2, BorderLayout.CENTER);
		this.add(pnl3, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.remove(pnl2);
		this.remove(pnlP);
		this.remove(pnlN);
		this.remove(pnlC);
		this.remove(pnlA);
		this.remove(pnlPa);

		if (e.getSource() == phone) {
			this.add(pnlP);
		} else if (e.getSource() == noPassbook) {
			this.add(pnlN);
		} else if (e.getSource() == card) {
			this.add(pnlC);
		} else if (e.getSource() == account) {
			this.add(pnlA);
		} else if (e.getSource() == payco) {
			this.add(pnlPa);
		}
		if(e.getSource()==payBtn){
			JOptionPane.showMessageDialog(null, "���� ���� �� �� �����ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			
			
		}

		this.repaint();
		this.revalidate();

		pnlA3.remove(pnlA4);
		pnlA3.remove(pnlA5);
		if (accYes == e.getSource()) {
			pnlA.add(pnlA4);
		} else if (accNo == e.getSource()) {
			pnlA.add(pnlA5);
		}
		pnlA.repaint();
		pnlA.revalidate();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new payment();
	}
}