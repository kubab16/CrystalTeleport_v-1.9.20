package crystal.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import crystal.menu.CustomInventory;
import crystal.teleport.CreateCrystalTeleport;



public class OpenMenu implements Listener{
	
	@EventHandler
	public void onPlayerClicks(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = player.getInventory().getItemInMainHand();
		if ( action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR))
		{
			if ( item.isSimilar( CreateCrystalTeleport.crystal() ) ) 
			{
				event.setCancelled(true);
				CustomInventory open = new CustomInventory();
				
				open.newInventory(player);		
			}
		}
			
	}	
}

