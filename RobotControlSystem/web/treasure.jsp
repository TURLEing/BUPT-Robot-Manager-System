<%--
  Created by IntelliJ IDEA.
  User: 14927
  Date: 2023/6/28
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="testWeb.vo.robotInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="testWeb.vo.dto.treasureDto" %>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    robotInfo robot = (robotInfo)session.getAttribute("robot");
    List<treasureDto> treasures = (List<treasureDto>)session.getAttribute("treasures");
  %>
  <title> Quering for Treasure Infomation - BUPT Robot Manager System </title>

  <link href="css/styles.css" rel="stylesheet" />
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
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
        <h1 class="mt-4">Quering for Treasure Infomation</h1>
        <h4 class="breadcrumb mb-4">
          <li class="breadcrumb-item active"> <%="Up to now, you have found "+ robot.getTotTreasure() +" treasures. "%>  </li>
        </h4>
        <div class="card mb-4">
          <div class="card-header">
            <i class="fas fa-chart-area me-1"></i>
            Treasure Trend -  Line Chart
          </div>
          <div class="card-body"><canvas id="myAreaChart" width="100%" height="30"></canvas></div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>
        <br>
        <h1 class="mt-4">Treasure in detail</h1>
        <h4 class="breadcrumb mb-4">
          <li class="breadcrumb-item active"> Up to now, you have found <%=Math.min(3,treasures.size())%> treasures。</li>
        </h4>

        <div class="container"><div class="row">
          <%
            for (int i=0; i<treasures.size(); i++) {
                treasureDto treasure = treasures.get(i);
                int id = treasure.getTreasureId();
          %>
          <div class="card col-sm-3 mx-5 mb-4">
            <img class="card-img-top img-fluid" src="assets/img/treasure/treasure<%=id%>.jpg" alt="Card image" style="width:100%">
            <h4 class="card-header card-title text-center"><%=id%>-TH Treasure</h4>
            <div class="card-body">
              <p class="card-text"> <b>Type: </b><%=treasure.getType()%>-level treasure</p>
              <p class="card-text"> <b>Maze ID</b>：  <%=treasure.getBelongMazeId()%>-th maze</p>
              <p class="card-text"> <b>Discovery Time</b>：  <%=treasure.getFindTime()%></p>
            </div>
          </div>
          <%}%>
        </div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>

</body>
</html>
