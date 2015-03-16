package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.dormienti.BwinProcessorDormienti;
import it.giocodigitale.pgad.bo.client.processor.dormienti.PartyProcessorDormienti;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class PartyPgadDormienti {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG","DATA_ULTIMA_ATTIVITA","saldo"} ;
        PartyProcessorDormienti partyProcessorDormienti = new PartyProcessorDormienti();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            partyProcessorDormienti.setPgadEndpoint(cmd.getUrl());
            //partyProcessorDormienti.setPgadEndpoint("http://10.20.12.25:8080");

            partyProcessorDormienti.setParser(cmd.getInput(),';',fields);
            partyProcessorDormienti.setCsvPrinter(cmd.getOutput());


            partyProcessorDormienti.setPrefix(cmd.getPrefix());
            partyProcessorDormienti.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
