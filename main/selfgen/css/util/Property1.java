/*
 * Copyright 2023 Objectos Software LTDA.
 *
 * Reprodução parcial ou total proibida.
 */
package selfgen.css.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import objectos.code.Code;
import objectos.selfgen.util.Check;

public final class Property1 extends PropertyClass {

  private final String propertyName;

  private final List<Constant> constants = new ArrayList<>();

  public Property1(String simpleName, String propertyName) {
    super(simpleName);

    this.propertyName = propertyName;
  }

  @Override
  public final void add(String name, String value) {
    Check.notNull(name, "name == null");
    Check.notNull(value, "value == null");

    Constant cte;
    cte = new Constant(name, value);

    constants.add(cte);
  }

  @Override
  final String generate(Code code, SelectorKind selector) {
    return code."""
    public enum \{simpleName} implements \{STYLE_CLASS} {
    \{generatePropertyConstants()}
      private final String className = \{CLASS_SEQ_ID}.next();

      private final String value;

      private \{simpleName}(String value) {
        this.value = value;
      }

      /**
       * Returns the CSS class name.
       *
       * @return the CSS class name
       */
      @Override
      public final String className() {
        return className;
      }

      /**
       * Returns the CSS style rule represented by this utility class.
       *
       * @return the CSS style rule
       */
      @Override
      public final String toString() {
        return \{selector.code} + " { \{propertyName}: " + value + " }";
      }

    }""";
  }

  @Override
  final String javadoc(Code code) {
    return code."""
    /**
     * Utility classes for the {@code \{propertyName}} CSS property.
     */""";
  }

  private String generatePropertyConstants() {
    List<String> result;
    result = new ArrayList<>();

    for (var cte : constants) {
      result.add("  " + cte.name() + "(\"" + Code.escape(cte.value()) + "\")");
    }

    return result.stream().collect(Collectors.joining(",\n\n", "\n", ";\n"));
  }

}