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

package ee.ellytr.chat.component.builder;

import ee.ellytr.chat.component.formattable.ListComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListComponentBuilder {

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

  public ListComponentBuilder(BaseComponent... fields) {
    this(Arrays.asList(fields));
  }

  public ListComponentBuilder(List<BaseComponent> fields) {
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

  public ListComponentBuilder color(ChatColor color) {
    this.color = color;
    return this;
  }

  public ListComponentBuilder bold(boolean bold) {
    this.bold = bold;
    return this;
  }

  public ListComponentBuilder italic(boolean italic) {
    this.italic = italic;
    return this;
  }

  public ListComponentBuilder underlined(boolean underlined) {
    this.underlined = underlined;
    return this;
  }

  public ListComponentBuilder strikethrough(boolean strikethrough) {
    this.strikethrough = strikethrough;
    return this;
  }

  public ListComponentBuilder obfuscated(boolean obfuscated) {
    this.obfuscated = obfuscated;
    return this;
  }

  public ListComponentBuilder clickEvent(ClickEvent clickEvent) {
    this.clickEvent = clickEvent;
    return this;
  }

  public ListComponentBuilder hoverEvent(HoverEvent hoverEvent) {
    this.hoverEvent = hoverEvent;
    return this;
  }

  public ListComponentBuilder extra(List<BaseComponent> extra) {
    this.extra = extra;
    return this;
  }

  public ListComponent build() {
    ListComponent component = new ListComponent(fields);
    component.setColor(color);
    component.setBold(bold);
    component.setItalic(italic);
    component.setUnderlined(underlined);
    component.setStrikethrough(strikethrough);
    component.setObfuscated(obfuscated);
    component.setClickEvent(clickEvent);
    component.setHoverEvent(hoverEvent);
    //component.setExtra(extra);
    return component;
  }

}
