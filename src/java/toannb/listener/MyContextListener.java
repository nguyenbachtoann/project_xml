/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.listener;

import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import toannb.crawlers.CrawlerMaster;

/**
 * Web application lifecycle listener.
 *
 * @author bachtoan
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            CrawlerMaster.crawlData();
//            Timer timer = new Timer();
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    System.out.println("Whoot");
//                }
//            };
//            timer.schedule(task, 0, 300);
        } catch (Exception e) {
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
