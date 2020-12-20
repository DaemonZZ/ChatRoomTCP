package client;

import common.Message;
import common.User;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class TCPClient {
    private Socket sk;
    private String host;
    private int port;
    private JTextArea MsgBox;
    private String user;
    public static TCPClient cl;

    public String getUser() {
        return user;
    }

    public TCPClient(String host, int port, JTextArea msgBox, String user) {
        this.host = host;
        this.port = port;
        MsgBox = msgBox;
        this.user=user;
        cl=this;

    }

    public void connectToServer(){
        DataOutputStream dos;
        try {
            sk = new Socket(host,port);
            MsgBox.append("Connected to server! \n");
            dos= new DataOutputStream(sk.getOutputStream());
            dos.writeUTF(user);
            ReadThreadClient readThreadClient=new ReadThreadClient();
            readThreadClient.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSk() {
        return sk;
    }

    public void setSk(Socket sk) {
        this.sk = sk;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public JTextArea getMsgBox() {
        return MsgBox;
    }

    public void setMsgBox(JTextArea msgBox) {
        MsgBox = msgBox;
    }
}
class ReadThreadClient extends Thread{

    @Override
    public void run() {
        Socket sk;
        ObjectInputStream ois;
        try {
            sk= TCPClient.cl.getSk();
            while (true){
                ois = new ObjectInputStream(sk.getInputStream());
                Message ms = (Message) ois.readObject();
                System.out.println(ms.getContent());
                ClientGUI.c.getMsgBox().append(ms.getSender()+": "+ms.getContent()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ReadThreadClient() {
    }
}
