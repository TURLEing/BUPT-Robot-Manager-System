<%--
  Created by IntelliJ IDEA.
  User: 14927
  Date: 2023/6/29
  Time: 11:30
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
  <title> 账户密码修改 - 巴普特机器人管理系统 </title>

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
      <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
      <% if (errorMessage != null) { %>
      <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <%= errorMessage %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <% } %>

      <% String successMessage = (String) request.getAttribute("successMessage"); %>
      <% if (successMessage != null) { %>
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        <%= successMessage %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <% } %>

      <div class="container-fluid px-4">
        <h1 class="mt-4">Password Modification</h1>
        <h4 class="breadcrumb mb-4">
          <li class="breadcrumb-item active"> Attention: The new password cannot be same with the old password! </li>
        </h4>

        <div class="container col-8 offset-2">
          <form action="userUpdate" method="post">
            <div class="form-group row mb-2">
              <label for="oriPassword" class="col-sm-2 col-form-label"><b>Origin Password</b></label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="oriPassword" name="oriPassword" placeholder="input your Origin Password">
              </div>
            </div>
            <div class="form-group row mb-2">
              <label for="newPassword" class="col-sm-2 col-form-label"><b>New Password</b></label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="input your New Password">
              </div>
            </div>
            <div class="form-group row mb-4">
              <label for="confirmPassword" class="col-sm-2 col-form-label"><b>Password in confirm</b></label>
              <div class="col-sm-10">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm your Password">
              </div>
            </div>
            <div class="mt-4 mb-0">
              <div class="d-grid col-sm-6 offset-sm-3"><button type="submit" class="btn btn-primary btn-block">Summit</button></div>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>

</body>
</html>
