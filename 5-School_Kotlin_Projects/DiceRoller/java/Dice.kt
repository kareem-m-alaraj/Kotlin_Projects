import androidx.appcompat.app.AppCompatActivity

class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}