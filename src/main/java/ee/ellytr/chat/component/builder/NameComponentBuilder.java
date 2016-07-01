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

import ee.ellytr.chat.component.NameComponent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.List;

public class NameComponentBuilder {

  private ServerOperator operator;

  private ChatColor color;
  private boolean bold;
  private boolean italic;
  private boolean underlined;
  private boolean strikethrough;
  private boolean obfuscated;
  private ClickEvent clickEvent;
  private HoverEvent hoverEvent;
  private List<BaseComponent> extra;
  private List<BaseComponent> flairs;
  private boolean showFlairs;
  private boolean hover;

  public NameComponentBuilder(ServerOperator operator) {
    this.operator = operator;
    color = null;
    bold = false;
    italic = false;
    underlined = false;
    strikethrough = false;
    obfuscated = false;
    clickEvent = null;
    hoverEvent = null;
    extra = new ArrayList<>();
    flairs = new ArrayList<>();
    showFlairs = true;
    hover = true;
  }

  public NameComponentBuilder color(ChatColor color) {
    this.color = color;
    return this;
  }

  public NameComponentBuilder bold(boolean bold) {
    this.bold = bold;
    return this;
  }

  public NameComponentBuilder italic(boolean italic) {
    this.italic = italic;
    return this;
  }

  public NameComponentBuilder underlined(boolean underlined) {
    this.underlined = underlined;
    return this;
  }

  public NameComponentBuilder strikethrough(boolean strikethrough) {
    this.strikethrough = strikethrough;
    return this;
  }

  public NameComponentBuilder obfuscated(boolean obfuscated) {
    this.obfuscated = obfuscated;
    return this;
  }

  public NameComponentBuilder clickEvent(ClickEvent clickEvent) {
    this.clickEvent = clickEvent;
    return this;
  }

  public NameComponentBuilder hoverEvent(HoverEvent hoverEvent) {
    this.hoverEvent = hoverEvent;
    return this;
  }

  public NameComponentBuilder extra(List<BaseComponent> extra) {
    this.extra = extra;
    return this;
  }

  public NameComponentBuilder flair(BaseComponent flair) {
    flairs.add(flair);
    return this;
  }

  public NameComponentBuilder flairs(List<BaseComponent> flairs) {
    this.flairs.addAll(flairs);
    return this;
  }

  public NameComponentBuilder showFlairs(boolean showFlairs) {
    this.showFlairs = showFlairs;
    return this;
  }

  public NameComponentBuilder hover(boolean hover) {
    this.hover = hover;
    return this;
  }

  public NameComponent build() {
    NameComponent component = new NameComponent(operator);
    component.setColor(color);
    component.setBold(bold);
    component.setItalic(italic);
    component.setUnderlined(underlined);
    component.setStrikethrough(strikethrough);
    component.setObfuscated(obfuscated);
    component.setClickEvent(clickEvent);
    component.setHoverEvent(hoverEvent);
    component.setExtra(extra);
    component.setFlairs(flairs);
    component.setShowFlairs(showFlairs);
    component.setHover(hover);
    return component;
  }

}
