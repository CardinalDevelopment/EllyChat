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

import lombok.Getter;

@Getter
public class Time {

  private double time;

  private double seconds;
  private int minutes;
  private int hours;
  private int days;
  private int weeks;

  public Time(double time) {
    calculate(time);
  }

  public void calculate(double time) {
    this.time = time;

    int flooredTime = (int) time;
    weeks = flooredTime / 604800; //604800 seconds are in a week
    days = flooredTime / 86400 - weeks * 7; //86400 seconds are in a day
    hours = flooredTime / 3600 - weeks * 168 - days * 24;
    minutes = flooredTime / 60 - weeks * 10080 - days * 1440 - hours * 60;
    seconds = time - weeks * 604800 - days * 86400 - hours * 3600 - minutes * 60;
  }

}
