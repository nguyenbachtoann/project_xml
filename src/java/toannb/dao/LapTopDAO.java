/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toannb.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import toannb.constant.AppConstants;
import toannb.db.MyConnection;
import toannb.dto.LaptopDTO;
import toannb.dto.LaptopDTOList;
import toannb.dto.TopBrandDTO;
import toannb.dto.TopBrandDTOList;

/**
 *
 * @author bachtoan
 */
public class LapTopDAO implements Serializable {

    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;

    LaptopDTOList laptopList = new LaptopDTOList();

    public LapTopDAO() {
    }

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public LaptopDTOList getAllLaptop() throws SQLException, Exception {

        try {
            String sql = "Select laptopId, laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain from tbl_LapTop order by len(laptopPrice), laptopPrice";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            int laptopId = 0;
            String laptopBrand = null;
            String laptopNameInfo = null;
            String laptopPrice = null;
            String laptopDescription = null;
            String laptopImageURL = null;
            String laptopDomain = null;
            laptopList = new LaptopDTOList();
            while (rs.next()) {
                laptopId = rs.getInt("laptopId");
                laptopBrand = rs.getString("laptopBrand");
                laptopNameInfo = rs.getString("laptopNameInfo");
                laptopPrice = rs.getString("laptopPrice");
                laptopImageURL = rs.getString("laptopImageURL");
                laptopDescription = rs.getString("laptopDescription");
                laptopDomain = rs.getString("laptopDomain");
                laptopImageURL = laptopImageURL.replace("web/", "");
                LaptopDTO dto = new LaptopDTO(Integer.toString(laptopId), laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain);
                laptopList.getLaptop().add(dto);
            }
        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
        return laptopList;

    }

    public LaptopDTOList getTop2TrendingBrand() throws SQLException, Exception {

        try {
            String sql = "select laptopId, laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain from tbl_LapTop where laptopBrand in (select top 2 laptopTrendBrand from tbl_LaptopBrandTrending) order by len(laptopPrice), laptopPrice";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            int laptopId = 0;
            String laptopBrand = null;
            String laptopNameInfo = null;
            String laptopPrice = null;
            String laptopDescription = null;
            String laptopImageURL = null;
            String laptopDomain = null;
            laptopList = new LaptopDTOList();
            while (rs.next()) {
                laptopId = rs.getInt("laptopId");
                laptopBrand = rs.getString("laptopBrand");
                laptopNameInfo = rs.getString("laptopNameInfo");
                laptopPrice = rs.getString("laptopPrice");
                laptopImageURL = rs.getString("laptopImageURL");
                laptopDescription = rs.getString("laptopDescription");
                laptopDomain = rs.getString("laptopDomain");
                laptopImageURL = laptopImageURL.replace("web/", "");
                LaptopDTO dto = new LaptopDTO(Integer.toString(laptopId), laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain);
                laptopList.getLaptop().add(dto);
            }
        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
        return laptopList;

    }

    public LaptopDTOList getLaptopLIKEBrand(String brandSearch) throws SQLException, Exception {

        try {
            String sql = "select laptopId, laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain from tbl_LapTop where laptopBrand LIKE ?";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + brandSearch + "%");
            rs = preStm.executeQuery();
            int laptopId = 0;
            String laptopBrand = null;
            String laptopNameInfo = null;
            String laptopPrice = null;
            String laptopDescription = null;
            String laptopImageURL = null;
            String laptopDomain = null;
            laptopList = new LaptopDTOList();
            while (rs.next()) {
                laptopId = rs.getInt("laptopId");
                laptopBrand = rs.getString("laptopBrand");
                laptopNameInfo = rs.getString("laptopNameInfo");
                laptopPrice = rs.getString("laptopPrice");
                laptopImageURL = rs.getString("laptopImageURL");
                laptopDescription = rs.getString("laptopDescription");
                laptopDomain = rs.getString("laptopDomain");
                laptopImageURL = laptopImageURL.replace("web/", "");
                LaptopDTO dto = new LaptopDTO(Integer.toString(laptopId), laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain);
                laptopList.getLaptop().add(dto);
            }
        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
        return laptopList;

    }

    public List<String> getLaptopBrand() throws SQLException, Exception {
        List<String> listBrand = null;
        try {
            String sql = "select distinct laptopBrand from tbl_LapTop";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            String laptopBrand = null;
            listBrand = new ArrayList<>();
            while (rs.next()) {
                laptopBrand = rs.getString("laptopBrand");
                listBrand.add(laptopBrand);
            }
        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
        return listBrand;

    }

    public List<LaptopDTO> updateImageURL() throws SQLException, Exception {
        List<LaptopDTO> list = null;
        try {
            String sql = "select laptopId, laptopImageURL from tbl_LapTop";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            int laptopId = 0;
            String laptopImageURL = null;
            list = new ArrayList<>();
            while (rs.next()) {
                laptopId = rs.getInt("laptopId");
                laptopImageURL = rs.getString("laptopImageURL");
                LaptopDTO dto = new LaptopDTO(Integer.toString(laptopId), laptopImageURL);
                list.add(dto);
            }
            updateImage(list);
        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
        return list;

    }

    public void updateImage(List<LaptopDTO> list) throws SQLException, ClassNotFoundException, Exception {

        try {

            String sql
                    = "update tbl_LapTop set laptopImageURL = ? where laptopId = ?";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (int i = 0; i < list.size(); i++) {
                preStm.setString(1, "web/images/" + AppConstants.LAPTOP_IMAGE_PREFIX + list.get(i).getLaptopId() + AppConstants.LAPTOP_IMAGE_FORMAT);
                preStm.setString(2, list.get(i).getLaptopId());
                preStm.addBatch();
            }

            preStm.executeBatch();
            con.commit();

            for (int i = 0; i < list.size(); i++) {

                System.setProperty("http.agent", "Chrome");
                String laptopImage = list.get(i).getLaptopImageURL();
                URL url = new URL(laptopImage);
                BufferedImage img = ImageIO.read(url);
                File file = new File("web/images/" + AppConstants.LAPTOP_IMAGE_PREFIX + list.get(i).getLaptopId() + AppConstants.LAPTOP_IMAGE_FORMAT);
                ImageIO.write(img, "jpg", file);

            }

        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }

    public void insert(List<LaptopDTO> dtoList) throws SQLException, ClassNotFoundException, Exception {

        try {

            String sql
                    = "Insert into tbl_LapTop( laptopBrand, laptopNameInfo, laptopPrice, laptopImageURL, laptopDescription, laptopDomain) values (?,?,?,?,?,?)";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (LaptopDTO laptop : dtoList) {
                preStm.setString(1, laptop.getLaptopBrand());
                preStm.setString(2, laptop.getLaptopNameInfo());
                preStm.setString(3, laptop.getLaptopPrice());
                preStm.setString(4, laptop.getLaptopImageURL());
                preStm.setString(5, laptop.getLaptopDescription());
                preStm.setString(6, laptop.getLaptopDomain());
                preStm.addBatch();
            }
            preStm.executeBatch();
            con.commit();

        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }

    
    public void insertTopBrand(TopBrandDTOList dtoList) throws SQLException, ClassNotFoundException, Exception {

        try {

            String sql
                    = "Insert into tbl_LaptopBrandTrending( laptopTrendBrand, laptopTrendReviews, laptopTrendDesign, laptopTrendSW, laptopTrendInnovation, laptopTrendIVS, laptopTrendOverall) values (?,?,?,?,?,?,?)";
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            con.setAutoCommit(false);
            for (TopBrandDTO laptop : dtoList.getListBrand()) {
                preStm.setString(1, laptop.getLaptopTrendBrand());
                preStm.setString(2, laptop.getLaptopTrendReviews());
                preStm.setString(3, laptop.getLaptopTrendDesign());
                preStm.setString(4, laptop.getLaptopTrendSW());
                preStm.setString(5, laptop.getLaptopTrendInnovation());
                preStm.setString(6, laptop.getLaptopTrendIVS());
                preStm.setString(7, laptop.getLaptopTrendOverall());
                preStm.addBatch();
            }
            preStm.executeBatch();
            con.commit();

        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }

    public void truncateTable(String tableName) throws Exception {

        try {
            String sql
                    = "truncate table " + tableName;
            con = MyConnection.getMyConnection();
            preStm = con.prepareStatement(sql);
            con.setAutoCommit(false);
            preStm.executeUpdate();
            con.commit();

        } catch (Exception e) {
            Logger.getLogger(LapTopDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection();
        }
    }

}
