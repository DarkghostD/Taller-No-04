package object MatchingProblem {
  type Match = (Int, Int)
  type Matching = List[Match]
  type Preferences = Vector[Vector[Int]]

  def matchByElement(i: Int, n: Int): List[Match] = {
    (1 to n).map(j => (i, j)).toList
  }

  def matchsByElements(n: Int): List[List[Match]] = {
    (1 to n).map(i => matchByElement(i, n)).toList
  }

  def possibleMatchings(n: Int): List[List[Match]] = {
    matchsByElements(n)
  }

  def matchings(n: Int): List[Matching] = {
    possibleMatchings(n).flatMap(_.combinations(n)).map(_.toList)
  }

  def weightedMatchings(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): List[(Matching, Int)] = {
    matchings(n).map { matching =>
      val weight = matching.map { case (i, j) =>
        pilotPrefs(i - 1)(j - 1) * navigPrefs(j - 1)(i - 1)
      }.sum
      (matching, weight)
    }
  }

  def bestMatching(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): (Matching, Int) = {
    weightedMatchings(n, pilotPrefs, navigPrefs).maxBy(_._2)
  }
}