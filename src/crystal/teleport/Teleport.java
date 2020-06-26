package crystal.teleport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import crystal.main.colldown;
import server.configuration.LocalConfiguration;
import server.database.Database;
import server.world.Spawn;

public class Teleport {
	public static boolean TeleportPlayer(int id,Player player)
	{
		ResultSet results = Database.executeQuery("SELECT name,x,y,z,world FROM "+ LocalConfiguration.DBPrefix.toString() + "home WHERE id_home="+id);
		try {
			if (results.next())
			{
				double x = results.getDouble("x");
				double y = results.getDouble("y");
				double z = results.getDouble("z");
				World world = Bukkit.getServer().getWorld(UUID.fromString(results.getString("world")));
				Location PlayerLocation = new Location(world,x,y,z);
				colldown.cooldown(player,PlayerLocation,"home");
				results.close();
				return true;
			}
			else
			{
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}

	
	public static boolean TeleportPlayerPremium(int id,Player player)
	{
			ResultSet results = Database.executeQuery("SELECT name,x,y,z,world FROM "+ LocalConfiguration.DBPrefix.toString() + "premium WHERE id_home="+id);
			try {
				if (results.next())
				{
					double x = results.getDouble("x");
					double y = results.getDouble("y");
					double z = results.getDouble("z");
					World world = Bukkit.getServer().getWorld(UUID.fromString(results.getString("world")));
					Location PlayerLocation = new Location(world,x,y,z);
					
					colldown.cooldown(player,PlayerLocation,"home");
					results.close();
					return true;
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		return false;
	}
	public static boolean TeleportPlayer(Player player)
	{
Location spawn = Spawn.getSpawn(player);
			if (spawn != null )
			{
				colldown.cooldown(player,spawn,"");
				return true;
			}
			else	
				return false;
		}

	public static boolean TeleportPlayer(Player player, Location location)
	{
		
		return colldown.cooldown(player, location,"spawn");
	}
}
