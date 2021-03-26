package fr.nil.uplacraft.updater;

import fr.nil.uplacraft.updater.commands.CheckForUpdateCommand;
import fr.nil.uplacraft.updater.commands.UpdateCommand;
import fr.nil.uplacraft.updater.manager.ServerManager;
import fr.nil.uplacraft.updater.utils.JarUtils;
import fr.nil.uplacraft.updater.utils.SignatureUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import fr.nil.uplacraft.updater.utils.ChatColor;
public class Core extends JavaPlugin {
public static String prefix = "§e[§bUplaUpdater§e] §c>> §r";

public static String consolePrefix = ChatColor.YELLOW+"["+ChatColor.BLUE +"UplaUpdater"+ChatColor.YELLOW+"]"+ChatColor.RED+" >> "+ChatColor.RESET;
public static boolean isUptoDate = SignatureUtils.isUpdated(ServerManager.getOnlineFileMD5(),SignatureUtils.getLocalMD5());
    public Core() throws IOException {
    }

    @Override
    public void onLoad() {
        logToConsole("Loading UplaUpdater...");
    }

    @Override
    public void onDisable() {
        logToConsole("Disabling UplaUpdater.");
    }

    @Override
    public void onEnable() {
        logToConsole("Loaded UplaUpdater");
        logToConsole("UplaUpdater is now enabled !");


            checkForUpdate();
            logToConsole(SignatureUtils.execCmd("md5sum ./server.jar"));
        this.getCommand("checkforupdate").setExecutor(new CheckForUpdateCommand());
        this.getCommand("updateserver").setExecutor(new UpdateCommand());
    }



    public static void checkForUpdate(){
        if(isUptoDate == true){

            logToConsole("The server is up-to-date.");
            for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.isOp()) {

                    p.sendMessage(prefix + ChatColor.GREEN + "The server is up-to-date !");

                }
            }
        }else{

            logToConsole("The server is not up-to-date.");
            logToConsole("You can use in-game /updateserver to update it (Bear in mind that the server will need to restart to update fully).");
            for(Player p : Bukkit.getServer().getOnlinePlayers()){
                if(p.isOp()){

                    p.sendMessage(prefix + "The server is not up-to-date, you can use /updateserver to update it.");

                }


            }
        }


    }
    public static void logToConsole(String message){

        System.out.print(consolePrefix + message);

    }
}
