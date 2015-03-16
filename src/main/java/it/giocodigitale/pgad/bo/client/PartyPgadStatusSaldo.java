package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.scan.PartyProcessorScan;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class PartyPgadStatusSaldo {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG","DATA_ULTIMA_ATTIVITA","saldo"} ;
        PartyProcessorScan partyProcessorScan = new PartyProcessorScan();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            partyProcessorScan.setPgadEndpoint(cmd.getUrl());
            //partyProcessorDormienti.setPgadEndpoint("http://10.20.12.25:8080");

            partyProcessorScan.setParser(cmd.getInput(),';',fields);
            partyProcessorScan.setCsvPrinter(cmd.getOutput());


            partyProcessorScan.setPrefix(cmd.getPrefix());
            partyProcessorScan.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
