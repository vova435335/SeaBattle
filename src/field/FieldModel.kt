package field

import field.field_items.Ship

class FieldModel {

    val field = Field()
    val ships = mutableListOf(
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship(),
        Ship()
    )

    init {
        ships[0].size = 4
        ships[1].size = 3
        ships[2].size = 3
        ships[3].size = 2
        ships[4].size = 2
        ships[5].size = 2
        ships[6].size = 1
        ships[7].size = 1
        ships[8].size = 1
        ships[9].size = 1
    }

}