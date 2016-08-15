#!/bin/bash

xmlpre='<source-file src='
xmlpost=' />'
platform="android"

for file in $(find . -name '*.java'); do
    file=${file/.\/}
    dir=$(dirname $file)
    dir=${dir/$platform\/}
    echo $xmlpre\"$file\" target-dir=\"$dir\"$xmlpost
done
