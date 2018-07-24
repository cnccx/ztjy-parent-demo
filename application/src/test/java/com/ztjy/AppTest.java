package com.ztjy;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * 
        * @Title 佛祖保佑
        * @Description 用于保佑本应用不出bug，表达了程序员的美好愿景
		* @ClassName AppTest
		* @Copyright 2014-现在 厦门神州鹰掌通家园项目组
		* @author 胡耀忠 huyaozhong
		* @date 2018年5月3日 下午5:23:31 
 *
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	System.out.println("");
    	String fozu =   
                "                   _ooOoo_"+"\n"+  
                "                  o8888888o"+"\n"+  
                "                  88\" . \"88"+"\n"+  
                "                  (| -_- |)"+"\n"+  
                "                  O\\  =  /O"+"\n"+  
                "               ____/`---'\\____"+"\n"+  
                "             .'  \\\\|     |//  `."+"\n"+  
                "            /  \\\\|||  :  |||//  \\"+"\n"+  
                "           /  _||||| -:- |||||-  \\"+"\n"+  
                "           |   | \\\\\\  -  /// |   |"+"\n"+  
                "           | \\_|  ''\\---/''  |   |"+"\n"+  
                "           \\  .-\\__  `-`  ___/-. /"+"\n"+  
                "         ___`. .'  /--.--\\  `. . __"+"\n"+  
                "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\"."+"\n"+  
                "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |"+"\n"+  
                "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /"+"\n"+  
                "======`-.____`-.___\\_____/___.-`____.-'======"+"\n"+  
                "                   `=---='"+"\n"+  
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+"\n"+  
                "\t\t佛祖保佑       永无BUG"+"\n"+
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+"\n"; 
        System.out.println(fozu);
    }
}
