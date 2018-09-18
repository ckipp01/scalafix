package scalafix.v1
// scalafmt: { maxColumn = 120 }

import scalafix.internal.util.Pretty

/**
 * Encoding of synthetic trees that are generated by inferred type parameters, implicits and <code>.apply</code>.
 */
sealed abstract class SemanticTree extends Product with Serializable {
  final override def toString: String = Pretty.pretty(this).render(80)
  final def isEmpty: Boolean = this == NoTree
  final def nonEmpty: Boolean = !isEmpty
}
final case class IdTree(symbol: Symbol) extends SemanticTree
final case class SelectTree(qualifier: SemanticTree, id: IdTree) extends SemanticTree
final case class ApplyTree(function: SemanticTree, arguments: List[SemanticTree]) extends SemanticTree
final case class TypeApplyTree(function: SemanticTree, typeArguments: List[SemanticType]) extends SemanticTree
final case class FunctionTree(parameters: List[IdTree], body: SemanticTree) extends SemanticTree
final case class LiteralTree(constant: Constant) extends SemanticTree
final case class MacroExpansionTree(beforeExpansion: SemanticTree, tpe: SemanticType) extends SemanticTree
final case class OriginalSubTree(tree: scala.meta.Tree) extends SemanticTree
final case class OriginalTree(tree: scala.meta.Tree) extends SemanticTree
case object NoTree extends SemanticTree
