package aaa.cubeup.cube.data.nonentities

interface ZZZZButtonable {
//	val name: String?

	fun findName(): String
	fun findId(): String?
	fun cattype(): Cattype
}

interface BaseBVable : ZZZZButtonable {
	fun rightLabel(): String?
}

interface DTOable : BaseBVable {
	fun findAppliedUid(): String?
	fun findInitiallyApplied(): Boolean
}