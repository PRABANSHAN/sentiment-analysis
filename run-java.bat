@echo off
javac SentimentServer.java
if errorlevel 1 exit /b %errorlevel%
java SentimentServer
