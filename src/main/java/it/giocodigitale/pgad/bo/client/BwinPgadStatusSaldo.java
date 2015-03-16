package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.scan.BwinProcessorScan;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class BwinPgadStatusSaldo {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG","DATA_ULTIMA_ATTIVITA","saldo"} ;
        BwinProcessorScan bwinProcessorScan = new BwinProcessorScan();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            bwinProcessorScan.setPgadEndpoint(cmd.getUrl());
            bwinProcessorScan.setParser(cmd.getInput(),';',fields);
            bwinProcessorScan.setCsvPrinter(cmd.getOutput());
            bwinProcessorScan.setPrefix(cmd.getPrefix());
            bwinProcessorScan.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
