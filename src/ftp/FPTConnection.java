package ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FPTConnection {
    private static final  String serverAddress = "127.0.0.1";
    private static final int serverPort =21;
    private static final int ftpTimeOut = 60000;
    private static final int bufferSize = 1024*1024;
    private static final String user = "admin";
    private static final String password = "admin";
    private FTPClient ftpClient;

    public FPTConnection( ) {

    }

    public void  connect(){
        ftpClient = new FTPClient();
        System.out.println("Connecting server....");
        ftpClient.setDefaultTimeout(ftpTimeOut);
        try {
            ftpClient.connect(serverAddress,serverPort);
            ftpClient.enterLocalPassiveMode();
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                disconnect();
            }
            else {
                ftpClient.setSoTimeout(ftpTimeOut);
                if(!ftpClient.login(user,password)){
                    throw new IOException("Login failed!");
                }
                ftpClient.setDataTimeout(ftpTimeOut);
                System.out.println("Connected");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void disconnect(){
        if(ftpClient!=null && ftpClient.isConnected()){
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<FTPFile> getlistFile(){
        ArrayList<FTPFile> list = new ArrayList<>();
        connect();
        try {
            FTPFile[] files = ftpClient.listFiles("\\");
            if(files.length>0){
                for (FTPFile f:files
                     ) {
                    list.add(f);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean upload(String path){
        boolean ok = false;
        File uploadfile = new File(path);
        InputStream is;
        try {
            is = new BufferedInputStream(new FileInputStream(uploadfile));
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(bufferSize);
            System.out.println("Bắt đầu tải lên");
            ok = ftpClient.storeFile(uploadfile.getName(),is);
            ftpClient.logout();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok;
    }
    public boolean download(String ftpPath, String savePath){
        OutputStream os = null;
        File downloadFile = new File(savePath);
        boolean ok = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(downloadFile));
            //ftpClient.enterRemotePassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(bufferSize);
            ok = ftpClient.retrieveFile(ftpPath,os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }
}
