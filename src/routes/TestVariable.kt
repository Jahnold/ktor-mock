package codes.arnold.matt.routes

import codes.arnold.matt.mockapp.variable.MockVariable
import codes.arnold.matt.mockapp.variable.VariableValue

class TestVariable: MockVariable<Boolean>() {

    override val name = "Feature Flag 1"
    override val values = listOf(
        VariableValue("Enabled", value = true, isDefault = true),
        VariableValue("Disabled", value = true)
    )

}