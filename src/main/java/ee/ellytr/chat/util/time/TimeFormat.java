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

/**
 * Specifies what format a {@link ee.ellytr.chat.component.TimeComponent} should be displayed in.
 *
 * The following examples are 3661 seconds, or 1 hour, 1 minute, and 1 second long:
 * A simple format would be: 3661 seconds
 * An expanded format would be: 1 hour, 1 minute, and 1 second
 * A sectioned format would be: 1:01:01
 */
public enum TimeFormat {
  SIMPLE, EXPANDED, SECTIONED
}
