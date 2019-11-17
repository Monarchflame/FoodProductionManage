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
    <title>原材料出库</title>

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
                                '<p id="id_label">原材料ID：<span id="id">'+  key +'</span></p>\n' +
                                '<p id="name_label">原材料名称：<span id="name">'+  nameList[key] +'</span></p>\n' +
                                '<p id="repertory_label">原材料库存：<span id="repertory">'+  quantityMap[key] +'</span></p>\n' +
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

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="/admin/staff/material/staff/material-list"><i class="icon icon-lg">account_box</i>&nbsp;查看原材料库存</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/add"><i class="icon icon-lg">add</i>&nbsp;添加原材料类型</a>
                        </li>
                        <li>
                            <a href="/admin/staff/material/staff/buy"><i class="icon icon-lg">attach_money</i>&nbsp;购入原材料</a>
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
            <h1 class="content-heading">原材料需求</h1>
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
                                        <label class="floating-label" for="materialID">原材料ID</label>
                                        <input class="form-control maxwidth-edit"  autocomplete="off"  name="materialID" id="materialID">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="col-lg-12 col-md-12">
                        <div class="card margin-bottom-no">
                            <div class="card-main">
                                <div class="card-inner">
                                    <div class="card-table">
                                        <div class="table-responsive table-user">
                                            <table class="table table-fixed tablesorter" id="requirementTable">

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-12 col-md-12">
                        <div class="card margin-bottom-no">
                            <div class="card-main">
                                <div class="card-inner">
                                    <div class="cardbtn-edit">
                                        <div class="card-heading">根据所选需求出库</div>
                                        <button class="btn btn-flat" id="drawUpPlan" onclick="javascript:drawUpPlan()"><span class="icon">check</span>&nbsp;
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div aria-hidden="true" class="modal modal-va-middle fade" id="plan_modal" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-heading">
                                <a class="modal-close" data-dismiss="modal">×</a>
                                <h2 class="modal-title">确认</h2>
                            </div>
                            <div id="plan-inner" class="modal-inner">

                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="plan_verify" type="button">确定
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
    $(document).ready(function () {
        function selectRequirementInfo(materialID)
        {
            $.ajax({
                type: "POST",
                url: "/admin/staff/material/staff/requirementInfoList",
                dataType: "json",
                async:false,
                data: {
                    materialID:materialID
                },
                success: function(data){
                    var requirementInfoList = eval(data.requirementInfoList);
                    var requirementTableHTML=[];
                    requirementTableHTML.push(
                        '<thead>\n' +
                        '<tr>\n' +
                        '<td style=" background: #f0faff;"><input type="checkbox" id="checkall" lay-skin="primary" onclick="javascript:selectAll();"></td>\n' +
                        '<th>需求ID</th>\n' +
                        '<th>原材料ID</th>\n' +
                        '<th>原材料名称</th>\n' +
                        '<th>数量</th>\n' +
                        '<th>创建时间</th>\n' +
                        '</tr>\n' +
                        '</thead>' +
                        '<tbody>\n');

                    for (let i=0; i<requirementInfoList.length; i++)
                    {
                        var requirementInfo = requirementInfoList[i];
                        requirementTableHTML.push(
                            '<tr>\n' +
                            '<th><input type="checkbox" name="check" lay-skin="primary"></th>\n' +
                            '<th name="id">'+ requirementInfo.id +'</th>\n' +
                            '<th name="material_id">'+ requirementInfo.material_id +'</th>\n' +
                            '<th name="name">'+ requirementInfo.material_name +'</th>\n' +
                            '<th name="quantity">'+ requirementInfo.quantity +'</th>\n' +
                            '<th name="create_time">'+ (new Date(requirementInfo.create_time)).toLocaleString() +'</th>\n' +
                            '</tr>');
                    }
                    requirementTableHTML.push('</tbody>');
                    $('#requirementTable').html(requirementTableHTML.join(''));
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }
            });
        }

        selectRequirementInfo("");

        $("#requirementTable").tablesorter();

        selectAll = function () {
            if (document.getElementById('checkall').checked)
            {
                let elementsByName = document.getElementsByName('check');
                for(let i=0; i<elementsByName.length; i++)
                {
                    let element = elementsByName[i];
                    element.checked = true;
                }
            }
            else
            {
                let elementsByName = document.getElementsByName('check');
                for(let i=0; i<elementsByName.length; i++)
                {
                    let element = elementsByName[i];
                    element.checked = false;
                }
            }

        };
        
        //出库
        $("#plan_verify").click(function () {
            var plans = document.getElementsByName('aplan');
            for(let i=0; i<plans.length; i++)
            {
                let plan = plans[i];
                var id = plan.children[0].children[0].innerHTML;
                var name = plan.children[1].children[0].innerHTML;
                var repertory = plan.children[2].children[0].innerHTML;
                var requirement = plan.children[3].children[0].innerHTML;
                if (parseInt(repertory) < parseInt(requirement))
                {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = name+'库存不足';
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/admin/staff/material/staff/inventoryOut",
                    dataType: "json",
                    async:false,
                    traditional:true,
                    data: {
                        material_id:id,
                        requirement:requirement,
                        requirementIdList:requirementIdList[id]
                    },
                    success: function(data){
                        if (data.ret === 1)
                        {
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = `成功出库`;
                        }
                        else
                        {
                            $("#result").modal();
                            document.getElementById('msg').innerHTML = `发生了错误`;
                        }
                    },
                    error: (jqXHR) => {
                        $("#result").modal();
                        document.getElementById('msg').innerHTML = `发生了错误`;
                    }
                });
            }
        });

        $("#findRequirement").click(function () {
            let materialID = $("#materialID").val();
            selectRequirementInfo(materialID);
        })
    });

</script>
