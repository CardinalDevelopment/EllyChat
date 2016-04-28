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
package ee.ellytr.chat.util.time;

import com.google.common.collect.Lists;
import ee.ellytr.chat.ChatConstant;
import ee.ellytr.chat.component.formattable.LocalizedComponent;
import ee.ellytr.chat.component.formattable.UnlocalizedComponent;
import lombok.Data;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;

@Data
public class TimeContext {

  private final double time;

  private final double seconds;
  private final int minutes;
  private final int hours;
  private final int days;
  private final int weeks;

  private String format;
  private List<BaseComponent> components;

  public void updateFormat(TimeFormat format) {
    components = Lists.newArrayList();
    if (format.equals(TimeFormat.SIMPLE)) {
      this.format = "{0}";
      components.add(new LocalizedComponent(ChatConstant.getConstant(time == 1 ? "time.second" : "time.seconds"),
          new UnlocalizedComponent(time + "")));
    } else if (format.equals(TimeFormat.EXPANDED)) {
      List<BaseComponent> components = Lists.newArrayList();

      if (weeks != 0) {
        components.add(new LocalizedComponent(ChatConstant.getConstant(weeks == 1 ? "time.week" : "time.weeks"),
            new UnlocalizedComponent(weeks + "")));
      }
      if (days != 0) {
        components.add(new LocalizedComponent(ChatConstant.getConstant(days == 1 ? "time.day" : "time.days"),
            new UnlocalizedComponent(days + "")));
      }
      if (hours != 0) {
        components.add(new LocalizedComponent(ChatConstant.getConstant(hours == 1 ? "time.hour" : "time.hours"),
            new UnlocalizedComponent(hours + "")));
      }
      if (minutes != 0) {
        components.add(new LocalizedComponent(ChatConstant.getConstant(minutes == 1 ? "time.minute" : "time.minutes"),
            new UnlocalizedComponent(minutes + "")));
      }
      if (seconds != 0 || components.size() == 0) {
        String secondValue = seconds + "";
        if (secondValue.endsWith(".0")) {
          secondValue = secondValue.substring(0, secondValue.length() - 2);
        }
        components.add(new LocalizedComponent(ChatConstant.getConstant(seconds == 1 ? "time.second" : "time.seconds"),
            new UnlocalizedComponent(secondValue)));
      }

      this.format = "{0}";
      for (int i = 1; i < components.size(); i ++) {
        if (i + 1 == components.size()) {
          components.add(i, new LocalizedComponent(ChatConstant.getConstant("misc.and")));
          if (i == 1) {
            this.format += " {1} {2}";
          } else {
            this.format += ", {" + i + "} {" + (i + 1) + "}";
          }
          break;
        } else {
          this.format += ", {" + i + "}";
        }
      }
      this.components = components;
    } else if (format.equals(TimeFormat.SECTIONED)) {
      this.format = "";

      int i = 0;
      List<Integer> optional = Lists.newArrayList(weeks, days, hours);
      boolean append = false;
      for (int section : optional) {
        if (append) {
          this.format += "{" + i + "}:";
          i ++;

          String sectionValue = section + "";
          if (sectionValue.length() == 1) {
            sectionValue = "0" + sectionValue;
          }
          components.add(new UnlocalizedComponent(sectionValue));
        } else if (section != 0) {
          append = true;

          this.format += "{" + i + "}:";
          i ++;

          components.add(new UnlocalizedComponent(section + ""));
        }
      }
      this.format += "{" + (i ++) + "}:{" + i + "}";

      String minuteValue = minutes + "";
      if (append && minuteValue.length() == 1) {
        minuteValue = "0" + minuteValue;
      }
      components.add(new UnlocalizedComponent(minuteValue));

      String secondValue = seconds + "";
      if (secondValue.indexOf(".") == 1) {
        secondValue = "0" + secondValue;
      }
      if (secondValue.endsWith(".0")) {
        secondValue = secondValue.substring(0, secondValue.length() - 2);
      }
      components.add(new UnlocalizedComponent(secondValue));
    }
  }

}
