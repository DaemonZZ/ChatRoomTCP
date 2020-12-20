package client;

import common.Message;
import common.User;

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

            }
        }
        else {
            if(client!=null){
                try {
                    DataOutputStream dos = new DataOutputStream(client.getSk().getOutputStream());
                    dos.writeInt(0);
                    client.disconnect();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
