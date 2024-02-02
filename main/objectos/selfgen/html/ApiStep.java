/*
 * Copyright (C) 2015-2023 Objectos Software LTDA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package objectos.selfgen.html;

import java.util.Iterator;
import java.util.stream.Collectors;
import objectos.code.ClassName;
import objectos.code.Code;

final class ApiStep extends ThisTemplate {

  public ApiStep(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(API);

    ClassName iterator;
    iterator = ClassName.of(Iterator.class);

    String extendsAll;
    extendsAll = extendsAll();

    return code."""
    /*
     * Copyright (C) \{COPYRIGHT_YEARS} Objectos Software LTDA.
     *
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     *
     * http://www.apache.org/licenses/LICENSE-2.0
     *
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     */
    package \{packageName};
    \{importList}
    /**
     * Defines the types of the {@link Html} domain-specific language.
     */
    \{GENERATED_MSG}
    public final class \{simpleName} {
      static final Attribute ATTRIBUTE = new Attribute();

      static final Element ELEMENT = new Element();

      static final Fragment FRAGMENT = new Fragment();

      static final NoOp NOOP = new NoOp();

      private \{simpleName}() {}

      /**
       * Represents an instruction that generates part of the output of an HTML template.
       *
       * <p>
       * Unless noted references to a particular instruction MUST NOT be reused.
       */
      public sealed interface Instruction {}

    \{elements()}
    \{attributes()}
      /**
       * Represents an HTML global attribute such as the {@code id} attribute for example.
       */
      public sealed interface GlobalAttribute
          extends
    \{extendsAll} {}

      /**
       * An instruction for an HTML attribute provided by an external object.
       */
      public sealed interface ExternalAttribute extends GlobalAttribute {

        /**
         * Represents a single {@code id} attribute.
         */
        non-sealed interface Id extends ExternalAttribute {
          /**
           * The value of this {@code id} attribute.
           *
           * @return the value of this {@code id} attribute
           */
          String id();
        }

        /**
         * Represents a single {@code class} attribute.
         */
        non-sealed interface StyleClass extends ExternalAttribute {
          /**
           * The value of this {@code class} attribute.
           *
           * @return the value of this {@code class} attribute
           */
          String className();
        }

        /**
         * Represents a set of {@code class} attributes.
         */
        non-sealed interface StyleClassSet extends ExternalAttribute {
          /**
           * Iterator over the {@code class} attribute values of this set.
           *
           * @return an iterator over the values of this set
           */
          \{iterator}<String> classNames();
        }
      }

      /**
       * The attribute instruction.
       */
      static final class Attribute
          implements
    \{extendsAttribute()},
          GlobalAttribute {
        Attribute() {}
      }

      /**
       * The element instruction.
       */
      public static final class Element
          implements
    \{extendsElementContents()} {
        Element() {}
      }

      /**
       * The fragment instruction.
       */
      public static final class Fragment
          implements
    \{extendsAll} {
        Fragment() {}
      }

      /**
       * The no-op instruction.
       */
      public static final class NoOp
          implements
    \{extendsAll} {
        private NoOp() {}
      }
    }
    """;
  }

  private String elements() {
    StringBuilder sb;
    sb = new StringBuilder();

    for (var element : spec.elements()) {
      String thisSimpleName;
      thisSimpleName = element.valueSimpleName;

      sb.append(
          code."""
          /**
           * Allowed as an argument to the {@code \{element.name()}} element method.
           */
          public sealed interface \{thisSimpleName} extends Instruction {}

        """
      );
    }

    return sb.toString();
  }

  private String attributes() {
    StringBuilder sb;
    sb = new StringBuilder();

    for (var attribute : spec.attributes()) {
      String thisSimpleName;
      thisSimpleName = attribute.instructionSimpleName;

      if (thisSimpleName == null) {
        continue;
      }

      String superTypes;
      superTypes = attribute.elements
          .stream()
          .collect(Collectors.joining(",\n"));

      superTypes = Code.indent(superTypes, 6);

      sb.append(
          code."""
          /**
           * The {@code \{attribute.name()}} attribute.
           */
          public sealed interface \{thisSimpleName}
              extends
        \{superTypes} {}

        """
      );
    }

    return sb.toString();
  }

  private String extendsAll() {
    String all = spec.elements().stream()
        .map(spec -> spec.valueSimpleName)
        .collect(Collectors.joining(",\n"));

    return Code.indent(all, 6);
  }

  private String extendsAttribute() {
    String s = spec.attributes().stream()
        .map(spec -> spec.instructionSimpleName)
        .filter(simpleName -> simpleName != null)
        .collect(Collectors.joining(",\n"));

    return Code.indent(s, 6);
  }

  private String extendsElementContents() {
    String s = spec.elements().stream()
        .filter(ElementSpec::hasEndTag)
        .map(spec -> spec.valueSimpleName)
        .collect(Collectors.joining(",\n"));

    return Code.indent(s, 6);
  }
}