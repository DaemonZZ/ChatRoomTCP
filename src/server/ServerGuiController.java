package server;

import common.Message;
import common.User;
import ftp.FPTConnection;
import ftp.FtpGUI;
import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServerGuiController implements ActionListener, ItemListener {
    private ServerGUI view ;
    private TCPServer server;
    public ServerGuiController(ServerGUI view) {
        this.view=view;
        view.getBtnSend().addActionListener(this);
        view.getBtnFiles().addActionListener(this);
        view.getBtnUsers().addActionListener(this);
        view.getPowerSwitch().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Send")){
            if(!view.getTxtmes().getText().equals("")){
                Message ms = new Message("Server",view.getTxtmes().getText());
                try {
                    ObjectOutputStream oos;
                    for (User user: TCPServer.listUser
                    ) {
                        oos = new ObjectOutputStream(user.getSocket().getOutputStream());
                        oos.writeObject(ms);
                    }
                    view.getMesBox().append("Server: "+ms.getContent()+"\n");
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
            TCPServer server =new TCPServer(25255, view.getMesBox());
            view.setServer(server);
            boolean openSuccess= server.open();

            if(openSuccess) {
                server.start();
            }
            else {
                view.getPowerSwitch().setSelected(false);
            }

        }
        else{
            view.getServer().closeServer();
        }
    }
}
