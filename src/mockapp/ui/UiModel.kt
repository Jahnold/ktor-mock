package codes.arnold.matt.mockapp.ui

data class UiModel(
    val title: String,
    val groups: List<UiGroup>,
    val httpStatuses: List<String>
) {

    data class UiGroup(
        val name: String,
        val items: List<UiGroupItem>
    )

    data class UiGroupItem(
        val type: UiItemType,
        val name: String,
        val values: List<UiItemValues>,
        val defaultStatus: String
    )

    data class UiItemValues(
        val key: String,
        val value: String,
        val recommendedStatus: String,
        val isSelected: Boolean
    )

    enum class UiItemType { ENDPOINT, VARIABLE }
}