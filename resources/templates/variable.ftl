<#-- @ftlvariable name="item" type="codes.arnold.matt.mockapp.ui.UiModel.UiGroupItem" -->

<div class="field">
    <label>${item.name}</label>
    <select class="ui search dropdown">
        <#list item.values as value>
            <option value="${value.key}" <#if value.selected>selected</#if>>${value.value}</option>
        </#list>
    </select>
</div>