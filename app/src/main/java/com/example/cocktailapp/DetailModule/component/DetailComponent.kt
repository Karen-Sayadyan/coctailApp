import com.arkivanov.decompose.ComponentContext


class DetailComponent(
    componentContext: ComponentContext,
    val cocktailId: String,
    val goBack: () -> Unit
) : ComponentContext by componentContext {
    fun goBackScreen() {
        goBack()
    }
}

