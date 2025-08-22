package com.playercount;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;
import net.runelite.client.ui.overlay.components.LineComponent;

class PlayerCountOverlay extends Overlay
{
    private final Client client;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private PlayerCountOverlay(Client client)
    {
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        this.client = client;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();
        String overlayTitle = "Players:";

        List<Player> players = client.getPlayers();
        Player local = client.getLocalPlayer();

        long playerCount = players.stream()
                .filter(p -> p != null && !p.equals(local)) // exclude yourself
                .count();

        // Build overlay title
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(overlayTitle)
                .color(Color.GREEN)
                .build());

        // Set the size of the overlay (width)
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 30,
                0));

        // Add a line on the overlay for world number
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Number:")
                .right(Long.toString(playerCount))
                .build());

        return panelComponent.render(graphics);
    }
}