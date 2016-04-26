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

package ee.ellytr.chat;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Getter
public class ChatConstant {

  private static List<ChatConstant> constants = Lists.newArrayList();

  private String path;
  private HashMap<Locale, String> messages;

  protected ChatConstant(String path) {
    this.path = path;
    messages = Maps.newHashMap();

    constants.add(this);
  }

  public void addMessage(Locale locale, String message) {
    messages.put(locale, message);
  }

  public static ChatConstant getConstant(String path) {
    for (ChatConstant constant : constants) {
      if (constant.getPath().equals(path)) {
        return constant;
      }
    }
    return new ChatConstant(path);
  }

  public String getMessage(Locale locale) {
    if (messages.size() == 0) {
      return null;
    }
    for (Locale msgLocale : messages.keySet()) {
      if (msgLocale.equals(locale)) {
        return messages.get(msgLocale);
      }
    }
    for (Locale msgLocale : messages.keySet()) {
      if (msgLocale.getLanguage().equals(locale.getLanguage())) {
        return messages.get(msgLocale);
      }
    }
    for (Locale msgLocale : messages.keySet()) {
      return messages.get(msgLocale);
    }
    return null;
  }

}
