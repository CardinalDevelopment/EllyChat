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

import ee.ellytr.chat.util.ChatUtil;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnlocalizedComponent extends LanguageComponent {

    private String text;
    private List<BaseComponent> fields;

    public UnlocalizedComponent(String text, BaseComponent... fields) {
        this(text, Arrays.asList(fields));
    }

    public UnlocalizedComponent(String text, List<BaseComponent> fields) {
        this.text = text;
        this.fields = fields;
    }

    @Override
    public BaseComponent duplicate() {
        UnlocalizedComponent component = new UnlocalizedComponent(text, fields);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public BaseComponent[] getComponents(String locale) {
        int i = 0;
        List<BaseComponent> components = new ArrayList<>();
        String message = text;
        for (BaseComponent field : fields) {
            String[] parsed = message.split("\\{" + i + "\\}");
            if (parsed.length > 0) {
                String text = parsed[0];
                message = message.substring(text.length() + 2 + (i + "").length());
                components.add(ChatUtil.getTextComponent(text, this));
            } else {
                message = message.substring(2 + (i + "").length());
            }
            if (field instanceof LanguageComponent) {
                for (BaseComponent component : ((LanguageComponent) field).getComponents(locale)) {
                    components.add(component);
                }
            } else if (field instanceof NameComponent) {
                for (BaseComponent component : ((NameComponent) field).getComponents(locale)) {
                    components.add(component);
                }
            } else {
                components.add(field);
            }
            i ++;
        }
        if (!message.equals("")) {
            components.add(ChatUtil.getTextComponent(message, this));
        }
        BaseComponent[] componentArray = new BaseComponent[components.size()];
        int j = 0;
        for (Object component : components) {
            componentArray[j] = (BaseComponent) component;
            j ++;
        }
        return componentArray;
    }

    @Override
    public List<BaseComponent> getFields() {
        return fields;
    }

    @Override
    public void setFields(List<BaseComponent> fields) {
        this.fields = fields;
    }

}
