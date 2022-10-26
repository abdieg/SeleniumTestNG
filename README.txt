1. Open a console and run:
java -jar selenium-server-4.5.0.jar hub

2. On same machine open another console and run (adjust path and IP address as needed):
java -Dwebdriver.gecko.driver="/Users/diego/Coding/SeleniumServer/geckodriver" -Dwebdriver.chrome.driver="/Users/diego/Coding/SeleniumServer/chromedriver" -Dwebdriver.edge.driver="/Users/diego/Coding/SeleniumServer/msedgedriver" -Dwebdriver.safari.driver="/Users/diego/Coding/SeleniumServer/safaridriver" -jar selenium-server-4.5.0.jar node --hub http://192.168.1.90:4444/grid/register --port 5566

3. Go to testng.xml location and run:
mvn clean test -DsuiteXmlFile=testng.xml