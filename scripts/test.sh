#!/usr/bin/env sh
set -eu
mkdir -p trash
find src/main/java -name '*.java' -print > /tmp/cartflow-sources.txt
javac -d trash @/tmp/cartflow-sources.txt
javac -cp trash -d trash src/test/java/com/store/StoreSmokeTest.java
java -cp trash com.store.StoreSmokeTest
