/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.hol.iberians.MrJesus997.SE;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author jesus
 */
public class SignEdit extends JavaPlugin{
    public Map<Player, Sign> sign = new HashMap<>();
    public Updater updater;
    
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        getCommand("se").setExecutor(new SECommands(this));
        getCommand("singedit").setExecutor(new SECommands(this));
        
        Bukkit.getPluginManager().registerEvents(new SEListener(this), this);
        
        getColor("&2&lSignEdit was loaded successfully!", Bukkit.getConsoleSender().getName());
        
        if(getConfig().getBoolean("AutoUpdater")){
            updater = new Updater(this, 83506, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
        }
    } 
    
    @Override
    public void onDisable(){
        getColor("&2&lSignEdit is disabled successfully!", Bukkit.getConsoleSender().getName());
    }
    
    public void getColor(String str, String s){
        if(s.equalsIgnoreCase("CONSOLE")){
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', str));
        }else{
            Bukkit.getPlayer(s).sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l[&bSE&f&L] " + str));
        }
    }
}
