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
package objectos.selfgen;

import java.io.IOException;
import java.util.Set;
import objectos.selfgen.html.HtmlSelfGen;

@SuppressWarnings("exports")
public final class HtmlSpec extends HtmlSelfGen {

  private HtmlSpec() {}

  public static Set<String> elementNamesForCss() {
    var spec = new HtmlSpec();

    return spec.elementNames();
  }

  public static void main(String[] args) throws IOException {
    var spec = new HtmlSpec();

    spec.execute(args);
  }

  @Override
  protected final void definition() {
    template()
        .skipAttribute("clipPath")
        .skipAttribute("form")
        .skipAttribute("label")
        .skipAttribute("title");

    rootElement()
        .attribute("accesskey")
        .attribute("class").as("className").classNameType()
        .attribute("contenteditable")
        .attribute("dir")
        .attribute("draggable")
        .attribute("hidden").booleanType()
        .attribute("id").idType()
        .attribute("lang")
        .attribute("spellcheck")
        .attribute("style").as("inlineStyle")
        .attribute("tabindex")
        .attribute("title")
        .attribute("translate")

        .attribute("onclick")
        .attribute("onsubmit")

        .attribute("aria-hidden")
        .attribute("aria-label")
        .attribute("role");

    text();

    element("html");

    // 4.2 Document metadata

    element("head");

    element("title");

    element("link")
        .attribute("href")
        .attribute("crossorigin")
        .attribute("rel")
        .attribute("rev")
        .attribute("media")
        .attribute("type")
        .attribute("referrerpolicy")
        .attribute("sizes")
        .noEndTag();

    element("meta")
        .attribute("name")
        .attribute("http-equiv")
        .attribute("content")
        .attribute("charset")
        .attribute("property")
        .noEndTag();

    // <style> is not a flow stricly speaking, but according to spec:
    // "Contexts in which this element can be used:
    // (...)
    // In the body, where flow content is expected."
    element("style")
        .attribute("type");

    // 4.3 Sections

    element("body")
        .attribute("onafterprint")
        .attribute("onbeforeprint")
        .attribute("onbeforeunload")
        .attribute("onhashchange")
        .attribute("onlanguagechange")
        .attribute("onmessage")
        .attribute("onoffline")
        .attribute("ononline")
        .attribute("onpagehide")
        .attribute("onpageshow")
        .attribute("onpopstate")
        .attribute("onrejectionhandled")
        .attribute("onstorage")
        .attribute("onunhandledrejection")
        .attribute("onunload")
        .attributeEnd();

    element("article");

    element("section");

    element("nav");

    for (int i = 1; i <= 6; i++) {
      element("h" + i)
          .simpleName("Heading" + i);
    }

    element("hgroup")
        .simpleName("HeadingGroup");

    element("header");

    element("footer");

    // 4.4 Grouping content

    element("p")
        .simpleName("Paragraph");

    element("hr")
        .simpleName("HorizontalRule")
        .noEndTag();

    element("pre");

    element("blockquote")
        .attribute("cite")
        .attributeEnd();

    element("ol")
        .simpleName("OrderedList")
        .attribute("reversed").booleanType()
        .attribute("start")
        .attribute("type")
        .attributeEnd();

    element("ul").simpleName("UnorderedList");

    element("menu");

    element("dl").simpleName("DefinitionList");

    element("dt").simpleName("DefinitionTerm");

    element("dd").simpleName("DefinitionDescription");

    element("figure");

    element("li").simpleName("ListItem");

    element("main");

    element("div");

    // 4.5 Text-level semantics

    // TODO: <a> transparent
    element("a").simpleName("Anchor")
        .attribute("href")
        .attribute("target");

    element("em").simpleName("Emphasis");

    element("strong");

    element("small");

    element("abbr").simpleName("Abbreviation");

    element("code");

    element("samp").simpleName("SampleOutput");

    element("kbd").simpleName("KeyboardInput");

    element("span");

    element("br").simpleName("LineBreak")
        .noEndTag();

    element("sub").simpleName("Subscript");

    element("sup").simpleName("Superscript");

    element("b").simpleName("BringAttentionTo");

    // 4.7 Embedded content

    element("img")
        .simpleName("Image")
        .attribute("alt")
        .attribute("src")
        .attribute("srcset")
        .attribute("width")
        .attribute("height")
        .noEndTag();

    Set<String> svgPresentationProps;
    svgPresentationProps = Set.of(
        "alignment-baseline",
        "baseline-shift",
        "clip-path",
        "clip-rule",
        "color",
        "color-interpolation",
        "color-interpolation-filters",
        "cursor",
        "direction",
        "display",
        "dominant-baseline",
        "fill",
        "fill-opacity",
        "fill-rule",
        "filter",
        "flood-color",
        "flood-opacity",
        "glyph-orientation-horizontal",
        "glyph-orientation-vertical",
        "image-rendering",
        "letter-spacing",
        "lighting-color",
        "marker-end",
        "marker-mid",
        "marker-start",
        "mask",
        "mask-type",
        "opacity",
        "overflow",
        "paint-order",
        "pointer-events",
        "shape-rendering",
        "stop-color",
        "stop-opacity",
        "stroke",
        "stroke-dasharray",
        "stroke-dashoffset",
        "stroke-linecap",
        "stroke-linejoin",
        "stroke-miterlimit",
        "stroke-opacity",
        "stroke-width",
        "text-anchor",
        "text-decoration",
        "text-overflow",
        "text-rendering",
        "transform-origin",
        "unicode-bidi",
        "vector-effect",
        "visibility",
        "white-space",
        "word-spacing",
        "writing-mode"
    );

    element("svg")
        .attributes(svgPresentationProps)
        .attribute("height")
        .attribute("transform")
        .attribute("viewBox")
        .attribute("xmlns")
        .attribute("width")
        .attributeEnd();

    element("clipPath")
        .attributes(svgPresentationProps)
        .attribute("d")
        .attribute("transform")
        .attributeEnd();

    element("defs")
        .attributes(svgPresentationProps)
        .attributeEnd();

    element("g")
        .attributes(svgPresentationProps)
        .attribute("transform");

    element("path")
        .attributes(svgPresentationProps)
        .attribute("d")
        .attribute("transform")
        .attributeEnd();

    // 4.9 Tabular data

    element("table")
        .attribute("align")
        .attribute("border")
        .attribute("cellpadding")
        .attribute("cellspacing")
        .attribute("width");

    element("thead")
        .simpleName("TableHead");

    element("tbody")
        .simpleName("TableBody");

    element("tr")
        .simpleName("TableRow");

    element("th")
        .simpleName("TableHeader");

    element("td")
        .simpleName("TableData");

    // 4.10 Forms

    element("form")
        .attribute("action")
        .attribute("enctype")
        .attribute("method")
        .attribute("name")
        .attribute("target")
        .attributeEnd();

    element("label")
        .attribute("for").as("forAttr", "forElement")
        .attributeEnd();

    element("input")
        .attribute("autocomplete")
        .attribute("autofocus").booleanType()
        .attribute("disabled").booleanType()
        .attribute("name")
        .attribute("placeholder")
        .attribute("readonly").booleanType()
        .attribute("required").booleanType()
        .attribute("type")
        .attribute("value")
        .noEndTag();

    element("button")
        .attribute("disabled").booleanType()
        .attribute("type");

    element("select")
        .attribute("autocomplete")
        .attribute("disabled").booleanType()
        .attribute("form")
        .attribute("multiple").booleanType()
        .attribute("name")
        .attribute("required").booleanType()
        .attribute("size");

    element("optgroup")
        .simpleName("OptionGroup");

    element("option")
        .attribute("disabled").booleanType()
        .attribute("label")
        .attribute("selected").booleanType()
        .attribute("value");

    element("textarea")
        .simpleName("TextArea")
        .attribute("autocomplete")
        .attribute("cols")
        .attribute("dirname")
        .attribute("disabled").booleanType()
        .attribute("form")
        .attribute("maxlength")
        .attribute("minlength")
        .attribute("name")
        .attribute("placeholder")
        .attribute("readonly").booleanType()
        .attribute("required").booleanType()
        .attribute("rows")
        .attribute("wrap");

    element("progress");

    element("fieldset")
        .attribute("disabled").booleanType();

    element("legend");

    // 4.11 Interactive elements

    element("details")
        .attribute("open").booleanType();

    element("summary");

    // 4.12 Scripting

    element("script")
        .attribute("src")
        .attribute("type")
        .attribute("nomodule").booleanType()
        .attribute("async").booleanType()
        .attribute("defer").booleanType()
        .attribute("crossorigin")
        .attribute("integrity")
        .attribute("referrerpolicy");

    element("template");
  }

}