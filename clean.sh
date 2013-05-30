rm -rf db
rm -rf tmp
cd upload
for fn in `ls *`
do
	if [ $fn != "1112426_1369919307731_tornado.mp3" ]; then
		rm $fn
	fi
done