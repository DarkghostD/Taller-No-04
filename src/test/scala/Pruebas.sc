import MatchingProblem._

val pilot = Vector(Vector(2, 3, 1, 1), Vector(1, 1, 4, 3), Vector(1, 2, 3, 4), Vector(2, 3, 2, 1))

val navig = Vector(Vector(4, 1, 3, 2),Vector(4, 2, 3, 1), Vector(1, 1, 1, 4), Vector(3, 2, 3, 3))

matchByElement(2, 5)
matchByElement(3, 5)
matchByElement(4, 5)
matchByElement(5, 5)
matchByElement(1, 5)

matchsByElements(3)
matchsByElements(4)
matchsByElements(5)
matchsByElements(2)
matchsByElements(6)

possibleMatchings(2)
possibleMatchings(3)
possibleMatchings(4)
possibleMatchings(5).length
possibleMatchings(6).length

matchings(2)
matchings(3)
matchings(4)
matchings(5).length
matchings(6).length

weightedMatchings(2, pilot, navig)
weightedMatchings(3, pilot, navig)
weightedMatchings(4, pilot, navig)
weightedMatchings(2, pilot, navig)
weightedMatchings(3, pilot, navig)

bestMatching(2, pilot, navig)
bestMatching(3, pilot, navig)
bestMatching(4, pilot, navig)
bestMatching(2, pilot, navig)
bestMatching(3, pilot, navig)
