import game.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage

class Main : Application() {

    private lateinit var root: AnchorPane
    private val gameScreen = GameView()

    override fun start(primaryStage: Stage) {
        initialize(primaryStage)

        root.style = "-fx-background-color: #ffffff"
        gameScreen.render(root)
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