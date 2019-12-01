<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/22
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="${pageContext.request.contextPath}/theme/css/auth.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">

</head>
<body class="page-brand">
<div class="authpage auth-reg">
    <div class="container">
        <section>
            <div class="auth-main auth-row">
                <div class="auth-top auth-row">
                    <a class="boardtop-left" href="/">
                        <div>首 页</div>
                    </a>
                    <div class="auth-logo">
                        <img src="${pageContext.request.contextPath}/images/authlogo.jpg">
                    </div>
                    <a href="${pageContext.request.contextPath}/user/login" class="boardtop-right">
                        <div>登 录</div>
                    </a>
                </div>
                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group-label auth-row">
                            <label class="floating-label" for="account">邮箱(唯一登录凭证)</label>
                            <input class="form-control maxwidth-auth" id="account" name="account" type="text" maxlength="32">
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group-label auth-row">
                            <label class="floating-label" for="name">姓名</label>
                            <input class="form-control maxwidth-auth" id="name" name="name" type="text">
                        </div>
                    </div>
                </div>

                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group-label auth-row">
                            <label class="floating-label" for="password">密码</label>
                            <input class="form-control maxwidth-auth" id="password" name="password" type="password">
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group form-group-label">
                            <label class="floating-label" for="repassword">重复密码</label>
                            <input class="form-control maxwidth-auth" id="repassword" name="repassword" type="password">
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group form-group-label">
                            <label class="floating-label" for="type">商家类型</label>
                            <select class="form-control maxwidth-auth" id="type">
                                <option value ="零售商">零售商</option>
                                <option value ="批发商">批发商</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group-label auth-row">
                            <label class="floating-label" for="phone">手机号</label>
                            <input class="form-control maxwidth-auth" id="phone" name="phone" type="text" maxlength="32">
                        </div>
                    </div>
                </div>

                <div class="rowtocol">
                    <div class="auth-row">
                        <div class="form-group form-group-label">
                            <label class="floating-label" for="address">地址</label>
                            <input class="form-control maxwidth-auth" id="address" name="address" type="text">
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="rowtocol">
                        <div class="form-group form-group-label">
                            <label class="floating-label" for="email_code">验证码</label>
                            <input class="form-control maxwidth-auth" id="email_code" name="email_code" type="text">
                        </div>
                    </div>
                    <div class="rowtocol">
                        <div class="form-group form-group-label">
                            <button id="email_verify" type="button" class="btn-reg btn btn-block btn-brand-accent waves-attach waves-light">
                                获取验证码
                            </button>
                        </div>
                    </div>
                </div>
                <div class="rowtocol">
                    <div class="btn-auth auth-row">
                        <button id="tos" type="submit" class="btn-reg btn btn-block btn-brand waves-attach waves-light">确认注册
                        </button>
                    </div>
                </div>
                <div class="auth-bottom auth-row auth-reg">
                    <div class="tgauth">
                        <p>注册即代表同意<a href="${pageContext.request.contextPath}/user/tos">服务条款</a>，以及保证所录入信息的真实性，如有不实信息会导致账号被删除。</p>
                    </div>
                </div>
            </div>
        </section>
        <div class="card auth-tg">
            <div class="card-main">
            </div>
        </div>
    </div>
</div>
<div aria-hidden="true" class="modal modal-va-middle fade" id="tos_modal" role="dialog" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-heading">
                <h2 class="modal-title">注册</h2>
            </div>
            <div class="modal-inner">
                <ul>
                    <li>本站会加密存储用户密码，尽量保证数据安全，但并不保证这些信息的绝对安全。</li>
                    <li>产品一经生产非质量问题不予退货。</li>
                </ul> </div>
            <div class="modal-footer">
                <p class="text-right">
                    <button class="btn btn-flat btn-brand-accent waves-attach waves-effect" data-dismiss="modal" type="button" id="cancel">我不同意
                    </button>
                    <button class="btn btn-flat btn-brand-accent waves-attach waves-effect" data-dismiss="modal" id="reg" type="button">我同意
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
                <p class="h5 margin-top-sm text-black-hint" id="msg">账号无效</p>
            </div>
            <div class="modal-footer">
                <p class="text-right">
                    <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok">知道了
                    </button>
                </p>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/clipboard@1.5.16/dist/clipboard.min.js" type="text/javascript"></script>

</body>
</html>

<!-- 注册方法 -->
<script type="text/javascript">
    $(document).ready(function () {
        function register()
        {
            //注册键可用
            document.getElementById("tos").disabled = true;
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/register",
                dataType: "json",
                data: {
                    account: $("#account").val(),
                    name: $("#name").val(),
                    password: $("#password").val(),
                    repassword: $("#repassword").val(),
                    type: $("#type").val(),
                    phone: $("#phone").val(),
                    address: $("#address").val(),
                    emailcode: $("input[name='email_code' ] ").val(),
                },
                success: function(data) {
                    if (data.ret === 1) {
                        $("#result").modal();
                        document.getElementById("msg").innerHTML = data.msg;
                        //1秒后转到location.href
                        window.setTimeout("location.href='/user/login'", 1000);
                    }
                    else //返回了信息，但是有问题
                    {
                        $("#result").modal();
                        document.getElementById("msg").innerHTML = data.msg;
                        // setCookie('code', '', 0);
                        //$("#code").val(getCookie('code'));
                        document.getElementById("tos").disabled = false;
                    }
                },
                error: function(jqXHR) {
                    $("#result").modal();
                    document.getElementById("msg").innerHTML = "错误";
                    document.getElementById("tos").disabled = false;
                }
            });

        }

        //点击服务条款：我同意
        $("#reg").click(function () {
            register();
        });
        //点击确认注册
        $("#tos").click(function () {
            $("#tos_modal").modal();
        });
    });
</script>

<!--发送验证码-->
<script type="text/javascript">
    var wait = 60;
    function time(o) {
        if (wait === 0) {
            o.removeAttr("disabled");
            o.text("获取验证码");
            wait = 60;
        } else {
            o.attr("disabled", "disabled");
            o.text("重新发送(" + wait + ")");
            wait--;
            setTimeout(function () {
                    time(o)
                },
                1000)
        }
    }

    //点击发送验证码
    $(document).ready(function () {
        $("#email_verify").click(function () {
            time($("#email_verify"));
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/verificationCode",
                dataType: "json",
                data: {
                    email: $("input[name='account'] ").val(),
                },
                success: (data) => {
                    $("#result").modal();
                    document.getElementById("msg").innerHTML = data.msg;
                },
                error: (jqXHR) => {
                    $("#result").modal();
                    document.getElementById("msg").innerHTML = "获取验证码错误";
                }
            })
        })
    })
</script>