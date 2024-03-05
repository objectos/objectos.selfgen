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

final class GeneratedAttributeNameStep extends ThisTemplate {

  public GeneratedAttributeNameStep(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(ClassName.of(HTML_PACKAGE, "GeneratedAttributeName"));

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
    \{GENERATED_MSG}
    abstract class \{simpleName} {

      \{simpleName}() {}

    \{constants()}
    }
    """;
  }

  private String constants() {
    List<String> constants;
    constants = new ArrayList<>();

    for (var attribute : spec.attributes()) {
      String javaName;
      javaName = attribute.constantName;

      String htmlName;
      htmlName = attribute.name();

      boolean booleanAttribute;
      booleanAttribute = attribute.kind().isBoolean();

      constants.add(
          code."""
            /**
             * The {@code \{htmlName}} attribute.
             */
            public static final \{ATTRIBUTE_NAME} \{javaName} = WayAttributeNameBuilder.create(\"\{htmlName}\", \{booleanAttribute});
          """
      );
    }

    return constants.stream().collect(Collectors.joining("\n"));
  }

}
