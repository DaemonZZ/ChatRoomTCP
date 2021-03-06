package client;

import common.Message;
import common.User;
import ftp.FPTConnection;
import ftp.FtpGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class ClientGuiController implements ItemListener, ActionListener {
    private ClientGUI view;
    private TCPClient client;

    public ClientGuiController(ClientGUI view) {
        this.view = view;
        view.getBtnSend().addActionListener(this);
        view.getPowerSwitch().addItemListener(this);
        view.getBtnFile().addActionListener(this);
        view.getBtnUser().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Send")){
            if(!view.getTxtmes().getText().equals("")){
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(client.getSk().getOutputStream());
                    Message ms = new Message(client.getUser(),view.getTxtmes().getText());
                    oos.writeObject(ms);
                    view.getTxtmes().setText("");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getActionCommand().equals("Files")){
            new FtpGUI();

        }
        if(e.getActionCommand().equals("Gửi file")){
            JFileChooser fc = new JFileChooser();
            String path = "";
            if(fc.showOpenDialog(view)==JFileChooser.APPROVE_OPTION){
                path = fc.getSelectedFile().getAbsolutePath();
                FPTConnection conn = new FPTConnection();
                conn.connect();
                if(conn.upload(path)){
                    JOptionPane.showMessageDialog(view,"Gửi file thành công");
                }
                else {
                    JOptionPane.showMessageDialog(view,"Gửi file thất bại");
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            if(view.getTxtName().getText().equals("")){
                view.getPowerSwitch().setSelected(false);
                JOptionPane.showMessageDialog(view,"Nhập tên trước!");
            }
            else {
                 client = new TCPClient("localhost",25255, view.getMsgBox(), view.getTxtName().getText());
                client.connectToServer();
                view.getTxtName().setEnabled(false);
            }
        }
        else {
            if(client!=null){
                try {
                    DataOutputStream dos = new DataOutputStream(client.getSk().getOutputStream());
                    dos.writeInt(0);
                    client.disconnect();
                    view.getTxtName().setEnabled(true);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
