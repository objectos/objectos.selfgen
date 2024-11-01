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

public class HtmlCompilerElementsStepTest {

  @Test
  public void execute() {
    HtmlSelfGen spec;
    spec = TestingHtmlSelfGen.SPEC;

    HtmlCompilerElementsStep template;
    template = new HtmlCompilerElementsStep(spec);

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
package objectos.way;

/**
 * Provides methods for rendering HTML elements.
 */
// Generated by objectos.selfgen.HtmlSpec. Do not edit!
abstract class HtmlCompilerElements extends HtmlCompilerAttributes {

  HtmlCompilerElements() {}

  /**
   * Generates the {@code a} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction a(Html.Instruction... contents) {
    return element(HtmlElementName.A, contents);
  }

  /**
   * Generates the {@code a} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction a(String text) {
    return element(HtmlElementName.A, text);
  }

  /**
   * Generates the {@code meta} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction meta(Html.VoidInstruction... contents) {
    return element(HtmlElementName.META, contents);
  }

  /**
   * Generates the {@code option} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction option(Html.Instruction... contents) {
    return element(HtmlElementName.OPTION, contents);
  }

  /**
   * Generates the {@code option} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction option(String text) {
    return element(HtmlElementName.OPTION, text);
  }

  /**
   * Generates the {@code select} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction select(Html.Instruction... contents) {
    return element(HtmlElementName.SELECT, contents);
  }

  /**
   * Generates the {@code select} element with the specified text.
   *
   * @param text
   *        the text value of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction select(String text) {
    return element(HtmlElementName.SELECT, text);
  }

  /**
   * Generates the {@code title} element with the specified content.
   *
   * @param contents
   *        the attributes and children of this element
   *
   * @return an instruction representing this element.
   */
  public final Html.ElementInstruction title(Html.Instruction... contents) {
    return element(HtmlElementName.TITLE, contents);
  }

  /**
   * Generates the {@code title} attribute or element with the specified text.
   *
   * @param text
   *        the text value of this attribute or element
   *
   * @return an instruction representing this attribute or element.
   */
  public final Html.ElementInstruction title(String text) {
    ambiguous(HtmlAmbiguous.TITLE, text);
    return Html.ELEMENT;
  }

}
"""
    );
  }

}
