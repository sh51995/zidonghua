package ui_web.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Test {
public static void main(String[] args) {
	WebDriver driver = new FirefoxDriver();
	//WebDriver driver = new InternetExplorerDriver();
	driver.get("https://locoms.365gl.com/h5/login.html");
	driver.findElement(By.id("name")).sendKeys("testp");
	driver.findElement(By.id("psw")).sendKeys("123456");
	driver.findElement(By.id("phone")).sendKeys("testp");
	driver.findElement(By.xpath("/html/body/div/div[1]/div[7]/p")).click();
	driver.findElement(By.id("code")).sendKeys("testp");
	driver.findElement(By.xpath("/html/body/div/div[1]/button")).click();
	//driver.quit();
}
}
