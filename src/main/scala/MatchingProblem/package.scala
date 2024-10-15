package object MatchingProblem {
  type Match = (Int, Int)
  type Matching = List[Match]
  type Preferences = Vector[Vector[Int]]

  // Función recursiva que genera las combinaciones de emparejamientos
  def recursion(i: Int, n: Int, current: List[Match]): List[List[Match]] = {
    if (i > n) {
      List(current) // Devuelve la combinación actual cuando i excede n
    } else {
      // Genera una lista de combinaciones para el elemento i
      (1 to n).toList.flatMap { j =>
        recursion(i + 1, n, current :+ (i, j)) // Llama recursivamente para el siguiente i
      }
    }
  }

  // Genera la lista de emparejamientos posibles para un piloto dado
  def matchByElement(i: Int, n: Int): List[Match] = {
    (1 to n).map(j => (i, j)).toList
  }

  // Genera la lista de emparejamientos posibles por cada piloto
  def matchsByElements(n: Int): List[List[Match]] = {
    (1 to n).toList.map(i => matchByElement(i, n)) // Llama a matchByElement para cada piloto del 1 al n
  }

  // Usa la función recursiva para generar todos los emparejamientos posibles
  def possibleMatchings(n: Int): List[List[Match]] = {
    recursion(1, n, List())
  }

  // Devuelve la lista de todos los emparejamientos válidos (sin repetir copilotos)
  def matchings(n: Int): List[Matching] = {
    possibleMatchings(n).filter(matching => matching.map(_._2).distinct.length == n)
  }

  // Calcula el peso de un emparejamiento dado
  def weight(matching: Matching, pilotPrefs: Preferences, navigPrefs: Preferences): Int = {
    matching.map { case (i, j) => pilotPrefs(i - 1)(j - 1) * navigPrefs(j - 1)(i - 1) }.sum
  }

  // Devuelve la lista de todos los emparejamientos válidos con su peso
  def weightedMatchings(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): List[(Matching, Int)] = {
    matchings(n).map(matching => (matching, weight(matching, pilotPrefs, navigPrefs)))
  }

  // Devuelve el emparejamiento válido de mayor peso
  def bestMatching(n: Int, pilotPrefs: Preferences, navigPrefs: Preferences): (Matching, Int) = {
    weightedMatchings(n, pilotPrefs, navigPrefs).maxBy(_._2)
  }
}
