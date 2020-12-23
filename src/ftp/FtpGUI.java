package ftp;

import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class FtpGUI extends JDialog {
	private JPanel panel;
	private JButton btnOk,btnUp;
	FPTConnection conn = new FPTConnection();
	public FtpGUI() {
		setTitle("Danh sách files");
		setSize(530,345);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh sách File");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 135, 23);
		getContentPane().add(lblNewLabel);
		
		 panel = new ListPanel(conn.getlistFile());

		getContentPane().add(panel);
		
		 btnOk = new JButton("Ok");
		btnOk.setBounds(408, 261, 89, 23);
		getContentPane().add(btnOk);
		
		 btnUp = new JButton("Upload");
		btnUp.setBounds(10, 261, 89, 23);
		getContentPane().add(btnUp);
		setVisible(true);
	}
	public JPanel getPanel() {
		return panel;
	}
	public JButton getBtnOk() {
		return btnOk;
	}
	public JButton getBtnUp() {
		return btnUp;
	}
	
}
class ListPanel extends JPanel{
	ArrayList<FTPFile> listfile ;
	MouseListener ms = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
			String name = ((JLabel)e.getSource()).getText();
			JFileChooser fc = new JFileChooser();
			String svPath = "";
			if(fc.showSaveDialog(getParent())==JFileChooser.APPROVE_OPTION){
				svPath=fc.getSelectedFile()+name;
				FPTConnection conn = new FPTConnection();
				conn.connect();
				if(conn.download("/"+name,svPath)){
					JOptionPane.showMessageDialog(getParent(),"Tải file "+name+" thành công!");
				}
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
	};

	public ListPanel(ArrayList<FTPFile> listfile) {
		this.listfile = listfile;
		BoxLayout fl = new BoxLayout(this,BoxLayout.Y_AXIS);
		setLayout(fl);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(10, 36, 487, 210);
		setToolTipText("List files - Click to Download");
		for (FTPFile f: listfile
			 ) {
			JLabel label = new JLabel(f.getName());
			label.setFont(new Font("Tahoma", Font.ITALIC, 11));
			label.setForeground(Color.blue);
			label.setBorder(BorderFactory.createLineBorder(Color.CYAN));
			label.addMouseListener(ms);
			this.add(label);
			System.out.println(label.getText());
		}
	}
}
