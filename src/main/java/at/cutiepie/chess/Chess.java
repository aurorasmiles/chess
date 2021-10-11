package at.cutiepie.chess;

import io.papermc.lib.PaperLib;
import org.bukkit.plugin.java.JavaPlugin;

public final class Chess extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!PaperLib.isPaper()) {
            PaperLib.suggestPaper(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
