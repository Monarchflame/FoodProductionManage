<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/19
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/labellogo.jpg" type="image/x-icon">
    <meta name="theme-color" content="#4285f4">
    <title>员工管理</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/user.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery.tablesorter.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="4text/javascript"></script>

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
                    <a class="waves-attach" href="${pageContext.request.contextPath}admin/leader/sale/leader"><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
                </li>
                <li>
                    <a class="padding-right-cd waves-attach" href="${pageContext.request.contextPath}/admin"><span class="icon icon-lg margin-right">exit_to_app</span>登出</a>
                </li>
            </ul>
        </div>
    </ul>
</header>
<nav aria-hidden="true" class="menu menu-left nav-drawer nav-drawer-md" id="ui_menu" tabindex="-1">
    <div class="menu-scroll">
        <div class="menu-content">

            <a class="menu-logo" href="${pageContext.request.contextPath}/admin"><i class="icon icon-lg">language</i>&nbsp后台</a>
            <ul class="nav">
                <li>
                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_me">我的</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_me">
                        <li>
                            <a href="${pageContext.request.contextPath}admin/leader/sale/leader"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/leader/sale/leader/order-list"><i class="icon icon-lg">account_box</i>&nbsp;查看订单</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/leader/sale/leader/staff-list"><i class="icon icon-lg">account_box</i>&nbsp;查看员工</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">产品</h1>
        </div>
    </div>
    <div class="container">
        <div class="col-lg-12 col-sm-12">
            <section class="content-inner margin-top-no">
                <div class="row">

                    <div class="col-lg-12 col-md-12">
                        <div class="card margin-bottom-no">
                            <div class="card-main">
                                <div class="card-inner">
                                    <div class="cardbtn-edit">
                                        <div class="card-heading">查找</div>
                                        <button class="btn btn-flat" id="findRequirement"><span class="icon">check</span>&nbsp;
                                        </button>
                                    </div>
                                    <div class="form-group form-group-label">
                                        <label class="floating-label" for="staffId">员工ID</label>
                                        <input class="form-control maxwidth-edit"  autocomplete="off"  name="staffId" id="staffId">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12 col-sm-12 nodelist">

                        <div class="card node-table" style="display: flex;">
                            <div class="card-main">
                                <div class="card-inner margin-bottom-no">
                                    <div class="tile-wrap">
                                        <div id="staff-inner"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div aria-hidden="true" class="modal modal-va-middle fade" id="result" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-inner">
                                <p class="h5 margin-top-sm text-black-hint" id="msg"></p>
                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok" onclick="location.reload()">知道了
                                    </button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>

                <div aria-hidden="true" class="modal modal-va-middle fade" id="staffinfo" role="dialog" tabindex="-1" style="display: none;position: center;">
                    <div class="modal-dialog modal-full">
                        <div class="modal-content">
                            <iframe class="iframe-seamless" title="Modal with iFrame" id="infoifram" ></iframe>
                        </div>
                    </div>
                </div>

            </section>
        </div>
    </div>
</main>

</body>
</html>
<script>
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/leader/sale/leader/allStaffInfo",
            dataType: "json",
            async:false,
            data: {
            },
            success: function(data){
                var staffInfoList = eval(data.staffInfoList);
                var staffHTML = [];
                staffHTML.push('<p class="card-heading">员工信息</p>');
                for (let i=0; i<staffInfoList.length; i++)
                {
                    let staff = staffInfoList[i];
                    staffHTML.push('<div class="tile tile-collapse">\n' +
                        '                  <div data-toggle="tile" data-target="#head0'+i+'">\n' +
                        '                      <div class="tile-inner">\n' +
                        '                           <div class="text-overflow node-textcolor">\n' +
                        '                               <span class="enable-flag">'+'员工ID:'+staff.id +'</span>\n' +
                        '    |\n' +
                        '                           <span class="node-icon">\n' +
                        '                            <i class="icon icon-lg">notifications_none</i></span>\n' +
                        '                        <span class="node-tr">'+'员工姓名:'+ staff.name+'</span>'  +

                        '                               <span class="node-icon">\n' +
                        '                                   <i class="icon icon-lg">flight_takeoff</i></span>\n' +
                        '                               <strong><b><span class="node-alive">'+'职位:'+staff.position +'</span></b></strong>\n' +
                        '            |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                    <i class="icon icon-lg">cloud</i></span>\n' +
                        '                               <span class="node-load"> '+'工资：'+ staff.salary +'</span>\n' +
                        '                           </div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '\n' +
                        '                  <div class="collapsible-region collapse" id="head0'+i+'">\n' +
                        '                      <div class="tile-sub">\n' +
                        '                           <br>\n' +
                        '                       <div class="card nodetip-table">\n' +
                        '    <div class="card-main">\n' +
                        '        <div class="card-inner">\n' +
                        '            <p class="card-heading">\n' +
                        '                <a href="javascript:void(0);" onclick="staffInfo('+ staff.id +')">查看详细信息</a>\n' +
                        '            </p>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '              </div>')
                }

                $('#staff-inner').html(staffHTML.join(''));
            },
            error: function() {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        });

        staffInfo = function (id) {
            document.getElementById('infoifram').src = '${pageContext.request.contextPath}/admin/leader/sale/leader/staffInfo?staffId=' + id;
            $("#staffinfo").modal();
        }
    });
</script>
