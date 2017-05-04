/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanasaBank.other;

import java.io.IOException;

/**
 *
 * @author Kalhara
 */
public class Backup {

    public static int writeBackup(String path) throws IOException, InterruptedException {
        Runtime runTime = Runtime.getRuntime();
        Process runtimeProcess =runTime.exec("mysqldump SanasaBank -h localhost -u root -pmysql -r "+path+".txt");
        int res=runtimeProcess.waitFor();
        return res;
    }


    public static int restoreBackup(String path) throws IOException, InterruptedException {
        String[] executeCmd = new String[]{"mysql", "SanasaBank", "--user=root" , "--password=mysql", "-e", " source "+path };
        Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
        int res=runtimeProcess.waitFor();
        return res;
    }
    
}
