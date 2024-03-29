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
    [r][a][u][t][o][u][p][d][a][t][e])
        echo "rautoupdate"
        echo "include ':app', ':rautoupdate'" > settings.gradle
        ./gradlew rautoupdate
        ;;
    [a][l][l])
	    echo "rall"
	    echo "include ':app', ':rlib', ':rlog', ':rjson', ':rautoupdate'" > settings.gradle
	    ./gradlew rlog
	    ./gradlew rlib
	    ./gradlew rjson
	    ./gradlew rautoupdate
        ;;
    *)
	    echo "rlog/rlib/rjson/all"
	    exit 0
        ;;
esac
echo "make alib jar finish"
