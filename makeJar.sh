#!/usr/bin/env bash
echo "java8"
echo "cp build.gradle-lib to build.gradle"
cp build.gradle-lib build.gradle
cp rlib/build.gradle-rlib rlib/build.gradle
cp rlog/build.gradle-rlog rlog/build.gradle
echo "make alib jar begin"
./gradlew rlib
./gradlew rlog
echo "make alib jar finish"
