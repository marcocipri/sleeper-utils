#!/bin/bash

P=$(dirname $0)
CP=$(echo ${P}/lib/*.jar | sed -e 's/ /:/g')
echo $CP
java  -cp "$CP":./pgad-tools.jar it.giocodigitale.pgad.bo.client.GdPgadDormienti