package ftp;

import org.apache.commons.net.ftp.FTPFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FtpController implements ActionListener {
    private FtpGUI view;
    private FPTConnection ftp;
    private ArrayList<FTPFile> listFile;

    public FtpController(FtpGUI view) {
        this.view = view;
        view.getBtnUp().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
