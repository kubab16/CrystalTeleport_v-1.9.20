package crystal.comand;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import server.configuration.LocalConfiguration;
import server.world.Permision;

public class WorldNewHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String alias, String[] arg) {
		
		if((sender.isOp() || sender.hasPermission("admin"))&& LocalConfiguration.ComandGive.toBoolean() && arg.length == 1)
		{	
			Player player = (Player)sender;
			switch(arg[0])
			{
			case "enable":
				Permision.SetPermision(player, true);
				return true;
			case "disamble":
				Permision.SetPermision(player, false);
				return true;
			default:
				return false;
			}
			
		}
		return false;
	}

}
