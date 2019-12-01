<%--
  Created by IntelliJ IDEA.
  User: 10703
  Date: 2019/9/22
  Time: 15:16
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
    <title>登录</title>

    <link href="${pageContext.request.contextPath}/theme/css/base.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/project.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/theme/css/auth.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Material+Icons" rel="stylesheet">
</head>
<body class="page-brand">
<div class="authpage">
    <div class="container">
        <section>
            <div class="auth-main auth-row auth-col-one">
                <div class="auth-top auth-row">
                    <a class="boardtop-left" href="/">
                        <div>首 页</div>
                    </a>
                    <div class="auth-logo">
                        <img src="${pageContext.request.contextPath}/images/authlogo.jpg">
                    </div>
                    <a href="${pageContext.request.contextPath}/user/register" class="boardtop-right">
                        <div>注 册</div>
                    </a>
                </div>
                <div class="auth-row">
                    <div class="form-group-label auth-row row-login">
                        <label class="floating-label" for="email">邮箱</label>
                        <input class="form-control maxwidth-auth" id="email" type="text" name="email">
                    </div>
                </div>
                <div class="auth-row">
                    <div class="form-group-label auth-row row-login">
                        <label class="floating-label" for="password">密码</label>
                        <input class="form-control maxwidth-auth" id="password" type="password" name="password">
                    </div>
                </div>
                <div class="btn-auth auth-row">
                    <button id="login" type="submit" class="btn btn-block btn-brand waves-attach waves-light">
                        确认登录
                    </button>
                </div>
                <div class="auth-help auth-row">
                    <div class="auth-help-table auth-row">
                        <div class="checkbox checkbox-adv">
                            <label for="remember_me">
                                <input class="access-hide" value="week" id="remember_me" name="remember_me" type="checkbox">记住我
                                <span class="checkbox-circle"></span>
                                <span class="checkbox-circle-check"></span>
                                <span class="checkbox-circle-icon icon">done</span>
                            </label>
                        </div>
                        <a href="${pageContext.request.contextPath}/user/reset">忘记密码？</a>
                    </div>
                </div>
            </div>
        </section>
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
                    <button class="btn btn-flat btn-brand-accent waves-attach" data-dismiss="modal" type="button" id="result_ok">知道了
                    </button>
                </p>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/jquery@2.2.1" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/base.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/theme/js/project.min.js" type="text/javascript"></script>
<script src="https://ajax.cloudflare.com/cdn-cgi/scripts/95c75768/cloudflare-static/rocket-loader.min.js" data-cf-settings="439ccd34d75bad8383a7ab79-|49" defer=""></script>
</body>
</html>
<script>
    $(document).ready(function () {

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/user/getCookie",
            dataType: "json",
            data: {
            },
            success: function (data) {
                if (data != null)
                {
                    document.getElementById("email").value = data.email;
                    document.getElementById("password").value = data.password;
                }
            },
            error: function (jqXHR) {
            }
        });

        $("#login").click(function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/login",
                dataType: "json",
                data: {
                    email: $("#email").val(),
                    password: $("#password").val(),
                    remember_me: $("#remember_me:checked").val()
                },
                success: function (data) {
                    if (data.ret === 1)//成功
                    {
                        $("#result").modal();
                        document.getElementById("msg").innerHTML = data.msg;
                        window.location.href = "${pageContext.request.contextPath}/user";
                    } else {
                        $("#result").modal();
                        document.getElementById("msg").innerHTML = data.msg;
                    }
                },
                error: function (jqXHR) {
                    document.getElementById("msg").innerHTML = `发生错误`;
                }
            });
        })
    })
</script>