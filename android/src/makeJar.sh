#!/usr/bin/env bash
echo "java8"
echo "update settings.gradle"
case $1 in
    [r][l][o][g])
	    echo "rlog"
	    echo "include ':app', ':rlog'" > settings.gradle
	    ./gradlew rlog
        ;;
    [r][l][i][b])
	    echo "rlib"
	    echo "include ':app', ':rlib'" > settings.gradle
	    ./gradlew rlib
        ;;
    [r][j][s][o][n])
	    echo "rjson"
	    echo "include ':app', ':rjson'" > settings.gradle
	    ./gradlew rjson
        ;;
    [a][l][l])
	    echo "rall"
	    echo "include ':app', ':rlib', ':rlog', ':rjson'" > settings.gradle
	    ./gradlew rlog
	    ./gradlew rlib
	    ./gradlew rjson
        ;;
    *)
	    echo "rlog/rlib/rjson/all"
	    exit 0
        ;;
esac
echo "make alib jar finish"
