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

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import objectos.code.Code;

public abstract class HtmlSelfGen {

  final Code code = Code.of();

  private List<AttributeSpec> attributes;

  private final Map<String, AttributeSpec> attributeMap = new TreeMap<>();

  private final Map<String, ElementSpec> elementMap = new TreeMap<>();

  private final RootElementSpec rootElement = new RootElementSpec(this);

  private final TemplateSpec template = new TemplateSpec();

  private final TextSpec text = new TextSpec();

  protected HtmlSelfGen() {}

  public final void execute(String[] args) throws IOException {
    String srcDir;
    srcDir = args[0];

    Path directory;
    directory = Path.of(srcDir);

    prepare();

    writeTo(new HtmlAttributeNameGeneratedStep(this), directory);

    writeTo(new HtmlCompilerAttributesStep(this), directory);

    writeTo(new HtmlCompilerElementsStep(this), directory);

    writeTo(new HtmlElementNameGeneratedStep(this), directory);

    writeTo(new HtmlStep(this), directory);
  }

  public final Set<String> elementNames() {
    definition();

    return elementMap.values()
        .stream()
        .map(ElementSpec::name)
        .collect(Collectors.toUnmodifiableSet());
  }

  public final boolean isAmbiguous(ElementSpec element) {
    String methodName;
    methodName = element.methodName();

    return !template.shouldIncludeAttribute(methodName);
  }

  protected abstract void definition();

  protected final ElementSpec el(String name) {
    return element(name);
  }

  protected final ElementSpec element(String name) {
    return elementMap.computeIfAbsent(name, this::element0);
  }

  protected final RootElementSpec rootElement() {
    return rootElement;
  }

  protected final TemplateSpec template() {
    return template;
  }

  protected final TextSpec text() {
    return text;
  }

  final List<AttributeSpec> attributes() {
    if (attributes == null) {
      attributes = List.copyOf(attributeMap.values());
    }

    return attributes;
  }

  final AttributeSpec attributeSpec(String name) {
    return attributeMap.get(name);
  }

  final Collection<ElementSpec> elements() {
    return elementMap.values();
  }

  final AttributeSpec attribute(String name) {
    AttributeSpec attr;
    attr = attributeMap.get(name);

    if (attr == null) {
      attr = new AttributeSpec(name);

      attributeMap.put(name, attr);
    }

    return attr;
  }

  final HtmlSelfGen prepare() {
    definition();

    return this;
  }

  private ElementSpec element0(String name) {
    return new ElementSpec(this, name);
  }

  private void writeTo(ThisTemplate template, Path directory) throws IOException {
    template.writeTo(directory);
  }

}