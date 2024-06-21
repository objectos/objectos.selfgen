#
# Copyright (C) 2023 Objectos Software LTDA.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#
# Objectos Selfgen
#

## Coordinates
GROUP_ID := br.com.objectos
ARTIFACT_ID := objectos.selfgen
VERSION := 0.5

## Deps versions
CODE := br.com.objectos/objectos.code/0.4
SLF4J_NOP := org.slf4j/slf4j-nop/1.7.36
TESTNG := org.testng/testng/7.9.0

# Delete the default suffixes
.SUFFIXES:

#
# selfgen
#

.PHONY: all
all: test

## use JDK 22
JAVA_HOME := /opt/jdk-22

include make/java-core.mk

#
# selfgen@clean
#

include make/common-clean.mk

#
# selfgen@compile
#

## javac --release option
JAVA_RELEASE := 22

## --enable-preview ?
ENABLE_PREVIEW := 1

## compile deps
COMPILE_DEPS := $(CODE)

include make/java-compile.mk

#
# selfgen@test-compile
#

## test compile deps
TEST_COMPILE_DEPS := $(TESTNG)

include make/java-test-compile.mk

#
# selfgen@test
#

## test main class
TEST_MAIN := objectos.selfgen.RunTests

## www test runtime dependencies
TEST_RUNTIME_DEPS := $(SLF4J_NOP)

## test modules
TEST_ADD_MODULES := org.testng

## test runtime exports
TEST_ADD_EXPORTS :=
TEST_ADD_EXPORTS += objectos.selfgen/objectos.selfgen.css=org.testng
TEST_ADD_EXPORTS += objectos.selfgen/objectos.selfgen.html=org.testng
TEST_ADD_EXPORTS += objectos.selfgen/selfgen.css.util=org.testng

## test --add-reads
TEST_ADD_READS := objectos.selfgen=org.testng

include make/java-test.mk

#
# selfgen@jar
#

include make/java-jar.mk

#
# selfgen@pom
#

## pom.xml description
DESCRIPTION := Code generators for the objectos.way project  

#
# selfgen@install
#

include make/java-install.mk