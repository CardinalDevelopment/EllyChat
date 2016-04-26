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
import ee.ellytr.chat.component.LanguageComponent;
import ee.ellytr.chat.component.NameComponent;
import ee.ellytr.chat.util.ChatUtil;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Getter
@Setter
public class LocalizedComponent extends FormattableLanguageComponent {

  private ChatConstant constant;

  public LocalizedComponent(ChatConstant constant, BaseComponent... fields) {
    this(constant, Arrays.asList(fields));
  }

  public LocalizedComponent(ChatConstant constant, List<BaseComponent> fields) {
    super(fields);
    this.constant = constant;
  }

  @Override
  public BaseComponent duplicate() {
    LocalizedComponent component = new LocalizedComponent(constant, getFields());
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

  @Override
  public BaseComponent[] getComponents(Locale locale) {
    int i = 0;
    List<BaseComponent> components = new ArrayList<>();
    String message = constant.getMessage(locale);
    for (BaseComponent field : getFields()) {
      String[] parsed = message.split("\\{" + i + "\\}");
      if (parsed.length > 0) {
        String text = parsed[0];
        message = message.substring(text.length() + 2 + (i + "").length());
        components.add(ChatUtil.getTextComponent(text, this));
      } else {
        message = message.substring(2 + (i + "").length());
      }
      if (field instanceof LanguageComponent) {
        Collections.addAll(components, ((LanguageComponent) field).getComponents(locale));
      } else {
        components.add(field);
      }
      i++;
    }
    if (!message.equals("")) {
      components.add(ChatUtil.getTextComponent(message, this));
    }
    BaseComponent[] componentArray = new BaseComponent[components.size()];
    int j = 0;
    for (Object component : components) {
      componentArray[j] = (BaseComponent) component;
      j++;
    }
    return componentArray;
  }

}
