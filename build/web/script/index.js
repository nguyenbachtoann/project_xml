/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var xmlHttp;
var xmlResponse;
var xslResponse;
//function checkLocalStorageListBrand(controllerURL) {
//
//    let xmlDOM = null;
//    if (!localStorage.getItem("LAPTOPLIST")) {
//        let url = controllerURL;
//        xmlDOM = loadXMLDoc(url);
//    } else {
//        xmlDOM = localStorage.getItem("LAPTOPLIST");
//    }
//    return xmlDOM;
//}


function GetXmlHttpObject() {
    xmlHttp = null;
    try {//Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } catch (e) {//Internet Explorer
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xmlHttp = new XMLHttpRequest();
        }
    }
    return xmlHttp;
}



function loadXSLDom(xslUrl, searchValue)
{

    xmlHttp = GetXmlHttpObject();
    if (!xmlHttp) {
        alert("Your browser does not support AJAX!");
        return;
    }

    xmlHttp.open("GET", xslUrl, true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

            xslResponse = xmlHttp.responseXML;
            if (xslResponse != null) {
                applyXSLT(xmlResponse, xslResponse, searchValue);
            }

        }
    };



}

function loadXMLDom(servletUrl, xslUrl, searchValue) {
    xmlHttp = GetXmlHttpObject();
    if (!xmlHttp) {
        alert("Your browser does not support AJAX!");
        return;
    }

    xmlHttp.open("GET", servletUrl, true);
    xmlHttp.send(null);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            xmlResponse = xmlHttp.responseXML;
            if (xmlResponse != null) {
                loadXSLDom(xslUrl, searchValue);
            }
        }
    };

}





function applyXSLTForList(servletUrl, xslUrl, searchValue) {
    
    let localSearch = localStorage.getItem('SEARCHVALUE');
    if (localSearch === null || localSearch === "") {
        localSearch = searchValue;
        localStorage.setItem('SEARCHVALUE', searchValue);
        loadXMLDom(servletUrl, xslUrl, searchValue);
    } else if (!localSearch.includes(searchValue) && searchValue !== "") {
        localSearch = searchValue;
        localStorage.setItem('SEARCHVALUE', searchValue);
        loadXMLDom(servletUrl, xslUrl, searchValue);
    } else {
        localSearch = localStorage.getItem('SEARCHVALUE');
        loadXMLDom(servletUrl, xslUrl, localSearch);
    }

}



function applyXSLT(xmlResponse, xslResponse, searchValue) {

    if (window.ActiveXObject || xmlHttp.responseType == "msxml-document")
    {
        ex = xmlResponse.transformNode(xslResponse);
        document.getElementById("laptop_list_content").innerHTML = ex;
    }
// code for Chrome, Firefox, Opera, etc.
    else if (document.implementation && document.implementation.createDocument)
    {
        xsltProcessor = new XSLTProcessor();
        xsltProcessor.importStylesheet(xslResponse);
        xsltProcessor.setParameter(null, "searchValue", searchValue.toUpperCase());
        document.getElementById("laptop_list_content").innerHTML = "";
        resultDocument = xsltProcessor.transformToFragment(xmlResponse, document);
        document.getElementById("laptop_list_content").appendChild(resultDocument);
        changeImageSlash();
    }

}




function changeImageSlash() {
    var images = document.getElementsByTagName('img');
 
    for (var i = 0; i < images.length; i++)
    {
        var img = images[i];

        if (img.src.length == 0 && (img.src !== 'ICON/magnifying_glass.png'))
        {
            img.src = img.src.split('%5C').join('/');

        }
    }
}





