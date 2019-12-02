<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/11/11
  Time: 14:52
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
    <title>原材料库存</title>

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

                    <a class="waves-attach" data-toggle="collapse" href="#ui_menu_material">原材料库</a>
                    <ul class="menu-collapse collapse in" id="ui_menu_material">
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/leader/material/leader/material-list"><i class="icon icon-lg">account_box</i>&nbsp;查看原材料库存</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/leader/material/leader/material-record"><i class="icon icon-lg">account_box</i>&nbsp;查看原材料记录</a></li>
                        <li>
                            <a href="${pageContext.request.contextPath}/admin/leader/material/leader/staff-list"><i class="icon icon-lg">account_box</i>&nbsp;查看员工</a>
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
                                        <button class="btn btn-flat" id="findInventory"><span class="icon">check</span>&nbsp;
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
                                            <table class="table table-fixed tablesorter" id="materialTable">

                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div aria-hidden="true" class="modal modal-va-middle fade" id="material_modal" role="dialog" tabindex="-1">
                    <div class="modal-dialog modal-xs">
                        <div class="modal-content">
                            <div class="modal-heading">
                                <a class="modal-close" data-dismiss="modal" onclick="closematerial()">×</a>
                                <h2 class="modal-title">生产计划确认</h2>
                            </div>
                            <div id="material-inner" class="modal-inner">

                            </div>
                            <div class="modal-footer">
                                <p class="text-right">
                                    <button class="btn btn-flat btn-brand waves-attach" data-dismiss="modal" id="material_verify" type="button">确定
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
        function selectMaterialInfo(materialID)
        {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/admin/leader/material/leader/materialInfoList",
                dataType: "json",
                async:false,
                data: {
                    materialID:materialID
                },
                success: function(data){
                    var materialInfoList = eval(data.materialInfoList);
                    var materialTableHTML=[];
                    materialTableHTML.push(
                        '<thead>\n' +
                        '<tr>\n' +
                        '<th>库存ID</th>\n' +
                        '<th>原材料ID</th>\n' +
                        '<th>名称</th>\n' +
                        '<th>库存</th>\n' +
                        '<th>入库时间</th>\n' +
                        '<th>过期时间</th>\n' +
                        '</tr>\n' +
                        '</thead>' +
                        '<tbody>\n');

                    for (let i=0; i<materialInfoList.length; i++)
                    {
                        var materialInfo = materialInfoList[i];
                        materialTableHTML.push(
                            '<th name="inventory_id">'+ materialInfo.inventory_id +'</th>\n' +
                            '<th name="material_id">'+ materialInfo.material_id +'</th>\n' +
                            '<th name="product_id">'+ materialInfo.name +'</th>\n' +
                            '<th name="name">'+ materialInfo.quantity +'</th>\n' +
                            '<th name="quantity">'+ (new Date(materialInfo.create_time)).toLocaleString() +'</th>\n' +
                            '<th name="create_time">'+ (new Date(materialInfo.expiration_time)).toLocaleString() +'</th>\n' +
                            '</tr>');
                    }
                    materialTableHTML.push('</tbody>');
                    $('#materialTable').html(materialTableHTML.join(''));
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById('msg').innerHTML = `发生了错误`;
                }

            });
        }
        selectMaterialInfo("");
        $("#materialTable").tablesorter();

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

        edit1 = function (inventory_id) {
            document.getElementById('infoifram').src = "/admin/leader/material/leader/materialInfo?inventory_id="+inventory_id;
            $("#materialinfo").modal();
        };

        $("#findInventory").click(function () {
            let materialID = $("#materialID").val();
            selectMaterialInfo(materialID);
        })
    })

</script>