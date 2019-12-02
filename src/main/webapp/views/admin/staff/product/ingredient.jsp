<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/27
  Time: 20:43
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
    <title>配料表</title>

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
                            <a href=""><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_product">成品库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/product-list"><i class="icon icon-lg">account_box</i>&nbsp;查看货物库存</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/add"><i class="icon icon-lg">add</i>&nbsp;添加产品类型</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/in"><i class="icon icon-lg">add_box</i>&nbsp;货物入库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/out"><i class="icon icon-lg">announcement</i>&nbsp;货物出库</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/return"><i class="icon icon-lg">announcement</i>&nbsp;处理退货</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看出入库记录</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/staff/product/staff/ingredient"><i class="icon icon-lg">change_history</i>&nbsp;查看产品配方</a>
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

                <div aria-hidden="true" class="modal modal-va-middle fade" id="alter_verify_modal" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-heading">
                                <a class="modal-close" data-dismiss="modal">×</a>
                                <h2 class="modal-title">您确认修改此产品的配料表吗？</h2>
                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" type="button">确定
                                    </button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div aria-hidden="true" class="modal modal-va-middle fade" id="add_verify_modal" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-heading">
                                <a class="modal-close" data-dismiss="modal">×</a>
                                <h2 class="modal-title">您确认添加此产品的配料吗？</h2>
                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" type="button" onclick="showInfo()">确定
                                    </button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div aria-hidden="true" class="modal modal-va-middle fade" id="delete_verify_modal" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-heading">
                                <a class="modal-close" data-dismiss="modal">×</a>
                                <h2 class="modal-title">您确认删除此产品的配料吗？</h2>
                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" type="button" onclick="showInfo()">确定
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
                            <div id="model-inner" class="modal-inner">

                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="order_verify" type="button" onclick="showInfo()">确定
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
            url: "${pageContext.request.contextPath}/admin/staff/product/staff/allGoodsInfoList",
            dataType: "json",
            async: false,
            data: {
            },
            //查询商品及客户信息返回相应数据
            success: function(data){
                let list = eval(data.productList);
                let productHTML = [];
                let ingredientList;
                for(let i = 0;i < list.length;i++) {
                    let product = list[i];
                    productHTML.push(
                        '<div class="card">' +
                        '<div class="card-main">' +
                        '<div class="shop-name">'+'产品ID:'+ product.id +'</div>' +
                        '<div class="shop-content">' +
                        '<div class="shop-content-left">'+'产品名称:'+'</div>' +
                        '<div class="shop-content-right">'+product.name+'</div>' +
                        '<div class="shop-content-left">'+'保质期:'+'</div>' +
                        '<div class="shop-content-right">'+product.shelf_life+'天'+'</div>' +
                        '</div>'+
                        '<div class="shop-content-extra">');
                    $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/admin/staff/product/staff/ingredientInfo",
                        dataType: "json",
                        async: false,
                        data: {productId : product.id},
                        success: function(data2){
                            ingredientList = eval(data2.ingredientInfo);
                            for(let j = 0;j < ingredientList.length;j++) {
                                productHTML.push(
                                    '<div>'+'<span class="icon">check_circle_outline</span>'+ingredientList[j].material_name +' : '+ ingredientList[j].material_quantity+'</div>'
                                );
                            }
                        },
                        error: function(jqXHR){
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = `发生了错误`;
                        }
                    });
                    productHTML.push('</div>'+
                        '<a class="btn btn-brand-accent shop-btn" href="javascript:void(0);" onclick="javascript:addIngredient()\">添加原材料</a>'+
                        '<a class="btn btn-brand-accent shop-btn" href="javascript:void(0);" onclick="javascript:deleteIngredient(' + JSON.stringify(ingredientList).replace(/"/g, '&quot;')  + ')\">删除原材料</a>'+
                        '<a class="btn btn-brand-accent shop-btn" href="javascript:void(0);" onclick="javascript:alterIngredient(' + JSON.stringify(ingredientList).replace(/"/g, '&quot;')  + ')\">修改原材料</a>'+
                        '</div></div>');
                }
                //下面的div与布局有关
                productHTML.push('<div class="flex-fix3"></div>' +
                    '<div class="flex-fix4"></div>');
                $('#shop-flex').html(productHTML.join(''));
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    //添加原材料
    addIngredient = function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/staff/product/staff/allMaterialList",
            dataType: "json",
            async: false,
            data: {
            },
            success(data)
            {
                let html = [];
                let materialList = data.materialList;
                for (let j = 0;j < materialList.length;j++)
                {
                    html.push('<div class="checkbox switch">\n'+
                        '<label for="pay_deposit">\n' +
                        '<input id="'+j+'material" class="access-hide" type="checkbox" onclick="function x() { if (this.checked===false){this.checked=true} else this.checked=false }">\n' +
                        '<span class="switch-toggle"></span>'+ materialList[j].name +
                        '<input id="'+j+'number" type="number" oninput="javascript:calculates()">'+
                        '</label>\n'+
                        '</div><br>')
                }
                $('#model-inner').html(html.join(''));
                $("#add_verify_modal").modal();
            },
            error(data)
            {

            }
        })
    };
    deleteIngredient = function (ingredientList) {
        let html = [];
        for (let j = 0;j < ingredientList.length;j++)
        {
            html.push('<div class="checkbox switch">\n'+
                '                                    <label for="pay_deposit">\n' +
                '                                        <input id="'+j+'material" class="access-hide" type="checkbox" onclick="function x() { this.checked=this.checked===false; }">\n' +
                '                                        <span class="switch-toggle"></span>'+ ingredientList[j].material_name +
                '                                    </label>\n'+
                '</div>')
        }
        $('#model-inner').html(html.join(''));
        $("#delete_verify_modal").modal();
    };

    alterIngredient = function (ingredientList) {
        let html = [];
        html.push('<p id="id_label">原材料名称：<span id="material_name">'+ ingredientList.material_name +'</span></p>');
        html.push('<p id="id_label">原材料数量：<input id="number" type="number" oninput="javascript:calculates()">');
        $('#model-inner').html(html.join(''));
        document.getElementById('number').value = ingredientList.material_quantity;
        $("#alter_verify_modal").modal();
    };

    //弹出信息面板
    showInfo = function()
    {
        $("#order_modal").modal();
    };

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
            url: "${pageContext.request.contextPath}/admin/staff/product/staff/changeIngredient",
            dataType: "json",
            data: {
                productId:theproduct.id,
                quantity:$("#number").val(),
                shelf_life:theproduct.shelf_life ,
            },
            success: function(data){
                if (data.ret === 1) {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = '入库成功，已自动生成入库记录';
                    window.setTimeout("location.href='/admin/staff/product/staff/in'", 1200);
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
