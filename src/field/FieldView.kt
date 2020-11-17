package field

import javafx.scene.layout.AnchorPane
import javafx.scene.shape.Line
import util.FieldUtil.ITEM_INLINE_COUNT
import util.FieldUtil.ITEM_SIZE
import util.FieldUtil.LINE_COUNT

class FieldView {

    private lateinit var canvas: AnchorPane

    private var x = 0
    private var y = 0

    fun render(canvas: AnchorPane, x: Int, y: Int) {
        this.x = x
        this.y = y
        this.canvas = canvas

        drawFieldGreed()
    }

    private fun drawFieldGreed() {
        for (lineCounter in 0..LINE_COUNT) {
            canvas.children.add(
                Line(
                    0.0 + x,
                    (lineCounter * ITEM_SIZE).toDouble() + y,
                    (ITEM_SIZE * ITEM_INLINE_COUNT).toDouble() + x,
                    (lineCounter * ITEM_SIZE).toDouble() + y
                )
            )
            canvas.children.add(
                Line(
                    (lineCounter * ITEM_SIZE).toDouble() + x,
                    0.0 + y,
                    (lineCounter * ITEM_SIZE).toDouble() + x,
                    (ITEM_SIZE * ITEM_INLINE_COUNT).toDouble() + y
                )
            )
        }
    }
}