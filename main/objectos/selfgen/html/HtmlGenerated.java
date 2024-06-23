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

final class HtmlGenerated extends ThisTemplate {

  HtmlGenerated(HtmlSelfGen spec) {
    super(spec);
  }

  @Override
  final String contents() {
    className(ClassName.of(WAY_PACKAGE, "HtmlGenerated"));

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
     * Provides the generated types for the Objectos HTML main class.
     */
    \{GENERATED_MSG}
    abstract class \{simpleName} {

    \{attributes()}
      \{simpleName}() {}

    }
    """;
  }
  
  private String attributes() {
    return code."""
      /**
       * Represents an instruction that generates part of the output of an HTML
       * template.
       *
       * <p>
       * Unless noted references to a particular instruction MUST NOT be reused.
       */
      public sealed interface Instruction {}
    
      /**
       * An attribute instruction in an HTML template.
       */
      public sealed interface AttributeInstruction extends Instruction {}
    
      private static final class HtmlAttributeInstruction implements AttributeInstruction {}
    
      static final AttributeInstruction ATTRIBUTE = new HtmlAttributeInstruction();
    """;
  }

}