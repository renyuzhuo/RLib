echo "-----Android begin-----"
echo "clean android/ dir"
rm -rf android/src/
rm -rf android/src/RLib.jar
rm -rf android/src/RLog.jar
rm -rf android/src/RJson.jar
rm -rf android/src/RAutoUpdate.jar
cp -r ~/AndroidStudioProjects/Rlib/ ./android/src
echo "cp file into android/"
cp android/src/rlib/build/libs/RLib.jar ./android/
cp android/src/rlog/build/libs/RLog.jar ./android/
cp android/src/rjson/build/libs/RJson.jar ./android/
cp android/src/rautoupdate/build/libs/RAutoUpdate.jar ./android/
echo "finish getSource"

echo "get docs begin"
rm -rf docs/android-rlib-javadoc/
rm -rf docs/android-rlog-javadoc/
rm -rf docs/android-rjson-javadoc/
rm -rf docs/android-rautoupdate-javadoc/
cp -r ~/AndroidStudioProjects/Rlib/rlib/build/docs/javadoc/ docs/android-rlib-javadoc
cp -r ~/AndroidStudioProjects/Rlib/rlog/build/docs/javadoc/ docs/android-rlog-javadoc
cp -r ~/AndroidStudioProjects/Rlib/rjson/build/docs/javadoc/ docs/android-rjson-javadoc
cp -r ~/AndroidStudioProjects/Rlib/rautoupdate/build/docs/javadoc/ docs/android-rautoupdate-javadoc

echo "clean files"
rm -rf *~
rm -rf */*~
rm -rf */*/*~

git add .
git status

echo "-----Android finish-----"
