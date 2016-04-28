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

import ee.ellytr.chat.component.formattable.UnlocalizedComponent;
import ee.ellytr.chat.util.Components;
import ee.ellytr.chat.util.time.TimeContext;
import ee.ellytr.chat.util.time.TimeFormat;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.Locale;

@Getter
@Setter
public class TimeComponent extends LanguageComponent {

  private double time;
  private TimeFormat format;

  private TimeContext context;

  public TimeComponent(double time) {
    this.time = time;
    format = TimeFormat.SECTIONED;

    context = calculateTime(time);
    context.updateFormat(format);
  }

  @Override
  public BaseComponent[] getComponents(Locale locale) {
    return Components.copyProperties(this,
        new UnlocalizedComponent(context.getFormat(), context.getComponents()).getComponents(locale));
  }

  @Override
  public TimeComponent duplicate() {
    TimeComponent component = new TimeComponent(time);
    component.setColor(getColor());
    component.setBold(isBold());
    component.setItalic(isItalic());
    component.setUnderlined(isUnderlined());
    component.setStrikethrough(isStrikethrough());
    component.setObfuscated(isObfuscated());
    component.setClickEvent(getClickEvent());
    component.setHoverEvent(getHoverEvent());
    if (getExtra() != null) {
      for (BaseComponent extra : getExtra()) {
        component.addExtra(extra.duplicate());
      }
    }
    return component;
  }

  public void setTime(double time) {
    this.time = time;
    context = calculateTime(time);
  }

  public void setFormat(TimeFormat format) {
    this.format = format;
    context.updateFormat(format);
  }

  private TimeContext calculateTime(double time) {
    int flooredTime = (int) time;
    int weeks = flooredTime / 604800; //604800 seconds are in a week
    int days = flooredTime / 86400 - weeks * 7; //86400 seconds are in a day
    int hours = flooredTime / 3600 - weeks * 168 - days * 24;
    int minutes = flooredTime / 60 - weeks * 10080 - days * 1440 - hours * 60;
    double seconds = time - weeks * 604800 - days * 86400 - hours * 3600 - minutes * 60;

    return new TimeContext(time, seconds, minutes, hours, days, weeks);
  }
}
