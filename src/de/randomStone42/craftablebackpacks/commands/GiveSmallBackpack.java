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

public class GiveSmallBackpack implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = Main.getPlugin().getConfig();
		ItemStack smallBackpack = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE).setName("§6§lSmall Backpack")
				.setLore("§6A Small Backpack which can hold up to §6"
						+ Main.getPlugin().getConfig().getString("SmallBackpack.Size") + " §6Items")
				.setColor(config.getInt("SmallBackpack.Color.R"), config.getInt("SmallBackpack.Color.G"),
						config.getInt("SmallBackpack.Color.B"))
				.setUnbreakable().build();
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("craftablebackpacks.givesmallbackpack")) {
					player.getInventory().addItem(smallBackpack);
				} else {
					player.sendMessage("§cNo Permission!");

				}
			} else {
				sender.sendMessage("§cPlease use §6/givesmallbackpacks <PlayerName>§c!");
			}
		} else if (args.length == 1) {
			if (Bukkit.getPlayer(args[0]) != null) {
				Player player = Bukkit.getPlayer(args[0]);
				player.getInventory().addItem(smallBackpack);
			} else {
				sender.sendMessage("Player is not online");
			}
		} else {
			sender.sendMessage("Please use /givesmallbackpack or /givesmallbackpack <PlayerName>");
		}
		return false;
	}
}
