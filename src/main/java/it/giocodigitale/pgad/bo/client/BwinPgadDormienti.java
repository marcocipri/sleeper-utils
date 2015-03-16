package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.dormienti.BwinProcessorDormienti;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class BwinPgadDormienti {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG","DATA_ULTIMA_ATTIVITA","saldo"} ;
        BwinProcessorDormienti bwinProcessorDormienti = new BwinProcessorDormienti();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            bwinProcessorDormienti.setPgadEndpoint(cmd.getUrl());
            bwinProcessorDormienti.setParser(cmd.getInput(),';',fields);
            bwinProcessorDormienti.setCsvPrinter(cmd.getOutput());
            bwinProcessorDormienti.setPrefix(cmd.getPrefix());
            bwinProcessorDormienti.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
