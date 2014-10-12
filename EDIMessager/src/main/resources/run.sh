#!/bin/bash

path=`pwd`

java -jar EDIMessager-1.1.jar $path/input/EDI.txt $path/config/template.vm $path/output/output.html $path/config/opis.txt $path/config/config.properties
