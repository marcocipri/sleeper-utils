package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.dormienti.BwinProcessorDormienti;
import it.giocodigitale.pgad.bo.client.processor.status.BwinProcessorStatus;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class BwinPgadStatus {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG"} ;
        BwinProcessorStatus bwinProcessorStatus = new BwinProcessorStatus();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);

        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            bwinProcessorStatus.setPgadEndpoint(cmd.getUrl());
            bwinProcessorStatus.setParser(cmd.getInput(),';',fields);
            bwinProcessorStatus.setCsvPrinter(cmd.getOutput());
            bwinProcessorStatus.setPrefix("");
            bwinProcessorStatus.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
