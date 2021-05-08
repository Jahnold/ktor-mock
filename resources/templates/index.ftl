<#-- @ftlvariable name="model" type="codes.arnold.matt.mockapp.ui.UiModel" -->

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Mock Server</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
    <link rel="stylesheet" type="text/css" href="/styles/main.css">
</head>
<body>

    <div class="ui fixed inverted menu">
        <div class="ui container">
            <a href="#" class="header item">
                <img class="logo" src="assets/images/logo.png">
                ${model.title}
            </a>
            <a href="#" class="item">Reset All</a>
        </div>
    </div>

    <div class="ui main text container">
        <h1 class="ui header">Semantic UI Fixed Template</h1>
        <#list model.groups as group>
            <#include "group.ftl">
        </#list>
    </div>
</body>
</html>