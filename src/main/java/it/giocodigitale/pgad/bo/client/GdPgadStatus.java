package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.status.GdProcessorStatus;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

public class GdPgadStatus {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG"} ;
        GdProcessorStatus gdProcessorStatus = new GdProcessorStatus();
        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            gdProcessorStatus.setPgadEndpoint(cmd.getUrl());
            gdProcessorStatus.setParser(cmd.getInput(),';',fields);
            gdProcessorStatus.setCsvPrinter(cmd.getOutput());
            gdProcessorStatus.setPrefix("");
            gdProcessorStatus.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
