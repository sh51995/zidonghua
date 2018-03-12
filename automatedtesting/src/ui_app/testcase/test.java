package ui_app.testcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import ui_app.util.AppiumUtil;
import ui_app.util.DingDing;
//@Listeners({testng.GenerateReporter.class})
public class test {
	


	public static void main(String[] args) {
		
		try {
			int s=0;
			for(int i=0;i<100;i++){
			XmlSuite suite = new XmlSuite();
			suite.setName("TmpSuite");
			XmlTest test = new XmlTest(suite);
			test.setName("TmpTest");
			List<XmlClass> classes = new ArrayList<XmlClass>();
			classes.add(new XmlClass("ui_app.testcase.test"));
			test.setXmlClasses(classes) ;
				
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG testNG = new TestNG();
			testNG.setXmlSuites(suites);
	//		testNG.setTestClasses(new Class[]{TestLoginCfrontAPP.class});
	//		testNG.setTestClasses(new Class[]{TestLogicBfrontAPP.class});
	//		testNG.setTestClasses(new Class[]{TestLoginSalesApp.class});
	        testNG.run();
	       if(testNG.hasFailure()){
	    	   s=s+1;
	       };
	       System.out.println("===========i:"+i);
	       System.out.println("===========s:"+s);
	        Thread.sleep(20000);
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	@Test(description="波测流程")
	public void testBoCe() throws IOException, InterruptedException{
		 AppiumUtil.appiumStart();
		 Thread.sleep(10000);
			try {
				boolean wxpay=TestWxApp.testWXPays();
				if(!wxpay){
					DingDing.send("微信支付失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				TestLogicBfrontAPP.testBfrontLoginAndOut();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				TestLoginCfrontAPP.testCforntLoginAndOut();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			AppiumUtil.appiumStop();
	}
}
