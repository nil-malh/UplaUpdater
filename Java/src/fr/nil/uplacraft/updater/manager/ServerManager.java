package fr.nil.uplacraft.updater.manager;


import fr.nil.uplacraft.updater.Core;
import fr.nil.uplacraft.updater.utils.SignatureUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static fr.nil.uplacraft.updater.utils.SignatureUtils.execCmd;

public class ServerManager {

    public static String getOnlineFileMD5() {
/*

        String md5 = "";

       Document doc;
        try {
            doc = Jsoup.connect("http://localhost:1002/").get();
            String title = doc.title();

            md5 = doc.body().toString().replace("<body>", "").replace("</body>", "").replace("The server's MD5 is : ", "");
            return md5;
        } catch (IOException e) {
            e.printStackTrace();
        }
return md5;
  */

        String content = null;
        URLConnection connection = null;
        try {
            connection =  new URL("http://localhost:1001").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }


        return content.replace("The server's MD5 is : ", "").replace("./server.jar","").replace(" ", "");
    }

    public static void main(String[] args) {

            System.out.println(Core.prefix + getOnlineFileMD5());
        System.out.println(execCmd("pwd"));

    }
}
