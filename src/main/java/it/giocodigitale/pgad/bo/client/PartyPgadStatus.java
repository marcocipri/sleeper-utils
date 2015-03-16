package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.dormienti.GdProcessorDormienti;
import it.giocodigitale.pgad.bo.client.processor.status.PartyProcessorStatus;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;




    public  class PartyPgadStatus {


        public static void main(String[] args) {
            String fields [] = new String[]{"CG"} ;
            PartyProcessorStatus partyProcessorStatus = new PartyProcessorStatus();
            CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
            try {
                //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
                partyProcessorStatus.setPgadEndpoint(cmd.getUrl());
                partyProcessorStatus.setParser(cmd.getInput(),';',fields);
                partyProcessorStatus.setCsvPrinter(cmd.getOutput());
                partyProcessorStatus.setPrefix("");
                partyProcessorStatus.process();
            } catch (Exception e) {
                e.printStackTrace();
            }





        }

}
