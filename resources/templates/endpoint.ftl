<#-- @ftlvariable name="model" type="codes.arnold.matt.mockapp.ui.UiModel" -->
<#-- @ftlvariable name="item" type="codes.arnold.matt.mockapp.ui.UiModel.UiGroupItem" -->

<div class="two fields">
    <div class="thirteen wide field">
        <label>${item.name}</label>
        <select class="ui search dropdown">
            <#list item.values as value>
                <option value="${value.key}" <#if value.selected>selected</#if>>${value.value}</option>
            </#list>
        </select>
    </div>

    <div class="three wide field">
        <label>Status</label>
        <select class="ui search dropdown">
            <#list model.httpStatuses as status>
                <option value="${status}" <#if status == item.defaultStatus>selected</#if>>${status}</option>
            </#list>
        </select>
    </div>
</div>