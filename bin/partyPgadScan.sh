#!/bin/bash

P=$(dirname $0)
CP=$(echo ${P}/lib/*.jar | sed -e 's/ /:/g')
echo $CP
echo "pgad endpoint $1 csv in $2 csv out $3 "
## esempio test
## bash gdPgadDormienti.sh http://10.20.12.25:8080 /Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv /Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormientiOut1.csv
java  -cp "$CP":./pgad-tools.jar it.giocodigitale.pgad.bo.client.PartyPgadStatusSaldo -u $1 -i $2 -o $3