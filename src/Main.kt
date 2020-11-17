import field.FieldView
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class Main : Application() {

    private lateinit var root: AnchorPane
    private val playerFieldView = FieldView()
    private val opponentFieldView = FieldView()

    override fun start(primaryStage: Stage) {
        initialize(primaryStage)

        playerFieldView.render(root, 50, 50)
        opponentFieldView.render(root, 550, 50)
    }

    private fun initialize(primaryStage: Stage) {
        root = AnchorPane()
        primaryStage.title = "Морской бой"
        primaryStage.scene = Scene(root, 1000.0, 800.0)
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}