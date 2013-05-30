@ECHO OFF
rd /S /Q db
rd /S /Q upload
mkdir upload
mkdir db
echo * > upload/.gitignore