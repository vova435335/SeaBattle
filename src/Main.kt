import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

    override fun start(primaryStage: Stage?) {
        val root = FXMLLoader.load<Parent>(javaClass.getResource("views/main.fxml"))
        primaryStage!!.title = "Морской бой"
        primaryStage.scene = Scene(root, 600.0, 400.0)
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }
    }
}