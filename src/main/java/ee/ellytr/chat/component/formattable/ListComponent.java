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
package ee.ellytr.chat.component.formattable;

import ee.ellytr.chat.ChatConstant;
import ee.ellytr.chat.component.FormattableLanguageComponent;
import ee.ellytr.chat.util.Components;
import lombok.NonNull;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ListComponent extends FormattableLanguageComponent {

  public ListComponent(BaseComponent... fields) {
    this(Arrays.asList(fields));
  }

  public ListComponent(@NonNull List<BaseComponent> fields) {
    super(fields);
  }

  @Override
  public BaseComponent[] getComponents(Locale locale) {
    List<BaseComponent> fields = new ArrayList<>(getFields());
    int size = fields.size();
    if (size > 1) {
      fields.add(size - 1, Components.copyProperties(this,
          new LocalizedComponent(ChatConstant.getConstant("misc.and"))));
    }

    StringBuilder format = new StringBuilder();
    for (int i = 0; i < size; i ++) {
      if (i == 0) {
        format.append("{0}");
      } else if (i + 1 == size) {
        if (size == 2) {
          format.append(" {1} {2}");
        } else {
          format.append(", {").append(i).append("} {").append(i + 1).append("}");
        }
      } else {
        format.append(", {").append(i).append("}");
      }
    }

    return Components.copyProperties(this, new UnlocalizedComponent(format.toString(), fields)).getComponents(locale);
  }

  @Override
  public BaseComponent duplicate() {
    ListComponent component = new ListComponent(getFields());
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

}
