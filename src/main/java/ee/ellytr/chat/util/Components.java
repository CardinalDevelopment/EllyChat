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

public class Components {

  public static <T extends BaseComponent> T copyProperties(T from, T to) {
    to.setBold(from.isBold());
    to.setClickEvent(from.getClickEvent());
    to.setColor(from.getColor());
    if (from.getExtra() != null) {
      to.setExtra(from.getExtra());
    }
    to.setHoverEvent(from.getHoverEvent());
    to.setInsertion(from.getInsertion());
    to.setItalic(from.isItalic());
    to.setObfuscated(from.isObfuscated());
    to.setStrikethrough(from.isStrikethrough());
    to.setUnderlined(from.isUnderlined());
    return to;
  }

  @SafeVarargs
  public static <T extends BaseComponent> T[] copyProperties(T from, T... to) {
    for (int i = 0; i < to.length; i ++) {
      BaseComponent component = to[i];
      component.setBold(from.isBold());
      component.setClickEvent(from.getClickEvent());
      component.setColor(from.getColor());
      if (from.getExtra() != null) {
        component.setExtra(from.getExtra());
      }
      component.setHoverEvent(from.getHoverEvent());
      component.setInsertion(from.getInsertion());
      component.setItalic(from.isItalic());
      component.setObfuscated(from.isObfuscated());
      component.setStrikethrough(from.isStrikethrough());
      component.setUnderlined(from.isUnderlined());
    }
    return to;
  }

}
