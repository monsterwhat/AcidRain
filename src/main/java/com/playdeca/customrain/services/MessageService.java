package com.playdeca.customrain.services;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class MessageService {
    NamedTextColor blue = NamedTextColor.BLUE;
    NamedTextColor red = NamedTextColor.RED;
    NamedTextColor green = NamedTextColor.GREEN;
    NamedTextColor yellow = NamedTextColor.YELLOW;
    NamedTextColor white = NamedTextColor.WHITE;

    public Component getRainStartMessage() {
        return Component.text("It's raining!").color(blue);
    }

    public Component getRainStopMessage() {
        return Component.text("The rain has stopped.").color(yellow);
    }




}
