package codes.arnold.matt.mockapp.ui

import codes.arnold.matt.mockapp.MockGroup
import codes.arnold.matt.mockapp.endpoint.EndpointValue
import codes.arnold.matt.mockapp.endpoint.MockEndpoint
import codes.arnold.matt.mockapp.variable.MockVariable
import codes.arnold.matt.mockapp.variable.VariableValue

class UiModelMapper {

    fun map(title: String, groups: Set<MockGroup>): UiModel = UiModel(
        title = title,
        groups = groups.map { it.toUiGroup() },
        httpStatuses = getHttpStatuses()
    )

    private fun MockGroup.toUiGroup(): UiModel.UiGroup = UiModel.UiGroup(
        name = name,
        items = endpoints.map { it.toUiItem() } + variables.map { it.toUiItem() }
    )

    private fun MockEndpoint.toUiItem(): UiModel.UiGroupItem = UiModel.UiGroupItem(
        type = UiModel.UiItemType.ENDPOINT,
        name = name,
        values = values.map { it.toUiItemValue(selectedResponse) },
        defaultStatus = selectedResponse.responseCode.toString()
    )

    private fun EndpointValue.toUiItemValue(selectedValue: EndpointValue): UiModel.UiItemValues = UiModel.UiItemValues(
        key = key,
        value = json,
        isSelected = key == selectedValue.key,
        recommendedStatus = responseCode.toString()
    )

    private fun MockVariable<*>.toUiItem(): UiModel.UiGroupItem = UiModel.UiGroupItem(
        type = UiModel.UiItemType.VARIABLE,
        name = name,
        values = values.map { it.toUiItemValue(selectedValue) },
        defaultStatus = ""
    )

    private fun VariableValue<*>.toUiItemValue(selectedValue: VariableValue<*>): UiModel.UiItemValues = UiModel.UiItemValues(
        key = key,
        value = key,
        isSelected = key == selectedValue.key,
        recommendedStatus = ""
    )

    private fun getHttpStatuses(): List<String> = listOf(
        "200", "201", "204", "300", "301", "302", "400", "401", "403", "404", "500"
    )
}