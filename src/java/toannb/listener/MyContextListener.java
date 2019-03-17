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
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date schedule = calendar.getTime();
            long period = 24 * 60 & 60 * 1000;
            Timer timer = new Timer();
            timer.schedule(crawler, schedule, period);

        } catch (Exception e) {
            Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
