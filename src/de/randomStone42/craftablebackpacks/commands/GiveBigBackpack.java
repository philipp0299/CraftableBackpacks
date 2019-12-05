package de.randomStone42.craftablebackpacks.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.randomStone42.craftablebackpacks.main.Main;
import de.randomStone42.craftablebackpacks.util.LeatherArmorBuilder;

public class GiveBigBackpack implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		ItemStack bigBackpack = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE).setName("§6§lBig Backpack")
				.setLore("§6A Big Backpack which can hold up to §6"
						+ Main.getPlugin().getConfig().getString("BigBackpack.Size") + " §6Items")
				.setColor(config.getInt("BigBackpack.Color.R"), config.getInt("BigBackpack.Color.G"),
						config.getInt("BigBackpack.Color.B"))
				.setUnbreakable().build();
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("craftablebackpacks.givebigbackpack")) {
					player.getInventory().addItem(bigBackpack);
				} else {
					player.sendMessage("§cNo Permission!");

				}
			} else {
				sender.sendMessage("§cPlease use §6/givebigbackpacks <PlayerName>§c!");
			}
		} else if (args.length == 1) {
			if (Bukkit.getPlayer(args[0]) != null) {
				Player player = Bukkit.getPlayer(args[0]);
				player.getInventory().addItem(bigBackpack);
			} else {
				sender.sendMessage("Player is not online");
			}
		} else {
			sender.sendMessage("Please use /givebigbackpack or /givebigbackpack <PlayerName>");
		}
		return false;
	}
}
