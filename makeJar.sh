#!/usr/bin/env bash
echo "java8"
echo "cp build.gradle-alib to build.gradle"
cp build.gradle-alib build.gradle
cp rlib/build.gradle-alib rlib/build.gradle
echo "make alib jar begin"
./gradlew alib
echo "make alib jar finish"

