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

import ee.ellytr.chat.ChatConstant;
import ee.ellytr.chat.util.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.ServerOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NameComponent extends BaseComponent {

  private boolean flairs;
  private boolean hover;
  private ServerOperator operator;

  public NameComponent(ServerOperator operator) {
    setColor(ChatColor.WHITE);
    flairs = true;
    hover = true;
    this.operator = operator;
  }

  @Override
  public NameComponent duplicate() {
    NameComponent component = new NameComponent(operator);
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
    component.setFlairs(flairs);
    component.setHover(hover);
    return component;
  }

  public ServerOperator getOperator() {
    return operator;
  }

  public void setOperator(ServerOperator operator) {
    this.operator = operator;
  }

  public BaseComponent[] getComponents(Locale locale) {
    List<BaseComponent> components = new ArrayList<>();
    ChatColor color = getColor();
    if (operator instanceof OfflinePlayer) {
      OfflinePlayer player = (OfflinePlayer) operator;
      if (flairs) {
        if (player.isOp()) {
          setColor(ChatColor.GOLD);
          components.add(ChatUtil.getTextComponent("\u2756", this));
        }
      }
      setColor(color == null ? (player.isOnline() ? ChatColor.WHITE : ChatColor.DARK_AQUA) : color);
      components.add(ChatUtil.getTextComponent(player.getName(), this));
    } else if (operator instanceof Entity) {
      Entity entity = (Entity) operator;
      setColor(color == null ? ChatColor.GRAY : color);
      components.add(ChatUtil.getTextComponent(entity.getCustomName() != null ? entity.getCustomName() : entity.getName(), this));
    } else {
      if (flairs) {
        setColor(ChatColor.GOLD);
        components.add(ChatUtil.getTextComponent("\u2756", this));
      }
      setColor(color == null ? ChatColor.DARK_AQUA : color);
      components.add(ChatUtil.getTextComponent("Console", this));
    }
    setColor(color);
    if (hover && operator instanceof OfflinePlayer) {
      for (BaseComponent component : components) {
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new LocalizedComponent(ChatConstant.getConstant("player.teleportTo"), new NameComponentBuilder(operator).hover(false).build()).getComponents(locale)));
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + ((OfflinePlayer) operator).getName()));
      }
    }
    BaseComponent[] componentArray = new BaseComponent[components.size()];
    int j = 0;
    for (Object component : components) {
      componentArray[j] = (BaseComponent) component;
      j++;
    }
    return componentArray;
  }

  public boolean hasHover() {
    return hover;
  }

  public void setHover(boolean hover) {
    this.hover = hover;
  }

  public boolean hasFlairs() {
    return flairs;
  }

  public void setFlairs(boolean flairs) {
    this.flairs = flairs;
  }

}
