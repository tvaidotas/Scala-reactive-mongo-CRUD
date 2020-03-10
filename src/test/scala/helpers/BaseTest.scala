package helpers

import org.scalatest.{BeforeAndAfter, FlatSpec}
import org.scalatestplus.mockito.MockitoSugar

abstract class BaseTest extends FlatSpec with MockitoSugar with  BeforeAndAfter
