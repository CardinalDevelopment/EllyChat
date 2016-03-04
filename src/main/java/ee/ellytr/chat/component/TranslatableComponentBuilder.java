/*
 * This file is part of EllyChat.
 *
 * EllyChat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EllyChat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EllyChat.  If not, see <http://www.gnu.org/licenses/>.
 */
package ee.ellytr.chat.component;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.List;

public class TranslatableComponentBuilder {

    private String path;
    private ChatColor color;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean strikethrough;
    private boolean obfuscated;
    private ClickEvent clickEvent;
    private HoverEvent hoverEvent;
    private List<BaseComponent> extra;

    public TranslatableComponentBuilder(String path) {
        this.path = path;
        color = null;
        bold = false;
        italic = false;
        underlined = false;
        strikethrough = false;
        obfuscated = false;
        clickEvent = null;
        hoverEvent = null;
        extra = new ArrayList<>();
    }

    public TranslatableComponentBuilder color(ChatColor color) {
        this.color = color;
        return this;
    }

    public TranslatableComponentBuilder bold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public TranslatableComponentBuilder italic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public TranslatableComponentBuilder underlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public TranslatableComponentBuilder strikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public TranslatableComponentBuilder obfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    public TranslatableComponentBuilder clickEvent(ClickEvent clickEvent) {
        this.clickEvent = clickEvent;
        return this;
    }

    public TranslatableComponentBuilder hoverEvent(HoverEvent hoverEvent) {
        this.hoverEvent = hoverEvent;
        return this;
    }

    public TranslatableComponentBuilder extra(List<BaseComponent> extra) {
        this.extra = extra;
        return this;
    }

    public TranslatableComponent build() {
        TranslatableComponent component = new TranslatableComponent(path);
        component.setColor(color);
        component.setBold(bold);
        component.setItalic(italic);
        component.setUnderlined(underlined);
        component.setStrikethrough(strikethrough);
        component.setObfuscated(obfuscated);
        component.setClickEvent(clickEvent);
        component.setHoverEvent(hoverEvent);
        component.setExtra(extra);
        return component;
    }

}
