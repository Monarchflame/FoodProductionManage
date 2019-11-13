<%@ page import="cn.qxt.pojo.Client" %><%--
Created by IntelliJ IDEA.
User: 10703
Date: 2019/9/18
Time: 15:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% Client client=(Client)request.getSession().getAttribute("LOGIN_USER"); %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="../../images/labellogo.jpg" type="image/x-icon">
    <meta name="theme-color" content="#4285f4">
    <title>客户界面</title>

    <link href="../../theme/css/base.min.css" rel="stylesheet">
    <!--小图片-->
    <link href="../../theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <!--柱状图样式和背景图-->
    <link rel="stylesheet" href="../../theme/css/user.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>

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
                    <a class="padding-right-cd waves-attach" href="/"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>
<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="/"><i class="icon icon-lg">language</i>&nbsp首页</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;用户中心</a>
                        </li>
                        <li>
                            <a href="/user/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                        <li>
                            <a href="/user/myorder"><i class="icon icon-lg">event_note</i>&nbsp;我的订单</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">业务</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="/user/shop"><i class="icon icon-lg">shopping_basket</i>&nbsp;商城</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>
<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">用户中心</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">姓名</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        <dd><% out.print(client.getName());%></dd>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-yellow">
                                    <i class="icon icon-md">person</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span><i class="icon icon-md"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">类型</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        <dd><% out.print(client.getType());%></dd>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-blue">
                                    <i class="icon icon-md">open_with</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span><i class="icon icon-md"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">信用等级</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        <dd><% out.print(client.getCreditrating());%></dd>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-red">
                                    <i class="icon icon-md t4-text">stars</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span><i class="icon icon-md">add_circle</i>购买</span>
                                <a href="/user/shop" class="card-tag tag-orange">商店</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xx-12 col-xs-6 col-lg-3">
                    <div class="card user-info">
                        <div class="user-info-main">
                            <div class="nodemain">
                                <div class="nodehead node-flex">
                                    <div class="nodename">余额</div>
                                </div>
                                <div class="nodemiddle node-flex">
                                    <div class="nodetype">
                                        ￥<% out.print(client.getAccountbalance());%>
                                    </div>
                                </div>
                            </div>
                            <div class="nodestatus">
                                <div class="infocolor-green">
                                    <i class="icon icon-md">account_balance_wallet</i>
                                </div>
                            </div>
                        </div>
                        <div class="user-info-bottom">
                            <div class="nodeinfo node-flex">
                                <span><i class="icon icon-md">attach_money</i>账户余额</span>
                                <a href="/user/code" class="card-tag tag-green">充值</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui-card-wrap">
                <div class="col-xx-12 col-sm-6">
                    <div class="card">
                        <div class="card-main">
                            <div class="card-inner margin-bottom-no" id="message">

                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xx-12 col-sm-6">
                    <div class="card" id="credit">
                        <div class="card-main">
                            <div class="card-inner margin-bottom-no">
                                <p class="card-heading"><i class="icon icon-md">notifications_active</i>&nbsp;当前信用等级与优惠和预付款比例关系</p>
                                <div class="card-table">
                                    <div class="table-responsive table-user">
                                        <table class="table table-fixed" id="clientTable">
                                            <tbody>
                                            <tr>
                                                <th>信用等级</th>
                                                <th>单件商品折扣</th>
                                                <th>最低预付款比例</th>
                                            </tr>
                                            <c:forEach items="${credits}" var="c" varStatus="status">
                                                <tr>
                                                    <td>${c.grade}</td>
                                                    <td>${c.discount}%</td>
                                                    <td>${c.ratio}%</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>


<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="../../theme/js/base.min.js" type="text/javascript"></script>
<script src="../../theme/js/project.min.js" type="text/javascript"></script>
</body>
</html>


<script>
    $(document).ready(function ()
    {
        //填写消息
        $.ajax({
            type: "POST",
            url: "/user/findMessage",
            dataType: "json",
            data: {
                clientId: '<% out.print(client.getId());%>'
            },
            //查询商品及客户信息返回相应数据
            success: function(data) {
                let messageList = eval(data.messageList);
                let messageHTML = [];
                messageHTML.push('<p class="card-heading"><i class="icon icon-md">notifications_active</i>&nbsp;消息栏</p>\n <hr />\n');
                if (messageList != null)
                {
                    for (let i=0 ; i<messageList.length; i++)
                    {
                        const message = messageList[i];
                        //alert(JSON.stringify(message));
                        messageHTML.push('<p>'+ message.message +'</p>\n');
                    }
                }
                messageHTML.push('<br />');
                $('#message').html(messageHTML.join(''));
            },
            error: function () {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    }
    )
</script>