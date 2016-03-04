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
package ee.ellytr.chat.util;

import ee.ellytr.chat.component.LanguageComponent;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class ChatUtil {

    public static String getLocale(CommandSender who) {
        String locale;
        if (who instanceof Player) {
            locale = ((Player) who).spigot().getLocale();
        } else {
            locale = Locale.getDefault().toString();
        }
        if (locale.contains("_")) {
            return locale.split("_")[0];
        }
        return locale;
    }

    public static TextComponent getTextComponent(String text, BaseComponent old) {
        TextComponent component = new TextComponent(text);
        component.setColor(old.getColor());
        component.setBold(old.isBold());
        component.setItalic(old.isItalic());
        component.setUnderlined(old.isUnderlined());
        component.setStrikethrough(old.isStrikethrough());
        component.setObfuscated(old.isObfuscated());
        component.setClickEvent(old.getClickEvent());
        component.setHoverEvent(old.getHoverEvent());
        if (old.getExtra() != null) {
            for (BaseComponent extra : old.getExtra()) {
                component.addExtra(extra.duplicate());
            }
        }
        return component;
    }

    public static void sendLanguageComponent(CommandSender sender, LanguageComponent component) {
        String locale = getLocale(sender);
        if (sender instanceof Player) {
            ((Player) sender).spigot().sendMessage(component.getComponents(locale));
            return;
        }
        StringBuilder message = new StringBuilder();
        for (BaseComponent baseComponent : component.getComponents(locale)) {
            message.append(baseComponent.toLegacyText());
        }
        sender.sendMessage(message.toString());
    }

}
