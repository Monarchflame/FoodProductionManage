<%@ page import="cn.qxt.pojo.Client" %>
<%@ page import="cn.qxt.pojo.Order" %>
<%@ page import="cn.qxt.pojo.MyOrder" %><%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/26
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% Client client=(Client) request.getSession().getAttribute("LOGIN_USER"); %>
<% MyOrder myOrder=(MyOrder) request.getSession().getAttribute("myOrder"); %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no, width=device-width" name="viewport">
    <meta name="theme-color" content="#4caf50">
    <meta http-equiv="X-Frame-Options" content="sameorigin">
    <title>订单信息</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <%--    没有他不能用$符号--%>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1" type="text/javascript"></script>

    <style>
        .pagination {
            display: inline-block;
            padding-left: 0;
            margin: 20px 0;
            border-radius: 4px
        }

        .pagination > li {
            display: inline
        }

        .pagination > li > a, .pagination > li > span {
            position: relative;
            float: left;
            padding: 6px 12px;
            margin-left: -1px;
            line-height: 1.42857143;
            color: #337ab7;
            text-decoration: none;
            background-color: #fff;
            border: 1px solid #ddd
        }

        .pagination > li:first-child > a, .pagination > li:first-child > span {
            margin-left: 0;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px
        }

        .pagination > li:last-child > a, .pagination > li:last-child > span {
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px
        }

        .pagination > li > a:focus, .pagination > li > a:hover, .pagination > li > span:focus, .pagination > li > span:hover {
            color: #23527c;
            background-color: #eee;
            border-color: #ddd
        }

        .pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
            z-index: 2;
            color: #fff;
            cursor: default;
            background-color: #337ab7;
            border-color: #337ab7
        }

        .pagination > .disabled > a, .pagination > .disabled > a:focus, .pagination > .disabled > a:hover, .pagination > .disabled > span, .pagination > .disabled > span:focus, .pagination > .disabled > span:hover {
            color: #777;
            cursor: not-allowed;
            background-color: #fff;
            border-color: #ddd
        }

        .pagination-lg > li > a, .pagination-lg > li > span {
            padding: 10px 16px;
            font-size: 18px
        }

        .pagination-lg > li:first-child > a, .pagination-lg > li:first-child > span {
            border-top-left-radius: 6px;
            border-bottom-left-radius: 6px
        }

        .pagination-lg > li:last-child > a, .pagination-lg > li:last-child > span {
            border-top-right-radius: 6px;
            border-bottom-right-radius: 6px
        }

        .pagination-sm > li > a, .pagination-sm > li > span {
            padding: 5px 10px;
            font-size: 12px
        }

        .pagination-sm > li:first-child > a, .pagination-sm > li:first-child > span {
            border-top-left-radius: 3px;
            border-bottom-left-radius: 3px
        }

        .pagination-sm > li:last-child > a, .pagination-sm > li:last-child > span {
            border-top-right-radius: 3px;
            border-bottom-right-radius: 3px
        }

        .pager {
            padding-left: 0;
            margin: 20px 0;
            text-align: center;
            list-style: none
        }

        .pager li {
            display: inline
        }

        .pager li > a, .pager li > span {
            display: inline-block;
            padding: 5px 14px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 15px
        }

        .pager li > a:focus, .pager li > a:hover {
            text-decoration: none;
            background-color: #eee
        }

        .pager .next > a, .pager .next > span {
            float: right
        }

        .pager .previous > a, .pager .previous > span {
            float: left
        }

        .pager .disabled > a, .pager .disabled > a:focus, .pager .disabled > a:hover, .pager .disabled > span {
            color: #777;
            cursor: not-allowed;
            background-color: #fff
        }


        .pagination > li > a,
        .pagination > li > span {
            border: 1px solid white;
        }

        .pagination > li.active > a {
            background: #f50057;
            color: #fff;
        }

        .pagination > li > a {
            background: white;
            color: #000;
        }


        .pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
            color: #fff;
            background-color: #000;
            border-color: #000;
        }

        .pagination > .active > span {
            background-color: #f50057;
            color: #fff;
            border-color: #fff;
        }


        .pagination > .disabled > span {
            border-color: #fff;
        }


        pre {
            white-space: pre-wrap;
            word-wrap: break-word;
        }

        .page-green .ui-content-header {
            background-image: url(${pageContext.request.contextPath}/images/amber.jpg);
        }


        .content-header-green, .page-green .content-header {
            background-color: #459f47;
            color: #fff;
        }
    </style>
</head>
<body class="page-green">
<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">订单信息</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner">
            <div class="ui-card-wrap">
                <div class="row">
                    <div class="col-lg-12 col-sm-12">
                        <div class="card">
                            <div class="card-main">
                                <div class="card-inner">
                                    <div class="card-table">
                                        <div class="table-responsive table-user">
                                            <table class="table table-fixed">
                                                <tbody>
                                                    <tr>
                                                        <td>订单编号</td>
                                                        <td><% out.print(myOrder.getOrderId());%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>订单状态</td>
                                                        <td><% out.print(myOrder.getStatus());%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>产品名称</td>
                                                        <td><% out.print(myOrder.getProductName());%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>购买数量</td>
                                                        <td><% out.print(myOrder.getQuantity());%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>交易金额</td>
                                                        <td>
                                                            <% out.print(myOrder.getMoney());%>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>地址</td>
                                                        <td>
                                                            <% out.print(client.getAddress());%>
                                                        </td>
                                                    </tr>

                                                </tbody></table>
                                        </div>
                                    </div>
                                    <div id="button-div"></div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div aria-hidden="true" class="modal modal-va-middle fade" id="verify_modal" role="dialog" tabindex="-1">
                <div class="modal-dialog modal-xs">
                    <div class="modal-content">
                        <div class="modal-heading">
                            <a class="modal-close" data-dismiss="modal">×</a>
                            <h2 class="modal-title" id="h2"> </h2>
                        </div>
                        <div class="modal-footer">
                            <p class="text-right">
                                <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="choose_function" type="button" onclick="">确定
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
                                <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok" onclick="parent.location.reload()">知道了
                                </button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</main>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/clipboard@1.5.16/dist/clipboard.min.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
    var goodsReturnOrdernumber;
    var goodsReturnOrderstatus;
    $(document).ready(function () {
        //查找是否有对应退货单
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/findGoodsReturnOrder",
            dataType: "json",
            data: {
                orderId:'<% out.print(myOrder.getOrderId());%>',
            },
            success: function(data) {
                var goodsReturnOrderList = eval(data.goodsReturnOrderList);
                if (goodsReturnOrderList!=null && goodsReturnOrderList.length > 0)
                {
                    goodsReturnOrdernumber = goodsReturnOrderList.length;
                    goodsReturnOrderstatus = goodsReturnOrderList[0].status;
                }
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        });

        //注意是字符串类型
        var status = '<% out.print(myOrder.getStatus());%>';
        orderId = '<% out.print(myOrder.getOrderId());%>';
        if (status === '已付定金' || status === '已付全款')
        {
            document.getElementById('button-div').innerHTML='<button class="btn btn-subscription col-xx-12 col-sm-3 col-lg-2" type="button" id="button1" onclick="cancelorder();"> '+'取消订单'+'  </button>';
            document.getElementById('h2').innerText='您确认取消订单吗？';
            document.getElementById('choose_function').onclick= verify_cancel;
        }
        else if (status === '已完成' || status === '待付尾款')
        {
            document.getElementById('button-div').innerHTML='<button class="btn btn-subscription col-xx-12 col-sm-3 col-lg-2" type="button" id="button1" onclick="returngoods();"> '+'申请退货'+'  </button>';
            document.getElementById('h2').innerText='您确认申请退货吗？';
            document.getElementById('choose_function').onclick= verify_return;
        }
    });
    //点击取消订单
    cancelorder = function () {
        if (goodsReturnOrdernumber === 1)
        {
            $("#result").modal();
            document.getElementById('msg').innerHTML = `不可重复申请`;
        }
        $("#verify_modal").modal();
    };
    //确认取消订单
    verify_cancel = function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/cancelorder",
            dataType: "json",
            data: {
                orderId:orderId,
            },
            success: function(data) {
                if (data.ret===1)
                {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }
                else
                {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    };
    //点击申请退货
    returngoods = function () {
        $("#verify_modal").modal();
    };
    //确认退货
    verify_return = function () {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/returngoods",
            dataType: "json",
            data: {
                orderId:orderId,
            },
            success: function(data) {
                if (data.ret===1)
                {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }
                else
                {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = data.msg;
                }
            },
            error: (jqXHR) => {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    }
</script>
