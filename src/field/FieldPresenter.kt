package field

import Position
import field.field_items.*
import util.FieldUtil

class FieldPresenter(private val view: FieldView) {

    private var model = FieldModel()

    fun start() {
        view.drawField(model.field)
    }

    fun shot(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is Water -> {
                model.field.items[position] = Miss()
            }
            is Ship -> {
                model.field.items[position] = WoundShip()
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun constructor(position: Position) {
        val item = model.field.items[position]

        when (item) {
            is Water -> {
                model.field.items[position] = Ship()
            }
            is Ship -> {
                model.field.items[position] = Water()
            }
            else -> {
                //Do nothing
            }
        }
        view.drawField(model.field)
    }

    fun getItems(): Int {
        var count = 0
        for (col in 1..FieldUtil.ITEM_INLINE_COUNT) {
            for (row in 1..FieldUtil.LINE_COUNT) {
                if(model.field.items[Position(col, row)] is Ship){
                    count++
                }
            }
        }
        return count
    }

}