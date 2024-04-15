<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="testWeb.vo.robotInfo" %>
<%@ page import="testWeb.vo.dto.exploreDto" %>
<%@ page import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.IOException" %>
<%--
  Created by IntelliJ IDEA.
  User: 14927
  Date: 2023/6/28
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    String nickname = (String)session.getAttribute("nickname");
    robotInfo robot = (robotInfo)session.getAttribute("robot");
    exploreDto explore = (exploreDto)session.getAttribute("newExplore");
    int exploreId = (int)session.getAttribute("newExploreId");
  %>
  <title> Display an exploration - BUPT Robot Manager System </title>

  <link href="${pageContext.request.contextPath}//css/styles.css" rel="stylesheet" />
  <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

  <script>
      var myVar = setInterval( function(){ setTime() }, 1000);
      function setTime() {
          var startTime = <%=explore.getStartTime().getTime()%>;
          var endTime = new Date();
          document.getElementById('datetime').innerHTML=
              "Explored Time：" + parseInt((endTime - startTime)/1000).toString() + " seconds";
      }
  </script>

</head>

<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
  <!-- Navbar Brand-->
  <a class="navbar-brand ps-3" href="homepage.jsp">BUPT Robot Manager System</a>
  <!-- Sidebar Toggle-->
  <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
  <!-- Navbar Search-->
  <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
    <div class="input-group">
      <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
      <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
    </div>
  </form>
  <!-- Navbar-->
  <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
        <li><span class="dropdown-item-text" > Hello，${nickname} </span></li>
        <li><hr class="dropdown-divider" /></li>
        <li><a class="dropdown-item" href="homepage.jsp">Back to Mainpage</a></li>
        <li><a class="dropdown-item" href="user_update.jsp">Reset password</a></li>
        <li><a class="dropdown-item" href="login.jsp">Account Cancellation</a></li>
      </ul>
    </li>
  </ul>
</nav>

<div id="layoutSidenav">
  <div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
      <div class="sb-sidenav-menu">
        <div class="nav">
          <div class="sb-sidenav-menu-heading">User</div>
          <a class="nav-link" href="homepage.jsp">
            <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
            Mainpage
          </a>

          <div class="sb-sidenav-menu-heading">Functions</div>
          <a class="nav-link" href="${pageContext.request.contextPath}/treasure">
            <div class="sb-nav-link-icon"><i class="fas fa-globe"></i></div>
            Querying for Treasure Information
          </a>
          <a class="nav-link" href="${pageContext.request.contextPath}/mazeinfo">
            <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
            Querying for Maze Information
          </a>
          <a class="nav-link" href="${pageContext.request.contextPath}/explore">
            <div class="sb-nav-link-icon"><i class="fas fa-book"></i></div>
            Querying for Exploring Information
          </a>
          <a class="nav-link" href="${pageContext.request.contextPath}/goingExplore">
            <div class="sb-nav-link-icon"><i class="fas fa-book"></i></div>
            Start an Exploration
          </a>
        </div>
      </div>

      <div class="sb-sidenav-footer">
        <div class="small">Driven By: </div>
        Start Bootstrap
      </div>

    </nav>
  </div>
  <div id="layoutSidenav_content">
    <main>
      <div class="container-fluid px-4">
        <h1 class="mt-4">Exploration in progress……</h1>
        <h4 class="breadcrumb mb-4">
          <li class="breadcrumb-item active"> ”<%=robot.getRobotName()%>” Exploring <%=explore.getMazeId()%>-th Maze! </li>
        </h4>

        <div class="container">
          <%
            String fileBasePath = "C:\\Users\\14927\\Desktop\\SmallTerm\\RobotControlSystem\\web\\assets\\";
            String filePath = fileBasePath + "img\\treasure\\treasure"+exploreId+".jpg";
            File f = new File(filePath);
            boolean imgExist = f.exists();
          %>
          <h3 id="datetime"></h3>
          <h3>Treasure Having Explored: <% if (imgExist) {%> 1 treasure. <%} else {%> Treasure Not Found! <%}%> </h3>
          <% if (imgExist) {
            String labelPath = fileBasePath + "img\\label\\label"+exploreId+".txt";
            String label = "";
            try {
              BufferedReader bufferedReader = new BufferedReader(new FileReader(labelPath));
              label = bufferedReader.readLine();
              session.setAttribute("newTreasureType", label);
            } catch (Exception e) { e.printStackTrace(); }
          %>
            <div class="card col-sm-3 mx-5 mb-4">
              <img class="card-img-top img-fluid" src="assets/img/treasure/treasure<%=exploreId%>.jpg" alt="Card image" style="width:100%">
              <h4  class="card-header card-title text-center"><%=exploreId%>-th Treasure. </h4>
              <div class="card-body">
                <p class="card-text"> <b>Type of Treasure:</b> <%=label%>-level. </p>
                <p class="card-text"> <b>Belong to:</b> <%=explore.getMazeId()%>-th Maze. </p>
<%--                <p class="card-text"> <b>发现时间</b>：  <%=treasure.getFindTime()%></p>--%>
              </div>
            </div>
          <%}%>
          <p class="mt-4"></p>
          <form action="endExplore" method="post">
              <div class="mt-4 mb-0">
                <div class="d-grid col-sm-6 offset-sm-3"><button type="submit" class="btn btn-danger btn-block" > Exploration End! </button></div>
              </div>
          </form>
      </div>
      </div>
    </main>
    <footer class="py-4 bg-light mt-auto">
      <div class="container-fluid px-4">
        <div class="d-flex align-items-center justify-content-between small">
          <div class="text-muted">Copyright &copy; Robot Management System 2023</div>
          <div>
            <a href="#">Privacy Policy</a>
            &middot;
            <a href="#">Terms &amp; Conditions</a>
          </div>
        </div>
      </div>
    </footer>
  </div>
</div>


<%-- Script info. --%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/datatables-simple-demo.js"></script>

</body>
</html>
