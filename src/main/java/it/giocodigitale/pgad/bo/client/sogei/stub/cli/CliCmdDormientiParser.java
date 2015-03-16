package it.giocodigitale.pgad.bo.client.sogei.stub.cli;


import org.apache.commons.cli.*;

public class CliCmdDormientiParser
{

    private String url="";
    private String input="";
    private String output="";
    private String prefix="";

    public String getUrl() {
        return url;
    }

    public String getOutput() {
        return output;
    }

    public String getPrefix() {
        return prefix;
    }



    public String getInput() {
        return input;
    }




    public CliCmdDormientiParser(String[] args)
    {
        /**
         * Defaults
         */


        /**
         * CLI Options
         */
        Option help = new Option("h", "prints this message");
        Option urlOpt = OptionBuilder.isRequired()
                .hasArg()
                .withDescription("url where the pgad service is exposed")
                .create("u");
        Option inputOpt = OptionBuilder.isRequired()
                .hasArg()
                .withDescription("CSV input file")
                .create("i");
        Option outputOpt = OptionBuilder.isRequired()
                .hasArg()
                .withDescription("CSV output file")
                .create("o");
        Option prefixOpt = OptionBuilder
                .hasArg()
                .withDescription("transactionId prefix")
                .create("p");


        Options options = new Options();
        options.addOption(help);
        options.addOption(urlOpt);
        options.addOption(inputOpt);
        options.addOption(outputOpt);
        options.addOption(prefixOpt);


        /**
         * CLI Parser
         */
        CommandLineParser parser = new GnuParser();
        try
        {

            CommandLine line = parser.parse(options, args);
            if (line.hasOption("h"))
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("gd-tool dormienti", options);
            } else
            {


                if (line.hasOption("u"))
                {
                    url = line.getOptionValue("u");
                }

                if (line.hasOption("i"))
                {
                    input  = line.getOptionValue("i");
                }

                if (line.hasOption("o"))
                {
                    output = line.getOptionValue("o");
                }

                if (line.hasOption("p"))
                {
                    prefix = line.getOptionValue("p");
                }



            }
        } catch (ParseException exp)
        {
            // oops, something went wrong
            System.err.println("Parsing failed. Reason: " + exp.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("gd-tool dormienti", options);
        }

    }
}
