#!/usr/bin/env bash
javac SentimentServer.java
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi
exec java SentimentServer
