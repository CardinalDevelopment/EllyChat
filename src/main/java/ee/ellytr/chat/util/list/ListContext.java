package ee.ellytr.chat.util.list;

import ee.ellytr.chat.ChatConstant;
import ee.ellytr.chat.component.formattable.ListComponent;
import ee.ellytr.chat.component.formattable.LocalizedComponent;
import ee.ellytr.chat.util.Components;
import lombok.Getter;
import lombok.NonNull;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;

@Getter
public class ListContext {

  private String format;
  private List<BaseComponent> components;

  public ListContext(@NonNull ListComponent component) {
    components = component.getFields();
    int size = components.size();
    if (size > 1) {
      components.add(size - 1, Components.copyProperties(component, new LocalizedComponent(ChatConstant.getConstant("misc.and"))));
    }

    StringBuilder format = new StringBuilder();
    for (int i = 0; i < size; i ++) {
      if (i == 0) {
        format.append("{0}");
      } else if (i + 1 == size) {
        if (size == 2) {
          format.append(" {1} {2}");
        } else {
          format.append(", {").append(i - 1).append("} {").append(i).append("}");
        }
      } else {
        format.append(", {").append(i).append("}");
      }
    }
    this.format = format.toString();
  }

}
