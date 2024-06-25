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

import java.util.Set;
import java.util.TreeSet;
import objectos.selfgen.util.JavaNames;

public final class ElementSpec
    implements
    Child,
    Comparable<ElementSpec>,
    Name {

  public String constantName;

  public String valueSimpleName;

  private AttributeSpec attribute;

  private final HtmlSelfGen dsl;

  private boolean hasEndTag = true;

  private final String name;

  private final Set<ElementSpec> parentSet = new TreeSet<>();

  ElementSpec(HtmlSelfGen dsl, String name) {
    this.dsl = dsl;

    this.name = name;

    simpleName(JavaNames.toValidClassName(name));
  }

  @Override
  public final Name addParent(ElementSpec parent) {
    parentSet.add(parent);
    return this;
  }

  // DSL methods

  public final ElementSpec as(String... alternatives) {
    attribute.as(alternatives);
    return this;
  }

  public final ElementSpec attribute(String name) {
    stringKindIfNecessary();

    attribute = dsl.attribute(name);

    return this;
  }

  public final ElementSpec attributeEnd() {
    stringKindIfNecessary();
    return this;
  }

  public final ElementSpec attributes(Iterable<String> names) {
    for (String name : names) {
      attribute(name);
    }

    return this;
  }

  public final ElementSpec booleanType() {
    setKind(AttributeKind.BOOLEAN);
    return this;
  }

  @Override
  public final int compareTo(ElementSpec o) {
    return name.compareTo(o.name);
  }

  @Override
  public final boolean equals(Object obj) {
    if (!(obj instanceof ElementSpec)) {
      return false;
    }

    ElementSpec that = (ElementSpec) obj;
    return name.equals(that.name);
  }

  //

  public final boolean hasEndTag() {
    return hasEndTag;
  }

  @Override
  public final int hashCode() {
    return name.hashCode();
  }

  public final String methodName() {
    return name;
  }

  @Override
  public final String name() {
    return name;
  }

  public final void noEndTag() {
    stringKindIfNecessary();
    hasEndTag = false;
  }

  public final Iterable<ElementSpec> parentStream() {
    return parentSet;
  }

  public final ElementSpec simpleName(String simpleName) {
    constantName = JavaNames.toIdentifier(name.toUpperCase());

    valueSimpleName = simpleName + "Value";

    return this;
  }

  private void setKind(AttributeKind kind) {
    attribute.addKind(kind);
    attribute = null;
  }

  private void stringKindIfNecessary() {
    if (attribute != null) {
      setKind(AttributeKind.STRING);
    }
  }

}