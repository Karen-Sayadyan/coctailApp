import com.arkivanov.decompose.ComponentContext


class DetailComponent(
    componentContext: ComponentContext,
    val cocktailId: String
) : ComponentContext by componentContext {

}