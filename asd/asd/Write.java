package asd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Write extends JFrame implements ActionListener, MouseListener, ItemListener, KeyListener {

	// --------DB ���̺� ���� --------//
	String header[] = { "��ǰ��ȣ", "�������", "��ǰ��", "����", "������","����" };
	String contents[][] = {};
	String s = "";
	DefaultTableModel Table_model, top_model, pants_model,shoes_model;
	JTable tb_Pro, tb_top, tb_pants,tb_shoes;
	// --------DB ���̺� ���� --------//

	// ----------------���� �������Ʈ-----------//
	BufferedReader br = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pw = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brtop = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwtop = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brpants = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwpants = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��
	BufferedReader brshoes = null;// ���۸� �̿��ؼ� ������� ���� �б⵵��
	PrintWriter pwshoes = null;// ���۸� �̿��ؼ� ������� ���� ���⵵��

	

	FileReader fr = null;// ���ϰ�ü �о����
	FileWriter fw = null;// ���ϰ�ü ����
	FileReader frtop = null;// ���ϰ�ü �о����
	FileWriter fwtop = null;// ���ϰ�ü ����
	FileReader frpants = null;// ���ϰ�ü �о����
	FileWriter fwpants = null;// ���ϰ�ü ����
	FileReader frshoes = null;// ���ϰ�ü �о����
	FileWriter fwshoes = null;// ���ϰ�ü ����

	String gr = "product.txt";// �������
	String topgr = "top.txt";// �������
	String pantsgr = "pants.txt";// �������
	String shoesgr = "shoes.txt";// �������
	String img_gr = "noimage.png";
	String size = "";

	String l;// ���� �о ���ڿ� ����
	int idx = 0;
	int k = 0;
	// ----------------���� �������Ʈ-----------//

	JTextField fd_title, fd_price, fd_size;
	JTextArea ta_contents;
	JPanel pnl_north, pnl_north1, pnl_north2, pnl_middle, pnl_south;
	JComboBox com_menu;
	JLabel lb_title, lb_upload1, lb_upload2, lb_upload3, lb_image, lb_price, lb_size, lb_class;
	JButton btn_reload, btn_preview;
	static JButton btn_finish;
	ImageIcon img;
	JCheckBox cb_sizeS, cb_sizeM, cb_sizeL;
	JComboBox cb_pro;
	String[] proname = {"����","����","�Ź�"};
	@SuppressWarnings("rawtypes")

	public Write() {
		
		
		// --------DB ���̺� ���� --------//
		Table_model = new DefaultTableModel(contents, header);
		top_model = new DefaultTableModel(contents, header);
		pants_model = new DefaultTableModel(contents, header);
		shoes_model = new DefaultTableModel(contents, header);
		
		
		tb_Pro = new JTable(Table_model);
		tb_top = new JTable(top_model);
		tb_pants = new JTable(pants_model);
		tb_shoes = new JTable(shoes_model);
		// --------DB ���̺� ���� --------//

		try {
			fr = new FileReader(gr);
			br = new BufferedReader(fr);// �о�� ���� ���ۿ� ��ü ���
			
			frtop = new FileReader(topgr);
			brtop = new BufferedReader(frtop);
			
			frpants = new FileReader(pantsgr);
			brpants = new BufferedReader(frpants);
			
			frshoes = new FileReader(shoesgr);
			brshoes = new BufferedReader(frshoes);

			while ((l = br.readLine()) != null) {

				String[] str = l.split("/");

				/*System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.print(str[2] + "/");
				System.out.print(str[3] + "/");
				System.out.print(str[4] + "/");
				System.out.println(str[5]);*/

				Table_model.addRow(str);
				idx = Integer.parseInt(str[0]) + 1;

			}
			while ((l = brtop.readLine()) != null) {

				String[] str = l.split("/");

				/*System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.print(str[2] + "/");
				System.out.print(str[3] + "/");
				System.out.print(str[4] + "/");
				System.out.println(str[5]);*/

				top_model.addRow(str);


			}
			while ((l = brpants.readLine()) != null) {

				String[] str = l.split("/");

				/*System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.print(str[2] + "/");
				System.out.print(str[3] + "/");
				System.out.print(str[4] + "/");
				System.out.println(str[5]);*/

				pants_model.addRow(str);


			}
			while ((l = brshoes.readLine()) != null) {

				String[] str = l.split("/");

				/*System.out.print(str[0] + "/");
				System.out.print(str[1] + "/");
				System.out.print(str[2] + "/");
				System.out.print(str[3] + "/");
				System.out.print(str[4] + "/");
				System.out.println(str[5]);*/

				shoes_model.addRow(str);


			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				fr.close();
				frtop.close();
				frpants.close();
				frshoes.close();
				
				br.close();
				brtop.close();
				brpants.close();
				brshoes.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		this.setSize(1000, 800);
		setLocationRelativeTo(null);
		fd_title = new JTextField(25);
		fd_price = new JTextField(20);
		// fd_size = new JTextField(28);

		// �ؽ�Ʈ������ ���εβ�����,����β�����
		Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
		Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
		ta_contents = new JTextArea(20, 60);
		ta_contents.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));

		cb_sizeS = new JCheckBox("S");
		cb_sizeM = new JCheckBox("M");
		cb_sizeL = new JCheckBox("L");

		pnl_north = new JPanel();
		pnl_north1 = new JPanel();
		pnl_north2 = new JPanel(null);
		pnl_north2.setPreferredSize(new Dimension(350, 130));
		pnl_middle = new JPanel();
		pnl_south = new JPanel();
		pnl_south.setPreferredSize(new Dimension(60, 60));

		lb_title = new JLabel("��ǰ�� :");
		lb_price = new JLabel("���� :");
		lb_class = new JLabel("�з� :");
		lb_size = new JLabel("������ :");
		img = new ImageIcon(img_gr);
		lb_image = new JLabel(img);
		cb_pro = new JComboBox(proname);

		Color color = new Color(176, 196, 222);
		
		btn_reload = new JButton("�ҷ� ����");
		
		lb_title.setBounds(10, 20, 50, 25);
		fd_title.setBounds(80, 20, 250, 25);
		lb_price.setBounds(10, 50, 50, 25);
		fd_price.setBounds(80, 50, 250, 25);
		lb_class.setBounds(10, 80, 50, 25);
		cb_pro.setBounds(80, 80, 60, 25);
		lb_size.setBounds(15, 110, 50, 25);
		cb_sizeS.setBounds(75, 110, 50, 25);
		cb_sizeM.setBounds(125, 110, 50, 25);
		cb_sizeL.setBounds(175, 110, 50, 25);
		
		lb_title.setHorizontalAlignment(JLabel.RIGHT);
		lb_price.setHorizontalAlignment(JLabel.RIGHT);
		lb_class.setHorizontalAlignment(JLabel.RIGHT);
		
		btn_finish = new JButton("���");
		btn_finish.setPreferredSize(new Dimension(90, 30));
		btn_finish.setBackground(color);
		
		//btn_finish.setEnabled(false);
		pnl_north1.add(lb_image);

		pnl_north2.add(lb_title);
		pnl_north2.add(fd_title);
		pnl_north2.add(lb_price);
		pnl_north2.add(fd_price);
		pnl_north2.add(lb_class);
		pnl_north2.add(cb_pro);
		pnl_north2.add(lb_size);
		pnl_north2.add(cb_sizeS);
		pnl_north2.add(cb_sizeM);
		pnl_north2.add(cb_sizeL);

		

		lb_image.addMouseListener(this);
		btn_finish.addActionListener(this);
		cb_sizeS.addItemListener(this);
		cb_sizeM.addItemListener(this);
		cb_sizeL.addItemListener(this);
	

		pnl_north.add(pnl_north1);
		pnl_north.add(pnl_north2);
		pnl_middle.add(ta_contents);
		
		//pnl_middle.add(tb_Pro);
		
		//pnl_south.add(btn_preview);
		pnl_south.add(btn_finish);
		
		pnl_north.setBackground(Color.white);
		pnl_middle.setBackground(Color.white);
		pnl_north2.setBackground(Color.white);
		pnl_south.setBackground(Color.white);
		//pnl_north1.setBackground(color.black);
		cb_sizeL.setBackground(color.white);
		cb_sizeS.setBackground(color.white);
		cb_sizeM.setBackground(color.white);
		//cb_pro.setBackground(color.white);
		fd_price.addKeyListener(this);

		this.add(pnl_north, "North");
		this.add(pnl_middle, "Center");
		this.add(pnl_south, "South");

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Write();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] Pro_str = new String[6];
		String ta_chi = ta_contents.getText();
		ta_chi = ta_chi.replace("\n","<br>");
		
/*		if(fd_title.getText().equals("") && fd_price.getText().equals("")
				&& ta_contents.getText().equals("")) {
			btn_finish.setEnabled(false);
		}
			else {
				btn_finish.setEnabled(true);
			}*/
		if (e.getSource() == btn_finish) {
			
			if(fd_title.getText().equals("")){
				JOptionPane.showMessageDialog(null, "��ǰ���� �Էµ��� �ʾҽ��ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else if(fd_price.getText().equals("")){
				JOptionPane.showMessageDialog(null, "������ �Էµ��� �ʾҽ��ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (cb_sizeL.isSelected()==false&&cb_sizeM.isSelected()==false&&cb_sizeS.isSelected()==false){
				JOptionPane.showMessageDialog(null, "����� ���õ��� �ʾҽ��ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);

			}

			else if(ta_contents.getText().equals("")){
				JOptionPane.showMessageDialog(null, "������ �Էµ��� �ʾҽ��ϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			}
	
			
			
			
			
			else{
			
			Pro_str[0] = idx + "";
			Pro_str[1] = img_gr;
			Pro_str[2] = fd_title.getText();
			Pro_str[3] = fd_price.getText();
			
			Pro_str[4] = size;
			Pro_str[5] = ta_chi;

			Table_model.addRow(Pro_str);
			
			if(cb_pro.getSelectedItem().toString().equals("����")){
				
				top_model.addRow(Pro_str);
				
				try {
					fwtop = new FileWriter(topgr);
					pwtop = new PrintWriter(fwtop);

					for (int i = 0; i < tb_top.getRowCount(); i++) {
						for (int j = 0; j < tb_top.getColumnCount(); j++) {

							if (j == tb_top.getColumnCount()-1) {
								l = top_model.getValueAt(i, j).toString();
								pwtop.print(l);

							} else {
								l = tb_top.getValueAt(i, j).toString();
								pwtop.print(l);
								pwtop.print("/");

							}
						}
						pwtop.println();
					}

				}

				catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} finally {

					try {
						fwtop.close();
						pwtop.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			}
			
			
			
			
			if(cb_pro.getSelectedItem().toString().equals("����")){
				pants_model.addRow(Pro_str);
				
				
				try {
					fwpants = new FileWriter(pantsgr);
					pwpants = new PrintWriter(fwpants);

					for (int i = 0; i < tb_pants.getRowCount(); i++) {
						for (int j = 0; j < tb_pants.getColumnCount(); j++) {

							if (j == tb_pants.getColumnCount()-1) {
								l = pants_model.getValueAt(i, j).toString();
								pwpants.print(l);

							} else {
								l = tb_pants.getValueAt(i, j).toString();
								pwpants.print(l);
								pwpants.print("/");

							}
						}
						pwpants.println();
					}

				}

				catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} finally {

					try {
						fwpants.close();
						pwpants.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			}
			
			
			
			if(cb_pro.getSelectedItem().toString().equals("�Ź�")){
				shoes_model.addRow(Pro_str);
				try {
					fwshoes = new FileWriter(shoesgr);
					pwshoes = new PrintWriter(fwshoes);

					for (int i = 0; i < tb_shoes.getRowCount(); i++) {
						for (int j = 0; j < tb_shoes.getColumnCount(); j++) {

							if (j == tb_shoes.getColumnCount()-1) {
								l = shoes_model.getValueAt(i, j).toString();
								pwshoes.print(l);

							} else {
								l = tb_shoes.getValueAt(i, j).toString();
								pwshoes.print(l);
								pwshoes.print("/");

							}
						}
						pwshoes.println();
					}

				}

				catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} finally {

					try {
						fwshoes.close();
						pwshoes.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
			}
			
			
			
			
			
			
			
			try {
				fw = new FileWriter(gr);
				pw = new PrintWriter(fw);

				for (int i = 0; i < tb_Pro.getRowCount(); i++) {
					for (int j = 0; j < tb_Pro.getColumnCount(); j++) {

						if (j == tb_Pro.getColumnCount()-1) {
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
			
			
			
	
			
			JOptionPane.showMessageDialog(null, "��ǰ����� �Ϸ�Ǿ����ϴ�.", "��ǰ��� �Ϸ�", JOptionPane.PLAIN_MESSAGE);
			System.out.println(size);
			this.setDefaultCloseOperation(3);
			setVisible(false);
			Table_model.setRowCount(0);
			new Shop_admin();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("�̹�������(png,jpg)", "png", "jpg");
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.showOpenDialog(this);
		if (fc.getSelectedFile() != null) {
			img_gr = fc.getSelectedFile().getPath();
			System.out.println(img_gr);
			Image image = null;
			Image resizeImage = null;
			try {
				image = ImageIO.read(new File(img_gr));
				resizeImage = image.getScaledInstance(150, 250, Image.SCALE_SMOOTH);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			lb_image.setIcon(new ImageIcon(resizeImage));
			
			repaint();
			revalidate();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getItemSelectable();

		if (source == cb_sizeS) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				k = k - 1;
				
				
			} else {
				k = k + 1;
				
			}
		}
		if (source == cb_sizeM) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				k = k - 2;
			} else {
				k = k + 2;
			}
		}
		if (source == cb_sizeL) {
			if (e.getStateChange() == ItemEvent.DESELECTED) {
				k = k - 4;
			} else {
				k = k + 4;
			}
		}
		switch(k) {
		case 1 : size = "S(90)"; break;
		case 2 : size = "M(95)"; break;
		case 3 : size = "S(90), M(95)"; break;
		case 4 : size = "L(100)"; break;
		case 5 : size = "S(90), L(100)"; break;
		case 6 : size = "M(95), L(100)"; break;
		case 7 : size = "S(90), M(95), L(100)"; break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		 char caracter = e.getKeyChar();
         if (((caracter < '0') || (caracter > '9'))
                 && (caracter != '\b')) {
             e.consume();
         }
	}

}

