package strategy

class WallExtendGenerator(
    width: Int,
    height: Int,
) : BaseGenerator(width, height) {
    override fun buildMap() {
        setStartAndGoal()
    }
}
