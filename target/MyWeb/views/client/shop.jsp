<%@ page import="cn.qxt.pojo.Client" %><%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/22
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Client client=(Client) request.getSession().getAttribute("LOGIN_USER"); %>

<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <meta name="theme-color" content="#4285f4">
    <title>商店</title>

<%--    <style type="text/css">@import url(${pageContext.request.contextPath}/theme/css/base.min.css);</style>--%>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/user.css">

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
                    <a class="waves-attach" href="${pageContext.request.contextPath}/user"><span class="icon icon-lg margin-right">account_box</span>用户中心</a>
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
                            <a href="${pageContext.request.contextPath}/user"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;用户中心</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/user/myorder"><i class="icon icon-lg">event_note</i>&nbsp;我的订单</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">业务</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href=""><i class="icon icon-lg">shopping_basket</i>&nbsp;商城</a>
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
                <div class="card">
                    <div class="card-main">
                        <div class="card-inner">
                            <p>公告</p>
                            <p><i class="icon icon-lg">attach_money</i>当前余额：<font color="#399AF2" size="5"><% out.print(client.getAccountbalance());%></font> 元</p>
                        </div>
                    </div>
                </div>
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
                                <h2 class="modal-title">您确认购入此商品吗？</h2>
                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="choose_product" type="button">确定
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
                                <a class="modal-close" data-dismiss="modal" onclick="closeorder()">×</a>
                                <h2 class="modal-title">订单确认</h2>
                            </div>
                            <div class="modal-inner">
                                <p id="name_label">商品名称：<span id="name"></span></p>
                                <p id="unit_price_label">商品单价：<span id="unit_price"></span></p>
                                <p id="number_label">商品数量：<input id="number" type="number" oninput="javascript:calculate()"></p>
                                <p id="total_label">总金额：<span id="total"></span></p>
                                <p id="min_advance_payment_label">最低预付款：<span id="min_advance_payment"></span></p>
                                <div class="checkbox switch">
                                    <label for="pay_full">
                                        <input checked="" class="access-hide" id="pay_full" type="radio" onclick="payfull()">
                                        <span class="switch-toggle"></span>付全款
                                    </label>
                                </div>
                                <br>
                                <div class="checkbox switch">
                                    <label for="pay_deposit">
                                        <input checked="" class="access-hide" id="pay_deposit" type="checkbox" onclick="paydeposit()">
                                        <span class="switch-toggle"></span>付定金
                                    </label>
                                </div>
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
<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="4text/javascript"></script>
<%--<script src="https://cdn.jsdelivr.net/npm/clipboard@1.5.16/dist/clipboard.min.js" type="text/javascript"></script>--%>
</body>
</html>
<script type="text/javascript">
    var ratio;

    $(document).ready(function(){
        document.getElementById('pay_deposit').checked = false;
        //根据客户的信用等级，改变商品价格的样式，中间划横线
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/changeprice",
            dataType: "json",
            data: {
            },
            //查询商品及客户信息返回相应数据
            success: (data) => {
                    var list = eval(data.products);
                    var priceHTML = [];
                    for(var i = 0;i < list.length;i++) {//循环遍历数据
                        var product = list[i];
                        var oldprice = product.unit_price;
                        var newprice = oldprice * data.discount/100;
                        //四舍五入
                        newprice=newprice.toFixed(2);
                        priceHTML.push('<div class="card">' +
                            '<div class="card-main">' +
                            '<div class="shop-name">'+product.name+'</div>' );
                        if (data.discount!==100)
                        {
                            priceHTML.push('<div class="shop-price">'+newprice+'<del class="shop-price">' +oldprice+'</del></div>');
                        }
                        else
                        {
                            priceHTML.push('<div class="shop-price">'+newprice+'</div>');
                        }
                        priceHTML.push('<div class="shop-content">' +
                            '<div class="shop-content-left">'+'商品名称:'+'</div>' +
                            '<div class="shop-content-right">'+product.name+'</div>' +
                            '<div class="shop-content-left">'+'保质期:'+'</div>' +
                            '<div class="shop-content-right">'+product.shelf_life+'</div>' +
                            '<div class="shop-content-left">'+'商品规格:'+'</div>' +
                            '<div class="shop-content-right">'+product.specification+'</div>' +
                            '</div>'+
                            '<div class="shop-content-extra">'+
                            '<div>'+'<span class="icon">check_circle_outline</span>'+1+'</div>'+
                            '</div>'+
                            '<a class="btn btn-brand-accent shop-btn" href="javascript:void(0);" onclick="javascript:buy(' + product.id + ')\">购买</a>'+
                            '</div></div>');
                    }
                    //下面的div与布局有关
                    priceHTML.push('<div class="flex-fix3"></div>' +
                        '<div class="flex-fix4"></div>');
                    $('#shop-flex').html(priceHTML.join(''));
                // }
            },
            error: function() {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    <%--点击购买--%>
    buy = function(id) {
        //弹出
        $("#verify_modal").modal();
        product_id=id;
    };
    //点击确认，生成订单信息
    $("#choose_product").click(function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/chooseproduct",
            dataType: "json",
            data: {
                productId: product_id
            },
            //查询商品及客户信息返回相应数据
            success: (data) => {
                if (data.ret) {
                    // 初始购买数为1
                    document.getElementById('number').value = 1;
                    document.getElementById('name').innerHTML = data.product_name;
                    document.getElementById('unit_price').innerHTML = data.unit_price * data.discount /100;  <%--单价设置为数字--%>
                    document.getElementById('total').innerHTML = document.getElementById('unit_price').innerHTML;
                    <%--设置常量：最低预付款比例--%>
                    ratio = data.ratio;
                    var min_advance_payment = ratio /100 * document.getElementById('unit_price').innerHTML;
                    //四舍五入
                    min_advance_payment=min_advance_payment.toFixed(2);
                    document.getElementById('min_advance_payment').innerHTML = min_advance_payment;

                    $("#order_modal").modal();
                } else {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }
            },
            error: function() {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });
    //关闭订单清空信息
    closeorder = function()
    {
        document.getElementById('number').value="";
        document.getElementById('min_advance_payment').innerHTML=null;
        document.getElementById('total').innerHTML=null;
    };
    //确认,购买。生成订单
    $("#order_verify").click(function () {
        var pay_deposit;
        var pay_full;
        var money = document.getElementById('total').innerHTML;
        var balance = <% out.print(client.getAccountbalance());%>;
        var min_advance_payment = document.getElementById('min_advance_payment').innerHTML;
        if (document.getElementById('pay_full').checked) {
            if (money>balance)
            {
                $("#result").modal();
                document.getElementById('msg').innerHTML = "余额不足，请充值";
                return;
            }
            pay_full = 1;
            pay_deposit = 0;
        }

        if (document.getElementById('pay_deposit').checked) {
            if (min_advance_payment>balance)
            {
                $("#result").modal();
                document.getElementById('msg').innerHTML = "余额不足，请充值";
                return;
            }
            pay_deposit = 1;
            pay_full = 0;
        }
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/buy",
            dataType: "json",
            data: {
                productId:product_id,
                quantity:$("#number").val(),
                pay_deposit:pay_deposit ,
                pay_full:pay_full ,
                min_advance_payment:min_advance_payment,
                money:money,
            },
            success: function(data){
                if (data.ret) {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                    window.setTimeout("location.href='/user/shop'", 1200);
                } else {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }

            },
            error: function() {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        });
        //清空订单信息
        document.getElementById('number').value="";
        document.getElementById('min_advance_payment').innerHTML=null;
        document.getElementById('total').innerHTML=null;
    });
    //自动计算金额，定金
    calculate = function () {
        var number = $("#number").val();//这是数字
        if (number < 1)
        {
            document.getElementById('number').value=1;
            number = 1;
        }
        if (number>1000)
        {
            document.getElementById('number').value=1000;
            $("#result").modal();
            document.getElementById('msg').innerHTML = '最多购买1000件';
            number = 1000;
        }
        var unit_price = document.getElementById('unit_price').innerHTML;//这是数字
        document.getElementById('total').innerHTML = (number * unit_price).toFixed(2);
        var min_advance_payment = ratio /100 *  number * unit_price;
        //四舍五入
        min_advance_payment=min_advance_payment.toFixed(2);
        document.getElementById('min_advance_payment').innerHTML = min_advance_payment;
    };
    //点击付全款
    payfull = function () {
        document.getElementById('pay_full').checked = true;
        document.getElementById('pay_deposit').checked = false;
    };
    //点击付定金
    paydeposit = function () {
        document.getElementById('pay_full').checked = false;
        document.getElementById('pay_deposit').checked = true;
    }
</script>