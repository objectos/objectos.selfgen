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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import objectos.code.ClassName;

final class BaseAttributesStep extends ThisTemplate {

  public BaseAttributesStep(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(ClassName.of(HTML_PACKAGE, "BaseAttributes"));

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
    public sealed abstract class \{simpleName} extends Recorder permits BaseElements {
      \{simpleName}() {}

    \{attributes()}
      /**
       * Generates the {@code clip-path} attribute with the specified value.
       *
       * @param value
       *        the value of the attribute
       *
       * @return an instruction representing this attribute.
       */
      public final \{API}.\{CLIP_PATH_ATTRIBUTE.simpleName()} clipPath(String value) {
        attribute(\{ATTRIBUTE_NAME}.CLIP_PATH, value);
        return \{API}.ATTRIBUTE;
      }
    }
    """;
  }

  private String attributes() {
    List<String> methods;
    methods = new ArrayList<>();

    TemplateSpec template;
    template = spec.template();

    for (var attribute : spec.attributes()) {
      for (String name : attribute.methodNames()) {
        if (!template.shouldIncludeAttribute(name)) {
          continue;
        }

        String returnName;
        returnName = attribute.instructionSimpleName;

        if (returnName == null) {
          if (attribute.global()) {
            returnName = GLOBAL_ATTRIBUTE2.simpleName();
          } else {
            returnName = attribute.elements
                .iterator()
                .next();
          }
        }

        String constantName;
        constantName = attribute.constantName;

        AttributeKind kind;
        kind = attribute.kind();

        if (kind.isString()) {

          methods.add(code."""
              /**
               * Generates the {@code \{attribute.name()}} attribute with the specified value.
               *
               * @param value
               *        the value of the attribute
               *
               * @return an instruction representing this attribute.
               */
              public final \{API}.\{returnName} \{name}(String value) {
                attribute(\{ATTRIBUTE_NAME}.\{constantName}, value);
                return \{API}.ATTRIBUTE;
              }
            """);

        } else {

              methods.add(code."""
              /**
               * Generates the {@code \{attribute.name()}} boolean attribute.
               *
               * @return an instruction representing this attribute.
               */
              public final \{API}.\{returnName} \{name}() {
                attribute(\{ATTRIBUTE_NAME}.\{constantName});
                return \{API}.ATTRIBUTE;
              }
            """);

        }
      }
    }

    return methods.stream().collect(Collectors.joining("\n"));
  }

}
