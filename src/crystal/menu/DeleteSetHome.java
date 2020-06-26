package crystal.menu;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import crystal.main.CrystalTeleport;
import server.player.PlayerGetHome;

public class DeleteSetHome {
	public static void DeleteOption(Player player) {
		List<ItemStack> home = PlayerGetHome.getHomeDelete(player);
		int size = 9;
		int sizeS = home.size()+1;
		if(sizeS <= 54)
		{
		if (sizeS%9 !=0)
		{
			size = ((int)(sizeS/9)+1)*9;
		}
		else
		{
			size = sizeS;
		}
		if (size%9 != 0 | size == 0 )
		{
			size = 9;
		}
		Inventory items = CrystalTeleport.server.createInventory(null, size, ChatColor.DARK_GREEN + "Crystal Teleport");
		int i = 0;
		if (home != null)
			for (ItemStack userHome : home)
			{
				
				items.setItem(i, userHome);//Create item in menu
				i++;
			}
		home.clear();
		if((size - sizeS ) >0)
			for (int s = size - sizeS ; s > 0 ; s--)
			{
				items.setItem(i, CrystalOption.None());
				i++;
			}
		
		items.setItem(i, CrystalOption.deleteCalnel());
		
		player.openInventory(items);
		
		}
	}
}
