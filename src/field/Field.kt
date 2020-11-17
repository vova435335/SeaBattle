package field

import Position
import field.field_items.FieldItem
import field.field_items.Water
import util.FieldUtil.ITEM_INLINE_COUNT
import util.FieldUtil.LINE_COUNT

class Field {

    val items = mutableMapOf<Position, FieldItem>()

    init {
        for (col in 1..ITEM_INLINE_COUNT) {
            for(row in 1..LINE_COUNT) {
                items[Position(col, row)] = Water()
            }
        }
    }
}