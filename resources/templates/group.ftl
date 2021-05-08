<#-- @ftlvariable name="group" type="codes.arnold.matt.mockapp.ui.UiModel.UiGroup" -->

<div class="ui segment">
    <h2>${group.name}</h2>
    <div class="ui form">
        <#list group.items as item>
            <#if item.type.name() == "ENDPOINT">
                <#include "endpoint.ftl">
            </#if>
            <#if item.type.name() == "VARIABLE">
                <#include "variable.ftl">
            </#if>
        </#list>
    </div>
</div>