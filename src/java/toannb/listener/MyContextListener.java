/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.listener;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
//        try {
//            Timer timer = new Timer();
//            TimerTask task = new TimerTask() {
//                @Override
//                public void run() {
//                    CrawlerMaster.crawlData();
//
//                }
//            };
//            timer.schedule(task, 4000000, 4000000);
//
////            4000000 1 hour
//        } catch (Exception ex) {
//            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
