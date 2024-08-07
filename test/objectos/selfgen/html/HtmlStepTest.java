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

public class HtmlStepTest {

  @Test
  public void execute() {
    HtmlSelfGen spec;
    spec = TestingHtmlSelfGen.SPEC;

    HtmlStep template;
    template = new HtmlStep(spec);

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
 * The <strong>Objectos HTML</strong> main class.
 */
public final class Html {

  // The code below was generated by objectos.selfgen.HtmlSpec. Do not edit!

  // @formatter:off

  /**
   * Represents an instruction that generates part of the output of an HTML
   * template.
   */
  public sealed interface Instruction {}

  /**
   * Class of instructions that are represented by methods of the template class.
   *
   * <p>
   * Instances of this interface MUST NOT be reused in a template.
   */
  public sealed interface MethodInstruction extends Instruction {}

  /**
   * Class of instructions that are allowed as arguments to template
   * methods that represent void elements.
   */
  public sealed interface VoidInstruction extends Instruction {}

  /**
   * An instruction to generate an HTML attribute in template.
   */
  public sealed interface AttributeInstruction extends MethodInstruction, VoidInstruction {}

  /**
   * An instruction to generate a {@code data-on-*} HTML attribute in a template.
   */
  public sealed interface DataOnInstruction extends MethodInstruction, VoidInstruction {}

  /**
   * An instruction to generate an HTML element in a template.
   */
  public sealed interface ElementInstruction extends MethodInstruction {}

  /**
   * An instruction to include an HTML fragment to a template.
   */
  public sealed interface FragmentInstruction extends MethodInstruction, VoidInstruction {}

  /**
   * The no-op instruction.
   */
  public sealed interface NoOpInstruction extends MethodInstruction, VoidInstruction {}

  sealed interface AttributeOrNoOp extends AttributeInstruction, DataOnInstruction, NoOpInstruction {}

  private static final class InstructionImpl
       implements
       AttributeOrNoOp,
       ElementInstruction,
       FragmentInstruction {}

  static final AttributeOrNoOp ATTRIBUTE = new InstructionImpl();
  static final ElementInstruction ELEMENT = new InstructionImpl();
  static final FragmentInstruction FRAGMENT = new InstructionImpl();
  static final AttributeOrNoOp NOOP = new InstructionImpl();

  /**
   * Class of instructions that are represented by object instances.
   *
   * <p>
   * Instances of this interface can be safely reused in multiple templates.
   */
  public sealed interface ObjectInstruction extends Instruction {}

  /**
   * The value of an HTML {@code class} attribute.
   */
  public sealed interface ClassName extends ObjectInstruction, VoidInstruction {

    /**
     * The {@code class} value.
     *
     * @return the {@code class} value
     */
    String value();

  }

  record HtmlClassName(String value) implements ClassName {}

  /**
   * The value of an HTML {@code id} attribute.
   */
  public sealed interface Id extends ObjectInstruction, VoidInstruction {

    /**
     * The {@code id} value.
     *
     * @return the {@code id} value
     */
    String value();

  }

  record HtmlId(String value) implements Id {}

  /**
   * Provides the HTML attributes template methods.
   */
  public sealed static abstract class TemplateAttributes {

    TemplateAttributes() {}

    /**
     * Generates the {@code disabled} boolean attribute.
     *
     * @return an instruction representing this attribute.
     */
    protected final AttributeInstruction disabled() {
      return $attributes().disabled();
    }

    /**
     * Generates the {@code label} attribute with the specified value.
     *
     * @param value
     *        the value of the attribute
     *
     * @return an instruction representing this attribute.
     */
    protected final AttributeInstruction label(String value) {
      return $attributes().label(value);
    }

    /**
     * Generates the {@code lang} attribute with the specified value.
     *
     * @param value
     *        the value of the attribute
     *
     * @return an instruction representing this attribute.
     */
    protected final AttributeInstruction lang(String value) {
      return $attributes().lang(value);
    }

    abstract CompilerAttributes $attributes();

  }

  /**
   * Provides the HTML attributes compiler methods.
   */
  public sealed interface CompilerAttributes permits Compiler {

    /**
     * Generates the {@code disabled} boolean attribute.
     *
     * @return an instruction representing this attribute.
     */
    AttributeInstruction disabled();

    /**
     * Generates the {@code label} attribute with the specified value.
     *
     * @param value
     *        the value of the attribute
     *
     * @return an instruction representing this attribute.
     */
    AttributeInstruction label(String value);

    /**
     * Generates the {@code lang} attribute with the specified value.
     *
     * @param value
     *        the value of the attribute
     *
     * @return an instruction representing this attribute.
     */
    AttributeInstruction lang(String value);

  }

  /**
   * Provides the HTML elements template methods.
   */
  public non-sealed static abstract class TemplateElements extends TemplateAttributes {

    /**
     * Sole constructor.
     */
    TemplateElements() {}

    /**
     * Generates the {@code <!DOCTYPE html>} doctype.
     */
    protected final void doctype() {
      $elements().doctype();
    }

    /**
     * Generates the {@code a} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction a(Instruction... contents) {
      return $elements().a(contents);
    }

    /**
     * Generates the {@code a} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction a(String text) {
      return $elements().a(text);
    }

    /**
     * Generates the {@code meta} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction meta(VoidInstruction... contents) {
      return $elements().meta(contents);
    }

    /**
     * Generates the {@code option} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction option(Instruction... contents) {
      return $elements().option(contents);
    }

    /**
     * Generates the {@code option} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction option(String text) {
      return $elements().option(text);
    }

    /**
     * Generates the {@code select} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction select(Instruction... contents) {
      return $elements().select(contents);
    }

    /**
     * Generates the {@code select} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction select(String text) {
      return $elements().select(text);
    }

    /**
     * Generates the {@code title} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    protected final ElementInstruction title(Instruction... contents) {
      return $elements().title(contents);
    }

    /**
     * Generates the {@code title} attribute or element with the specified text.
     *
     * @param text
     *        the text value of this attribute or element
     *
     * @return an instruction representing this attribute or element.
     */
    protected final ElementInstruction title(String text) {
      return $elements().title(text);
    }

    abstract CompilerElements $elements();

  }

  /**
   * Provides the HTML elements compiler methods.
   */
  public sealed interface CompilerElements permits Compiler {

    /**
     * Generates the {@code <!DOCTYPE html>} doctype.
     */
    void doctype();

    /**
     * Generates the {@code a} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction a(Instruction... contents);

    /**
     * Generates the {@code a} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction a(String text);

    /**
     * Generates the {@code meta} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction meta(VoidInstruction... contents);

    /**
     * Generates the {@code option} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction option(Instruction... contents);

    /**
     * Generates the {@code option} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction option(String text);

    /**
     * Generates the {@code select} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction select(Instruction... contents);

    /**
     * Generates the {@code select} element with the specified text.
     *
     * @param text
     *        the text value of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction select(String text);

    /**
     * Generates the {@code title} element with the specified content.
     *
     * @param contents
     *        the attributes and children of this element
     *
     * @return an instruction representing this element.
     */
    ElementInstruction title(Instruction... contents);

    /**
     * Generates the {@code title} attribute or element with the specified text.
     *
     * @param text
     *        the text value of this attribute or element
     *
     * @return an instruction representing this attribute or element.
     */
    ElementInstruction title(String text);

  }

  // @formatter:on

}
"""
    );
  }

}