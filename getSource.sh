echo "-----Android begin-----"
echo "clean android/ dir"
rm -rf android/src/
rm -rf android/src/RLib.jar
rm -rf android/src/RLog.jar
cp -r ~/AndroidStudioProjects/Rlib/ ./android/src
echo "cp file into android/"
cp android/src/rlib/build/libs/RLib.jar ./android/
cp android/src/rlog/build/libs/RLog.jar ./android/
cp android/src/build.gradle-lib android/src/build.gradle
cp android/src/rlib/build.gradle-rlib android/src/rlib/build.gradle
cp android/src/rlog/build.gradle-rlog android/src/rlog/build.gradle
echo "finish getSource"

echo "get docs begin"
rm -rf docs/android-rlib-javadoc/
rm -rf docs/android-rlog-javadoc/
cp -r ~/AndroidStudioProjects/Rlib/rlib/build/docs/javadoc/ docs/android-rlib-javadoc
cp -r ~/AndroidStudioProjects/Rlib/rlog/build/docs/javadoc/ docs/android-rlog-javadoc

git add .
git status

echo "-----Android finish-----"

echo "clean files"
rm -rf *~
rm -rf */*~
rm -rf */*/*~
