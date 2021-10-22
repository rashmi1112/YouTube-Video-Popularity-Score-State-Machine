package channelpopularity.driver;

import channelpopularity.context.Context;
import channelpopularity.context.ContextI;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.HelperClass;
import channelpopularity.util.Results;
import channelpopularity.util.StdoutDisplayInterface;

/**
 * @author Rashmi Badadale
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		FileDisplayInterface fileDisplayInterface = null;
		StdoutDisplayInterface stdoutDisplayInterface = null;
		HelperClass helperClass = null;

		try{
		if ((args.length != 2) || (args[0].equals("${input}")) ||(args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			throw new IllegalArgumentException();
		}
		if(args[0].startsWith(" ") || args[1].startsWith(" ")){
			throw new IllegalArgumentException(" Error: Empty string for input/output filename!");
		}
		SimpleStateFactory simpleStateFactory = new SimpleStateFactory();
		fileDisplayInterface = new Results(args[1]);
		stdoutDisplayInterface = new Results(args[1]);
		ContextI context = new Context(simpleStateFactory, fileDisplayInterface, stdoutDisplayInterface);
			helperClass = new HelperClass(args[0],context);
			helperClass.getWords();
		} catch (IllegalArgumentException illegalArgumentException) {
			illegalArgumentException.printStackTrace();
			System.exit(0);
		} finally {
			assert helperClass != null;
			helperClass.close();
		}
		fileDisplayInterface.writeToFile();
		stdoutDisplayInterface.writeToStdOut();

	}
}
