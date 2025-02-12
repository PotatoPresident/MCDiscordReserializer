/*
 * MCDiscordReserializer: A library for transcoding between Minecraft and Discord.
 * Copyright (C) 2018-2021 Vankka
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package dev.vankka.mcdiscordreserializer.renderer.implementation;

import dev.vankka.mcdiscordreserializer.renderer.MinecraftRenderer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.regex.Pattern;

/**
 * The default implementation for the {@link MinecraftRenderer}.
 */
public class DefaultMinecraftRenderer implements MinecraftRenderer {

    /**
     * The instance of {@link dev.vankka.mcdiscordreserializer.renderer.implementation.DefaultMinecraftRenderer}.
     */
    public static final DefaultMinecraftRenderer INSTANCE = new DefaultMinecraftRenderer();

    private static final Pattern PATTERN_NEWLINE = Pattern.compile("\n");

    /**
     * Creates a new instance of the {@link dev.vankka.mcdiscordreserializer.renderer.implementation.DefaultMinecraftRenderer} unless you're extending the class you shouldn't use this.
     * @see #INSTANCE
     */
    public DefaultMinecraftRenderer() {
    }

    @Override
    public Component strikethrough(Component component) {
        return component.decoration(TextDecoration.STRIKETHROUGH, true);
    }

    @Override
    public Component underline(Component component) {
        return component.decoration(TextDecoration.UNDERLINED, true);
    }

    @Override
    public Component italics(Component component) {
        return component.decoration(TextDecoration.ITALIC, true);
    }

    @Override
    public Component bold(Component component) {
        return component.decoration(TextDecoration.BOLD, true);
    }

    @Override
    public Component codeString(Component component) {
        return component.color(NamedTextColor.DARK_GRAY);
    }

    @Override
    public Component codeBlock(Component component) {
        return component.color(NamedTextColor.DARK_GRAY);
    }

    @Override
    public Component appendSpoiler(Component component, Component content) {
        return component.append(content.decoration(TextDecoration.OBFUSCATED, true)
                .color(NamedTextColor.DARK_GRAY).hoverEvent(HoverEvent.showText(content)));
    }

    @Override
    public Component appendQuote(Component component, Component content) {
        Component prefix = Component.text("| ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD);
        return Component.empty().append(prefix).append(component.replaceText(PATTERN_NEWLINE, builder -> builder.append(prefix)));
    }

    @Override
    public Component appendEmoteMention(Component component, String name, String id) {
        return component.append(Component.text(":" + name + ":"));
    }

    @Override
    public Component appendChannelMention(Component component, String id) {
        return component.append(Component.text("<#" + id + ">"));
    }

    @Override
    public Component appendUserMention(Component component, String id) {
        return component.append(Component.text("<@" + id + ">"));
    }

    @Override
    public Component appendRoleMention(Component component, String id) {
        return component.append(Component.text("<@&" + id + ">"));
    }
}
