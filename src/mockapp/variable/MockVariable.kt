package codes.arnold.matt.mockapp.variable

abstract class MockVariable<T> {

    abstract val name: String
    abstract val values: List<VariableValue<T>>

    lateinit var selectedValue: VariableValue<T>

    fun setDefault() {
        selectedValue = values.find { it.isDefault } ?: values.first()
    }
}

data class VariableValue<T>(
    val key: String,
    val value: T,
    val isDefault: Boolean = false
)