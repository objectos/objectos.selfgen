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

public class BaseElementsStepTest {

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

    BaseElementsStep template;
    template = new BaseElementsStep(spec);

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

/**
 * Provides methods for rendering HTML elements.
 */
// Generated by objectos.selfgen.HtmlSpec. Do not edit!
public sealed abstract class BaseElements extends BaseAttributes permits Html {
  BaseElements() {}

  /**
   * Generates the {@code a} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element a(Api.AnchorValue... contents) {
    element(StandardElementName.A, contents);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code a} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element a(String text) {
    element(StandardElementName.A, text);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code meta} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element meta(Api.MetaValue... contents) {
    element(StandardElementName.META, contents);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code option} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element option(Api.OptionValue... contents) {
    element(StandardElementName.OPTION, contents);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code option} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element option(String text) {
    element(StandardElementName.OPTION, text);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code select} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element select(Api.SelectValue... contents) {
    element(StandardElementName.SELECT, contents);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code select} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element select(String text) {
    element(StandardElementName.SELECT, text);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code title} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Api.Element title(Api.TitleValue... contents) {
    element(StandardElementName.TITLE, contents);
    return Api.ELEMENT;
  }

  /**
   * Generates the {@code title} attribute or element with the specified text.
   *
   * @param text
   *        the text value of this attribute or element
   *
   * @return an instruction representing this attribute or element.
   */
  public final Api.Element title(String text) {
    ambiguous(Ambiguous.TITLE, text);
    return Api.ELEMENT;
  }

}
"""
    );
  }

}
