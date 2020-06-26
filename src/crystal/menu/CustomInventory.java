package crystal.menu;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import crystal.main.CrystalTeleport;
import server.player.PlayerGetHome;

public class CustomInventory implements Listener{
	public ArrayList<Inventory> items = new ArrayList<Inventory>();
	public int page = 0; 
	public  void newInventory(Player player) {
		List<ItemStack> home = PlayerGetHome.getHome(player);
		int size = 9;
		int sizeS = home.size()+1;
		if(sizeS <= 63)
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
		
			items.setItem(i, CrystalOption.delete());
		
			player.openInventory(items);
		}
		else
		{
			@SuppressWarnings("unused")
			int rest = 0;
			if( sizeS % 61 == 0 )
			{
				page = sizeS / 61;
			}
			else
			{
				page = ( sizeS / 61 ) + 1;
				rest = sizeS % 61;
			}
			int i = 0;
			for (int p = 0; p < page; p++)
			{
				items.set(p, CrystalTeleport.server.createInventory(null, size, ChatColor.DARK_GREEN + "Crystal Teleport Page:" + (p + 1) + "/" + page));
				if (p == page-1)
				{
					if (home != null)
						for (int it = 0 ; it <=54; it++)
						{					
							items.get(p).setItem(i, home.get(i));//Create item in menu
							i++;
						}
					home.clear();
					if((size - sizeS ) >0)
						for (int s = size - sizeS ; s > 0 ; s--)
						{
							items.get(p).setItem(i, CrystalOption.None());
							i++;
						}
				}
				else
				{
					for(int it = 0 ; it <=54;it++)
					{
						items.get(p).setItem(i, home.get(i));//Create item in menu
						i++;
					}
				}
			}
			player.openInventory(items.get(0));
		}
	}
}
