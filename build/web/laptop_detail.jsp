<%-- 
    Document   : laptop_detail
    Created on : Mar 17, 2019, 4:44:57 PM
    Author     : bachtoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LAPTOP DETAIL</title>

        <link rel="stylesheet" type="text/css" href="CSS/detail.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/index.css"/>
        <link rel="icon" href="ICON/logo.jpg"/>
        <script src="script/index.js" type="text/javascript"></script>
        <script src="script/detail.js" type="text/javascript"></script>
        <style>
            .button_in_detail {
                background-color: #4169E1; /* Green */
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;

                font-size: 16px;

            }
        </style>

    </head>
    <body onload="getDetailWithCompare('GetLaptopWithComapreController', 'stylesheetXSL/laptopDetailXSL.xsl', '${requestScope.COMPARESTRING}','${requestScope.COMPAREPRICE}')">

        <div class="topnav">
            <a class="active" href="index.jsp" onclick="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', ' ')">Home</a>
            <a href="#about">About</a>
            <a href="#contact">Contact</a>
            
        </div>
        <div class="web_header">
            <h1 class="h1_web_brand"> Detail Page </h1>
        </div>

        <div class="detail_page_body">

            <div id="detail_page_display">
                <c:set var="laptopDetail" value="${requestScope.LAPTOPDETAIL}" />
                <c:if test="${not empty laptopDetail}">
                    <c:forEach var="detail" items="${laptopDetail}">

                        <div class="laptop_detail_image">
                            <img src="${detail.laptopImageURL}" class="detail_image"/>
                        </div>
                        <div class="laptop_detail_content">
                            <div class="laptop_detail_brand">
                                ${detail.laptopBrand}
                            </div>
                            <div class="laptop_detail_nameinfo">
                                ${detail.laptopNameInfo}
                            </div>
                            <div class="laptop_detail_price">
                                ${detail.laptopPrice}
                            </div>
                            <div class="laptop_detail_description">
                                Description:
                                ${detail.laptopDescription}
                            </div>
                            <div class="laptop_detail_domain">
                                From domain:
                                ${detail.laptopDomain}
                            </div>
                        </div>
                    </c:forEach>

                </c:if>                 
            </div>
            <div id="title_compared_div">
                <h3 >
                    Similar System Specification
                </h3>
            </div>



            <div id="detail_page_compare">

            </div>

        </div>

    </body>
</html>
