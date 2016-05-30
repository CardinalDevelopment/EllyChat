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

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Locale;

public class ChatUtil {

  public static Locale getLocale(String locale) {
    if (locale.contains("_")) {
      String[] parsedLocale = locale.split("_");
      return new Locale(parsedLocale[0], parsedLocale[1]);
    }
    return new Locale(locale);
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

}
