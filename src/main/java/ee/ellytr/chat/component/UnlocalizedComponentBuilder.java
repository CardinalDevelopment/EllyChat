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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnlocalizedComponentBuilder {

  private String text;
  private List<BaseComponent> fields;
  private ChatColor color;
  private boolean bold;
  private boolean italic;
  private boolean underlined;
  private boolean strikethrough;
  private boolean obfuscated;
  private ClickEvent clickEvent;
  private HoverEvent hoverEvent;
  private List<BaseComponent> extra;

  public UnlocalizedComponentBuilder(String text, BaseComponent... fields) {
    this(text, Arrays.asList(fields));
  }

  public UnlocalizedComponentBuilder(String text, List<BaseComponent> fields) {
    this.text = text;
    this.fields = fields;
    color = ChatColor.RESET;
    bold = false;
    italic = false;
    underlined = false;
    strikethrough = false;
    obfuscated = false;
    clickEvent = null;
    hoverEvent = null;
    extra = new ArrayList<>();
  }

  public UnlocalizedComponentBuilder color(ChatColor color) {
    this.color = color;
    return this;
  }

  public UnlocalizedComponentBuilder bold(boolean bold) {
    this.bold = bold;
    return this;
  }

  public UnlocalizedComponentBuilder italic(boolean italic) {
    this.italic = italic;
    return this;
  }

  public UnlocalizedComponentBuilder underlined(boolean underlined) {
    this.underlined = underlined;
    return this;
  }

  public UnlocalizedComponentBuilder strikethrough(boolean strikethrough) {
    this.strikethrough = strikethrough;
    return this;
  }

  public UnlocalizedComponentBuilder obfuscated(boolean obfuscated) {
    this.obfuscated = obfuscated;
    return this;
  }

  public UnlocalizedComponentBuilder clickEvent(ClickEvent clickEvent) {
    this.clickEvent = clickEvent;
    return this;
  }

  public UnlocalizedComponentBuilder hoverEvent(HoverEvent hoverEvent) {
    this.hoverEvent = hoverEvent;
    return this;
  }

  public UnlocalizedComponentBuilder extra(List<BaseComponent> extra) {
    this.extra = extra;
    return this;
  }

  public UnlocalizedComponent build() {
    UnlocalizedComponent component = new UnlocalizedComponent(text, fields);
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
