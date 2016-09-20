echo "get doc begin"
git checkout gh-pages 
rm -rf android-javadoc/
cp -r ~/AndroidStudioProjects/Rlib/rlib/build/docs/javadoc/ android-javadoc
git add .
git status
echo "please commit, push and checkout develop"
