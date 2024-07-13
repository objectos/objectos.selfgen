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
package objectos.selfgen;

import java.util.Map;
import java.util.TreeMap;
import objectos.code.Code;

abstract class CarbonSelfGen extends CarbonSpec {

  static final class CarbonElement {
    final String name;
    final String description;

    public CarbonElement(String name, String description) {
      this.name = name;
      this.description = description;
    }

    public final String dataName() {
      return name + "Data";
    }
  }

  private final Code code = Code.of();

  private final Map<String, CarbonElement> elements = new TreeMap<>();

  protected abstract void configure();

  protected final CarbonElement element(String name, String description) {
    return elements.computeIfAbsent(name, k -> new CarbonElement(name, description));
  }

  final CarbonSpec toSpec() {
    configure();

    return this;
  }

  @Override
  final Code code() { return code; }

  @Override
  final Iterable<CarbonElement> elements() {
    return elements.values();
  }

}