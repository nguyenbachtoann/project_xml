/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.thread;

/**
 *
 * @author bachtoan
 */
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import toannb.base.BaseThread;

import toannb.crawlers.LapTopXachTayCrawler;


public class LapTopXachTayEachPageThread extends BaseThread {

//    public void eachPageCrawlerThread(String url, int pageNum, String beginTag, String endTag) {
//        try {
//            LapTopXachTayCrawler crawler = new LapTopXachTayCrawler();
//            ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//            Future<List<LapTopXachTayDTO>> future = executorService.submit(new Callable() {
//                public Object call() throws Exception {
//                    List<LapTopXachTayDTO> listProduct = crawler.getListProductEachPage(url, pageNum, beginTag, endTag);
//                    return listProduct;
//                }
//            });
//
////            List<LapTopXachTayDTO> list = future.get();
////
////            LapTopXachTayDAO dao = new LapTopXachTayDAO();
////            dao.insert(list);
//
////        } catch (InterruptedException e) {
////            Logger.getLogger(LapTopXachTayEachPageThread.class.getName()).log(Level.SEVERE, null, e);
////        } catch (ExecutionException ex) {
////            Logger.getLogger(LapTopXachTayEachPageThread.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception e) {
//            Logger.getLogger(LapTopXachTayEachPageThread.class.getName()).log(Level.SEVERE, null, e);
//        }
//
//    }

//    private String url, beginTag, endTag;
//    private int pageNum;
//
//    public LapTopXachTayEachPageThread() {
//    }
//    
//    Future<List<ShoesBean>> futureBullDog = executorService.submit(new Callable() {
//                public Object call() throws Exception {
//                    List<ShoesBean> listBullDogThread = getBullDog(sce, ProjectConstant.bulldog);
//                    return listBullDogThread;
//                }
//            });
//            List<ShoesBean> listBullDog = futureBullDog.get();
//    public LapTopXachTayEachPageThread(String url, int pageNum, String beginTag, String endTag) {
//        this.url = url;
//        this.beginTag = beginTag;
//        this.endTag = endTag;
//        this.pageNum = pageNum;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getBeginTag() {
//        return beginTag;
//    }
//
//    public void setBeginTag(String beginTag) {
//        this.beginTag = beginTag;
//    }
//
//    public String getEndTag() {
//        return endTag;
//    }
//
//    public void setEndTag(String endTag) {
//        this.endTag = endTag;
//    }
//
//    public int getPageNum() {
//        return pageNum;
//    }
//
//    public void setPageNum(int pageNum) {
//        this.pageNum = pageNum;
//    }
//    
//    @Override
//    public void run() {
//        try {
//            LapTopXachTayCrawler crawler = new LapTopXachTayCrawler();
//            List<LapTopXachTayDTO> listProduct = crawler.getListProductEachPage(url, pageNum, beginTag, endTag);
//        } catch (Exception e) {
//            Logger.getLogger(LapTopXachTayEachPageThread.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
//  
}
