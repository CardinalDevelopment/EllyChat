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

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Getter
public class LocaleRegistry {

  private HashMap<Locale, List<InputStream>> localeFiles;
  private LocaleFactory factory;

  public LocaleRegistry() {
    localeFiles = Maps.newHashMap();
    factory = new LocaleFactory(this);

    addDefaultLocaleFiles();
  }

  public void addLocaleFile(Locale locale, InputStream stream) {
    if (!localeFiles.containsKey(locale)) {
      localeFiles.put(locale, Lists.newArrayList());
    }
    localeFiles.get(locale).add(stream);
  }

  public void register() {
    factory.build();
  }

  private void addDefaultLocaleFiles() {
    addLocaleFile(new Locale("en", "US"), getClass().getResourceAsStream("/lang/ellyChat/en_US.properties"));
  }

}
