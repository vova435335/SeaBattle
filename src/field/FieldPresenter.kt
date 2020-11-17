package field

class FieldPresenter(private val view: FieldView) {

    private val model = FieldModel()

    fun start() {
        view.drawField(model.field)
    }
}