package asd;

import javax.swing.*;

import com.sun.javafx.embed.swing.Disposer;

import java.awt.*;
import java.awt.event.*;

public class payment extends JPanel implements ActionListener {
	JPanel pnl, pnl2, pnl3, // 결제수단, 빈패널, 체크버튼패널
	pnlP, // 휴대폰 패널
	pnlN, pnlN2, pnlN3, pnlN4, pnlN5, pnlN6, // 무통장 패널
	pnlC, // 카드 패널
	pnlA, pnlA1, pnlA2, pnlA3, pnlA4, pnlA5, // 계좌이체 패널
	pnlPa, pnlPa1, pnlPa2; // payco 패널
	
	ButtonGroup pay, // 맨 위 5개 그룹
	noPay, // 무통장 그룹
	accPay, accPay2; // 계좌이체 그룹
	
	JRadioButton phone, noPassbook, card, account, payco, // 폰, 무통장, 카드, 계좌이체, payco
	nopYes, nopNo, // 무통장 현금영수증
	accYes, accNo, // 계좌이체 현금영수증
	ind, ind2, ent, ent2; // 개인, 사업자 버튼
	
	JLabel payLabel, // 체크버튼패널 글씨
	phoneLbl, phoneLbl2, // 휴대폰 결제 라벨
	noLbl, noLbl2, noLbl3, noLbl4, noLbl5, noLbl6, noLbl7, // 무통장 결제 라벨
	cardLbl, cardLbl2, // 카드결제 라벨
	accLbl, accLbl2, accLbl3,accLbl4, accLbl5, accLbl6, accLbl7, accLbl8, accLbl9, // 계좌이체 라벨
	paycoLbl, paycoLbl2, paycoLbl3, paycoLbl4; //payco 라벨
	
	JCheckBox payCheck, accCheck, accCheck2; // 체크 후 결제버튼 활성화 , 에스크로 선택1,2
	
	static JButton payBtn; // 결제하기 버튼
	
	JTextField noTf, noTf2, noTf3, // 무통장 텍스트필드
	acTf, // account 텍스트필드
	accTf, accTf2; // 계좌이체 텍스트필드
	
	String bank[] = { ":::::선택해주세요:::::", "카카오뱅크 3333-07-0152358", "농협 222-2222-2222-22",
					"부산은행 333-3333-3333-33", "신한은행 555555-55-5555555" }; // 은행목록
	JComboBox<String> bankCombo; // 무통장 은행 선택 콤보박스
	String PN[] = { "010", "011", "016", "017", "019", "020" }; // 무통장 휴대폰 앞자리
	String PN2[] = { "010", "011", "016", "017", "019", "020" }; // 계좌이체 휴대폰 앞자리
	JComboBox<String> PNCombo, PNCombo2;

	public payment() {
		this.setLayout(new BorderLayout());
		
		pnl = new JPanel();
		phone = new JRadioButton("휴대폰결제");
		noPassbook = new JRadioButton("무통장입금");
		card = new JRadioButton("카드결제");
		account = new JRadioButton("실시간 계좌이체");
		payco = new JRadioButton("PAYCO");
		payBtn = new JButton("결제하기");
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
		payLabel = new JLabel("결제정보를 확인하였으며, 구매진행에 동의합니다.");
		pnl3.add(payCheck);
		pnl3.add(payLabel, "Center");
		payBtn.setEnabled(false);
		pnl3.add(payBtn, "Center");

		// 휴대폰 결제 패널
		pnlP = new JPanel();
		phoneLbl = new JLabel("!");
		phoneLbl.setForeground(Color.RED);
		phoneLbl2 = new JLabel(" 소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.");
		pnlP.add(phoneLbl);
		pnlP.add(phoneLbl2);

		// 무통장 입금 패널
		pnlN = new JPanel();
		pnlN.setLayout(new GridLayout(5,0));
		
		pnlN2 = new JPanel();
		noLbl = new JLabel("입금자명");
		noTf = new JTextField(10); // 입금자명 필드
		pnlN2.add(noLbl);
		pnlN2.add(noTf);
		
		pnlN3 = new JPanel();
		noLbl2 = new JLabel("입금은행");
		bankCombo = new JComboBox<String>(bank);
		pnlN3.add(noLbl2);
		pnlN3.add(bankCombo);
		
		pnlN4 = new JPanel();
		noLbl3 = new JLabel("현금영수증 신청");
		noPay = new ButtonGroup();
		nopYes = new JRadioButton("현금영수증 신청");
		nopNo = new JRadioButton("신청안함");
		noPay.add(nopYes);
		noPay.add(nopNo);
		pnlN4.add(noLbl3);
		pnlN4.add(nopYes);
		pnlN4.add(nopNo);
		
		pnlN5 = new JPanel();
		noPay = new ButtonGroup();
		ind = new JRadioButton("개인");
		ent = new JRadioButton("사업가");
		noPay.add(ind);
		noPay.add(ent);
		noLbl4 = new JLabel("구분 : ");
		pnlN5.add(noLbl4);
		pnlN5.add(ind);
		pnlN5.add(ent);
		
		pnlN6 = new JPanel();
		noLbl5 = new JLabel("휴대폰 번호 : ");
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

		// 카드결제 패널
		pnlC = new JPanel();
		cardLbl = new JLabel("!");
		cardLbl.setForeground(Color.RED);
		cardLbl2 = new JLabel(" 소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.");
		pnlC.add(cardLbl);
		pnlC.add(cardLbl2);

		// 계좌이체 결제 패널
		pnlA = new JPanel();
		pnlA.setLayout(new GridLayout(5, 0));

		pnlA1 = new JPanel();
		accLbl = new JLabel("예금주명");
		acTf = new JTextField(10); // 예금주명 입력 텍스트필드
		accCheck = new JCheckBox();
		accLbl2 = new JLabel("에스크로(구매안전) 서비스를 적용합니다.");
		pnlA1.add(accLbl);
		pnlA1.add(acTf);
		pnlA1.add(accCheck);
		pnlA1.add(accLbl2);

		pnlA2 = new JPanel();
		accLbl3 = new JLabel("!");
		accLbl3.setForeground(Color.RED);
		accLbl4 = new JLabel("소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.");
		pnlA2.add(accLbl3);
		pnlA2.add(accLbl4);

		pnlA3 = new JPanel();
		accLbl5 = new JLabel("현금영수증 신청");
		accYes = new JRadioButton("현금영수증 신청");

		pnlA4 = new JPanel();
		accPay2 = new ButtonGroup();
		ind2 = new JRadioButton("개인");
		ent2 = new JRadioButton("사업가");
		accPay2.add(ind2);
		accPay2.add(ent2);
		accLbl6 = new JLabel("구분 : ");
		pnlA4.add(accLbl6);
		pnlA4.add(ind2);
		pnlA4.add(ent2);

		pnlA5 = new JPanel();
		accLbl7 = new JLabel("휴대폰 번호 : ");
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

		accNo = new JRadioButton("신청 안함");
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

		// Payco 결제 패널
		pnlPa = new JPanel();
		pnlPa.setLayout(new GridLayout(5, 0));
		paycoLbl = new JLabel("!");
		paycoLbl.setForeground(Color.RED);
		paycoLbl2 = new JLabel("페이코 결제 팝업창에서 비밀번호 입력만으로 빠르고 안전하게 결제가 가능한 서비스 입니다.");
		paycoLbl3 = new JLabel("!");
		paycoLbl3.setForeground(Color.RED);
		paycoLbl4 = new JLabel("www.payco.com 에 회원가입 후, 최초 1회 카드 및 계좌 정보를 등록하셔야 사용 가능합니다.");

		pnlPa1 = new JPanel();
		pnlPa1.add(paycoLbl);
		pnlPa1.add(paycoLbl2);

		pnlPa2 = new JPanel();
		pnlPa2.add(paycoLbl3);
		pnlPa2.add(paycoLbl4);
		pnlPa.add(pnlPa1);
		pnlPa.add(pnlPa2);

		// 모든 결제수단을 더하기

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
			JOptionPane.showMessageDialog(null, "아직 구매 할 수 없습니다.", "Error", JOptionPane.ERROR_MESSAGE);
			
			
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