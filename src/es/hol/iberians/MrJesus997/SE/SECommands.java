package es.hol.iberians.MrJesus997.SE;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author jesus
 */
public class SECommands implements CommandExecutor{
    private final SignEdit main;
    public SECommands(SignEdit main){
        this.main = main;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("se") || cmd.getName().equalsIgnoreCase("signedit")){
            Player p = (Player) sender;
            if(p.hasPermission("SE.edit") || p.isOp()){
                if(args.length < 1){
                    sendHelp(p);
                }
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("stop")){
                        main.sign.remove(p);
                        main.getColor("&2You have finished editing the sign!", p.getName());
                    }
                }
                if(args.length >= 2){
                    if(!main.sign.containsKey(p)){ 
                       main.getColor("&4First have to click a sign!", p.getName());
                       return true; 
                    } 
                    
                    if(!isNumeric(args[0])){
                       main.getColor("&4&l" + args[0] + " &4is not a number!", p.getName());
                       return true; 
                    }
                    
                    int num = Integer.parseInt(args[0]) - 1;
                    
                    if(num > 3 || num < 0){
                       main.getColor("&4You can not put more than 4 lines.!", p.getName());
                       main.getColor("&4Starts in line 1 and ends at 4.", p.getName());
                       return true; 
                    }
                    
                    String line = "";
                    Sign s = main.sign.get(p);
                    boolean antiLineNumber = true;
                    
                    for(String space : args){
                        if(line != "") line += " ";
                        if(!antiLineNumber){
                            line += space;
                        }
                        antiLineNumber = false;
                    }
                    
                    String line1 = ChatColor.translateAlternateColorCodes('&', line);
                    s.setLine(num, line1);
                    s.update();
                    main.getColor("&2Line saved!, write &f/se stop &2to stop editing.", p.getName());
                }
            }else{
                main.getColor("&4You do not have permission to execute this command!", p.getName());
            }
        }
        return false;
    }
    
    private static boolean isNumeric(String cadena){
	try {
            Integer.parseInt(cadena);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }
    
    public void sendHelp(Player p){
        main.getColor("&9------------------------------------------------", p.getName());
        main.getColor("&2/se &f[&6#line&f] [&dmessage&f] &7&l- &3Change line sign.", p.getName());
        /*main.getColor("&2/se copy &f[&6all/#line&f] &7&l- &3Copy this sign.", p.getName());
        main.getColor("&2/se paste &7&l- &3Paste what you got copied to sign.", p.getName());*/
        main.getColor("&2/se stop &7&l- &3Ends the editing task the sign.", p.getName());
        main.getColor("&eThe maximum of visible characters on the sign is &b&l15&e.", p.getName());
        main.getColor("&9------------------------------------------------", p.getName());
    }
}
