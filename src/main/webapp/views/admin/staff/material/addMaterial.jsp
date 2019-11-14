<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/14
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="../../../../images/labellogo.jpg" type="image/x-icon">
    <meta name="theme-color" content="#4285f4">
    <title>原材料入库</title>

    <link href="../../../../theme/css/base.min.css" rel="stylesheet">
    <link href="../../../../theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../../../../theme/css/style.css">
    <link rel="stylesheet" href="../../../../theme/css/user.css">

    <script type="text/javascript" src="../../../../theme/js/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../../../theme/js/jquery.tablesorter.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>
    <script src="../../../../theme/js/base.min.js" type="text/javascript"></script>
    <script src="../../../../theme/js/project.min.js" type="4text/javascript"></script>

</head>
<body class="page-orange">
<header class="header header-orange header-transparent header-waterfall ui-header">
    <ul class="nav nav-list pull-left">
        <div>
            <a data-toggle="menu" href="#ui_menu">
                <span class="icon icon-lg text-white">menu</span>
            </a>
        </div>
    </ul>
    <ul class="nav nav-list pull-right">
        <div class="dropdown margin-right">
            <a class="dropdown-toggle padding-left-no padding-right-no" data-toggle="dropdown">
                <span class="access-hide">MonarchFlame</span>
                <span class="icon icon-cd margin-right">account_circle</span>
            </a>
            <ul class="dropdown-menu dropdown-menu-right">
                <li>
                    <a class="waves-attach" href=""><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
                </li>
                <li>
                    <a class="padding-right-cd waves-attach" href="/admin"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>
<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="/admin"><i class="icon icon-lg">language</i>&nbsp后台</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="/admin/staff/material/staff/material-list"><i class="icon icon-lg">account_box</i>&nbsp;查看原材料库存</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/add"><i class="icon icon-lg">add</i>&nbsp;添加原材料类型</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/in"><i class="icon icon-lg">add_box</i>&nbsp;原材料入库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/out"><i class="icon icon-lg">announcement</i>&nbsp;原材料出库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看出入库记录</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>
<main class="content">
    <div class="authpage auth-reg">
        <div class="container" style="width: 50%; height: 50%;" >
            <section>
                <div class="auth-main auth-row">
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="account">邮箱(唯一登录凭证)</label>
                                <input class="form-control maxwidth-auth" id="account" name="account" type="text" maxlength="32">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="name">姓名</label>
                                <input class="form-control maxwidth-auth" id="name" name="name" type="text">
                            </div>
                        </div>
                    </div>

                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="password">密码</label>
                                <input class="form-control maxwidth-auth" id="password" name="password" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group form-group-label">
                                <label class="floating-label" for="repassword">重复密码</label>
                                <input class="form-control maxwidth-auth" id="repassword" name="repassword" type="password">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group form-group-label">
                                <label class="floating-label" for="type">商家类型</label>
                                <select class="form-control maxwidth-auth" id="type">
                                    <option value ="零售商">零售商</option>
                                    <option value ="批发商">批发商</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group-label auth-row">
                                <label class="floating-label" for="phone">手机号</label>
                                <input class="form-control maxwidth-auth" id="phone" name="phone" type="text" maxlength="32">
                            </div>
                        </div>
                    </div>

                    <div class="rowtocol">
                        <div class="auth-row">
                            <div class="form-group form-group-label">
                                <label class="floating-label" for="address">地址</label>
                                <input class="form-control maxwidth-auth" id="address" name="address" type="text">
                            </div>
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="btn-auth auth-row">
                            <button id="tos" type="submit" class="btn-reg btn btn-block btn-brand waves-attach waves-light">确认注册
                            </button>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
</main>
</body>
</html>
