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

import objectos.code.ClassName;

final class HtmlCompilerAttributesStep extends ThisTemplate {

  public HtmlCompilerAttributesStep(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(ClassName.of(WAY_PACKAGE, "HtmlCompilerAttributes"));

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
     * Provides methods for rendering HTML attributes.
     */
    \{GENERATED_MSG}
    abstract class \{simpleName} extends HtmlRecorder {
    
      \{simpleName}() {}

    \{attributes()}
    }
    """;
  }

  private String attributes() {
    return attributeMethods((name, attribute) -> {
      String constantName;
      constantName = attribute.constantName;

      AttributeKind kind;
      kind = attribute.kind();

      if (kind.isString()) {
        return code."""
          /**
           * Generates the {@code \{attribute.name()}} attribute with the specified value.
           *
           * @param value
           *        the value of the attribute
           *
           * @return an instruction representing this attribute.
           */
          public final Html.AttributeInstruction \{name}(String value) {
            \{CHECK}.notNull(value, "value == null");
            return attribute0(HtmlAttributeName.\{constantName}, value);
          }
        """;
      } else {
        return code."""
          /**
           * Generates the {@code \{attribute.name()}} boolean attribute.
           *
           * @return an instruction representing this attribute.
           */
          public final Html.AttributeInstruction \{name}() {
            return attribute0(HtmlAttributeName.\{constantName});
          }
        """;
      }
    });
  }

}
