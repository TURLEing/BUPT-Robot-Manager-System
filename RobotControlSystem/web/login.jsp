<%--
  Created by IntelliJ IDEA.
  User: 14927
  Date: 2023/6/26
  Time: 9:36
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
  %>

  <title> Login - BUPT Robot Manager System </title>
  <link rel="stylesheet" href="<%=basePath%>/css/styles.css">
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

  <style>
    body {
      background-image: url("<%=basePath%>/assets/img/R-C.jpeg");
      background-repeat: no-repeat;
      background-size: cover;
    }

    .card {
        opacity: 0.9;
        transition: opacity 1s ease;
    }
    .card:hover {
        opacity: 1;
        transition: opacity 0.3s ease;
    }
  </style>

  <script>
      document.addEventListener('DOMContentLoaded', function() {
          // 获取卡片元素
          var card = document.querySelector('.card');

          // 添加鼠标移入和移出事件监听器
          card.addEventListener('mouseenter', function() {
              // 设置完全不透明
              card.style.opacity = '1';
          });

          card.addEventListener('mouseleave', function() {
              // 设置80%透明
              card.style.opacity = '0.9';
          });
      });
  </script>

</head>

<body>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
<div class="alert alert-danger alert-dismissible fade show" role="alert">
  <%= errorMessage %>
  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>
<% } %>

<div id="layoutAuthentication">
  <div id="layoutAuthentication_content">
    <main>
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-5">
            <div class="card shadow-lg border-0 rounded-lg mt-5">
              <div class="card-header"><h3 class="text-center font-weight-light my-4">BUPT Robot Manager System</h3></div>
              <div class="card-body">
                <form action="login" method="post">
                  <div class="form-floating mb-3">
                    <input class="form-control" id="username" name="username" type="text" placeholder="name@example.com" />
                    <label for="username">Username</label>
                  </div>
                  <div class="form-floating mb-3">
                    <input class="form-control" id="inputPassword" name="password" type="password" placeholder="Password" />
                    <label for="inputPassword">Password</label>
                  </div>
                  <div class="form-check mb-3">
                    <input class="form-check-input" id="inputRememberPassword" type="checkbox" value="" />
                    <label class="form-check-label" for="inputRememberPassword">Remember Password</label>
                  </div>
                  <div class="mt-4 mb-0">
                    <div class="d-grid"><button type="submit" class="btn btn-primary btn-block">Log in</button></div>
                  </div>
                </form>
              </div>
              <div class="card-footer text-center py-3">
                No account? <a href="register.jsp">Here to register!</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
  <div id="layoutAuthentication_footer">
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="<%=basePath%>/js/scripts.js"></script>
</body>
</html>
