<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/15
  Time: 10:33
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
    <title>原材料记录</title>

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
                    <a class="waves-attach" href=""><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
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
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;CEO中心</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/order-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看订单列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_finance">财务部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_finance">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/bill"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看账单列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                                                <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/material-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/material-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看原材料记录</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_plan">生产计划科</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_plan">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/plan-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看生产计划列表</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_product">成品库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_product">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/product-list"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品列表</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/ceo/product-record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看成品记录</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">原材料</h1>
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
                                        <label class="floating-label" for="clientName">库存ID</label>
                                        <input class="form-control maxwidth-edit"  autocomplete="off"  name="clientName" id="clientName">
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
                                        <div id="inRecord-inner"></div>
                                        <div id="outRecord-inner"></div>
                                        <div id="destroyRecord-inner"></div>
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

                <div aria-hidden="true" class="modal modal-va-middle fade" id="materialinfo" role="dialog" tabindex="-1" style="display: none;position: center;">
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
            url: "${pageContext.request.contextPath}/admin/ceo/materialRecordInfo",
            dataType: "json",
            async:false,
            data: {
            },
            success: function(data){
                var inRecordInfoList = eval(data.inRecordInfoList);
                var inRecordHTML = [];
                inRecordHTML.push('<p class="card-heading">入库记录</p>');
                for (let i=0; i<inRecordInfoList.length; i++)
                {
                    let record = inRecordInfoList[i];
                    inRecordHTML.push('<div class="tile tile-collapse">\n' +
                        '                  <div data-toggle="tile" data-target="#head0'+i+'">\n' +
                        '                      <div class="tile-inner">\n' +
                        '                           <div class="text-overflow node-textcolor">\n' +
                        '                               <span class="enable-flag">'+'库存ID:'+record.inventory_id +'</span>\n' +
                        '    |\n' +
                        '                           <span class="node-icon">\n' +
                        '                            <i class="icon icon-lg">notifications_none</i></span>\n' +
                        '                        <span class="node-tr">'+record.material_id +':'+ record.material_name+'</span>'  +

                        '                               <span class="node-icon">\n' +
                        '                                   <i class="icon icon-lg">flight_takeoff</i></span>\n' +
                        '                               <strong><b><span class="node-alive">'+'数量'+record.quantity +'</span></b></strong>\n' +
                        '            |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                    <i class="icon icon-lg">cloud</i></span>\n' +
                        '                               <span class="node-load"> '+'入库时间：'+(new Date(record.record_create_time)).toLocaleString()+'</span>\n' +
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
                        '                <a href="javascript:void(0);" onclick="">查看详细信息</a>\n' +
                        '            </p>\n' +
                        '            <div>\n' +
                        '                <i class="icon icon-lg node-icon">chat</i> 系统信息提示节点 不用于上网连接\n' +
                        '            </div>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '              </div>')
                }

                var outRecordInfoList = eval(data.outRecordInfoList);
                var outRecordHTML = [];
                outRecordHTML.push('<p class="card-heading">出库记录</p>');
                for (let i=0; i<outRecordInfoList.length; i++)
                {
                    let record = outRecordInfoList[i];
                    outRecordHTML.push('<div class="tile tile-collapse">\n' +
                        '                  <div data-toggle="tile" data-target="#head1'+i+'">\n' +
                        '                      <div class="tile-inner">\n' +
                        '                           <div class="text-overflow node-textcolor">\n' +
                        '                               <span class="enable-flag">'+'库存ID:'+record.inventory_id +'</span>\n' +
                        '    |\n' +
                        '                           <span class="node-icon">\n' +
                        '                            <i class="icon icon-lg">notifications_none</i></span>\n' +
                        '                        <span class="node-tr">'+record.material_id +':'+ record.material_name+'</span>'  +
                        '    |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                   <i class="icon icon-lg">flight_takeoff</i></span>\n' +
                        '                               <strong><b><span class="node-alive">'+'数量'+record.quantity +'</span></b></strong>\n' +
                        '            |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                    <i class="icon icon-lg">cloud</i></span>\n' +
                        '                               <span class="node-load"> '+'出库时间：'+ (new Date(record.record_create_time)).toLocaleString() +'</span>\n' +
                        '                           </div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '\n' +
                        '                  <div class="collapsible-region collapse" id="head1'+i+'">\n' +
                        '                      <div class="tile-sub">\n' +
                        '                           <br>\n' +
                        '                       <div class="card nodetip-table">\n' +
                        '    <div class="card-main">\n' +
                        '        <div class="card-inner">\n' +
                        '            <p class="card-heading">\n' +
                        '                <a href="javascript:void(0);" onclick="">查看详细信息</a>\n' +
                        '            </p>\n' +
                        '            <div>\n' +
                        '                <i class="icon icon-lg node-icon">chat</i> 系统信息提示节点 不用于上网连接\n' +
                        '            </div>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '              </div>')
                }

                var destroyRecordInfoList = eval(data.destroyRecordInfoList);
                var destroyRecordHTML = [];
                destroyRecordHTML.push('<p class="card-heading">销毁记录</p>');
                for (let i=0; i<destroyRecordInfoList.length; i++)
                {
                    let record = destroyRecordInfoList[i];
                    destroyRecordHTML.push('<div class="tile tile-collapse">\n' +
                        '                  <div data-toggle="tile" data-target="#head2'+i+'">\n' +
                        '                      <div class="tile-inner">\n' +
                        '                           <div class="text-overflow node-textcolor">\n' +
                        '                               <span class="enable-flag">'+'库存ID:'+record.inventory_id +'</span>\n' +
                        '    |\n' +
                        '                           <span class="node-icon">\n' +
                        '                            <i class="icon icon-lg">notifications_none</i></span>\n' +
                        '                        <span class="node-tr">'+record.material_id +':'+ record.material_name+'</span>'  +
                        '    |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                   <i class="icon icon-lg">flight_takeoff</i></span>\n' +
                        '                               <strong><b><span class="node-alive">'+'数量'+record.quantity +'</span></b></strong>\n' +
                        '            |\n' +
                        '                               <span class="node-icon">\n' +
                        '                                    <i class="icon icon-lg">cloud</i></span>\n' +
                        '                               <span class="node-load"> '+'出库时间：'+ (new Date(record.record_create_time)).toLocaleString() +'</span>\n' +
                        '                           </div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '\n' +
                        '                  <div class="collapsible-region collapse" id="head2'+i+'">\n' +
                        '                      <div class="tile-sub">\n' +
                        '                           <br>\n' +
                        '                       <div class="card nodetip-table">\n' +
                        '    <div class="card-main">\n' +
                        '        <div class="card-inner">\n' +
                        '            <p class="card-heading">\n' +
                        '                <a href="javascript:void(0);" onclick="">查看详细信息</a>\n' +
                        '            </p>\n' +
                        '            <div>\n' +
                        '                <i class="icon icon-lg node-icon">chat</i> 系统信息提示节点 不用于上网连接\n' +
                        '            </div>\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        '</div>\n' +
                        '                      </div>\n' +
                        '                  </div>\n' +
                        '              </div>')
                }

                $('#inRecord-inner').html(inRecordHTML.join(''));
                $('#outRecord-inner').html(outRecordHTML.join(''));
                $('#destroyRecord-inner').html(destroyRecordHTML.join(''));
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }

        });

    });
</script>