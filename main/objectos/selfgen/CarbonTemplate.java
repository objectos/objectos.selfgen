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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import objectos.code.ClassName;
import objectos.code.Code;
import objectos.code.Code.ImportList;

abstract class CarbonTemplate {

  static final String COPYRIGHT_YEARS = "2024";

  static final String WAY_PACKAGE = "objectos.way";

  final CarbonSpec spec;

  final Code code;

  ClassName className;

  ImportList importList;

  String packageName;

  String simpleName;

  public CarbonTemplate(CarbonSpec spec) {
    this.spec = spec;

    code = spec.code();
  }

  @Override
  public final String toString() {
    return contents();
  }

  public void writeTo(Path directory) throws IOException {
    String contents;
    contents = contents();

    Path file;
    file = className.toPath(directory);

    Files.writeString(
        file, contents, StandardCharsets.UTF_8,
        StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
    );
  }

  final void className(String simpleName) {
    this.className = ClassName.of("objectos.way", simpleName);

    packageName = this.className.packageName();

    simpleName = this.className.simpleName();

    importList = code.importList(packageName);
  }

  abstract String contents();

}