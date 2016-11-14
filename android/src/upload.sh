#!/usr/bin/env bash
echo "java8"
echo "update settings.gradle"
case $1 in
    [r][l][o][g])
	    echo "rlog"
	    echo "include ':app', ':rlog'" > settings.gradle
        ;;
    [r][l][i][b])
	    echo "rlib"
	    echo "include ':app', ':rlib'" > settings.gradle
        ;;
    [r][j][s][o][n])
	    echo "rjson"
	    echo "include ':app', ':rjson'" > settings.gradle
        ;;
    [r][a][u][t][o][u][p][d][a][t][e])
        echo "rautoupdate"
        echo "include ':app', ':rautoupdate'" > settings.gradle
        ;;
    [a][l][l])
	    echo "rall"
	    echo "include ':app', ':rlib', ':rlog', ':rjson', ':rautoupdate'" > settings.gradle
        ;;
    *)
	    echo "rlog/rlib/rjson/all"
	    exit 0
        ;;
esac

echo "upload to bintray begin"
./gradlew install
echo "-----------------------"
./gradlew bintrayUpload
echo "upload to bintray finish"
