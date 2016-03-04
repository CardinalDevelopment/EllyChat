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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LocaleFactory {

    private LocaleRegistry registry;

    public void build() {
        for (String locale : registry.getLocaleFiles().keySet()) {
            for (InputStream stream : registry.getLocaleFiles().get(locale)) {
                Properties properties = new Properties();
                try {
                    properties.load(stream);
                } catch (IOException e) {
                    Logger.getLogger("EllyChat").warning("Could not register file for locale \"" + locale + "\"");
                    e.printStackTrace();
                    continue;
                }
                for (String property : properties.stringPropertyNames()) {
                    ChatConstant constant = ChatConstant.getConstant(property);
                    constant.addMessage(locale, properties.getProperty(property));
                }
                properties.clear();
            }
        }
    }

}
