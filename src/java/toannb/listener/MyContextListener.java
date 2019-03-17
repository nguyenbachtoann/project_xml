/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
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
        try {

            String realPath = sce.getServletContext().getRealPath("/");
            CrawlerMaster crawler = new CrawlerMaster(realPath);
            
            Timer timer = new Timer();
            timer.schedule(crawler, 43200000, 43200000);

        } catch (Exception e) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
