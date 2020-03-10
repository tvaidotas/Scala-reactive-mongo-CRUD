package models

import helpers.BaseTest

class PersonTest extends BaseTest {

  "Trying to create a person object" should "produce the person object" in {
    assert(Person("name","surname").isInstanceOf[Person])
  }

}
