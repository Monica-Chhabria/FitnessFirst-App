package com.fitnessfirst.logging;

/*import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CalorieLogger {
	private static final Logger log = LoggerFactory.getLogger(CalorieLogger.class);
	// static Logger log = Logger.getLogger(CalorieLogger.class);  

	/*	static {

			try {
				
				PropertyConfigurator.configure(System.getProperty("user.dir") +
						 System.getProperty("file.separator") +"FF_CALORIE_CONFIG"+
						 System.getProperty("file.separator") +"log.properties");
				
			} 
			
			
			
			catch (Exception e) {
				
				e.printStackTrace();
			}
			
			finally {
				
			}
		
		}*/
		
		    //function for getting info logging done
			public static void LogMsg(String msg) {
				
				/*if(log.isInfoEnabled()){*/
				
					log.info(msg);
				/*}*/
			}

		    //function for getting error logging done
			public static void ErrMsg(String msg) {
				/*if(log.isEnabledFor(Level.ERROR)){*/

				
					log.error(msg);
				/*}*/
			}

		    //function for getting error logging done
			public static void ErrMsg(String msg,Exception e) {
			/*	if(log.isEnabledFor(Level.ERROR)){*/

					
					log.error(msg,e);
				/*}*/
			}
			

		    //function for getting warning logging done
			public static void TransactionMsg(String msg) {
			/*	if(log.isEnabledFor(Level.WARN)){*/
					
					log.warn(msg);
				/*}*/
			}
			
			
			/*//function for getting fatal logging done
			public static void FatalMsg(String msg) {
				if(log.isEnabledFor(Level.FATAL)){
					log.fatal(msg);
				}
			}*/
}
