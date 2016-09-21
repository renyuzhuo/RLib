echo "-----Android begin-----"
echo "clean android/ dir"
rm -rf android/src/
rm -rf android/src/RALib.jar
cp -r ~/AndroidStudioProjects/Rlib/ ./android/src
echo "cp file into android/"
cp android/src/rlib/build/libs/RALib.jar ./android/
cp android/src/build.gradle-alib android/src/build.gradle
cp android/src/rlib/build.gradle-alib android/src/rlib/build.gradle
echo "finish getSource"
echo "-----Android finish-----"

git status

