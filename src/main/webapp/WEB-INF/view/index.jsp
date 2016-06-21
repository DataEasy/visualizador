<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>GroupDocs Annotation for Java Sample</title>
    <%=(String) request.getAttribute("annotation_head")%>
</head>
<body>
<div id="container-visualizador"
     style="width:100%;height:100%;overflow:hidden;position:relative;margin-bottom:20px;background-color:gray;border:1px solid #ccc;"></div>
<%=(String) request.getAttribute("annotation_scripts")%>
</body>
</html>