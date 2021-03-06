package fr.nil.uplacraft.updater.utils;

import fr.nil.uplacraft.updater.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloaderUtils {
    public static boolean hasFileBeenDownloaded = false;



    private static final int BUFFER_SIZE = 4096;
        public static void startFileDownload(String fileURL , String saveDir)
        {
try{
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10,
                                disposition.length() - 1);
                    }
                } else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                            fileURL.length());
                }

                Core.logToConsole("Content-Type = " + contentType);
                Core.logToConsole("Content-Disposition = " + disposition);
                Core.logToConsole("Content-Length = " + contentLength);
                Core.logToConsole("fileName = " + fileName);

                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                File file = new File(saveFilePath);
                if(!file.exists())file.getParentFile().mkdirs();
                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);



                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);

                }
                for(Player p : Bukkit.getServer().getOnlinePlayers()){
                    if(p.isOp())p.sendMessage(Core.prefix + ChatColor.GREEN + "Server update completed.");
                }
                System.out.println("File downloaded");
                hasFileBeenDownloaded = true;
            } else {
                Core.logToConsole("No file to download. Server replied HTTP code: " + responseCode);
            }
            httpConn.disconnect();


        }catch (IOException e){

e.printStackTrace();
}
        }

    public static void main(String[] args) {
        startFileDownload("http://localhost:1001/latest", "./");
    }

}
