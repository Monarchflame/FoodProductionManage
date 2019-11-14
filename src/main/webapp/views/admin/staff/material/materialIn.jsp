<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/14
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
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
        <div class="content-header ui-content-header">
            <div class="container">
                <h1 class="content-heading">商品列表</h1>
            </div>
        </div>
        <div class="container">
            <div class="col-lg-12 col-sm-12">
                <section class="content-inner margin-top-no">
                    <div class="ui-switch">
                        <div class="card">
                            <div class="card-main">
                                <div class="card-inner ui-switch">
                                    <div class="switch-btn" id="switch-cards">
                                        <a href="#" onclick="javascript:UIchange();">
                                            <i class="mdui-icon material-icons">apps</i>
                                        </a>
                                    </div>
                                    <div class="switch-btn" id="switch-table">
                                        <a href="#" onclick="javascript:UIchange();">
                                            <i class="mdui-icon material-icons">dehaze</i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="shop-flex" style="display: flex;" id="shop-flex">

                    </div>

                    <div aria-hidden="true" class="modal modal-va-middle fade" id="verify_modal" role="dialog" tabindex="-1">
                        <div class="modal-dialog modal-xs">
                            <div class="modal-content">
                                <div class="modal-heading">
                                    <a class="modal-close" data-dismiss="modal">×</a>
                                    <h2 class="modal-title">您确认选择此原材料入库吗？</h2>
                                </div>
                                <div class="modal-footer">
                                    <p class="text-right">
                                        <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="choose_material" type="button">确定
                                        </button>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div aria-hidden="true" class="modal modal-va-middle fade" id="order_modal" role="dialog" tabindex="-1">
                        <div class="modal-dialog modal-xs">
                            <div class="modal-content">
                                <div class="modal-heading">
                                    <a class="modal-close" data-dismiss="modal">×</a>
                                    <h2 class="modal-title">确认</h2>
                                </div>
                                <div class="modal-inner">
                                    <p id="id_label">原材料ID：<span id="id"></span></p>
                                    <p id="name_label">原材料名称：<span id="name"></span></p>
                                    <p id="number_label">原材料数量：<input id="number" type="number" oninput="javascript:calculates()"></p>
                                    <p id="shelf_life_label">保质期：<span id="shelf_life"></span></p>
                                </div>
                                <div class="modal-footer">
                                    <p class="text-right">
                                        <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="order_verify" type="button">确定
                                        </button>
                                    </p>
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
                </section>
            </div>
        </div>
    </main>
</body>
</html>
<script>
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "/admin/staff/material/staff/allMaterialInfoList",
            dataType: "json",
            data: {
            },
            //查询商品及客户信息返回相应数据
            success: function(data){
                var list = eval(data.materialList);
                var materialHTML = [];
                for(var i = 0;i < list.length;i++) {
                    var material = list[i];
                    materialHTML.push(
                        '<div class="card">' +
                        '<div class="card-main">' +
                        '<div class="shop-name">'+'原材料ID:'+ material.id +'</div>' +
                        '<div class="shop-content">' +
                        '<div class="shop-content-left">'+'原材料名称:'+'</div>' +
                        '<div class="shop-content-right">'+material.name+'</div>' +
                        '<div class="shop-content-left">'+'保质期:'+'</div>' +
                        '<div class="shop-content-right">'+material.shelf_life+'天'+'</div>' +
                        '</div>'+
                        '<a class="btn btn-brand-accent shop-btn" href="javascript:void(0);" onclick="javascript:add(' + JSON.stringify(material).replace(/"/g, '&quot;')  + ')\">添加</a>'+
                        '</div></div>');
                }
                //下面的div与布局有关
                materialHTML.push('<div class="flex-fix3"></div>' +
                    '<div class="flex-fix4"></div>');
                $('#shop-flex').html(materialHTML.join(''));
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    <%--点击添加--%>
    var thematerial;
    add = function(material) {
        //弹出
        $("#verify_modal").modal();
        thematerial = material;
    };
    //点击确认添加，弹出信息面板
    $("#choose_material").click(function () {
        document.getElementById('id').innerHTML = thematerial.id;
        document.getElementById('number').value = 1;
        document.getElementById('name').innerHTML = thematerial.name;
        document.getElementById('shelf_life').innerHTML = thematerial.shelf_life;
        $("#order_modal").modal();
    });

    calculates = function () {
        var number = $("#number").val();//这是数字
        if (number < 1)
        {
            document.getElementById('number').value=1;
            number = 1;
        }
    };

    //确认添加
    $("#order_verify").click(function () {
        $.ajax({
            type: "POST",
            url: "/admin/staff/material/staff/addInventory",
            dataType: "json",
            data: {
                materialId:thematerial.id,
                quantity:$("#number").val(),
                shelf_life:thematerial.shelf_life ,
            },
            success: function(data){
                if (data.ret === 1) {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = '入库成功，已自动生成入库记录';
                    window.setTimeout("location.href='/admin/staff/material/staff/materialIn'", 1200);
                } else {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = '入库失败';
                }
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        });
        //清空信息
        document.getElementById('number').value="";
    });
</script>