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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import objectos.selfgen.util.JavaNames;

final class AttributeSpec {

  public final String constantName;

  String instructionSimpleName;

  private final Set<AttributeKind> kindSet = new TreeSet<>();

  private final String name;

  private final Set<String> nameSet = new LinkedHashSet<>();

  AttributeSpec(String name) {
    this.name = name;

    constantName = JavaNames.toIdentifier(name.replace('-', '_').toUpperCase());
  }

  public final void addKind(AttributeKind kind) {
    kindSet.add(kind);
  }

  public final void as(String... names) {
    for (String name : names) {
      nameSet.add(name);
    }
  }

  public final String constantName() {
    return constantName;
  }

  public final AttributeKind kind() {
    Set<AttributeKind> s;
    s = kindSet();

    if (s.size() == 1) {
      Iterator<AttributeKind> it;
      it = s.iterator();

      return it.next();
    } else {
      return AttributeKind.STRING;
    }
  }

  public final Set<AttributeKind> kindSet() {
    return kindSet;
  }

  public final Iterable<String> methodNames() {
    return nameSet.isEmpty()
        ? Set.of(methodName(name))
        : nameSet;
  }

  public final String name() {
    return name;
  }

  public final String simpleName() {
    return JavaNames.toValidClassName(name);
  }

  private String methodName(String value) {
    return JavaNames.toValidMethodName(value);
  }

}