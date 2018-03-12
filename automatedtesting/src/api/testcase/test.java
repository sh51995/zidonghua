package api.testcase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class test {

public static void main(String[] args) {
	TestNG testNG = new TestNG();
    List<String> suites = new ArrayList<String>();
    suites.add("src/api/testcase/testng.xml");
    testNG.setTestSuites(suites);
    testNG.run();
    try {
		Thread.sleep(5000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    testNG.run();
}
}
