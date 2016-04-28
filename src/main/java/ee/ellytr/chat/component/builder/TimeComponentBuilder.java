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

import ee.ellytr.chat.component.TimeComponent;
import ee.ellytr.chat.util.time.TimeFormat;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;

import java.util.ArrayList;
import java.util.List;

public class TimeComponentBuilder {

  private double time;
  private ChatColor color;
  private boolean bold;
  private boolean italic;
  private boolean underlined;
  private boolean strikethrough;
  private boolean obfuscated;
  private ClickEvent clickEvent;
  private HoverEvent hoverEvent;
  private List<BaseComponent> extra;
  private TimeFormat format;

  public TimeComponentBuilder(double time) {
    this.time = time;
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

  public TimeComponentBuilder color(ChatColor color) {
    this.color = color;
    return this;
  }

  public TimeComponentBuilder bold(boolean bold) {
    this.bold = bold;
    return this;
  }

  public TimeComponentBuilder italic(boolean italic) {
    this.italic = italic;
    return this;
  }

  public TimeComponentBuilder underlined(boolean underlined) {
    this.underlined = underlined;
    return this;
  }

  public TimeComponentBuilder strikethrough(boolean strikethrough) {
    this.strikethrough = strikethrough;
    return this;
  }

  public TimeComponentBuilder obfuscated(boolean obfuscated) {
    this.obfuscated = obfuscated;
    return this;
  }

  public TimeComponentBuilder clickEvent(ClickEvent clickEvent) {
    this.clickEvent = clickEvent;
    return this;
  }

  public TimeComponentBuilder hoverEvent(HoverEvent hoverEvent) {
    this.hoverEvent = hoverEvent;
    return this;
  }

  public TimeComponentBuilder extra(List<BaseComponent> extra) {
    this.extra = extra;
    return this;
  }

  public TimeComponentBuilder format(TimeFormat format) {
    this.format = format;
    return this;
  }

  public TimeComponent build() {
    TimeComponent component = new TimeComponent(time);
    component.setColor(color);
    component.setBold(bold);
    component.setItalic(italic);
    component.setUnderlined(underlined);
    component.setStrikethrough(strikethrough);
    component.setObfuscated(obfuscated);
    component.setClickEvent(clickEvent);
    component.setHoverEvent(hoverEvent);
    component.setExtra(extra);
    component.setFormat(format);
    return component;
  }

}
