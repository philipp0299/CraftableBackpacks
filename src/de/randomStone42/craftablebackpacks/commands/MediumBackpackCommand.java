package de.randomStone42.craftablebackpacks.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.randomStone42.craftablebackpacks.files.BackpackFileHandler;
import de.randomStone42.craftablebackpacks.main.Main;

public class MediumBackpackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("craftablebackpacks.mediumbackpackcommand")) {
				if (args.length == 0) {
					Inventory mediumBackpackInventory = Bukkit.createInventory(player, Main.getPlugin().getConfig().getInt("MediumBackpack.Size"), "§6§lMedium Backpack");
					HashMap<Integer, ItemStack> itemsWithPositions = BackpackFileHandler.loadItemsFromFile(player,
							"MediumBackpack");
					if (itemsWithPositions != null) {
						itemsWithPositions.entrySet().stream().forEach(entry -> {
							mediumBackpackInventory.setItem(entry.getKey(), entry.getValue());
						});
					}
					player.openInventory(mediumBackpackInventory);
				} else {
					player.sendMessage("§cPlease use §6/backpackmedium§c!");
				}
			} else {
				player.sendMessage("§cNo Permission!");
			}
		} else {
			sender.sendMessage("This command can only be executed by players");
		}
		return false;
	}
}
