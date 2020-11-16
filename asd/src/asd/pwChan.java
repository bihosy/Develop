package asd;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class pwChan extends JFrame implements ActionListener {

	// --------DB 테이블 관련 --------//
	String header[] = { "ID", "PW", "이름", "성별", "휴대폰", "주소", "권한" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model;
	JTable tb_Mem;
	// --------DB 테이블 관련 --------//

	// ----------------파일 리드라이트-----------//
	BufferedReader br = null;// 버퍼를 이용해서 만들어진 파일 읽기도구
	PrintWriter pw = null;// 버퍼를 이용해서 만들어진 파일 쓰기도구

	FileReader fr = null;// 파일객체 읽어오기
	FileWriter fw = null;// 파일객체 쓰기

	String gr = "Members.txt";// 경로저장

	String l;// 파일 읽어서 문자열 저장

	// ----------------파일 리드라이트-----------//

	static int pwChanRow;

	JPasswordField pf_newPW; // 새 비밀번호
	JPasswordField pf_newPWC; // 새 비밀번호 확인
	JButton btn_newPW;

	public pwChan() {

		// --------DB 테이블 관련 --------//
		Table_model = new DefaultTableModel(contents, header);
		tb_Mem = new JTable(Table_model);
		// --------DB 테이블 관련 --------//

		pf_newPW = new JPasswordField(10);
		pf_newPWC = new JPasswordField(10);
		btn_newPW = new JButton("변경");

		this.setSize(300, 120);
		this.setLayout(new FlowLayout());

		this.add(pf_newPW);
		this.add(pf_newPWC);
		this.add(btn_newPW);

		btn_newPW.addActionListener(this);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// 읽어온 파일 버퍼에 객체 담기

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

						if (j == 6) {
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
		}
		
		else{
			System.out.println("비밀번호가 다릅니다.");
			Table_model.setRowCount(0);
		}
	}

	public static void main(String[] args) {
		new pwChan();
	}

}
