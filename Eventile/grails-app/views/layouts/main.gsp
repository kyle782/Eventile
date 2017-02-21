<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

    <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Eventile</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <g:if test="${session.user==null}" >
                        <li class="active"><a href="/">Welcome Page</a></li>
                    </g:if>
                    <li><a href="/search/create">Search</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">

                    <!-- Change navbar buttons depending if user logged in or not -->
                    <g:if test="${session.user==null}" >
                        <li><a href="/user/login">Login</a></li>
                        <li><a href="/user/create">Register</a></li>
                    </g:if>
                    <g:if test="${session.user!=null}" >
                        <li><a href="/user#logout">Log Out</a></li>
                    </g:if>

                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>


    <!-- <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/#">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> Grails
                </a>
            </div>
            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                </ul>
            </div>
        </div>
    </div> -->

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>


    <asset:javascript src="application.js"/>

</body>
</html>
