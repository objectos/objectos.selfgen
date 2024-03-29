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

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BaseAttributesStepTest {

  @Test
  public void execute() {
    HtmlSelfGen spec;
    spec = new HtmlSelfGen() {
      @Override
      protected final void definition() {
        template()
            .skipAttribute("title");

        rootElement()
            .attribute("lang")
            .attribute("title");

        element("a").simpleName("Anchor");

        element("title");

        element("meta").noEndTag();

        element("option")
            .attribute("disabled").booleanType()
            .attribute("label");

        element("select")
            .attribute("disabled").booleanType();
      }
    }.prepare();

    BaseAttributesStep template;
    template = new BaseAttributesStep(spec);

    assertEquals(
        template.toString(),

        """
/*
 * Copyright (C) 2015-2024 Objectos Software LTDA.
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
package objectos.html;

import objectos.lang.object.Check;

/**
 * Provides methods for rendering HTML attributes.
 */
// Generated by objectos.selfgen.HtmlSpec. Do not edit!
public sealed abstract class BaseAttributes extends Recorder permits BaseElements {
  BaseAttributes() {}

  /**
   * Generates the {@code disabled} boolean attribute.
   *
   * @return an instruction representing this attribute.
   */
  public final Api.DisabledAttribute disabled() {
    return attribute0(AttributeName.DISABLED);
  }

  /**
   * Generates the {@code label} attribute with the specified value.
   *
   * @param value
   *        the value of the attribute
   *
   * @return an instruction representing this attribute.
   */
  public final Api.OptionValue label(String value) {
    Check.notNull(value, "value == null");
    return attribute0(AttributeName.LABEL, value);
  }

  /**
   * Generates the {@code lang} attribute with the specified value.
   *
   * @param value
   *        the value of the attribute
   *
   * @return an instruction representing this attribute.
   */
  public final Api.GlobalAttribute lang(String value) {
    Check.notNull(value, "value == null");
    return attribute0(AttributeName.LANG, value);
  }

  /**
   * Generates the {@code clip-path} attribute with the specified value.
   *
   * @param value
   *        the value of the attribute
   *
   * @return an instruction representing this attribute.
   */
  public final Api.ClipPathAttribute clipPath(String value) {
    Check.notNull(value, "value == null");
    return attribute0(AttributeName.CLIP_PATH, value);
  }
}
"""
    );
  }

}
