<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<%@ page isELIgnored="false"%>--%>
<!doctype html>
<!--
Material Design Lite
Copyright 2015 Google Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License
-->
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Login</title>

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="images/favicon.png">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="estilo.css">
    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 40px;
            z-index: 900;
        }
    </style>
</head>

<body>
<div class="mdl-layout mdl-js-layout mdl-color--grey-100 mdl-layout--fixed-header">
    <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">Gerenciador Qualitat</span>
            <div class="mdl-layout-spacer"></div>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="mdl-grid demo-content">

            <div class="mdl-card mdl-shadow--6dp mdl-cell mdl-cell--8-col">
                <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h2 class="mdl-card__title-text">Descrição</h2>
                </div>
                <div class="mdl-grid demo-content">

                    <div class="mdl-card__supporting-text mdl-cell mdl-cell--6-col">
                        <img src="images/logo.png" alt="Logo da empresa Qualitat" class="logoLogin">
                    </div>

                    <div class="mdl-card__supporting-text mdl-cell mdl-cell--6-col">
                        <p>Aqui vai uma breve descrição sobre o sistema, imperdiet ut vivamus a, nam lectus at nunc. Quam euismod sem, semper ut potenti pellentesque quisque.</p>
                    </div>
                </div>

            </div>


            <div class="mdl-card mdl-shadow--6dp mdl-cell mdl-cell--4-col">
                <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h2 class="mdl-card__title-text">Login</h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <form action="#">
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="text" id="username" />
                            <label class="mdl-textfield__label" for="username">Usuário</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="password" id="userpass" />
                            <label class="mdl-textfield__label" for="userpass">Senha</label>
                        </div>
                    </form>
                </div>
                <div class="mdl-card__actions mdl-card--border">
                    <a href="index.html"><button class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Entrar</button></a>
                    <a href="#">Esqueci minha senha</a>
                </div>
            </div>
        </div>
    </main>
</div>
<!-- MDL Mini Footer Container-->
<footer class="mdl-mini-footer">
    <div class="mdl-layout-spacer"></div>
    <!-- MDL Footer left Section-->
    <div class="mdl-mini-footer__left-section">
        <div class="mdl-logo">Gerenciador Qualitat</div>
        <!-- MDL Footer link-list -->
        <ul class="mdl-mini-footer__link-list">
            <li>Gerenciador Qualitat - Versão 1.0</li>
            <li>Copyright © 2017 Vinícios Tomazetti. Todos os direitos reservados.</li>
        </ul>
    </div>
</footer>
</body>

</html>