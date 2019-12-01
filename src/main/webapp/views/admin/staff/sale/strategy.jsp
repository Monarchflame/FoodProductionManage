<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/18
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <meta name="theme-color" content="#4285f4">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/labellogo.jpg" type="image/x-icon">
    <title>潮汐</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/css/user.css">

    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1" type="7f08d2d5c89f636849dfe7dd-text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="7f08d2d5c89f636849dfe7dd-text/javascript"></script>

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
                    <a class="waves-attach" href="/admin/sale/staff"><span class="icon icon-lg margin-right">account_box</span>员工中心</a>
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
                            <a href="/admin/staff/sale/staff"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;员工中心</a>
                        </li>
                        <li>
                            <a href="/admin/account"><i class="icon icon-lg">account_box</i>&nbsp;账户信息</a>
                        </li>
                    </ul>

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_sale">销售部</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_sale">
                        <li>
                            <a href="/admin/staff/sale/staff/client-list"><i class="icon icon-lg">account_box</i>&nbsp;管理客户信息</a>
                        </li>
                        <li>
                            <a href=""><i class="icon icon-lg">announcement</i>&nbsp;管理销售策略</a>
                        </li>
                        <li>
                            <a href="/admin/staff/sale/staff/order"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;接受订货</a>
                        </li>
                        <li>
                            <a href="/admin/staff/sale/staff/returnOrder"><i class="icon icon-lg">sync_problem</i>&nbsp;处理退货</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">管理销售策略</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="card-inner">
                                    <dl class="dl-horizontal">
                                        <dt>比如50%就直接输入50</dt>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--这两个放在class为row的div中才能横着排-->
                <div class="col-lg-6 col-md-6">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="cardbtn-edit">
                                    <p class="card-heading">客户折扣修改</p>
                                    <p>修改后点击右侧对号确定。</p>
                                    <button class="btn btn-flat" id="discount-update"><span class="icon">check</span>&nbsp;
                                    </button>
                                </div>
                                <div class="card-table">
                                    <div class="table-responsive table-user">
                                        <table id="discount-table" class="table table-fixed">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-6 col-md-6">
                    <div class="card margin-bottom-no">
                        <div class="card-main">
                            <div class="card-inner">
                                <div class="cardbtn-edit">
                                    <p class="card-heading">预付款比例修改</p>
                                    <p>修改后点击右侧对号确定。</p>
                                    <button class="btn btn-flat" id="ratio-update"><span class="icon">check</span>&nbsp;
                                    </button>
                                </div>
                                <div class="card-table">
                                    <div class="table-responsive table-user">
                                        <table id="ratio-table" class="table table-fixed">

                                        </table>
                                    </div>
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
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function ()
    {
        //填入销售策略和预付款比例
        {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/staff/sale/staff/getCredit",
                dataType: "json",
                data: {
                },
                success: function(data){
                    var list = eval(data.credits);
                    var discountHTML = [];
                    discountHTML.push('<tbody> <tr><th>'+'信誉等级'+'</th><th>'+'当前折扣'+'</th><th>'+'新折扣'+'</th></tr><tr>');
                    var ratioHTML = [];
                    ratioHTML.push('<tbody> <tr><th>'+'信誉等级'+'</th><th>'+'当前折扣'+'</th><th>'+'新折扣'+'</th></tr><tr>');
                    for(var i = 0;i < list.length;i++) {//循环遍历数据
                        var credit = list[i];
                        discountHTML.push('<td>'+credit.grade+'</td>'+
                            '<td>'+credit.discount+'</td>'+
                            '<td><div class="form-group form-group-label">' +
                            '<input class="form-control maxwidth-edit" name="discount" value="'+credit.discount+'" type="text"> </div> </td> </tr> </tbody>'
                        );
                        ratioHTML.push('<td>'+credit.grade+'</td>'+
                            '<td>'+credit.ratio+'</td>'+
                            '<td><div class="form-group form-group-label">' +
                            '<input class="form-control maxwidth-edit" name="ratio" value="'+credit.ratio+'" type="text"> </div> </td> </tr> </tbody>')
                    }
                    $('#discount-table').html(discountHTML.join(''));
                    $('#ratio-table').html(ratioHTML.join(''));
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            })
        }
        $("#discount-update").click(function () {
            var discountList = [];
            for (var i = 0; i < 5; i++) {
                discountList.push($("input[name='discount']")[i].value);
            }
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/staff/sale/staff/updateDiscount",
                dataType: "json",
                traditional: true,
                data: {
                    discountList:discountList,
                },
                success: function(data){
                    if (data.ret) {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = data.msg;
                    } else {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = data.msg;
                    }
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            });
        });
        $("#ratio-update").click(function () {
            var ratioList = [];
            for (var i = 0; i < 5; i++) {
                ratioList.push($("input[name='ratio']")[i].value)
            }
            alert(typeof ratioList);
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/staff/sale/staff/updateRatio",
                dataType: "json",
                //这个参数非常重要，不然你后台取出来的值第一个和最后一个是错的，会多'['和 ']' 这两个东西。
                traditional: true,
                data: {
                    ratioList:ratioList,
                },
                success: function(data){
                    if (data.ret) {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = data.msg;
                    } else {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = data.msg;
                    }
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            });
        })
    })
</script>