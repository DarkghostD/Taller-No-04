package object MatchingProblem {
  type Match = (Int, Int)
  type Matching = List[Match]
  type Preferences = Vector[Vector[Int]]

  def recursion(i: Int, n: Int, current: List[Match]): List[List[Match]] = {
    if (i > n) {
      List(current)
    } else {
      (1 to n).toList.flatMap { j =>
        recursion(i + 1, n, current :+ (i, j))
      }
    }
  }

  def matchByElement(i: Int, n: Int): List[Match] = {
    (1 to n).map(j => (i, j)).toList
  }

  def matchsByElements(n: Int): List[List[Match]] = {
    (1 to n).toList.map(i => matchByElement(i, n)) // Llama a matchByElement para cada piloto del 1 al n
  }

  def possibleMatchings(n: Int): List[List[Match]] = {
    recursion(1, n, List())
  }

  def matchings(n: Int): List[Matching] = {
    possibleMatchings(n).filter(matching => matching.map(_._2).distinct.length == n)
  }

  def weight(matching: Matching, pilotPrefs: Preferences, navigPrefs: Preferences): Int = {
    matching.map { case (i, j) => pilotPrefs(i - 1)(j - 1) * navigPrefs(j - 1)(i - 1) }.sum
  }

  def weightedMatchings(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): List[(Matching, Int)] = {
    matchings(n).map(matching => (matching, weight(matching, pilotPrefs, navigPrefs)))
  }

  def bestMatching(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): (Matching, Int) = {
    weightedMatchings(n, pilotPrefs, navigPrefs).maxBy(_._2)
  }
}
