import org.scalatest.{FlatSpec, Matchers}

class Test extends FlatSpec with Matchers {

  it should "mapF None" in {
    val some: Option[Int] = Some(3)
    val none: Option[Int] = None

    some mapF (_ + 1) shouldBe Some(4)
    none mapF (_ + 1) shouldBe None
  }

  it should "hold functor laws for Option" in {
    val some: Option[Int] = Some(3)
    val none: Option[Int] = None

    val plusOne: Int => Int = _ + 1
    val plusTwo: Int => Int = _ + 2
    val id: Int => Int = i => i

    some mapF id shouldBe some
    none mapF id shouldBe none

    (some mapF plusOne) mapF plusTwo shouldBe (some mapF plusOne.andThen(plusTwo))
    (some mapF plusOne) mapF plusTwo shouldBe (some mapF plusTwo compose plusOne)
    (none mapF plusOne) mapF plusTwo shouldBe (none mapF plusOne.andThen(plusTwo))
    (none mapF plusOne) mapF plusTwo shouldBe (none mapF plusTwo compose plusOne)
  }

  it should "lift the function for Option" in {
    val lenOption: Option[String] => Option[Int] = ???

    lenOption(None) shouldBe None
    lenOption(Some("")) shouldBe Some(0)
    lenOption(Some("abc")) shouldBe Some(3)
    lenOption(Some("something")) shouldBe Some(9)
  }

  it should "mapF List" in {
    val nil: List[Int] = Nil

    nil mapF (_ + 1) shouldBe Nil
    List(0) mapF (_ + 1) shouldBe List(1)
    List(1, 2, 3) mapF (_ + 1) shouldBe List(2, 3, 4)
  }

  it should "lift the function for List" in {
    val lenList: List[String] => List[Int] = ???

    lenList(Nil) shouldBe Nil
    lenList(List()) shouldBe List()
    lenList(List("")) shouldBe List(0)
    lenList(List("a", "ab", "abc")) shouldBe List(1, 2, 3)
  }

  it should "map List[Option]" in {
    val list: List[Option[Int]] = List(Some(2), None, Some(0))
    val mappedList: List[Option[Int]] = list.mapF(_.mapF(_ + 1))

    mappedList shouldBe List(Some(3), None, Some(1))
  }
}
