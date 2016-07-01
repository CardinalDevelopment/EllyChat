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

import ee.ellytr.chat.ChatConstant;
import ee.ellytr.chat.component.formattable.ListComponent;
import ee.ellytr.chat.component.formattable.LocalizedComponent;
import ee.ellytr.chat.component.formattable.UnlocalizedComponent;
import ee.ellytr.chat.util.Components;
import ee.ellytr.chat.util.time.Time;
import ee.ellytr.chat.util.time.TimeFormat;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class TimeComponent extends LanguageComponent {

  private Time time;
  private TimeFormat format;

  private ChatColor timeColor;
  private ChatColor formatColor;

  public TimeComponent(double time) {
    this.time = new Time(time);

    format = TimeFormat.SECTIONED;
  }

  @Override
  public BaseComponent[] getComponents(Locale locale) {
    if (format.equals(TimeFormat.EXPANDED)) {

      List<BaseComponent> fields = new ArrayList<>();

      int weeks = time.getWeeks();
      if (weeks != 0) {
        UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(weeks + ""));
        time.setColor(timeColor);

        LocalizedComponent format = Components.copyProperties(this,
            new LocalizedComponent(ChatConstant.getConstant(weeks != 1 ? "time.weeks" : "time.week"), time));
        format.setColor(formatColor);

        fields.add(format);
      }

      int days = time.getDays();
      if (days != 0) {
        UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(days + ""));
        time.setColor(timeColor);

        LocalizedComponent format = Components.copyProperties(this,
            new LocalizedComponent(ChatConstant.getConstant(days != 1 ? "time.days" : "time.day"), time));
        format.setColor(formatColor);

        fields.add(format);
      }

      int hours = time.getHours();
      if (hours != 0) {
        UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(hours + ""));
        time.setColor(timeColor);

        LocalizedComponent format = Components.copyProperties(this,
            new LocalizedComponent(ChatConstant.getConstant(hours != 1 ? "time.hours" : "time.hour"), time));
        format.setColor(formatColor);

        fields.add(format);
      }

      int minutes = time.getMinutes();
      if (minutes != 0) {
        UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(minutes + ""));
        time.setColor(timeColor);

        LocalizedComponent format = Components.copyProperties(this,
            new LocalizedComponent(ChatConstant.getConstant(minutes != 1 ? "time.minutes" : "time.minute"), time));
        format.setColor(formatColor);

        fields.add(format);
      }

      double seconds = time.getSeconds();
      if (seconds != 0 || fields.isEmpty()) {
        String secondsValue = seconds + "";
        if (secondsValue.endsWith(".0")) {
          secondsValue = secondsValue.substring(0, secondsValue.length() - 2);
        }

        UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(secondsValue));
        time.setColor(timeColor);

        LocalizedComponent format = Components.copyProperties(this,
            new LocalizedComponent(ChatConstant.getConstant(seconds != 1 ? "time.seconds" : "time.second"), time));
        format.setColor(formatColor);

        fields.add(format);
      }

      ListComponent component = Components.copyProperties(this, new ListComponent(fields));
      component.setColor(formatColor);
      return component.getComponents(locale);

    } else if (format.equals(TimeFormat.SECTIONED)) {

      List<BaseComponent> components = new ArrayList<>();

      List<Integer> sections = new LinkedList<>(Arrays.asList(time.getWeeks(), time.getDays(), time.getHours()));
      boolean append = false;
      int i = 0;

      StringBuilder format = new StringBuilder();
      for (int section : sections) {
        if (section != 0) {
          append = true;
        }
        if (append) {
          format.append("{").append(i).append("}:");
          i++;

          String sectionValue = section + "";
          if (sectionValue.length() == 1) {
            sectionValue = "0" + sectionValue;
          }
          UnlocalizedComponent sectionComponent = Components.copyProperties(this, new UnlocalizedComponent(sectionValue));
          sectionComponent.setColor(timeColor);
          components.add(sectionComponent);
        }
      }
      format.append("{").append(i).append("}:{").append(i + 1).append("}");

      String minutesValue = time.getMinutes() + "";
      if (minutesValue.length() == 1) {
        minutesValue = "0" + minutesValue;
      }
      UnlocalizedComponent minutesComponent = Components.copyProperties(this, new UnlocalizedComponent(minutesValue));
      minutesComponent.setColor(timeColor);
      components.add(minutesComponent);

      String secondsValue = time.getSeconds() + "";
      if (secondsValue.length() == 1) {
        secondsValue = "0" + secondsValue;
      }
      if (secondsValue.endsWith(".0")) {
        secondsValue = secondsValue.substring(0, secondsValue.length() - 2);
      }
      if (secondsValue.length() == 1) {
        secondsValue = "0" + secondsValue;
      }
      UnlocalizedComponent secondsComponent = Components.copyProperties(this, new UnlocalizedComponent(secondsValue));
      secondsComponent.setColor(timeColor);
      components.add(secondsComponent);

      UnlocalizedComponent component = Components.copyProperties(this,
          new UnlocalizedComponent(format.toString(), components));
      component.setColor(formatColor);
      return component.getComponents(locale);

    } else if (format.equals(TimeFormat.SIMPLE)) {

      double seconds = time.getTime();
      String secondsValue = seconds + "";
      if (secondsValue.endsWith(".0")) {
        secondsValue = secondsValue.substring(0, secondsValue.length() - 2);
      }

      UnlocalizedComponent time = Components.copyProperties(this, new UnlocalizedComponent(secondsValue));
      time.setColor(timeColor);

      LocalizedComponent format = Components.copyProperties(this,
          new LocalizedComponent(ChatConstant.getConstant(seconds != 1 ? "time.seconds" : "time.second"), time));
      format.setColor(formatColor);

      return format.getComponents(locale);

    }
    return new BaseComponent[]{};
  }

  @Override
  public TimeComponent duplicate() {
    TimeComponent component = new TimeComponent(time.getTime());
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
    component.setTimeColor(getTimeColor());
    component.setFormatColor(getFormatColor());
    return component;
  }

  public void setTime(double time) {
    this.time.calculate(time);
  }

  @Override
  public void setColor(ChatColor color) {
    super.setColor(color);

    timeColor = color;
    formatColor = color;
  }
}
