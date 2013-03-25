#!/bin/sh
COMPILER_CMD="java -jar 3rdParty/compiler.jar"
COMPILER_OPT=" --charset utf-8"
MIN_EXT_NAME=".min.js"

handleWithCorrectFile(){
	fn=$1
	if [[ $fn != *$MIN_EXT_NAME ]] 
	then 
		echo "Compress javascript "$fn
		output_filename="${fn%.*}"$MIN_EXT_NAME
		$COMPILER_CMD $fn $COMPILER_OPT > $output_filename
	fi
}

handleFile(){
	filename=$1
	if echo "$filename" | grep -q "require.js" ;then
		echo Ignore $filename
		return ;
	fi
	ext=${filename##*.}
	if [ $ext = js ];then
		handleWithCorrectFile "$filename"
	fi
}

handleDir(){
	dirname=$1
	files=`ls $1`
	for fn in $files
	do
		if [ -d $1/$fn ];
		then 
			handleDir $1/$fn
		else
			handleFile $1/$fn
		fi
	done
}

handleDir "public/javascripts"
echo "Complete."
read