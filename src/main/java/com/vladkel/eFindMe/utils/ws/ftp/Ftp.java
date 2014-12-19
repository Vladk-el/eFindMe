package com.vladkel.eFindMe.utils.ws.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Ftp {

    private static final Logger log = LoggerFactory.getLogger(Ftp.class);

    private FTPClient ftp = new FTPClient();

    public boolean connect(String server, String username, String password) {
        try {
            ftp.connect(server);
            ftp.login(username, password);
            return true;
        } catch (IOException e) {
            log.error("", e);
        }
        return false;
    }

    public void changeWorkingDirectory(String workingDirectory) {
        try {
            if(workingDirectory!=null)
                ftp.changeWorkingDirectory(workingDirectory);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public FTPFile getFile(String fileName) {
        try {
            FTPFile[] files =  ftp.listFiles();
            for(FTPFile file:files)
                if(file.getName().equalsIgnoreCase(fileName))
                    return file;

        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }

    public boolean download(FTPFile from, File to) {
        FileOutputStream fos = null;

        try {
            fos = new  FileOutputStream(to);
            ftp.retrieveFile(from.getName(), fos);
            fos.close();
            return true;
        }catch (IOException e) {
            log.error("",e);
        }
        return false;
    }

    public boolean delete(FTPFile file) {
        try {
            ftp.deleteFile(file.getName());
            return true;
        } catch (IOException e) {
            log.error("",e);
        }
        return false;
    }


    public boolean disconnect() {
        try {
            ftp.disconnect();
            return true;
        } catch (IOException e) {
            log.error("", e);
        }
        return false;
    }




}
