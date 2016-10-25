#!/usr/bin/env bash
echo "java8"
echo "cp build.gradle-upload to build.gradle"
cp build.gradle-upload build.gradle
cp rlib/build.gradle-upload rlib/build.gradle
cp rlog/build.gradle-upload rlog/build.gradle
echo "upload to bintray begin"
./gradlew install
echo "-----------------------"
./gradlew bintrayUpload
echo "upload to bintray finish"
