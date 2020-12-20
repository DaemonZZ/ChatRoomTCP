package server;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ServerGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtmes;
	private JButton btnUsers,btnFiles,btnSend;
	private JToggleButton powerSwitch;
	private TCPServer server;
	private JPanel panel;
	private JTextArea mesBox;
	public static ServerGUI s;

	/**
	 * Create the frame.
	 */
	public ServerGUI() {
		s=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 404);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Server Manager");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 19));
		lblNewLabel.setBounds(41, 11, 159, 37);
		contentPane.add(lblNewLabel);

		 powerSwitch = new JToggleButton();
		powerSwitch.setForeground(Color.WHITE);
		powerSwitch.setBackground(Color.WHITE);
		powerSwitch.setBounds(303, 17, 56, 35);
		contentPane.add(powerSwitch);
		Icon onIcon =loadIco("ico/on.png",60,35);
		powerSwitch.setSelectedIcon(onIcon);
		Icon offIcon = loadIco("ico/off.png",60,35);
		powerSwitch.setIcon(offIcon);
		powerSwitch.setBorder(null);
		powerSwitch.setHorizontalAlignment(JToggleButton.CENTER);

		 btnUsers = new JButton("Users");
		btnUsers.setBounds(348, 101, 76, 23);
		contentPane.add(btnUsers);

		 btnFiles = new JButton("Files");
		btnFiles.setBounds(348, 171, 76, 23);
		contentPane.add(btnFiles);

		 btnSend = new JButton("Send");
		btnSend.setBounds(335, 300, 89, 54);
		contentPane.add(btnSend);

		txtmes = new JTextField();
		txtmes.setForeground(UIManager.getColor("CheckBox.focus"));
		txtmes.setBounds(10, 300, 305, 54);

		contentPane.add(txtmes);
		txtmes.setColumns(10);

		panel = new JPanel();
		panel.setBounds(10, 60, 331, 225);
		panel.setLayout(new GridLayout());
		contentPane.add(panel);
		mesBox = new JTextArea();
		mesBox.setBorder(BorderFactory.createLineBorder(Color.blue));
		//mesBox.setEditable(false);
		panel.add(mesBox);

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

	public JTextArea getMesBox() {
		return mesBox;
	}

	public void setMesBox(JTextArea mesBox) {
		this.mesBox = mesBox;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public TCPServer getServer() {
		return server;
	}

	public void setServer(TCPServer server) {
		this.server = server;
	}

	public JTextField getTxtmes() {
		return txtmes;
	}

	public void setTxtmes(JTextField txtmes) {
		this.txtmes = txtmes;
	}

	public JButton getBtnUsers() {
		return btnUsers;
	}

	public void setBtnUsers(JButton btnUsers) {
		this.btnUsers = btnUsers;
	}

	public JButton getBtnFiles() {
		return btnFiles;
	}

	public void setBtnFiles(JButton btnFiles) {
		this.btnFiles = btnFiles;
	}

	public JButton getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(JButton btnSend) {
		this.btnSend = btnSend;
	}

	public JToggleButton getPowerSwitch() {
		return powerSwitch;
	}

	public void setPowerSwitch(JToggleButton powerSwitch) {
		this.powerSwitch = powerSwitch;
	}
}
