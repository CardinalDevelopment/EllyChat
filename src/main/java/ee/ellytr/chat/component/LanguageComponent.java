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

import net.md_5.bungee.api.chat.BaseComponent;

import java.util.Locale;

public abstract class LanguageComponent extends BaseComponent {

  public abstract BaseComponent[] getComponents(Locale locale);

  @Override
  public String toLegacyText() {
    StringBuilder text = new StringBuilder();
    for (BaseComponent component : getComponents(Locale.getDefault())) {
      text.append(component.toLegacyText());
    }
    return text.toString();
  }

  @Override
  public String toPlainText() {
    StringBuilder text = new StringBuilder();
    for (BaseComponent component : getComponents(Locale.getDefault())) {
      text.append(component.toPlainText());
    }
    return text.toString();
  }

}
