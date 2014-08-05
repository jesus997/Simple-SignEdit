/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.hol.iberians.MrJesus997.SE;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author jesus
 */
public class SEListener implements Listener{
    private final SignEdit main;
    public SEListener(SignEdit main){
        this.main = main;
    }
    
    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent e){
        if(main.getConfig().getBoolean("AutoUpdater")){
            main.getColor("&9------------------------------------------------" + main.updater.getLatestName(), e.getPlayer().getName());
            main.getColor("&2A new update to SignEdit is ready to be used.", e.getPlayer().getName());
            main.getColor("&2Please download the new version for the new features!", e.getPlayer().getName());
            main.getColor("" + main.updater.getLatestName(), e.getPlayer().getName());
            main.getColor("&bName: &e" + main.updater.getLatestName(), e.getPlayer().getName());
            main.getColor("&bGame version: &e" + main.updater.getLatestGameVersion(), e.getPlayer().getName());
            main.getColor("&bType: &e" + main.updater.getLatestType(), e.getPlayer().getName());
            main.getColor("&bURL: &e" + main.updater.getLatestFileLink(), e.getPlayer().getName());
            main.getColor("&9------------------------------------------------" + main.updater.getLatestName(), e.getPlayer().getName());
        }
    }
    
    @EventHandler
    public void onClickSignEvent(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.WALL_SIGN || e.getClickedBlock().getType() == Material.SIGN_POST){
                if(e.getPlayer().hasPermission("SE.edit")){
                    //main.signEdit = true;
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    if(!main.sign.containsKey(e.getPlayer())){
                        main.sign.put(e.getPlayer(), sign);
                        
                        main.getColor("&2You have clicked on a sign!", e.getPlayer().getName());
                        main.getColor("&2Write the command in chat &f/se [#line] [YouMessage]", e.getPlayer().getName());
                        main.getColor("&aYour message must be less than 15 characters!", e.getPlayer().getName());
                    }else{
                        main.getColor("&4You're already editing a sign!", e.getPlayer().getName());
                    }
                }
            }
        }
    }
}
