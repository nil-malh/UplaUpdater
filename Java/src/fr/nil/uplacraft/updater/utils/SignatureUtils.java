package fr.nil.uplacraft.updater.utils;

import fr.nil.uplacraft.updater.Core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class SignatureUtils {


    public static boolean isUpdated(String md5s, String md5c) {
        Core.logToConsole("md5s: '" + md5s + "' , md5c: '" + md5c+"'");

        if (md5c.isEmpty() || md5s.isEmpty()) {

            Core.logToConsole("Can't fetch one of the file signatures.");
        return false;


    }else if(md5s.equals(md5c)) {


            
            return true;


        }else if(!md5s.equals(md5c)){
            
            return false;
            
        }


        return false;
    }

    public static String execCmd(String cmd) {
        String result = null;
        try (InputStream inputStream = Runtime.getRuntime().exec(cmd).getInputStream();
             Scanner s = new Scanner(inputStream).useDelimiter("\\A")) {
            result = s.hasNext() ? s.next() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getLocalMD5(){

        String md5 = "";
            String commandOutput = execCmd("md5sum ./server.jar");
            if(!commandOutput.isEmpty()){

                md5 = commandOutput.replace("./server.jar","").replace(" ","").replace("\n","");
                Core.logToConsole("The local file's MD5 is : " + md5);

            }else{
                Core.logToConsole("Can't fetch file MD5 (Might be a permission problem...)");

            }


        return md5;
    }
}
