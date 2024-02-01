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

final class BaseElementsStep extends ThisTemplate {

  public BaseElementsStep(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(ClassName.of(HTML_PACKAGE, "BaseElements"));

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
     * Provides methods for rendering HTML elements.
     */
    \{GENERATED_MSG}
    public sealed abstract class \{simpleName} extends BaseAttributes permits Html {
      \{simpleName}() {}

    \{elements()}
      /**
       * Generates the {@code <!DOCTYPE html>} doctype.
       */
      public abstract void doctype();
    }
    """;
  }

  private String elements() {
    List<String> methods;
    methods = new ArrayList<>();

    TemplateSpec template;
    template = spec.template();

    for (var element : spec.elements()) {
      String methodName;
      methodName = element.methodName();

      ClassName paramType;
      paramType = element.instructionClassName2;

      String constantName;
      constantName = element.constantName;

      methods.add(
          code."""
          /**
           * Generates the {@code \{element.name()}} element with the specified content.
           *
           * @param contents
           *        the attributes and children of this element
           *
           * @return an instruction representing this element.
           */
          public final \{ELEMENT_INSTRUCTION} \{methodName}(\{paramType}... contents) {
            element(\{STD_ELEMENT_NAME}.\{constantName}, contents);
            return \{ELEMENT_INSTRUCTION}.INSTANCE;
          }
        """
      );

      if (template.shouldIncludeText(element)) {

        methods.add(
            code."""
            /**
             * Generates the {@code \{element.name()}} element with the specified text.
             *
             * @param text
             *        the text value of this element
             *
             * @return an instruction representing this element.
             */
            public final \{ELEMENT_INSTRUCTION} \{methodName}(String text) {
              element(\{STD_ELEMENT_NAME}.\{constantName}, text);
              return \{ELEMENT_INSTRUCTION}.INSTANCE;
            }
          """
        );

      } else if (spec.isAmbiguous(element)) {

        methods.add(
            code."""
            /**
             * Generates the {@code \{element.name()}} attribute or element with the specified text.
             *
             * @param text
             *        the text value of this attribute or element
             *
             * @return an instruction representing this attribute or element.
             */
            public final \{ELEMENT_INSTRUCTION} \{methodName}(String text) {
              ambiguous(\{AMBIGUOUS}.\{constantName}, text);
              return \{ELEMENT_INSTRUCTION}.INSTANCE;
            }
          """
        );

      }
    }

    return methods.stream().collect(Collectors.joining("\n"));
  }

}
