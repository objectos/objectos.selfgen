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

  protected static final class CarbonElement {
    @SuppressWarnings("unused")
    private final String name;

    public CarbonElement(String name) {
      this.name = name;
    }
  }

  private final Code code = Code.of();

  private final Map<String, CarbonElement> elements = new TreeMap<>();

  protected abstract void configure();

  protected final CarbonElement element(String name) {
    return elements.computeIfAbsent(name, CarbonElement::new);
  }

  @Override
  final Code code() { return code; }

}