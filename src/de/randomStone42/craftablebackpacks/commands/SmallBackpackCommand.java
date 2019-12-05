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

public class SmallBackpackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("craftablebackpacks.smallbackpackcommand")) { 
				if (args.length == 0) {
					Inventory smallBackpackInventory = Bukkit.createInventory(player, Main.getPlugin().getConfig().getInt("SmallBackpack.Size"), "§6§lSmall Backpack");
					HashMap<Integer, ItemStack> itemsWithPositions = BackpackFileHandler.loadItemsFromFile(player,
							"SmallBackpack");
					if (itemsWithPositions != null) {
						itemsWithPositions.entrySet().stream().forEach(entry -> {
							smallBackpackInventory.setItem(entry.getKey(), entry.getValue());
						});
					}
					player.openInventory(smallBackpackInventory);
				} else {
					player.sendMessage("§cPlease use §6/backpacksmall§c!"); 
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
