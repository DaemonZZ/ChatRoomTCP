package client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.LineBorder;

public class ClientGUI extends JFrame {
	private JTextField txtName;
	private JTextField txtmes;
	private JToggleButton powerSwitch;
	private JButton btnUser,btnFile,btnSend;
	private JTextArea msgBox;
	public static ClientGUI c;
	public ClientGUI() {
		c=this;
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chat Room");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 21));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 21, 152, 48);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nhập tên");
		lblNewLabel_1.setBounds(172, 21, 60, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setBounds(172, 46, 105, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		 powerSwitch = new JToggleButton("");
		powerSwitch.setBackground(Color.WHITE);
		powerSwitch.setBounds(350, 32, 60, 35);
		getContentPane().add(powerSwitch);
		Icon onIcon =loadIco("ico/on.png",60,35);
		powerSwitch.setSelectedIcon(onIcon);
		Icon offIcon = loadIco("ico/off.png",60,35);
		powerSwitch.setIcon(offIcon);
		powerSwitch.setBorder(null);
		powerSwitch.setHorizontalAlignment(JToggleButton.CENTER);
		
		 btnUser = new JButton("Users");
		btnUser.setBounds(342, 116, 89, 23);
		getContentPane().add(btnUser);
		
		 btnFile = new JButton("Gửi File");
		btnFile.setBounds(342, 192, 89, 23);
		getContentPane().add(btnFile);
		
		 btnSend = new JButton("Send");
		btnSend.setBounds(335, 298, 89, 56);
		getContentPane().add(btnSend);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 80, 322, 205);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		 msgBox = new JTextArea();
		msgBox.setBorder(new LineBorder(Color.BLUE));
		msgBox.setEditable(false);
		panel.add(msgBox);
		
		txtmes = new JTextField();
		txtmes.setBounds(10, 298, 322, 56);
		getContentPane().add(txtmes);
		txtmes.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("K\u1EBFt n\u1ED1i");
		lblNewLabel_2.setBounds(289, 21, 46, 14);
		getContentPane().add(lblNewLabel_2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 404);
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	public Icon loadIco(String linkImage, int k, int m) {
		try {
			BufferedImage image = ImageIO.read(new File(linkImage));

			int x = k;
			int y = m;
			int ix = image.getWidth();
			int iy = image.getHeight();
			int dx = 0, dy = 0;

			if (x / y > ix / iy) {
				dy = y;
				dx = dy * ix / iy;
			} else {
				dx = x;
				dy = dx * iy / ix;
			}

			return new ImageIcon(image.getScaledInstance(dx, dy,
					Image.SCALE_SMOOTH));

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtmes() {
		return txtmes;
	}

	public void setTxtmes(JTextField txtmes) {
		this.txtmes = txtmes;
	}

	public JToggleButton getPowerSwitch() {
		return powerSwitch;
	}

	public void setPowerSwitch(JToggleButton powerSwitch) {
		this.powerSwitch = powerSwitch;
	}

	public JButton getBtnUser() {
		return btnUser;
	}

	public void setBtnUser(JButton btnUser) {
		this.btnUser = btnUser;
	}

	public JButton getBtnFile() {
		return btnFile;
	}

	public void setBtnFile(JButton btnFile) {
		this.btnFile = btnFile;
	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}

	public JTextArea getMsgBox() {
		return msgBox;
	}

	public void setMsgBox(JTextArea msgBox) {
		this.msgBox = msgBox;
	}


}
