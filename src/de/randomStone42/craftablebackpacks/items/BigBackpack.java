package de.randomStone42.craftablebackpacks.items;

import java.io.FileNotFoundException;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.randomStone42.craftablebackpacks.files.BackpackFileHandler;
import de.randomStone42.craftablebackpacks.main.Main;

public class BigBackpack implements Listener {

	@EventHandler
	public void OnBackpackOpen(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		ItemStack item = e.getItem();
		if (item == null)
			return;
		if (!item.hasItemMeta())
			return;
		if (!item.getItemMeta().hasLore() || !item.getItemMeta().hasDisplayName())
			return;
		if (item.getItemMeta().getDisplayName().equals("§6§lBig Backpack")) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (player.hasPermission("craftablebackpacks.openbigbackpack")) {
					Inventory bigBackpackInventory = Bukkit.createInventory(player,
							Main.getPlugin().getConfig().getInt("BigBackpack.Size"), "§6§lBig Backpack");
					HashMap<Integer, ItemStack> itemsWithPositions = BackpackFileHandler.loadItemsFromFile(player,
							"BigBackpack");
					if (itemsWithPositions != null) {
						itemsWithPositions.entrySet().stream().forEach(entry -> {
							bigBackpackInventory.setItem(entry.getKey(), entry.getValue());
						});
					}
					player.openInventory(bigBackpackInventory);
				} else {
					player.sendMessage("§cNo Permission!");
				}
			}
		}
	}

	@EventHandler
	public void OnBackpackClose(InventoryCloseEvent e) throws FileNotFoundException {
		if (!(e.getPlayer() instanceof Player))
			return;
		Player player = (Player) e.getPlayer();
		if (e.getView().getTitle() == "§6§lBig Backpack") {
			HashMap<Integer, ItemStack> itemsWithPositions = new HashMap<>();
			int i = 0;
			for (ItemStack item : e.getInventory()) {
				itemsWithPositions.put(i, item);
				i++;
			}
			BackpackFileHandler.saveItemsInFile(player, itemsWithPositions, "BigBackpack");
		}
	}
}
