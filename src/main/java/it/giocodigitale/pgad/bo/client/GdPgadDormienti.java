package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.dormienti.GdProcessorDormienti;
import it.giocodigitale.pgad.bo.client.processor.status.PartyProcessorStatus;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class GdPgadDormienti {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG","DATA_ULTIMA_ATTIVITA","saldo"} ;
        GdProcessorDormienti gdProcessorDormienti = new GdProcessorDormienti();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            gdProcessorDormienti.setPgadEndpoint(cmd.getUrl());
            gdProcessorDormienti.setParser(cmd.getInput(),';',fields);
            gdProcessorDormienti.setCsvPrinter(cmd.getOutput());
            gdProcessorDormienti.setPrefix(cmd.getPrefix());
            gdProcessorDormienti.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }


}
