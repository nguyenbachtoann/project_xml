<%-- 
    Document   : index
    Created on : Mar 4, 2019, 10:54:57 PM
    Author     : bachtoan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="CSS/index.css"/>
<link rel="icon" href="ICON/logo.jpg"/>
<script src="script/index.js" type="text/javascript"></script>

<html>
    <head>
        <title>NICE LAPTOP HOME</title>

        <style>
            .dropbtn {
                background-color:#4169E1;
                color: white;
                padding: 10px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 130px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 10px 14px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1}

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #4682B4;
            }

        </style>
    </head>

    <body onload="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', '')">
        <div class="topnav">
            <a class="active" href="index.jsp" onclick="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', ' ')">Home</a>
            <a href="index.jsp" onclick="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', 'a')">About</a>
            <a href="#contact">Contact</a>
            <div class="search-container">
                <input type="text" id="btnSearch" placeholder="Search.." name="laptopId" value=""> 
                <button type="button" onclick="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', document.getElementById('btnSearch').value)">
                    <img class="imgSearch" src="ICON/magnifying_glass.png" />
                </button>
            </div>
        </div>
        <div class="web_header">
            <h1 class="h1_web_brand"> NICE LAPTOP </h1>
        </div>

        <div class="web_body" >
            <div id="options">

                <div class="brand_option">
                    <div class="dropdown">
                        <button class="dropbtn">Brands</button>
                        <div class="dropdown-content">
                            <c:set var="brandList" value="${requestScope.BRANDLIST}"/>
                            <c:if test="${not empty brandList}">
                                <c:forEach var="brand" items="${brandList}">
                                    <a href="#" onclick="applyXSLTForList('GetAllProductPagingController', 'stylesheetXSL/laptopListStyle.xsl', '${brand}')">${brand}</a>
                                </c:forEach>

                            </c:if>                   
                        </div>
                    </div> 
                </div>
            </div>  
            <div id="laptop_list_content">

            </div>
            <div>       
                <c:set var="pagingCount" value="${requestScope.PAGINGCOUNT}"/>
                <c:if test="${not empty pagingCount}">
                    <c:forEach var="page" items="${pagingCount}">
                        <a href="#">${count}</a>
                    </c:forEach>

                </c:if>   
            </div>         


        </div>
    </body>
</html>


