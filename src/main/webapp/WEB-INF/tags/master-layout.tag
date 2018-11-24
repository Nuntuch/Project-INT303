<%@tag description="Master layout template" pageEncoding="UTF-8"%>
<%@attribute name="titlePage" required="true"  description="title of page"%>
<%@attribute name="keywordPage" required="false"  description="keyword of page eg. [computer, smartphone, tablet]"%>
<%@attribute name="descriptionPage" required="false"  description="description of page"%>
<%@attribute name="header" fragment="true"  description="header in head tag"%>
<%@attribute name="footer" fragment="true" description="footer atfer body"%>
<%@attribute name="script" fragment="true" description="internal script after footer"%>

<!DOCTYPE html>
<html>
    <head>
        <title>${titlePage} Moommim</title>
        <meta charset="utf-8"/>
        <meta name="theme-color" content="#FFFFFF" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1" />
        <meta name="description" content="${descriptionPage}" />
        <meta name="keywords" content="${keywordPage}" />
        <link rel="shortcut icon" href="assets/images/favicon.ico" />
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css" />
        <style>
            <%@ include file="../../assets/css/styles.css"%>
        </style>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Pridi:400,500&amp;subset=latin-ext,thai" />
        <jsp:invoke fragment="header" />
        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js" ></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js" ></script>
        <![endif]-->
    </head>
    <body>
        <div class="ui container">
            <div class="ui inverted menu">
                <div class="header item">Moommim</div>
                <div class="right menu">
                    <a class="item">สมัครสมาชิก</a>
                    <a class="item">เข้าสู่ระบบ</a>
                </div>
            </div>
            <div class="ui huge stackable menu">
                <div class="ui dropdown item" tabindex="0">
                    หมวดมู่
                    <i class="dropdown icon"></i>
                    <div class="menu transition hidden" tabindex="-1">
                        <a class="item">Notebook</a>
                        <a class="item">Smartphone</a>
                        <a class="item">Tablet</a>
                        <a class="item">Camera</a>
                    </div>
                </div>
                <div class="item">
                    <div class="ui action input left icon">
                        <i class="search icon"></i>
                        <input type="text" placeholder="ต้องการหาสินค้า...">
                        <button class="ui button">ค้นหา</button>
                    </div>
                </div>
                <div class="right menu">
                    <div class="item">
                        <div class="ui labeled button" tabindex="0">
                            <div class="ui primary button">
                                <i class="shopping cart icon"></i>
                            </div>
                            <a class="ui basic primary left pointing label">
                                2
                            </a>
                        </div> 
                    </div>
                </div>
            </div>
            <jsp:doBody />
        </div>
        <jsp:invoke fragment="footer"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js" ></script>
        <script>
            <%@ include file="../../assets/js/app.js"%>
            <jsp:invoke fragment="script" />
            var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
            (function () {
                var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                s1.async = true;
                s1.src = 'https://embed.tawk.to/5bf9732240105007f3796cbd/default';
                s1.charset = 'UTF-8';
                s1.setAttribute('crossorigin', '*');
                s0.parentNode.insertBefore(s1, s0);
            })();
        </script>
    </body>
</html>
