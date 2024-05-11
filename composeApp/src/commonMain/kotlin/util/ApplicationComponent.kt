package util

object ApplicationComponent {
    private var _coreComponent: CoreComponent? = null
    val coreComponent
        get() = _coreComponent
            ?: throw IllegalStateException("Make sure to call ApplicationComponent.init()")

    fun init() {
        _coreComponent = CoreComponentImpl()
    }
}

val coreComponent get() = ApplicationComponent.coreComponent