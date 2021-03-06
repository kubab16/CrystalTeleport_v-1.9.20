package crystal.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import crystal.main.ConfigSpawn;
import crystal.teleport.CreateCrystalTeleport;
import crystal.teleport.Teleport;
import server.configuration.WorldConfig;

public class Spawn implements Listener{		
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = player.getInventory().getItemInMainHand();

		if ( action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))
		{
			if ( item.isSimilar( CreateCrystalTeleport.spawn() ) )
			{
				event.setCancelled(true);
				if(Teleport.TeleportPlayer(player) && WorldConfig.MuliSpawn.toBoolean())
					;
				else
				{
					if(ConfigSpawn.getLocation()!=null)
					{
						Location location = ConfigSpawn.getLocation().add(0.5, 0, 0.5);
						Teleport.TeleportPlayer(player, location);
					}
					else
					{
						player.sendMessage("Brak spawna na tym świecie");
					}					
					return;	
				}
			}
		}
		else
			return;
		
	}
}

