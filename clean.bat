@ECHO OFF
rd /S /Q db
rd /S /Q upload
rd /S /Q tmp
mkdir upload
mkdir db
echo * > upload/.gitignore