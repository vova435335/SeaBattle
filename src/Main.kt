import game.GameView
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import screens.AlienScreen
import screens.OwnScreen

class Main : Application() {

    private lateinit var root: AnchorPane
    private val gameScreen = GameView()
//    private val ownScreen = OwnScreen()
//    private val alienScreen = AlienScreen()

    override fun start(primaryStage: Stage) {
        initialize(primaryStage)

        gameScreen.render(root)
//        root.children.add(alienScreen)
//        root.children.add(ownScreen)
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