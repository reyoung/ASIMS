#!/bin/sh
GlobalCounter=0
handleJava(){
	fn=$1
	line=`git blame $fn >> java`
	#let "GlobalCounter+=$line"
}
handleJavascript(){
	fn=$1
	line=`git blame $fn >> javascript`
}
handleHtml(){
	fn=$1
	line=`git blame $fn >> html`
}

handleFile(){
	filename=$1
	
	ext=${filename##*.}
	if [ $ext = java ];then
		handleJava "$filename"
	fi
	
	if [ $ext = js ];then
		handleJavascript "$filename"
	fi
	
	if [ $ext = html ];then
		handleHtml "$filename"
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

handleDir .
wc -l java
wc -l javascript
wc -l html
cat java javascript html | wc -l

rm java javascript html