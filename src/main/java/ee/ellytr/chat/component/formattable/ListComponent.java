package ee.ellytr.chat.component.formattable;

import ee.ellytr.chat.component.FormattableLanguageComponent;
import ee.ellytr.chat.util.Components;
import ee.ellytr.chat.util.list.ListContext;
import lombok.NonNull;
import net.md_5.bungee.api.chat.BaseComponent;

import java.util.List;
import java.util.Locale;

public class ListComponent extends FormattableLanguageComponent {

  private final ListContext context;

  public ListComponent(@NonNull List<BaseComponent> fields) {
    super(fields);

    context = new ListContext(this);
  }

  @Override
  public BaseComponent[] getComponents(Locale locale) {
    return Components.copyProperties(this, new UnlocalizedComponent(
        context.getFormat(), context.getComponents()).getComponents(locale));
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
