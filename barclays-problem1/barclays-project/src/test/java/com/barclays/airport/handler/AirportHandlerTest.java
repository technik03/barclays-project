/**
 * @author niharika
 */
package com.barclays.airport.handler;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class AirportHandlerTest {

	private AirportHandler airportHandler;
	private ClassLoader classLoader;
	private File file;
	
	@Before
	public void before() throws Exception{
		airportHandler = new AirportHandler();		
		classLoader = getClass().getClassLoader();
	}
	
	@Test
	public void processTest_BlankInputFile() {	
		file = new File(classLoader.getResource("input_blank.txt").getFile());
		airportHandler.process(file.getAbsolutePath());		
	}
	
	@Test
	public void processTest_NonExistingInputFile(){
		 airportHandler.process("BadInputFile");
	}
	
	@Test
	public void processTest_BadDataOneLineInputFile(){		
		 file = new File(classLoader.getResource("input_bad_data_oneline.txt").getFile());
		 airportHandler.process(file.getAbsolutePath());
	}
	
	
	@Test
	public void processTest_HappyPath(){		
		 file = new File(classLoader.getResource("input.txt").getFile());
		 airportHandler.process(file.getAbsolutePath());
	}

}
