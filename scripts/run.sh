#!/usr/bin/env sh
set -eu
mkdir -p trash
find src/main/java -name '*.java' -print > /tmp/cartflow-sources.txt
javac -d trash @/tmp/cartflow-sources.txt
java -cp trash com.store.Main
