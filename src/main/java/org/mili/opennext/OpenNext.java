package org.mili.opennext;

import net.md_5.bungee.api.plugin.Plugin;

public final class OpenNext extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getProxy().getPluginManager().registerListener(this, new PlayerJoinListener());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
