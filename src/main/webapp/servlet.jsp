<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%
    String path = (String)request.getAttribute("path");
    if(path == null){
        path = "D:/filemanager/";
    }
    String previousPath = (String)request.getAttribute("previousPath");
%>
<html>
<head>
    <style>
        button {
            padding: 10px;
            background: linear-gradient(to right, #0056b3, #800080);
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        img {
            height: 24px;
            width: 24px;
        }
    </style>
    <title>Files</title>
</head>
<body>
<form method="POST">
    <button type="submit">Sign out</button>
</form>
<%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date())%>
<h1>Список файлов и папок</h1>
<h2><%=path%></h2>
<table>
    <tr>
        <th align="left"><img src="https://img.forum.ge/attach/post-54-1408628340.jpg"/></th>
        <th><a href="?path=<%=java.net.URLEncoder.encode(previousPath, "UTF-8")%>">Вверх</a></th>
    </tr>
</table>
<table>
    <tr>
        <th></th>
        <th><b>Файл</b></th>
        <th><b>Размер</b></th>
        <th><b>Дата</b></th>
    </tr>
    <%
        File[] directories =  (File[])request.getAttribute("directories");
        for(File directory : directories){
    %>
    <tr>
        <th align="left"><img src="https://icon-library.com/images/folder-icon/folder-icon-1.jpg"/></th>
        <th><a href="?path=<%=java.net.URLEncoder.encode(directory.getAbsolutePath(), "UTF-8")%>"><%=directory.getName() + "/"%></a></th>
        <th></th>
        <th><%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(directory.lastModified()))%></th>
    </tr>
    <%}%>

    <%
        File[] files =  (File[])request.getAttribute("files");
        for(File file : files){
    %>
    <tr>
        <th align="left"><img src="https://www.ams-kovka.ru/upload/medialibrary/88f/orxus3i7tpmiiwyt23x8pxaynvtf13n7/699345.png"/></th>
        <th><a href="download?path=<%=java.net.URLEncoder.encode(file.getAbsolutePath(), "UTF-8")%>"><%=file.getName()%></a></th>
        <th><%=file.length() + " B"%></th>
        <th><%=new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(new Date(file.lastModified()))%></th>
    </tr>
    <%}%>
</table>
</body>
</html>