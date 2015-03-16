package it.giocodigitale.pgad.bo.client;

/**
 * Created by mcipri on 28/08/14.
 */

import it.giocodigitale.pgad.bo.client.processor.listadormienti.GdProcessorListaDormienti;
import it.giocodigitale.pgad.bo.client.sogei.stub.cli.CliCmdDormientiParser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GdPgadListaDormienti {


    public static void main(String[] args) {
        String fields [] = new String[]{"CG"} ;
        GdProcessorListaDormienti gdProcessorListaDormienti = new GdProcessorListaDormienti();
//        CliCmdDormientiParser cmd = new CliCmdDormientiParser(args);
        try {
            //gdProcessorDormienti.setParser("/Users/mcipri/Documents/IntellijWorkspace/GD-TOOLS/gdDormienti.csv",',',fields);
            //gdProcessorListaDormienti.setPgadEndpoint("http://10.20.12.25:8080/");
            gdProcessorListaDormienti.setPgadEndpoint("http://10.20.45.216:8080/");
            // parser isn't requested
            //gdProcessorListaDormienti.setParser(cmd.getInput(),';',fields);
            // but a range of dates
            // start date and how many day after the start date to explore
            SimpleDateFormat inDateFormat = new SimpleDateFormat("dd/MM/yy");
            Date startDay = inDateFormat.parse("01/01/14");
            gdProcessorListaDormienti.setStartDay(startDay);
            // day to scan before the start day
            gdProcessorListaDormienti.setTill(352);
            //gdProcessorListaDormienti.setCsvPrinter("test.csv");
            gdProcessorListaDormienti.setPrefix("");
            gdProcessorListaDormienti.process();
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
