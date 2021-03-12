package de.warsteiner.ultimatejobs.jobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class PlayerBlockPlaceEvent implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		e.getBlock().setMetadata("placed-by-player",  new FixedMetadataValue(UltimateJobs.getPlugin(), "placed-by-player"));
	}

}
