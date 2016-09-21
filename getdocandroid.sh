#!/bin/sh
echo "get doc begin"
git checkout gh-pages 
rm -rf android-javadoc/
cp -r ~/AndroidStudioProjects/Rlib/rlib/build/docs/javadoc/ android-javadoc
git add android-javadoc/*
git status

if [ "$1" =  "" ]; then 
    echo "please commit, push and checkout develop"
else
    git commit -m "$1"
    echo "git commit finish"
    if [ "$2" = "" ]; then
        echo "git not push"
        git checkout develop
        git branch
    else
        if [ "$2" = "y" ]; then
            git push origin
            echo "git push finish"
            git checkout develop
            git branch
        fi
    fi
fi
