<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/17
  Time: 16:43
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
    <title>成品出库</title>

    <link href="../../../../theme/css/base.min.css" rel="stylesheet">
    <link href="../../../../theme/css/project.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="../../../../theme/css/user.css">
    <link rel="stylesheet" href="../../../../theme/css/style.css">



    <script type="text/javascript" src="../../../../theme/js/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="../../../../theme/js/jquery.tablesorter.js"></script>

    <script src="https://cdn.jsdelivr.net/gh/davidshimjs/qrcodejs@gh-pages/qrcode.min.js" type="text/javascript"></script>
    <script src="../../../../theme/js/base.min.js" type="text/javascript"></script>
    <script src="../../../../theme/js/project.min.js" type="4text/javascript"></script>

    <script>
        var requirementIdList = [[]];
        //根据所选要求出库
        drawUpPlan = function () {
            var material_idList = [];
            var quantityList = [];
            var nameList = [];
            //把生产需求的创建时间放进去，根据此找到需求，改变状态
            var check = $("table input[type=checkbox]:checked");//在table中找input下类型为checkbox属性为选中状态的数据
            if (check.length === 0)
            {
                $("#result").modal();
                document.getElementById('msg').innerHTML = `请勾选需求`;
            }
            else
            {
                //遍历被选中的数据
                check.each(function () {
                    var row = $(this).parent("th").parent("tr");//获取选中行

                    var material_id = row.find("[name='material_id']").html();//获取name='material_id'的值
                    var quantity = row.find("[name='quantity']").html();
                    var name = row.find("[name='name']").html();
                    var id = row.find("[name='id']").html();
                    var create_time = row.find("[name='create_time']").html();

                    if (material_id !== undefined)
                    {
                        material_idList.push(material_id);
                        //二维数组
                        if(requirementIdList[material_id] === undefined)
                            requirementIdList[material_id]=[];
                        requirementIdList[material_id].push(id);

                        nameList[material_id] = name;
                        if (quantityList[material_id] === undefined)
                            quantityList[material_id] = Number(quantity);
                        else
                        //记得把quantity转换为数字
                            quantityList[material_id] += Number(quantity);

                    }
                });
                console.log(requirementIdList);

                var requirementTable = document.getElementById("requirementTable");
                var planHTML=[];

                $.ajax({
                    type: "POST",
                    url: "/admin/staff/material/staff/classify",
                    dataType: "json",
                    async:false,
                    traditional:true,
                    data: {
                        material_idList : material_idList
                    },
                    success: function(data) {
                        var quantityMap = data.quantityMap;
                        //alert(quantityMap);
                        for(var key in quantityMap){
                            // console.log(key + "==" + quantityMap[key]);
                            planHTML.push('<div name="aplan">\n'+
                                '<p id="id_label">产品ID：<span id="id">'+  key +'</span></p>\n' +
                                '<p id="name_label">产品名称：<span id="name">'+  nameList[key] +'</span></p>\n' +
                                '<p id="repertory_label">产品库存：<span id="repertory">'+  quantityMap[key] +'</span></p>\n' +
                                '<p id="requirement_label">需求量：<span id="requirement">'+ quantityList[key] +'</span></p>\n' +
                                '</div>'+
                                '<br>');
                            if (parseInt(quantityMap[key]) < parseInt(quantityList[key]))
                            {
                                console.log("库存不足");
                                document.getElementById("drawUpPlan").disabled = true;
                            }

                        }
                        $("#plan-inner").html(planHTML.join(''));
                    },
                    error: (jqXHR) => {
                    }
                });

                $("#plan_modal").modal();
            }
        };
    </script>

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

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">成品库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="/admin/staff/product/staff/product-list"><i class="icon icon-lg">account_box</i>&nbsp;查看货物库存</a>
                        </li>
                        <li>
                            <a href="/admin/staff/product/staff/add"><i class="icon icon-lg">add</i>&nbsp;添加产品类型</a>
                        </li>
                        <li>
                            <a href="/admin/staff/product/staff/in"><i class="icon icon-lg">add_box</i>&nbsp;货物入库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/product/staff/out"><i class="icon icon-lg">announcement</i>&nbsp;货物出库</a>
                        </li>
                        <li>
                            <a href="/admin/staff/product/staff/return"><i class="icon icon-lg">announcement</i>&nbsp;处理退货</a>
                        </li>
                        <li>
                            <a href="/admin/staff/product/staff/record"><i class="icon icon-lg">account_balance_wallet</i>&nbsp;查看出入库记录</a>
                        </li>
                    </ul>
            </ul>
        </div>
    </div>
</nav>

<main class="content">
    <div class="content-header ui-content-header">
        <div class="container">
            <h1 class="content-heading">发货</h1>
        </div>
    </div>
    <div class="container">
        <section class="content-inner margin-top-no">
            <div class="ui-card-wrap">
                <div class="row">
                    <div class="col-lg-12 col-sm-12 nodelist">
                        <div class="node-cardgroup" style="display: grid">

                            <div class="nodetitle">
                                <a class="waves-effect waves-button" data-toggle="collapse" href="#ordergroup1" aria-expanded="true" aria-controls="cardgroup1">
                                    <span>待发货订单</span><i class="material-icons">expand_more</i>
                                </a>
                            </div>
                            <div class="card-row collapse in" id="ordergroup1">
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
                    <div aria-hidden="true" class="modal modal-va-middle fade" id="orderinfo" role="dialog" tabindex="-1">
                        <div class="modal-dialog modal-full">
                            <div class="modal-content">
                                <iframe class="iframe-seamless" title="Modal with iFrame" id="infoifram"></iframe>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    </div>
</main>

</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            type: "POST",
            url: "/admin/staff/product/staff/readyDeliverOrderList",
            dataType: "json",
            data: {
            },
            //查询商品及客户信息返回相应数据
            success: function(data) {
                var readyDeliverOrdersList = eval(data.readyDeliverOrderInfo);
                var readyDeliverOrdersHTML = [];
                for(let i = 0;i < readyDeliverOrdersList.length;i++) {
                    let order = readyDeliverOrdersList[i];
                    readyDeliverOrdersHTML.push('<div class="node-card node-flex" onclick="readyDeliverOrder(' + order.order_id + ')">' +
                        '<div class="nodemain">' +
                        '<div class="nodehead node-flex">' +
                        '<div class="nodename">' + order.product_name + '</div>' +
                        '</div>' +
                        '<div class="nodemiddle node-flex">' +
                        '<div class="onlinemember node-flex">' +
                        '<i class="material-icons node-icon">star</i><span>客户类型</span>' +
                        '</div>' +
                        '<div class="nodetype">' + order.client_type + '</div>' +
                        '</div>' +
                        '<div class="nodeinfo node-flex">' +
                        '<div class="nodetraffic node-flex">' +
                        '<i class="material-icons node-icon">person</i><span>客户</span>' +
                        '</div>' +
                        '<div class="nodetype">' + order.client_name + '</div>' +
                        '<div class="nodecheck node-flex">' +
                        '<i class="material-icons node-icon">archive</i><span>数量</span>' +
                        '</div>' +
                        '<div class="nodetype">' + order.quantity + '</div>' +
                        '<div class="nodeband node-flex">' +
                        '<i class="material-icons node-icon">flash_on</i><span>状态</span>' +
                        '</div>' +
                        '<div class="nodetype">' + order.status + '</div>' +
                        '</div></div></div>')

                }
                $('#ordergroup1').html(readyDeliverOrdersHTML.join(''));
            },
            error: function(){
                $("#result").modal();
                document.getElementById('msg').innerHTML = `发生了错误`;
            }
        })
    });

    //点击待发货订单
    function readyDeliverOrder(id) {
        document.getElementById('infoifram').src = '/admin/staff/product/staff/readyDeliverOrderInfo?orderId=' + id;
        $("#orderinfo").modal();
    }
</script>