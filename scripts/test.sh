#!/usr/bin/env sh
set -eu
mkdir -p bin
find src/main/java -name '*.java' -print > /tmp/cartflow-sources.txt
javac -d bin @/tmp/cartflow-sources.txt
javac -cp bin -d bin src/test/java/com/store/StoreSmokeTest.java
java -cp bin com.store.StoreSmokeTest
