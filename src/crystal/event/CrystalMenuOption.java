package crystal.event;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import crystal.economy.PriseHome;
import crystal.economy.premium.AddPremiumHome;
import crystal.economy.premium.DeletePremiumHome;
import crystal.main.CrystalTeleport;
import crystal.menu.CrystalOption;
import crystal.menu.CustomInventory;
import crystal.menu.DeleteSetHome;
import crystal.teleport.Teleport;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import server.configuration.EconomyConfig;
import server.player.DeleteHome;
import server.player.PlayerHome;
import server.player.addHome;

public class CrystalMenuOption implements Listener{
	public String prefix = (ChatColor.GREEN + "TUTORIAL>> ");

	@EventHandler
	public void InvenClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();

		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();

		if (open == null) {
			return;
		}
			
			
		if (item == null || !item.hasItemMeta()) {
			return;
		}

		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("Teleport") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				player.closeInventory();
				Teleport.TeleportPlayer(item.getItemMeta().getCustomModelData(), player);
								
			}
		}
		
		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("delete") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				DeleteHome.Delete(player,item.getItemMeta().getCustomModelData());
				CustomInventory open1 = new CustomInventory();
				event.setCancelled(true);
				open1.newInventory(player);	
				
			}
		}
		
		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("deletePremium") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				DeletePremiumHome.Delete(player,item.getItemMeta().getCustomModelData());
				CustomInventory open1 = new CustomInventory();
				event.setCancelled(true);
				open1.newInventory(player);	
				
			}
		}
		
		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("PremiumTeleport") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				player.closeInventory();
				Teleport.TeleportPlayerPremium(item.getItemMeta().getCustomModelData(), player);
			}
		}
		
		if ( event.getCurrentItem().getItemMeta().getLocalizedName().equals("delete") ) {
			event.setCancelled(true);
			if (item.getItemMeta().hasCustomModelData())
			{
				DeleteHome.Delete(player,item.getItemMeta().getCustomModelData());
				CustomInventory open1 = new CustomInventory();
				event.setCancelled(true);
				open1.newInventory(player);	
				
			}
		}
		
		if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("SetHome") )
		{
			event.setCancelled(true);
			if(item.isSimilar(CrystalOption.cancel()))
			{
				player.closeInventory();
			}
			else
			{
				Economy economy = CrystalTeleport.economy;
				double withdraw_amount = PriseHome.getPrise(player);///addPremiumHome
                //if the method returns an economyresponse, set the method equal to a reference for one
                //so that you can use it for information on the transaction
                EconomyResponse response = economy.withdrawPlayer(player, withdraw_amount);
				if (response.transactionSuccess()) {
					player.sendMessage(ChatColor.GREEN +"Balance: "+ ChatColor.RED + economy.format(response.balance));
					Location location = player.getLocation();
					double x = location.getX();
					double y = location.getBlockY();
					double z = location.getZ();
					String word = location.getWorld().getUID().toString();
					String name = item.getItemMeta().getDisplayName().replace(ChatColor.GREEN+"","");
					PlayerHome NewHome =new PlayerHome( 0 , name , x, y, z, word);
					@SuppressWarnings("unused")
					addHome home = new addHome(player,NewHome);
					player.closeInventory();
				}
				else
				{
					player.sendMessage(ChatColor.RED+EconomyConfig.NOMONEY.toString());
				}
			}
		}
		
		if (event.getCurrentItem().getItemMeta().getLocalizedName().equals("addPremiumHome") )
		{
			event.setCancelled(true);
			if(item.isSimilar(CrystalOption.cancel()))
			{
				player.closeInventory();
			}
			else
			{
				
				Location location = player.getLocation();
				double x = location.getX();
				double y = location.getBlockY();
				double z = location.getZ();
				String word = location.getWorld().getUID().toString();
				String name = item.getItemMeta().getDisplayName().replace(ChatColor.GREEN+"","");
				PlayerHome NewHome =new PlayerHome( 0 , name , x, y, z, word);
				AddPremiumHome home = null;
				if (player.hasPermission("svip"))
					home = new AddPremiumHome(player,NewHome,"svip");
				else
				if(player.hasPermission("svip"))
					home = new AddPremiumHome(player,NewHome,"vip");
				if(home == null ||!home.exist)
					player.closeInventory();
				else
					player.sendMessage("Brak domów premium do przypisania");
				}
		}
		
		if (event.getCurrentItem().isSimilar(CrystalOption.delete()) )
		{
			event.setCancelled(true);
			DeleteSetHome.DeleteOption(player);
		}
		if (event.getCurrentItem().isSimilar(CrystalOption.deleteCalnel()) )
		{
			CustomInventory open1 = new CustomInventory();
			event.setCancelled(true);
			open1.newInventory(player);	
		}
	}
	
}