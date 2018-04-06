package Gameground;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


public class ConsoleRedirector extends PrintStream {
	static OutputStream logfile;
	static PrintStream  oldStdout;
	static PrintStream  oldStderr;
	public static String LogFile = "";
 
	ConsoleRedirector(PrintStream ps)
	{
		super(ps);
	}
 
 
 
	// Starts copying stdout and 
	//stderr to the file f.
	public static void start(String f_OUT) throws IOException
	{
		LogFile = f_OUT;
//		 Save old settings.
		oldStdout = System.out;
		oldStderr = System.err;
 
		// Create/Open logfile.
		logfile = new PrintStream(new FileOutputStream(f_OUT));
 
		// Start redirecting the output.
		System.setErr(new ConsoleRedirector(System.err));
		System.setOut(new ConsoleRedirector(System.out));
	}
 
	// Restores the original settings.
	public static void stop()
	{
		System.setErr(oldStderr);
		System.setOut(oldStdout);
 
		try
		{
                                 
			logfile.flush();
			logfile.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
 
//	 PrintStream override.
	public void write(int b)
	{
		try
		{
			logfile.write(b);
 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			setError();
		}
 
		super.write(b);
	}
 
	// PrintStream override.
	public void write(byte [] buf, int off, int len)
	{
		try
		{
			logfile.write(buf, off, len);
 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			setError();
		}
 
		super.write(buf, off, len);
	}

}
