package com.playercount;

import javax.inject.Inject;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
        name = "Player Count",
        description = "Show count of current players on screen",
        tags = {"world", "overlay"}
)
public class PlayerCountPlugin extends Plugin
{
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PlayerCountOverlay overlay;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
    }
}