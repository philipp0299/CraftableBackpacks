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

public class BigBackpackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("craftablebackpacks.bigbackpackcommand")) {
				if (args.length == 0) {
					Inventory bigBackpackInventory = Bukkit.createInventory(player, Main.getPlugin().getConfig().getInt("BigBackpack.Size"), "§6§lBig Backpack");
					HashMap<Integer, ItemStack> itemsWithPositions = BackpackFileHandler.loadItemsFromFile(player,
							"BigBackpack");
					if (itemsWithPositions != null) {
						itemsWithPositions.entrySet().stream().forEach(entry -> {
							bigBackpackInventory.setItem(entry.getKey(), entry.getValue());
						});
					}
					player.openInventory(bigBackpackInventory);
				} else {
					player.sendMessage("§cPlease use §6/backpackbig§c!");
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
