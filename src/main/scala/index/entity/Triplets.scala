package index.entity

case class Triplets(
                     subject: String,
                     predicate: String,
                     tobject: String
                   )

case class Schema(
                 id: String,
                 triplets: Triplets
                 )
